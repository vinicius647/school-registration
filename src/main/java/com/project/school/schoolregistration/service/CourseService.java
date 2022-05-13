package com.project.school.schoolregistration.service;

import com.project.school.schoolregistration.entity.Course;
import com.project.school.schoolregistration.exception.SchoolException;
import com.project.school.schoolregistration.model.CourseDto;

import java.util.List;

public interface CourseService {

    Course create(CourseDto courseDto);

    void update(CourseDto courseDto) throws SchoolException;

    void delete(int courseId);

    Course findById(int course);

    List<Course> listCourseByStudent(int studentId);

    List<Course> listCoursesWithoutStudents();

    List<Course> findAll();
}
