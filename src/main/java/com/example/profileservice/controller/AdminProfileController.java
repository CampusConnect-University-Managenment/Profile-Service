package com.example.profileservice.controller;

import com.example.profileservice.entity.AdminProfile;
import com.example.profileservice.service.impl.AdminProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Map;

import java.util.List;

@RestController
@RequestMapping("/api/admin")
@CrossOrigin(origins = "*") // allow all origins, or restrict to your frontend URL
public class AdminProfileController {

    @Autowired
    private AdminProfileService service;

    // Create admin
    @PostMapping("/create")
    public ResponseEntity<AdminProfile> createAdmin(@RequestBody AdminProfile profile) {
        AdminProfile saved = service.saveAdmin(profile);
        return ResponseEntity.ok(saved);
    }

    // Get admin by adminCode
    @GetMapping("/{adminCode}")
    public ResponseEntity<AdminProfile> getByAdminCode(@PathVariable String adminCode) {
        AdminProfile profile = service.getAdminByCode(adminCode);
        return ResponseEntity.ok(profile);
    }

    // Get all admins
    @GetMapping("/all")
    public ResponseEntity<List<AdminProfile>> getAll() {
        return ResponseEntity.ok(service.getAllAdmins());
    }

    // Change password
    @PutMapping("/{adminCode}/change-password")
    public ResponseEntity<AdminProfile> changePassword(@PathVariable String adminCode,
                                                       @RequestBody Map<String, String> request) {
        String newPassword = request.get("password"); // This must match key in Postman
        AdminProfile updated = service.updatePassword(adminCode, newPassword);
        return ResponseEntity.ok(updated);
    }
    // NEW: Update photo URL
    @PutMapping("/{adminCode}/update-photo")
    public ResponseEntity<AdminProfile> updatePhotoUrl(@PathVariable String adminCode,
                                                       @RequestBody Map<String, String> request) {
        String photoUrl = request.get("photoUrl");
        return ResponseEntity.ok(service.updatePhotoUrl(adminCode, photoUrl));
    }
}
