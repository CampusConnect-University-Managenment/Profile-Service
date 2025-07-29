package com.example.profileservice.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

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

    // Keep your getters and setters

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getStudentRollNo() {
        return studentRollNo;
    }

    public void setStudentRollNo(String studentRollNo) {
        this.studentRollNo = studentRollNo;
    }

    public String getStudentDepartment() {
        return studentDepartment;
    }

    public void setStudentDepartment(String studentDeprtment) {
        this.studentDepartment = studentDeprtment;
    }

    public String getStudentYear() {
        return studentYear;
    }

    public void setStudentYear(String studentYear) {
        this.studentYear = studentYear;
    }

    public String getStudentSection() {
        return studentSection;
    }

    public void setStudentSection(String studentSection) {
        this.studentSection = studentSection;
    }

    public String getStudentEmail() {
        return studentEmail;
    }

    public void setStudentEmail(String studentEmail) {
        this.studentEmail = studentEmail;
    }

    public String getStudentPhoneNo() {
        return studentPhoneNo;
    }

    public void setStudentPhoneNo(String studentPhoneNo) {
        this.studentPhoneNo = studentPhoneNo;
    }

    public String getStudentAddress() {
        return studentAddress;
    }

    public void setStudentAddress(String studentAddress) {
        this.studentAddress = studentAddress;
    }

    public StudentEntity(String studentId, String studentName, String studentRollNo, String studentDeprtment, String studentYear, String studentSection, String studentEmail, String studentPhoneNo, String studentAddress) {
        this.studentId = studentId;
        this.studentName = studentName;
        this.studentRollNo = studentRollNo;
        this.studentDepartment = studentDeprtment;
        this.studentYear = studentYear;
        this.studentSection = studentSection;
        this.studentEmail = studentEmail;
        this.studentPhoneNo = studentPhoneNo;
        this.studentAddress = studentAddress;
    }

    public StudentEntity() {

    }
}
