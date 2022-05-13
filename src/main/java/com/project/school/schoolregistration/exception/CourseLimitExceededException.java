package com.project.school.schoolregistration.exception;

public class CourseLimitExceededException extends SchoolException {

    public CourseLimitExceededException() {
        super("Student has exceeded the course limit.");
    }
}
