package com.example.profileservice.controller;

import com.example.profileservice.dto.FacultyDTO;
import com.example.profileservice.service.FacultyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/admin/faculty")
public class FacultyController {

    @Autowired
    private FacultyService facultyService;

    @PostMapping(produces = "application/json", consumes = "application/json")
    public ResponseEntity<FacultyDTO> createFaculty(@RequestBody FacultyDTO dto) {
        System.out.println("DEBUG: FacultyDTO runtime class = " + dto.getClass().getName());
        return ResponseEntity.ok(facultyService.createFaculty(dto));
    }

    @GetMapping("/{facultyCode}")
    public ResponseEntity<FacultyDTO> getFacultyByCode(@PathVariable String facultyCode) {
        return ResponseEntity.ok(facultyService.getFacultyById(facultyCode));
    }

    @GetMapping("/department/{department}")
    public ResponseEntity<List<FacultyDTO>> getFacultyByDepartment(@PathVariable String department) {
        return ResponseEntity.ok(facultyService.getFacultyByDepartment(department));
    }

    @GetMapping("/role/{role}")
    public ResponseEntity<List<FacultyDTO>> getFacultyByRole(@PathVariable String role) {
        return ResponseEntity.ok(facultyService.getFacultyByRole(role));
    }

    @GetMapping("/search")
    public ResponseEntity<List<FacultyDTO>> searchFaculty(@RequestParam("query") String query) {
        return ResponseEntity.ok(facultyService.searchFaculty(query));
    }

    @GetMapping(produces = "application/json")
    public ResponseEntity<List<FacultyDTO>> getAllFaculty() {
        return ResponseEntity.ok(facultyService.getAllFaculty());
    }

    @GetMapping("/count")
    public ResponseEntity<Long> getFacultyCount() {
        return ResponseEntity.ok(facultyService.getFacultyCount());
    }

    @PutMapping("/{facultyCode}")
    public ResponseEntity<FacultyDTO> updateFaculty(@PathVariable String facultyCode, @RequestBody FacultyDTO dto) {
        return ResponseEntity.ok(facultyService.updateFaculty(facultyCode, dto));
    }

    @DeleteMapping("/{facultyCode}")
    public ResponseEntity<Void> deleteFaculty(@PathVariable String facultyCode) {
        facultyService.deleteFaculty(facultyCode);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping(value = "/{facultyCode}", consumes = "application/json")
    public ResponseEntity<Void> partialUpdateFaculty(
            @PathVariable String facultyCode,
            @RequestBody Map<String, Object> updates) {

        facultyService.partialUpdateFaculty(facultyCode, updates);
        return ResponseEntity.noContent().build();
    }

    @PostMapping(value = "/bulk-upload", consumes = "multipart/form-data")
    public ResponseEntity<String> bulkUploadFaculty(@RequestParam("file") MultipartFile file) {
        facultyService.bulkUploadFaculty(file);
        return ResponseEntity.ok("Bulk upload started");
    }
}
