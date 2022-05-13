package com.project.school.schoolregistration.service;

import com.project.school.schoolregistration.entity.Course;
import com.project.school.schoolregistration.exception.SchoolException;
import com.project.school.schoolregistration.model.CourseDto;
import com.project.school.schoolregistration.repository.CourseRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseServiceImpl implements CourseService {

    private final CourseRepository repository;

    public CourseServiceImpl(CourseRepository repository) {
        this.repository = repository;
    }

    @Override
    public Course create(CourseDto courseDto) {
        courseDto.setId(null);
        var course = new Course();
        BeanUtils.copyProperties(courseDto, course);

        course = repository.saveAndFlush(course);
        return course;
    }

    @Override
    public void update(CourseDto courseDto) throws SchoolException {
        if (courseDto.getId() == null || courseDto.getId() == 0) {
            throw new SchoolException("Invalid course id.");
        }
        var course = new Course();
        BeanUtils.copyProperties(courseDto, course);
        repository.saveAndFlush(course);
    }

    @Override
    public void delete(int courseId) {
        this.repository.deleteById(courseId);
    }

    @Override
    public Course findById(int courseId) {
        var optCourse = this.repository.findById(courseId);
        return optCourse.isPresent() ? optCourse.get() : null;
    }

    @Override
    public List<Course> listCourseByStudent(int studentId) {
        var result = this.repository.courseByStudent(studentId);
        return result;
    }

    @Override
    public List<Course> listCoursesWithoutStudents() {
        var result = this.repository.coursesWithoutStudent();
        return result;
    }

    @Override
    public List<Course> findAll() {
        return this.repository.findAll();
    }
}
