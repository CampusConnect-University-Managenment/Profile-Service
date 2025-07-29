package com.example.profileservice.repository;

import com.example.profileservice.entity.Faculty;
import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.Optional;

public interface FacultyRepository extends MongoRepository<Faculty,String> {
    Optional<Faculty> findByFacultyCode(String facultyCode);
    void deleteByFacultyCode(String facultyCode);
}
