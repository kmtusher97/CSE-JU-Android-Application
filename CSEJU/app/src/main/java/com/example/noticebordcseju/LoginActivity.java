package com.example.noticebordcseju;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


public class LoginActivity extends AppCompatActivity {

    private final String ADMIN_EMAIL = "kmtusher97@gmail.com";

    private EditText editTextUserEmail;
    private EditText editTextPassword;
    private Button buttonLogin, buttonForgetPassword;

    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setTitle("CSE-JU Login");

        /**Initialize Firebase Auth*/
        firebaseAuth = FirebaseAuth.getInstance();


        buttonLogin = findViewById(R.id.login_button);
        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editTextUserEmail = findViewById(R.id.userEmailInput);
                editTextPassword = findViewById(R.id.passwordInput);

                final String userEmail = editTextUserEmail.getText().toString().trim();
                String password = editTextPassword.getText().toString().trim();

                if (!validateData(userEmail, password, true)) {
                    return;
                }

                firebaseAuth.signInWithEmailAndPassword(userEmail, password)
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {

                                if (task.isSuccessful()) {
                                    finish();
                                    openHomeActivity(userEmail);
                                } else {
                                    Toast.makeText(
                                            getApplicationContext(),
                                            "Login Failed",
                                            Toast.LENGTH_LONG
                                    ).show();
                                }
                            }
                        });
            }
        });


        /**
         * password recovery
         */
        buttonForgetPassword = findViewById(R.id.forget_password_button);
        buttonForgetPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editTextUserEmail = findViewById(R.id.userEmailInput);
                final String userEmail = editTextUserEmail.getText().toString().trim();

                if (!validateData(userEmail, "", false)) {
                    return;
                }

                sendPasswordRecoveryEmail(userEmail);
            }
        });
    }

    /**
     * recover account
     * @param userEmail
     */
    private void sendPasswordRecoveryEmail(String userEmail) {
        firebaseAuth.sendPasswordResetEmail(userEmail)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(
                                    getApplicationContext(),
                                    "Recovery Email Sent",
                                    Toast.LENGTH_LONG
                            ).show();
                        }
                    }
                });
    }

    /**
     * validation of data
     * @param userEmail
     * @param password
     */
    private boolean validateData(String userEmail, String password, boolean type) {

        if (userEmail.isEmpty()) {
            editTextUserEmail.setError("Empty User Email");
            return false;
        }

        if (!android.util.Patterns.EMAIL_ADDRESS.matcher(userEmail).matches()) {
            editTextUserEmail.setError("Enter a valid email address");
            editTextUserEmail.requestFocus();
            return false;
        }

        if (type && password.isEmpty()) {
            editTextPassword.setError("Empty Password");
            return false;
        }
        System.out.println("okkkkkkkkkkkk");
        return true;
    }

    /**
     * through user to home activity
     * @param userEmail
     */
    private void openHomeActivity(String userEmail) {
        if (userEmail.equals(ADMIN_EMAIL)) {
            Intent intentAdmin = new Intent(getApplicationContext(), AdminActivity.class);
            startActivity(intentAdmin);
        } else {
            //
        }
    }

    @Override
    protected void onStart() {
        super.onStart();

        /**
         * Check if user is signed in
         */
        FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();
        if (firebaseUser != null) {
            openHomeActivity(firebaseUser.getEmail());
        }
    }
}
