package com.example.course_app.Fragment;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.course_app.ClasesJava.Categories;
import com.example.course_app.ClasesJava.Courses;
import com.example.course_app.RoomDataBase.OnlineDataBase;
import com.example.course_app.databinding.FragmentAddCourseAdminBinding;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class AddCourseAdminFragment extends Fragment {

    private FragmentAddCourseAdminBinding binding;
    private Bitmap selectedImageBitmap;
    private static final int PICK_IMAGE_REQUEST = 1;
    private List<Categories> categoryList = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentAddCourseAdminBinding.inflate(inflater, container, false);
        initializeListeners();
        loadCategories();
        return binding.getRoot();
    }

    private void initializeListeners() {
        binding.selectImageButton.setOnClickListener(v -> openImageChooser());
        binding.saveCourseButton.setOnClickListener(v -> handleSaveCourse());
    }

    private void loadCategories() {
        OnlineDataBase.getDatabase(requireContext()).categoriesDao().getAllCategories().observe(getViewLifecycleOwner(), categories -> {
            categoryList = categories != null ? categories : new ArrayList<>();
            updateCategorySpinner();
        });
    }


    private void updateCategorySpinner() {
        List<String> categoryNames = categoryList.stream()
                .map(Categories::getCategory_name)
                .collect(Collectors.toList());

        ArrayAdapter<String> adapter = new ArrayAdapter<>(requireContext(),
                android.R.layout.simple_spinner_item, categoryNames);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        binding.categorySpinner.setAdapter(adapter);
    }


    private void openImageChooser() {
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent, PICK_IMAGE_REQUEST);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_IMAGE_REQUEST && resultCode == Activity.RESULT_OK && data != null) {
            Uri imageUri = data.getData();
            try {
                selectedImageBitmap = MediaStore.Images.Media.getBitmap(requireActivity().getContentResolver(), imageUri);
                binding.courseImage.setImageBitmap(selectedImageBitmap);
            } catch (IOException e) {
                showToast("Failed to load image");
            }
        }
    }

    private void handleSaveCourse() {
        String title = binding.courseTitle.getText().toString().trim();
        String description = binding.courseDescription.getText().toString().trim();
        String instructor = binding.courseInstructor.getText().toString().trim();
        String priceText = binding.coursePrice.getText().toString().trim();

        if (validateInputs(title, description, instructor, priceText)) {
            saveCourseToDatabase(title, description, instructor, priceText);
        }
    }

    private boolean validateInputs(String title, String description, String instructor, String priceText) {
        if (TextUtils.isEmpty(title) || TextUtils.isEmpty(description) || TextUtils.isEmpty(instructor) || TextUtils.isEmpty(priceText)) {
            showToast("Please fill in all fields");
            return false;
        }
        if (selectedImageBitmap == null) {
            showToast("Please upload an image");
            return false;
        }
        if (!TextUtils.isDigitsOnly(priceText)) {
            showToast("Price must be a number");
            return false;
        }
        return true;
    }

    private void saveCourseToDatabase(String title, String description, String instructor, String priceText) {
        int categoryId = categoryList.get(binding.categorySpinner.getSelectedItemPosition()).getCategory_id();
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        selectedImageBitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
        byte[] imageByteArray = stream.toByteArray();

        Courses course = new Courses(title, description, instructor, Integer.parseInt(priceText), imageByteArray, categoryId);

        OnlineDataBase.databaseWriteExecutor.execute(() -> {
            OnlineDataBase.getDatabase(requireContext()).coursesDao().insertCourse(course);
            requireActivity().runOnUiThread(() -> {
                showToast("Course saved successfully");
                resetFields();
            });
        });
    }

    private void resetFields() {
        binding.courseTitle.setText("");
        binding.courseDescription.setText("");
        binding.courseInstructor.setText("");
        binding.coursePrice.setText("");
        binding.courseImage.setImageResource(android.R.color.darker_gray);
        selectedImageBitmap = null;
    }

    private void showToast(String message) {
        Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
