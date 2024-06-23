package org.example;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.io.File;
import java.io.FileNotFoundException;

public class Main {
    //returns the given txt file as a String
    public static String readFileAsString(String fileName) throws Exception {
        return new String(Files.readAllBytes(Paths.get(fileName)));
    }

    public static void main(String[] args) throws Exception {
        ListPage listPage = new ListPage();

        String data = readFileAsString(".\\src\\main\\java\\org\\example\\checklist_data.txt");
        //System.out.println(data);;

        Checklist checklist1 = new Checklist("daily", 1);
        listPage.addList(checklist1);

        Habit h1 = new Habit("drink less coffee", 1, 2, 0,true, listPage.getLists());
        Goal g1 = new Goal("work on software engineering project", 1, 1, 0,true, listPage.getLists());
        Goal g2 = new Goal("water plants", 1, 1, 0,true, listPage.getLists());

        System.out.println(listPage.toString());
        System.out.println(h1.toString());
        System.out.println(g1.toString());
    }
}

