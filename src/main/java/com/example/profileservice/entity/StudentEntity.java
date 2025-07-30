package com.example.profileservice.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document
public class StudentEntity {
    @Id
    private String studentId;
    private String studentName;
    private String studentRollNo;
    private String studentDepartment;
    private String studentYear;
    private String studentSection;
    private String studentEmail;
    private String studentPhoneNo;
    private String studentAddress;
}
