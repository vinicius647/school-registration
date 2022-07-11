package com.project.school.schoolregistration;

import com.project.school.schoolregistration.entity.Student;
import com.project.school.schoolregistration.model.StudentDto;
import com.project.school.schoolregistration.repository.StudentRepository;
import com.project.school.schoolregistration.service.StudentServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@SpringBootTest
public class StudentServiceTest {
    @InjectMocks
    private StudentServiceImpl service;

    @Mock
    private StudentRepository repository;

    private Student student;

    @BeforeEach
    public void studentFactory() {
        student = new Student();
        student.setId(1);
        student.setName("Student-1");
        student.setUsername("Username");
        student.setPassword("Password");
    }

    @Test
    void shouldCreateNewStudent() throws Exception {
        var dto = StudentDto.builder().name("Student-1").password("Password").username("Username").build();

        when(repository.saveAndFlush(any())).thenReturn(student);
        var studentCreated = service.create(dto);

        verify(repository, times(1)).saveAndFlush(any());
        assertNotNull(studentCreated);
        assertNotNull(studentCreated.getId());
        assertEquals(studentCreated.getId(), student.getId());
    }

    @Test
    void shouldUpdateStudent() throws Exception {
        var dto = StudentDto.builder().id(1).name("Student-1").password("Password").username("Username").build();

        when(repository.saveAndFlush(any())).thenReturn(student);
        service.update(dto);

        verify(repository, times(1)).saveAndFlush(any());
    }

    @Test
    void shouldListStudentByCourse() throws Exception {
        int courseId = 1;
        var listStudent = Arrays.asList(student);

        when(repository.studentByCourse(anyInt())).thenReturn(listStudent);
        var listReturned= service.listStudentByCourse(courseId);

        verify(repository, times(1)).studentByCourse(anyInt());
        assertNotNull(listReturned);
        assertEquals(listStudent.size(), listReturned.size());
    }

    @Test
    void shouldListStudentsWithoutCourse() throws Exception {
        var listStudent = Arrays.asList(student);

        when(repository.studentsWithoutCourse()).thenReturn(listStudent);
        var listReturned= service.listStudentsWithoutCourses();

        verify(repository, times(1)).studentsWithoutCourse();
        assertNotNull(listReturned);
        assertEquals(listStudent.size(), listReturned.size());
    }

    @Test
    void shouldFindById() throws Exception {
        var optStudent = Optional.of(student);

        when(repository.findById(anyInt())).thenReturn(optStudent);

        var studentFound= service.findById(student.getId());

        verify(repository, times(1)).findById(anyInt());
        assertNotNull(studentFound);
        assertEquals(optStudent.get().getId(), student.getId());
    }

    @Test
    void shouldDeleteStudent() throws Exception {
        int studentId = 1;
        service.delete(studentId);
        verify(repository, times(1)).deleteById(anyInt());
    }
}
