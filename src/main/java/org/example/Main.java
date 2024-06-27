package org.example;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;

public class Main {
    //returns the given txt file as a String
    public static String readFileAsString(String fileName) throws Exception {
        return new String(Files.readAllBytes(Paths.get(fileName)));
    }

    public static void main(String[] args) throws Exception {
        // creates the main listPage
        ListPage listPage = new ListPage();
        ItemPage itemPage = new ItemPage();

        // reads the saved data
        String checklistData = readFileAsString(".\\src\\main\\java\\org\\example\\data\\Checklist_data.txt");
        String listItemData = readFileAsString(".\\src\\main\\java\\org\\example\\data\\ListItem_data.txt");

        // reassembles checklists and listItems from saved data
        checklistReader(listPage, checklistData);
        listItemReader(listPage, itemPage, listItemData);

        mainMenu(listPage, itemPage);

        // saves all data from the listPage's ArrayList into the checklist_data.txt file
        try (PrintWriter out = new PrintWriter(".\\src\\main\\java\\org\\example\\data\\Checklist_data.txt")) {
            checklistData = "";
            for (Checklist list : listPage.getLists()) {
                checklistData += list.toString();
            }
            out.println(checklistData);
        }
        try (PrintWriter out = new PrintWriter(".\\src\\main\\java\\org\\example\\data\\ListItem_data.txt")) {
            listItemData = "";
            for (ListItem item : itemPage.getItems()) {
                listItemData += item.toString();
            }
            out.println(listItemData);
        }
    }

    /**
     * reads the data of a given String and adds them as Checklists to the given ListPage ArrayList
     * @param listPage the ListPage that the Checklists are being added to
     * @param checklistData the String data
     */
    public static void checklistReader(ListPage listPage, String checklistData) {
        String tempChecklistString = "";

        for (int i = 0; i < checklistData.length(); i++){
            if (checklistData.charAt(i) != ';') {
                tempChecklistString += checklistData.charAt(i);
            } else {
                listPage.addList(stringToChecklist(tempChecklistString));
                tempChecklistString = "";
            }
        }
    }

    /**
     + reads the data of a given String and adds them as ListItems to the given ItemPage
     * @param listPage the ListPage to search for Checklists, when creating a ListItem
     * @param itemPage the ItemPage to add the ListItem to
     * @param listItemData the given String data
     */
    private static void listItemReader(ListPage listPage, ItemPage itemPage, String listItemData) {
        String tempListItemString = "";

        for (int i = 0; i < listItemData.length(); i++) {
            if (listItemData.charAt(i) != ';') {
                tempListItemString += listItemData.charAt(i);
            } else {
                stringToListItem(tempListItemString, listPage, itemPage);
                tempListItemString = "";
            }
        }
    }

    /**
     * assembles a Checklist from a given String
     * @param checklistString the given String data
     * @return the output Checklist
     */
    public static Checklist stringToChecklist(String checklistString) {
        String name = "";
        String tempRefreshTime = "";
        int refresh;
        int index = 0;

        for (int i = 0; i < checklistString.length(); i++) {
            index++;
            if (checklistString.charAt(i) == ','){
                break;
            }
            name += checklistString.charAt(i);
        }
        for (int i = index; i < checklistString.length(); i++) {
            tempRefreshTime += checklistString.charAt(i);
        }
        refresh = stringToInt(tempRefreshTime);

        return new Checklist(name, refresh);
    }

