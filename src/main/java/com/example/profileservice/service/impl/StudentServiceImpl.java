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
    public long getTotalStudentCount() {
        return studentRepository.count();
    }

    @Override
    public List<StudentEntity> GetallStudents() {
        return studentRepository.findAll();
    }

    @Override
    public Optional<StudentEntity> findByStudentRollNo(String studentRollNo) {
        // Return Optional instead of throwing directly
        return studentRepository.findByStudentRollNo(studentRollNo);
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
    public List<StudentEntity> GetStudentByYear(String studentYear) {
        Optional<List<StudentEntity>> request = studentRepository.findByStudentYear(studentYear);
        if (request.isEmpty()) {
            throw new StudentNotFoundException("Students Not Found in this Year: " + studentYear);
        }
        return request.get();
    }

    @Override
    public List<StudentEntity> saveAllStudents(List<StudentEntity> students) {
        for (StudentEntity student : students) {
            student.setStudentId(null);
        }
        return studentRepository.saveAll(students);
    }

    @Override
    public StudentEntity UpdateStudent(String studentRollNo, StudentEntity updateStudent) {
        Optional<StudentEntity> req = studentRepository.findByStudentRollNo(studentRollNo);

        if (req.isEmpty()) {
            throw new StudentNotFoundException("Student not found with Roll No: " + studentRollNo);
        }

        StudentEntity existingStudent = req.get();
        BeanUtils.copyProperties(updateStudent, existingStudent, "studentId", "studentRollNo");
        System.out.println("Updated student: " + existingStudent.getStudentFirstname() + " " + existingStudent.getStudentLastname());
        return studentRepository.save(existingStudent);
    }

    @Override
    public StudentEntity AddStudents(StudentEntity student) {
        // Only generate roll number if not provided
        if (student.getStudentRollNo() == null || student.getStudentRollNo().isBlank()) {
            String department = student.getStudentDepartment().toUpperCase();

            Optional<StudentEntity> lastStudentOpt =
                    studentRepository.findTopByStudentDepartmentOrderByStudentRollNoDesc(department);

            int nextNumber = 1;
            if (lastStudentOpt.isPresent()) {
                String lastRollNo = lastStudentOpt.get().getStudentRollNo();
                String numberPart = lastRollNo.replaceAll("\\D+", ""); // Extract digits
                if (!numberPart.isEmpty()) {
                    nextNumber = Integer.parseInt(numberPart) + 1;
                }
            }

            String newRollNo = department + String.format("%03d", nextNumber); // e.g., IT007
            student.setStudentRollNo(newRollNo);
        }

        return studentRepository.save(student);
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
