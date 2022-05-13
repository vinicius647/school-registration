package com.project.school.schoolregistration.controller;

import com.project.school.schoolregistration.entity.Student;
import com.project.school.schoolregistration.model.CourseDto;
import com.project.school.schoolregistration.model.EnrollDto;
import com.project.school.schoolregistration.model.StudentDto;
import com.project.school.schoolregistration.service.EnrollmentService;
import com.project.school.schoolregistration.service.StudentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@Tag(name = "Endpoint student, all operations regarding students.")
@RestController
@RequestMapping("student")
public class StudentController {

    private final StudentService service;
    private final EnrollmentService enrollmentService;

    public StudentController(StudentService service, EnrollmentService enrollmentService) {
        this.service = service;
        this.enrollmentService = enrollmentService;
    }

    @Operation(description = "Create a student. Username will be validated, if it already exists a bad request will be returned. Required authority 'ROLE_SCHOOL'")
    @PreAuthorize("hasAuthority('ROLE_SCHOOL')")
    @PostMapping
    public ResponseEntity<Student> createStudent(@RequestBody StudentDto studentDto) {

        var student = this.service.create(studentDto);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{id}")
                .buildAndExpand(student.getId()).toUri();

        return ResponseEntity.created(uri).body(student);
    }

    @Operation(description = "Update a student. Username will be validated, if it already exists a bad request will be returned. Required authority 'ROLE_SCHOOL'")
    @PreAuthorize("hasAuthority('ROLE_SCHOOL')")
    @PutMapping()
    public ResponseEntity updateStudent(@RequestBody StudentDto studentDto) {
        this.service.update(studentDto);
        return ResponseEntity.ok().build();
    }

    @Operation(description = "Delete a student by id. Required authority 'ROLE_SCHOOL'")
    @PreAuthorize("hasAuthority('ROLE_SCHOOL')")
    @DeleteMapping("/{id}")
    public ResponseEntity deleteStudent(@PathVariable(name = "id") int studentId) {
        this.service.delete(studentId);
        return ResponseEntity.ok().build();
    }

    @Operation(description = "Enroll the student in a new course. It will be validated if the course not exceeded 50 students and if the student not exceeded 5 courses.  Required authority 'ROLE_SCHOOL' or 'ROLE_STUDENT'.")
    @PreAuthorize("hasAuthority('ROLE_STUDENT') or hasAuthority('ROLE_SCHOOL')")
    @PostMapping("/enroll")
    public ResponseEntity<Student> enroll(@RequestBody EnrollDto dto) {
        var student = this.enrollmentService.enroll(dto);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{id}")
                .buildAndExpand(student.getId()).toUri();

        return ResponseEntity.created(uri).body(student);
    }

    @Operation(description = "Find student by id. Required authority 'ROLE_SCHOOL' or 'ROLE_STUDENT'.")
    @PreAuthorize("hasAuthority('ROLE_SCHOOL') or hasAuthority('ROLE_STUDENT')")
    @GetMapping("{id}")
    public ResponseEntity<Student> findById(@PathVariable(name = "id") int id) {
        Student student = this.service.findById(id);
        return student == null ? ResponseEntity.noContent().build() : ResponseEntity.ok(student);
    }

    @Operation(description = "List all students are enrolled in a specific course. Required authority 'ROLE_SCHOOL'.")
    @PreAuthorize("hasAuthority('ROLE_SCHOOL')")
    @GetMapping("/course/{courseId}")
    public ResponseEntity<List<Student>> studentByCourse(@PathVariable(name = "courseId") int courseId) {
        var result = this.service.listStudentByCourse(courseId);
        return result.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(result);
    }

    @Operation(description = "List all students without courses. Required authority 'ROLE_SCHOOL'.")
    @PreAuthorize("hasAuthority('ROLE_SCHOOL')")
    @GetMapping("/nocourse")
    public ResponseEntity<List<Student>> noCourse() {
        var result = this.service.listStudentsWithoutCourses();
        return result.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(result);
    }

    @Operation(description = "List all enrolled students. Required authority 'ROLE_SCHOOL'.")
    @PreAuthorize("hasAuthority('ROLE_SCHOOL')")
    @GetMapping("/enrolled")
    public ResponseEntity<List<Student>> allStudentsEnrolled() {
        var result = this.service.listAllStudentsEnrolled();
        return result.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(result);
    }

}
