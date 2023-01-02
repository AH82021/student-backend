package com.ltp.gradesubmission.service;

import java.util.List;
import java.util.Optional;

import com.ltp.gradesubmission.entity.Course;
import com.ltp.gradesubmission.entity.Grade;
import com.ltp.gradesubmission.entity.Student;
import com.ltp.gradesubmission.exception.CourseNotFoundException;
import com.ltp.gradesubmission.exception.EntityNotFoundException;
import com.ltp.gradesubmission.exception.GradeNotFoundException;
import com.ltp.gradesubmission.exception.StuentNotEnrolledException;
import com.ltp.gradesubmission.repository.CourseRepository;
import com.ltp.gradesubmission.repository.GradeRepository;
import com.ltp.gradesubmission.repository.StudentRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@AllArgsConstructor
@Service
public class GradeServiceImpl implements GradeService {
 GradeRepository gradeRepository;
 CourseRepository courseRepository;
 StudentRepository studentRepository;

    static Grade unwrapGrade(Optional<Grade> entity, Long studentId, Long courseId) {
        if (entity.isPresent()) return entity.get();
        else throw new EntityNotFoundException(courseId,Course.class);
    }

    @Override
    public Grade getGrade(Long studentId, Long courseId) {
            Optional<Grade> grade = gradeRepository.findByStudentIdAndCourseId(studentId,courseId);
            return unwrapGrade(grade,studentId,courseId);

    }

    @Override
    public Grade saveGrade(Grade grade, Long studentId, Long courseId) {
        Student student = StudentServiceImpl.unwrapStudent(studentRepository.findById(studentId), studentId);
        Course course =CourseServiceImpl.unwrapCourse(courseRepository.findById(courseId),courseId);
        if(!student.getCourses().contains(course)) throw  new StuentNotEnrolledException(studentId,courseId);
        grade.setStudent(student);
        grade.setCourse(course);
        return gradeRepository.save(grade);
    }

    @Override
    public Grade updateGrade(String score, Long studentId, Long courseId) {

      Optional < Grade> grade = gradeRepository.findByStudentIdAndCourseId(studentId,courseId);
      Grade unWrappedGrade = unwrapGrade(grade,studentId,courseId);
      unWrappedGrade.setScore(score);

      return  gradeRepository.save(unWrappedGrade);


    }

    @Override
    public void deleteGrade(Long studentId, Long courseId) {

      gradeRepository.deleteByStudentIdAndCourseId(studentId,courseId);

    }

    @Override
    public List<Grade> getStudentGrades(Long studentId) {
      return  gradeRepository.findGradesByStudentId(studentId);

    }

    @Override
    public List<Grade> getCourseGrades(Long courseId) {

        return gradeRepository.findGradesByCourseId(courseId);
    }

    @Override
    public List<Grade> getAllGrades() {
        return (List<Grade>) gradeRepository.findAll();
    }

}
