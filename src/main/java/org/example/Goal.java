package org.example;
import java.util.ArrayList;

public class Goal extends ListItem{

    /**
     * Constructor for Goal
     * @param name name of the Goal
     * @param timeFrame time frame that the goals is tracked in
     * @param maxProgress how often you have to check off the goal to reach the required amount
     * @param trackType what kind of tracking is being used (e.g. checkbox/percentage bar/etc.)
     */
    public Goal (String name, int timeFrame, int maxProgress, int trackType, boolean isTracked, ArrayList<Checklist> lists, ItemPage itemPage) {
        this.setName(name);
        this.setTimeFrame(timeFrame);
        this.setMaxProgress(maxProgress);
        this.setTrackType(trackType);
        this.setIsTracked(isTracked, lists);
        this.setIsGoal(true);
        itemPage.addItem(this);
    }
}
