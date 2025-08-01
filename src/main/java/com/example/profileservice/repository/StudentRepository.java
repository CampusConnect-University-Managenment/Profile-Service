package com.example.profileservice.repository;

import com.example.profileservice.entity.StudentEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StudentRepository extends MongoRepository<StudentEntity,String> {
    long count();

    Optional<List<StudentEntity>> findByStudentDepartment(String studentDepartment);
    Optional<List<StudentEntity>> findByStudentYear(String studentYear);
    Optional<StudentEntity> findByStudentRollNo(String studentRollNo);
    Optional<StudentEntity> findTopByStudentDepartmentOrderByStudentRollNoDesc(String studentDepartment);
}
