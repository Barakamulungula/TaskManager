package com.company;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import static com.company.utils.Colors.*;

public class Manager {
    private Scanner input = new Scanner(System.in);
    static List<Task> taskList = new ArrayList<Task>();
    MainMenu mainMenu;
    SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yy");
    EditMenu editMenu;


    private String title;
    private String description;
    private Date date1;

    public Manager(MainMenu mainMenu) {
        this.mainMenu = mainMenu;
    }

    protected void viewTask(){
        int num = 1;
        /** checks if task list is empty prints "You currently have nothing in your tasklist" if nothing is in task list**/
        if(!taskList.isEmpty()){
            System.out.println(CYAN_BOLD+CYAN_UNDERLINED+"\n   Here is a list of all your tasks   "+RESET);
            /** the loop gets every task object in tasklist array*/
            for(Task t: taskList){
                /** Checks if the current task is completed */
                if(!t.isCompleted()) {
                    System.out.println(GREEN_BOLD + num + ". " + t.getTitle()+RESET +
                            CYAN_BOLD+"\tDue Date: "+dateFormat.format(t.getDueDate())+" "+RESET+
                            RED_BOLD+"\t|not completed"+ RESET);
                }else{
                    System.out.println(GREEN_BOLD + num + ". " + t.getTitle() + RESET+
                            CYAN_BOLD+"\tDue Date: "+dateFormat.format(t.getDueDate())+RESET+
                            GREEN_BOLD+"\t|completed"+ RESET);
                }
                num++;
            }
        }else{
            System.out.println(RED_BOLD+"\"You currently have nothing in your tasklist\"           "+RESET);
        }
        System.out.println();
    }


    protected void addTask(){
        setTitle();
        setDescription();
        setDueDate();
        System.out.println(GREEN_BOLD+title+" added to your task list\n"+RESET);
        taskList.add(new Task(title,description,date1));
        mainMenu.startMenu();


    }


    protected void setTitle() {
        System.out.println(PURPLE_BOLD+"Name your task:");
        /** return true if there is another line of input*/
        if(input.hasNextLine()){
            String t = input.nextLine();
            /** Checks if the user entered any input*/
            if(t.length() > 0){
                title = t;
            }else {
                System.out.println(RED_BOLD+"Enter input");
                setTitle();
            }
        }
    }

    /** The method takes in user input and sets a description for the task. Also handles any error in user input*/
    protected void setDescription(){
        System.out.println(PURPLE_BOLD+"Give your task a description:");
        if(input.hasNextLine()){
            String d = input.nextLine();
            if(d.length() > 0){
                description = d;
            }else {
                System.out.println(RED_BOLD+"Enter input");
                setDescription();
            }
        }

    }

    public void setDueDate() {
        System.out.println(PURPLE_BOLD+"What date do you need the task done use (MM/DD/YY) e.g '02/19/18' format:"+RESET);
        SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yy");
        if(input.hasNextLine()){
            String d = input.nextLine();
            if(d.length() > 0){
                try{
                    date1 = dateFormat.parse(d);

                }catch (ParseException pe){
                    System.out.println(RED_BOLD+"Enter date in MM/DD/YY format"+RESET);
                    setDueDate();
                }
            }else{
                System.out.println(RED_BOLD+"Enter input"+RESET);
                setDueDate();
            }
        }else{
            System.out.println("Enter valid date");
            setDueDate();
        }
    }


    protected void removeTask(){
        viewTask();
        if(!taskList.isEmpty()){
            System.out.println(PURPLE_BOLD+"Choose a task to remove"+RESET);
            if(input.hasNextInt()){
                int num = input.nextInt();
                if(num > 0 &&  num <= taskList.size()){
                    System.out.println(GREEN_BOLD+"You have removed "+taskList.get(num-1).getTitle()+RESET);
                    taskList.remove(num-1);
                    input.nextLine();
                    mainMenu.startMenu();

                }else {
                    System.out.println("Enter a number that corresponds with the task");
                    input.nextLine();
                    removeTask();
                }
            }else{
                System.out.println(RED_BOLD+"enter input"+RESET);
                input.nextLine();
                removeTask();
            }
        }else{
            mainMenu.startMenu();
        }
    }

