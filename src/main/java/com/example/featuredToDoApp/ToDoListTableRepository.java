package com.example.featuredToDoApp;

import com.example.featuredToDoApp.mySQLTable.ToDoListTable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Repository
public interface ToDoListTableRepository extends CrudRepository<ToDoListTable, Integer>   {


    @Query("SELECT u FROM ToDoListTable u WHERE (u.parentId = null) OR (u.parentId = 0)")
    List<ToDoListTable> findAllParentToDos();

    @Query("SELECT u FROM ToDoListTable u")
    List<ToDoListTable> findAllToDos();


    //gets the list of expiry dates for a given user
    @Query("SELECT u.expiryDate FROM ToDoListTable u WHERE u.userId = :userId")
    List<Date> getExpiryDateList(@Param("userId") Integer userId);


    //gets the list of todos for a given expiry date and a given user.
    @Query("SELECT u FROM ToDoListTable u WHERE ((u.userId=:userId) AND (u.expiryDate=:expDate))")
    List<Object> findToDoGivenExp(@Param("userId") Integer userId, @Param("expDate") Date eDate);


    //get all todos for a given user.
    @Query("SELECT u FROM ToDoListTable u WHERE u.userId=:userId")
    List<ToDoListTable> findToDoList(@Param("userId") Integer userId);



    @Query("SELECT u.description, u.expiryDate FROM ToDoListTable u WHERE (u.isDone=:isDone AND u.userId=:searchTerm)")
    List<Object> findPendingToDoList(@Param("isDone") boolean isDone, @Param("searchTerm") Integer searchTerm);



    @Query("SELECT  u FROM ToDoListTable u WHERE u.Id=:givenId")
    List<Object> findToDo(@Param("givenId") Integer iD);



    @Transactional
    @Modifying
    @Query("UPDATE ToDoListTable SET isDone=true WHERE Id=:givenId")
    void setToDoDone(@Param("givenId") Integer iD);



    @Query("SELECT u FROM ToDoListTable u WHERE (u.expiryDate<:currDate AND u.userId=:givenId)")
    List<Object> findExpiredToDo(@Param("currDate") Date nowDate, @Param("givenId") Integer userId);



    @Query("SELECT u.expiryDate, u FROM ToDoListTable u WHERE u.userId =: givenId ORDER BY u.expiryDate")
    List<Object> listToDosByExp(@Param("givenId") Integer givenId);

    //****************************************************************************************************************

    @Query("SELECT COUNT(u) FROM ToDoListTable u WHERE u.userId=:userId")
    Integer getCountUserToDosR(@Param("userId") Integer userId);

    @Query("SELECT COUNT(u) FROM ToDoListTable u WHERE u.userId=:givenId AND isDone=false")
    Integer getCountUserPendToDosR(@Param("givenId") Integer givenId);

// This will be AUTO IMPLEMENTED by Spring into a Bean called userRepository
// CRUD refers Create, Read, Update, Delete
}
