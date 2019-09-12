package com.example.noticebordcseju.model;

import java.util.Date;

public class Notice {
    private Date date;
    private String title;
    private String description;

    public Notice() {
    }

    public Notice(Date date, String title, String description) {
        this.date = date;
        this.title = title;
        this.description = description;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Notice{" +
                "date=" + date +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
