package org.example;

public class ListItem {
    public String name;
    public Boolean isTracked; //this is true by default
    public int timeFrame;

    /**
     * Constructor for a list item
     * @param name the name of the item to be used
     * @param timeFrame the time frame in which to track this list item
     */
    /*public ListItem(String name, int timeFrame) {
        this.isTracked = true;
        this.name = name;
        this.timeFrame = timeFrame;
    }*/

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
}
