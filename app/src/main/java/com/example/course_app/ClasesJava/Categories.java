package com.example.course_app.ClasesJava;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "Categories")
public class Categories {
    @PrimaryKey(autoGenerate = true)
    private int category_id;
    private String category_name;
    private String description;


    public Categories(String category_name, String description) {
        this.category_name = category_name;
        this.description = description;
    }


    public int getCategory_id() {
        return category_id;
    }

    public void setCategory_id(int category_id) {
        this.category_id = category_id;
    }

    public String getCategory_name() {
        return category_name;
    }

    public void setCategory_name(String category_name) {
        this.category_name = category_name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
