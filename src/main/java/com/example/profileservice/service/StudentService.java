package com.example.profileservice.service;

import com.example.profileservice.entity.StudentEntity;
import com.example.profileservice.repository.StudentSummary;

import java.util.List;
import java.util.Optional;


public interface StudentService {
    List<StudentEntity> saveAllStudents(List<StudentEntity> students);
    List<StudentEntity> GetallStudents();
    StudentEntity AddStudents(StudentEntity studentEntity);
    List<StudentEntity> GetStudentByDepartment(String studentDepartment);
    Optional<StudentSummary> findByStudentRollNo(String studentRollNo);
    Optional<StudentEntity> findById(String studentId);
    StudentEntity DeleteById(String studentId);
    StudentEntity UpdateStudent(String studentId, StudentEntity updateStudent);
}