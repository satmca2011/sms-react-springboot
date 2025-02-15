package com.sat.student.service.Impl;

import com.sat.student.dto.StudentDto;
import com.sat.student.exception.ResourceNotFoundException;
import com.sat.student.mapper.StudentMapper;
import com.sat.student.model.Student;
import com.sat.student.repository.StudentRepository;
import com.sat.student.service.StudentService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class StudentServiceImpl implements StudentService {

    private StudentRepository studentRepository;
    @Override
    public StudentDto createStudent(StudentDto studentDto) {
        Student student = StudentMapper.mapToStudent(studentDto);
        Student savedStudent = studentRepository.save(student);
        return StudentMapper.mapToStudentDto(savedStudent);
    }

    @Override
    public List<StudentDto> getAllStudents() {
        List<Student> allStudents = studentRepository.findAll();
        return StudentMapper.mapToStudentDtos(allStudents);
    }

    @Override
    public StudentDto getStudentById(Long id) {
        Student dbStudent = getStudent(id);
       return  StudentMapper.mapToStudentDto(dbStudent);
    }

    @Override
    public StudentDto updateStudent(Long id, StudentDto studentDto) {
        Student dbStudent = getStudent(id);
        dbStudent.setFirstName(studentDto.getFirstName());
        dbStudent.setLastName(studentDto.getLastName());
        dbStudent.setEmail(studentDto.getEmail());
        dbStudent.setMobileNumber(studentDto.getMobileNumber());
        Student updatedStudent = studentRepository.save(dbStudent);
        return StudentMapper.mapToStudentDto(updatedStudent);
    }

    @Override
    public void deleteStudent(Long id) {
        Student dbStudent = getStudent(id);
        studentRepository.delete(dbStudent);
    }

    @Override
    public List<StudentDto> getStudentByEmail(String email) {
        List<Student> dbStudentsList = new ArrayList<>();
        Optional<List<Student>> dbStudents = studentRepository.findByEmail(email);
        if(dbStudents.isPresent() && !dbStudents.get().isEmpty()){
            dbStudentsList = dbStudents.get();
            return StudentMapper.mapToStudentDtos(dbStudentsList);
        }
        else{
            throw  new ResourceNotFoundException("Students", "Email", email);
        }
    }

    @Override
    public List<StudentDto> getStudentByMobileNumber(String mobileNumber) {
        List<Student> dbStudentsList = new ArrayList<>();
        Optional<List<Student>> dbStudents = studentRepository.findByMobileNumber(mobileNumber);
        if(dbStudents.isPresent() && !dbStudents.get().isEmpty()){
            dbStudentsList = dbStudents.get();
            return StudentMapper.mapToStudentDtos(dbStudentsList);
        }
        else{
            throw  new ResourceNotFoundException("Students", "Mobile Number", mobileNumber);
        }
    }

    @Override
    public void deleteStudentByMobileNumber(String mobileNumber) {
        List<Student> dbStudentsList = new ArrayList<>();
        Optional<List<Student>> dbStudents = studentRepository.findByMobileNumber(mobileNumber);
        if(dbStudents.isPresent() && !dbStudents.get().isEmpty()){
                studentRepository.deleteStudentByMobileNumber(mobileNumber);
        }
        else{
            throw  new ResourceNotFoundException("Students", "Mobile Number", mobileNumber);
        }
    }

    private Student getStudent(Long id) {
        Student dbStudent = studentRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Student", "Id", id.toString()));
        return dbStudent;
    }
}
