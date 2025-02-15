package com.sat.student.service;

import com.sat.student.dto.StudentDto;
import com.sat.student.model.Student;

import java.util.List;

public interface StudentService {

    StudentDto createStudent(StudentDto studentDto);

    List<StudentDto> getAllStudents();

    StudentDto getStudentById(Long id);

    StudentDto updateStudent(Long id, StudentDto studentDto);

    void deleteStudent(Long id);

    List<StudentDto> getStudentByEmail(String email);

    List<StudentDto> getStudentByMobileNumber(String mobileNumber);
    void deleteStudentByMobileNumber(String mobileNumber);

}
