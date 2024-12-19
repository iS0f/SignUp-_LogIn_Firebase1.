package com.example.signuploginfirebase;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

public class LogInActivity extends AppCompatActivity {

    private EditText loginEmail, loginPassword;
    private TextView signupRedirectText;
    private Button loginButton;
    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);

        // Initialize Firebase Authentication
        auth = FirebaseAuth.getInstance();

        // Bind UI elements to their respective IDs
        loginEmail = findViewById(R.id.login_email);
        loginPassword = findViewById(R.id.login_password);
        loginButton = findViewById(R.id.login_button);
        signupRedirectText = findViewById(R.id.signUpRedirectText);

        // Set login button functionality
        loginButton.setOnClickListener(v -> {
            String email = loginEmail.getText().toString();
            String pass = loginPassword.getText().toString();

            if (!email.isEmpty() && Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                if (!pass.isEmpty()) {
                    auth.signInWithEmailAndPassword(email, pass)
                            .addOnSuccessListener(authResult -> {
                                Toast.makeText(LogInActivity.this, "Login Successful", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(LogInActivity.this, MainActivity.class));
                                finish();
                            })
                            .addOnFailureListener(e ->
                                    Toast.makeText(LogInActivity.this, "Login Failed", Toast.LENGTH_SHORT).show()
                            );
                } else {
                    loginPassword.setError("Empty fields are not allowed");
                }
            } else if (email.isEmpty()) {
                loginEmail.setError("Empty fields are not allowed");
            } else {
                loginEmail.setError("Please enter correct email");
            }
        });

        // Redirect to Sign Up activity
        signupRedirectText.setOnClickListener(v ->
                startActivity(new Intent(LogInActivity.this, SignUpActivity.class))
        );
    }
}
