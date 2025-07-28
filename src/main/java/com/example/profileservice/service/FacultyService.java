package com.example.profileservice.service;

import com.example.profileservice.entity.Faculty;

public interface FacultyService {
    void createFaculty(Faculty faculty);
    void updateFaculty(Faculty faculty);
    void deleteFaculty(Faculty faculty);
}
