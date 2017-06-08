package test.studentacademy.model;

import app.studentacademy.model.Student;
import org.junit.Assert;
import org.junit.Test;

public class StudentTest {

    @Test
    public void testThatStudentIsCreated() throws Exception {

        Student student = new Student.Builder()
                .id(1)
                .name("Casper")
                .surname("Nyovest")
                .score(80)
                .build();

        Assert.assertEquals(1, student.getId());
        Assert.assertEquals("Casper", student.getName());
        Assert.assertEquals("Nyovest", student.getSurname());
        Assert.assertEquals(80, student.getScore());
    }
}
