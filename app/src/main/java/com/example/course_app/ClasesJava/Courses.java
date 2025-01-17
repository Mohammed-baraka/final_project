package com.example.course_app.ClasesJava;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Ignore;
import androidx.room.Index;
import androidx.room.PrimaryKey;

@Entity(
        tableName = "courses",
        foreignKeys = @ForeignKey(
                entity = Categories.class,
                parentColumns = "category_id",
                childColumns = "category_id",
                onDelete = ForeignKey.CASCADE
        ),
        indices = @Index("category_id")  // Add index on the foreign key column
)
public class Courses {
    @PrimaryKey(autoGenerate = true)
    private int course_id;
    private String title;
    private String description;
    private String instructor;
    private int price;
    private byte[] bitmap;
    private int category_id;

    @Ignore
    public Courses(int course_id, String title, String description, String instructor, int price, byte[] bitmap, int category_id) {
        this.course_id = course_id;
        this.title = title;
        this.description = description;
        this.instructor = instructor;
        this.price = price;
        this.bitmap = bitmap;
        this.category_id = category_id;
    }

    public Courses(String title, String description, String instructor, int price, byte[] bitmap, int category_id) {
        this.title = title;
        this.description = description;
        this.instructor = instructor;
        this.price = price;
        this.bitmap = bitmap;
        this.category_id = category_id;
    }

    public int getCourse_id() {
        return course_id;
    }

    public void setCourse_id(int course_id) {
        this.course_id = course_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getInstructor() {
        return instructor;
    }

    public void setInstructor(String instructor) {
        this.instructor = instructor;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public byte[] getBitmap() {
        return bitmap;
    }

    public void setBitmap(byte[] bitmap) {
        this.bitmap = bitmap;
    }

    public int getCategory_id() {
        return category_id;
    }

    public void setCategory_id(int category_id) {
        this.category_id = category_id;
    }
}