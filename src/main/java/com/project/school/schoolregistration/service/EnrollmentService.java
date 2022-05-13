package com.project.school.schoolregistration.service;

import com.project.school.schoolregistration.entity.Student;
import com.project.school.schoolregistration.exception.SchoolException;
import com.project.school.schoolregistration.model.EnrollDto;

public interface EnrollmentService {

    Student enroll(EnrollDto dto) throws SchoolException;
}
