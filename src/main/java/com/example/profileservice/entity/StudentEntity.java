package com.example.profileservice.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "students")

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
    private float studentTwelthmark;
    private String studentYear;
    private String studentSem;
    private String studentModeofjoing;
    private String studentGender;
    private String studentBloodgroup;
    private String studentAddress;
    private String studentParentorguardian;
    private String studentParentorguardianname;
    private String studentParentorguardianphone;
    private String studentSection;

    private int studentCredits;
    private float studentAttendance;
    private float studentCgpa;
    private String studentProfilepic;


    public String getStudentRollNo() {
        return studentRollNo;
    }
public String getStudentEmail(){
        return studentEmail;
}
public String getStudentDepartment(){
        return studentDepartment;
}
public void setStudentRollNo(String studentRollNo){
        this.studentRollNo=studentRollNo;
}
public void setStudentId(String studentId){
        this.studentId=studentId;
}
public String getStudentFirstname(){
        return studentFirstname;
}
public String getStudentLastname(){
        return studentLastname;
}
public String getStudentId(){
        return studentId;
}
}
