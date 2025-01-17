package com.example.course_app.MyAdapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.course_app.ClasesJava.Categories;
import com.example.course_app.ClasesJava.Courses;
import com.example.course_app.R;

import java.util.List;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.CategoriesViewHolder> {

    private final List<Categories> categoriesList;
    private final LayoutInflater layoutInflater;

    public CategoryAdapter(Context context, List<Categories> categories, List<Courses> coursesList) {
        this.layoutInflater = LayoutInflater.from(context);
        this.categoriesList = categories;
    }

    @NonNull
    @Override
    public CategoriesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = layoutInflater.inflate(R.layout.item_category, parent, false);
        return new CategoriesViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoriesViewHolder holder, int position) {
        Categories category = categoriesList.get(position);
        holder.categoryNameTextView.setText(category.getCategory_name());
    }

    @Override
    public int getItemCount() {
        return categoriesList.size();
    }

    static class CategoriesViewHolder extends RecyclerView.ViewHolder {
        TextView categoryNameTextView;

        public CategoriesViewHolder(@NonNull View itemView) {
            super(itemView);
            categoryNameTextView = itemView.findViewById(R.id.categoryNameTextView);
        }
    }
}
