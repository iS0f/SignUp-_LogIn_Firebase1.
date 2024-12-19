package com.example.signuploginfirebase;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class SubjectAdapter extends RecyclerView.Adapter<SubjectAdapter.SubjectViewHolder> {
    private final List<SubjectModel> subjectList;
    private final OnSubjectClickListener listener;
    private int totalCredits;  // Tracks the total credits

    public SubjectAdapter(List<SubjectModel> subjectList, OnSubjectClickListener listener, int totalCredits) {
        this.subjectList = subjectList;
        this.listener = listener;
        this.totalCredits = totalCredits;  // Initialize with the current total credits
    }

    @NonNull
    @Override
    public SubjectViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_subject, parent, false);
        return new SubjectViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SubjectViewHolder holder, int position) {
        SubjectModel subject = subjectList.get(position);

        // Display subject name and credit
        holder.subjectNameTextView.setText(subject.getName());
        holder.subjectCreditsTextView.setText(String.format("%d Credits", subject.getCredits()));

        // Set checkbox checked state based on selection
        holder.subjectCheckBox.setOnCheckedChangeListener(null);  // To prevent recursion
        holder.subjectCheckBox.setChecked(subject.isSelected());

        // Disable checkbox if selecting it would exceed 24 credits
        holder.subjectCheckBox.setEnabled(totalCredits + subject.getCredits() <= 24 || subject.isSelected());

        // Handle checkbox click
        holder.subjectCheckBox.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                // Check if credits exceed the limit
                if (totalCredits + subject.getCredits() <= 24) {
                    totalCredits += subject.getCredits();  // Increase total credits
                    subject.setSelected(true);
                    listener.onSubjectSelected(subject);
                } else {
                    // Display message if credits exceed limit
                    Toast.makeText(holder.itemView.getContext(), "Cannot select more than 24 credits!", Toast.LENGTH_SHORT).show();
                    holder.subjectCheckBox.setChecked(false);  // Uncheck the checkbox if exceeds limit
                }
            } else {
                // Decrease total credits when unselecting
                totalCredits -= subject.getCredits();  // Decrease total credits
                subject.setSelected(false);  // Unselect the subject
                listener.onSubjectUnselected(subject);

                // Enable selection again if total credits are less than 24
                notifyItemChanged(holder.getAdapterPosition());  // Update the item view
            }

            // Check if total credits exceed 24
            if (totalCredits > 24) {
                // Allow switching between checked items
                Toast.makeText(holder.itemView.getContext(), "Unselect a subject first to change selection!", Toast.LENGTH_SHORT).show();
                holder.subjectCheckBox.setEnabled(false);
            }
        });
    }

    @Override
    public int getItemCount() {
        return subjectList.size();
    }

    public interface OnSubjectClickListener {
        void onSubjectSelected(SubjectModel subject);
        void onSubjectUnselected(SubjectModel subject);
    }

    public static class SubjectViewHolder extends RecyclerView.ViewHolder {
        TextView subjectNameTextView, subjectCreditsTextView;
        CheckBox subjectCheckBox;

        public SubjectViewHolder(@NonNull View itemView) {
            super(itemView);
            subjectNameTextView = itemView.findViewById(R.id.subjectName);
            subjectCreditsTextView = itemView.findViewById(R.id.subjectCredits);
            subjectCheckBox = itemView.findViewById(R.id.subjectCheckBox);
        }
    }
}
