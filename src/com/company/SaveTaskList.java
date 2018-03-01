package com.company;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import static com.company.utils.Colors.GREEN_BOLD;
import static com.company.utils.Colors.RED_BOLD;
import static com.company.utils.Colors.RESET;

public class SaveTaskList {
    MainMenu mainMenu;
    private String fileName;
    private List<Task> list;
    SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yy");


    public SaveTaskList(MainMenu mainMenu, String fileName, List<Task> list) {
        this.mainMenu = mainMenu;
        this.fileName = fileName;
        this.list = list;
        setList(this.list);
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public List<Task> getList() {
        return list;
    }

    public void setList(List<Task> list) {
        this.list = list;
    }

    protected void saveTask(){
        if(!getList().isEmpty()){
            try{
                File file = new File(getFileName());
                FileWriter fileWriter = new FileWriter(file, true);
                for (Task task:
                 list) {
                    fileWriter.write(task.getTitle()+", "+task.getDescription()+", "+
                            dateFormat.format(task.getDateCreated())+", "+dateFormat.format(task.getDueDate())+", "+task.isCompleted()+"\n");
                    fileWriter.flush();
                    fileWriter.close();
                    System.out.println(GREEN_BOLD+"Your list has been saved"+RESET);
                    mainMenu.startMenu();
                }
            } catch (Exception e) {
                System.out.println(RED_BOLD+"file not found"+RESET);
            }
        }else{
            System.out.println(RED_BOLD+"nothing to save"+RESET);
        }
    }

    protected void saveToTaskList(){
            try{
                String delimiter = ",";
                String newLine = "";
                File file = new File(getFileName());
                boolean isCompleted;
                BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
                while ((newLine = bufferedReader.readLine()) != null){
                    String data [] = newLine.split(delimiter);
                    Date date1 = dateFormat.parse(data[2]);
                    Date date2 = dateFormat.parse(data[3]);
                    if(data[4].trim().toLowerCase().equals("true")){
                        isCompleted = true;
                    }else{
                        isCompleted = false;
                    }
                    getList().clear();
                    getList().add(new Task(data[0], data[1], date1, date2, isCompleted));
                }

                System.out.println(GREEN_BOLD+"Your saved tasks have been retrieved"+RESET);
                mainMenu.startMenu();


            }catch (Exception e){
                System.out.println("file not found");
                mainMenu.startMenu();
            }
    }


}
