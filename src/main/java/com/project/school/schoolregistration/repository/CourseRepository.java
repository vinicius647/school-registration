package com.project.school.schoolregistration.repository;

import com.project.school.schoolregistration.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CourseRepository extends JpaRepository<Course, Integer> {

    @Query(name = "Course.student")
    public List<Course> courseByStudent(@Param("studentId") int studentId);

    @Query(name = "Course.nostudent")
    public List<Course> coursesWithoutStudent();
}
