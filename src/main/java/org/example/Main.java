package org.example;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;

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

        // saves all data from the listPage's ArrayList into the checklist_data.txt file
        try (PrintWriter out = new PrintWriter(".\\src\\main\\java\\org\\example\\data\\Checklist_data.txt")) {
            checklistData = "";
            for (Checklist list : listPage.getLists()) {
                checklistData += list.toString();
            }
            out.println(checklistData);
        }

        System.out.println(readFileAsString(".\\src\\main\\java\\org\\example\\data\\Checklist_data.txt"));

        try (PrintWriter out = new PrintWriter(".\\src\\main\\java\\org\\example\\data\\ListItem_data.txt")) {
            listItemData = "";
            for (ListItem item : itemPage.getItems()) {
                listItemData += item.toString();
            }
            out.println(listItemData);
        }

        System.out.println(readFileAsString(".\\src\\main\\java\\org\\example\\data\\ListItem_data.txt"));

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
     * @return the output ListItem
     */
    public static ListItem stringToListItem(String listItemString, ListPage listPage, ItemPage itemPage) {
        String name = "";
        String tempIsTracKed = "";
        String tempTimeFrame = "";
        String tempMaxProgress = "";
        String tempCurrentProgress = "";
        String tempTrackType = "";
        String tempIsGoal = "";
        int index = 0;
        Boolean isTracked;
        int timeFrame;
        int maxProgress;
        int currentProgress;
        int trackType;
        Boolean isGoal;

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
            return goal;
        }
        Habit habit = new Habit(name, timeFrame, maxProgress, trackType, isTracked, listPage.getLists(), itemPage);
        for (int i = 0; i < currentProgress; i++) {
            habit.addProgress();
        }
        return habit;
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
}

