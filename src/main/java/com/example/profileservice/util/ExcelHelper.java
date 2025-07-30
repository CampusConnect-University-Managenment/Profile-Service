package com.example.profileservice.util;

import com.example.profileservice.entity.StudentEntity;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ExcelHelper {

    public static List<StudentEntity> convertExcelToStudentList(MultipartFile file) {
        List<StudentEntity> studentList = new ArrayList<>();

        try (Workbook workbook = new XSSFWorkbook(file.getInputStream())) {
            Sheet sheet = workbook.getSheetAt(0);
            Iterator<Row> rows = sheet.iterator();

            boolean isHeader = true;
            while (rows.hasNext()) {
                Row currentRow = rows.next();
                if (isHeader) {
                    isHeader = false;
                    continue;
                }

                StudentEntity student = new StudentEntity();
                student.setStudentName(getCellValue(currentRow.getCell(0)));
                student.setStudentRollNo(getCellValue(currentRow.getCell(1)));
                student.setStudentDepartment(getCellValue(currentRow.getCell(2)));
                student.setStudentYear(getCellValue(currentRow.getCell(3)));
                student.setStudentSection(getCellValue(currentRow.getCell(4)));
                student.setStudentEmail(getCellValue(currentRow.getCell(5)));
                student.setStudentPhoneNo(getCellValue(currentRow.getCell(6)));
                student.setStudentAddress(getCellValue(currentRow.getCell(7)));

                studentList.add(student);
            }
        } catch (IOException e) {
            throw new RuntimeException("Failed to parse Excel file: " + e.getMessage());
        }

        return studentList;
    }

    private static String getCellValue(Cell cell) {
        if (cell == null) return "";
        switch (cell.getCellType()) {
            case STRING: return cell.getStringCellValue().trim();
            case NUMERIC: return String.valueOf((long) cell.getNumericCellValue());
            case BOOLEAN: return String.valueOf(cell.getBooleanCellValue());
            default: return "";
        }
    }
}
