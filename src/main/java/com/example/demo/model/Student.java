package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import net.bytebuddy.implementation.bind.annotation.Super;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;
    private String email;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="student_superviser")
    private Superviser superviser;

    public Student() {
    }

    public Student( String name, String email, Superviser superviser) {
        this.name = name;
        this.email = email;
        this.superviser = superviser;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Superviser getSuperviser() {
        return superviser;
    }

    public void setSuperviser(Superviser superviser) {
        this.superviser = superviser;
    }
}
