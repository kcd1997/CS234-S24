import java.util.ArrayList;
import java.util.Scanner;

/**
 * The FlightMenu class is used to host flight-specific functions. It can read, write, delete, search, list, and view
 * flight objects within the terminalData structure, and hosts the submenu structure for taking command input from the
 * user.
 * @author Kyle Dean, Mason Herbig, and Lwesso Mukeni
 */

public class FlightMenu extends Menu
{
    /* terminalData's location is always passed in from the MainMenu */
    TerminalData terminalData;

    /**
     * This is the constructor for the FlightMenu class
     * @param optionsList The list of options presented to the user
     * @param menuPrint The header for the menu
     * @param altPrint The header for each option in the menu
     * @param terminalData the TerminalData structure being accessed by the menu
     */
    public FlightMenu(ArrayList<String> optionsList, String menuPrint, String altPrint, TerminalData terminalData)
    {
        super(optionsList, menuPrint, altPrint);
        this.terminalData = terminalData;
    }

    /**
     menuOption is defined for the FlightMenu class as containing methods to call the appropriate methods to:
     * 1- Add a Flight
     * 2- Edit a Flight
     * 3- Search for a specific Flight by checking a search string against the Flight's data
     * 4- View a Flight when given a specific Flight ID
     * 5- View a list of all Flights in the "manifest" (terminalData)
     * 6- Delete a Flight when given a specific Flight ID
     * 7- Seat passengers on a Flight, displaying a list of passengers boarding by compartment
     * 8- (Automatically added by the Menu class) Exit the Submenu
     * @param userIn The integer value provided by the runMenu function to determine the function the menu performs.
     */
    public void menuOption(int userIn)
    {
        switch(userIn)
        {
            case 1:
                addOption(terminalData);
                break;

            case 2:
                editOption(terminalData);
                break;

            case 3:
                searchOption(terminalData);
                break;

            case 4:
                viewOption(terminalData);
                break;

            case 5:
                viewListOption(terminalData);
                break;

            case 6:
                deleteOption(terminalData);
                break;

            case 7:
                seatPassengersOption(terminalData);
                break;
        }
    }

    /**
     * The addOption method is triggered from the menu selection, and tries to add a new Flight to the manifest when
     * given a unique Flight ID from the user. If the Flight ID is already in use, the process is not carried out.
     * The Flight ID string supplied is also limited in the following ways:
     * -It may not contain "%", "|", or "/" characters, as these are used as delimiters in the file storage structure.
     * -It may not be empty.
     * @param terminalData the TerminalData structure being modified by the addOption
     */
    public void addOption(TerminalData terminalData)
    {
        /* First, the user's input is taken and checked against ID codes in the manifest */

        boolean inputFlag = false;

        do
        {
            /* Get user input */

            Scanner in = new Scanner(System.in);

            System.out.println("Adding Flights. Input New Flight Code, or enter nothing to exit:");
            String userInput = in.nextLine();

            /* Ensure user input does not contain illegal characters, and is not empty */

            if (userInput.contains("%") || userInput.contains("|") || userInput.contains("/"))
            {
                inputFlag = true;
                System.out.println("Input Error: Code contains illegal characters '%', '|', or '/'. Please try again.");
            }

            else if (userInput.isEmpty())
            {
                break;
            }

            /* Check the manifest data to make sure the supplied ID code is unique */

            else
            {
                for (Flight i : terminalData.getFlightManifest())
                {
                    if (i.getFlightID().equals(userInput))
                    {
                        inputFlag = true;
                        System.out.println("Input Error: Non-Unique Code. Please try again.");
                        break;
                    }

                    else
                    {
                        inputFlag = false;
                    }
                }
            }

            /* If all checks are passed, add the object to the manifest with the supplied ID code */

            if(!inputFlag)
            {
                ArrayList<Flight> newManifest = terminalData.getFlightManifest();
                newManifest.add(new Flight(userInput,0,0," "," "," "));
                terminalData.setFlightManifest(newManifest);
            }
        }
        while(inputFlag);
    }

