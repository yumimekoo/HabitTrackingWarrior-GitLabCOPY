package org.example;

public class Goals extends ListItem{
    private int goalTrackType;

    /**
     * Constructor for Goals
     * @param name name of the Goals
     * @param timeFrame time frame that the goals is tracked in
     * @param maxProgress how often you have to check off the goal to reach the required amount
     * @param goalTrackType what kind of tracking is being used (e.g. checkbox/percentage bar/etc.)
     */
    public Goals (String name, int timeFrame, int maxProgress, int goalTrackType) {
        this.isTracked = true;
        this.name = name;
        this.timeFrame = timeFrame;
        this.maxProgress = maxProgress;
        this.goalTrackType = goalTrackType;
    }

    /**
     * sets the new type the goal is tracked by
     * @param goalTrackType the new track type
     */
    public void setGoalTrackType(int goalTrackType) {
        this.goalTrackType = goalTrackType;
    }

}
