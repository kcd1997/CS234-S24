package MainPackage;

import java.util.ArrayList;

/**
 * Terminal Ticketing Software
 * This software is used to store and manage data related to the day-to-day operations of a ticketing/operations kiosk
 * in an airport. It allows the user to access various functions in viewing and editing data and reports, and has
 * an inbuilt functionality for reading and writing data to and from the file system. It also uses a user-set system
 * time which allows the user to automatically update location information for planes based on flight times, and to
 * update other information related to flight arrival/departure times.
 * @author Kyle Dean, Mason Herbig, and Lwesso Mukeni
 * 09 March 2024
 */

public class Main
{
    /**
     * This is the main method, which serves to create the MainMenu implementation for the terminal.
     * @param args a set of string arguments which may be passed by the user in the command line.
     */
    public static void main(String[] args)
    {

        System.out.println("TERMINAL TICKETING SOFTWARE\nBy Kyle Dean, Mason Herbig, and Lwesso Mukeni\n");
        String mainMenuPrint = "Main Menu: ";
        String mainMenuAltPrint = "Option #";

        ArrayList<String> mainMenuOptionsList = new ArrayList<String>();
        mainMenuOptionsList.add("- Load State");
        mainMenuOptionsList.add("- Save State");
        mainMenuOptionsList.add("- Edit System Time");
        mainMenuOptionsList.add("- Generate Reports");
        mainMenuOptionsList.add("- Ticket Menu");
        mainMenuOptionsList.add("- Passenger Menu");
        mainMenuOptionsList.add("- Flight Menu");
        mainMenuOptionsList.add("- Plane Menu");
        mainMenuOptionsList.add("- Airport Menu");
        mainMenuOptionsList.add("- Exit Program");

        MainMenu mainMenu = new MainMenu(mainMenuOptionsList,mainMenuPrint,mainMenuAltPrint);
        mainMenu.runMenu();
    }
}