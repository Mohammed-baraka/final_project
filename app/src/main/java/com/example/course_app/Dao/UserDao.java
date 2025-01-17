    package com.example.course_app.Dao;


    import androidx.lifecycle.LiveData;
    import androidx.room.Dao;
    import androidx.room.Delete;
    import androidx.room.Insert;
    import androidx.room.Query;
    import androidx.room.Update;

    import com.example.course_app.ClasesJava.User;

    import java.util.List;

    @Dao
    public interface UserDao {
        @Insert
        void insertUser(User user);

        @Update
        void updateUser(User user);

        @Delete
        void deleteUser(User user);

        @Query("SELECT * FROM user")
        LiveData<List<User>> getAllUsers();

        @Query("SELECT * FROM user WHERE email = :email And password = :password")
        LiveData<User> getUserByEmailAndPassword(String email, String password);

        @Query("SELECT * FROM user WHERE email =:email")
        LiveData<User> getUserByEmail(String email);

        @Query("SELECT * FROM user WHERE user_id =:userId")
        LiveData<User> getUserById(int userId);

    }
