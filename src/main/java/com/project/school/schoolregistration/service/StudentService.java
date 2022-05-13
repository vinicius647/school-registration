package com.project.school.schoolregistration.service;

import com.project.school.schoolregistration.entity.Student;
import com.project.school.schoolregistration.exception.SchoolException;
import com.project.school.schoolregistration.exception.UsernameAlreadyExistsException;
import com.project.school.schoolregistration.model.StudentDto;

import java.util.List;

public interface StudentService {

    Student create(StudentDto studentDto) throws UsernameAlreadyExistsException;

    void update(StudentDto studentDto) throws SchoolException;

    void delete(int studentId);

    Student findById(int studentId);

    List<Student> listStudentByCourse(int courseId);

    List<Student> listStudentsWithoutCourses();

    List<Student> listAllStudentsEnrolled();

}
