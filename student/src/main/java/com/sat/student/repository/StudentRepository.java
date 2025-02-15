package com.sat.student.repository;

import com.sat.student.model.Student;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;

import java.util.List;
import java.util.Optional;

public interface StudentRepository extends JpaRepository<Student, Long> {
    Optional<List<Student>> findByEmail(String email);
    Optional<List<Student>> findByMobileNumber(String mobileNumber);

    @Transactional
    @Modifying
    void deleteStudentByMobileNumber(String mobileNumber);
}
