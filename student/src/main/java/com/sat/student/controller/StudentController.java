package com.sat.student.controller;

import com.sat.student.dto.StudentDto;
import com.sat.student.mapper.StudentMapper;
import com.sat.student.model.Student;
import com.sat.student.service.Impl.StudentServiceImpl;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@AllArgsConstructor
@RequestMapping("api/students")
public class StudentController {
    private StudentServiceImpl studentService;

    @PostMapping("/create")
    public ResponseEntity<StudentDto> createStudent(@RequestBody StudentDto studentDto){
        StudentDto savedStudentDto = studentService.createStudent(studentDto);
        return new ResponseEntity<>(savedStudentDto, HttpStatus.CREATED);
    }

    @GetMapping("/all")
    public ResponseEntity<List<StudentDto>> getAllStudents(){
        List<StudentDto> allStudents = studentService.getAllStudents();
        return ResponseEntity.ok(allStudents);
    }

    @GetMapping("/{id}")
    public ResponseEntity<StudentDto> getStudentById(@PathVariable Long id){
        StudentDto dbStudent = studentService.getStudentById(id);
        return ResponseEntity.ok(dbStudent);
    }

    @GetMapping("email/{email}")
    public ResponseEntity<List<StudentDto>> getStudentByEmail(@PathVariable String email){
        List<StudentDto> dbStudents = studentService.getStudentByEmail(email);
        return ResponseEntity.ok(dbStudents);
    }

    @GetMapping("mobile/{mobile}")
    public ResponseEntity<List<StudentDto>> getStudentByMobileNumber(@PathVariable String mobile){
        List<StudentDto> dbStudents = studentService.getStudentByMobileNumber(mobile);
        return ResponseEntity.ok(dbStudents);
    }

    @PutMapping("/{id}")
    public ResponseEntity<StudentDto> updateStudent(@PathVariable Long id, @RequestBody StudentDto studentDto){

        StudentDto updatedStudent = studentService.updateStudent(id, studentDto);
        return ResponseEntity.ok(updatedStudent);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteStudent(@PathVariable Long id){
        studentService.deleteStudent(id);
        return new ResponseEntity<>("Student with id "+id+" deleted successfully", HttpStatus.OK);
    }

    @DeleteMapping("mobile/{mobileNumber}")
    public ResponseEntity<String> deleteStudent(@PathVariable String mobileNumber){
        studentService.deleteStudentByMobileNumber(mobileNumber);
        return new ResponseEntity<>("Students with Mobile Number "+mobileNumber+" deleted successfully", HttpStatus.OK);
    }
}
