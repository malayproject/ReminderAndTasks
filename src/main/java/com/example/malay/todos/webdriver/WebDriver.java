package com.example.malay.todos.webdriver;

import com.example.malay.todos.logic.Manager;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
//@RequestMapping(path = "/user")
public class WebDriver {

    private Manager m;

    @RequestMapping(path = "/user_definition")
    public void createUser(@RequestParam String userName)  {
        m = new Manager(userName);

    }
    @RequestMapping(path = "/printlist")
    public String printlist()  {
        String str = m.getUserName() + m.getToDoList();
        return str;
    }

    @RequestMapping(path = "/addtodo")
    public String addToDo(@RequestParam String desc)    {
        m.addToDo(desc);
        return "add success";
    }
}
