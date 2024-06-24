package org.example;
import java.util.ArrayList;

public class Habit extends ListItem{
    private int habitTrackType; //for now always zero

    /**
     * Constructor for habits
     * @param name name of the limit
     * @param timeFrame time frame that the habit is tracked in
     * @param maxProgress how many checkboxes you have left in the given time frame
     * @param habitTrackType what kind of tracking is being used (e.g. checkbox/percentage bar/etc.)
     */
    public Habit (String name, int timeFrame, int maxProgress, int habitTrackType, Boolean isTracked, ArrayList<Checklist> lists, ItemPage itemPage) {
        this.name = name;
        this.timeFrame = timeFrame;
        this.maxProgress = maxProgress;
        this.habitTrackType = habitTrackType;
        setIsTracked(isTracked, lists);
        itemPage.addItem(this);
        isGoal = false;
    }

    /**
     * sets the new type the habit is tracked by
     * @param habitTrackType the new track type
     */
    public void setHabitTrackType(int habitTrackType) {
        this.habitTrackType = habitTrackType;
    }

    /**
     * toString method for Habit Objects
     * @return the Habit's name, time frame, max/current progress, assigned Checklist, the track type and if it's tracked or not
     */
    @Override
    public String toString() {
        String habitString = "";

        habitString += this.getName();
        habitString += ",";
        habitString += this.isTracked;
        habitString += ",";
        habitString += this.timeFrame;
        habitString += ",";
        habitString += this.maxProgress;
        habitString += ",";
        habitString += this.currentProgress;
        habitString += ",";
        habitString += this.habitTrackType;
        habitString += ",";
        habitString += this.isGoal;
        habitString += ";";

        return habitString;
    }
}
