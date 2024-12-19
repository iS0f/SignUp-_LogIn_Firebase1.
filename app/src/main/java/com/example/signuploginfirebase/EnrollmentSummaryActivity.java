package com.example.signuploginfirebase;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EnrollmentSummaryActivity extends AppCompatActivity {

    private TextView summary1TextView;
    private TextView summary2TextView;
    private TextView selectedSubjectsTextView;
    private TextView subjectsListTextView;
    private TextView totalCreditsTextView;
    private Button confirmEnrollmentButton;
    private List<SubjectModel> selectedSubjects;
    private int totalCredits;
    private String name; // Variable to store user name

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enrollment_summary);

        // Menghubungkan komponen UI dengan ID baru sesuai XML
        summary1TextView = findViewById(R.id.summary1_text);
        summary2TextView = findViewById(R.id.summary2_text);
        selectedSubjectsTextView = findViewById(R.id.selectedSubjectsTextView);
        subjectsListTextView = findViewById(R.id.subjectsListTextView);
        totalCreditsTextView = findViewById(R.id.totalCreditsTextView);
        confirmEnrollmentButton = findViewById(R.id.confirmEnrollmentButton);

        // Menerima data dari Intent yang dikirimkan dari aktivitas sebelumnya
        selectedSubjects = getIntent().getParcelableArrayListExtra("selectedSubjects");
        totalCredits = getIntent().getIntExtra("totalCredits", 0);
        name = getIntent().getStringExtra("name"); // Get the name from the Intent

        // Display the name of the user and the selected subjects
        displaySelectedSubjects();

        // Aksi tombol Konfirmasi Enrollment
        confirmEnrollmentButton.setOnClickListener(v -> saveEnrollmentToFirestore());
    }

    private void displaySelectedSubjects() {
        // Menampilkan nama pengguna di bagian atas daftar mata kuliah
        StringBuilder subjectsList = new StringBuilder();

        // Set user name in the TextView
        if (name != null && !name.isEmpty()) {
            summary1TextView.setText("Enrollment Summary");
            summary2TextView.setText("These are the courses you'll take next semester");
            selectedSubjectsTextView.setText("Selected Subjects:");

            // Display the user name (if available)
            subjectsList.append("Student: ").append(name).append("\n\n");
        }

        // Menambahkan daftar mata kuliah yang dipilih
        for (SubjectModel subject : selectedSubjects) {
            subjectsList.append("- ").append(subject.getName()).append(" - ")
                    .append(subject.getCredits()).append(" Credits\n");
        }

        // Memperbarui tampilan teks dengan daftar mata kuliah dan total kredit
        subjectsListTextView.setText(subjectsList.toString());
        totalCreditsTextView.setText("Total Credits: " + totalCredits);
    }

    // Fungsi untuk menyimpan data enrollment ke Firestore
    private void saveEnrollmentToFirestore() {
        // Ambil instance Firestore
        FirebaseFirestore db = FirebaseFirestore.getInstance();

        // Data yang akan disimpan
        Map<String, Object> enrollmentData = new HashMap<>();

        // Menambahkan totalCredits dan daftar mata kuliah yang dipilih
        enrollmentData.put("totalCredits", totalCredits);

        // Buat daftar mata kuliah sebagai array string
        List<String> selectedSubjectsList = new ArrayList<>();
        for (SubjectModel subject : selectedSubjects) {
            selectedSubjectsList.add(subject.getName() + " (" + subject.getCredits() + " Credits)");
        }
        enrollmentData.put("selectedSubjects", selectedSubjectsList);

        // Menyimpan data ke koleksi "Enrollments"
        db.collection("Enrollments")
                .add(enrollmentData)
                .addOnSuccessListener(documentReference -> {
                    // Jika berhasil menyimpan data
                    Toast.makeText(this, "Enrollment Confirmed!", Toast.LENGTH_SHORT).show();
                })
                .addOnFailureListener(e -> {
                    // Jika gagal menyimpan data
                    Toast.makeText(this, "Error saving enrollment: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                    e.printStackTrace(); // Cetak error di log untuk debugging
                });
    }
}
