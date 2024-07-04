package org.example;
import java.util.ArrayList;

public class Habit extends ListItem{

    /**
     * Constructor for habits
     * @param name name of the limit
     * @param timeFrame time frame that the habit is tracked in
     * @param maxProgress how many checkboxes you have left in the given time frame
     * @param trackType what kind of tracking is being used (e.g. checkbox/percentage bar/etc.)
     */
    public Habit (String name, int timeFrame, int maxProgress, int trackType, Boolean isTracked, ArrayList<Checklist> lists, ItemPage itemPage) {
        this.setName(name);
        this.setTimeFrame(timeFrame);
        this.setMaxProgress(maxProgress);
        this.setTrackType(trackType);
        this.setIsTracked(isTracked, lists);
        this.setIsGoal(false);
        itemPage.addItem(this);
    }
}
