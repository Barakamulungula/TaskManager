package com.company;
import java.util.Scanner;

/** Imports everything from the util package  */
import static com.company.utils.Colors.*;

public class MainMenu {

    /** Instantiate the Manager class*/
    private Manager manager = new Manager(this);
    private static Scanner input = new Scanner(System.in);

    /** Starting method for the TaskManager */
    protected void startMenu(){

        System.out.println("\n"+BLUE_BACKGROUND+BLACK_BOLD+"Welcome, to the task manager what would you like to do? "+RESET);
        System.out.println(BLUE_BOLD+"1. View all tasks\n" +
                "2. Add a task\n" +
                "3. Remove a task\n" +
                "4. Select a task\n" +
                "5. Edit a task\n" +
                "6. Mark a task completed\n" +
                "7. View Completed task\n" +
                "8. View Uncompleted task\n"+ RESET+
                RED_BOLD+ "9. Exit Program"+RESET);

        System.out.println(BLUE_BACKGROUND+BLACK_BOLD+"SELECT AN OPTION (1-9)                                  "+RESET);

        try{
            if(input.hasNextInt()){
                switch (input.nextInt()){
                    case 1:
                        manager.viewTask();
                        startMenu();
                        break;
                    case 2:
                        manager.createTask();
                        break;
                    case 3:
                        System.out.println(CYAN_BOLD+"You have chosen to remove a task"+RESET);
                        manager.removeTask();
                        break;
                    case 4:
                        System.out.println(CYAN_BOLD+"You have chosen to select a task"+RESET);
                        manager.selectATask();
                        break;
                    case 5:

                        break;
                    case 6:
                        manager.markCompleted();
                        break;
                    case 7:
                        manager.viewCompletedTask();
                        break;
                    case 8:
                        manager.viewUncompletedTask();
                        break;
                    case 9:
                        System.out.println(RED_BOLD+"You left the program");
                        System.exit(0);
                        break;
                    default:
                        System.out.println(RED_BOLD+"Enter a number from 1 to 9"+RESET);
                        startMenu();
                        break;
                }
            }else{
                input.nextLine();
                System.out.println(WHITE_BACKGROUND+RED_BOLD+"ENTER NUMBER                                            "+RESET);
                startMenu();
            }

        }catch (Exception e){
            System.out.println(WHITE_BACKGROUND+RED_BOLD+"ENTER NUMBER                                                "+RESET);
            startMenu();
        }

    }
}
