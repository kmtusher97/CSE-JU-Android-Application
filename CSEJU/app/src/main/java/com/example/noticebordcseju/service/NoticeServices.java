package com.example.noticebordcseju.service;

import com.example.noticebordcseju.model.Notice;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class NoticeServices {
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;

    public NoticeServices() {
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference("notices");
    }

    /**
     * add new notice
     * @param notice
     */
    public void addPost(Notice notice) {
        databaseReference.child(
                databaseReference.push().getKey()
        ).setValue(notice);
    }
}
