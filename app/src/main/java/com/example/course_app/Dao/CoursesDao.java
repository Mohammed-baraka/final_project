package com.example.course_app.Dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.course_app.ClasesJava.Courses;

import java.util.List;

@Dao
public interface CoursesDao {

    @Insert
    void insertCourse(Courses courses);

    @Update
    void updateCourse(Courses courses);

    @Delete
    void deleteCourse(Courses courses);

    @Query("SELECT * FROM Courses WHERE course_id = :courseId")
    Courses getCourseById(int courseId);

    @Query("SELECT * FROM Courses")
    LiveData<List<Courses>> getAllCourses();

    @Query("SELECT * FROM courses WHERE category_id = :categoryId")
    List<Courses> getCoursesByCategory(int categoryId);
}
