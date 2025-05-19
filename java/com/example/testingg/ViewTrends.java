package com.example.testingg;

import android.os.Bundle;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class ViewTrends extends AppCompatActivity {

    private RecyclerView recyclerView;
    private Button closeButton;
    private ArrayList<TrendItem> trendHistory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_trends);

        recyclerView = findViewById(R.id.rv_trends);
        closeButton = findViewById(R.id.close_button);


        // Simulated calorie history (you can later make it dynamic)
        trendHistory = new ArrayList<>();
        trendHistory.add(new TrendItem("2025-05-02", 1980));
        trendHistory.add(new TrendItem("2025-04-29", 2100));
        trendHistory.add(new TrendItem(today(), 1650));

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new TrendAdapter(trendHistory));

        closeButton.setOnClickListener(v -> finish());
    }

    private String today() {
        return new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(new Date());
    }
}
