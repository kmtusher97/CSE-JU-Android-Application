package com.example.noticebordcseju.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.example.noticebordcseju.R;
import com.google.firebase.auth.FirebaseAuth;

public class StudentActivity extends AppCompatActivity {

    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student);

        setTitle("Notice Board");

        firebaseAuth = FirebaseAuth.getInstance();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.student_menu_layout, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (item.getItemId() == R.id.signOutMenuItem) {
            FirebaseAuth.getInstance().signOut();
            finish();
            Intent intentMain = new Intent(getApplicationContext(), LoginActivity.class);
            startActivity(intentMain);
        }

        return super.onOptionsItemSelected(item);
    }
}