    /**
     * assembles a ListItem from a given String
     * @param listItemString the given String data
     * @param listPage the ListPage to look for Checklists when creating the ListItem
     * @param itemPage the ItemPage the ListItem is added to, when created
     */
    public static void stringToListItem(String listItemString, ListPage listPage, ItemPage itemPage) {
        String name = "";
        String tempIsTracKed = "";
        String tempTimeFrame = "";
        String tempMaxProgress = "";
        String tempCurrentProgress = "";
        String tempTrackType = "";
        String tempIsGoal = "";
        int index = 0;
        boolean isTracked;
        int timeFrame;
        int maxProgress;
        int currentProgress;
        int trackType;
        boolean isGoal;

        for (int i = 0; i < listItemString.length(); i++) {
            index++;
            if (listItemString.charAt(i) == ','){
                break;
            }
            name += listItemString.charAt(i);
        }
        for (int i = index; i < listItemString.length(); i++) {
            index++;
            if (listItemString.charAt(i) == ','){
                break;
            }
            tempIsTracKed += listItemString.charAt(i);
        }
        for (int i = index; i < listItemString.length(); i++) {
            index++;
            if (listItemString.charAt(i) == ','){
                break;
            }
            tempTimeFrame += listItemString.charAt(i);
        }
        for (int i = index; i < listItemString.length(); i++) {
            index++;
            if (listItemString.charAt(i) == ','){
                break;
            }
            tempMaxProgress += listItemString.charAt(i);
        }
        for (int i = index; i < listItemString.length(); i++) {
            index++;
            if (listItemString.charAt(i) == ','){
                break;
            }
            tempCurrentProgress += listItemString.charAt(i);
        }
        for (int i = index; i < listItemString.length(); i++) {
            index++;
            if (listItemString.charAt(i) == ','){
                break;
            }
            tempTrackType += listItemString.charAt(i);
        }
        for (int i = index; i < listItemString.length(); i++) {
            if (listItemString.charAt(i) == ','){
                break;
            }
            tempIsGoal += listItemString.charAt(i);
        }
        isTracked = stringToBoolean(tempIsTracKed);
        timeFrame = stringToInt(tempTimeFrame);
        maxProgress = stringToInt(tempMaxProgress);
        currentProgress = stringToInt(tempCurrentProgress);
        trackType = stringToInt(tempTrackType);
        isGoal = stringToBoolean(tempIsGoal);

        if (isGoal){
            Goal goal = new Goal(name, timeFrame, maxProgress, trackType, isTracked, listPage.getLists(), itemPage);
            for (int i = 0; i < currentProgress; i++) {
                goal.addProgress();
            }
        } else {
            Habit habit = new Habit(name, timeFrame, maxProgress, trackType, isTracked, listPage.getLists(), itemPage);
            for (int i = 0; i < currentProgress; i++) {
                habit.addProgress();
            }
        }
    }

    /**
     * converts a given String into a Boolean signal
     * @param string the given String
     * @return the output Boolean signal
     */
    public static Boolean stringToBoolean(String string) {
        return string.equals("true");
    }

    /**
     * converts a given String into an Integer
     * @param string the given String
     * @return the output Integer
     */
    public static int stringToInt(String string) {
        int converted = 0;
        for (int i = 0; i < string.length(); i++) {
         converted += (int)(((int)string.charAt(i) - 48) * Math.pow(10, string.length() - 1 - i));
        }
        return converted;
    }

    public static void showListPage(ListPage listPage, ItemPage itemPage) {
        System.out.println();
        System.out.println("L-I-S-T P-A-G-E");
        System.out.println();
        for(int i = 0; i < listPage.getLists().size(); i++) {
            System.out.print(i + 1);
            System.out.print(". ");
            System.out.println(listPage.getChecklist(i).getName());
        }
        System.out.println();

        boolean listAvailable = false;
        int index = 0;

        System.out.println("what do you want to view?");
        System.out.println("- 'main menu'");
        System.out.println("- 'items'");
        System.out.println("- '<checklist name>'");
        System.out.println();
        System.out.println("input here: ");

        String input = getUserInput();

        for (int i = 0; i < listPage.getSize(); i++) {
            if (listPage.getChecklist(i).getName().equals(input)) {
                index = i;
                listAvailable = true;
                break;
            }
        }

        if(input.equals("main menu")){
            mainMenu(listPage, itemPage);
        } else if (input.equals("items")) {
            showItemPage(listPage, itemPage);
        } else if (listAvailable) {
            showChecklist(listPage, itemPage, listPage.getChecklist(index));
        } else {
            System.out.println("--- NO VALID INPUT, PLEASE TRY AGAIN ---");
            showListPage(listPage, itemPage);
        }
    }

