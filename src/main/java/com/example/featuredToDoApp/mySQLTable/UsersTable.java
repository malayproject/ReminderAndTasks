package com.example.featuredToDoApp.mySQLTable;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "users")
public class UsersTable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer userId;

    private String fName;

    private String lName;

    private Date userAddedDate;

    public UsersTable(){}

    public UsersTable(String fName, String lName)  {
        this.fName = fName;
        this.lName = lName;
        this.userAddedDate = new Date();
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getfName() {
        return fName;
    }

    public void setfName(String fName) {
        this.fName = fName;
    }

    public String getlName() {
        return lName;
    }

    public void setlName(String lName) {
        this.lName = lName;
    }

    public Date getUserAddedDate() {
        return userAddedDate;
    }

    public void setUserAddedDate(Date userAddedDate) {
        this.userAddedDate = userAddedDate;
    }

    public String toString()    {

        return this.getUserId() + ", " + this.fName + " " + this.lName;
    }
}
