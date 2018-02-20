package com.company;

import com.company.utils.*;

import java.util.Scanner;

import static com.company.utils.Colors.*;

public class MainMenu {

    private Manager manager = new Manager(this);
    private static Scanner input = new Scanner(System.in);

    protected void startMenu(){

        System.out.println(BLUE_BACKGROUND+BLACK_BOLD+"Welcome, to the task manager what would you like to do? "+RESET);
        System.out.println(BLUE_BOLD+"1. View all tasks\n" +
                "2. Add a task\n" +
                "3. Remove a task\n" +
                "4. Select a task\n" +
                "5. Edit a task\n" +
                "6. Mark a task completed\n" +
                "7. View Completed task\n" +
                "8. View uncompleted task\n" +RESET+RED_BOLD+
                "9. Exit Program"+RESET);
        System.out.println(BLUE_BACKGROUND+BLACK_BOLD+"SELECT AN OPTION (1-9)                                  "+RESET);

        try{
            if(input.hasNextInt()){
                switch (input.nextInt()){
                    case 1:
                        manager.viewTask();
                        break;
                    case 2:
                        manager.createTask();
                        break;
                    case 3:
                        System.out.println(CYAN_BOLD+"You have chosen to add a task");
                        break;
                    case 4:
                        System.out.println(CYAN_BOLD+"You have chosen to add a task");
                        break;
                    case 5:
                        System.out.println(CYAN_BOLD+"You have chosen to add a task");
                        break;
                    case 6:
                        System.out.println(CYAN_BOLD+"You have chosen to add a task");
                        break;
                    case 7:
                        System.out.println(CYAN_BOLD+"You have chosen to add a task");
                        break;
                    case 8:
                        System.out.println(CYAN_BOLD+"You have chosen to add a task");
                        break;
                    case 9:
                        System.out.println(RED_BOLD+"You left the program");
                        System.exit(0);
                        break;
                    default:
                        startMenu();
                        break;
                }
            }else{
                startMenu();
            }

        }catch (Exception e){
            startMenu();
        }

    }
}
