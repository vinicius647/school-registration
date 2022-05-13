package com.project.school.schoolregistration.exception;

public class CourseNotFoundException extends SchoolException {

    public CourseNotFoundException() {
        super("Course not found.");
    }
}
