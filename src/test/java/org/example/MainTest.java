package org.example;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MainTest {

    @Test
    void deleteItem() {
        ListPage listPage = new ListPage();
        ItemPage itemPage = new ItemPage();
        Checklist checklist = new Checklist("daily", 1);
        listPage.addList(checklist);
        Goal goal1 = new Goal("drink water", 1, 4, 0, true, listPage.getLists(), itemPage);
        assertEquals(goal1.getName(), checklist.getItem(0).getName());
        Main.deleteItem(itemPage, "drink water");
        assertEquals(0, checklist.getSize());
    }
}