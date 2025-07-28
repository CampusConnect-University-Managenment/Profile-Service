package com.example.profileservice.repository;

import com.example.profileservice.entity.Faculty;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Repository
public interface FacultyRepository extends MongoRepository<Faculty,String> {
//    List<Faculty> findByFacultyId(String FacultyId);
}
