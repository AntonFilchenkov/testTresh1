package com.example.testtresh;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

public class ViewLessonsActivity extends AppCompatActivity {

    private TextView lessonsTextView;
    private Button closeButton;
    private DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_lessons);

        lessonsTextView = findViewById(R.id.lessonsTextView);
        closeButton = findViewById(R.id.closeButton);
        databaseHelper = new DatabaseHelper(this);

        List<Lesson> lessons = databaseHelper.getAllLessons();
        StringBuilder stringBuilder = new StringBuilder();
        for (Lesson lesson : lessons) {
            stringBuilder.append(lesson.getDayOfWeek()).append(": ").append(lesson.getSubject()).append("\\n");
        }
        lessonsTextView.setText(stringBuilder.toString());

        closeButton.setOnClickListener(v -> finish());
    }
}