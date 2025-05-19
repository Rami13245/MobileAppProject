package com.example.testingg;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class TermsofServices extends AppCompatActivity {

    private Button acceptButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_terms_of_services);

        acceptButton = findViewById(R.id.btn_accept_terms);

        acceptButton.setOnClickListener(v -> {
            SessionManager sessionManager = new SessionManager(getApplicationContext());
            sessionManager.acceptTerms();

            Toast.makeText(this, "Thanks for accepting our Terms!", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(this, LoginActivity.class));
            finish();
        });
    }
}
