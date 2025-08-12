package com.example.profileservice.service;

import com.example.profileservice.dto.AssignmentDTO;
import com.example.profileservice.entity.AssignmentEntity;

import java.util.List;

public interface Assignmentservice {
    AssignmentEntity addAssignment(AssignmentEntity assignment);

    // Instead of exposing Entity, return DTO for student view
    List<AssignmentDTO> getAssignmentsByStudent(String rollNo);
}
