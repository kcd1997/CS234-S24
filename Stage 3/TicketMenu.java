import java.util.ArrayList;
import java.util.Scanner;

/**
 * The TicketMenu class is used to host ticket-specific functions. It can read, write, delete, search, list, and view
 * ticket objects within the terminalData structure, and hosts the submenu structure for taking command input from the
 * user.
 * @author Kyle Dean, Mason Herbig, and Lwesso Mukeni
 */

public class TicketMenu extends Menu
{
    /* terminalData's location is always passed in from the MainMenu */
    TerminalData terminalData;

    /**
     * This is the constructor for the TicketMenu class
     * @param optionsList The list of options presented to the user
     * @param menuPrint The header for the menu
     * @param altPrint The header for each option in the menu
     * @param terminalData the TerminalData structure being accessed by the menu
     */
    public TicketMenu(ArrayList<String> optionsList, String menuPrint, String altPrint, TerminalData terminalData)
    {
        super(optionsList, menuPrint, altPrint);
        this.terminalData = terminalData;
    }

    /**
     menuOption is defined for the FlightMenu class as containing methods to call the appropriate methods to:
     * 1- Add a Ticket
     * 2- Edit a Ticket
     * 3- Search for a specific Ticket by checking a search string against the Ticket's data
     * 4- View a Ticket when given a specific Ticket ID
     * 5- View a list of all Tickets in the "manifest" (terminalData)
     * 6- Delete a Ticket when given a specific Ticket ID
     * 7- (Automatically added by the Menu class) Exit the Submenu
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
        }
    }

    /**
     * The addOption method is triggered from the menu selection, and tries to add a new Ticket to the manifest when
     * given a unique Ticket ID from the user. If the Ticket ID is already in use, the process is not carried out.
     * The Ticket ID string supplied is also limited in the following ways:
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

            System.out.println("Adding Tickets. Input New Ticket Code, or enter nothing to exit:");
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
                for (Ticket i : terminalData.getTicketManifest())
                {
                    if (i.getTicketID().equals(userInput))
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
                ArrayList<Ticket> newManifest = terminalData.getTicketManifest();
                newManifest.add(new Ticket(userInput,false," "," ",(byte)0,(byte)0));
                terminalData.setTicketManifest(newManifest);
            }
        }
        while(inputFlag);
    }

    /**
     * The editOption method is triggered from the menu selection, and tries to edit a Ticket within the manifest when
     * given a specified Ticket ID from the user. It does not allow the user to change the Ticket's ID, but its other
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

            System.out.println("Editing Tickets. Input Ticket Code, or type % to exit:");

            String userInput = getter.getInput("S",0,0);

            if (userInput.equals("%"))
            {
                break;
            }

            /* Try to find the object in the manifest */

            else
            {
                for (int i = 0; i < terminalData.getTicketManifest().size(); i++)
                {
                    if (terminalData.getTicketManifest().get(i).getTicketID().equals(userInput))
                    {
                        found = i;
                        break;
                    }
                }
            }

            /* Report if the object isn't found */

            if(found == -1)
            {
                System.out.println("Ticket Code not found");
                inputFlag = true;
            }

            /* If all checks are passed, create a dialogue which allows the user to edit the object */

