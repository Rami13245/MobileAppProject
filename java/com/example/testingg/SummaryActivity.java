package com.example.testingg;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;

import androidx.appcompat.app.AppCompatActivity;

public class SummaryActivity extends AppCompatActivity {
    private static final int SPLASH_TIME_OUT = 2500; // 2.5 seconds

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_summary);

        // Simulate loading time
        new Handler(Looper.getMainLooper()).postDelayed(() -> {
            SessionManager sessionManager = new SessionManager(SummaryActivity.this);

            // Check if the user is already logged in
            if (sessionManager.isLoggedIn()) {
                // Redirect to MainActivity if logged in
                Intent intent = new Intent(SummaryActivity.this, MainActivity.class);
                startActivity(intent);
            } else {
                // Redirect to LoginActivity if not logged in
                Intent intent = new Intent(SummaryActivity.this, MainActivity.class);
                startActivity(intent);
            }
            finish();  // Close splash screen activity
        }, SPLASH_TIME_OUT);
    }
}