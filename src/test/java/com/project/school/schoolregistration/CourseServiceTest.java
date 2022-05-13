package com.project.school.schoolregistration;

import com.project.school.schoolregistration.entity.Course;
import com.project.school.schoolregistration.model.CourseDto;
import com.project.school.schoolregistration.repository.CourseRepository;
import com.project.school.schoolregistration.service.CourseServiceImpl;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@SpringBootTest
public class CourseServiceTest {

    @InjectMocks
    private CourseServiceImpl service;
    @Mock
    private CourseRepository repository;

    private Course courseFactory() {
        var course = new Course();
        course.setId(1);
        course.setCourseName("Course Test");
        return course;
    }

    @Test
    void shouldCreateNewCourse() throws Exception {
        var dto = CourseDto.builder().courseName("Course Test").build();
        var course = courseFactory();

        when(repository.saveAndFlush(any())).thenReturn(course);
        var courseCreated = service.create(dto);

        verify(repository, times(1)).saveAndFlush(any());
        assertNotNull(courseCreated);
        assertNotNull(courseCreated.getId());
        assertEquals(courseCreated.getId(), course.getId());
    }

    @Test
    void shouldUpdateCourse() throws Exception {
        var dto = CourseDto.builder().id(1).courseName("Course Test").build();
        var course = courseFactory();

        when(repository.saveAndFlush(any())).thenReturn(course);
        service.update(dto);

        verify(repository, times(1)).saveAndFlush(any());
    }

    @Test
    void shouldListCourseByStudent() throws Exception {
        int studentId = 1;
        var listCourse = Arrays.asList(courseFactory());

        when(repository.courseByStudent(anyInt())).thenReturn(listCourse);
        var listReturned= service.listCourseByStudent(studentId);

        verify(repository, times(1)).courseByStudent(anyInt());
        assertNotNull(listReturned);
        assertEquals(listCourse.size(), listReturned.size());
    }

    @Test
    void shouldListWithoutStudent() throws Exception {
        var listCourse = Arrays.asList(courseFactory());

        when(repository.coursesWithoutStudent()).thenReturn(listCourse);
        var listReturned= service.listCoursesWithoutStudents();

        verify(repository, times(1)).coursesWithoutStudent();
        assertNotNull(listReturned);
        assertEquals(listCourse.size(), listReturned.size());
    }

    @Test
    void shouldFindById() throws Exception {
        var course = courseFactory();
        var optCourse = Optional.of(course);

        when(repository.findById(anyInt())).thenReturn(optCourse);
        var courseFound= service.findById(course.getId());

        verify(repository, times(1)).findById(anyInt());
        assertNotNull(courseFound);
        assertEquals(optCourse.get().getId(), courseFound.getId());
    }

    @Test
    void shouldDeleteCourse() throws Exception {
        int courseId = 1;
        service.delete(courseId);
        verify(repository, times(1)).deleteById(anyInt());
    }
}
