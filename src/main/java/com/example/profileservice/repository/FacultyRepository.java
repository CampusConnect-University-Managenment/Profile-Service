package com.example.profileservice.repository;

import com.example.profileservice.entity.Faculty;
import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.List;

public interface FacultyRepository extends MongoRepository<Faculty,String> {
    List<Faculty> findByDepartment(String department);
    List<Faculty> findByRole(String role);
}
