package com.project.school.schoolregistration.repository;

import com.project.school.schoolregistration.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface StudentRepository extends JpaRepository<Student, Integer> {

    public Optional<Student> findByUsername(String username);

    @Query(name = "Student.course")
    public List<Student> studentByCourse(@Param("courseId") int courseId);

    @Query(name = "Student.nocourse")
    public List<Student> studentsWithoutCourse();

    @Query(name = "Student.fetchCourse")
    public List<Student> allStudentsFetchingCourses();

}
