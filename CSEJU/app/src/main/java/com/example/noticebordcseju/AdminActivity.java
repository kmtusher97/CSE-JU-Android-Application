package com.example.noticebordcseju;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;

public class AdminActivity extends AppCompatActivity {

    private Button addStudentButton, editSyllabus, uploadNotice;

    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);

        setTitle("Admin");

        firebaseAuth = FirebaseAuth.getInstance();

        /**add student*/
        addStudentButton = findViewById(R.id.addStudentButton);
        addStudentButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentAddStudent = new Intent(getApplicationContext(), AddStudentActivity.class);
                startActivity(intentAddStudent);
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.admin_menu_layout, menu);
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
