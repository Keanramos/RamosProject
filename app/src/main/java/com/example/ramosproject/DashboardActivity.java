package com.example.ramosproject;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class DashboardActivity extends AppCompatActivity {

    private RecyclerView recyclerViewNotes;
    private NoteAdapter noteAdapter;
    private List<Note> noteList;

    FirebaseDatabase db;
    DatabaseReference reference;

    public static final String EXTRA_MESSAGE = "com.example.registration.MESSAGE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_dashboard);

        // Get username and display
        TextView username = findViewById(R.id.Textbudget);
        Intent intent = getIntent();
        String printUsername = intent.getStringExtra(EXTRA_MESSAGE);
        username.setText(printUsername);

        // Initialize Firebase
        db = FirebaseDatabase.getInstance();
        reference = db.getReference("Users");

        // Setup RecyclerView
        recyclerViewNotes = findViewById(R.id.recyclerViewNotes);
        recyclerViewNotes.setLayoutManager(new LinearLayoutManager(this));
        recyclerViewNotes.setHasFixedSize(true);
        recyclerViewNotes.setItemAnimator(new DefaultItemAnimator());

        noteList = new ArrayList<>();
        noteAdapter = new NoteAdapter(noteList);
        recyclerViewNotes.setAdapter(noteAdapter);

        // Load data from Firebase
        loadNotesFromFirebase();

        // Add New Button
        Button btnAddNew = findViewById(R.id.button_add_new);
        btnAddNew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(DashboardActivity.this, "Opening Add New", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(DashboardActivity.this, BudgetingActivity.class));
            }
        });
    }

    private void loadNotesFromFirebase() {
        reference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                noteList.clear();
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    UserBudget user = dataSnapshot.getValue(UserBudget.class);
                    if (user != null) {
                        // Convert UserBudget to Note object for adapter
                        Note note = new Note(user.getTitle(), user.getName(), user.getDaysOfSchool(), user.getDescription());
                        noteList.add(note);
                    }
                }
                noteAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(DashboardActivity.this, "Failed to load data: " + error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