    /**
     * The editOption method is triggered from the menu selection, and tries to edit a Flight within the manifest when
     * given a specified Flight ID from the user. It does not allow the user to change the Flight's ID, but its other
     * data may be changed.
     * @param terminalData the TerminalData structure being modified by the editOption
     */
    public void editOption(TerminalData terminalData)
    {
        /* First, the user's input is taken and checked against ID codes in the manifest */

        boolean inputFlag = false;

        do
        {
            /* Get user input, optionally allowing the user to exit with "%" */

            inputFlag = false;
            int found = -1;
            GetInput getter = new GetInput();

            System.out.println("Editing Flights. Input Flight Code, or type % to exit:");

            String userInput = getter.getInput("S",0,0);

            if (userInput.equals("%"))
            {
                break;
            }

            /* Try to find the object in the manifest */

            else
            {
                for (int i = 0; i < terminalData.getFlightManifest().size(); i++)
                {
                    if (terminalData.getFlightManifest().get(i).getFlightID().equals(userInput))
                    {
                        found = i;
                        break;
                    }
                }
            }

            /* Report if the object isn't found */

            if(found == -1)
            {
                System.out.println("Flight Code not found");
                inputFlag = true;
            }

            /* If all checks are passed, create a dialogue which allows the user to edit the object */

            else
            {
                /* Set the object's data, and create a string of acceptable inputs from the user in the dialogue */

                Flight currentFlight = terminalData.getFlightManifest().get(found);
                String flightReadIn = "1%Departure Time%departure time%depart time%Depart Time%"
                        + "2%Arrival Time%arrival time%Arrive Time%arrive time%"
                        + "3%Destination%destination%Destination Airport Code%Destination Code%destination airport code%destination code%"
                        + "4%Starting%starting%Starting Airport Code%Starting Code%starting airport code%starting code%"
                        + "5%Plane Code%plane code%Plane%plane%"
                        + "6%Exit%Exit Menu%Exit Edit Menu%exit%exit menu%exit edit menu%";

                /* Using reader to create an array from the delimited string of acceptable inputs */

                Reader reader = new Reader(flightReadIn,"%");
                ArrayList<String> attributesList;
                attributesList = reader.readOutList();
                attributesList.add("%");

                /* Run a menu-like dialogue for the edit process */

                boolean editRunning = true;

                do
                {
                    /* First, display the object's data, give dialogue instructions, and initialize an object to get
                     * further user input */

                    String flightData = currentFlight.getFlightID() + ":\n1. Departure Time- " + currentFlight.getDepartTime()
                            + "\n2. Arrival Time- " + currentFlight.getArriveTime()
                            + "\n3. Destination Airport Code- " + currentFlight.getDestinationPortCode()
                            + "\n4. Starting Airport Code- " + currentFlight.getStartingPortCode()
                            + "\n5. Plane Code- " + currentFlight.getAssociatedPlaneCode()
                            + "\n6. Exit Edit Menu";

                    System.out.println("Editing Flight " + flightData + "\nInput the label or index of the field you wish to access, or type % to exit:");

                    String fieldInput = getter.getInput(attributesList);
                    String currentSetter = "%";

                    /* Next, edit the data the user tried to access, or exit */

                    switch (fieldInput)
                    {
                        /* If the user tries to edit data, get further input to set the new value, rejecting illegal
                         * inputs which contain delimiter characters "%", "|", or "/" */

                        case "1", "Departure Time", "depart time", "departure time", "Depart Time":
                            System.out.println("Input new data: ");
                            currentSetter = getter.getInput("R",-999999999,999999999);

                            if (!(currentSetter.contains("/")) && !(currentSetter.contains("|")) && !(currentSetter.contains("%")))
                            {
                                currentFlight.setDepartTime(Double.parseDouble(currentSetter));
                            }

                            else
                            {
                                System.out.println("Input Error: Code contains illegal characters '%', '|', or '/'. Please try again.");
                            }
                            break;

                        case "2", "Arrival Time", "arrive time", "arrival time", "Arrive Time":
                            System.out.println("Input new data: ");
                            currentSetter = getter.getInput("R",-999999999,999999999);

                            if (!(currentSetter.contains("/")) && !(currentSetter.contains("|")) && !(currentSetter.contains("%")))
                            {
                                currentFlight.setArriveTime(Double.parseDouble(currentSetter));
                            }

                            else
                            {
                                System.out.println("Input Error: Code contains illegal characters '%', '|', or '/'. Please try again.");
                            }
                            break;

                        case "3", "Destination", "destination", "Destination Airport Code", "Destination Code", "destination airport code", "destination code":
                            System.out.println("Input new data: ");
                            ArrayList<String> destinationAirportCodesList = new ArrayList<String>();

                            for (Airport i : terminalData.getAirportManifest())
                            {
                                destinationAirportCodesList.add(i.getAirportID());
                            }

                            destinationAirportCodesList.add("");
                            currentSetter = getter.getInput(destinationAirportCodesList);

                            if(currentSetter.isEmpty())
                            {
                                System.out.println("Input Error: No code input.");
                            }

                            else
                            {
                                if (!(currentSetter.contains("/")) && !(currentSetter.contains("|")) && !(currentSetter.contains("%")))
                                {
                                    currentFlight.setDestinationPortCode(currentSetter);
                                }

                                else
                                {
                                    System.out.println("Input Error: Code contains illegal characters '%', '|', or '/'. Please try again.");
                                }
                            }
                            break;

                        case "4", "Starting", "starting", "Starting Airport Code", "Starting Code", "starting airport code", "starting code":
                            System.out.println("Input new data: ");
                            ArrayList<String> startingAirportCodesList = new ArrayList<String>();

                            for (Airport i : terminalData.getAirportManifest())
                            {
                                startingAirportCodesList.add(i.getAirportID());
                            }

                            startingAirportCodesList.add("");
                            currentSetter = getter.getInput(startingAirportCodesList);

                            if(currentSetter.isEmpty())
                            {
                                System.out.println("Input Error: No code input.");
                            }

                            else
                            {
                                if (!(currentSetter.contains("/")) && !(currentSetter.contains("|")) && !(currentSetter.contains("%")))
                                {
                                    currentFlight.setStartingPortCode(currentSetter);
                                }

                                else
                                {
                                    System.out.println("Input Error: Code contains illegal characters '%', '|', or '/'. Please try again.");
                                }
                            }
                            break;

                        case "5", "Plane Code", "Plane", "plane code", "plane":
                            System.out.println("Input new data: ");
                            ArrayList<String> planeCodesList = new ArrayList<String>();

                            for (Plane i : terminalData.getPlaneManifest())
                            {
                                planeCodesList.add(i.getPlaneID());
                            }

                            planeCodesList.add("");
                            currentSetter = getter.getInput(planeCodesList);

                            if(currentSetter.isEmpty())
                            {
                                System.out.println("Input Error: No code input.");
                            }

                            else
                            {
                                if (!(currentSetter.contains("/")) && !(currentSetter.contains("|")) && !(currentSetter.contains("%")))
                                {
                                    currentFlight.setAssociatedPlaneCode(currentSetter);
                                }

                                else
                                {
                                    System.out.println("Input Error: Code contains illegal characters '%', '|', or '/'. Please try again.");
                                }
                            }
                            break;

                        case "6", "Exit Edit Menu", "Exit", "Exit Menu", "exit", "exit menu", "exit edit menu", "%":
                            editRunning = false;
                            break;
                    }
                }
                while(editRunning);
            }
        }
        while(inputFlag);
    }