    public static void showItemPage(ListPage listPage, ItemPage itemPage) {
        System.out.println();
        System.out.println("I-T-E-M  P-A-G-E");
        System.out.println();
        System.out.println("Goals:");
        System.out.println();
        int index = 1;
        for(int i = 0; i < itemPage.getItems().size(); i++) {
            if (itemPage.getItem(i).getIsGoal()) {
                System.out.print(index);
                System.out.print(". ");
                System.out.println(itemPage.getItem(i).getName());
                index++;
            }
        }
        System.out.println();
        System.out.println("Habits:");
        System.out.println();
        index = 1;
        for(int i = 0; i < itemPage.getItems().size(); i++) {
            if (!itemPage.getItem(i).getIsGoal()) {
                System.out.print(index);
                System.out.print(". ");
                System.out.println(itemPage.getItem(i).getName());
                index++;
            }
        }

        boolean itemAvailable = false;
        index = 0;
        System.out.println("what do you want to view?");
        System.out.println("- 'main menu'");
        System.out.println("- 'checklists'");
        System.out.println("- '<item name>'");
        System.out.println("- 'create item'");
        System.out.println("- 'delete item'");
        System.out.println();
        System.out.println("input here: ");
        String input = getUserInput();

        for (int i = 0; i < itemPage.getItems().size(); i++) {
            if (itemPage.getItem(i).getName().equals(input)) {
                index = i;
                itemAvailable = true;
                break;
            }
        }

        if(input.equals("main menu")){
            mainMenu(listPage, itemPage);
        } else if (input.equals("checklists")) {
            showListPage(listPage, itemPage);
        } else if (input.equals("create item")) {
            createItemType(listPage, itemPage);
        } else if (input.equals("delete item")) {
            System.out.println("what item do you want to delete?");
            deleteItem(itemPage, getUserInput());
            showItemPage(listPage, itemPage);
        } else if (itemAvailable) {
            showListItem(listPage, itemPage, itemPage.getItem(index));
        } else {
            System.out.println("--- NO VALID INPUT, PLEASE TRY AGAIN ---");
            showItemPage(listPage, itemPage);
        }
    }

    public static void showChecklist(ListPage listPage, ItemPage itemPage, Checklist checklist) {
        System.out.println();
        System.out.println(checklist.getName());
        for (int i = 0; i < checklist.getList().size(); i++) {
            System.out.print("- ");
            System.out.print(checklist.getList().get(i).getName());
            int crossedOf = checklist.getList().get(i).getCurrentProgress();
            for (int j = 0; j < checklist.getList().get(i).getMaxProgress(); j++) {
                if (crossedOf > 0) {
                    System.out.print(" [X]");
                } else {
                    System.out.print(" [ ]");
                }
                crossedOf--;
            }
            System.out.println();
        }
        System.out.println();

        boolean itemAvailable = false;
        int index = 0;

        System.out.println("what do you want to do?");
        System.out.println("- 'back to lists'");
        System.out.println("- '<item name>'");
        System.out.println("- 'main menu'");
        System.out.println();
        System.out.println("input here: ");
        String input = getUserInput();

        for (int i = 0; i < itemPage.getItems().size(); i++) {
            if (itemPage.getItem(i).getName().equals(input)) {
                index = i;
                itemAvailable = true;
                break;
            }
        }

        if(input.equals("main menu")){
            mainMenu(listPage, itemPage);
        } else if (input.equals("back to lists")) {
            showListPage(listPage, itemPage);
        } else if (itemAvailable) {
            showListItem(listPage, itemPage, itemPage.getItem(index));
        } else {
            System.out.println("--- NO VALID INPUT, PLEASE TRY AGAIN ---");
            showChecklist(listPage, itemPage, checklist);
        }
    }

