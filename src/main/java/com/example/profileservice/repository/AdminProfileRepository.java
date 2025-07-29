package com.example.profileservice.repository;

import com.example.profileservice.entity.AdminProfile;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface AdminProfileRepository extends MongoRepository<AdminProfile, String> {
    Optional<AdminProfile> findByAdminCode(String adminCode);
    boolean existsByAdminCode(String adminCode);
}
