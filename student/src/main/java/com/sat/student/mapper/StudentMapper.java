package com.sat.student.mapper;

import com.sat.student.dto.StudentDto;
import com.sat.student.model.Student;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class StudentMapper {
    public static StudentDto mapToStudentDto(Student student){
        StudentDto studentDto=new StudentDto();
        studentDto.setId(student.getId());
        studentDto.setFirstName(student.getFirstName());
        studentDto.setLastName(student.getLastName());
        studentDto.setEmail(student.getEmail());
        studentDto.setMobileNumber(student.getMobileNumber());
        return studentDto;
    }

    public static Student mapToStudent(StudentDto studentDto){
        Student student=new Student();
        student.setId(studentDto.getId());
        student.setFirstName(studentDto.getFirstName());
        student.setLastName(studentDto.getLastName());
        student.setEmail(studentDto.getEmail());
        student.setMobileNumber(studentDto.getMobileNumber());
        return student;
    }

    public static List<StudentDto> mapToStudentDtos(List<Student> students){
        List<StudentDto> dtoList = new ArrayList<>();
        dtoList = students.stream().map((student -> mapToStudentDto(student))).collect(Collectors.toList());
        return dtoList;
    }
}
