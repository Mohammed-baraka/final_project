package com.example.course_app.Dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.course_app.ClasesJava.Categories;
import com.example.course_app.ClasesJava.Courses;
import java.util.List;

@Dao
public interface CategoriesDao {

    @Insert
    void insertCategory(Categories category);

    @Update
    void updateCategory(Categories categories);

    @Delete
    void deleteCategory(Categories categories);

    @Query("SELECT * FROM Categories WHERE category_id = :categoryId")
    Categories getCategoryById(int categoryId);

    @Query("SELECT * FROM Categories")
    LiveData<List<Categories>> getAllCategories();

    @Query("SELECT * FROM Courses WHERE category_id = :categoryId")
    LiveData<List<Courses>> getCoursesByCategoryId(int categoryId);
}
