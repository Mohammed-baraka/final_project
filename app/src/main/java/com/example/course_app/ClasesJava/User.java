package com.example.course_app.ClasesJava;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class User {
    @PrimaryKey(autoGenerate = true)
    private  int user_id;
    private String fullName;
    private String email;
    private String password;
    private byte[] profileImage;


    public User(String fullName, String email, String password, byte[] profileImage) {
        this.fullName = fullName;
        this.email = email;
        this.password = password;
        this.profileImage = profileImage;
    }


    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    public byte[] getProfileImage() { // تأكد من وجود هذه الدالة
        return profileImage;
    }

    public void setProfileImage(byte[] profileImage) {
        this.profileImage = profileImage;
    }
}
