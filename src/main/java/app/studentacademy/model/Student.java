package app.studentacademy.model;

import org.hibernate.annotations.Proxy;

import javax.persistence.*;
import java.io.Serializable;

@Entity(name = "STUDENT")
@Proxy(lazy = false)
public class Student implements Serializable{

    public Student(){}

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name = "NAME")
    private String name;

    @Column(name = "SURNAME")
    private String surname;

    @Column(name = "SCORE")
    private int score;

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public int getScore() {
        return score;
    }

    @Override
    public String toString() {
        return String.format("Student Details : \n Id %d \n Name %s \n Surname %s \n Score %d", id, name,
                surname, score);
    }

    public Student(Builder builder){
        this.id = builder.id;
        this.name = builder.name;
        this.surname = builder.surname;
        this.score = builder.score;
    }

    public static class Builder{

        private int id;
        private String name;
        private String surname;
        private int score;

        public Builder id(int id){
            this.id = id;
            return this;
        }

        public Builder name(String name){
            this.name = name;
            return this;
        }

        public Builder surname(String surname){
            this.surname = surname;
            return this;
        }

        public Builder score(int score){
            this.score = score;
            return this;
        }

        public Builder updater(Student student){
            this.id = student.id;
            this.name = student.name;
            this.surname = student.surname;
            this.score = student.score;
            return this;
        }

        public Student build(){
            return new Student(this);
        }
    }
}
