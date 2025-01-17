package com.example.course_app.ClasesJava;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "EnrollCourse",
        foreignKeys = {
                @ForeignKey(
                        entity = User.class,
                        parentColumns = "user_id",
                        childColumns = "user_id",
                        onDelete = ForeignKey.CASCADE
                ),
                @ForeignKey(
                        entity = Courses.class,
                        parentColumns = "course_id",
                        childColumns = "course_id",
                        onDelete = ForeignKey.CASCADE
                )
        })
public class EnrollCourse {
    @PrimaryKey(autoGenerate = true)
    private int enrollmentId;
    private int user_id;
    private int course_id;
    private String enrollmentDate;

    public EnrollCourse(int user_id, int course_id, String enrollmentDate) {
        this.user_id = user_id;
        this.course_id = course_id;
        this.enrollmentDate = enrollmentDate;
    }

    @Ignore
    public EnrollCourse(int enrollmentId, int user_id, int course_id, String enrollmentDate) {
        this.enrollmentId = enrollmentId;
        this.user_id = user_id;
        this.course_id = course_id;
        this.enrollmentDate = enrollmentDate;
    }

    public EnrollCourse() {
    }

    public int getEnrollmentId() {
        return enrollmentId;
    }

    public void setEnrollmentId(int enrollmentId) {
        this.enrollmentId = enrollmentId;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public int getCourse_id() {
        return course_id;
    }

    public void setCourse_id(int course_id) {
        this.course_id = course_id;
    }

    public String getEnrollmentDate() {
        return enrollmentDate;
    }

    public void setEnrollmentDate(String enrollmentDate) {
        this.enrollmentDate = enrollmentDate;
    }
}