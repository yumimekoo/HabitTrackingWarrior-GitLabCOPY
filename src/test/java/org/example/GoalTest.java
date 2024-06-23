package org.example;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class GoalsTest {
    Goal goal1;

    @Test
    void getTimeFrame() {
        ArrayList<Checklist> lists = new ArrayList<>(); // creating new ArrayList of Checklists
        Checklist checklist1 = new Checklist("daily", 7); // creating new Checklist with refreshTime 7...
        lists.add(checklist1); // ... and adding it to the Checklist list
        System.out.println(lists.getFirst().getRefreshTime()); // ensuring that refreshTime is 7

        goal1 = new Goal("Goal1", 7, 3, 0, true, lists); // creating new Goal with timeFrame 7
        System.out.println(goal1.getTimeFrame()); // ensuring that timeFrame is 7
        System.out.println(goal1.getTimeFrame() == checklist1.getRefreshTime());
        System.out.println(goal1.getAssignedList());
    }
}