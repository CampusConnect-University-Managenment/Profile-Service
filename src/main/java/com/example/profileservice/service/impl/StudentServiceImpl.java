package com.example.profileservice.service.impl;
import com.example.profileservice.dto.AuthUserRequest;
import java.util.List;
import java.util.Optional;
import com.example.profileservice.entity.StudentEntity;
import com.example.profileservice.exception.StudentNotFoundException;
import com.example.profileservice.feign.AuthServiceClient;
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
        Optional<StudentEntity> request = studentRepository.findByStudentRollNo(studentRollNo);
        if (request.isEmpty()) {
            throw new StudentNotFoundException("Student not found with RollNo: " + studentRollNo);
        }
        return request;
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
    @Autowired
    private AuthServiceClient authServiceClient;

    @Override
    public StudentEntity AddStudents(StudentEntity student) {
        String department = student.getStudentDepartment().toUpperCase();

        Optional<StudentEntity> lastStudentOpt =
                studentRepository.findTopByStudentDepartmentOrderByStudentRollNoDesc(department);

        int nextNumber = 1;
        if (lastStudentOpt.isPresent()) {
            String lastRollNo = lastStudentOpt.get().getStudentRollNo();
            String numberPart = lastRollNo.replaceAll("\\D+", "");
            if (!numberPart.isEmpty()) {
                nextNumber = Integer.parseInt(numberPart) + 1;
            }
        }

        String newRollNo = department + String.format("%03d", nextNumber);
        student.setStudentRollNo(newRollNo);

        StudentEntity saved = studentRepository.save(student);

        // Prepare official credentials
        String officialEmail = newRollNo + "@university.edu";
        String password = "Student@123";

        // Call Auth Service (Feign Client)
        authServiceClient.registerStudent(
                new AuthUserRequest(saved.getStudentRollNo(), officialEmail, password)
        );

        return saved;
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
