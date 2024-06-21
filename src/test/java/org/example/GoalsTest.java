package org.example;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class GoalsTest {
    Goal goal1;

    @Test
    void getTimeFrame() {
        ArrayList<Checklist> lists = new ArrayList<>();
        Checklist checklist1 = new Checklist("daily", 1);
        lists.add(checklist1);
        goal1 = new Goal("Goal1", 7, 3, 0, true, lists);
        assertEquals(7, goal1.getTimeFrame());
        assertNull(goal1.getAssignedList());
    }
}