package com.example.featuredToDoApp.mySQLTable;

public class ToDoJson {
    private Integer userId;
    private String title;
    private String description;
    private String expiryDate;

    public ToDoJson()   {}
    public ToDoJson(Integer userId, String title, String description, String expiryDate)   {
        this.userId = userId;
        this.title = title;
        this.description = description;
        this.expiryDate = expiryDate;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
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

    public String getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(String expiryDate) {
        this.expiryDate = expiryDate;
    }
}
