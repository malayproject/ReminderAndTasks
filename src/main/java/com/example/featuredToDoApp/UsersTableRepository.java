package com.example.featuredToDoApp;

import com.example.featuredToDoApp.mySQLTable.UsersTable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface UsersTableRepository extends CrudRepository<UsersTable, Integer>   {



    @Query(value = "SELECT u FROM UsersTable u")
    List<UsersTable> getAllUsers();


// This will be AUTO IMPLEMENTED by Spring into a Bean called userRepository
// CRUD refers Create, Read, Update, Delete
}
