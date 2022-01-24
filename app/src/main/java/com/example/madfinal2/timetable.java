package com.example.madfinal2;

import com.google.firebase.database.Exclude;

import java.io.Serializable;

public class timetable implements Serializable {
    @Exclude
    private String key;
    private String subject;
    private String date;
    private String time;

    public timetable() {}

    public timetable(String subject, String date, String time) {
        this.subject = subject;
        this.date = date;
        this.time = time;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
}
