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

    /**
     * gets the name of the give Checklist
     * @return the checklist's name
     */
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
     * removes a list item from the checklist, if it exists on the checklist
     * @param item the list item to be removed
     */
    public void removeListItem(ListItem item){
        for (int i = 0; i < this.getList().size(); i++) {
            if (this.getItem(i) == item) {
                this.getList().remove(i);
                break;
            }
        }
    }

    /**
     * gets the size of the checklist's arraylist of list items
     * @return the length of the arraylist
     */
    public int getSize() {
        return this.getList().size();
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

    /**
     * gets a list item from the checklist's arraylist from its index on the list
     * @param index the index of the list item
     * @return the list item at the given index
     */
    public ListItem getItem(int index) {
        return this.listItems.get(index);
    }

    /**
     * removes every list item on the checklist from the checklist's arraylist and sets their assigned list to null
     */
    public void purgeList() {
        for (int i = 0; i < this.getSize(); i++) {
            this.getItem(i).resetAssignedList();
        }
    }

    /**
     * toString method for Checklist Objects
     * @return Checklists name, refresh time, and every ListItem in the listItems Array
     */
    @Override
    public String toString(){
        String checklistString = "";

        checklistString += this.name;
        checklistString += ",";
        checklistString += this.refreshTime;
        checklistString += ";";

        return checklistString;
    }
}
