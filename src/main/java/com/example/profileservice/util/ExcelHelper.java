package com.example.profileservice.util;

import com.example.profileservice.entity.StudentEntity;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.text.SimpleDateFormat;
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
                try {
                    student.setStudentFirstname(getCellValue(currentRow.getCell(0)));
                    student.setStudentLastname(getCellValue(currentRow.getCell(1)));
                    student.setStudentRollNo(getCellValue(currentRow.getCell(2)));
                    student.setStudentDepartment(getCellValue(currentRow.getCell(3)));
                    student.setStudentDob(getCellValue(currentRow.getCell(4)));
                    student.setStudentPhoneNo(getCellValue(currentRow.getCell(5)));
                    student.setStudentEmail(getCellValue(currentRow.getCell(6)));
                    student.setStudentAadharno(getCellValue(currentRow.getCell(7)));
                    student.setStudentTenthmark((float) parseDouble(currentRow.getCell(8)));
                    student.setStudentTwelthmark((float) parseDouble(currentRow.getCell(9)));
                    student.setStudentDiplomamark((float) parseDouble(currentRow.getCell(10)));
                    student.setStudentYear(getCellValue(currentRow.getCell(11)));
                    student.setStudentSem(getCellValue(currentRow.getCell(12)));
                    student.setStudentModeofjoing(getCellValue(currentRow.getCell(13)));
                    student.setStudentGender(getCellValue(currentRow.getCell(14)));
                    student.setStudentBloodgroup(getCellValue(currentRow.getCell(15)));
                    student.setStudentAddress(getCellValue(currentRow.getCell(16)));
                    student.setStudentParentorguardian(getCellValue(currentRow.getCell(17)));
                    student.setStudentParentorguardianname(getCellValue(currentRow.getCell(18)));
                    student.setStudentParentorguardianphone(getCellValue(currentRow.getCell(19)));
                    student.setStudentSection(getCellValue(currentRow.getCell(20)));
                    student.setStudentCredits((int) parseDouble(currentRow.getCell(21)));
                    student.setStudentAttendance((float) parseDouble(currentRow.getCell(22)));
                    student.setStudentCgpa((float) parseDouble(currentRow.getCell(23)));
                    student.setStudentProfilepic(getCellValue(currentRow.getCell(24)));

                    studentList.add(student);
                } catch (Exception e) {
                    System.err.println("Skipping row due to error: " + e.getMessage());
                }
            }

        } catch (IOException e) {
            throw new RuntimeException("Failed to parse Excel: " + e.getMessage());
        }

        return studentList;
    }

    private static String getCellValue(Cell cell) {
        if (cell == null) return "";
        switch (cell.getCellType()) {
            case STRING: return cell.getStringCellValue().trim();
            case NUMERIC:
                if (DateUtil.isCellDateFormatted(cell)) {
                    return new SimpleDateFormat("yyyy-MM-dd").format(cell.getDateCellValue());
                }
                return String.valueOf(cell.getNumericCellValue());
            case BOOLEAN: return String.valueOf(cell.getBooleanCellValue());
            default: return "";
        }
    }

    private static double parseDouble(Cell cell) {
        try {
            return Double.parseDouble(getCellValue(cell));
        } catch (Exception e) {
            return 0.0;
        }
    }

}

