package com.example.course_app.MyAdapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.course_app.R;

import java.util.List;

public class ViewPagerAdapter extends RecyclerView.Adapter<ViewPagerAdapter.Pager2ViewHolder> {

    private final List<String> titlesList;
    private final List<Integer> imagesList;

    public ViewPagerAdapter(List<String> titlesList, List<Integer> imagesList) {
        this.titlesList = titlesList;
        this.imagesList = imagesList;
    }

    public static class Pager2ViewHolder extends RecyclerView.ViewHolder {
        TextView itemTitle;
        ImageView itemImage;

        public Pager2ViewHolder(View itemView) {
            super(itemView);
            itemTitle = itemView.findViewById(R.id.tvTitle);
            itemImage = itemView.findViewById(R.id.ivImage);
        }
    }

    @NonNull
    @Override
    public Pager2ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_page, parent, false);
        return new Pager2ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Pager2ViewHolder holder, int position) {
        holder.itemTitle.setText(titlesList.get(position));
        holder.itemImage.setImageResource(imagesList.get(position));
    }

    @Override
    public int getItemCount() {
        return titlesList.size();
    }
}
