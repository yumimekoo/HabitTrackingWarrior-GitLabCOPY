package org.example;

public class ListItem {
    public String name;
    public Boolean isTracked; //this is true by default
    public int timeFrame;
    public int maxProgress;
    public int currentProgress;
    public Checklist assignedList;

    /**
     * empty constructor
     */
    public ListItem(){

    }

    /**
     * deletes a list item (stub)
     */
    private void deleteItem() {

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
     * @param isTracked the new value for this.isTracked
     */
    public void setIsTracked(Boolean isTracked) {
        this.isTracked = isTracked;
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
        this.assignedList = checklist;
    }
}