            else
            {
                /* Set the object's data, and create a string of acceptable inputs from the user in the dialogue */

                Ticket currentTicket = terminalData.getTicketManifest().get(found);
                String ticketReadIn = "1%Paid Status%Status%status%paid%Paid%paid status%payment%Payment%Pay%pay%"
                        + "2%Flight Code%flight code%flight%Flight%"
                        + "3%Passenger Code%passenger code%passenger%Passenger%"
                        + "4%Seating Compartment%seating%Seating%seating compartment%seat compartment%compartment%Seat Compartment%Compartment%"
                        + "5%Checked Bags%checked bags%bags%Bags%"
                        + "6%Exit%Exit Menu%Exit Edit Menu%exit%exit menu%exit edit menu%";

                /* Using reader to create an array from the delimited string of acceptable inputs */

                Reader reader = new Reader(ticketReadIn,"%");
                ArrayList<String> attributesList;
                attributesList = reader.readOutList();
                attributesList.add("%");

                /* Run a menu-like dialogue for the edit process */

                boolean editRunning = true;

                do
                {
                    /* First, display the object's data, give dialogue instructions, and initialize an object to get
                     * further user input */

                    String ticketData = currentTicket.getTicketID() + ":\n1. Paid Status- " + currentTicket.isPaidStatus()
                            + "\n2. Flight Code- " + currentTicket.getFlightCode()
                            + "\n3. Passenger Code- " + currentTicket.getPassengerCode()
                            + "\n4. Seating Compartment- " + currentTicket.getSeatCompartment()
                            + "\n5. Checked Bags- " + currentTicket.getCheckBags()
                            + "\n6. Exit Edit Menu";

                    System.out.println("Editing Ticket " + ticketData + "\nInput the label or index of the field you wish to access, or type % to exit:");

                    String fieldInput = getter.getInput(attributesList);
                    String currentSetter = "%";

                    /* Next, edit the data the user tried to access, or exit */

                    switch (fieldInput)
                    {
                        /* If the user tries to edit data, get further input to set the new value, rejecting illegal
                         * inputs which contain delimiter characters "%", "|", or "/" */

                        case "1", "Paid Status", "Payment", "Pay", "Paid", "Status", "paid status", "payment", "pay", "paid", "status":
                            System.out.println("Input new data: ");
                            currentSetter = getter.getInput("S",0,0);

                            if (currentSetter.equalsIgnoreCase("true") || currentSetter.equals("1"))
                            {
                                currentTicket.setPaidStatus(true);
                            }

                            else if (currentSetter.equalsIgnoreCase("false") || currentSetter.equals("0"))
                            {
                                currentTicket.setPaidStatus(false);
                            }

                            else
                            {
                                System.out.println("Input Error. Please only input true or false.");
                            }
                            break;

                        case "2", "Flight Code", "Flight", "flight", "flight code":
                            System.out.println("Input new data: ");
                            ArrayList<String> flightCodesList = new ArrayList<String>();

                            for (Flight i : terminalData.getFlightManifest())
                            {
                                flightCodesList.add(i.getFlightID());
                            }

                            flightCodesList.add("");
                            currentSetter = getter.getInput(flightCodesList);

                            if(currentSetter.isEmpty())
                            {
                                System.out.println("Input Error: No code input.");
                            }

                            else
                            {
                                if (!(currentSetter.contains("/")) && !(currentSetter.contains("|")) && !(currentSetter.contains("%")))
                                {
                                    currentTicket.setFlightCode(currentSetter);
                                }

                                else
                                {
                                    System.out.println("Input Error: Code contains illegal characters '%', '|', or '/'. Please try again.");
                                }
                            }
                            break;

                        case "3", "Passenger Code", "passenger code", "Passenger", "passenger":
                            System.out.println("Input new data: ");
                            ArrayList<String> passCodesList = new ArrayList<String>();

                            for (Passenger i : terminalData.getPassengerManifest())
                            {
                                passCodesList.add(i.getPassengerID());
                            }

                            passCodesList.add("");
                            currentSetter = getter.getInput(passCodesList);

                            if(currentSetter.isEmpty())
                            {
                                System.out.println("Input Error: No code input.");
                            }

                            else
                            {
                                if (!(currentSetter.contains("/")) && !(currentSetter.contains("|")) && !(currentSetter.contains("%")))
                                {
                                    currentTicket.setPassengerCode(currentSetter);
                                }

                                else
                                {
                                    System.out.println("Input Error: Code contains illegal characters '%', '|', or '/'. Please try again.");
                                }
                            }
                            break;

                        case "4", "Seating Compartment", "seating compartment", "seat compartment", "compartment", "Seat Compartment", "Compartment", "seating", "Seating":
                            System.out.println("Input new data: ");

                            currentSetter = getter.getInput("Z",1,99);
                            byte seatByte = Byte.parseByte(currentSetter);

                            currentTicket.setSeatCompartment(seatByte);
                            break;

                        case "5", "Checked Bags", "checked bags", "bags", "Bags":
                            System.out.println("Input new data: ");

                            currentSetter = getter.getInput("Z",0,99);
                            byte bagByte = Byte.parseByte(currentSetter);

                            currentTicket.setCheckBags(bagByte);
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
     * searchOption gets user input and checks it against Ticket data in the manifest. If there are any matches
     * which contain the input string, the Ticket is added to a list which is displayed to the user after all
     * Tickets have been checked.
     * @param terminalData the TerminalData structure being searched by the searchOption
     */
    public void searchOption(TerminalData terminalData)
    {
        /* First, initialize the ArrayList to which will hold the indices of the found objects,
        and the objects to get user input. Then, present a dialogue to the user to prompt their input */

        ArrayList<Integer> foundIndices = new ArrayList<Integer>();
        System.out.println("Searching tickets. Input search query: ");
        GetInput getter = new GetInput();

        /* Get user input, and check against manifest data for the provided search term */

        String searchQuery = getter.getInput("S",0,0);

        for (int i = 0; i < terminalData.getTicketManifest().size(); i++)
        {
            Ticket currentTicket = terminalData.getTicketManifest().get(i);

            if (currentTicket.getTicketID().contains(searchQuery)
                    || currentTicket.getFlightCode().contains(searchQuery)
                    || currentTicket.getPassengerCode().contains(searchQuery)
                    || Byte.toString(currentTicket.getSeatCompartment()).equals(searchQuery)
                    || Byte.toString(currentTicket.getCheckBags()).equals(searchQuery))
            {
                foundIndices.add(i);
            }
        }

        /* Output the list of potential matches */

        System.out.println(foundIndices.size() + " possible matching ticket(s):\n");

        for (Integer i : foundIndices)
        {
            Ticket currentTicket = terminalData.getTicketManifest().get(i);

            String ticketData = currentTicket.getTicketID() + ":\n1. Paid Status- " + currentTicket.isPaidStatus()
                    + "\n2. Flight Code- " + currentTicket.getFlightCode()
                    + "\n3. Passenger Code- " + currentTicket.getPassengerCode()
                    + "\n4. Seating Compartment- " + currentTicket.getSeatCompartment()
                    + "\n5. Checked Bags- " + currentTicket.getCheckBags()
                    + "\n";

            System.out.println("Manifest Index " + i + ":\nTicket ID- " + ticketData);
        }
    }

    /**
     * viewOption allows the user to input a specific Ticket ID and view the data for that Ticket alone
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

            System.out.println("Viewing Tickets. Input Ticket Code, or type % to exit:");

            String userInput = getter.getInput("S", 0, 0);

            /* Allow the user to exit the dialogue */

            if (userInput.equals("%"))
            {
                break;
            }

            /* Check for the provided object's ID in the manifest */

            else
            {
                for (int i = 0; i < terminalData.getTicketManifest().size(); i++)
                {
                    if (terminalData.getTicketManifest().get(i).getTicketID().equals(userInput))
                    {
                        found = i;
                        break;
                    }
                }
            }

            /* If no such ID is found, display as much to the user */

            if (found == -1)
            {
                System.out.println("Ticket Code not found");
                inputFlag = true;
            }

            /* Otherwise, display the found object's data */

            else
            {
                Ticket currentTicket = terminalData.getTicketManifest().get(found);

                String ticketData = currentTicket.getTicketID() + ":\n1. Paid Status- " + currentTicket.isPaidStatus()
                        + "\n2. Flight Code- " + currentTicket.getFlightCode()
                        + "\n3. Passenger Code- " + currentTicket.getPassengerCode()
                        + "\n4. Seating Compartment- " + currentTicket.getSeatCompartment()
                        + "\n5. Checked Bags- " + currentTicket.getCheckBags()
                        + "\n";

                System.out.println("Viewing Ticket " + ticketData);
            }
        }
        while (inputFlag);
    }

    /**
     * viewListOption allows the user to see all Tickets in the Ticket Manifest (see terminalData)
     * @param terminalData the TerminalData structure being viewed by the viewOption
     */
    public void viewListOption(TerminalData terminalData)
    {
        /* Output the data for all objects in the manifest, one by one, after a header */

        System.out.println("Viewing Ticket List:\n");

        for (int i = 0; i < terminalData.getTicketManifest().size(); i++)
        {
            Ticket currentTicket = terminalData.getTicketManifest().get(i);

            String ticketData = currentTicket.getTicketID() + ":\n1. Paid Status- " + currentTicket.isPaidStatus()
                    + "\n2. Flight Code- " + currentTicket.getFlightCode()
                    + "\n3. Passenger Code- " + currentTicket.getPassengerCode()
                    + "\n4. Seating Compartment- " + currentTicket.getSeatCompartment()
                    + "\n5. Checked Bags- " + currentTicket.getCheckBags()
                    + "\n";

            System.out.println("Manifest Index " + i + ":\nTicket ID- " + ticketData);
        }
    }

    /**
     * deleteOption allows the user to delete a Ticket from the manifest when given a specified Ticket ID
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

            System.out.println("Deleting Tickets. Input Ticket Code, or type % to exit:");

            String userInput = getter.getInput("S", 0, 0);

            if (userInput.equals("%"))
            {
                break;
            }

            /* If the user did not exit, check against all objects in the manifest to find the match */

            else
            {
                for (int i = 0; i < terminalData.getTicketManifest().size(); i++)
                {
                    if (terminalData.getTicketManifest().get(i).getTicketID().equals(userInput))
                    {
                        found = i;
                        break;
                    }
                }
            }

            /* If there is no match, output as much to the user */

            if (found == -1)
            {
                System.out.println("Ticket Code not found");
                inputFlag = true;
            }

            /* If a match is found, delete the object from the manifest */

            else
            {
                terminalData.getTicketManifest().remove(found);

                System.out.println("Ticket " + userInput + " deleted.");
            }
        }
        while (inputFlag);
    }
}