package com.example.signuploginfirebase;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

public class MainActivity extends AppCompatActivity {

    private TextView welcome1Text, welcome2Text;
    private Button next1Button;
    private CardView cardView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Bind UI elements to their respective IDs
        welcome1Text = findViewById(R.id.welcome1_text);
        welcome2Text = findViewById(R.id.welcome2_text);
        next1Button = findViewById(R.id.next1_button);
        cardView = findViewById(R.id.cardView);

        // No modification to default text in XML (kept as is)
        // "Welcome to The New Semester!" and "Enroll Now to Move to The Next Level!" are already set in XML

        // Set functionality for the "Next" button
        next1Button.setOnClickListener(v -> {
            // Navigate to the Enrollment Menu (or any next step activity)
            Intent intent = new Intent(MainActivity.this, EnrollmentMenuActivity.class);
            startActivity(intent);
            finish(); // Close MainActivity so the user can't go back to it
        });
    }
}