    /**
     * searchOption gets user input and checks it against Flight data in the manifest. If there are any matches
     * which contain the input string, the Flight is added to a list which is displayed to the user after all
     * Flights have been checked.
     * @param terminalData the TerminalData structure being searched by the searchOption
     */
    public void searchOption(TerminalData terminalData)
    {
        /* First, initialize the ArrayList to which will hold the indices of the found objects,
        and the objects to get user input. Then, present a dialogue to the user to prompt their input */

        ArrayList<Integer> foundIndices = new ArrayList<Integer>();
        System.out.println("Searching flights. Input search query: ");
        GetInput getter = new GetInput();

        /* Get user input, and check against manifest data for the provided search term */

        String searchQuery = getter.getInput("S",0,0);

        for (int i = 0; i < terminalData.getFlightManifest().size(); i++)
        {
            Flight currentFlight = terminalData.getFlightManifest().get(i);

            if (currentFlight.getFlightID().contains(searchQuery)
                    || Double.toString(currentFlight.getArriveTime()).equals(searchQuery)
                    || Double.toString(currentFlight.getDepartTime()).equals(searchQuery)
                    || currentFlight.getStartingPortCode().contains(searchQuery)
                    || currentFlight.getDestinationPortCode().contains(searchQuery)
                    || currentFlight.getAssociatedPlaneCode().contains(searchQuery))
            {
                foundIndices.add(i);
            }
        }

        /* Output the list of potential matches */

        System.out.println(foundIndices.size() + " possible matching flight(s):\n");

        for (Integer i : foundIndices)
        {
            Flight currentFlight = terminalData.getFlightManifest().get(i);

            String flightData = currentFlight.getFlightID() + ":\n1. Departure Time- " + currentFlight.getDepartTime()
                    + "\n2. Arrival Time- " + currentFlight.getArriveTime()
                    + "\n3. Destination Airport Code- " + currentFlight.getDestinationPortCode()
                    + "\n4. Starting Airport Code- " + currentFlight.getStartingPortCode()
                    + "\n5. Plane Code- " + currentFlight.getAssociatedPlaneCode()
                    + "\n";

            System.out.println("Manifest Index " + i + ":\nFlight ID- " + flightData);
        }
    }

