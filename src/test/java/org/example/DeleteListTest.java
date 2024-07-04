package org.example;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DeleteListTest {

    @Test
    void deleteList() {
        ListPage listPage = new ListPage();
        Checklist checklist = new Checklist("testList", 1);
        listPage.addList(checklist);
        Main.deleteList(listPage, "testList");
    }

}