package com.example.course_app.Activitys;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.provider.MediaStore;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.example.course_app.ClasesJava.User;
import com.example.course_app.RoomDataBase.MyViewModel;
import com.example.course_app.Utils;
import com.example.course_app.databinding.ActivitySignupBinding;

import java.io.ByteArrayOutputStream;

public class SignupActivity extends AppCompatActivity {

    private boolean isVisiblePassword = false;
    private boolean isVisiblePassword2 = false;
    private MyViewModel viewModel;
    private ActivitySignupBinding binding;
    private Bitmap profileImageBitmap;

    // Launcher لاختيار الصورة
    private final ActivityResultLauncher<Intent> imagePickerLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            result -> {
                if (result.getResultCode() == RESULT_OK && result.getData() != null) {
                    try {
                        profileImageBitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), result.getData().getData());
                        binding.profileImage.setImageBitmap(profileImageBitmap); // عرض الصورة
                    } catch (Exception e) {
                        showToast("Failed to load image");
                    }
                }
            }
    );

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySignupBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        initializeComponents();
        setupListeners();
    }

    private void initializeComponents() {
        viewModel = new ViewModelProvider(this).get(MyViewModel.class);
    }

    private void setupListeners() {
//        binding.arrowBack.setOnClickListener(view -> navigateToLogIn());
//
//        binding.signInTv.setOnClickListener(view -> finish());
//
//        binding.hidePasswordIv.setOnClickListener(view -> {
//            isVisiblePassword = Utils.showPassword(isVisiblePassword, binding.passwordEt);
//        });
//
//        binding.hide2PasswordIv.setOnClickListener(view -> {
//            isVisiblePassword2 = Utils.showPassword(isVisiblePassword2, binding.confirmPasswordEt);
//        });

        binding.signInBt.setOnClickListener(view -> handleSignUp());

        binding.uploadImageButton.setOnClickListener(view -> openImagePicker());
    }

    private void navigateToLogIn() {
        Intent intent = new Intent(SignupActivity.this, LogInActivity.class)
                .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }

    private void openImagePicker() {
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        imagePickerLauncher.launch(intent);
    }

    private void handleSignUp() {
        String fullName = binding.fullNameEt.getText().toString();
        String email = binding.emailEt.getText().toString();
        String password = binding.passwordEt.getText().toString();
        String confirmPassword = binding.confirmPasswordEt.getText().toString();

        if (isInputValid(fullName, email, password, confirmPassword)) {
            if (password.equals(confirmPassword)) {
                checkIfUserExistsAndSignUp(fullName, email, password);
            } else {
                showToast("Password and confirm password don't match");
            }
        } else {
            showToast("Enter all required fields");
        }
    }

    private boolean isInputValid(String fullName, String email, String password, String confirmPassword) {
        if (fullName.isEmpty() || email.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()) {
            return false;
        }
        if (profileImageBitmap == null) {
            showToast("Please upload a profile image");
            return false;
        }
        return true;
    }

    private void checkIfUserExistsAndSignUp(String fullName, String email, String password) {
        viewModel.getUserByEmail(email).observe(this, user -> {
            if (user == null) {
                registerNewUser(fullName, email, password);
            } else {
                showToast("User already exists!");
            }
        });
    }

    private void registerNewUser(String fullName, String email, String password) {
        // تحويل الصورة إلى byte[]
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        profileImageBitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
        byte[] imageBytes = stream.toByteArray();

        // إنشاء كائن User جديد
        User newUser = new User(fullName, email, password, imageBytes);
        viewModel.insertUser(newUser);

        showToast("Signup successfully");
        finish();
    }

    private void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}
