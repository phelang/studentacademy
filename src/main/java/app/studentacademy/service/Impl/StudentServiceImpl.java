package app.studentacademy.service.Impl;

import app.studentacademy.model.Student;
import app.studentacademy.repository.StudentRepository;
import app.studentacademy.service.StudentService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import java.util.List;

@Service
public class StudentServiceImpl implements StudentService{

    @Inject
    private StudentRepository studentRepository;

    public void setStudentRepository(StudentRepository studentRepository){
        this.studentRepository = studentRepository;
    }

    @Transactional
    public Student save(Student student) {
        return this.studentRepository.save(student);
    }

    @Transactional
    public Student find(Integer id) {
        return this.studentRepository.findById(id);
    }

    @Transactional
    public List<Student> getAll() {
        return this.studentRepository.getAll();
    }

    @Transactional
    public Student update(Student student) {
        return this.studentRepository.update(student);
    }

    @Transactional
    public Student delete(Integer id) {
        return this.studentRepository.delete(id);
    }
}
