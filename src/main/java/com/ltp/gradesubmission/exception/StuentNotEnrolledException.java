package com.ltp.gradesubmission.exception;

public class StuentNotEnrolledException extends RuntimeException {
    public StuentNotEnrolledException(Long studentId, Long courseId) {
        super("The student id '" + studentId + "' does not enrolled in course: "+courseId);
    }
}
