package com.example.testtresh;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private EditText classNumberEditText, dayOfWeekEditText, subjectEditText,
            startTimeEditText, endTimeEditText, roomEditText;
    private DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        databaseHelper = new DatabaseHelper(this);

        classNumberEditText = findViewById(R.id.classNumberEditText);
        dayOfWeekEditText = findViewById(R.id.dayOfWeekEditText);
        subjectEditText = findViewById(R.id.subjectEditText);
        startTimeEditText = findViewById(R.id.startTimeEditText);
        endTimeEditText = findViewById(R.id.endTimeEditText);
        roomEditText = findViewById(R.id.roomEditText);

        Button addButton = findViewById(R.id.addButton);
        Button viewButton = findViewById(R.id.viewButton);
        Button updateButton = findViewById(R.id.updateButton);

        // Добавление урока
        addButton.setOnClickListener(v -> {
            Lesson lesson = new Lesson();
            lesson.setClassNumber(classNumberEditText.getText().toString());
            lesson.setDayOfWeek(dayOfWeekEditText.getText().toString());
            lesson.setSubject(subjectEditText.getText().toString());
            lesson.setStartTime(startTimeEditText.getText().toString());
            lesson.setEndTime(endTimeEditText.getText().toString());
            lesson.setRoom(roomEditText.getText().toString());
            if (databaseHelper.addLesson(lesson)) {
                Toast.makeText(MainActivity.this, "Урок добавлен", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(MainActivity.this, "Ошибка добавления", Toast.LENGTH_SHORT).show();
            }
        });

        // Просмотр всех уроков
        viewButton.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, ViewLessonsActivity.class);
            startActivity(intent);
        });

        // Изменение урока по дню недели
        updateButton.setOnClickListener(v -> {
            Lesson lesson = new Lesson();
            lesson.setDayOfWeek(dayOfWeekEditText.getText().toString());
            lesson.setClassNumber(classNumberEditText.getText().toString());
            lesson.setSubject(subjectEditText.getText().toString());
            lesson.setStartTime(startTimeEditText.getText().toString());
            lesson.setEndTime(endTimeEditText.getText().toString());
            lesson.setRoom(roomEditText.getText().toString());
            if (databaseHelper.updateLesson(lesson)) {
                Toast.makeText(MainActivity.this, "Урок изменён", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(MainActivity.this, "Ошибка изменения", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
