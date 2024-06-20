package org.example;

public class Habit extends ListItem{
    private int habitTrackType;

    /**
     * Constructor for habits
     * @param name name of the limit
     * @param timeFrame time frame that the habit is tracked in
     * @param maxProgress how many checkboxes you have left in the given time frame
     * @param habitTrackType what kind of tracking is being used (e.g. checkbox/percentage bar/etc.)
     */
    public Habit (String name, int timeFrame, int maxProgress, int habitTrackType) {
        this.isTracked = true;
        this.name = name;
        this.timeFrame = timeFrame;
        this.maxProgress = maxProgress;
        this.habitTrackType = habitTrackType;
    }

    /**
     * sets the new type the habit is tracked by
     * @param habitTrackType the new track type
     */
    public void setHabitTrackType(int habitTrackType) {
        this.habitTrackType = habitTrackType;
    }
}
