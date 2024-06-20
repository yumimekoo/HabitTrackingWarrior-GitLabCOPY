package org.example;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GoalsTest {
    Goals goal1;

    @Test
    void getTimeFrame() {
        goal1 = new Goals("Goal1", 7, 3, 0);
        assertEquals(7, goal1.getTimeFrame());
    }
}