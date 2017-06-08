package app.studentacademy.repository.Impl;

import app.studentacademy.model.Student;
import app.studentacademy.repository.StudentRepository;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import java.util.List;

@Repository
@Transactional
public class StudentRepositoryImpl implements StudentRepository {

    private static final String TABLE_NAME = "STUDENT";

    @Inject
    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory){
        this.sessionFactory = sessionFactory;
    }

    public Student save(Student student) {
        Session session = null;
        try{
            session = this.sessionFactory.openSession();
            session.beginTransaction();
            session.save(student);
            session.getTransaction().commit();
        } catch ( Exception e){
            session.getTransaction().rollback();
        } finally {
            if(session != null){
                session.close();
            }
        }
        return student;
    }

    public Student findById(Integer id) {
        Session session = null;
        Student student = null;
        try{
            session = this.sessionFactory.openSession();
            session.beginTransaction();
            student = (Student) session.load(Student.class, new Integer(id));
            session.getTransaction().commit();
        } catch (Exception e){
        } finally {
            if(session != null){
                session.close();
            }
        }
        return student;
    }

    public List<Student> getAll() {
        Session session = null;
        List<Student> studentList = null;
        String query = "from " + TABLE_NAME;
        try{
            session = this.sessionFactory.openSession();
            session.beginTransaction();
            studentList = session.createQuery(query).list();
            session.getTransaction().commit();
        } catch (Exception e){
            session.getTransaction().rollback();
        } finally {
            if(session != null){
                session.close();
            }
        }
        return studentList;
    }

    public Student update(Student student) {
        Session session = null;
        try{
            session = this.sessionFactory.openSession();
            session.beginTransaction();
            session.update(student);
            session.getTransaction().commit();
        } catch (Exception e){
            session.getTransaction().rollback();
            return student;
        } finally {
            if(session != null){
                session.close();
            }
        }
        return student;
    }

    public Student delete(Integer id) {
        Session session = null;
        Student deleteThisStudent = null;
        try{
            session = this.sessionFactory.openSession();
            session.beginTransaction();
            deleteThisStudent = this.findById(id);
            session.delete(deleteThisStudent);
            session.getTransaction().commit();
        } catch (Exception e){
            session.getTransaction().rollback();
        } finally {
            if(session != null){
                session.close();
            }
        }
        return deleteThisStudent;
    }
}
