package com.company;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.Scanner;

import static com.company.utils.Colors.*;

public class EditMenu{
    private Task task;
    private String title;
    private String description;
    private Date dueDate;
    private Scanner input = new Scanner(System.in);
    MainMenu mainMenu;


    public EditMenu(Task task, MainMenu mainMenu) {
        this.task = task;
        this.mainMenu = mainMenu;
    }

    public Task getTask() {
        return task;
    }

    public void setTask(Task task) {
        this.task = task;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle() {
        System.out.println(PURPLE_BOLD+"Enter a new title for you task"+RESET);
        if(input.hasNextLine()){
            String t = input.nextLine();
            if(t.length() > 0){
                this.title = t;
                getTask().setTitle(getTitle());
                System.out.println(GREEN_BOLD+"You renamed to "+getTitle()+RESET);
            }else{
                System.out.println(RED_BOLD+"Enter input"+RESET);
                setTitle();
            }
        }else{
            System.out.println(RED_BOLD+"Enter valid title"+RESET);
            setTitle();
        }
    }

    public String getDescription() {
        return description;
    }

    public void setDescription() {
        System.out.println(PURPLE_BOLD+"Enter a new description for you task"+RESET);
        if(input.hasNextLine()){
            String desc = input.nextLine();
            if(desc.length() > 0){
                this.description = desc;
                getTask().setDescription(getDescription());
                System.out.println(GREEN_BOLD+"description changed to "+getDescription()+RESET);
            }else{
                System.out.println(RED_BOLD+"Enter input"+RESET);
                setDescription();
            }
        }else{
            System.out.println(RED_BOLD+"Enter valid description"+RESET);
            setDescription();
        }
    }

    public Date getDueDate() {
        return dueDate;
    }

    public void setDueDate() {
        System.out.println(PURPLE_BOLD+"Enter a new due date for you task"+RESET);
        SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yy");
        if(input.hasNextLine()){
            String d = input.nextLine();
            if(d.length() > 0){
                try{
                    this.dueDate = dateFormat.parse(d);
                    task.setDueDate(getDueDate());
                    System.out.println(GREEN_BOLD+"due date changed to "+dateFormat.format(getDueDate())+RESET);

                }catch (ParseException pe){
                    System.out.println(RED_BOLD+"Enter date in MM/DD/YY format"+RESET);
                    setDueDate();
                }
            }else{
                System.out.println(RED_BOLD+"Enter input"+RESET);
                setDueDate();
            }
        }else{
            System.out.println(RED_BOLD+"Enter valid title"+RESET);
            setDueDate();
        }
    }

    protected void startEditMenu(){
            System.out.println(WHITE_BOLD+BLUE_BACKGROUND+"What would you like to edit about "+ getTask().getTitle()+RESET);
            System.out.println(BLUE_BOLD+"1. Title\n" +
                    "2. Description\n" +
                    "3. Due Date\n" +
                    "4. Main Menu"+RESET);
            try {
                if(input.hasNextInt()){
                    switch (input.nextInt()){
                        case 1:
                            input.nextLine();
                            setTitle();
                            startEditMenu();
                            break;
                        case 2:
                            input.nextLine();
                            setDescription();
                            startEditMenu();
                            break;
                        case 3:
                            input.nextLine();
                            setDueDate();
                            startEditMenu();
                            break;
                        case 4:
                            mainMenu.startMenu();
                            break;
                        default:
                            System.out.println(RED_BOLD+"Enter number from 1 to 4"+RESET);
                            startEditMenu();
                            break;
                    }
                }else {
                    System.out.println(RED_BOLD+"Enter a number"+RESET);
                    input.nextLine();
                    startEditMenu();
                }
            }catch (InputMismatchException imfe){
                System.out.println("Enter valid input");
                startEditMenu();
}
    }}
