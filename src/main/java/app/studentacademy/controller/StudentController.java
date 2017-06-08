package app.studentacademy.controller;

import app.studentacademy.model.Student;
import app.studentacademy.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/student")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @RequestMapping(value = "/add", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Student> addStudent(@RequestBody Student student){
        return new ResponseEntity<Student>(studentService.save(student), HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.POST, headers = "Accept=application/json")
    public ResponseEntity<Student> getStudent(@PathVariable int id){
        return new ResponseEntity<Student>(studentService.find(id), HttpStatus.OK);
    }

    @RequestMapping(value = "/students", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Student>> getAllStudents(){

        List<Student> students = this.studentService.getAll();

        if(students.isEmpty()){
            return new ResponseEntity<List<Student>>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<List<Student>>(students, HttpStatus.OK);
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Student> updateStudent(@RequestBody Student student){
        return new ResponseEntity<Student>(studentService.update(student), HttpStatus.OK);
    }

    @RequestMapping(value = "/remove/{id}", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Student> removeStudent(@PathVariable("id") int id){
        return new ResponseEntity<Student>(studentService.delete(id), HttpStatus.OK);
    }
}