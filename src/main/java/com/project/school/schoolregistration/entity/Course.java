package com.project.school.schoolregistration.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;

@Getter
@Setter
@ToString
@Entity
@Table(name = "course")
@NamedQueries({
        @NamedQuery(name = "Course.student", query = "SELECT DISTINCT c FROM Course c JOIN c.studentList s WHERE s.id = :studentId ORDER BY c.courseName"),
        @NamedQuery(name = "Course.nostudent", query = "SELECT c FROM Course c WHERE size(c.studentList) = 0 ORDER BY c.courseName")})
public class Course implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "course_name")
    private String courseName;
    @JoinTable(name = "course_student", joinColumns = {
            @JoinColumn(name = "course_id", referencedColumnName = "id")}, inverseJoinColumns = {
            @JoinColumn(name = "student_id", referencedColumnName = "id")})
    @ManyToMany
    private List<Student> studentList = new java.util.ArrayList<>();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Course course = (Course) o;
        return id.equals(course.id);
    }

    @JsonIgnore
    public List<Student> getStudentList() {
        return studentList;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
