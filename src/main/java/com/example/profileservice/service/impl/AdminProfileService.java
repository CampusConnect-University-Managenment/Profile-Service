package com.example.profileservice.service.impl;

import com.example.profileservice.entity.AdminProfile;
import com.example.profileservice.repository.AdminProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminProfileService {

    @Autowired
    private AdminProfileRepository repository;

    // Save admin profile with all fields
    public AdminProfile saveAdmin(AdminProfile profile) {
        return repository.save(profile);
    }

    public List<AdminProfile> getAllAdmins() {
        return repository.findAll();
    }

    public AdminProfile getAdminByCode(String adminCode) {
        return repository.findByAdminCode(adminCode)
                .orElseThrow(() -> new RuntimeException("Admin not found with code: " + adminCode));
    }

    public AdminProfile updatePassword(String adminCode, String newPassword) {
        AdminProfile admin = repository.findByAdminCode(adminCode)
                .orElseThrow(() -> new RuntimeException("Admin not found with code: " + adminCode));

        admin.setPassword(newPassword); // Update password

        return repository.save(admin); // Save to DB
    }

}
