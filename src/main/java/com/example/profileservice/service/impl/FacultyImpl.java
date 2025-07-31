package com.example.profileservice.service.impl;

import com.example.profileservice.dto.FacultyDTO;
import com.example.profileservice.entity.Faculty;
import com.example.profileservice.exception.FacultyNotFoundException;
import com.example.profileservice.repository.FacultyRepository;
import com.example.profileservice.service.FacultyService;
import com.example.profileservice.service.SequenceGeneratorService;
import com.mongodb.client.result.UpdateResult;
import lombok.Data;
import org.apache.poi.ss.usermodel.*;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class FacultyImpl implements FacultyService {

    @Autowired
    private FacultyRepository facultyRepository;

    @Autowired
    private MongoTemplate mongoTemplate;

    @Autowired
    private SequenceGeneratorService sequenceGeneratorService;

    private FacultyDTO convertToDTO(Faculty faculty) {
        FacultyDTO dto = new FacultyDTO();
        BeanUtils.copyProperties(faculty, dto);
        return dto;
    }

    private Faculty convertToEntity(FacultyDTO dto) {
        Faculty faculty = new Faculty();
        BeanUtils.copyProperties(dto, faculty);
        return faculty;
    }

    @Override
    public FacultyDTO createFaculty(FacultyDTO dto) {
        Faculty faculty = convertToEntity(dto);
        long nextSeq = sequenceGeneratorService.getNextSequence("faculty_sequence");
        faculty.setFacultyCode("CS" + String.format("%03d", nextSeq)); // CS001, CS002

        Faculty saved = facultyRepository.save(faculty);
        return convertToDTO(saved);
    }

    @Override
    public FacultyDTO getFacultyById(String facultyCode) {
        Faculty faculty = facultyRepository.findByFacultyCode(facultyCode)
                .orElseThrow(() -> new FacultyNotFoundException("Faculty not found with code: " + facultyCode));
        return convertToDTO(faculty);
    }

    @Override
    public List<FacultyDTO> getAllFaculty() {
        return facultyRepository.findAll()
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public FacultyDTO updateFaculty(String facultyCode, FacultyDTO dto) {
        Faculty faculty = facultyRepository.findByFacultyCode(facultyCode)
                .orElseThrow(() -> new FacultyNotFoundException("Faculty not found with code: " + facultyCode));
        // Update fields if needed using setters
        return convertToDTO(facultyRepository.save(faculty));
    }

    @Override
    public List<FacultyDTO> getFacultyByDepartment(String department) {
        List<Faculty> facultyList = facultyRepository.findByDepartment(department);
        return facultyList.stream()
                .map(this::convertToDTO)
                .toList();
    }

    @Override
    public List<FacultyDTO> getFacultyByRole(String role) {
        List<Faculty> facultyList = facultyRepository.findByRole(role);
        return facultyList.stream()
                .map(this::convertToDTO)
                .toList();
    }

    @Override
    public List<FacultyDTO> searchFaculty(String query) {
        List<Faculty> matched = facultyRepository.findByFirstNameContainingIgnoreCaseOrLastNameContainingIgnoreCaseOrEmailContainingIgnoreCaseOrFacultyCodeContainingIgnoreCase(
                query, query, query, query
        );
        return matched.stream().map(this::convertToDTO).toList();
    }

    @Override
    public void deleteFaculty(String facultyCode) {
        facultyRepository.deleteByFacultyCode(facultyCode);
    }

    @Override
    public void partialUpdateFaculty(String facultyCode, Map<String, Object> updates) {
        Query query = new Query(Criteria.where("facultyCode").is(facultyCode));
        Update update = new Update();

        updates.forEach(update::set);

        UpdateResult result = mongoTemplate.updateFirst(query, update, Faculty.class);

        if (result.getMatchedCount() == 0) {
            throw new FacultyNotFoundException("Faculty not found with code " + facultyCode);
        }
    }

    @Override
    public long getFacultyCount() {
        return facultyRepository.count();
    }

    @Override
    public void bulkUploadFaculty(MultipartFile file) {
        try (Workbook workbook = WorkbookFactory.create(file.getInputStream())) {
            Sheet sheet = workbook.getSheetAt(0);
            DataFormatter formatter = new DataFormatter(); // Safe conversion

            for (int i = 1; i <= sheet.getLastRowNum(); i++) { // start at 1 (skip header)
                Row row = sheet.getRow(i);
                if (row == null) continue;

                Faculty faculty = new Faculty();
                faculty.setFirstName(formatter.formatCellValue(row.getCell(0)));
                faculty.setLastName(formatter.formatCellValue(row.getCell(1)));
                faculty.setEmail(formatter.formatCellValue(row.getCell(2)));
                faculty.setDepartmentId(formatter.formatCellValue(row.getCell(3)));
                faculty.setGender(formatter.formatCellValue(row.getCell(4)));
                faculty.setAddress(formatter.formatCellValue(row.getCell(5)));

                // ✅ DOB & joiningDate: parse if not empty
                String dobStr = formatter.formatCellValue(row.getCell(6));
                if (!dobStr.isEmpty()) {
                    DateTimeFormatter formater = DateTimeFormatter.ofPattern("M/d/yy");
                    faculty.setDob(LocalDate.parse(dobStr, formater));
                }

                faculty.setBloodGroup(formatter.formatCellValue(row.getCell(7)));

                String expStr = formatter.formatCellValue(row.getCell(8));
                faculty.setExperience(expStr.isEmpty() ? 0 : Integer.parseInt(expStr));

                String joiningDateStr = formatter.formatCellValue(row.getCell(9));
                if (!joiningDateStr.isEmpty()) {
                    DateTimeFormatter formater = DateTimeFormatter.ofPattern("M/d/yy");
                    faculty.setJoiningDate(LocalDate.parse(joiningDateStr, formater));
                }

                // ✅ Fixed here: use setDegree not setEducationQualification
                faculty.setDegree(formatter.formatCellValue(row.getCell(10)));

                faculty.setPhotoUrl(formatter.formatCellValue(row.getCell(11)));
                faculty.setContact(formatter.formatCellValue(row.getCell(12)));

                long nextSeq = sequenceGeneratorService.getNextSequence("faculty_sequence");
                faculty.setFacultyCode("CS" + String.format("%03d", nextSeq));

                // ✅ courseIds: assume comma-separated string
                String coursesStr = formatter.formatCellValue(row.getCell(13));
                if (!coursesStr.isEmpty()) {
                    faculty.setCourseIds(Arrays.asList(coursesStr.split(",")));
                }

                facultyRepository.save(faculty); // facultyCode auto-generated
            }

        } catch (Exception e) {
            throw new RuntimeException("Bulk upload failed: " + e.getMessage(), e);
        }
    }
}
