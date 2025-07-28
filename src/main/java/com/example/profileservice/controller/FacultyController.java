package com.example.profileservice.controller;

import com.example.profileservice.dto.FacultyDTO;
import com.example.profileservice.service.FacultyService;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/faculty")
public class FacultyController {
    @Autowired
    private FacultyService facultyService;

    @PostMapping
    public FacultyDTO createFaculty(@RequestBody FacultyDTO dto) {
        return facultyService.createFaculty(dto);
    }

    @GetMapping("/{id}")
    public FacultyDTO getFacultyById(@PathVariable String id) {
        return facultyService.getFacultyById(id);
    }

    @GetMapping
    public List<FacultyDTO> getAllFaculty() {
        return facultyService.getAllFaculty();
    }

    @PutMapping("/{id}")
    public FacultyDTO updateFaculty(@PathVariable String id, @RequestBody FacultyDTO dto) {
        return facultyService.updateFaculty(id, dto);
    }

    @DeleteMapping("/{id}")
    public void deleteFaculty(@PathVariable String id) {
        facultyService.deleteFaculty(id);
    }

}
