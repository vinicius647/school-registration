package com.project.school.schoolregistration.model;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class StudentDto {

    private Integer id;
    private String name;
    private String username;
    private String password;

}
