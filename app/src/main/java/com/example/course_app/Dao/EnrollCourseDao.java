package com.example.course_app.Dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.course_app.ClasesJava.EnrollCourse;

import java.util.List;

@Dao
public interface EnrollCourseDao {

    @Insert
    void insertEnrolledCourse(EnrollCourse enrollCourse);

    @Update
    void updateEnrolledCourse(EnrollCourse enrollCourse);

    @Delete
    void deleteEnrolledCourse(EnrollCourse enrollCourse);

    @Query("SELECT * FROM EnrollCourse")
    LiveData<List<EnrollCourse>> getAllEnrolledCourse();
}