    /**
     * viewOption allows the user to input a specific Flight ID and view the data for that Flight alone
     * @param terminalData the TerminalData structure being viewed by the viewOption
     */
    public void viewOption(TerminalData terminalData)
    {
        /* Present the user a dialogue. They can exit with "%" or input an ID to be checked */

        boolean inputFlag = false;

        do
        {
            inputFlag = false;
            int found = -1;
            GetInput getter = new GetInput();

            System.out.println("Viewing Flights. Input Flight Code, or type % to exit:");

            String userInput = getter.getInput("S", 0, 0);

            /* Allow the user to exit the dialogue */

            if (userInput.equals("%"))
            {
                break;
            }

            /* Check for the provided object's ID in the manifest */

            else
            {
                for (int i = 0; i < terminalData.getFlightManifest().size(); i++)
                {
                    if (terminalData.getFlightManifest().get(i).getFlightID().equals(userInput))
                    {
                        found = i;
                        break;
                    }
                }
            }

            /* If no such ID is found, display as much to the user */

            if (found == -1)
            {
                System.out.println("Flight Code not found");
                inputFlag = true;
            }

            /* Otherwise, display the found object's data */

            else
            {
                Flight currentFlight = terminalData.getFlightManifest().get(found);

                String flightData = currentFlight.getFlightID()
                        + ":\n1. Departure Time- " + currentFlight.getDepartTime()
                        + "\n2. Arrival Time- " + currentFlight.getArriveTime()
                        + "\n3. Destination Airport Code- " + currentFlight.getDestinationPortCode()
                        + "\n4. Starting Airport Code- " + currentFlight.getStartingPortCode()
                        + "\n5. Plane Code- " + currentFlight.getAssociatedPlaneCode()
                        + "\n";

                System.out.println("Viewing Flight " + flightData);
            }
        }
        while (inputFlag);
    }

