package com.example.ramosproject;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class BudgetingActivity extends AppCompatActivity {
    public static final String EXTRA_MESSAGE = "com.example.registration.MESSAGE";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_budgeting);


        Button btnshool = findViewById(R.id.btn_School_day);
        Button btneveryday = findViewById(R.id.btn_Every_day);

        EditText inptutxt = findViewById(R.id.txtname);

        btnshool.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String pastext = inptutxt.getText().toString();
                if (pastext.isEmpty()) {
                    Toast.makeText(BudgetingActivity.this, "Please enter your name", Toast.LENGTH_SHORT).show();
                    return;
                }

                Intent intent = new Intent(BudgetingActivity.this, AddnewActivity.class);
                intent.putExtra(EXTRA_MESSAGE, pastext);
                startActivity(intent);
            }
        });

        btneveryday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String pastext = inptutxt.getText().toString();
                if (pastext.isEmpty()) {
                    Toast.makeText(BudgetingActivity.this, "Please enter your name", Toast.LENGTH_SHORT).show();
                    return;
                }
                Intent intent = new Intent(BudgetingActivity.this, AddnewActivity2.class);
                intent.putExtra(EXTRA_MESSAGE, pastext);
                startActivity(intent);
            }
        });
    }
}