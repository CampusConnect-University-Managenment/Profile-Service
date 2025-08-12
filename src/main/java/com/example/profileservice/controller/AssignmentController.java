package com.example.profileservice.controller;

import com.example.profileservice.dto.AssignmentDTO; // Use DTO, not Entity
import com.example.profileservice.entity.AssignmentEntity;
import com.example.profileservice.service.Assignmentservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin/assignments")
@CrossOrigin(origins = "http://localhost:3000")
public class AssignmentController {

    @Autowired
    private Assignmentservice assignmentService;

    @PostMapping("/add")
    public AssignmentEntity addAssignment(@RequestBody AssignmentEntity assignment) {
        return assignmentService.addAssignment(assignment);
    }

    @GetMapping("/{rollNo}")
    public List<AssignmentDTO> getAssignmentsByStudent(@PathVariable String rollNo) {
        return assignmentService.getAssignmentsByStudent(rollNo);
    }
}