    public static void showListItem(ListPage listPage, ItemPage itemPage, ListItem listItem) {
        System.out.println();
        System.out.print(listItem.getName());
        if (listItem.getIsGoal()) {
            System.out.println(" (Goal)");
        } else {
            System.out.println(" (Habit)");
        }
        System.out.println();
        System.out.print("progress:");
        int crossedOf = listItem.getCurrentProgress();
        for (int i = 0; i < listItem.getMaxProgress(); i++) {
            if (crossedOf > 0) {
                System.out.print(" [X]");
            } else {
                System.out.print(" [ ]");
            }
            crossedOf--;
        }
        System.out.println();
        if(listItem.getIsTracked()){
            System.out.println("is being tracked");
            System.out.print("assigned list: ");
            if (listItem.getAssignedList() != null) {
                System.out.println(listItem.getAssignedList().getName());
            } else {
                System.out.println("not tracked on any list");
            }
        } else {
            System.out.println("is not being tracked");
        }
        System.out.println();

        System.out.println("what do you want to do?");
        System.out.println("- 'go to checklist'");
        System.out.println("- 'checklists'");
        System.out.println("- 'items'");
        System.out.println("- 'main menu'");
        System.out.println("- 'delete this item'");
        System.out.println();
        System.out.println("input here: ");

        String input = getUserInput();

        switch (input) {
            case "checklists" -> showListPage(listPage, itemPage);
            case "items" -> showItemPage(listPage, itemPage);
            case "go to checklist" -> showChecklist(listPage, itemPage, listItem.getAssignedList());
            case "main menu" -> mainMenu(listPage, itemPage);
            case "delete this item" -> {
                deleteItem(itemPage, listItem.getName());
                showItemPage(listPage, itemPage);
            }
            default -> {
                System.out.println("--- NO VALID INPUT, PLEASE TRY AGAIN ---");
                showListItem(listPage, itemPage, listItem);
            }
        }
    }

    public static void mainMenu(ListPage listPage, ItemPage itemPage){
        System.out.println("M-A-I-N M-E-N-U");
        System.out.println();
        System.out.println("what do you want to view?");
        System.out.println("- 'checklists'");
        System.out.println("- 'items'");
        System.out.println("- 'exit'");
        System.out.println();
        System.out.println("input here: ");

        String input = getUserInput();

        if(input.equals("checklists")){
            showListPage(listPage, itemPage);
        } else if (input.equals("items")) {
            showItemPage(listPage, itemPage);
        } else if (!input.equals("exit")){
            System.out.println("--- NO VALID INPUT, PLEASE TRY AGAIN ---");
            mainMenu(listPage, itemPage);
        }
    }

    public static void createItemType(ListPage listPage, ItemPage itemPage) {
        System.out.println();

        System.out.println("do you want to create a goal or a habit?");
        String tempIsGoal = getUserInput();
        if (!tempIsGoal.equals("goal") & !tempIsGoal.equals("habit")) {
            System.out.println("--- NO VALID INPUT, PLEASE TRY AGAIN ---");
            createItemType(listPage, itemPage);
        }
        boolean isGoal = tempIsGoal.equals("goal");
        System.out.println();
        createItemName(listPage, itemPage, isGoal);
    }

    public static void createItemName(ListPage listPage, ItemPage itemPage, boolean isGoal) {
        System.out.println();
        System.out.print("name your ");
        if (isGoal) {
            System.out.println("goal:");
        } else {
            System.out.println("habit:");
        }
        String listItemName = getUserInput();
        createItemTimeFrame(listPage, itemPage, isGoal, listItemName);
    }

