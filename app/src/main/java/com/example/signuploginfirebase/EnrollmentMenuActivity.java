package com.example.signuploginfirebase;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class EnrollmentMenuActivity extends AppCompatActivity implements SubjectAdapter.OnSubjectClickListener {

    private RecyclerView recyclerView;
    private SubjectAdapter subjectAdapter;
    private List<SubjectModel> subjectList = new ArrayList<>();
    private List<SubjectModel> selectedSubjects = new ArrayList<>();
    private int totalCredits = 0;
    private Button next2Button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enrollment_menu);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Load the subjects
        loadSubjects();

        // Initialize the adapter and pass the current totalCredits
        subjectAdapter = new SubjectAdapter(subjectList, this, totalCredits);
        recyclerView.setAdapter(subjectAdapter);

        next2Button = findViewById(R.id.next2_button);
        updateNextButtonState();

        next2Button.setOnClickListener(v -> {
            if (totalCredits >= 6 && totalCredits <= 24) {
                Intent intent = new Intent(EnrollmentMenuActivity.this, EnrollmentSummaryActivity.class);
                intent.putParcelableArrayListExtra("selectedSubjects", new ArrayList<>(selectedSubjects));
                intent.putExtra("totalCredits", totalCredits);
                startActivity(intent);
            }
        });
    }

    private void loadSubjects() {
        subjectList.add(new SubjectModel("Advanced Makeup Artistry", 6));
        subjectList.add(new SubjectModel("Professional Product Knowledge", 6));
        subjectList.add(new SubjectModel("Color Theory in Makeup", 6));
        subjectList.add(new SubjectModel("Dermatology for Beauty Professionals", 6));
        subjectList.add(new SubjectModel("Holistic Skincare", 6));
        subjectList.add(new SubjectModel("Scalp Micropigmentation", 6));
    }

    @Override
    public void onSubjectSelected(SubjectModel subject) {
        selectedSubjects.add(subject);
        totalCredits += subject.getCredits();
        updateNextButtonState();
    }

    @Override
    public void onSubjectUnselected(SubjectModel subject) {
        selectedSubjects.remove(subject);
        totalCredits -= subject.getCredits();
        updateNextButtonState();
    }

    private void updateNextButtonState() {
        next2Button.setEnabled(totalCredits >= 6 && totalCredits <= 24);
    }
}
