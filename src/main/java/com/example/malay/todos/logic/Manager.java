package com.example.malay.todos.logic;

import com.example.malay.todos.data.ToDo;


import java.util.ArrayList;

public class Manager {
    private String userName;
    private ArrayList<ToDo> userList;

    public Manager()    {
        this.userName = "malayweb";
        this.userList = new ArrayList<>();
    }

    public Manager(String userName)    {
        this.userName = userName;
        this.userList = new ArrayList<>();
    }

    public String getUserName() {
        return userName;
    }

    public void addToDo(String description) {
        userList.add(new ToDo(description));
    }


    public String getToDoList() {

        StringBuilder sb = new StringBuilder(1000);
        sb.append("[\n");
        for(int i = 0; i < userList.size(); i++)    {
            sb.append(userList.get(i).toString());

            sb.append("\n");
        }
        sb.append("]");
        System.out.println(sb.toString());
        return sb.toString();
    }

}
