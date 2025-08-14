package com.example.profileservice.entity;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "assignments")

public class AssignmentEntity {
    @Id
    private String id;
    private String title;
    private String description;
    @JsonProperty("rollNo")
    private String studentRollNo;
    private String dueDate;
    private String submittedFileUrl;
    private LocalDate submissionDate;
    private String remarks;
    private float grade;

}
