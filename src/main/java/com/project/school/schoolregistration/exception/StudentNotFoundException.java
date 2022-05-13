package com.project.school.schoolregistration.exception;

public class StudentNotFoundException extends SchoolException {

    public StudentNotFoundException() {
        super("Student not found.");
    }
}
