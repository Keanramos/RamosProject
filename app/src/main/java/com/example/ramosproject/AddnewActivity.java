package com.example.ramosproject;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;
import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.ramosproject.databinding.ActivityAddnewBinding;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class AddnewActivity extends AppCompatActivity {

    ActivityAddnewBinding binding;
    DatabaseReference reference;
    FirebaseDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);

        binding = ActivityAddnewBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        db = FirebaseDatabase.getInstance();
        reference = db.getReference("Users");

        // Get passed name from BudgetingActivity
        String passedName = getIntent().getStringExtra(BudgetingActivity.EXTRA_MESSAGE);
        binding.Name.setText(passedName);

        if (passedName != null && !passedName.isEmpty()) {
            reference.child(passedName).addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    if (snapshot.exists()) {
                        // Prefill form if user exists
                        String title = snapshot.child("title").getValue(String.class);
                        String days = snapshot.child("daysOfSchool").getValue(String.class);
                        String desc = snapshot.child("description").getValue(String.class);

                        binding.inputTitle.setText(title);
                        binding.DaysOfSchool.setText(days);
                        binding.Description.setText(desc);
                        Toast.makeText(AddnewActivity.this, "Existing user loaded.", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(AddnewActivity.this, "No existing data. Please fill the form.", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {
                    Toast.makeText(AddnewActivity.this, "DB Error: " + error.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        }

        binding.Buttonsave.setOnClickListener(view -> {
            String name = binding.Name.getText().toString();
            String title = binding.inputTitle.getText().toString();
            String days = binding.DaysOfSchool.getText().toString();
            String desc = binding.Description.getText().toString();



            if (!name.isEmpty() && !title.isEmpty() && !days.isEmpty() && !desc.isEmpty()) {
                // Create a user object
                UserBudget user = new UserBudget(name, title, days, desc);
                reference.child(name).setValue(user).addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        Toast.makeText(AddnewActivity.this, "Data saved successfully!", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent (AddnewActivity.this, DashboardActivity.class);
                        startActivity(intent);
                    } else {
                        Toast.makeText(AddnewActivity.this, "Failed to save data.", Toast.LENGTH_SHORT).show();
                    }
                });
            } else {
                Toast.makeText(AddnewActivity.this, "Please fill all fields!", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
