package org.example;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
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
        System.out.println("- 'create checklist'");
        System.out.println("- 'delete checklist'");
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
        } else if (input.equals("create checklist")) {
            createChecklist(listPage, itemPage);
        } else if (input.equals("delete checklist")) {
            System.out.println("what list do you want to delete?");
            deleteList(listPage, getUserInput());
            showListPage(listPage, itemPage);
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
            createItem(listPage, itemPage);
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
        System.out.println("- 'delete this list'");
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
        } else if (input.equals("delete this list")) {
            deleteList(listPage, checklist.getName());
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

    public static void createChecklist(ListPage listPage, ItemPage itemPage) {
        System.out.println();
        System.out.println("how do you want to name your list?");
        String name = createName();
        System.out.println();
        System.out.println("how often should this list refresh");
        int refreshTime = createChecklistRefreshTime();
        Checklist checklist = new Checklist(name, refreshTime);
        listPage.addList(checklist);

        showChecklist(listPage, itemPage, checklist);
    }

    public static String createName() {
        String name = getUserInput();

        for (int i = 0; i < name.length(); i++){
            if (name.charAt(i) == ';' || name.charAt(i) == ',') {
                System.out.println("please refrain from using ',' and ';', when naming lists and items");
                name = createName();
            }
        }

        return name;
    }

    public static int createChecklistRefreshTime() {
        int refreshTime = stringToInt(getUserInput());

        if (refreshTime > 28) {
            System.out.println("refresh time too large");
            refreshTime = createChecklistRefreshTime();
        }

        return refreshTime;
    }

    public static void createItem(ListPage listPage, ItemPage itemPage) {
        System.out.println();
        System.out.println("do you want to create a goal or a habit?");
        boolean isGoal = createItemType();

        System.out.println();
        if (isGoal) {
            System.out.println("name your goal:");
        } else {
            System.out.println("name your habit:");
        }
        String name = createName();

        System.out.println();
        if (isGoal) {
            System.out.println("what time frame do you want your goal to be tracked in?");
        } else {
            System.out.println("what time frame do you want your habit to be tracked in?");
        }
        int timeFrame = createItemTimeFrame();
        boolean isTracked = timeFrame != 0;
        if (!isTracked) {
            System.out.println("--- TRACKING IS TURNED OFF ---");
        }

        System.out.println();
        System.out.println("your ideal progress in this time frame:");
        int maxProgress;
        if (isTracked) {
            maxProgress = createItemMaxProgress();
        } else {
            maxProgress = 0;
        }
        ListItem item;
        if (isGoal) {
            if (isTracked) {
                item = new Goal(name, timeFrame, maxProgress, 0, true, listPage.getLists(), itemPage);
            } else {
                item = new Goal(name, timeFrame, maxProgress, 0, false, listPage.getLists(), itemPage);
            }
            System.out.print("--- YOUR GOAL HAS BEEN CREATED ---");
        } else {
            if (isTracked) {
                item = new Habit(name, timeFrame, maxProgress, 0, true, listPage.getLists(), itemPage);
            } else {
                item = new Habit(name, timeFrame, maxProgress, 0, false, listPage.getLists(), itemPage);
            }
            System.out.print("--- YOUR HABIT HAS BEEN CREATED ---");
        }
        showListItem(listPage, itemPage, item);
    }

    public static boolean createItemType() {
        String tempIsGoal = getUserInput();
        boolean isGoal;
        if (!tempIsGoal.equals("goal") & !tempIsGoal.equals("habit")) {
            System.out.println("--- NO VALID INPUT, PLEASE TRY AGAIN ---");
            isGoal = createItemType();
        } else {
            isGoal = tempIsGoal.equals("goal");
        }
        return isGoal;
    }

    public static int createItemTimeFrame() {
        int timeFrame = stringToInt(getUserInput());

        if (timeFrame < 0) {
            System.out.println("--- TIME FRAME NEEDS TO BE POSITIVE ---");
            timeFrame = createItemTimeFrame();
        } else if (timeFrame > 3 & timeFrame != 7 & timeFrame != 14) {
            System.out.println("--- ONLY (BI-)DAILY, (BI-)WEEKLY AND NO LISTING IS AVAILABLE RIGHT NOW ---");
            timeFrame = createItemTimeFrame();
        }

        return timeFrame;
    }

    public static int createItemMaxProgress() {
        int maxProgress = stringToInt(getUserInput());

        if (maxProgress < 0) {
            System.out.println("--- MAXIMUM PROGRESS NEEDS TO BE POSITIVE ---");
            maxProgress = createItemMaxProgress();
        }

        return maxProgress;
    }

    public static void deleteItem(ItemPage itemPage, String name) {
        boolean deleted = false;
        int itemIndex = 0;
        for (ListItem item : itemPage.getItems()) {
            if (item.getName().equals(name)) {
                if (item.getAssignedList() != null) {
                    item.getAssignedList().removeListItem(item);
                    item.resetAssignedList();
                }
                itemIndex = itemPage.getItemIndex(item);
                deleted = true;
            }
        }
        if (deleted) {
            itemPage.removeItem(itemIndex);
            System.out.println("--- SUCCESSFULLY DELETED ---");
        } else {
            System.out.println("--- INVALID NAME, NO ITEM FOUND ---");
        }
    }

    public static void deleteList(ListPage listPage, String name) {
        boolean deleted = false;
        int listIndex = 0;

        for (int i = 0; i < listPage.getSize(); i++) {
            if (listPage.getChecklist(i).getName().equals(name)) {
                listIndex = i;
                listPage.getChecklist(i).purgeList();
                deleted = true;
            }
        }
        if (deleted) {
            listPage.removeList(listIndex);
            System.out.println("--- SUCCESSFULLY DELETED ---");
        } else {
            System.out.println("--- INVALID NAME, NO LIST FOUND ---");
        }
    }

    public static String getUserInput() {
        Scanner inputGetter = new Scanner(System.in);
        return inputGetter.nextLine();
    }

}

