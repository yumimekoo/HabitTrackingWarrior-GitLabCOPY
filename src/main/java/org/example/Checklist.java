package org.example;
import java.util.ArrayList;


public class Checklist {
    private String name;
    private int refreshTime;
    private ArrayList<ListItem> listItems;

    /**
     * Constructor for Checklist
     * @param name the Name of the CheckList
     * @param refreshTime the time frame in which the list refreshes
     */
    public Checklist(String name, int refreshTime) {
        this.name = name;
        this.refreshTime = refreshTime;
        listItems = new ArrayList<>();
    }

    /**
     * sets the name for the checklist
     * @param name the new name of the checklist
     */
    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    /**
     * sets the refresh timer for the checklist
     * @param refreshTime the new refresh time for the checklist
     */
    public void setRefreshTime(int refreshTime) {
        this.refreshTime = refreshTime;
    }

    /**
     * gets the Checklists refreshTime
     * @return the lists refreshTime
     */
    public int getRefreshTime(){
        return this.refreshTime;
    }

    /**
     * adds a list item to the checklist
     * @param item the added list item
     */
    public void addListItem(ListItem item){
        this.listItems.add(item);
    }

    /**
     * removes a list item from the checklist
     * @param item the list item to be removed
     */
    public void removeListItem(ListItem item){
        this.listItems.remove(item);
        item.resetAssignedList();
    }

    /**
     * gets the list items assigned to this checklist
     * @return returns this checklist's ArrayList
     */
    public ArrayList<ListItem> getList(){
        return this.listItems;
    }

    /**
     * adds 1 to the currentProgress value of a given list item, if it doesn't exceed maxProgress
     * @param item the given list item
     */
    public void addCheck(ListItem item) {
        if (item.getMaxProgress() > item.getCurrentProgress()) {
            item.addProgress();
        }
    }

    /**
     * removes 1 of the currentProgress value of a given list item, if it doesn't get smaller than 0
     * @param item the given list item
     */
    public void removeCheck(ListItem item) {
        if (0 < item.getCurrentProgress()) {
            item.reduceProgress();
        }
    }

    @Override
    public String toString(){
        String checklistString = "";

        checklistString += this.name;
        checklistString += ", ";
        checklistString += this.refreshTime;
        checklistString += ", {";
        for(int i = 0; i < this.listItems.size(); i++) {
            if(i > 0){
                checklistString += ", ";
            }
            checklistString += this.listItems.get(i).getName();
        }
        checklistString += "}";

        return checklistString;
    }
}