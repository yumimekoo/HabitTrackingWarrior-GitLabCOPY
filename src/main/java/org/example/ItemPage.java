package org.example;

import java.util.ArrayList;

public class ItemPage {
    ArrayList<ListItem> items;

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

    /**
     * removes a ListItem from the ArrayList
     * @param index the index of the ListItem that's removed
     */
    public void removeItem(int index) {
        this.items.remove(index);
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
