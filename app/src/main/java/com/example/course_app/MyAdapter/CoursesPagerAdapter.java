package com.example.course_app.MyAdapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.course_app.ClasesJava.Categories;
import com.example.course_app.ClasesJava.Courses;
import com.example.course_app.Fragment.CoursesFragment;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class CoursesPagerAdapter extends FragmentStateAdapter {
    private final List<Categories> categoriesList;
    private final List<Courses> coursesList;

    public CoursesPagerAdapter(@NonNull FragmentActivity fragmentActivity,
                               List<Categories> categoriesList,
                               List<Courses> coursesList) {
        super(fragmentActivity);
        this.categoriesList = categoriesList;
        this.coursesList = coursesList;
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        // الحصول على الفئة المطلوبة
        Categories category = categoriesList.get(position);
        int categoryId = category.getCategory_id();

        // تصفية الدورات بناءً على categoryId
        List<Courses> filteredCourses = coursesList.stream()
                .filter(course -> course.getCategory_id() == categoryId)
                .collect(Collectors.toList());

        // إنشاء CoursesFragment وتمرير قائمة الدورات ذات الصلة
        return CoursesFragment.newInstance(categoryId, new ArrayList<>(filteredCourses));
    }

    @Override
    public int getItemCount() {
        return categoriesList.size();
    }
}
