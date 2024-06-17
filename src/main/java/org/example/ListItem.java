package org.example;

public class ListItem {
    private String name;
    private Boolean isTracked;
    private int timeFrame;

    public ListItem(String name, int timeFrame) {
        this.isTracked = true;
        this.name = name;
        this.timeFrame = timeFrame;
    }

    private void deleteItem() {

    }

    public void setTimeFrame(int timeFrame) {
        this.timeFrame = timeFrame;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setIsTracked(Boolean isTracked) {
        this.isTracked = isTracked;
    }
}
