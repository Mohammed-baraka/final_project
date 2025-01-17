package com.example.course_app.Activitys;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.viewpager2.widget.ViewPager2;

import com.example.course_app.Fragment.ProfileFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;
import com.example.course_app.MyAdapter.CoursesAdapter;
import com.example.course_app.MyAdapter.CoursesPagerAdapter;
import com.example.course_app.ClasesJava.Categories;
import com.example.course_app.ClasesJava.Courses;
import com.example.course_app.Fragment.BottomFragment;
import com.example.course_app.Fragment.CourseDetailsFragment;
import com.example.course_app.R;
import com.example.course_app.RoomDataBase.MyViewModel;
import com.example.course_app.databinding.ActivityHomeScreenBinding;

import java.util.List;

public class HomeScreenActivity extends AppCompatActivity {

    private ActivityHomeScreenBinding binding;
    private MyViewModel myViewModel;
    private CoursesPagerAdapter pagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityHomeScreenBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        initializeComponents();
    }

    private void initializeComponents() {
        myViewModel = new ViewModelProvider(this).get(MyViewModel.class);

        setupViewPagerAndTabs();

        setupBottomNavigation();
    }

    private void setupViewPagerAndTabs() {
        myViewModel.getAllCategories().observe(this, categories -> {
            if (categories != null && !categories.isEmpty()) {
                myViewModel.getAllCourses().observe(this, courses -> {
                    if (courses != null) {
                        setupPagerAdapter(categories, courses);
                        setupTabLayout(categories);
                    }
                });
            }
        });
    }

    private void setupPagerAdapter(List<Categories> categories, List<Courses> courses) {
        pagerAdapter = new CoursesPagerAdapter(this, categories, courses);
        binding.viewPager.setAdapter(pagerAdapter);

        CoursesAdapter adapter = new CoursesAdapter();
        adapter.setOnCourseClickListener(this::navigateToCourseDetails);
    }


    private void setupTabLayout(List<Categories> categories) {
        new TabLayoutMediator(binding.tabLayout, binding.viewPager, (tab, position) -> {
            tab.setText(categories.get(position).getCategory_name());
        }).attach();
    }

    private void setupBottomNavigation() {
        binding.BottomNavigation.setOnItemSelectedListener(this::handleBottomNavigation);
    }

    private boolean handleBottomNavigation(@NonNull MenuItem item) {
        int itemId = item.getItemId();

        if (itemId == R.id.home) {
            showHomeScreen();
            return true;
        } else if (itemId == R.id.profile) {
            navigateToProfileFragment();
            return true;
        } else if (itemId == R.id.myCourse) {
            navigateToFragment("MyCourse");
            return true;
        }

        return false;
    }



    private void showHomeScreen() {
        binding.viewPager.setVisibility(View.VISIBLE);
        binding.tabLayout.setVisibility(View.VISIBLE);
        binding.FragmentsContainer.setVisibility(View.GONE);
    }


    private void navigateToFragment(String sectionName) {
        binding.viewPager.setVisibility(View.GONE);
        binding.tabLayout.setVisibility(View.GONE);
        binding.FragmentsContainer.setVisibility(View.VISIBLE);

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.FragmentsContainer, BottomFragment.newInstance(sectionName))
                .commit();
    }

    private void navigateToCourseDetails(Courses course) {
        Bitmap bitmap = BitmapFactory.decodeByteArray(course.getBitmap(), 0, course.getBitmap().length);

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.FragmentsContainer, CourseDetailsFragment.newInstance(
                        course.getTitle(),
                        course.getDescription(),
                        course.getInstructor(),
                        String.valueOf(course.getPrice()),
                        course.getBitmap()
                ))
                .addToBackStack(null)
                .commit();

    }

    private void navigateToProfileFragment() {
        binding.viewPager.setVisibility(View.GONE);
        binding.tabLayout.setVisibility(View.GONE);
        binding.FragmentsContainer.setVisibility(View.VISIBLE);

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.FragmentsContainer, ProfileFragment.newInstance("Profile"))
                .commit();
    }


}
