package com.application.service;

import com.application.entity.Student;
import com.application.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentRepository studentRepository;

    @Override
    public List<Student> listStudents() {
        return studentRepository.findAll();
    }

    @Override
    public Student addStudent(Student student) {
        return studentRepository.save(student);
    }

    @Override
    public Student getStudent(Long id) {
        Student student = null;
        Optional<Student> optionalStudent = studentRepository.findById(id);
        if(optionalStudent.isPresent()){
            student = optionalStudent.get();
        }
        return student;
    }

    @Override
    public Student updateStudent(Long id, Student student) {
        Student studentFromDB = getStudent(id);
        if(studentFromDB != null){
            student.setId(studentFromDB.getId());
            student = studentRepository.saveAndFlush(student);
        }
        return student;
    }

    @Override
    public void deleteStudent(Long id) {
        Student studentFromDb = getStudent(id);
        if(studentFromDb != null){
            studentRepository.delete(studentFromDb);
        }
    }
}
