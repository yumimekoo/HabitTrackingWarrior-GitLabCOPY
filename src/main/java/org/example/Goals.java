package org.example;

public class Goals extends ListItem{
    private int goalPerFrame;
    private int goalTrackType;

    /**
     * Constructor for Goals
     * @param name name of the Goals
     * @param timeFrame time frame that the goals is tracked in
     * @param goalPerFrame how often you have to check off the goal to reach the required amount
     * @param goalTrackType what kind of tracking is being used (e.g. checkbox/percentage bar/etc.)
     */
    public Goals (String name, int timeFrame, int goalPerFrame, int goalTrackType) {
        this.isTracked = true;
        this.name = name;
        this.timeFrame = timeFrame;
        this.goalPerFrame = goalPerFrame;
        this.goalTrackType = goalTrackType;
    }

    /**
     * sets the goal amount per refresh time frame
     * @param goalPerFrame the new goal amount
     */
    public void setGoalPerFrame(int goalPerFrame) {
        this.goalPerFrame = goalPerFrame;
    }

    /**
     * sets the new type the goal is tracked by
     * @param goalTrackType the new track type
     */
    public void setGoalTrackType(int goalTrackType) {
        this.goalTrackType = goalTrackType;
    }
}
