package kz.timka.springmvc.controllers;

import kz.timka.springmvc.exceptions.ResourceNotFoundException;
import kz.timka.springmvc.dto.StudentDto;
import kz.timka.springmvc.models.Student;
import kz.timka.springmvc.repositories.StudentRepository;
import kz.timka.springmvc.services.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/students")
public class StudentController {
    private StudentService studentService;
    private StudentRepository studentRepository;
    @Autowired
    public StudentController(StudentService studentService, StudentRepository studentRepository) {
        this.studentService = studentService;
        this.studentRepository = studentRepository;
    }


    @GetMapping("")
    public Page<StudentDto> getAllStudent(
            @RequestParam(name = "p", defaultValue = "1") Integer page,
            @RequestParam(name = "min_score", required = false) Integer minScore,
            @RequestParam(name = "max_score", required = false) Integer maxScore,
            @RequestParam(name = "part_name", required = false) String partName
    ) {
        if(page < 1) {
            page = 1;
        }

        return studentService.find(minScore, maxScore, partName, page).map(
                s -> new StudentDto(s)
        );
    }

    @PostMapping()
    public Student saveStudent(@RequestBody Student student) {
        student.setId(null);
        return studentService.save(student);
    }


    @GetMapping("/{id}")
    public Student getStudentById(@PathVariable Long id) {
        return studentService.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Student not found by id: " + id));
    }

    @PutMapping()
    public Student updateStudent(@RequestBody Student student) {
        if(student.getId() == null) throw new RuntimeException(); //TODO
        return studentService.save(student);
    }




    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id) {
        studentService.deleteById(id);
    }




}
