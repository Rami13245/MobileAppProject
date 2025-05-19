package com.example.testingg;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;
import java.util.HashMap;

public class AddMealsActivity extends AppCompatActivity {

    private ListView mealsListView;
    private Button setMealsButton;
    private ArrayList<String> selectedMeals = new ArrayList<>();
    private HashMap<String, Integer> mealCalories = new HashMap<>();
    private String[] availableMeals = {"Chicken Salad", "Grilled Fish", "Vegan Bowl", "Fruit Smoothie", "Protein Shake"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_meals);

        mealsListView = findViewById(R.id.list_meals);
        setMealsButton = findViewById(R.id.btn_set_meals);

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_multiple_choice, availableMeals);
        mealsListView.setAdapter(adapter);
        mealsListView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);

        mealCalories.put("Chicken Salad", 350);
        mealCalories.put("Grilled Fish", 400);
        mealCalories.put("Vegan Bowl", 450);
        mealCalories.put("Fruit Smoothie", 200);
        mealCalories.put("Protein Shake", 250);

        setMealsButton.setOnClickListener(v -> {
            selectedMeals.clear();
            int totalCalories = 0;

            for (int i = 0; i < mealsListView.getCount(); i++) {
                if (mealsListView.isItemChecked(i)) {
                    String meal = availableMeals[i];
                    selectedMeals.add(meal);
                    totalCalories += mealCalories.get(meal);
                }
            }

            int recommendedCalories = new SessionManager(this).getBMR();
            String result = "Selected: " + selectedMeals.size() + " meals (" + totalCalories + " kcal)\n" +
                    "Recommended: " + recommendedCalories + " kcal";

            Toast.makeText(this, result, Toast.LENGTH_LONG).show();
            Intent i = new Intent(AddMealsActivity.this, MainActivity.class);
        });
    }
}
