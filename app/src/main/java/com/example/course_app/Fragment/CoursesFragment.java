package com.example.course_app.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.course_app.MyAdapter.CoursesAdapter;
import com.example.course_app.ClasesJava.Courses;
import com.example.course_app.R;
import com.example.course_app.RoomDataBase.MyViewModel;

import java.util.ArrayList;

public class CoursesFragment extends Fragment {

    private static final String ARG_CATEGORY_ID = "categoryId";
    private int categoryId;
    private MyViewModel coursesViewModel;

    public static CoursesFragment newInstance(int categoryId, ArrayList<Courses> coursesList) {
        CoursesFragment fragment = new CoursesFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_CATEGORY_ID, categoryId);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            categoryId = getArguments().getInt(ARG_CATEGORY_ID);
        }
        coursesViewModel = new ViewModelProvider(this).get(MyViewModel.class);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_courses, container, false);
        RecyclerView recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        CoursesAdapter adapter = new CoursesAdapter();
        recyclerView.setAdapter(adapter);

        coursesViewModel.getCoursesByCategoryId(categoryId).observe(getViewLifecycleOwner(), courses -> {
            if (courses != null) {
                adapter.submitList(courses);
            }
        });

        return view;
    }
}
