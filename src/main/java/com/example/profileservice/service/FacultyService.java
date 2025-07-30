package com.example.profileservice.service;

import com.example.profileservice.dto.FacultyDTO;
import org.springframework.web.multipart.MultipartFile;

import java.util.*;

public interface FacultyService {
    FacultyDTO createFaculty(FacultyDTO dto);
    FacultyDTO getFacultyById(String id);
    List<FacultyDTO> getAllFaculty();
    FacultyDTO updateFaculty(String id, FacultyDTO dto);
    long getFacultyCount();
    void partialUpdateFaculty(String id, Map<String, Object> updates);
    void bulkUploadFaculty(MultipartFile file);
    void deleteFaculty(String id);
}
