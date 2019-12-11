package com.example.featuredToDoApp;


import com.example.featuredToDoApp.mySQLTable.ToDoJson;
import com.example.featuredToDoApp.mySQLTable.ToDoListTable;
import com.example.featuredToDoApp.mySQLTable.UserJson;
import com.example.featuredToDoApp.mySQLTable.UsersTable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@Controller
public class MainController {


    @Autowired
    private UsersTableRepository uRep;

    @Autowired
    private ServiceClass sc;

    @Autowired
    private ToDoListTableRepository toRep;

    //by requestParam on 10-12
    @RequestMapping(path = "/newUser", method = RequestMethod.GET)
    public @ResponseBody
    UsersTable addNewUser(@RequestParam String fName, @RequestParam String lName) {
        return sc.addNewUserS(fName, lName);
    }

    //request by json on 10-12
    @RequestMapping(path = "/newUser", method = RequestMethod.POST)
    public @ResponseBody
    UsersTable addNewUser(@RequestBody UserJson newUser) {
        return sc.addNewUserS(newUser);
    }

    //
    @RequestMapping(path = "/getAllUsers", method = RequestMethod.GET)
    public @ResponseBody
    List<UsersTable> getAllUsers() {
        return sc.getAllUsersS();
    }

    //request by requestParam
    ToDoListTable addToDoS(@RequestParam Integer userId, @RequestParam String title, @RequestParam String description,
                           @RequestParam Integer year, @RequestParam Integer month, @RequestParam Integer date) {
        return sc.addToDoS(userId, title, description, year, month, date);
    }

    //request by json
    @RequestMapping(path = "/addToDo", method = RequestMethod.POST)
    public @ResponseBody
    ToDoListTable addToDo(@RequestBody ToDoJson jsonReq) {
        return sc.addToDoS(jsonReq);
    }


    //request by json or requestParam
    @RequestMapping(path = "/getCountUserToDos")
    public @ResponseBody
    Integer getCountUserToDos(@RequestParam Integer userId) {
        return sc.getCountUserToDosS(userId);
    }

    //
    @RequestMapping(path = "/getCountUserPendToDos")
    public @ResponseBody
    Integer getCountUserPendToDos(@RequestParam Integer userId) {
        return sc.getCountUserPendToDosS(userId);
    }

    //request by json or requestParam
    //@RequestMapping()


//********************************************************************************************************************************************************

    @RequestMapping(path = "/getAllParentToDos")
    public @ResponseBody
    Object getAllToDos() {
        return toRep.findAllParentToDos();
    }

    @RequestMapping(path = "/getAllParentWiseChildToDos")
    public @ResponseBody
    Object getAllParentWiseChildToDos() {
        return sc.findAllPWiseCToDos();
    }


    @RequestMapping(path = "/getToDo")
    public @ResponseBody
    Object getToDo(@RequestParam Integer iD) {
        return toRep.findToDo(iD);
    }

    //list of todos for a given users
    @RequestMapping(path = "/getUserToDos")
    public @ResponseBody
    Object showUserList(@RequestParam Integer userId) {
        return toRep.findToDoList(userId);
    }

    //list of pending todos for a given users
    @RequestMapping(path = "/getPendingToDos")
    public @ResponseBody
    Object showUserPendingList(@RequestParam boolean isDone, @RequestParam Integer searchTerm) {
        return toRep.findPendingToDoList(isDone, searchTerm);
    }


    @RequestMapping(path = "/setToDoDone")
    public @ResponseBody
    Object setToDoDone(@RequestParam Integer iD) {
        toRep.setToDoDone(iD);
        return getToDo(iD);
    }


    @RequestMapping(path = "/getExpiredToDos")
    public @ResponseBody
    Object getExpiredToDo(@RequestParam Integer givenId) {
        return toRep.findExpiredToDo(new Date(), givenId);
    }


    @RequestMapping(path = "/getListToDosByExp")
    public @ResponseBody
    Object listToDosByExp(@RequestParam Integer givenId) {
        return toRep.listToDosByExp(givenId);
    }

    //get datewise todos for a given user
    @RequestMapping(path = "/getDateWiseList/user")
    public @ResponseBody
    Object mapOfToDosWithExp(@RequestParam Integer givenId) {

        return sc.getMapOfToDoListOfUsers(givenId);

    }


    //to get a map of all todos grouped as per expiry date and users...
    @RequestMapping(path = "/getExpDateWiseToDo/all")
    public @ResponseBody
    Object expDateWiseAllToDo() {
        return sc.getDateWiseExpOfAllUsers();
    }

    //
    @RequestMapping(path = "/getsExpiryDateCount/user")
    public @ResponseBody
    Object getExpDateCount(@RequestParam Integer givenId) {

        return sc.getExpDateCount(givenId);

    }


    @RequestMapping(path = "/addChildToDo")
    public @ResponseBody
    Object postChildToDo(@RequestParam Integer userId, @RequestParam Integer parentId, @RequestParam String title,
                         @RequestParam String description, @RequestParam Integer year,
                         @RequestParam Integer month, @RequestParam Integer date) {

        sc.postChildToDoToDB(userId, parentId, title, description, year, month, date);

        return "success";
    }

    @RequestMapping(path = "/getsUserWiseDateWiseToDo/all")
    public @ResponseBody
    Object userWiseDateWiseAllToDo() {
        return sc.getUserWisedateWiseToDo();
    }

    //
    @RequestMapping(path = "/getsAllToDosWithExpDate/all")
    public @ResponseBody
    Object allToDosWithExpDate() {
        return sc.getAllToDosWithExpDate();
    }


    // new apis by palash

    @RequestMapping(path = "/addNewTodo", method = RequestMethod.POST)
    public @ResponseBody
    ToDoListTable addNewTodo(@RequestBody ToDoListTable todo) {
        todo.setEntryDate(new Date());
        return toRep.save(todo);
    }

    @RequestMapping(path = "/updateTodo", method = RequestMethod.POST)
    public @ResponseBody
    ToDoListTable updateTodo(@RequestBody ToDoListTable todo) {
        return toRep.save(todo);
    }


}
