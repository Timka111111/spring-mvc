package kz.timka.springmvc.services;

import kz.timka.springmvc.models.Student;
import kz.timka.springmvc.repositories.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class StudentService {

    private StudentRepository studentRepository;

    @Autowired
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }


    public Optional<Student> findById(Long id) {
        return studentRepository.findById(id);
    }

    public List<Student> findAll() {
       return studentRepository.findAll();
    }

    public void deleteById(Long id) {
        studentRepository.deleteById(id);
    }

    @Transactional
    public void changeScore(Long studentId, Integer delta) {
        // session open
        // transaction open
        Student student = studentRepository.findById(studentId).get();
        student.setScore(student.getScore() + delta);
        // session close
        // transaction close

    }

    public List<Student> findStudentsByScoreBetween(Integer min, Integer max) {
        return studentRepository.findAllByScoreBetween(min, max);
    }
}
