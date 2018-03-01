package com.company;

import java.io.File;
import java.io.FileWriter;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Task {
    private String title;
    private String description;
    private Date dateCreated;
    private Date dueDate;
    private boolean isCompleted;
    SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yy");

    public Task(String title, String description, Date dueDate) {
        this.title = title;
        this.description = description;
        this.dueDate = dueDate;
        this.isCompleted = false;
        setDateCreated();
        this.dateCreated = getDateCreated();

    }

    public Task(String title, String description, Date dateCreated, Date dueDate, boolean isCompleted) {
        this.title = title;
        this.description = description;
        this.dateCreated = dateCreated;
        this.dueDate = dueDate;
        this.isCompleted = isCompleted;
    }

    Calendar calendar = Calendar.getInstance();

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

    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated() {
        this.dateCreated = calendar.getTime();
    }

    public Date getDueDate() {
        return dueDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    public boolean isCompleted() {
        return isCompleted;
    }

    public void setCompleted(boolean completed) {
        isCompleted = completed;
    }
}
