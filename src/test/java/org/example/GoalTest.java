package org.example;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

class GoalTest {
    Goal goal1;
    Goal goal2;
    Habit habit1;

    @Test
    void goalTest() {
        ArrayList<Checklist> lists = new ArrayList<>(); // creating new ArrayList of Checklists
        Checklist checklist1 = new Checklist("daily", 7); // creating new Checklist with refreshTime 7...
        lists.add(checklist1); // ... and adding it to the Checklist list
        goal1 = new Goal("Goal1", 7, 3, 0, true, lists); // creating new Goal with timeFrame 7
        goal2 = new Goal("Goal2", 1, 3, 0, true, lists); // creating new Goal with timeFrame 1
        habit1 = new Habit("Habit1", 1, 3, 0, true, lists); // creating new Habit with timeFrame 1

    }
}