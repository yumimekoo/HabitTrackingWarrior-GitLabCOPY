package org.example;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {

        ArrayList<Checklist> lists = new ArrayList<>();
        Checklist checklist1 = new Checklist("daily", 1);
        lists.add(checklist1);

        Goal g1 = new Goal("drink less coffee", 1, 1, 0,true, lists);

    }
}

