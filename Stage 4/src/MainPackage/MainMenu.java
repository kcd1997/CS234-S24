package MainPackage;

import java.io.FileWriter;
import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * The MainMenu class hosts the functionality of the rest of the program by allowing the user to enter various sub-
 * menus, including a menu for generating reports and menus for each of the data types (Ticket, Passenger, Flight,
 * Plane, Airport). It also has functionality for loading and saving data in the file system, and for changing the
 * system time.
 * @author Kyle Dean, Mason Herbig, and Lwesso Mukeni
 */

public class MainMenu extends Menu
{
    /* Initialize a new TerminalData structure */

    TerminalData terminalData = new TerminalData(true, new ArrayList<Passenger>(), new ArrayList<Airport>(),
            new ArrayList<Plane>(), new ArrayList<Flight>(), new ArrayList<Ticket>(), 0);


    /* Initialize a variable to assist with changing the system clock */
    double currentTime;


    /**
     * This is the constructor for the MainMenu class
     * @param optionsList The list of options presented to the user
     * @param menuPrint The header for the menu
     * @param altPrint The header for each option in the menu
     */
    public MainMenu(ArrayList<String> optionsList, String menuPrint, String altPrint)
    {
        super(optionsList, menuPrint, altPrint);
    }

    /**
     * getTerminal gets the terminalData object currently in use by the MainMenu.
     * @return the terminalData object used by this MainMenu.
     */
    public TerminalData getTerminal()
    {
        return terminalData;
    }

    /**
     * setTerminal sets the terminalData object currently in use by the MainMenu to the given TerminalData object.
     * @param terminalData the object to which the MainMenu's terminalData object will be changed.
     */
    public void setTerminal(TerminalData terminalData)
    {
        this.terminalData = terminalData;
    }

    /**
     * runMenu is overridden here to include a printout of the current system time.
     */
    public void runMenu()
    {
        setMenuPrint(menuPrint + "\n" + "[ Current System Time- " + terminalData.getSystemTime() + " ]");
        super.runMenu();
    }

