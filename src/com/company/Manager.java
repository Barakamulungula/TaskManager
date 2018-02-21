package com.company;
import static com.company.utils.Colors.*;


import java.text.SimpleDateFormat;
import java.util.*;
import java.text.ParseException;
public class Manager {

    /** List to save task */
    private List<Task> taskList = new ArrayList<Task>();

    private String title;
    private String description;
    private Date date1;
    private int index;
    private static Scanner input = new Scanner(System.in);

    //format for date object
    private SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yy");

    private MainMenu mainMenu;

    /** Constructor Manager (Use one instance of the Mainmenu class*/
    protected Manager(MainMenu mainMenu) {
        this.mainMenu = mainMenu;
    }

    /**The method checks if the user enters a number within task list value range*/
    private void select(int num, String error){
        boolean done = false;
        do {
            if(num > 0 && num <= taskList.size()){
                index = num-1;
            }
            else{
                System.out.println(RED_BOLD + error + RESET);
                done = true;
            }

        }while (done);

    }

    /** This method prints out the task currently in your library. "It prints nothing when you have nothing in task list*/
    protected void viewTask(){
        int num = 1;
        /** checks if task list is empty prints "You currently have nothing in your tasklist" if nothing is in task list**/
        if(!taskList.isEmpty()){
            System.out.println(CYAN_BOLD+CYAN_UNDERLINED+"\n   Here is a list of all your tasks   "+RESET);
            /** the loop gets every task object in tasklist array*/
            for(Task t: taskList){
                /** Checks if the current task is completed */
                if(!t.getCompleted()) {
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

    /** This method adds a new task object to tasklist array*/
    protected void createTask(){
        System.out.println(CYAN_BOLD+"You have chosen to add a task"+RESET);
        setTitle();
        setDescription();
        setDate();
        Calendar calendar = Calendar.getInstance();
        System.out.println(CYAN_BOLD+"You have added "+title+" task to your task list\n"+RESET);
        taskList.add(new Task(title, description, date1, calendar.getTime()));
        mainMenu.startMenu();

    }

    /** The method set a title for the task. Also handles any error in user input*/
    private void setTitle() {
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
    private void setDescription(){
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

    /** The method takes in user input and sets a date for the task . Also handles any error in user input*/
    private void setDate(){
        System.out.println(PURPLE_BOLD+"What date do you need the task done use (MM/DD/YY) e.g '02/19/18' format:");
        if(input.hasNextLine()){
            String dt = input.nextLine();
            if(dt.length() > 0){
                dateHandler(dt);
            }else {
                System.out.println(RED_BOLD+"Enter input"+RESET);
                setDate();
            }
        }
    }
    /** set the date*/
    private void dateHandler(String d){
        try{
            date1 = dateFormat.parse(d);

        }catch (ParseException pe){
            System.out.println(RED_BOLD+"Enter date in MM/DD/YY format"+RESET);
            setDate();
        }
    }

    /** removeTask removes a task from the tasklist array */
    protected void removeTask(){
        viewTask();
        if(!taskList.isEmpty()){
            if(input.hasNextInt()){
                System.out.println(PURPLE_BOLD+"Choose a task to remove"+RESET);
                //uses the select handle errors
                select(input.nextInt(),"Enter a number");
                System.out.println(GREEN_BOLD+"You have removed "+taskList.get(index).getTitle()+RESET);
                taskList.remove(index);
            }else{
                System.out.println(RED_BOLD+"enter input"+RESET);
                removeTask();
            }
        }else{
            mainMenu.startMenu();
        }
    }

    /** Lets user select a task and view its descriptive details*/
    protected void selectATask() {
        viewTask();
        if (!taskList.isEmpty()) {
            System.out.println(PURPLE_BOLD + "Choose a task to select" + RESET);
            if (input.hasNextInt()) {
                select(input.nextInt(), "Enter a number");
                String d1 = dateFormat.format(taskList.get(index).getDateCreated());
                String d2 = dateFormat.format(taskList.get(index).getDueDate());

                System.out.println(YELLOW_BOLD + "Name: " + taskList.get(index).getTitle() + "\n" +
                        "Description: " + taskList.get(index).getDescription() + "\n" +
                        "Date Created: " + d1 + "\n" +
                        "Due Date: " + d2 + "\n" +
                        "Completed: " + taskList.get(index).getCompleted() + RESET);
                mainMenu.startMenu();
            } else {
                System.out.println(RED_BOLD + "enter input" + RESET);
                selectATask();
            }
        }else{
            mainMenu.startMenu();
        }
    }

    /** Marks a task completed (Changes the completed boolean variable to true for a task object in the tasklist)*/
    protected void markCompleted(){
        viewTask();
        if(!taskList.isEmpty()){
            System.out.println(PURPLE_BOLD+"Pick the task you wish to mark completed"+RESET);
            if(input.hasNextInt()){
                select(input.nextInt(), "Please enter valid input");
                if(taskList.get(index).getCompleted()){
                    System.out.println(GREEN_BOLD_BRIGHT+GREEN_UNDERLINED+taskList.get(index).getTitle()+" is already marked completed");
                    mainMenu.startMenu();
                }else {
                    //set the private completed boolean variable to true
                    taskList.get(index).setCompleted(true);
                    System.out.println(GREEN_BOLD+taskList.get(index).getTitle()+" has been marked completed");
                    mainMenu.startMenu();
                }

            }else{
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
                if(t.getCompleted()){
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
                if(!t.getCompleted()){
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
