package kz.timka.springmvc.repositories;

import jakarta.annotation.PostConstruct;
import kz.timka.springmvc.models.Student;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Component
public class StudentRepository {
    private List<Student> studentList;


    @PostConstruct
    public void init() {
        studentList = new ArrayList<>(List.of(
                new Student(1L, "Bob"),
                new Student(2L, "Jack"),
                new Student(3L, "Antony"),
                new Student(4L, "Margaret"),
                new Student(5L, "Jane")
        ));
    }

    public List<Student> getStudentList() {
        return Collections.unmodifiableList(studentList);
    }


    public Student findById(Long id) {
        return studentList.stream().filter(s -> s.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Student not found by id: " + id));
    }
}