    /**
     * menuOption contains methods to call the appropriate methods to:
     *      1- Load data from a file
     *      2- Save data to a file
     *      3- Change the System Time
     *      4- Generate various reports via the ReportMenu submenu
     *      5- Access the TicketMenu submenu
     *      6- Access the PassengerMenu submenu
     *      7- Access the FlightMenu submenu
     *      8- Access the PlaneMenu submenu
     *      9- Access the AirportMenu submenu
     *      10- Exit the program (added automatically by the Menu class)
     * It also contains functionality to automatically update locations and associated data for Planes based on the
     * time data included in Flights using that Plane. This occurs each time the MainMenu is accessed.
     * @param userIn the user's input, which determines the function of the menu.
     */
    public void menuOption(int userIn) {
        /* Initialize objects to get user input and to manage system time functions */

        GetInput userInput = new GetInput();

        currentTime = terminalData.getSystemTime();

        /* Perform checks for all flights in the manifest */

        for (Flight currentFlight : terminalData.getFlightManifest())
        {
            boolean inferior = false;

            /* Check if the Flight's arrival time is superseded by another Flight's arrival time, to make sure it is the
            most recent Flight to change the Plane's location via arrival. This check is only done if the Flight
            has actually already arrived */

            if (currentFlight.getArriveTime() <= currentTime)
            {
                for (Flight flight : terminalData.getFlightManifest())
                {
                    if ((flight.getArriveTime() > currentFlight.getArriveTime())
                            && (flight.getArriveTime() <= currentTime)
                            && (flight.getAssociatedPlaneCode().equals(currentFlight.getAssociatedPlaneCode())))
                    {
                        inferior = true;

                        break;
                    }

                    if ((flight.getDepartTime() > currentFlight.getArriveTime())
                            && (flight.getDepartTime() <= currentTime)
                            && (flight.getAssociatedPlaneCode().equals(currentFlight.getAssociatedPlaneCode())))
                    {
                        inferior = true;

                        break;
                    }
                }

                /* If this is the most recent Flight to change the Plane's location, adjust the data accordingly */

                if (!inferior)
                {
                    for (Plane currentPlane : terminalData.getPlaneManifest())
                    {
                        if (currentFlight.getAssociatedPlaneCode().equals(currentPlane.getPlaneID()))
                        {
                            currentPlane.setCurrentAssignmentType("Airport");
                            currentPlane.setCurrentAssignmentCode(currentFlight.getDestinationPortCode());

                            break;
                        }
                    }
                }
            }

            /* If the Flight hasn't arrived, check if the Flight is the most recent Departure. If so, adjust data */

            else if (currentFlight.getDepartTime() <= currentTime)
            {
                for (Flight flight : terminalData.getFlightManifest())
                {
                    if ((flight.getDepartTime() > currentFlight.getDepartTime())
                            && (flight.getDepartTime() <= currentTime)
                            && (flight.getAssociatedPlaneCode().equals(currentFlight.getAssociatedPlaneCode())))
                    {
                        inferior = true;

                        break;
                    }
                }

                if (!inferior)
                {
                    for (Plane currentPlane : terminalData.getPlaneManifest())
                    {
                        if (currentFlight.getAssociatedPlaneCode().equals(currentPlane.getPlaneID()))
                        {
                            currentPlane.setCurrentAssignmentType("Flight");
                            currentPlane.setCurrentAssignmentCode(currentFlight.getFlightID());

                            break;
                        }
                    }
                }
            }
        }

        /* Perform the user-input function */

        switch (userIn)
        {
            /* Load Data */

            case 1:
                try
                {
                    String fileData = null;

                    Scanner fileNamer = new Scanner(System.in);

                    System.out.println("Please input the file name or directory address to load from:\n");
                    String fileName = fileNamer.nextLine();

                    File newFile = new File(fileName);
                    Scanner fileReader = new Scanner(newFile);

                    fileData = fileReader.nextLine();
                    fileReader.close();

                    terminalData.toTerminalData(fileData);

                    System.out.println("File read successfully.");
                }

                catch(Exception e)
                {
                    System.out.println("Error: invalid entry");
                }

                break;

            /* Save Data */

            case 2:
                try
                {
                    String fileData = terminalData.toFileData();

                    Scanner fileNamer = new Scanner(System.in);

                    System.out.println("Please input the file name or directory address to save to:\n");
                    String fileName = fileNamer.nextLine();

                    File newFile = new File(fileName);

                    if(newFile.createNewFile())
                    {
                        FileWriter fileWriter = new FileWriter(fileName);
                        fileWriter.write(fileData);
                        fileWriter.close();

                        System.out.println("File written successfully.");
                    }

                    else
                    {
                        System.out.println("Error: file name already exists");
                    }
                }

                catch(Exception e)
                {
                    System.out.println("Error: invalid entry");
                }

                break;

            /* Change System Time */

            case 3:
                System.out.println("Input new data (previous time = " + terminalData.getSystemTime() + "): ");

                currentTime = Double.parseDouble(userInput.getInput("R", -999999999, 999999999));

                terminalData.setSystemTime(currentTime);

                break;

            /* Report Menu */

            case 4:
                ArrayList<String> reportOptionsList = new ArrayList<>();

                reportOptionsList.add("- Comprehensive Reports");
                reportOptionsList.add("- Comprehensive Search");
                reportOptionsList.add("- Ticket Reports");
                reportOptionsList.add("- Passenger Reports");
                reportOptionsList.add("- Flight Reports");
                reportOptionsList.add("- Plane Reports");
                reportOptionsList.add("- Airport Reports");
                reportOptionsList.add("- Return to Main Menu");

                ReportMenu reportMenu = new ReportMenu(reportOptionsList, "Report Menu:", "Option #", terminalData);

                reportMenu.runMenu();

                break;

            /* Ticket Menu */

            case 5:
                ArrayList<String> ticketOptionsList = new ArrayList<String>();

                ticketOptionsList.add("- New Ticket");
                ticketOptionsList.add("- Modify Ticket");
                ticketOptionsList.add("- Search Tickets");
                ticketOptionsList.add("- Display Ticket");
                ticketOptionsList.add("- Display Ticket Manifest");
                ticketOptionsList.add("- Delete Ticket");
                ticketOptionsList.add("- Return to Main Menu");

                TicketMenu ticketMenu = new TicketMenu(ticketOptionsList,"Ticket Menu:","Option #",terminalData);

                ticketMenu.runMenu();

                break;

            /* Passenger Menu */

            case 6:
                ArrayList<String> passengerOptionsList = new ArrayList<String>();

                passengerOptionsList.add("- New Passenger");
                passengerOptionsList.add("- Modify Passenger");
                passengerOptionsList.add("- Search Passengers");
                passengerOptionsList.add("- Display Passenger");
                passengerOptionsList.add("- Display Passenger Manifest");
                passengerOptionsList.add("- Delete Passenger");
                passengerOptionsList.add("- Return to Main Menu");

                PassengerMenu passengerMenu = new PassengerMenu(passengerOptionsList,"Passenger Menu:","Option #",terminalData);

                passengerMenu.runMenu();

                break;

            /* Flight Menu */

            case 7:
                ArrayList<String> flightOptionsList = new ArrayList<String>();

                flightOptionsList.add("- New Flight");
                flightOptionsList.add("- Modify Flight");
                flightOptionsList.add("- Search Flights");
                flightOptionsList.add("- Display Flight");
                flightOptionsList.add("- Display Flight Manifest");
                flightOptionsList.add("- Delete Flight");
                flightOptionsList.add("- Seat Passengers");
                flightOptionsList.add("- Return to Main Menu");

                FlightMenu flightMenu = new FlightMenu(flightOptionsList,"Flight Menu:","Option #",terminalData);

                flightMenu.runMenu();

                break;

            /* Plane Menu */

            case 8:
                ArrayList<String> planeOptionsList = new ArrayList<String>();

                planeOptionsList.add("- New Plane");
                planeOptionsList.add("- Modify Plane");
                planeOptionsList.add("- Search Planes");
                planeOptionsList.add("- Display Plane");
                planeOptionsList.add("- Display Plane Manifest");
                planeOptionsList.add("- Delete Plane");
                planeOptionsList.add("- Return to Main Menu");

                PlaneMenu planeMenu = new PlaneMenu(planeOptionsList,"Plane Menu:","Option #",terminalData);

                planeMenu.runMenu();

                break;

            /* Airport Menu */

            case 9:
                ArrayList<String> airportOptionsList = new ArrayList<String>();

                airportOptionsList.add("- New Airport");
                airportOptionsList.add("- Modify Airport");
                airportOptionsList.add("- Search Airports");
                airportOptionsList.add("- Display Airport");
                airportOptionsList.add("- Display Airport Manifest");
                airportOptionsList.add("- Delete Airport");
                airportOptionsList.add("- Return to Main Menu");

                AirportMenu airportMenu = new AirportMenu(airportOptionsList,"Airport Menu","Option #",terminalData);

                airportMenu.runMenu();

                break;
        }

        setMenuPrint("Main Menu: "+ "\n" + "[ Current System Time- " + terminalData.getSystemTime() + " ]");
    }
}