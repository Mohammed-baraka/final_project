package com.example.course_app.ClasesJava;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "Lesson",
        foreignKeys = @ForeignKey(
                entity = Courses.class,
                parentColumns = "course_id",
                childColumns = "courseId",
                onDelete = ForeignKey.CASCADE
        ))
public class Lesson {
    @PrimaryKey(autoGenerate = true)
    private int lessonId;
    private String lessonName;
    private String lessonDescription;
    private String videoPath;
    private int courseId;

    @Ignore
    public Lesson(int lessonId, String lessonName, String lessonDescription, String videoPath, int courseId) {
        this.lessonId = lessonId;
        this.lessonName = lessonName;
        this.lessonDescription = lessonDescription;
        this.videoPath = videoPath;
        this.courseId = courseId;
    }

    public Lesson(String lessonName, String lessonDescription, String videoPath, int courseId) {
        this.lessonName = lessonName;
        this.lessonDescription = lessonDescription;
        this.videoPath = videoPath;
        this.courseId = courseId;
    }

    public int getLessonId() {
        return lessonId;
    }

    public void setLessonId(int lessonId) {
        this.lessonId = lessonId;
    }

    public String getLessonName() {
        return lessonName;
    }

    public void setLessonName(String lessonName) {
        this.lessonName = lessonName;
    }

    public String getLessonDescription() {
        return lessonDescription;
    }

    public void setLessonDescription(String lessonDescription) {
        this.lessonDescription = lessonDescription;
    }

    public String getVideoPath() {
        return videoPath;
    }

    public void setVideoPath(String videoPath) {
        this.videoPath = videoPath;
    }

    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }
}