    /**
     * viewListOption allows the user to see all Flights in the Flight Manifest (see terminalData)
     * @param terminalData the TerminalData structure being viewed by the viewOption
     */
    public void viewListOption(TerminalData terminalData)
    {
        /* Output the data for all objects in the manifest, one by one, after a header */

        System.out.println("Viewing Flight List:\n");

        for (int i = 0; i < terminalData.getFlightManifest().size(); i++)
        {
            Flight currentFlight = terminalData.getFlightManifest().get(i);

            String flightData = currentFlight.getFlightID()
                    + ":\n1. Departure Time- " + currentFlight.getDepartTime()
                    + "\n2. Arrival Time- " + currentFlight.getArriveTime()
                    + "\n3. Destination Airport Code- " + currentFlight.getDestinationPortCode()
                    + "\n4. Starting Airport Code- " + currentFlight.getStartingPortCode()
                    + "\n5. Plane Code- " + currentFlight.getAssociatedPlaneCode()
                    + "\n";

            System.out.println("Manifest Index " + i + ":\nFlight ID- " + flightData);
        }
    }

    /**
     * deleteOption allows the user to delete a Flight from the manifest when given a specified Flight ID
     * @param terminalData the TerminalData structure being viewed by the viewOption
     */
    public void deleteOption(TerminalData terminalData)
    {
        /* First, the user's input is taken and checked against ID codes in the manifest. A dialogue is created which
         * allows the user to exit with "%" */

        boolean inputFlag = false;

        do
        {
            /* Initialize the objects to get user input, and to find the object by supplied input code */

            inputFlag = false;
            int found = -1;
            GetInput getter = new GetInput();

            /* Present the dialogue and get user input */

            System.out.println("Deleting Flights. Input Flight Code, or type % to exit:");

            String userInput = getter.getInput("S", 0, 0);

            if (userInput.equals("%"))
            {
                break;
            }

            /* If the user did not exit, check against all objects in the manifest to find the match */

            else
            {
                for (int i = 0; i < terminalData.getFlightManifest().size(); i++)
                {
                    if (terminalData.getFlightManifest().get(i).getFlightID().equals(userInput))
                    {
                        found = i;
                        break;
                    }
                }
            }

            /* If there is no match, output as much to the user */

            if (found == -1)
            {
                System.out.println("Flight code not found");
                inputFlag = true;
            }

            /* If a match is found, delete the object from the manifest */

            else
            {
                terminalData.getFlightManifest().remove(found);

                System.out.println("Flight " + userInput + " deleted.");
            }
        }
        while (inputFlag);
    }

