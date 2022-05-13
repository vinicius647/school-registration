package com.project.school.schoolregistration.exception;

public class StudentLimitExceededException extends SchoolException {

    public StudentLimitExceededException() {
        super("Course has exceeded the student limit.");
    }
}
