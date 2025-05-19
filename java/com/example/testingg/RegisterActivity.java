package com.example.testingg;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;


import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class RegisterActivity extends AppCompatActivity {
    EditText name, email, password;
    Button registerBtn;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        name = findViewById(R.id.name);
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        registerBtn = findViewById(R.id.registerBtn);

        registerBtn.setOnClickListener(v -> {
            String userName = name.getText().toString().trim();
            String userEmail = email.getText().toString().trim();
            String userPass = password.getText().toString().trim();

            if(userName.isEmpty() || userEmail.isEmpty() || userPass.isEmpty()) {
                Toast.makeText(this, "All fields are required", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Registration successful!", Toast.LENGTH_SHORT).show();
                registerUser(userName, userEmail, userPass);
                //startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
                //finish();
            }
        });
    }

    private void registerUser(String userName, String userEmail, String userPass) {
        // URL to the PHP registration script (replace with your XAMPP URL)
        String url = "http://10.0.2.2/detracker/register.php";  // For Android Emulator use this, or replace with your IP

        // Create a new request queue
        RequestQueue requestQueue = Volley.newRequestQueue(this);

        // Create a new StringRequest (POST)
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                response -> {
                    // Handle the response from the PHP backend
                    if (response.equals("Registration Success")) {
                        Toast.makeText(this, "Registration Successful!", Toast.LENGTH_SHORT).show();
                        // Redirect to LoginActivity
                        Intent i = new Intent(RegisterActivity.this, LoginActivity.class);
                        startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
                        finish();
                    } else {
                        Toast.makeText(this, "Registration Failed", Toast.LENGTH_SHORT).show();
                    }
                },
                error -> {
                    // Handle error if request fails
                    Toast.makeText(RegisterActivity.this, "Error: " + error.toString(), Toast.LENGTH_SHORT).show();
                }) {

            @Override
            protected Map<String, String> getParams() {
                // Create a map to send POST parameters
                Map<String, String> params = new HashMap<>();
                params.put("name", userName);
                params.put("email", userEmail);
                params.put("password", userPass);
                return params;
            }
        };

        // Add the request to the request queue
        requestQueue.add(stringRequest);
    }
}
