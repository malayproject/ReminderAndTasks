package com.example.featuredToDoApp;


import com.example.featuredToDoApp.mySQLTable.ToDoListTable;
import com.example.featuredToDoApp.mySQLTable.UsersTable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.*;

@Controller
public class MainController {


    @Autowired
    private UsersTableRepository uRep;

    @Autowired
    private ServiceClass sc;

    @Autowired
    private ToDoListTableRepository toRep;


    @RequestMapping(path = "/newUser")
    public @ResponseBody Object insertNewUser(@RequestParam String fName, @RequestParam String lName) {
        UsersTable user = new UsersTable(fName, lName);
        user = uRep.save(user);
        return user;
    }


    @RequestMapping(path = "/getUsers")
    public @ResponseBody List<UsersTable> getAllUsers()    {
        return uRep.getAllUsers();
    }


    @RequestMapping(path = "/getAllParentToDos")
    public @ResponseBody Object getAllToDos()  {
        return toRep.findAllParentToDos();
    }

    @RequestMapping(path = "/getAllParentWiseChildToDos")
    public @ResponseBody Object getAllParentWiseChildToDos()    {
        return sc.findAllPWiseCToDos();
    }


    @RequestMapping(path = "/addToDo")
    public @ResponseBody Object postData(@RequestParam Integer userId, @RequestParam String description,
                                         @RequestParam Integer year, @RequestParam Integer month, @RequestParam Integer date)   {
        Date tempExpiryDate = new Date(year-1900, month-1, date);
        if(sc.dateValidator(tempExpiryDate)) {
            ToDoListTable toDoElement = new ToDoListTable(userId, description, 0, tempExpiryDate);
            toDoElement = toRep.save(toDoElement);
            return toDoElement;
        }
        else return "Invalid expiry date.";
    }


    @RequestMapping(path = "/getToDo")
    public @ResponseBody Object getToDo(@RequestParam Integer iD)   {
        return toRep.findToDo(iD);
    }

    //list of todos for a given users
    @RequestMapping(path = "/getUserToDos")
    public @ResponseBody Object showUserList(@RequestParam Integer userId) {
        return toRep.findToDoList(userId);
    }

    //list of pending todos for a given users
    @RequestMapping(path = "/getPendingToDos")
    public @ResponseBody Object showUserPendingList(@RequestParam boolean isDone, @RequestParam Integer searchTerm) {
        return toRep.findPendingToDoList(isDone, searchTerm);
    }


    @RequestMapping(path = "/setToDoDone")
    public @ResponseBody Object setToDoDone(@RequestParam Integer iD)   {
        toRep.setToDoDone(iD);
        return getToDo(iD);
    }


    @RequestMapping(path = "/getExpiredToDos")
    public @ResponseBody Object getExpiredToDo(@RequestParam Integer givenId)    {
        return toRep.findExpiredToDo(new Date(), givenId);
    }


    @RequestMapping(path = "/getListToDosByExp")
    public @ResponseBody Object listToDosByExp(@RequestParam Integer givenId)    {
     return toRep.listToDosByExp(givenId);
    }

    //get datewise todos for a given user
    @RequestMapping(path = "/getDateWiseList/user")
    public @ResponseBody Object mapOfToDosWithExp(@RequestParam Integer givenId) {

        return sc.getMapOfToDoListOfUsers(givenId);

    }


    //to get a map of all todos grouped as per expiry date and users...
    @RequestMapping(path = "/getExpDateWiseToDo/all")
    public @ResponseBody Object expDateWiseAllToDo()    {
        return sc.getDateWiseExpOfAllUsers();
    }

    //
    @RequestMapping(path = "/getsExpiryDateCount/user")
    public @ResponseBody Object getExpDateCount(@RequestParam Integer givenId) {

        return sc.getExpDateCount(givenId);

    }


    @RequestMapping(path = "/addChildToDo")
    public @ResponseBody Object postChildToDo(@RequestParam Integer userId, @RequestParam Integer parentId,
                                              @RequestParam String description,@RequestParam Integer year,
                                              @RequestParam Integer month, @RequestParam Integer date)    {

        sc.postChildToDoToDB(userId, parentId, description, year, month, date);

        return "success";
    }

    @RequestMapping(path = "/getsUserWiseDateWiseToDo/all")
    public @ResponseBody Object userWiseDateWiseAllToDo(){
        return sc.getUserWisedateWiseToDo();
    }

    //
    @RequestMapping(path = "/getsAllToDosWithExpDate/all")
    public @ResponseBody Object allToDosWithExpDate()   {
        return sc.getAllToDosWithExpDate();
    }



}
