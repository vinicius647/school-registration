package com.project.school.schoolregistration.controller;

import com.project.school.schoolregistration.entity.Course;
import com.project.school.schoolregistration.model.CourseDto;
import com.project.school.schoolregistration.service.CourseService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@Tag(name = "Endpoint course, all operations regarding courses.")
@RestController
@RequestMapping("course")
public class CourseController {

    private final CourseService service;

    public CourseController(CourseService service) {
        this.service = service;
    }

    @Operation(description = "Create a new course. Required authority 'ROLE_SCHOOL'.")
    @PreAuthorize("hasAuthority('ROLE_SCHOOL')")
    @PostMapping
    public ResponseEntity<Course> createCourse(@RequestBody CourseDto courseDto) {

        var course = this.service.create(courseDto);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{id}")
                .buildAndExpand(course.getId()).toUri();

        return ResponseEntity.created(uri).body(course);
    }

    @Operation(description = "Update a course. Attribute id is required. Required authority 'ROLE_SCHOOL'.")
    @PreAuthorize("hasAuthority('ROLE_SCHOOL')")
    @PutMapping()
    public ResponseEntity updateCourse(@RequestBody CourseDto courseDto) {
        this.service.update(courseDto);
        return ResponseEntity.ok().build();
    }

    @Operation(description = "delete a course by id. Required authority 'ROLE_SCHOOL'.")
    @PreAuthorize("hasAuthority('ROLE_SCHOOL')")
    @DeleteMapping("/{id}")
    public ResponseEntity deleteCourse(@PathVariable(name = "id") int courseId) {
        this.service.delete(courseId);
        return ResponseEntity.ok().build();
    }

    @Operation(description = "Find a course by id. Required authority 'ROLE_SCHOOL'.")
    @PreAuthorize("hasAuthority('ROLE_SCHOOL')")
    @GetMapping("/{id}")
    public ResponseEntity<Course> findById(@PathVariable(name = "id") int id) {
        Course course = this.service.findById(id);
        return course == null ? ResponseEntity.noContent().build() : ResponseEntity.ok(course);
    }

    @Operation(description = "List all courses of a specific student. Required authority 'ROLE_SCHOOL' or 'ROLE_STUDENT'.")
    @PreAuthorize("hasAuthority('ROLE_SCHOOL') or hasAuthority('ROLE_STUDENT')")
    @GetMapping("/student/{studentId}")
    public ResponseEntity<List<Course>> courseByStudent(@PathVariable(name = "studentId") int studentId) {
        var result = this.service.listCourseByStudent(studentId);
        return result.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(result);
    }

    @Operation(description = "List all courses without students. Required authority 'ROLE_SCHOOL'.")
    @PreAuthorize("hasAuthority('ROLE_SCHOOL')")
    @GetMapping("/nostudent")
    public ResponseEntity<List<Course>> noStudent() {
        var result = this.service.listCoursesWithoutStudents();
        return result.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(result);
    }

    @Operation(description = "List all courses. Required authority 'ROLE_SCHOOL' or 'ROLE_STUDENT'.")
    @PreAuthorize("hasAuthority('ROLE_SCHOOL') or hasAuthority('ROLE_STUDENT')")
    @GetMapping()
    public ResponseEntity<List<Course>> allCourses() {
        var result = this.service.findAll();
        return result.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(result);
    }

}
