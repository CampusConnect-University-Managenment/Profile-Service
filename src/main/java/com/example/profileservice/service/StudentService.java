package com.example.profileservice.service;

import com.example.profileservice.entity.StudentEntity;

import java.util.List;
import java.util.Optional;

public interface StudentService {
    List<StudentEntity> GetallStudents();
    StudentEntity AddStudents(StudentEntity studentEntity);
    List<StudentEntity> GetStudentByDepartment(String studentDepartment);
    Optional<StudentEntity> findById(String studentId);
    StudentEntity DeleteById(String studentId);
    StudentEntity UpdateStudent(String studentId, StudentEntity updateStudent);
}
