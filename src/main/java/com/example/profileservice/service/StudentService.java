package com.example.profileservice.service;

import com.example.profileservice.entity.StudentEntity;
import com.example.profileservice.repository.StudentSummary;

import java.util.List;
import java.util.Optional;


public interface StudentService {
    long getTotalStudentCount();

    List<StudentEntity> saveAllStudents(List<StudentEntity> students);
    List<StudentEntity> GetallStudents();
    StudentEntity AddStudents(StudentEntity studentEntity);
    List<StudentEntity> GetStudentByDepartment(String studentDepartment);
    List<StudentEntity> GetStudentByYear(String studentYear);
    Optional<StudentEntity> findByStudentRollNo(String studentRollNo);
    StudentEntity DeleteById(String studentId);
    StudentEntity UpdateStudent(String studentId, StudentEntity updateStudent);
}
