package org.example;
import java.util.ArrayList;


public class Checklist {
    private String name;
    private int timeFrame;
    private String timeScale;
    private final ArrayList<ListItem> listItems;
    private int[] initDate;

    /**
     * Constructor for Checklist
     * @param name the Name of the CheckList
     * @param timeFrame the time frame in which the list refreshes
     */
    public Checklist(String name, int timeFrame, String timeScale, int[] initDate) {
        this.setName(name);
        this.setTimeFrame(timeFrame);
        this.setTimeScale(timeScale);
        listItems = new ArrayList<>();
        this.setInitDate(initDate);
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
     * @param timeFrame the new refresh time for the checklist
     */
    public void setTimeFrame(int timeFrame) {
        this.timeFrame = timeFrame;
    }

    /**
     * gets the Checklists timeFrame
     * @return the lists timeFrame
     */
    public int getTimeFrame(){
        return this.timeFrame;
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

    public void refreshList(){
        for (ListItem item : this.getList()) {
            item.refreshItem();
        }
    }

    public void setInitDate(int[] initDate) {
        this.initDate = initDate;
    }

    public int[] getInitDate() {
        return this.initDate;
    }

    public void setTimeScale(String timeScale) {
        this.timeScale = timeScale;
    }

    public String getTimeScale() {
        return timeScale;
    }

    /**
     * toString method for Checklist Objects
     * @return Checklists name, refresh time, and every ListItem in the listItems Array
     */
    @Override
    public String toString(){
        String checklistString = "";

        checklistString += this.getName();
        checklistString += ",";
        checklistString += this.getTimeFrame();
        checklistString += ",";
        checklistString += this.getTimeScale();
        checklistString += ",";
        checklistString += this.getInitDate()[0];
        checklistString += ",";
        checklistString += this.getInitDate()[1];
        checklistString += ",";
        checklistString += this.getInitDate()[2];
        checklistString += ";";

        return checklistString;
    }
}
