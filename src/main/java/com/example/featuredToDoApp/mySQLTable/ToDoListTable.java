package com.example.featuredToDoApp.mySQLTable;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "ToDos")
public class ToDoListTable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer Id;

    private Integer userId;

    private String description;

    private Integer parentId;

    private Date entryDate;

    private Date expiryDate;

    private boolean isDone;

    private boolean hasExpired;

    public ToDoListTable() {}

    public ToDoListTable(Integer userId, String description, Integer parentId, Date expiryDate)    {
        this.userId = userId;
        this.description = description;
        this.parentId = parentId;
        this.entryDate = new Date();
        this.expiryDate = expiryDate;
        this.isDone = false;
        this.hasExpired = false;
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public boolean getHasExpired() {
        return hasExpired;
    }

    public void setHasExpired(boolean hasExpired) {
        this.hasExpired = hasExpired;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Date getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(Date expiryDate) {
        this.expiryDate = expiryDate;
    }

    public Integer getId() {
        return Id;

    }

    public void setId(Integer id) {
        this.Id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getEntryDate() {
        return entryDate;
    }

    public void setEntryDate(Date entryDate) {
        this.entryDate = entryDate;
    }

    public boolean isDone() {
        return isDone;
    }

    public void setDone(boolean done) {
        isDone = done;
    }

    public String toString()    {
        return this.description + ", " + this.getExpiryDate();
    }


}
