package com.example.profileservice.service;

import com.example.profileservice.dto.FacultyDTO;

import java.util.List;

public interface FacultyService {
    FacultyDTO createFaculty(FacultyDTO dto);
    FacultyDTO updateFaculty(String id, FacultyDTO dto);
    FacultyDTO getFacultyById(String id);
    List<FacultyDTO> getAllFaculty();
    void deleteFaculty(String id);
}
