create table course (
    id bigint not null auto_increment,
    course_name varchar(50) not null,
    CONSTRAINT student_pkey PRIMARY KEY (id)
);

create table student (
    id bigint not null auto_increment,
    name varchar(50) not null,
    username varchar(25) not null,
    password varchar(25) not null,
    CONSTRAINT primary key (id)
);

create table course_student (
    course_id  bigint not null,
    student_id bigint not null,
    CONSTRAINT PK_course_student PRIMARY KEY (course_id, student_id)
);