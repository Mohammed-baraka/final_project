package com.example.course_app.RoomDataBase;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.migration.Migration;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.example.course_app.ClasesJava.Categories;
import com.example.course_app.Dao.CategoriesDao;
import com.example.course_app.ClasesJava.Courses;
import com.example.course_app.Dao.CoursesDao;
import com.example.course_app.ClasesJava.EnrollCourse;
import com.example.course_app.Dao.EnrollCourseDao;
import com.example.course_app.ClasesJava.Lesson;
import com.example.course_app.Dao.LessonDao;
import com.example.course_app.ClasesJava.User;
import com.example.course_app.Dao.UserDao;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {User.class, Categories.class, Courses.class, Lesson.class, EnrollCourse.class},
        version = 2,
        exportSchema = false)
public abstract class OnlineDataBase extends RoomDatabase {

    public abstract UserDao userDao();
    public abstract CategoriesDao categoriesDao();
    public abstract CoursesDao coursesDao();
    public abstract LessonDao lessonDao();
    public abstract EnrollCourseDao enrolledCourseDao();

    private static volatile OnlineDataBase INSTANCE;
    private static final int NUMBER_OF_THREADS = 4;

    public static final ExecutorService databaseWriteExecutor =
            Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    // تعريف Migration من الإصدار 1 إلى الإصدار 2
    static final Migration MIGRATION_1_2 = new Migration(1, 2) {
        @Override
        public void migrate(@NonNull SupportSQLiteDatabase database) {
            // إضافة عمود جديد إلى جدول User
            database.execSQL("ALTER TABLE user ADD COLUMN profileImage BLOB");
        }
    };

    public static OnlineDataBase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (OnlineDataBase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                                    OnlineDataBase.class, "online_database")
                            // إضافة Migration إلى البناء
                            .addMigrations(MIGRATION_1_2)
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}
