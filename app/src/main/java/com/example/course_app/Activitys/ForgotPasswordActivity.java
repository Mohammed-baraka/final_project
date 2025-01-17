package com.example.course_app.Activitys;

import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.example.course_app.RoomDataBase.MyViewModel;
import com.example.course_app.databinding.ActivityForgotPasswordBinding;

public class ForgotPasswordActivity extends AppCompatActivity {

    private MyViewModel viewModel;
    private ActivityForgotPasswordBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityForgotPasswordBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        initializeViewModel();
        setupListeners();
    }

    private void initializeViewModel() {
        viewModel = new ViewModelProvider(this).get(MyViewModel.class);
    }

    private void setupListeners() {
        binding.signInBt.setOnClickListener(view -> handlePasswordUpdate());

        binding.arrowBack.setOnClickListener(view -> finish());
    }

    private void handlePasswordUpdate() {
        String email = binding.emailEt.getText().toString();
        String newPassword = binding.passwordEt.getText().toString();

        if (isInputValid(email, newPassword)) {
            viewModel.getUserByEmail(email).observe(this, user -> {
                if (user != null) {
                    updateUserPassword(user, newPassword);
                } else {
                    showToast("Email not found!");
                }
            });
        } else {
            showToast("Fill all fields!");
        }
    }

    private boolean isInputValid(String email, String newPassword) {
        return !TextUtils.isEmpty(email) && !TextUtils.isEmpty(newPassword);
    }

    private void updateUserPassword(com.example.course_app.ClasesJava.User user, String newPassword) {
        user.setPassword(newPassword);
        viewModel.updateUser(user);
        showToast("Password updated successfully!");
        finish();
    }

    private void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}
