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

    private String studentFirstname;
    private String studentLastname;
    private String studentRollNo;
    private String studentDepartment;
    private String studentDob;
    private String studentPhoneNo;
    private String studentEmail;
    private String studentAadharno;
    private float studentTenthmark;
    private float studentDiplomamark;
    private float studentTwelfthmark;
    private String studentYear;
    private String studentSem;
    private String studentModeofjoing;
    private String studentGender;
    private String studentBloodgroup;
    private String studentAddress;
    private String studentParentrole;
    private String studentParentname;
    private String studentParentphone;

    private String studentSection;
}
