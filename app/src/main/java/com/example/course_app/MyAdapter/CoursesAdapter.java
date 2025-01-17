package com.example.course_app.MyAdapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.course_app.ClasesJava.Courses;
import com.example.course_app.R;

public class CoursesAdapter extends ListAdapter<Courses, CoursesAdapter.CourseViewHolder> {

    private OnCourseClickListener listener;

    public interface OnCourseClickListener {
        void onCourseClick(Courses course);
    }

    public void setOnCourseClickListener(OnCourseClickListener listener) {
        this.listener = listener;
    }

    public CoursesAdapter() {
        super(DIFF_CALLBACK);
    }

    private static final DiffUtil.ItemCallback<Courses> DIFF_CALLBACK = new DiffUtil.ItemCallback<Courses>() {
        @Override
        public boolean areItemsTheSame(@NonNull Courses oldItem, @NonNull Courses newItem) {
            return oldItem.getCourse_id() == newItem.getCourse_id();
        }

        @Override
        public boolean areContentsTheSame(@NonNull Courses oldItem, @NonNull Courses newItem) {
            return oldItem.equals(newItem);
        }
    };

    @NonNull
    @Override
    public CourseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_course, parent, false);
        return new CourseViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull CourseViewHolder holder, int position) {
        Courses currentCourse = getItem(position);

        if (currentCourse != null) {
            holder.textViewTitle.setText(currentCourse.getTitle());
            holder.textViewDescription.setText(currentCourse.getDescription());
            holder.textViewInstructor.setText("Instructor: " + currentCourse.getInstructor());
            holder.textViewPrice.setText("Price: $" + currentCourse.getPrice());

            Glide.with(holder.itemView.getContext())
                    .load(currentCourse.getBitmap())
                    .placeholder(R.drawable.img_7)
                    .error(R.drawable.img_1)
                    .into(holder.imageViewCourse);
        }
    }

    class CourseViewHolder extends RecyclerView.ViewHolder {
        private final TextView textViewTitle;
        private final TextView textViewDescription;
        private final TextView textViewInstructor;
        private final TextView textViewPrice;
        private final ImageView imageViewCourse;

        public CourseViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewTitle = itemView.findViewById(R.id.textView_courseTitle);
            textViewDescription = itemView.findViewById(R.id.textView_courseDescription);
            textViewInstructor = itemView.findViewById(R.id.textView_courseInstructor);
            textViewPrice = itemView.findViewById(R.id.textView_coursePrice);
            imageViewCourse = itemView.findViewById(R.id.imageView_courseImage);

            itemView.setOnClickListener(v -> {
                int position = getAdapterPosition();
                if (listener != null && position != RecyclerView.NO_POSITION) {
                    listener.onCourseClick(getItem(position));
                }
            });
        }
    }
}
