package com.project.school.schoolregistration.exception;

import com.project.school.schoolregistration.model.Error;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class SchoolExceptionHandler extends ResponseEntityExceptionHandler {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler({SchoolException.class, StudentNotFoundException.class, CourseNotFoundException.class, UsernameAlreadyExistsException.class})
    public ResponseEntity<Object> handleNotFoundException(RuntimeException exception, WebRequest request) {
        return handleExceptionInternal(exception, new Error(exception.getMessage()), new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }

    @ResponseStatus(HttpStatus.PRECONDITION_FAILED)
    @ExceptionHandler({StudentLimitExceededException.class, CourseLimitExceededException.class})
    public ResponseEntity<Object> handleExceededLimitException(RuntimeException exception, WebRequest request) {
        return handleExceptionInternal(exception, new Error(exception.getMessage()), new HttpHeaders(), HttpStatus.PRECONDITION_FAILED, request);
    }
}
