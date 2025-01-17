package com.example.course_app.Activitys;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.example.course_app.RoomDataBase.MyViewModel;
import com.example.course_app.Utils;
import com.example.course_app.databinding.ActivityLogInBinding;

public class LogInActivity extends AppCompatActivity {

    private ActivityLogInBinding binding;
    private MyViewModel viewModel;
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;
    private boolean isPasswordVisible = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLogInBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        initializeComponents();
        checkRememberedUser();
        setupListeners();
    }

    private void initializeComponents() {
        sharedPreferences = getSharedPreferences("Events_Preferences", MODE_PRIVATE);
        editor = sharedPreferences.edit();
        viewModel = new ViewModelProvider(this).get(MyViewModel.class);
    }


    private void checkRememberedUser() {
        if (sharedPreferences.getBoolean("remembered", false)) {
            int userId = sharedPreferences.getInt("user_Id", -1);
            navigateToHomeScreen(userId);
        }
    }

    private void setupListeners() {
        binding.signUpTv.setOnClickListener(view -> navigateToSignupScreen());
        binding.hidePasswordIv.setOnClickListener(view -> togglePasswordVisibility());
        binding.forgetPasswordTv.setOnClickListener(view -> navigateToForgotPasswordScreen());
        binding.signInBt.setOnClickListener(view -> handleSignIn());
    }

    private void navigateToSignupScreen() {
        Intent intent = new Intent(LogInActivity.this, SignupActivity.class);
        startActivity(intent);
    }


    private void togglePasswordVisibility() {
        isPasswordVisible = Utils.showPassword(isPasswordVisible, binding.passwordEt);
    }


    private void navigateToForgotPasswordScreen() {
        Intent intent = new Intent(LogInActivity.this, ForgotPasswordActivity.class);
        startActivity(intent);
    }


    private void handleSignIn() {
        String email = binding.emailEt.getText().toString().trim();
        String password = binding.passwordEt.getText().toString().trim();

        if (isInputValid(email, password)) {
            if (isAdmin(email, password)) {
                navigateToAdminScreen();
            } else {
                authenticateUser(email, password);
            }
        } else {
            Toast.makeText(this, "Enter email and password", Toast.LENGTH_SHORT).show();
        }
    }

    private boolean isInputValid(String email, String password) {
        return !email.isEmpty() && !password.isEmpty();
    }

    private boolean isAdmin(String email, String password) {
        return email.equals("admin@gmail.com") && password.equals("admin");
    }


    private void navigateToAdminScreen() {
        Intent intent = new Intent(LogInActivity.this, AdministratorActivity.class);
        startActivity(intent);
    }

    private void authenticateUser(String email, String password) {
        viewModel.getUserByEmailAndPassword(email, password).observe(this, user -> {
            if (user != null) {
                saveUserIfRemembered(user);
                navigateToHomeScreen(user.getUser_id());
            } else {
                Toast.makeText(this, "Check Email or Password", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void saveUserIfRemembered(com.example.course_app.ClasesJava.User user) {
        if (binding.rememberMeSwitch.isChecked()) {
            editor.putBoolean("remembered", true).apply();
            editor.putInt("user_Id", user.getUser_id()).apply();
        }
    }

    private void navigateToHomeScreen(int userId) {
        Intent intent = new Intent(LogInActivity.this, HomeScreenActivity.class);
        intent.putExtra("user_Id", userId);
        startActivity(intent);
    }
}
