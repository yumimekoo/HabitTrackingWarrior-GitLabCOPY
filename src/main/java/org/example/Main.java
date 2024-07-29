package org.example;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;

public class Main {
    /**
     * reads a txt file and saves it as a String
     * @param fileName the name of the file to be read
     * @return the contents of the file in a string
     * @throws Exception file not found exception, if the file wasn't found
     */
    public static String readFileAsString(String fileName) throws Exception {
        return new String(Files.readAllBytes(Paths.get(fileName)));
    }

    public static void main(String[] args) throws Exception {
        // creates the main listPage
        ListPage listPage = new ListPage();
        ItemPage itemPage = new ItemPage();

        // reads the saved data
        String checklistData = readFileAsString("./src/main/java/org/example/data/Checklist_data.txt");
        String listItemData = readFileAsString("./src/main/java/org/example/data/ListItem_data.txt");

        // reassembles checklists and listItems from saved data
        checklistReader(listPage, checklistData);
        listItemReader(listPage, itemPage, listItemData);

        String menuKey = "main";

        while (!menuKey.equals("exit")) {
            switch (menuKey) {
                case "main" -> menuKey = mainMenu(listPage, itemPage, menuKey); // menuKey gets set in openMain
                case "list" -> menuKey = showListPage(listPage, itemPage, menuKey); // menuKey gets set in openList
                case "item" -> menuKey = showItemPage(listPage, itemPage, menuKey); // menuKey gets set in openItem
            }
        }

        // saves all data from the listPage's ArrayList into the checklist_data.txt file
        try (PrintWriter out = new PrintWriter("./src/main/java/org/example/data/Checklist_data.txt")) {
            checklistData = "";
            for (Checklist list : listPage.getLists()) {
                checklistData += list.toString();
            }
            out.println(checklistData);
        }
        try (PrintWriter out = new PrintWriter("./src/main/java/org/example/data/ListItem_data.txt")) {
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

        ListItem item = new ListItem(name, timeFrame, maxProgress, trackType, isTracked, listPage.getLists(), itemPage, isGoal);
        for (int i = 0; i < currentProgress; i++) {
            item.addProgress();
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

    /**
     * shows the list page and it's contents. then gives you a variety of options to choose from via text input
     * @param listPage the list page of the program
     * @param itemPage the item page of the program
     */
    public static String showListPage(ListPage listPage, ItemPage itemPage, String menuKey) {
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
        if (listAvailable) {
            return showChecklist(listPage, itemPage, listPage.getChecklist(index), menuKey);
        } else{
            switch (input) {
                case "main menu" -> {
                    return "main";
                }
                case "items" -> {
                    return "item";
                }
                case "create checklist" -> {
                    return createChecklist(listPage, itemPage, menuKey);
                }
                case "delete checklist" -> {
                    System.out.println("what list do you want to delete?");
                    deleteList(listPage, getUserInput());
                    return showListPage(listPage, itemPage, menuKey);
                }
                default -> {
                    System.out.println("--- NO VALID INPUT, PLEASE TRY AGAIN ---");
                    return showListPage(listPage, itemPage, menuKey);
                }
            }
        }
    }

    /**
     * shows the item page and it's contents. then gives you a variety of options to choose from via text input
     * @param listPage the list page of the program
     * @param itemPage the item page of the program
     */
    public static String showItemPage(ListPage listPage, ItemPage itemPage, String menuKey) {
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
        System.out.println();

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
        if (itemAvailable) {
            return showListItem(listPage, itemPage, itemPage.getItem(index), menuKey);
        } else {
            switch (input) {
                case "main menu" -> {
                    return "main";
                }
                case "checklists" -> {
                    return "list";
                }
                case "create item" -> {
                    return createItem(listPage, itemPage, menuKey);
                }
                case "delete item" ->{
                    System.out.println("what item do you want to delete?");
                    deleteItem(itemPage, getUserInput());
                    return showItemPage(listPage, itemPage, menuKey);
                }
                default -> {
                    System.out.println("--- NO VALID INPUT, PLEASE TRY AGAIN ---");
                    return showItemPage(listPage, itemPage, menuKey);
                }
            }
        }
    }

    /**
     * shows a checklist and it's contents. then gives you a variety of options to choose from via text input
     * @param listPage the list page of the program
     * @param itemPage the item page of the program
     * @param checklist the checklist that is viewed
     */
    public static String showChecklist(ListPage listPage, ItemPage itemPage, Checklist checklist, String menuKey) {
        System.out.println();
        System.out.println(checklist.getName());
        for (int i = 0; i < checklist.getList().size(); i++) {
            System.out.print("- ");
            System.out.print(checklist.getList().get(i).getName());
            int crossedOf = checklist.getList().get(i).getCurrentProgress();
            switch (checklist.getList().get(i).getTrackType()) {
                case 0 -> {
                    for (int j = 0; j < checklist.getList().get(i).getMaxProgress(); j++) {
                        if (crossedOf > 0) {
                            System.out.print(" [X]");
                        } else {
                            System.out.print(" [ ]");
                        }
                        crossedOf--;
                    }
                }
                case 2 -> {
                    //stub2
                }
                case 3 -> {
                    //stub3
                }
                case 4 -> {
                    //stub4
                }
                default -> {
                }
            }
            System.out.println();
        }
        System.out.println();

        boolean itemAvailable = false;
        int index = 0;

        System.out.println("what do you want to do?");
        System.out.println("- 'add/remove checkmark");
        System.out.println("- 'back to lists'");
        System.out.println("- '<item name>'");
        System.out.println("- 'edit this list'");
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

        if (itemAvailable) {
            return showListItem(listPage, itemPage, itemPage.getItem(index), menuKey);
        } else {
            switch (input) {
                case "main menu" -> {
                    return "main";
                }
                case "back to lists" -> {
                    return "list";
                }
                case "add checkmark" -> {
                    int itemIndex = 0;
                    boolean itemStatus = false;
                    System.out.println("what item do you want to update?");
                    String name = getUserInput();
                    for (int i = 0; i < checklist.getSize(); i++) {
                        if (name.equals(checklist.getItem(i).getName())) {
                            itemIndex = i;
                            itemStatus = true;
                            break;
                        }
                    }
                    if (itemStatus) {
                        addCheckmark(checklist.getItem(itemIndex));
                    } else {
                        System.out.println("--- INVALID INPUT, ITEM NOT FOUND ON THIS LIST ---");
                    }
                    return showChecklist(listPage, itemPage, checklist, menuKey);
                }
                case "remove checkmark" -> {
                    int itemIndex = 0;
                    boolean itemStatus = false;
                    System.out.println("what item do you want to update?");
                    String name = getUserInput();
                    for (int i = 0; i < checklist.getSize(); i++) {
                        if (name.equals(checklist.getItem(i).getName())) {
                            itemIndex = i;
                            itemStatus = true;
                            break;
                        }
                    }
                    if (itemStatus) {
                        removeCheckmark(checklist.getItem(itemIndex));
                    } else {
                        System.out.println("--- INVALID INPUT, ITEM NOT FOUND ON THIS LIST ---");
                    }
                    return showChecklist(listPage, itemPage, checklist, menuKey);
                }
                case "edit this list" -> {
                    editList(listPage, checklist);
                    return "list";
                }
                case "delete this list" -> {
                    deleteList(listPage, checklist.getName());
                    return "list";
                }
                default -> {
                    System.out.println("--- NO VALID INPUT, PLEASE TRY AGAIN ---");
                    return showChecklist(listPage, itemPage, checklist, menuKey);
                }
            }
        }
    }

    /**
     * shows a list item and it's contents. then gives you a variety of options to choose from via text input
     * @param listPage the list page of the program
     * @param itemPage the item page of the program
     * @param listItem the list item that is viewed
     */
    public static String showListItem(ListPage listPage, ItemPage itemPage, ListItem listItem, String menuKey) {
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
        System.out.println("- 'add/remove checkmark'");
        System.out.println("- 'go to checklist'");
        System.out.println("- 'checklists'");
        System.out.println("- 'items'");
        System.out.println("- 'main menu'");
        System.out.println("- 'edit this item'");
        System.out.println("- 'delete this item'");
        System.out.println();
        System.out.println("input here: ");

        String input = getUserInput();

        switch (input) {
            case "add checkmark" -> {
                addCheckmark(listItem);
                return showListItem(listPage, itemPage, listItem, menuKey);
            }
            case "remove checkmark" -> {
                removeCheckmark(listItem);
                return showListItem(listPage, itemPage, listItem, menuKey);
            }
            case "checklists" -> {
                return "list";
            }
            case "items" -> {
                return "item";
            }
            case "go to checklist" -> {
                if (listItem.getAssignedList() != null) {
                    return showChecklist(listPage, itemPage, listItem.getAssignedList(), menuKey);
                } else {
                    System.out.println("--- ITEM NOT ASSIGNED TO ANY LIST ---");
                    return showListItem(listPage, itemPage, listItem, menuKey);
                }
            }
            case "main menu" -> {
                return "main";
            }
            case "delete this item" -> {
                deleteItem(itemPage, listItem.getName());
                return "item";
            }
            case "edit this item" -> {
                editItem(listPage, listItem);
                return showListItem(listPage, itemPage, listItem, menuKey);
            }
            default -> {
                System.out.println("--- NO VALID INPUT, PLEASE TRY AGAIN ---");
                return showListItem(listPage, itemPage, listItem, menuKey);
            }
        }
    }

    /**
     * the main menu of the program. gives you the option to view the list page, the item page or exit
     * @param listPage the list page of the program
     * @param itemPage the item page of the program
     */
    public static String mainMenu(ListPage listPage, ItemPage itemPage, String menuKey){
        System.out.println("M-A-I-N M-E-N-U");
        System.out.println();
        System.out.println("what do you want to view?");
        System.out.println("- 'checklists'");
        System.out.println("- 'items'");
        System.out.println("- 'exit'");
        System.out.println();
        System.out.println("input here: ");

        String input = getUserInput();

        switch (input) {
            case "checklists" -> {
                return "list";
            }
            case "items" -> {
                return "item";
            }
            case "exit" -> {
                return "exit";
            }
            default -> {
                System.out.println("--- NO VALID INPUT, PLEASE TRY AGAIN ---");
                return "main";
            }
        }
    }

    /**
     * initiates the creation of a new checklist.
     * @param listPage the list page of the program
     * @param itemPage the item page of the program
     */
    public static String createChecklist(ListPage listPage, ItemPage itemPage, String menuKey) {
        System.out.println();
        System.out.println("how do you want to name your list?");
        String name = createName();
        System.out.println();
        System.out.println("how often should this list refresh");
        int refreshTime = createTimeFrame();
        Checklist checklist = new Checklist(name, refreshTime);
        listPage.addList(checklist);

        return showChecklist(listPage, itemPage, checklist, menuKey);
    }

    /**
     * creates a name for a new checklist or list item through user input
     * @return the created name as a string
     */
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

    /**
     * creates the refreshTime value for a new checklist through user input
     * @return the created refreshTime as an int
     */
    public static int createTimeFrame() {
        int refreshTime = stringToInt(getUserInput());

        if (refreshTime < 0) {
            System.out.println("--- TIME FRAME NEEDS TO BE POSITIVE ---");
            refreshTime = createTimeFrame();
        } else if (refreshTime > 3 & refreshTime != 7 & refreshTime != 14) {
            System.out.println("--- ONLY (BI-)DAILY, (BI-)WEEKLY AND NO LISTING IS AVAILABLE RIGHT NOW ---");
            refreshTime = createTimeFrame();
        }

        return refreshTime;
    }

    /**
     * initiates the creation of a new list item.
     * @param listPage the list page of the program
     * @param itemPage the item page of the program
     */
    public static String createItem(ListPage listPage, ItemPage itemPage, String menuKey) {
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
        int timeFrame = createTimeFrame();
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

        int trackType = 0; // for now always zero

        ListItem item;

        item = new ListItem(name, timeFrame, maxProgress, trackType, isTracked, listPage.getLists(), itemPage, isGoal);

        return showListItem(listPage, itemPage, item, menuKey);
    }

    /**
     * creates the isGoal value for a new list item through user input
     * @return the created isGoal as a boolean
     */
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

    /**
     * creates the maxProgress value for a new list item through user input
     * @return the created maxProgress as an int
     */
    public static int createItemMaxProgress() {
        int maxProgress = stringToInt(getUserInput());

        if (maxProgress < 0) {
            System.out.println("--- MAXIMUM PROGRESS NEEDS TO BE POSITIVE ---");
            maxProgress = createItemMaxProgress();
        }

        return maxProgress;
    }

    public static void editItem(ListPage listPage, ListItem listItem) {
        System.out.println();
        System.out.println("--- EDITING ITEM ---");
        System.out.println();
        System.out.print("name:              ");
        System.out.print(listItem.getName());
        if (listItem.getIsGoal()) {
            System.out.println("(Goal)");
        } else {
            System.out.println("(Habit)");
        }
        System.out.print("time frame (list): ");
        System.out.print(listItem.getTimeFrame());
        if (listItem.getAssignedList() == null) {
            System.out.println(" (no assigned list)");
        } else {
            System.out.print(" (");
            System.out.print(listItem.getAssignedList().getName());
            System.out.println(")");
        }
        System.out.print("maximum progress:  ");
        System.out.println(listItem.getMaxProgress());
        System.out.print("track type (stub): ");
        System.out.println(listItem.getTrackType());
        System.out.print("tracked:           ");
        if (listItem.getIsTracked()) {
            System.out.println("yes");
        } else {
            System.out.println("no");
        }
        System.out.print("finished:          ");
        if (listItem.getIsFinished()) {
            System.out.println("yes");
        } else {
            System.out.println("no");
        }
        System.out.println();
        System.out.println("what do you want to edit?");
        System.out.println("name/tracked/timeframe/progress/changeItemType/finished/back");
        String input = getUserInput();
        System.out.println();
        boolean goBack = false;

        switch (input) {
            case "name" -> {
                System.out.println("new name:");
                listItem.setName(createName());
            }
            case "tracked" -> {
                if (listItem.getIsTracked()) {
                    listItem.setIsTracked(false, listPage.getLists());
                    System.out.println("tracking is turned off");
                } else {
                    listItem.setIsTracked(true, listPage.getLists());
                    System.out.println("tracking is turned on");
                }
            }
            case "timeframe" -> {
                System.out.println("new time frame:");
                listItem.setTimeFrame(createTimeFrame());
            }
            case "progress" -> {
                System.out.println("new maximum progress");
                listItem.setMaxProgress(createItemMaxProgress());
            }
            case "changeItemType" -> {
                if (!listItem.getIsGoal()) {
                    listItem.setIsGoal(true);
                    System.out.println("this item is now a goal");
                } else {
                    listItem.setIsGoal(false);
                    System.out.println("this item is now a habit");
                }
            }
            case "finished" -> {
                listItem.switchIsFinished();

                if(listItem.getIsFinished()) {
                    listItem.setIsTracked(false, listPage.getLists());
                    System.out.println("the item is now marked as finished");
                } else {
                    listItem.setIsTracked(true, listPage.getLists());
                    System.out.print("the item is now marked as not finished and added to the list '");
                    System.out.print(listItem.getAssignedList().getName());
                    System.out.println("'");
                }
            }
            case "back" -> {
                goBack = true;
            }
            default -> {
                System.out.println("--- NO VALID INPUT, PLEASE TRY AGAIN ---");
            }
        }

        if (!goBack) {
            editItem(listPage, listItem);
        }
    }

    /**
     * deletes a list item by removing it from the list page and the item page, then opens the item page
     * @param itemPage the item page of the program
     * @param name the list item that is deleted
     */
    public static void deleteItem(ItemPage itemPage, String name) {
        boolean deleted = false;
        int itemIndex = 0;
        for (ListItem item : itemPage.getItems()) {
            if (item.getName().equals(name)) {
                if (item.getAssignedList() != null) {
                    item.getAssignedList().removeListItem(item);
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

    public static void editList(ListPage listPage, Checklist checklist) {
        System.out.println();
        System.out.println("--- EDITING CHECKLIST ---");
        System.out.println();
        System.out.print("name:       ");
        System.out.println(checklist.getName());
        System.out.print("time frame: ");
        System.out.println(checklist.getTimeFrame());
        System.out.println();
        System.out.println("what do you want to edit?");
        System.out.println("name/timeframe/back");
        String input = getUserInput();
        System.out.println();
        boolean goBack = false;

        switch (input) {
            case "name" -> {
                System.out.println("new name:");
                checklist.setName(createName());
            }
            case "timeframe" -> {
                System.out.println("new time frame:");
                checklist.setTimeFrame(createTimeFrame());
                System.out.println("dow you want to migrate all the list items?");
                migrateListItems(checklist);
            }
            case "back" -> {
                goBack = true;
            }
            default -> {
                System.out.println("--- NO VALID INPUT, PLEASE TRY AGAIN ---");
            }
        }

        if (!goBack) {
            editList(listPage, checklist);
        }
    }

    public static void migrateListItems(Checklist checklist){
        System.out.println();
        System.out.println("- 'yes' (change all items' time frames)");
        System.out.println("- 'no' (keep all items' time frames and turn tracking off)");

        String input = getUserInput();

        switch (input) {
            case "yes" -> {
                for (ListItem item : checklist.getList()) {
                    item.setTimeFrame(checklist.getTimeFrame());
                }
            }
            case "no" -> {
                for (ListItem item : checklist.getList()) {
                    item.resetAssignedList();
                }
            }
            default -> {
                System.out.println("--- NO VALID INPUT, PLEASE TRY AGAIN ---");
                migrateListItems(checklist);
            }
        }
    }

    /**
     * deletes a checklist by removing it from the list page and removing every list item from the itself
     * @param listPage the list page of the program
     * @param name the checklist that is deleted
     */
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

    public static void addCheckmark(ListItem listItem) {
        if (listItem.getCurrentProgress() == listItem.getMaxProgress()) {
            System.out.println("--- THIS ITEM'S PROGRESS IS ALREADY MAXED OUT ---");
        } else {
            listItem.addProgress();
        }
    }

    public static void removeCheckmark(ListItem listItem) {
        if (listItem.getCurrentProgress() == 0) {
            System.out.println("--- THIS ITEM'S PROGRESS IS ALREADY 0 ---");
        } else {
            listItem.reduceProgress();
        }
    }

    /**
     * gets the users input with a scanner object
     * @return the user's input as a string
     */
    public static String getUserInput() {
        Scanner inputGetter = new Scanner(System.in);
        return inputGetter.nextLine();
    }

}

