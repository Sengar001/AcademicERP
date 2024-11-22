create  database if not exists AcademicERP;

use AcademicERP;

create table if not exists departments(
    department_id bigint auto_increment primary key,
    name varchar(20) not null unique,
    capacity int not null
);

create table if not exists employees(
    employee_id bigint auto_increment primary key,
    first_name varchar(20) not null,
    last_name varchar(20),
    email varchar(50) unique not null,
    title varchar(20),
    dep_id bigint not null,
    password varchar(100) not null
);

