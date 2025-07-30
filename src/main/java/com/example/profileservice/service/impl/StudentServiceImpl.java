package com.example.profileservice.service.impl;

import java.util.List;
import java.util.Optional;

import com.example.profileservice.entity.StudentEntity;
import com.example.profileservice.exception.StudentNotFoundException;
import com.example.profileservice.repository.StudentRepository;
import com.example.profileservice.service.StudentService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;

    @Autowired
    public StudentServiceImpl(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Override
    public List<StudentEntity> GetallStudents() {
        return studentRepository.findAll();
    }

    @Override
    public List<StudentEntity> GetStudentByDepartment(String studentDepartment) {
        Optional<List<StudentEntity>> request = studentRepository.findByStudentDepartment(studentDepartment);
        if (request.isEmpty()) {
            throw new StudentNotFoundException("Students Not Found in this Department: " + studentDepartment);
        }
        return request.get();
    }
    @Override
    public List<StudentEntity> saveAllStudents(List<StudentEntity> students) {
        // Make sure studentId is null so MongoDB will auto-generate
        for (StudentEntity student : students) {
            student.setStudentId(null);
        }
        return studentRepository.saveAll(students);
    }

    @Override
    public Optional<StudentEntity> findById(String studentId){
        Optional<StudentEntity>  request = studentRepository.findById(studentId);
        if(request.isEmpty()){
            throw new StudentNotFoundException("Student Not Found in this Id:"+studentId);
        }
        return request;
    }

    @Override
    public StudentEntity UpdateStudent(String studentId, StudentEntity updateStudent) {
        Optional<StudentEntity> req = studentRepository.findById(studentId);
        if (req.isEmpty()) {
            throw new StudentNotFoundException("Student not found with this Id: " + studentId);
        } else {
            StudentEntity existingStudent = req.get();

            // Logging IDs before and after copying for debugging
            System.out.println("Before copy, existing ID: " + existingStudent.getStudentId());

            // Use correct field name "studentId" with exact case
            BeanUtils.copyProperties(updateStudent, existingStudent, "studentId", "createdDate");

            System.out.println("After copy, existing ID: " + existingStudent.getStudentId());

            return studentRepository.save(existingStudent);
        }
    }

    @Override
    public StudentEntity AddStudents(StudentEntity studentEntity) {
        return studentRepository.save(studentEntity);
    }

    @Override
    public StudentEntity DeleteById(String studentId) {
        Optional<StudentEntity> existingRequest = studentRepository.findById(studentId);
        if (existingRequest.isEmpty()) {
            throw new StudentNotFoundException("Student not found with this id: " + studentId);
        }
        studentRepository.delete(existingRequest.get());
        return existingRequest.get();
    }
}
