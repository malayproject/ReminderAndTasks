package com.example.featuredToDoApp;

import com.example.featuredToDoApp.mySQLTable.ToDoListTable;
import com.example.featuredToDoApp.mySQLTable.UsersTable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ServiceClass {

    @Autowired
    private ToDoListTableRepository toRep;

    @Autowired
    private UsersTableRepository uRep;

    static boolean dateValidator(Date expiryDate)  {
        return expiryDate.after(new Date());
    }

    void postChildToDoToDB(Integer userId, Integer parentId, String description, Integer year, Integer month, Integer date)  {
        Date tempExpDate = new Date(year - 1900, month - 1, date);
        if(dateValidator(tempExpDate)) {
            ToDoListTable newToDo = new ToDoListTable(userId, description, parentId, tempExpDate);
            newToDo = toRep.save(newToDo);
        }
    }


    Map<ToDoListTable, List<ToDoListTable>> findAllPWiseCToDos()    {
        List<ToDoListTable> tempToDoList = toRep.findAllToDos();
        List<ToDoListTable> tempParentToDo = toRep.findAllParentToDos();
        Map<ToDoListTable, List<ToDoListTable>> m = new HashMap<>();
        for(int i = 0; i < tempToDoList.size(); i++)    {
            ToDoListTable tempToDo = tempToDoList.get(i);
            if(tempToDo.getParentId() == null || tempToDo.getParentId().equals(0)) {

                m.put(tempToDo, new ArrayList<>());
            }
            else    {

                m.get(tempToDoList.get(tempToDo.getParentId())).add(tempToDo);
            }
        }
        return m;
    }


    Map<Date, List<ToDoListTable>> getMapOfToDoListOfUsers(int givenId)  {
        Map<Date, List<ToDoListTable>> m = new HashMap<>();
        List<ToDoListTable> t = toRep.findToDoList(givenId);
        List<List<ToDoListTable>> tj = new ArrayList<>();
        List<ToDoListTable> temp;

        for(int i = 0, j = 0; i < t.size(); i++)   {
            Date ki = t.get(i).getExpiryDate();
            if(i == 0)  {
                tj.add(new ArrayList<>());
                tj.get(j).add(t.get(i));
                m.put(ki,tj.get(j));

            }
            else if(m.containsKey(ki))  {
                temp = m.get(ki);
                temp.add(t.get(i));
                m.put(ki, temp);

            }
            else    {
                j++;
                tj.add(new ArrayList<>());
                tj.get(j).add(t.get(i));
                m.put(ki,tj.get(j));

            }
        }

        return m;
    }


    Map<Date, List<ToDoListTable>> getMapOfToDoListOfUsers2(int givenId) {
        Map<Date, List<ToDoListTable>> m = new HashMap<>();
        List<ToDoListTable> t = toRep.findToDoList(givenId);

        for (int i=0;i<t.size(); i++){

            ToDoListTable todo = t.get(i);
            Date expiryDate = todo.getExpiryDate();

            if(!m.containsKey(expiryDate)){
                m.put(expiryDate, new ArrayList<ToDoListTable>());
            }

            m.get(expiryDate).add(todo);
        }
        return m;
    }

    Map<Date, Integer> getExpDateCount(Integer userId)  {

        Map<Date, Integer> m = new HashMap<>();
        List<ToDoListTable> t = toRep.findToDoList(userId);
        ToDoListTable todo;
        Date k;

        for(int i = 0; i < t.size(); i++)   {
            todo = t.get(i);
            k = todo.getExpiryDate();
            if(m.containsKey(k)&& m.get(k) != null)    {
                m.put(k, m.get(k) + 1);
            }
            else if(!m.containsKey(k))  {
                m.put(k, 1);
            }
        }
        return m;
    }

    Map<Date, Map<UsersTable, List<ToDoListTable>>> getDateWiseExpOfAllUsers()  {

        List<ToDoListTable> todo = toRep.findAllToDos();
        List<UsersTable> user = uRep.getAllUsers();
        System.out.println(todo.size());

        Map<Date, Map<UsersTable, List<ToDoListTable>>> m = new HashMap<>();

        List<Date> uniqDate = new ArrayList<>();
        for(int i = 0; i < todo.size(); i++)    {
            Date temp = todo.get(i).getExpiryDate();
            if(!uniqDate.contains(temp)) {
                uniqDate.add(temp);
            }
        }
        for(int j = 0; j < uniqDate.size(); j++)    {//...................................................loop for each unique date
            Date temp = uniqDate.get(j);
            Map<UsersTable, List<ToDoListTable>> n = new HashMap<>();
            for(int k = 0; k < user.size(); k++)    {//...................................................loop for each user
                List<ToDoListTable> tempToDo  = new ArrayList<>();
                for(int l = 0; l < todo.size(); l++)    {
                    if((todo.get(l).getUserId().equals(k+1))&&(temp .equals( todo.get(l).getExpiryDate())))   {

                        tempToDo.add(todo.get(l));
                    }
                }
                n.put(user.get(k), tempToDo);
            }
            m.put(temp, n);
        }
        return m;
    }
    Map<Date, Map<UsersTable, List<ToDoListTable>>> getDateWiseExpOfAllUsers1()  {

        List<ToDoListTable> todo = toRep.findAllToDos();
        List<UsersTable> users = uRep.getAllUsers();

        Map<Date, Map<UsersTable, List<ToDoListTable>>> m = new HashMap<>();

        Map<Integer, UsersTable> usersMap = new HashMap<>();
        for(int i = 0; i < users.size(); i++)   {
            int s = users.get(i).getUserId();
            if(!usersMap.containsKey(s))    {
                usersMap.put(s, users.get(i));
            }
        }
        for(int j = 0; j < todo.size(); j++)    {
            Date tempDate = todo.get(j).getExpiryDate();

            if(!m.containsKey(tempDate))    {
                List<ToDoListTable> userToDoByDate = new ArrayList<>();
                userToDoByDate.add(todo.get(j));
                Map<UsersTable, List<ToDoListTable>>n = new HashMap<>();
                n.put(usersMap.get(todo.get(j).getUserId()), userToDoByDate);
                m.put(tempDate, n);
            }

            else    {
                if(!m.get(tempDate).containsKey(usersMap.get(todo.get(j).getUserId())))  {
                    List<ToDoListTable> userToDoByDate = new ArrayList<>();
                    userToDoByDate.add(todo.get(j));
                    m.get(tempDate).put(usersMap.get(todo.get(j).getUserId()), userToDoByDate);
                }
                else    {
                    m.get(tempDate).get(usersMap.get(todo.get(j).getUserId())).add(todo.get(j));
                }
            }
        }
        return m;
    }

    Map<UsersTable, Map<Date, List<ToDoListTable>>> getUserWisedateWiseToDo()   {

        List<ToDoListTable> todo = toRep.findAllToDos();
        List<UsersTable> users = uRep.getAllUsers();
        Map<UsersTable, Map<Date, List<ToDoListTable>>> m = new HashMap<>();

        Map<Integer, UsersTable> usersMap = new HashMap<>();
        for(int i = 0; i < users.size(); i++)   {
            int s = users.get(i).getUserId();
            if(!usersMap.containsKey(s))    {
                usersMap.put(s, users.get(i));
            }
        }
        for(int i = 0; i < todo.size(); i++)    {
            UsersTable tempUser = usersMap.get(todo.get(i).getUserId());
            if(!m.containsKey(tempUser))    {
                Map<Date, List<ToDoListTable>> n = new HashMap<>();
                List<ToDoListTable> userWiseDateWiseToDo = new ArrayList<>();
                userWiseDateWiseToDo.add(todo.get(i));
                n.put(todo.get(i).getExpiryDate(), userWiseDateWiseToDo);
                m.put(tempUser, n);
            }
            else    {
                if(!m.get(tempUser).containsKey(todo.get(i).getExpiryDate()))   {
                    List<ToDoListTable> userWiseDateWiseToDo = new ArrayList<>();
                    userWiseDateWiseToDo.add(todo.get(i));
                    m.get(tempUser).put(todo.get(i).getExpiryDate(), userWiseDateWiseToDo);
                }
                else    {
                    m.get(tempUser).get(todo.get(i).getExpiryDate()).add(todo.get(i));
                }
            }
        }
        return m;
    }

    Map<ToDoListTable, Date> getAllToDosWithExpDate()   {

        Map<ToDoListTable, Date> m = new HashMap<>();
        List<ToDoListTable> todos = toRep.findAllToDos();
        for(int i = 0; i < todos.size(); i++)   {
            m.put(todos.get(i), todos.get(i).getExpiryDate());
        }
        return m;
    }


}



