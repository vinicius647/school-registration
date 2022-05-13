package com.project.school.schoolregistration.service;

import com.project.school.schoolregistration.entity.Student;
import com.project.school.schoolregistration.exception.SchoolException;
import com.project.school.schoolregistration.exception.UsernameAlreadyExistsException;
import com.project.school.schoolregistration.model.StudentDto;
import com.project.school.schoolregistration.repository.StudentRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {

    private StudentRepository repository;

    public StudentServiceImpl(StudentRepository repository) {
        this.repository = repository;
    }

    @Override
    public Student create(StudentDto studentDto) throws UsernameAlreadyExistsException {

        var optUsername = this.repository.findByUsername(studentDto.getUsername());
        if (optUsername.isPresent()) {
            throw new UsernameAlreadyExistsException();
        }

        studentDto.setId(null);
        var student = new Student();
        BeanUtils.copyProperties(studentDto, student);

        student = this.repository.saveAndFlush(student);
        return student;
    }

    @Override
    public void update(StudentDto studentDto) throws SchoolException {
        if (studentDto.getId() == null || studentDto.getId() == 0) {
            throw new SchoolException("Invalid student id.");
        }

        var optUsername = this.repository.findByUsername(studentDto.getUsername());
        if (optUsername.isPresent()) {
            throw new UsernameAlreadyExistsException();
        }

        var student = new Student();
        BeanUtils.copyProperties(studentDto, student);
        this.repository.saveAndFlush(student);
    }

    @Override
    public void delete(int studentId) {
        this.repository.deleteById(studentId);
    }

    @Override
    public Student findById(int studentId) {
        var optStudent = this.repository.findById(studentId);
        return optStudent.isPresent() ? optStudent.get() : null;
    }

    @Override
    public List<Student> listStudentByCourse(int courseId) {
        var result = this.repository.studentByCourse(courseId);
        return result;
    }

    @Override
    public List<Student> listStudentsWithoutCourses() {
        var result = this.repository.studentsWithoutCourse();
        return result;
    }

    @Override
    public List<Student> listAllStudentsEnrolled() {
        var result = this.repository.allStudentsFetchingCourses();
        return result;
    }
}
