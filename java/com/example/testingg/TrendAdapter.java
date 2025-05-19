package com.example.testingg;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class TrendAdapter extends RecyclerView.Adapter<TrendAdapter.TrendViewHolder> {

    private List<TrendItem> trendList;

    public TrendAdapter(List<TrendItem> trendList) {
        this.trendList = trendList;
    }

    @NonNull
    @Override
    public TrendViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_trend_item, parent, false);
        return new TrendViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TrendViewHolder holder, int position) {
        TrendItem item = trendList.get(position);
        holder.dateText.setText(item.getDate());
        holder.caloriesText.setText(item.getTotalCalories() + " kcal");
    }

    @Override
    public int getItemCount() {
        return trendList.size();
    }

    public static class TrendViewHolder extends RecyclerView.ViewHolder {
        TextView dateText, caloriesText;

        public TrendViewHolder(@NonNull View itemView) {
            super(itemView);
            dateText = itemView.findViewById(R.id.trend_date);
            caloriesText = itemView.findViewById(R.id.trend_calories);
        }
    }
}
