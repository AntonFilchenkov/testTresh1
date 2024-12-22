package com.example.testtresh;

public class Lesson {
    private int id;
    private String classNumber;
    private String dayOfWeek;
    private String subject;
    private String startTime;
    private String endTime;
    private String room;

    public String getClassNumber() {
        return classNumber;
    }

    public int getId() {
        return id;
    }

    public String getDayOfWeek() {
        return dayOfWeek;
    }

    public String getEndTime() {
        return endTime;
    }

    public String getRoom() {
        return room;
    }

    public String getStartTime() {
        return startTime;
    }

    public String getSubject() {
        return subject;
    }

    public void setClassNumber(String classNumber) {
        this.classNumber = classNumber;
    }

    public void setDayOfWeek(String dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public void setRoom(String room) {
        this.room = room;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

}
