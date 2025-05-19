package com.example.testingg;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.appbar.MaterialToolbar;

public class MainActivity extends AppCompatActivity {

    TextView welcomeText;
    Button logoutButton, addMealsButton, viewTrendsButton;
    SessionManager sessionManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sessionManager = new SessionManager(getApplicationContext());

        welcomeText = findViewById(R.id.welcomeText);
        logoutButton = findViewById(R.id.btn_logout);
        addMealsButton = findViewById(R.id.btn_add_meals);
        viewTrendsButton = findViewById(R.id.btn_view_trends);

        MaterialToolbar toolbar = findViewById(R.id.topAppBarMain);
        toolbar.setOnMenuItemClickListener(item -> {
            if (item.getItemId() == R.id.menu_profile) {
                startActivity(new Intent(MainActivity.this, ProfileActivity.class));
                return true;
            }
            return false;
        });

        if (sessionManager.isLoggedIn()) {
            String email = sessionManager.getUserEmail();
            welcomeText.setText("Welcome " + email);
        } else {
            welcomeText.setText("Welcome!");
        }

        logoutButton.setOnClickListener(v -> {
            sessionManager.logoutUser();
            startActivity(new Intent(MainActivity.this, LoginActivity.class));
            finish();
        });

        addMealsButton.setOnClickListener(v ->
                startActivity(new Intent(MainActivity.this, AddMealsActivity.class)));

        viewTrendsButton.setOnClickListener(v ->
                startActivity(new Intent(MainActivity.this, ViewTrends.class)));
    }
}
