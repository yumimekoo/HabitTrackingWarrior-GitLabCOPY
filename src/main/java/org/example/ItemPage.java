package org.example;

import java.util.ArrayList;
import java.util.List;

public class ItemPage {
    private ArrayList<ListItem> items;

    /**
     * Constructor for ItemPage Objects
     */
    public ItemPage(){
        this.items = new ArrayList<>();
    }

    /**
     * gets the ArrayList of ListItems
     * @return the ArrayList
     */
    public ArrayList<ListItem> getItems() {
        return items;
    }

    /**
     * adds a ListItem to the ArrayList
     * @param listItems
     */
    public void addItem(ListItem listItems) {
        this.items.add(listItems);
    }


    public void removeItem(int index) {
        this.getItems().remove(index);
    }

    public int getItemIndex(ListItem listItem) {
        int index = 0;

        for (int i = 0; i < this.getItems().size(); i++) {
            if (this.getItem(i) == listItem) {
                index = i;
                break;
            }
        }

        return index;
    }

    /**
     * gets a specific ListItem from the ArrayList
     * @param index index of the ListItem
     * @return
     */
    public ListItem getItem(int index) {
        return this.items.get(index);
    }
}
