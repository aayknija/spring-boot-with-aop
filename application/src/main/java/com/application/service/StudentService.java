package com.application.service;

import com.application.entity.Student;

import java.util.List;

public interface StudentService {

    List<Student> listStudents();

    Student addStudent(Student student);

    Student getStudent(Long id);

    Student updateStudent(Long id, Student student);

    void deleteStudent(Long id);
}
