package com.company;

import java.util.Calendar;
import java.util.Date;

public class Task {
    private String title;
    private String description;
    private Date dateCreated;
    private Date dueDate;
    private boolean isCompleted;

    public Task(String title, String description, Date dueDate) {
        this.title = title;
        this.description = description;
        this.dueDate = dueDate;
        this.isCompleted = false;
        setDateCreated();
        this.dateCreated = getDateCreated();
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

    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated() {
        Calendar calendar = Calendar.getInstance();
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
