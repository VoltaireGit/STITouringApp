package com.example.stitouringapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class OTP extends AppCompatActivity {
    TextView btnVerify;
    String username;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otp);

        // Get username from previous screen
        username = getIntent().getStringExtra("username");

        btnVerify = findViewById(R.id.btnVerify);

        int correctOtp = getIntent().getIntExtra("otp", 0);
        String username = getIntent().getStringExtra("username");


        btnVerify.setOnClickListener(v -> {
            // Show welcome toast
            Toast.makeText(this, "Welcome, " + username + "!", Toast.LENGTH_SHORT).show();

            // Go to MainActivity2
            Intent intent = new Intent(OTP.this, MainActivity2.class);
            startActivity(intent);
            finish(); // Optional: prevents going back to OTP
        });
    }
}
