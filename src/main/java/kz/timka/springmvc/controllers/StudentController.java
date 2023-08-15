package kz.timka.springmvc.controllers;

import kz.timka.springmvc.models.Student;
import kz.timka.springmvc.repositories.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class StudentController {
    private StudentRepository studentRepository;

    @Autowired
    public StudentController(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    // http://localhost:8189/app -> корень


    // http://localhost:8189/app/add?product_id=5&b=6
    @GetMapping("/add")
    @ResponseBody
    public int add(@RequestParam Integer a, @RequestParam Integer b) {
        return a + b;
    }


    // GET http://localhost:8189/app/product/45/info
    @GetMapping("/students/{id}")
    public String showProductById(Model model, @PathVariable Long id) {
        Student student = studentRepository.findById(id);
        model.addAttribute("student", student);
        return "student_info_page";
    }

    @GetMapping("/students")
    public String showStudents(Model model) {
        model.addAttribute("students", studentRepository.getStudentList());
        return "students_page";
    }




}
