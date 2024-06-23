package org.example;

import java.util.ArrayList;

public class ListPage {
    private ArrayList<Checklist> lists;

    public ListPage(){
        this.lists = new ArrayList<>();
    }

    public void addList(Checklist checklist) {
        this.lists.add(checklist);
    }

    public void removeList(int checklistIndex) {
        this.lists.remove(checklistIndex);
    }

    public Checklist getChecklist(int checklistIndex) {
        return this.lists.get(checklistIndex);
    }

    public int getSize() {
        return this.lists.size();
    }

    public ArrayList<Checklist> getLists(){
        return this.lists;
    }

    @Override
    public String toString(){
        String listsString = "";

        for(Checklist checklist : lists) {

            listsString += "[";
            listsString += checklist.toString();
            listsString += "]";
            listsString += "\n";
        }

        return listsString;
    }
}
