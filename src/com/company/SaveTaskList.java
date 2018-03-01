package com.company;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.List;

import static com.company.utils.Colors.GREEN_BOLD;
import static com.company.utils.Colors.RED_BOLD;
import static com.company.utils.Colors.RESET;

public class SaveTaskList {
    MainMenu mainMenu;
    private String fileName;
    private List<Task> list;


    public SaveTaskList(MainMenu mainMenu, String fileName, List<Task> list) {
        this.mainMenu = mainMenu;
        this.fileName = fileName;
        this.list = list;
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
                            task.getDateCreated()+", "+task.getDueDate()+", "+task.isCompleted()+"\n");
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
            File file = new File(getFileName());

        }catch (Exception e){
            System.out.println("file not found");
        }
    }


}
