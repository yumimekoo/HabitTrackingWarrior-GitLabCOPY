package org.example;
import java.util.List;


public class CheckList {
    private String name;
    private int refreshTime;
    private List<ListItem> ListItems;

    public CheckList(String name, int refreshTime) {
        this.name = name;
        this.refreshTime = refreshTime;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setRefreshTime(int refreshTime) {
        this.refreshTime = refreshTime;
    }

    public void addListItem(ListItem item){
        this.ListItems.add(item);
    }

    public void removeListItem(){

    }
}