    protected void selectATask() {
        viewTask();
        if (!taskList.isEmpty()) {
            System.out.println(PURPLE_BOLD + "Choose a task to select" + RESET);
            if (input.hasNextInt()) {
                int index = input.nextInt();
                if(index > 0 && index <= taskList.size()){
                    index = index -1;
                    String d1 = dateFormat.format(taskList.get(index).getDateCreated());
                    String d2 = dateFormat.format(taskList.get(index).getDueDate());

                    System.out.println(YELLOW_BOLD + "Name: " + taskList.get(index).getTitle() + "\n" +
                        "Description: " + taskList.get(index).getDescription() + "\n" +
                        "Date Created: " + d1 + "\n" +
                        "Due Date: " + d2 + "\n" +
                        "Completed: " + taskList.get(index).isCompleted() + RESET);
                    mainMenu.startMenu();
                }else {
                    System.out.println(RED_UNDERLINED+RED_BOLD+"Enter the number for the task you wish to select"+RESET);
                    input.nextLine();
                    selectATask();
                }
            } else {
                System.out.println(RED_BOLD + "enter a number" + RESET);
                input.nextLine();
                selectATask();
            }
        }else{
            mainMenu.startMenu();
        }
    }

    protected void editTask(){
        viewTask();
        if(!taskList.isEmpty()){
            int n;
            System.out.println("What task would you like to edit");
            if(input.hasNextInt()){
                n = input.nextInt();
                if(n > 0 && n <= taskList.size()){
                    editMenu = new EditMenu(taskList.get(n-1),mainMenu);
                    editMenu.startEditMenu();
                }
                }else{
                System.out.println(RED_BOLD+"Enter a number"+RESET);
                input.nextLine();
                editTask();
            }
            }else{
                input.nextLine();
                System.out.println(RED_UNDERLINED+RED_BOLD+"Enter a number"+RESET);
                editTask();
            }
        }

    /** Marks a task completed (Changes the completed boolean variable to true for a task object in the tasklist)*/
    protected void markCompleted(){
        viewTask();
        if(!taskList.isEmpty()){
            System.out.println(PURPLE_BOLD+"Pick the task you wish to mark completed"+RESET);
            if(input.hasNextInt()){
                int index = input.nextInt();
                if(index > 0 && index <= taskList.size()) {
                    index = index -1;
                    if (taskList.get(index).isCompleted()) {
                        System.out.println(GREEN_BOLD_BRIGHT + GREEN_UNDERLINED + taskList.get(index).getTitle() + " is already marked completed");
                        mainMenu.startMenu();
                    } else {
                        //set the private completed boolean variable to true
                        taskList.get(index).setCompleted(true);
                        System.out.println(GREEN_BOLD + taskList.get(index).getTitle() + " has been marked completed");
                        mainMenu.startMenu();
                    }
                }else {
                    System.out.println(RED_BOLD+"Enter the number for a task you wish to mark completed"+RESET);
                    input.nextLine();
                    markCompleted();
                }

            }else{
                System.out.println(RED_BOLD+"Enter valid input"+RESET);
                input.nextLine();
                markCompleted();
            }
        }else {
            mainMenu.startMenu();
        }
    }

    /** Prints out all all objects in tasklist array if variable completed is marked true*/
    protected void viewCompletedTask(){
        if(!taskList.isEmpty()){
            int num = 1;
            for(Task t: taskList){
                if(t.isCompleted()){
                    System.out.println(CYAN_BOLD+CYAN_UNDERLINED+"Here is a list of your completed task"+RESET);
                    System.out.println(GREEN_BOLD + num + ". " + t.getTitle() + RESET+
                            CYAN_BOLD+"\tDue Date: "+dateFormat.format(t.getDueDate())+RESET+
                            GREEN_BOLD+"\t|completed"+ RESET);
                    num++;
                }
                if(num == 1){
                    System.out.println(RED_BOLD+"You have no completed tasks"+RESET);
                }
                mainMenu.startMenu();
            }
        }else {
            System.out.println(RED_BOLD+"You no tasks in your task list"+RESET);
            mainMenu.startMenu();
        }
    }

    protected void viewUncompletedTask(){
        if(!taskList.isEmpty()){
            int num = 1;
            for(Task t: taskList){
                if(!t.isCompleted()){
                    System.out.println(CYAN_BOLD+CYAN_UNDERLINED+"Here is a list of things you need to do"+RESET);
                    System.out.println(GREEN_BOLD + num + ". " + t.getTitle() + RESET+
                            CYAN_BOLD+"\tDue Date: "+dateFormat.format(t.getDueDate())+RESET+
                            RED_BOLD+"\t|not completed"+ RESET);
                    num++;
                }
                if(num == 1){
                    System.out.println(GREEN_BOLD+"Wazoo! you have everything done."+RESET);
                }
                mainMenu.startMenu();
            }
        }else {
            System.out.println(GREEN_BOLD+"There is nothing in your task list"+RESET);
            mainMenu.startMenu();
        }
    }

}

