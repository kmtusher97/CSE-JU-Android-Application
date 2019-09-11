package com.example.noticebordcseju.service;

import com.example.noticebordcseju.model.Student;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class StudentServices {
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;

    public StudentServices() {
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference("students");
    }

    /**
     * add new student it database
     * @param student
     */
    public void addStudent(Student student) {
        databaseReference.child(
                databaseReference.push().getKey()
        ).setValue(student);
    }
}
