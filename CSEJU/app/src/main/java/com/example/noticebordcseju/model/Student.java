package com.example.noticebordcseju.model;

public class Student {
    private String email;
    private Integer roll;
    private String session;
    private String name;

    public Student() {
    }

    public Student(String email, Integer roll, String session, String name) {
        this.email = email;
        this.roll = roll;
        this.session = session;
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getRoll() {
        return roll;
    }

    public void setRoll(Integer roll) {
        this.roll = roll;
    }

    public String getSession() {
        return session;
    }

    public void setSession(String session) {
        this.session = session;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Student{" +
                "email='" + email + '\'' +
                ", roll=" + roll +
                ", session='" + session + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
