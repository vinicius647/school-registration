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
@Table(name = "student")
@NamedQueries({
        @NamedQuery(name = "Student.course", query = "SELECT DISTINCT s FROM Student s JOIN s.courseList c WHERE c.id = :courseId ORDER BY s.name"),
        @NamedQuery(name = "Student.fetchCourse", query = "SELECT DISTINCT s FROM Student s JOIN FETCH s.courseList ORDER BY s.name"),
        @NamedQuery(name = "Student.nocourse", query = "SELECT s FROM Student s WHERE size(s.courseList) = 0 ORDER BY s.name")})
public class Student implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "name")
    private String name;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 25)
    @Column(name = "username")
    private String username;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 25)
    @Column(name = "password")
    private String password;
    @ManyToMany(fetch = FetchType.EAGER, mappedBy = "studentList")
    private List<Course> courseList = new java.util.ArrayList<>();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        return id.equals(student.id);
    }

    @JsonIgnore
    public String getPassword() {
        return password;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
