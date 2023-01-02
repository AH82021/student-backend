package com.ltp.gradesubmission.web;

import com.ltp.gradesubmission.entity.Course;
import com.ltp.gradesubmission.entity.Student;
import com.ltp.gradesubmission.service.StudentService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Set;

@AllArgsConstructor
@RestController
@RequestMapping("/student")
@Valid
public class StudentController {
  
    StudentService studentService;

    @GetMapping("/{id}")
    public ResponseEntity<Student> getStudentById(@PathVariable Long id){
        return new ResponseEntity<>(studentService.getStudent(id),HttpStatus.OK);
    }
    @GetMapping("/all")
    public ResponseEntity<List<Student>> getStudents(){
        return new ResponseEntity<>(studentService.getStudents(),HttpStatus.OK);
    }

   @PostMapping
    public ResponseEntity<Student> saveStudent(@RequestBody @Valid Student student){

        return new ResponseEntity<>(studentService.saveStudent(student),HttpStatus.CREATED);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Student> deleteStudent(@PathVariable Long id){
        studentService.deleteStudent(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/{studentId}/courses")
    public ResponseEntity<Set<Course>> getEnrolledCourses(@PathVariable Long studentId){
        return new ResponseEntity<>(studentService.getEnrolledCourses(studentId),HttpStatus.OK);
    }






}
