    package com.example.course_app.Dao;

    import androidx.lifecycle.LiveData;
    import androidx.room.Dao;
    import androidx.room.Delete;
    import androidx.room.Insert;
    import androidx.room.Query;
    import androidx.room.Update;

    import com.example.course_app.ClasesJava.Lesson;

    import java.util.List;

    @Dao
    public interface LessonDao {
        @Insert
        void insertLesson(Lesson lesson);

        @Update
        void updateLesson(Lesson lesson);

        @Delete
        void deleteLesson(Lesson lesson);


        @Query("SELECT * FROM Lesson")
        LiveData<List<Lesson>> getAllLesson();
    }
