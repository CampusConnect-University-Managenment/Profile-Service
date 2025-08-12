package com.example.profileservice.repository;
import com.example.profileservice.entity.AssignmentEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface AssignmentRepository extends MongoRepository<AssignmentEntity, String> {
    List<AssignmentEntity> findByStudentRollNo(String studentRollNo);
}
