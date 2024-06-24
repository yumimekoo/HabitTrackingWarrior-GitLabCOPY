package org.example;

import java.util.ArrayList;

public class ListPage {
    private ArrayList<Checklist> lists;

    /**
     * Constructor for ListPage Objects
     */
    public ListPage(){
        this.lists = new ArrayList<>();
    }

    /**
     * adds a new Checklist to the ArrayList
     * @param checklist the new Checklist
     */
    public void addList(Checklist checklist) {
        this.lists.add(checklist);
    }

    /**
     * removes a Checklist from the ArrayList
     * @param checklistIndex the Checklist's index
     */
    public void removeList(int checklistIndex) {
        this.lists.remove(checklistIndex);
    }

    /**
     * returns a Checklist from the ArrayList
     * @param checklistIndex the wanted Checklist's index in the ArrayList
     * @return the wanted Checklist
     */
    public Checklist getChecklist(int checklistIndex) {
        return this.lists.get(checklistIndex);
    }

    /**
     * gets the amount of Checklists in the ListPage
     * @return the amount of Checklists
     */
    public int getSize() {
        return this.lists.size();
    }

    /**
     * gets the entire ArrayList attribute
     * @return the ArrayList
     */
    public ArrayList<Checklist> getLists(){
        return this.lists;
    }
}
