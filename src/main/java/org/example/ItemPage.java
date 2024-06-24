package org.example;

import java.util.ArrayList;

public class ItemPage {
    ArrayList<ListItem> items;

    public ItemPage(){
        this.items = new ArrayList<>();
    }

    public ArrayList<ListItem> getItems() {
        return items;
    }

    public void addItem(ListItem listItems) {
        this.items.add(listItems);
    }

    public void removeItem(int index) {
        this.items.remove(index);
    }

    public ListItem getItem(int index) {
        return this.items.get(index);
    }
}
