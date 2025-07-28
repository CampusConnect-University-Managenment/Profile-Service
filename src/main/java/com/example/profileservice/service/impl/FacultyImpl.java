package com.example.profileservice.service.impl;

import com.example.profileservice.dto.FacultyDTO;
import com.example.profileservice.entity.Faculty;
import com.example.profileservice.exception.FacultyNotFoundException;
import com.example.profileservice.repository.FacultyRepository;
import com.example.profileservice.service.FacultyService;
import lombok.Data;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
@Data
@Service
public class FacultyImpl implements FacultyService {

    @Autowired
    private FacultyRepository facultyRepository;

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
        return convertToDTO(facultyRepository.save(faculty));
    }

    @Override
    public FacultyDTO updateFaculty(String id, FacultyDTO dto) {
        Faculty faculty = facultyRepository.findById(id)
                .orElseThrow(() -> new FacultyNotFoundException("Faculty not found with id " + id));
        faculty.setFirstName(dto.getFirstName());
        faculty.setLastName(dto.getLastName());
        faculty.setEmail(dto.getEmail());
        faculty.setDepartment(dto.getDepartment());
        faculty.setRole(dto.getRole());
        faculty.setPhotoUrl(dto.getPhotoUrl());
        return convertToDTO(facultyRepository.save(faculty));
    }

    @Override
    public FacultyDTO getFacultyById(String id) {
        Faculty faculty = facultyRepository.findById(id)
                .orElseThrow(() -> new FacultyNotFoundException("Faculty not found with id " + id));
        return convertToDTO(faculty);
    }

    @Override
    public List<FacultyDTO> getAllFaculty() {
        return facultyRepository.findAll().stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    @Override
    public void deleteFaculty(String id) {
        facultyRepository.deleteById(id);
    }
}
