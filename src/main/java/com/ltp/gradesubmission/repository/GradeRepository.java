package com.ltp.gradesubmission.repository;

import com.ltp.gradesubmission.entity.Grade;
import com.ltp.gradesubmission.entity.Student;
import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

public interface GradeRepository extends CrudRepository<Grade,Long> {
 Optional<Grade> findByStudentIdAndCourseId(Long studentId, Long courseId);
 @Transactional
 void deleteByStudentIdAndCourseId(Long studentId,Long courseId);

 List<Grade> findGradesByStudentId(Long studentId);
 List<Grade> findGradesByCourseId(Long studentId);

}