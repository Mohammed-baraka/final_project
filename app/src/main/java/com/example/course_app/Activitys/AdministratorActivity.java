package com.example.course_app.Activitys;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.example.course_app.Fragment.AddCategoriesAdminFragment;
import com.example.course_app.Fragment.AddCourseAdminFragment;
import com.example.course_app.R;

public class AdministratorActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_administrator);

        if (savedInstanceState == null) {
            replaceFragment(new AddCourseAdminFragment());
        }

        findViewById(R.id.FragmentsCategories).setOnClickListener(v -> {
            replaceFragment(new AddCategoriesAdminFragment());
        });

        findViewById(R.id.FragmentsCourse).setOnClickListener(v -> {
            replaceFragment(new AddCourseAdminFragment());
        });
    }

    private void replaceFragment(Fragment fragment) {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.FragmentsContainer, fragment)
                .commit();
    }
}