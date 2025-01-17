package com.example.course_app.RoomDataBase;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.example.course_app.ClasesJava.Categories;
import com.example.course_app.ClasesJava.Courses;
import com.example.course_app.ClasesJava.EnrollCourse;
import com.example.course_app.ClasesJava.Lesson;
import com.example.course_app.ClasesJava.User;
import com.example.course_app.Dao.CategoriesDao;
import com.example.course_app.Dao.CoursesDao;
import com.example.course_app.Dao.EnrollCourseDao;
import com.example.course_app.Dao.LessonDao;
import com.example.course_app.Dao.UserDao;

import java.util.List;

public class MyRepository {
    UserDao userDao;
    LessonDao lessonDao;
    CategoriesDao categoriesDao;
    CoursesDao coursesDao;
    EnrollCourseDao enrollCourseDao;

    public MyRepository(Application application) {
        OnlineDataBase db = OnlineDataBase.getDatabase(application);
        userDao = db.userDao();
        lessonDao = db.lessonDao();
        enrollCourseDao = db.enrolledCourseDao();
        categoriesDao = db.categoriesDao();
        coursesDao = db.coursesDao();
    }

    public void insertUser(User user){
        OnlineDataBase.databaseWriteExecutor.execute(() ->{
            userDao.insertUser(user);
        });
    }

    public void updateUser(User user){
        OnlineDataBase.databaseWriteExecutor.execute(() ->{
            userDao.updateUser(user);
        });
    }

    public void deleteUser(User user){ // تم إصلاح
        OnlineDataBase.databaseWriteExecutor.execute(() ->{
            userDao.deleteUser(user);
        });
    }

    public LiveData<List<User>> getAllUsers(){
        return userDao.getAllUsers();
    }

    public LiveData<User> getUserByEmailAndPassword(String email , String password){
        return userDao.getUserByEmailAndPassword(email,password);
    }

    public LiveData<User> getUserById(int userId){
        return userDao.getUserById(userId);
    }

    public LiveData<User> getUserByEmail(String email){
        return userDao.getUserByEmail(email);
    }

    //-----------------------------------------------------------------

    public void insertLesson(Lesson lesson){
        OnlineDataBase.databaseWriteExecutor.execute(() ->{
            lessonDao.insertLesson(lesson);
        });
    }

    public void updateLesson(Lesson lesson){
        OnlineDataBase.databaseWriteExecutor.execute(() ->{
            lessonDao.updateLesson(lesson);
        });
    }

    public void deleteLesson(Lesson lesson){
        OnlineDataBase.databaseWriteExecutor.execute(() ->{
            lessonDao.deleteLesson(lesson);
        });
    }

    public LiveData<List<Lesson>> getAllLessons(){
        return lessonDao.getAllLesson();
    }

    //********************************************************

    public void insertCourse(Courses courses){
        OnlineDataBase.databaseWriteExecutor.execute(() ->{
            coursesDao.insertCourse(courses);
        });
    }

    public void updateCourse(Courses courses){
        OnlineDataBase.databaseWriteExecutor.execute(() ->{
            coursesDao.updateCourse(courses);
        });
    }

    public void deleteCourse(Courses courses){
        OnlineDataBase.databaseWriteExecutor.execute(() ->{
            coursesDao.deleteCourse(courses);
        });
    }

    public Courses getCourseById(int courseId){
        return coursesDao.getCourseById(courseId);
    }

    public LiveData<List<Courses>> getAllCourses(){
        return coursesDao.getAllCourses();
    }

    //******************************************************************************

    public void insertCategory(Categories categories){
        OnlineDataBase.databaseWriteExecutor.execute(() ->{
            categoriesDao.insertCategory(categories);
        });
    }

    public void updateCategory(Categories categories){
        OnlineDataBase.databaseWriteExecutor.execute(() ->{
            categoriesDao.updateCategory(categories);
        });
    }

    public void deleteCategory(Categories categories){
        OnlineDataBase.databaseWriteExecutor.execute(() ->{
            categoriesDao.deleteCategory(categories);
        });
    }

    public Categories getCategoryById(int categoryId){
        return categoriesDao.getCategoryById(categoryId);
    }

    public LiveData<List<Categories>> getAllCategories(){
        return categoriesDao.getAllCategories();
    }

    public LiveData<List<Courses>> getCoursesByCategoryId(int categoryId){
        return categoriesDao.getCoursesByCategoryId(categoryId);
    }

    //****************************************************************

    public void insertEnrolledCourse(EnrollCourse enrollCourse){
        OnlineDataBase.databaseWriteExecutor.execute(() ->{
            enrollCourseDao.insertEnrolledCourse(enrollCourse);
        });
    }


    public void updateEnrolledCourse(EnrollCourse enrollCourse){
        OnlineDataBase.databaseWriteExecutor.execute(() ->{
            enrollCourseDao.updateEnrolledCourse(enrollCourse);
        });
    }


    public void deleteEnrolledCourse(EnrollCourse enrollCourse){
        OnlineDataBase.databaseWriteExecutor.execute(() ->{
            enrollCourseDao.deleteEnrolledCourse(enrollCourse);
        });
    }


    public LiveData<List<EnrollCourse>> getAllEnrolledCourse(){
        return enrollCourseDao.getAllEnrolledCourse();
    }
}
