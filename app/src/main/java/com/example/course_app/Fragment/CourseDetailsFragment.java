package com.example.course_app.Fragment;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.course_app.R;

public class CourseDetailsFragment extends Fragment {

    private static final String ARG_TITLE = "title";
    private static final String ARG_DESCRIPTION = "description";
    private static final String ARG_INSTRUCTOR = "instructor";
    private static final String ARG_PRICE = "price";
    private static final String ARG_BITMAP = "imageBytes";

    public static CourseDetailsFragment newInstance(String title, String description, String instructor, String price, byte[] imageBytes) {
        CourseDetailsFragment fragment = new CourseDetailsFragment();
        Bundle args = new Bundle();
        args.putString(ARG_TITLE, title);
        args.putString(ARG_DESCRIPTION, description);
        args.putString(ARG_INSTRUCTOR, instructor);
        args.putString(ARG_PRICE, price);
        args.putByteArray(ARG_BITMAP, imageBytes);
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_course_details, container, false);

        initializeUI(view);

        return view;
    }

    private void initializeUI(View view) {
        TextView titleTextView = view.findViewById(R.id.course_title);
        TextView descriptionTextView = view.findViewById(R.id.course_description);
        TextView instructorTextView = view.findViewById(R.id.course_instructor);
        TextView priceTextView = view.findViewById(R.id.course_price);
        ImageView courseImageView = view.findViewById(R.id.course_image);

        Bundle args = getArguments();
        if (args != null) {
            String title = args.getString(ARG_TITLE);
            String description = args.getString(ARG_DESCRIPTION);
            String instructor = args.getString(ARG_INSTRUCTOR);
            String price = args.getString(ARG_PRICE);
            byte[] imageBytes = args.getByteArray(ARG_BITMAP);

            titleTextView.setText(title);
            descriptionTextView.setText(description);
            instructorTextView.setText(instructor);
            priceTextView.setText(price);

            if (imageBytes != null) {
                Bitmap bitmap = BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.length);
                courseImageView.setImageBitmap(bitmap);
            }
        }
    }
}
