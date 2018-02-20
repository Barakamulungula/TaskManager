package com.company;
import static com.company.utils.Colors.*;


import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Manager {
    private List<Task> tasklist = new ArrayList<Task>();
    private String title;
    private String description;

    private static Scanner input = new Scanner(System.in);

    private MainMenu mainMenu;

    private Manager(MainMenu mainMenu) {
        this.mainMenu = mainMenu;
    }

    protected void viewTask(){
        int num = 1;
        if(!tasklist.isEmpty()){
            System.out.println(CYAN_BOLD+"\nHere is a list of all your tasks"+RESET);
            for(Task t: tasklist){
                System.out.println(GREEN_BOLD+num+". "+t.getTitle()+RESET);
                num++;
            }
        }else{
            System.out.println(RED_BACKGROUND+BLUE_BOLD+"\"You currently have nothing in your tasklist\"           "+RESET);
        }
        System.out.println();
        mainMenu.startMenu();
    }

    protected void createTask(){
        System.out.println(CYAN_BOLD+"You have chosen to add a task"+RESET);
        setTitle();
        setDescription();
        System.out.println(CYAN_BOLD+"You have added "+title+" task to your task list\n"+RESET);
        String date = "";
        tasklist.add(new Task(title, description, date));
        mainMenu.startMenu();

    }

    private void setTitle() {
        System.out.println(PURPLE_BOLD+"Name your task:");
        if(input.hasNextLine()){
            String t = input.nextLine();
            if(t.length() > 0){
                title = t;
            }else {
                System.out.println(RED_BOLD+"Enter input");
                setTitle();
            }
        }
    }

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
}
