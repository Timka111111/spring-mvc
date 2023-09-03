package kz.timka.springmvc.controllers;

import kz.timka.springmvc.exceptions.AppError;
import kz.timka.springmvc.models.Student;
import kz.timka.springmvc.repositories.StudentRepository;
import kz.timka.springmvc.services.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class StudentController {
    private StudentService studentService;
    private StudentRepository studentRepository;
    @Autowired
    public StudentController(StudentService studentService, StudentRepository studentRepository) {
        this.studentService = studentService;
        this.studentRepository = studentRepository;
    }




    @GetMapping("/students")
    public List<Student> getAllStudent() {
        return studentService.findAll();
    }


    @GetMapping("/students/{id}")
    public ResponseEntity<?> getStudentById(@PathVariable Long id) {
        Optional<Student> student = studentService.findById(id);
        if(student.isPresent()) {
            return new ResponseEntity<>(student.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(
                new AppError(HttpStatus.NOT_FOUND.value(), "Student not found by id: " + id)
                ,HttpStatus.NOT_FOUND);
    }


    @GetMapping("/students/delete/{id}")
    public void deleteById(@PathVariable Long id) {
        studentService.deleteById(id);
    }

    @GetMapping("/students/change_score")
    public void changeScore(@RequestParam Long studentId, @RequestParam Integer delta) {
        studentService.changeScore(studentId, delta);
    }

    @GetMapping("/students/score_between")
    public List<Student> findStudentsByScoreBetween(
            @RequestParam(defaultValue = "0") Integer min,
            @RequestParam(defaultValue = "100") Integer max) {
        return studentService.findStudentsByScoreBetween(min, max);
    }


    @GetMapping("/demo")
    public Object demo(@RequestParam String name) {
        return studentRepository.sqlGetScoreByName(name);
    }

}
