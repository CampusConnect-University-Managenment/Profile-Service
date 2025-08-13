package com.example.profileservice.service.impl;

import com.example.profileservice.dto.AssignmentDTO;
import com.example.profileservice.entity.AssignmentEntity;
import com.example.profileservice.repository.AssignmentRepository;
import com.example.profileservice.service.Assignmentservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AssignmentServiceImple implements Assignmentservice {

    @Autowired
    private AssignmentRepository assignmentRepository;

    @Override
    public AssignmentEntity addAssignment(AssignmentEntity assignment) {
        // If submissionDate is not provided, set to today's date
        if (assignment.getSubmissionDate() == null) {
            assignment.setSubmissionDate(LocalDate.now());
        }

        return assignmentRepository.save(assignment);
    }

    @Override
    public List<AssignmentDTO> getAssignmentsByStudent(String rollNo) {
        return assignmentRepository.findByStudentRollNo(rollNo)
                .stream()
                .map(a -> new AssignmentDTO(
                        a.getTitle(),
                        a.getSubmissionDate().toString(),
                        a.getGrade(),
                        a.getRemarks()
                ))
                .collect(Collectors.toList());
    }
}
