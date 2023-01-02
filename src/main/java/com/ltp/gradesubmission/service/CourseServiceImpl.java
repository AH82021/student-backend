package com.ltp.gradesubmission.service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import com.ltp.gradesubmission.entity.Course;
import com.ltp.gradesubmission.entity.Student;
import com.ltp.gradesubmission.exception.CourseNotFoundException;
import com.ltp.gradesubmission.exception.EntityNotFoundException;
import com.ltp.gradesubmission.repository.CourseRepository;
import com.ltp.gradesubmission.repository.StudentRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class CourseServiceImpl implements CourseService {
 CourseRepository courseRepository;
 StudentRepository studentRepository;

    public static Course unwrapCourse(Optional<Course> entity, Long courseId) {
        if(entity.isPresent()) return entity.get();
        else throw new EntityNotFoundException(courseId,Course.class);
    }

    @Override
    public Course getCourse(Long id) {
        Optional<Course> course = courseRepository.findById(id);
        if (course.isPresent()) {
         return    course.get();
        } else {
           throw new  EntityNotFoundException(id,Course.class);
        }

    }

    @Override
    public Course saveCourse(Course course) {

        return courseRepository.save(course);
    }

    @Override
    public void deleteCourse(Long id) {
        courseRepository.deleteById(id);
    }

    @Override
    public List<Course> getCourses() {

        return (List<Course>) courseRepository.findAll();
    }


@Override
    public Course addStudentToCourse(Long studentId,Long courseId){
        Course course = getCourse(courseId);
       Optional<Student> student      = studentRepository.findById(studentId);
       Student unwrapStudent = StudentServiceImpl.unwrapStudent(student,studentId);
      course.getStudents().add(unwrapStudent);
      return courseRepository.save(course);
    }

    @Override
    public Set<Student> getEnrolledStudents(Long courseId) {
        Course course = getCourse(courseId);
        return course.getStudents() ;
    }

}