    /**
     * seatPassengersOptions serves to output a list of passengers on a flight by their seating compartment, allowing
     * the user to quickly see which passengers should be seated in what order.
     * @param terminalData the TerminalData structure being viewed by the seatPassengersOption.
     */
    public void seatPassengersOption(TerminalData terminalData)
    {
        /* First, the user's input is taken and checked against ID codes in the manifest. A dialogue is created which
         * allows the user to exit with "%" */

        boolean inputFlag = false;

        do
        {
            /* Initialize the objects to get user input, and to find the object by supplied input code */

            inputFlag = false;
            int found = -1;
            GetInput getter = new GetInput();

            /* Present the dialogue and get user input */

            System.out.println("Seating Passengers. Input Flight Code, or type % to exit:");

            String userInput = getter.getInput("S", 0, 0);

            if (userInput.equals("%"))
            {
                break;
            }

            /* If the user did not exit, check against all objects in the manifest to find the match */

            else
            {
                for (int i = 0; i < terminalData.getFlightManifest().size(); i++)
                {
                    if (terminalData.getFlightManifest().get(i).getFlightID().equals(userInput))
                    {
                        found = i;
                        break;
                    }
                }
            }

            /* If there is no match, output as much to the user */

            if (found == -1)
            {
                System.out.println("Flight code not found");
                inputFlag = true;
            }

            /* Otherwise, attempt to "seat" passengers on the flight */

            else
            {
                /* Set the currentFlight object, the variable which will hold the index of the Flight's associated
                Plane, and the variable which will hold the count of passengers seated, which will be checked
                against the maximum passenger count of the Plane in use */

                Flight currentFlight = terminalData.getFlightManifest().get(found);

                int planeIndex = -1;
                int passCount = 0;

                /* Create a list which will hold the Flight's Tickets */

                ArrayList<Ticket> currentTickets = new ArrayList<>();

                /* Create variables to limit the range of compartments checked and seated */

                int minCompartment = 1;
                int maxCompartment = 1;

                /* Find the Flight's associated Plane in the manifest */

                for (int i = 0; i < terminalData.getPlaneManifest().size(); i++)
                {
                    if (terminalData.getPlaneManifest().get(i).getPlaneID().equals(currentFlight.getAssociatedPlaneCode()))
                    {
                        planeIndex = i;
                        break;
                    }
                }

                /* Add the Flight's associated Tickets to the list of tickets */

                for(Ticket ticket : terminalData.getTicketManifest())
                {
                    if (ticket.getFlightCode().equals(userInput))
                    {
                        passCount += 1;
                        currentTickets.add(ticket);

                        /* Update the seat compartment limits based on the current Ticket */

                        if (ticket.getSeatCompartment() > maxCompartment)
                        {
                            maxCompartment = ticket.getSeatCompartment();
                        }

                        else if (ticket.getSeatCompartment() < minCompartment)
                        {
                            minCompartment = ticket.getSeatCompartment();
                        }
                    }
                }

                /* If no valid Plane is found, output as much to the user */

                if (planeIndex == -1)
                {
                    System.out.println("Error: Flight has no valid associated Plane Code");
                    inputFlag = true;
                }

                /* Otherwise, begin seating passengers */

                else
                {
                    /* Check against the Plane's passenger limit to ensure the Flight can actually be seated */

                    int currentPassLimit = terminalData.getPlaneManifest().get(planeIndex).getPassLimit();

                    /* If the number of Tickets being seated exceeds the Plane's capacity, output as much to the user */

                    if (currentPassLimit < passCount)
                    {
                        System.out.println("Error: Too many tickets to assign. Please remove at least "
                                + (passCount - currentPassLimit) + " associated tickets to initiate boarding.");
                        inputFlag = true;
                    }

                    /* Catch any invalid entries in seating compartment data */

                    else if (minCompartment < 1)
                    {
                        System.out.println("Error: Seating Compartment data improperly stored as value less than 1.");
                        inputFlag = true;
                    }

                    /* Otherwise, output the boarding data */

                    else
                    {
                        /* Output the Plane data */

                        Plane currentPlane = terminalData.getPlaneManifest().get(planeIndex);

                        String planeData = "Plane Code- " + currentPlane.getPlaneID()
                                + "\nModel- " + currentPlane.getModel()
                                + "\nPassenger Limit- " + currentPlane.getPassLimit()
                                + "\nCurrent Assignment Code- " + currentPlane.getCurrentAssignmentCode()
                                + "\nCurrent Assignment Type- " + currentPlane.getCurrentAssignmentType()
                                + "\n";

                        System.out.println("Flight " + userInput + " now boarding:\n");

                        System.out.println(planeData);

                        int passengerCounter = 0;

                        /* Output Ticket data by compartment */

                        for (int compartmentNumber = minCompartment; compartmentNumber <= maxCompartment; compartmentNumber ++)
                        {
                            System.out.println("Compartment " + compartmentNumber + " seats by Ticket Code:\n");
                            for (Ticket currentTicket : currentTickets)
                            {
                                if (currentTicket.getSeatCompartment() == compartmentNumber)
                                {
                                    String ticketData = "Ticket Code- " + currentTicket.getTicketID()
                                            + "\nPaid Status- " + currentTicket.isPaidStatus()
                                            + "\nPassenger Code- " + currentTicket.getPassengerCode()
                                            + "\nSeating Compartment- " + currentTicket.getSeatCompartment()
                                            + "\nChecked Bags- " + currentTicket.getCheckBags()
                                            + "\n";

                                    passengerCounter += 1;

                                    System.out.println("Ticket Order #" + passengerCounter + ":\n"
                                            + ticketData);
                                }
                            }
                            System.out.println("Finished compartment " + compartmentNumber + "\n");
                        }

                        /* End the reporting, and give the number of passengers seated */

                        System.out.println("Seating protocol complete.\nPassengers seated- " + passengerCounter + "\n");
                    }
                }
            }
        }
        while (inputFlag);
    }
}