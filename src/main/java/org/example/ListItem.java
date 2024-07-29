package org.example;
import java.util.ArrayList;

public class ListItem {
    private String name;
    private Boolean isTracked; //this is true by default
    private int timeFrame;
    private int maxProgress;
    private int currentProgress;
    private Checklist assignedList;
    private Boolean isGoal;
    private int trackType; //for now always zero
    private boolean isFinished = false;

    /**
     * Constructor for a ListItem
     * @param name name of the ListItem
     * @param timeFrame time frame that the ListItem is tracked in
     * @param maxProgress how often you have to check off the ListItem to reach the required amount
     * @param trackType what kind of tracking is being used (e.g. checkbox/percentage bar/etc.)
     */
    public ListItem (String name, int timeFrame, int maxProgress, int trackType, boolean isTracked, ArrayList<Checklist> lists, ItemPage itemPage, boolean isGoal) {
        this.setName(name);
        this.setTimeFrame(timeFrame);
        this.setMaxProgress(maxProgress);
        this.setTrackType(trackType);
        this.setIsTracked(isTracked, lists);
        this.setIsGoal(isGoal);
        itemPage.addItem(this);
    }

    /**
     * sets the time frame of the list item
     * @param timeFrame is the new time frame
     */
    public void setTimeFrame(int timeFrame) {
        this.timeFrame = timeFrame;
    }

    /**
     * sets the name of the list item
     * @param name is the new name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * sets whether the list item is being tracked or not
     * if isTracked is true, it uses the assignListByTimeFrame method
     * if not, it checks if it has an assigned list, if it has, assignedList is set to null
     * @param isTracked the new value for this.isTracked
     */
    public void setIsTracked(Boolean isTracked, ArrayList<Checklist> lists) {
        this.isTracked = isTracked;

        if(this.isTracked) {
            this.assignListByTimeFrame(this.timeFrame, lists);
        } else if (this.assignedList != null) {
            this.resetAssignedList();
        }
    }

    /**
     * specifically sets the isTracked value to false
     */
    public void removeTracking() {
        this.isTracked = false;
    }

    /**
     * checks if the list item is being tracked or not
     * @return the isTracked field
     */
    public Boolean getIsTracked(){
        return this.isTracked;
    }

    /**
     * assigns a Checklist to a ListItem based on it's timeFrame
     * if there is no Checklist that has a matching refreshTime, isTracked will be set to false
     * @param timeFrame the time frame in which the ListItem is supposed to be tracked in
     * @param lists the ArrayList of Checklists the method uses to search for a suitable Checklist
     */
    public void assignListByTimeFrame(int timeFrame, ArrayList<Checklist> lists) {
        for (Checklist list : lists) {
            if (timeFrame == list.getTimeFrame()) {
                this.setAssignedList(list);
                break;
            }
        }
        if (this.assignedList == null) {
            this.isTracked = false;
            System.out.print("There exists no list with a suitable time frame (");
            System.out.print(this.getName());
            System.out.println(")");
        }
    }

    /**
     * sets the goal amount per refresh time frame
     * @param maxProgress the new goal amount
     */
    public void setMaxProgress(int maxProgress) {
        this.maxProgress = maxProgress;
    }

    /**
     * gets the maxProgress Value
     * @return maxProgress
     */
    public int getMaxProgress() {
        return maxProgress;
    }

    /**
     * adds 1 to the currentProgress
     */
    public void addProgress() {
        this.currentProgress += 1;
    }

    /**
     * removes 1 from the currentProgress
     */
    public void reduceProgress() {
        this.currentProgress -= 1;
    }

    /**
     * gets the currentProgress
     * @return currentProgress
     */
    public int getCurrentProgress() {
        return currentProgress;
    }

    /**
     * gets the timeFrame value
     * @return timeFrame
     */
    public int getTimeFrame() {
        return this.timeFrame;
    }

    /**
     * assigns the checklist to this list item
     * @param checklist the given checklist to be assigned
     */
    public void setAssignedList(Checklist checklist) {
        checklist.addListItem(this);
        this.assignedList = checklist;
    }

    /**
     * "resets" the assigned list, sets the value tu null and removes the list item from the Checklist's listItems Array
     */
    public void resetAssignedList(){
        this.assignedList = null;
        this.removeTracking();
    }

    /**
     * gets the ListItem's assigned list
     * @return the assigned list
     */
    public Checklist getAssignedList() {
        return this.assignedList;
    }

    /**
     * gets the ListItem's name
     * @return the name
     */
    public String getName(){
        return this.name;
    }

    /**
     * sets if the ListItem is being tracked or not
     * @param isGoal the new value
     */
    public void setIsGoal(Boolean isGoal) {
        this.isGoal = isGoal;
    }

    /**
     * gets if the ListItem is being Tracked or not
     * @return the isTracked value
     */
    public Boolean getIsGoal() {
        return this.isGoal;
    }

    public int getTrackType(){
        return this.trackType;
    }

    public void setTrackType(int trackType) {
        this.trackType = trackType;
    }

    /**
     * toString method for Goal Objects
     * @return the ListItem's name, time frame, max/current progress, assigned Checklist, the track type and if it's tracked or not
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
        goalString += this.getTrackType();
        goalString += ",";
        goalString += this.getIsGoal();
        goalString += ";";

        return goalString;
    }

    /**
     * switches the value of isFinished
     */
    public void switchIsFinished(){
        this.isFinished = !isFinished;
    }

    /**
     * getter for isFinished
     * @return isFinished
     */
    public boolean getIsFinished(){
        return this.isFinished;
    }
}
