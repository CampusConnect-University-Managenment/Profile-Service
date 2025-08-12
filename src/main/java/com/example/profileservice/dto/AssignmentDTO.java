package com.example.profileservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AssignmentDTO {
    private String id;
    private String title;
    private String description;
    private String studentRollNo;
    private LocalDateTime dueDate;
    private String fileUrl; // if you store file links
    private LocalDateTime submittedAt; // optional
    private String status; // e.g., "Pending", "Submitted", "Graded"
    private String submissionDate;
    private float grade;
    private String remarks;

    public AssignmentDTO(String title, String submissionDate, float grade, String remarks) {
        this.title = title;
        this.submissionDate = submissionDate;
        this.grade = grade;
        this.remarks = remarks;
    }
}
