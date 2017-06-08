package test.studentacademy.service;

import app.studentacademy.model.Student;
import app.studentacademy.service.StudentService;
import app.studentacademy.springConfig.ApplicationContextConfig;
import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@ContextConfiguration(classes = {ApplicationContextConfig.class})
public class StudentServiceTest {

    @Autowired
    private StudentService studentService;
    static int id;

    @Test
    @Transactional
    public void A_testThatStudentIsSavedToDatabase() throws Exception {

        Student student = new Student.Builder()
                .name("Fana")
                .surname("Mothibe")
                .score(75)
                .build();

        Student savedStudent = (Student)  this.studentService.save(student);
        id = student.getId();

        Assert.assertNotNull(id);
        Assert.assertEquals(id, student.getId());
        Assert.assertEquals(savedStudent.getId(), student.getId());
    }

    @Test
    @Transactional
    public void B_testThatStudentIsReadFromDatabase() throws Exception {

        Student student = (Student) this.studentService.find(id);

        Assert.assertNotNull(student);
        Assert.assertEquals("Fana", student.getName());
        Assert.assertEquals("Mothibe", student.getSurname());
        Assert.assertEquals(75, student.getScore());
    }

    @Test
    @Transactional
    public void C_testThatThereIsAListOfStudents() throws Exception {
        List<Student> studentList = this.studentService.getAll();
        Assert.assertTrue(studentList.size() >= 0);
    }

    @Test
    @Transactional
    public void D_testThatStudentIsUpdated() throws Exception {
        Student student = (Student) this.studentService.find(id);

        Student updateStudent = new Student.Builder()
                .updater(student)
                .name("Thabile")
                .surname("Zulu")
                .score(100)
                .build();

        Student newlyUpdatedStudent = this.studentService.update(updateStudent);

        Assert.assertEquals(id, newlyUpdatedStudent.getId());
        Assert.assertEquals(updateStudent, newlyUpdatedStudent);
        Assert.assertEquals("Thabile", newlyUpdatedStudent.getName());
        Assert.assertEquals("Zulu", newlyUpdatedStudent.getSurname());
        Assert.assertEquals(100, newlyUpdatedStudent.getScore());
    }

    @Test
    public void E_testThatUserIsDeletedFromDatabase() throws Exception {

        Student deletedStudent = this.studentService.delete(id);

        Student finDeletedStudent = (Student) this.studentService.find(id);

        Assert.assertNotNull(deletedStudent);
        Assert.assertNull(finDeletedStudent);
    }
}
