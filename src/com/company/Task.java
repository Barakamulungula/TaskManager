package com.company;

import java.util.Date;

public class Task {

    private String title;
    private String description;
    private Boolean completed;
    private Date dateCreated;
    private Date dueDate;


    public Task(String title, String description, Date dueDate, Date dateCreated) {
        this.title = title;
        this.description = description;
        this.dueDate = dueDate;
        this.dateCreated = dateCreated;
        this.completed = false;

    }


    public Task() {
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

    public Boolean getCompleted() {
        return completed;
    }

    public void setCompleted(Boolean completed) {
        this.completed = completed;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }


}