    public static void createItemTimeFrame(ListPage listPage, ItemPage itemPage, boolean isGoal, String name) {
        System.out.println();
        System.out.print("what time frame do you want your ");
        if (isGoal) {
            System.out.print("goal");
        } else {
            System.out.print("habit");
        }
        System.out.println(" to be tracked in");

        int timeFrame = stringToInt(getUserInput());

        if (timeFrame < 0) {
            System.out.println("--- TIME FRAME NEEDS TO BE POSITIVE ---");
            createItemTimeFrame(listPage, itemPage, isGoal, name);
        } else if (timeFrame < 1) {
            System.out.println("--- TRACKING IS TURNED OFF ---");
            createItemMaxProgress(listPage, itemPage, isGoal, name, timeFrame, false);
        } else if (timeFrame != 7 & timeFrame != 1) {
            System.out.println("--- ONLY DAILY AND WEEKLY LISTING IS AVAILABLE RIGHT NOW ---");
            createItemTimeFrame(listPage, itemPage, isGoal, name);
        } else {
            createItemMaxProgress(listPage, itemPage, isGoal, name, timeFrame, true);
        }

    }

    public static void createItemMaxProgress(ListPage listPage, ItemPage itemPage, boolean isGoal, String name, int timeFrame, boolean isTracked) {
        System.out.println();
        System.out.print("your ideal progress in this time frame:");

        int maxProgress = stringToInt(getUserInput());

        if (maxProgress < 0) {
            System.out.println("--- CAN NOT BE NEGATIVE ---");
            createItemMaxProgress(listPage, itemPage, isGoal, name, timeFrame, isTracked);
        } if (maxProgress == 0) {
            if (isTracked) {
                if (isGoal) {
                    System.out.println("--- MAX PROGRESS NEEDS TO BE GREATER THAN 0, IF THE GOAL IS TRACKED ON A LIST ---");
                    createItemMaxProgress(listPage, itemPage, true, name, timeFrame, true);
                } else {
                    System.out.println("--- MAX PROGRESS NEEDS TO BE GREATER THAN 0, IF THE HABIT IS TRACKED ON A LIST ---");
                    createItemMaxProgress(listPage, itemPage, false, name, timeFrame, true);
                }
            } else {
                if (isGoal) {
                    System.out.println("goal has been created");
                    Goal goal = new Goal(name, timeFrame, maxProgress, 0, false, listPage.getLists(), itemPage);
                    showItemPage(listPage, itemPage);
                } else {
                    System.out.println("habit has been created");
                    Habit habit = new Habit(name, timeFrame, maxProgress, 0, false, listPage.getLists(), itemPage);
                    showItemPage(listPage, itemPage);
                }
            }
        } else {
            if (isGoal) {
                System.out.println("goal has been created");
                Goal goal = new Goal(name, timeFrame, maxProgress, 0, false, listPage.getLists(), itemPage);
                showListItem(listPage, itemPage, goal);
            } else {
                System.out.println("habit has been created");
                Habit habit = new Habit(name, timeFrame, maxProgress, 0, false, listPage.getLists(), itemPage);
                showListItem(listPage, itemPage, habit);
            }
        }
    }

    // deleteItem still doesn't work properly:
    // resetting assigned list works (can now also view item details that are not assigned to a list properly)
    // removing item from assigned list and item page is still erroneous
    // -> for now deleting an item only sets its own assigned list value to 0, but it still shows on both the checklist and the list page
    public static void deleteItem(ItemPage itemPage, String name) {
        boolean deleted = false;
        for (ListItem item : itemPage.getItems()) {
            if (item.getName().equals(name)) {
                //item.getAssignedList().removeListItem(item);
                item.resetAssignedList();
                /* for (int i = 0; i < itemPage.getItems().size(); i++) {
                    if (itemPage.getItem(i) == item) {
                        itemPage.removeItem(i);
                    }
                } */
                deleted = true;
            }
        }
        if (deleted) {
            System.out.println("--- SUCCESSFULLY DELETED ---");
        } else {
            System.out.println("--- INVALID NAME, NO ITEM FOUND ---");
        }
    }

    public static String getUserInput() {
        Scanner inputGetter = new Scanner(System.in);
        return inputGetter.nextLine();
    }

}

