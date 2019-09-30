package com.example.malay.todos.data;

import java.util.Date;

public class ToDo {

    private String toDoDescription;
    private Date entryDate;
    private boolean isDone;

    public ToDo(String toDoDescription)   {
        this.toDoDescription = toDoDescription;
        this.entryDate = new Date();
        this.isDone = false;
    }

    public String getToDoDescription() {
        return toDoDescription;
    }

    public Date getEntryDate() {
        return entryDate;
    }

    public boolean isDone() {
        return isDone;
    }

    public String toString()    {
        StringBuilder toDoSB = new StringBuilder(100);
        toDoSB.append("{ ");
        toDoSB.append(toDoDescription);
        toDoSB.append("     ");
        toDoSB.append(entryDate);
        toDoSB.append("     ");
        toDoSB.append(isDone);
        toDoSB.append(" }");
        return toDoSB.toString();
    }
    public String toString(String description)    {
        StringBuilder toDoSB = new StringBuilder(100);
        toDoSB.append("[ ");
        toDoSB.append(toDoDescription);
        toDoSB.append(" ");
        toDoSB.append(entryDate);
        toDoSB.append(isDone);
        toDoSB.append(" ]");
        return toDoSB.toString();
    }

}
