package org.example;

import java.util.ArrayList;

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
     * @param listItems the list item that is added
     */
    public void addItem(ListItem listItems) {
        this.items.add(listItems);
    }

    /**
     * removes a list item from the item page's arraylist
     * @param index the position of the list item that's removed
     */
    public void removeItem(int index) {
        this.getItems().remove(index);
    }

    /**
     * gets the index of a list item in the item page
     * @param listItem the given list item
     * @return the list item's index
     */
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
     * @return the list item at the index
     */
    public ListItem getItem(int index) {
        return this.items.get(index);
    }
}
