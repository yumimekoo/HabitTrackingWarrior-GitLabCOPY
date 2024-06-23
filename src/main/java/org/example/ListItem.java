package org.example;
import java.util.ArrayList;

public class ListItem {
    // THESE NEED TO BE CHANGED TO PRIVATE AND HAVE GETTERS/SETTERS ADDED
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
     * assigns a Checklist to a ListItem based on it's timeFrame
     * if there is no Checklist that has a matching refreshTime, isTracked will be set to false
     * @param timeFrame the time frame in which the ListItem is supposed to be tracked in
     * @param lists the ArrayList of Checklists the method uses to search for a suitable Checklist
     */
    public void assignListByTimeFrame(int timeFrame, ArrayList<Checklist> lists) {
        for (Checklist list : lists) {
            if (timeFrame == list.getRefreshTime()) {
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
        this.assignedList.removeListItem(this);
        this.assignedList = null;
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
}
