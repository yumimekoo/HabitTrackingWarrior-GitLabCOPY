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
    public Goal (String name, int timeFrame, int maxProgress, int goalTrackType, boolean isTracked, ArrayList<Checklist> lists, ItemPage itemPage) {
        this.setName(name);
        this.setTimeFrame(timeFrame);
        this.setMaxProgress(maxProgress);
        this.goalTrackType = goalTrackType;
        this.setIsTracked(isTracked, lists);
        this.setIsGoal(true);
        itemPage.addItem(this);
    }

    /**
     * sets the new type the goal is tracked by
     * @param goalTrackType the new track type
     */
    public void setGoalTrackType(int goalTrackType) {
        this.goalTrackType = goalTrackType;
    }

    /**
     * toString method for Goal Objects
     * @return the Goal's name, time frame, max/current progress, assigned Checklist, the track type and if it's tracked or not
     */
    @Override
    public String toString() {
        String goalString = "";

        goalString += this.getName();
        goalString += ",";
        goalString += this.getIsTracked();
        goalString += ",";
        goalString += this.getTimeFrame();
        goalString += ",";
        goalString += this.getMaxProgress();
        goalString += ",";
        goalString += this.getCurrentProgress();
        goalString += ",";
        goalString += this.goalTrackType;
        goalString += ",";
        goalString += this.getIsGoal();
        goalString += ";";

        return goalString;
    }
}
