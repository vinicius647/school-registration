package com.project.school.schoolregistration.service;

import com.project.school.schoolregistration.entity.Student;
import com.project.school.schoolregistration.exception.*;
import com.project.school.schoolregistration.model.EnrollDto;
import com.project.school.schoolregistration.repository.CourseRepository;
import com.project.school.schoolregistration.repository.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.function.BiPredicate;

@Service
public class EnrollmentServiceImpl implements EnrollmentService {

    private final CourseRepository courseRepository;
    private final StudentRepository studentRepository;

    public EnrollmentServiceImpl(CourseRepository courseRepository, StudentRepository studentRepository) {
        this.courseRepository = courseRepository;
        this.studentRepository = studentRepository;
    }

    public Student enroll(EnrollDto dto) throws SchoolException {

        var optStudent = this.studentRepository.findById(dto.getStudentId());
        var student = optStudent.orElseThrow(() -> new StudentNotFoundException());

        var optCourse = this.courseRepository.findById(dto.getCourseId());
        var course = optCourse.orElseThrow(() -> new CourseNotFoundException());

        BiPredicate<Integer, Integer> checkLimitExceeded = (q, l) -> {
            return q >= l;
        };

        if (checkLimitExceeded.test(student.getCourseList().size(), 5)) {
            throw new CourseLimitExceededException();
        }

        if (checkLimitExceeded.test(course.getStudentList().size(), 50)) {
            throw new StudentLimitExceededException();
        }

        student.getCourseList().add(course);
        course.getStudentList().add(student);
        this.courseRepository.save(course);
        this.studentRepository.save(student);

        return student;
    }
}
