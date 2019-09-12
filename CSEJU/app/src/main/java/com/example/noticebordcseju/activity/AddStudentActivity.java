package com.example.noticebordcseju.activity;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.noticebordcseju.R;
import com.example.noticebordcseju.model.Student;
import com.example.noticebordcseju.service.StudentServices;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;

public class AddStudentActivity extends AppCompatActivity {

    private EditText
            editTextStudentName,
            editTextStudentEmail,
            editTextStudentRoll;

    private Button buttonAddStudent;

    private Student student;

    private Spinner spinnerStudentSession;

    private FirebaseAuth firebaseAuth;

    private StudentServices studentServices;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_student);

        /**fire base initialization*/
        firebaseAuth = FirebaseAuth.getInstance();

        /**make session list*/
        addSessionListToSessionSpinner();

        /**initialize StudentServices*/
        studentServices = new StudentServices();

        editTextStudentName = findViewById(R.id.student_name);
        editTextStudentEmail = findViewById(R.id.student_email);
        editTextStudentRoll = findViewById(R.id.student_roll);

        student = new Student();

        /**after submitting student info to add in database*/
        buttonAddStudent = findViewById(R.id.add_student_button);
        buttonAddStudent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFormData();
            }
        });
    }

    /**
     * get the form data
     */
    private void getFormData() {
        student.setName(editTextStudentName.getText().toString().trim());
        student.setEmail(editTextStudentEmail.getText().toString().trim());

        String rollText = editTextStudentRoll.getText().toString().trim();
        if (rollText.isEmpty()) {
            editTextStudentRoll.setError("Empty Roll");
            return;
        }
        student.setRoll(Integer.parseInt(rollText));
        student.setSession(spinnerStudentSession.getSelectedItem().toString().trim());

        if (!validateData(student)) {
            return;
        } else {
            addUser(student.getEmail(), "123456");
        }
    }

    /**
     * add new user
     * @param email
     * @param password
     */
    private void addUser(String email, String password) {
        firebaseAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(
                                    getApplicationContext(),
                                    "Student Added SuccessFully!!",
                                    Toast.LENGTH_LONG
                            ).show();

                            studentServices.addStudent(student);

                            AddStudentActivity.super.onBackPressed();
                        } else {
                            if (task.getException() instanceof FirebaseAuthUserCollisionException) {
                                Toast.makeText(
                                        getApplicationContext(),
                                        "Already Registered!!",
                                        Toast.LENGTH_SHORT
                                ).show();
                            } else {
                                Toast.makeText(
                                        getApplicationContext(),
                                        "Failed to Add Student",
                                        Toast.LENGTH_SHORT
                                ).show();
                            }

                        }
                    }
                });
    }

    /**
     * data validation
     * @param student
     */
    private boolean validateData(Student student) {
        if (student.getName().isEmpty()) {
            editTextStudentName.setError("Empty Name");
            return false;
        }

        if (student.getEmail().isEmpty()) {
            editTextStudentEmail.setError("Empty Email");
            return false;
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(student.getEmail()).matches()) {
            editTextStudentEmail.setError("Invalid Email");
            return false;
        }



        return true;
    }

    /**
     * add sessions to spinner
     */
    private void addSessionListToSessionSpinner() {
        spinnerStudentSession = findViewById(R.id.student_session);
        ArrayAdapter<CharSequence> adapterSessionList = ArrayAdapter.createFromResource(
                this,
                R.array.studnet_session_list,
                android.R.layout.simple_spinner_item
        );
        adapterSessionList.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerStudentSession.setAdapter(adapterSessionList);
    }


}
