package com.example.malay.todos.driver;

import com.example.malay.todos.logic.Manager;

import java.util.Scanner;

public class Driver {

    private static Scanner scan = new Scanner(System.in);

    public static void main(String[] args) {
        Manager m = new Manager("malay");
        String description;
        while(true) {
            System.out.println("Enter '1' for adding entry OR '2' for displaying List:");
            int i = scan.nextInt();
            scan.nextLine();
            if(i == 1) {
                System.out.println("Enter To Do Description:");
                description = scan.nextLine();
                //scan.nextLine();
                m.addToDo(description);
            }
            else if(i == 2) {
                m.getToDoList();
            }

            else System.out.println("invalid entry");

        }
    }
}
