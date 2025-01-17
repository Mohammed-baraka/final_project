package com.example.course_app.Fragment;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.course_app.ClasesJava.Categories;
import com.example.course_app.RoomDataBase.OnlineDataBase;
import com.example.course_app.databinding.FragmentAddCategoriesAdminBinding;

public class AddCategoriesAdminFragment extends Fragment {

    private FragmentAddCategoriesAdminBinding binding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentAddCategoriesAdminBinding.inflate(inflater, container, false);

        initializeListeners();
        return binding.getRoot();
    }

    private void initializeListeners() {
        binding.saveCategoryButton.setOnClickListener(v -> handleSaveCategory());
    }

    private void handleSaveCategory() {
        String categoryName = binding.categoryName.getText().toString().trim();
        String description = binding.categoryDescription.getText().toString().trim();

        if (isInputValid(categoryName, description)) {
            saveCategoryToDatabase(new Categories(categoryName, description));
        } else {
            showToast("Please fill in all fields");
        }
    }

    private boolean isInputValid(String categoryName, String description) {
        return !TextUtils.isEmpty(categoryName) && !TextUtils.isEmpty(description);
    }

    private void saveCategoryToDatabase(Categories category) {
        OnlineDataBase.databaseWriteExecutor.execute(() -> {
            try {
                OnlineDataBase.getDatabase(requireContext()).categoriesDao().insertCategory(category);
                requireActivity().runOnUiThread(() -> {
                    showToast("Category added successfully");
                    resetInputFields();
                });
            } catch (Exception e) {
                requireActivity().runOnUiThread(() -> showToast("Failed to add category"));
                e.printStackTrace();
            }
        });
    }

    private void resetInputFields() {
        binding.categoryName.setText("");
        binding.categoryDescription.setText("");
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
