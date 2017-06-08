package test.studentacademy.repository;

import app.studentacademy.model.Student;
import app.studentacademy.repository.StudentRepository;
import app.studentacademy.springConfig.ApplicationContextConfig;
import app.studentacademy.springConfig.ServletInitializer;
import app.studentacademy.springConfig.SpringWebConfig;
import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.List;


@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@ContextConfiguration(classes = {ApplicationContextConfig.class, ServletInitializer.class, SpringWebConfig.class})
public class StudentRepositoryTest {

    @Autowired
    private StudentRepository studentRepository;
    static int id;

    @Test
    public void A_testThatUserIsSavedToDatabase() throws Exception {

        Student student = new Student.Builder()
                .name("Robert")
                .surname("Martin")
                .score(85)
                .build();

        Student savedStudent = (Student) this.studentRepository.save(student);
        id = student.getId();

        Assert.assertNotNull(id);
        Assert.assertEquals(student.getId(), id);
        Assert.assertEquals(savedStudent.getId(), student.getId());

    }

    @Test
    public void B_testThatStudentIsReadFromDatabase() throws Exception {

        Student student = (Student) this.studentRepository.findById(id);

        Assert.assertNotNull(student);
        Assert.assertEquals("Robert", student.getName());
        Assert.assertEquals("Martin", student.getSurname());
        Assert.assertEquals(id, student.getId());
    }

    @Test
    public void C_testThatThereIsAListOfStudents() throws Exception {

        List<Student> studentList = this.studentRepository.getAll();
        Assert.assertTrue(studentList.size() >= 0);
    }

    @Test
    public void D_testThatUserIsUpdatedToDatabase() throws Exception {
        Student student = (Student) this.studentRepository.findById(2);

        Student updateStudent = new Student.Builder()
                .updater(student)
                .name("Robert C")
                .surname("Martin")
                .score(100)
                .build();

        Student newlyUpdatedStudent = this.studentRepository.update(updateStudent);

        Assert.assertEquals("Robert C", newlyUpdatedStudent.getName());
        Assert.assertEquals("Martin", newlyUpdatedStudent.getSurname());
        Assert.assertEquals(100, newlyUpdatedStudent.getScore());
    }

    @Test
    public void E_testThatUserIsDeletedFromDatabase() throws Exception {

        Student deletedStudent = this.studentRepository.delete(id);

        Student finDeletedStudent = (Student) this.studentRepository.findById(id);

        Assert.assertNotNull(deletedStudent);
        Assert.assertNull(finDeletedStudent);
    }
}
