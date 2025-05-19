package com.example.testingg;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;

public class ProfileActivity extends AppCompatActivity {

    private ImageView profileImage;
    private Spinner genderSpinner;
    private SeekBar heightSeekBar, weightSeekBar;
    private TextView heightTextView, weightTextView, profileNameTextView;
    private Button saveProfileButton, editProfileButton;
    private int selectedHeight = 0, selectedWeight = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        profileImage = findViewById(R.id.profile_image);
        genderSpinner = findViewById(R.id.spinner_age);
        heightSeekBar = findViewById(R.id.seekbar_height);
        weightSeekBar = findViewById(R.id.seekbar_weight);
        heightTextView = findViewById(R.id.tv_selected_height);
        weightTextView = findViewById(R.id.tv_selected_weight);
        saveProfileButton = findViewById(R.id.btn_save_profile);
        editProfileButton = findViewById(R.id.btn_edit_profile);
        profileNameTextView = findViewById(R.id.tv_profile_name);

        SessionManager sessionManager = new SessionManager(getApplicationContext());
        profileNameTextView.setText(sessionManager.getUserEmail());

        genderSpinner.setEnabled(false);
        heightSeekBar.setEnabled(false);
        weightSeekBar.setEnabled(false);

        ArrayList<String> genderOptions = new ArrayList<>();
        genderOptions.add("Select Gender");
        genderOptions.add("Male");
        genderOptions.add("Female");

        ArrayAdapter<String> genderAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, genderOptions);
        genderAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        genderSpinner.setAdapter(genderAdapter);
        genderSpinner.setSelection(0);

        heightSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                selectedHeight = progress;
                heightTextView.setText("Height: " + progress + " cm");
            }
            public void onStartTrackingTouch(SeekBar seekBar) {}
            public void onStopTrackingTouch(SeekBar seekBar) {}
        });

        weightSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                selectedWeight = progress;
                weightTextView.setText("Weight: " + progress + " kg");
            }
            public void onStartTrackingTouch(SeekBar seekBar) {}
            public void onStopTrackingTouch(SeekBar seekBar) {}
        });

        editProfileButton.setOnClickListener(v -> {
            genderSpinner.setEnabled(true);
            heightSeekBar.setEnabled(true);
            weightSeekBar.setEnabled(true);
            Toast.makeText(this, "Fields are now editable", Toast.LENGTH_SHORT).show();
        });

        saveProfileButton.setOnClickListener(v -> {
            String gender = genderSpinner.getSelectedItem().toString();
            if (gender.equals("Select Gender")) {
                Toast.makeText(this, "Please select a gender", Toast.LENGTH_SHORT).show();
                return;
            }

            int bmr = calculateBMR(gender, selectedHeight, selectedWeight);
            sessionManager.saveProfile(sessionManager.getUserEmail(), gender, selectedHeight, selectedWeight, bmr);

            Toast.makeText(this, "Profile Saved! Estimated Calories: " + bmr + " kcal/day", Toast.LENGTH_LONG).show();
            Intent i = new Intent(ProfileActivity.this, MainActivity.class);
        });
    }

    private int calculateBMR(String gender, int height, int weight) {
        int age = 25; // Static age for now (can be made dynamic)
        double bmr;

        if (gender.equalsIgnoreCase("Male")) {
            bmr = 10 * weight + 6.25 * height - 5 * age + 5;
        } else {
            bmr = 10 * weight + 6.25 * height - 5 * age - 161;
        }
        return (int) (bmr * 1.2); // Sedentary activity multiplier
    }
}
