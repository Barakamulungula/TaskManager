package com.company;

import java.util.Scanner;

import static com.company.utils.Colors.*;

public class MainMenu {
    private Scanner input = new Scanner(System.in);
    Manager manager = new Manager(this);
    SaveTaskList saveTaskList = new SaveTaskList(this,"/Users/barakamulungula/IdeaProjects/TaskManager/src/com/company/data.txt", Manager.taskList);

    protected void startMenu(){
        System.out.println(BLUE_BACKGROUND+BLACK_BOLD+"Welcome, to the task manager what would you like to do? "+RESET);
        System.out.println(BLUE_BOLD+"1. View all tasks\n" +
                "2. Add a task\n" +
                "3. Remove a task\n" +
                "4. Select a task\n" +
                "5. Edit a task\n" +
                "6. Mark a task completed\n" +
                "7. View Completed task\n" +
                "8. View Uncompleted task\n"+
                "9. Save your task list\n"+
                "10. Retrieve saved tasks\n"+RESET+
                RED_BOLD+ "11. Exit Program"+RESET);

        System.out.println(BLUE_BACKGROUND+BLACK_BOLD+"SELECT AN OPTION (1-9)                                  "+RESET);
        if(input.hasNextInt()){
            switch (input.nextInt()){
                case 1:
                    manager.viewTask();
                    startMenu();
                    break;
                case 2:
                    manager.addTask();
                    break;
                case 3:
                    manager.removeTask();
                    break;
                case 4:
                    manager.selectATask();
                    break;
                case 5:
                    input.nextLine();
                    manager.editTask();
                    input.nextLine();
                    break;
                case 6:
                    input.nextLine();
                    manager.markCompleted();
                    input.nextLine();
                    break;
                case 7:
                    manager.viewCompletedTask();
                    break;
                case 8:
                    manager.viewUncompletedTask();
                    break;
                case 9:
                    saveTaskList.saveTask();
                    break;
                case 10:
                    saveTaskList.saveToTaskList();
                    break;
                case 11:
                    System.out.println(RED_BOLD+"You have left the program"+RESET);
                    System.exit(0);
                    break;
                default:
                    System.out.println(RED_BOLD+" Enter number from 1 to 9"+RESET);
                    startMenu();
                    break;
            }
        }else {
            System.out.println("\n"+RED_BOLD+"Enter valid input"+RESET);
            input.nextLine();
            startMenu();
        }
    }
}
