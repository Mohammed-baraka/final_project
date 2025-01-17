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
import com.example.course_app.RoomDataBase.OnlineDataBase;

public class ProfileFragment extends Fragment {

    private static final String ARG_USER_ID = "user_id";
    private int userId;
    private static final String ARG_SECTION_NAME = "Profile";


    public static ProfileFragment newInstance(String sectionName) {
        ProfileFragment fragment = new ProfileFragment();
        Bundle args = new Bundle();
        args.putString(ARG_SECTION_NAME, sectionName);
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile, container, false);

        if (getArguments() != null) {
            userId = getArguments().getInt(ARG_USER_ID);
        }

        initializeUI(view);

        return view;
    }

    private void initializeUI(View view) {
        TextView nameTextView = view.findViewById(R.id.profile_name);
        TextView emailTextView = view.findViewById(R.id.profile_email);
        ImageView profileImageView = view.findViewById(R.id.profile_image);

        OnlineDataBase.getDatabase(requireContext()).userDao().getUserById(userId).observe(getViewLifecycleOwner(), user -> {
            if (user != null) {
                nameTextView.setText(user.getFullName());
                emailTextView.setText(user.getEmail());

                byte[] imageBytes = user.getProfileImage();
                if (imageBytes != null) {
                    Bitmap bitmap = BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.length);
                    profileImageView.setImageBitmap(bitmap);
                } else {
                    profileImageView.setImageResource(R.drawable.img_7);
                }

            }
        });
    }
}
