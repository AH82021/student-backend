package com.ltp.gradesubmission.service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import com.ltp.gradesubmission.entity.Course;
import com.ltp.gradesubmission.entity.Student;
import com.ltp.gradesubmission.exception.EntityNotFoundException;
import com.ltp.gradesubmission.exception.StudentNotFoundException;
import com.ltp.gradesubmission.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentServiceImpl implements StudentService {

    StudentRepository studentRepository;
    @Autowired
    public  StudentServiceImpl(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public static Student unwrapStudent(Optional<Student> entity, Long studentId) {
        if(entity.isPresent()) return entity.get();
        else throw new EntityNotFoundException(studentId,Student.class);
    }

    @Override
    public Student getStudent(Long id) {
        Optional<Student> student  = studentRepository.findById(id);
        if(student.isPresent()){
            return student.get();
        }else
        {
          throw  new StudentNotFoundException(id);
        }

    }

    @Override
    public Student saveStudent(Student student) {
        return studentRepository.save(student);
    }

    @Override
    public void deleteStudent(Long id) {
        studentRepository.deleteById(id);
    }

    @Override
    public List<Student> getStudents() {
        return (List<Student>) studentRepository.findAll();
    }

    @Override
    public Set<Course> getEnrolledCourses(Long studentId) {
        Student student = getStudent(studentId);
        return student.getCourses();
    }


}