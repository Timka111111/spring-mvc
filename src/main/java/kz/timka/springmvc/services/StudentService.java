package kz.timka.springmvc.services;

import kz.timka.springmvc.exceptions.ResourceNotFoundException;
import kz.timka.springmvc.models.Student;
import kz.timka.springmvc.repositories.StudentRepository;
import kz.timka.springmvc.repositories.specification.StudentSpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
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

    public Page<Student> find(Integer minScore, Integer maxScore, String partName, Integer page) {

        Specification<Student> spec = Specification.where(null);
        // select s from Student s where true
        if(minScore != null) {
            spec = spec.and(StudentSpecification.scoreGreaterOrEqualsThan(minScore));
            // select s from Student s where true AND s.score > minScore
        }

        if(maxScore != null) {
            spec = spec.and(StudentSpecification.scoreLessThanOrEqualsThan(maxScore));
            // select s from Student s where true AND s.score > minScore AND s.score < maxScore
        }

        if(partName != null) {
            spec = spec.and(StudentSpecification.nameLike(partName));
            // select s from Student s where true AND s.score > minScore AND s.score < maxScore AND s.name like %partName%
        }
        return studentRepository.findAll(spec, PageRequest.of(page - 1, 5));

    }


    public void deleteById(Long id) {
        studentRepository.deleteById(id);
    }

    public Student save(Student student) {
        return studentRepository.save(student);
    }
}
