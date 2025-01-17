    package com.example.course_app.RoomDataBase;

    import android.app.Application;

    import androidx.annotation.NonNull;
    import androidx.lifecycle.AndroidViewModel;
    import androidx.lifecycle.LiveData;

    import com.example.course_app.ClasesJava.Categories;
    import com.example.course_app.ClasesJava.Courses;
    import com.example.course_app.ClasesJava.EnrollCourse;
    import com.example.course_app.ClasesJava.Lesson;
    import com.example.course_app.ClasesJava.User;

    import java.util.List;

    public class MyViewModel extends AndroidViewModel {

        MyRepository repository;

        public MyViewModel(@NonNull Application application) {
            super(application);
            repository = new MyRepository(application);
        }

        public void insertUser(User user) {
            repository.insertUser(user);
        }

        public void updateUser(User user) {
            repository.updateUser(user);
        }

        public void deleteUser(User user) {
            repository.deleteUser(user);
        }

        public LiveData<List<User>> getAllUsers() {
            return repository.getAllUsers();
        }

        public LiveData<User> getUserByEmailAndPassword(String email, String password) {
            return repository.getUserByEmailAndPassword(email, password);
        }

        public LiveData<User> getUserById(int userId) {
            return repository.getUserById(userId);
        }

        public LiveData<User> getUserByEmail(String email){
            return repository.getUserByEmail(email);
        }

        //********************************************************************

        public void insertLesson(Lesson lesson){
            repository.insertLesson(lesson);
        }

        public void updateLesson(Lesson lesson){
            repository.updateLesson(lesson);
        }

        public void deleteLesson(Lesson lesson){
            repository.deleteLesson(lesson);
        }

        public LiveData<List<Lesson>> getAllLessons(){
            return repository.getAllLessons();
        }

        //*******************************************************************

        public void insertCourse(Courses courses){
            repository.insertCourse(courses);
        }

        public void updateCourse(Courses courses){
            repository.updateCourse(courses);
        }

        public void deleteCourse(Courses courses){
            repository.deleteCourse(courses);
        }

        public LiveData<List<Courses>> getAllCourses(){
            return repository.getAllCourses();
        }

        //*******************************************************************

        public void insertCategory(Categories categories){
            repository.insertCategory(categories);
        }

        public void updateCategory(Categories categories){
            repository.updateCategory(categories);
        }

        public void deleteCategory(Categories categories){
            repository.deleteCategory(categories);
        }

        public LiveData<List<Categories>> getAllCategories(){
            return repository.getAllCategories();
        }

        public LiveData<List<Courses>> getCoursesByCategoryId(int categoryId){
            return repository.getCoursesByCategoryId(categoryId);
        }

        //*********************************************************

        public void insertEnrolledCourse(EnrollCourse enrollCourse){
            repository.insertEnrolledCourse(enrollCourse);
        }

        public void updateEnrolledCourse(EnrollCourse enrollCourse){
            repository.updateEnrolledCourse(enrollCourse);
        }

        public void deleteEnrolledCourse(EnrollCourse enrollCourse){
            repository.deleteEnrolledCourse(enrollCourse);
        }

        public LiveData<List<EnrollCourse>> getAllEnrolledCourse(){
            return repository.getAllEnrolledCourse();
        }



    }
