package com.example.testtresh;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "schedule.db";
    private static final String TABLE_NAME = "lessons";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_CLASS_NUMBER = "class_number";
    private static final String COLUMN_DAY_OF_WEEK = "day_of_week";
    private static final String COLUMN_SUBJECT = "subject";
    private static final String COLUMN_START_TIME = "start_time";
    private static final String COLUMN_END_TIME = "end_time";
    private static final String COLUMN_ROOM = "room";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTable = "CREATE TABLE " + TABLE_NAME + " (" +
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_CLASS_NUMBER + " TEXT, " +
                COLUMN_DAY_OF_WEEK + " TEXT, " +
                COLUMN_SUBJECT + " TEXT, " +
                COLUMN_START_TIME + " TEXT, " +
                COLUMN_END_TIME + " TEXT, " +
                COLUMN_ROOM + " TEXT)";
        db.execSQL(createTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    // Метод для добавления уроков
    public boolean addLesson(Lesson lesson) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_CLASS_NUMBER, lesson.getClassNumber());
        contentValues.put(COLUMN_DAY_OF_WEEK, lesson.getDayOfWeek());
        contentValues.put(COLUMN_SUBJECT, lesson.getSubject());
        contentValues.put(COLUMN_START_TIME, lesson.getStartTime());
        contentValues.put(COLUMN_END_TIME, lesson.getEndTime());
        contentValues.put(COLUMN_ROOM, lesson.getRoom());
        long result = db.insert(TABLE_NAME, null, contentValues);
        return result != -1;
    }

    // Метод для получения всех уроков
    public List<Lesson> getAllLessons() {
        List<Lesson> lessons = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME, null);

        if (cursor.moveToFirst()) {
            do {
                Lesson lesson = new Lesson();
                lesson.setId(cursor.getInt(0));
                lesson.setClassNumber(cursor.getString(1));
                lesson.setDayOfWeek(cursor.getString(2));
                lesson.setSubject(cursor.getString(3));
                lesson.setStartTime(cursor.getString(4));
                lesson.setEndTime(cursor.getString(5));
                lesson.setRoom(cursor.getString(6));
                lessons.add(lesson);
            } while (cursor.moveToNext());
        }
        cursor.close();
        return lessons;
    }

    // Метод для изменения информации по дню недели
    public boolean updateLesson(Lesson lesson) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_CLASS_NUMBER, lesson.getClassNumber());
        contentValues.put(COLUMN_SUBJECT, lesson.getSubject());
        contentValues.put(COLUMN_START_TIME, lesson.getStartTime());
        contentValues.put(COLUMN_END_TIME, lesson.getEndTime());
        contentValues.put(COLUMN_ROOM, lesson.getRoom());
        return db.update(TABLE_NAME, contentValues, COLUMN_DAY_OF_WEEK + " = ?", new String[]{lesson.getDayOfWeek()}) > 0;
    }
}
