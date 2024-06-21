package org.example;
import java.util.ArrayList;

public class Goal extends ListItem{
    private int goalTrackType; //for now always zero

    /**
     * Constructor for Goal
     * @param name name of the Goal
     * @param timeFrame time frame that the goals is tracked in
     * @param maxProgress how often you have to check off the goal to reach the required amount
     * @param goalTrackType what kind of tracking is being used (e.g. checkbox/percentage bar/etc.)
     */
    public Goal (String name, int timeFrame, int maxProgress, int goalTrackType, Boolean isTracked, ArrayList<Checklist> lists) {
        setIsTracked(isTracked, lists);
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
