package com.project.school.schoolregistration.exception;

public class UsernameAlreadyExistsException extends SchoolException {

    public UsernameAlreadyExistsException() {
        super("Username already exists.");
    }
}
