package MainPackage;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * The PlaneMenu class is used to host plane-specific functions. It can read, write, delete, search, list, and view
 * plane objects within the terminalData structure, and hosts the submenu structure for taking command input from the
 * user.
 * @author Kyle Dean, Mason Herbig, and Lwesso Mukeni
 */

public class PlaneMenu extends Menu
{
    /* terminalData's location is always passed in from the MainMenu */
    TerminalData terminalData;

    /**
     * This is the constructor for the PlaneMenu class
     * @param optionsList The list of options presented to the user
     * @param menuPrint The header for the menu
     * @param altPrint The header for each option in the menu
     * @param terminalData the TerminalData structure being accessed by the menu
     */
    public PlaneMenu(ArrayList<String> optionsList, String menuPrint, String altPrint, TerminalData terminalData)
    {
        super(optionsList, menuPrint, altPrint);
        this.terminalData = terminalData;
    }

    /**
     menuOption is defined for the FlightMenu class as containing methods to call the appropriate methods to:
     * 1- Add a Plane
     * 2- Edit a Plane
     * 3- Search for a specific Plane by checking a search string against the Plane's data
     * 4- View a Plane when given a specific Plane ID
     * 5- View a list of all Planes in the "manifest" (terminalData)
     * 6- Delete a Plane when given a specific Plane ID
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
     * The addOption method is triggered from the menu selection, and tries to add a new Plane to the manifest when
     * given a unique Plane ID from the user. If the Plane ID is already in use, the process is not carried out.
     * The Plane ID string supplied is also limited in the following ways:
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

            System.out.println("Adding Planes. Input New Plane Code, or enter nothing to exit:");
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
                for (Plane i : terminalData.getPlaneManifest())
                {
                    if (i.getPlaneID().equals(userInput))
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
                ArrayList<Plane> newManifest = terminalData.getPlaneManifest();
                newManifest.add(new Plane(userInput," ",0," "," "));
                terminalData.setPlaneManifest(newManifest);
            }
        }
        while(inputFlag);
    }

    /**
     * The editOption method is triggered from the menu selection, and tries to edit a Plane within the manifest when
     * given a specified Plane ID from the user. It does not allow the user to change the Plane's ID, but its other
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

            System.out.println("Editing Plane. Input Plane Code, or type % to exit:");

            String userInput = getter.getInput("S",0,0);

            if (userInput.equals("%"))
            {
                break;
            }

            /* Try to find the object in the manifest */

            else
            {
                for (int i = 0; i < terminalData.getPlaneManifest().size(); i++)
                {
                    if (terminalData.getPlaneManifest().get(i).getPlaneID().equals(userInput))
                    {
                        found = i;
                        break;
                    }
                }
            }

            /* Report if the object isn't found */

            if(found == -1)
            {
                System.out.println("Plane Code not found");
                inputFlag = true;
            }

            /* If all checks are passed, create a dialogue which allows the user to edit the object */

            else
            {
                /* Set the object's data, and create a string of acceptable inputs from the user in the dialogue */

                Plane currentPlane = terminalData.getPlaneManifest().get(found);

                String planeReadIn = "1%Model%model%"
                        + "2%Passenger Limit%passenger limit%Limit%limit%"
                        + "3%Current Assignment Code%Assignment Code%assignment code%current assignment code%"
                        + "4%Current Assignment Type%Assignment Type%assignment type%current assignment type%"
                        + "5%Exit%Exit Menu%Exit Edit Menu%exit%exit menu%exit edit menu%";

                /* Using reader to create an array from the delimited string of acceptable inputs */

                Reader reader = new Reader(planeReadIn,"%");
                ArrayList<String> attributesList;
                attributesList = reader.readOutList();
                attributesList.add("%");

                /* Run a menu-like dialogue for the edit process */

                boolean editRunning = true;

                do
                {
                    /* First, display the object's data, give dialogue instructions, and initialize an object to get
                     * further user input */

                    String planeData = currentPlane.getPlaneID() + ":\n1. Model- " + currentPlane.getModel()
                            + "\n2. Passenger Limit- " + currentPlane.getPassLimit()
                            + "\n3. Current Assignment Code- " + currentPlane.getCurrentAssignmentCode()
                            + "\n4. Current Assignment Type- " + currentPlane.getCurrentAssignmentType()
                            + "\n5. Exit Edit Menu";

                    System.out.println("Editing Plane " + planeData + "\nInput the label or index of the field you wish to access, or type % to exit:");

                    String fieldInput = getter.getInput(attributesList);
                    String currentSetter = "%";

                    /* Next, edit the data the user tried to access, or exit */

                    switch (fieldInput)
                    {
                        /* If the user tries to edit data, get further input to set the new value, rejecting illegal
                         * inputs which contain delimiter characters "%", "|", or "/" */

                        case "1", "Model", "model":
                            System.out.println("Input new data: ");
                            currentSetter = getter.getInput("S",0,0);

                            if (!(currentSetter.contains("/")) && !(currentSetter.contains("|")) && !(currentSetter.contains("%")))
                            {
                                currentPlane.setModel(currentSetter);
                            }

                            else
                            {
                                System.out.println("Input Error: Code contains illegal characters '%', '|', or '/'. Please try again.");
                            }

                            break;

                        case "2", "Passenger Limit", "passenger limit", "limit", "Limit":
                            System.out.println("Input new data: ");
                            currentSetter = getter.getInput("Z",0,999);

                            if (!(currentSetter.contains("/")) && !(currentSetter.contains("|")) && !(currentSetter.contains("%")))
                            {
                                try
                                {
                                    currentPlane.setPassLimit(Integer.parseInt(currentSetter));
                                }

                                catch (Exception e)
                                {
                                    System.out.println("Input Error: Code is not a non-negative integer. Please try again.");
                                }
                            }

                            else
                            {
                                System.out.println("Input Error: Code contains illegal characters '%', '|', or '/'. Please try again.");
                            }

                            break;

                        case "3", "Current Assignment Code", "current assignment code", "Assignment Code", "assignment code":
                            if (currentPlane.getCurrentAssignmentType().equals("Flight"))
                            {
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
                                        currentPlane.setCurrentAssignmentCode(currentSetter);
                                    }

                                    else
                                    {
                                        System.out.println("Input Error: Code contains illegal characters '%', '|', or '/'. Please try again.");
                                    }
                                }

                                break;
                            }

                            else if (currentPlane.getCurrentAssignmentType().equals("Airport"))
                            {
                                System.out.println("Input new data: ");
                                ArrayList<String> portCodesList = new ArrayList<String>();

                                for (Airport i : terminalData.getAirportManifest())
                                {
                                    portCodesList.add(i.getAirportID());
                                }

                                portCodesList.add("");
                                currentSetter = getter.getInput(portCodesList);

                                if(currentSetter.isEmpty())
                                {
                                    System.out.println("Input Error: No code input.");
                                }

                                else
                                {
                                    if (!(currentSetter.contains("/")) && !(currentSetter.contains("|")) && !(currentSetter.contains("%")))
                                    {
                                        currentPlane.setCurrentAssignmentCode(currentSetter);
                                    }

                                    else
                                    {
                                        System.out.println("Input Error: Code contains illegal characters '%', '|', or '/'. Please try again.");
                                    }
                                }

                                break;
                            }

                            else
                            {
                                System.out.println("Error: Current Assignment Type not set");
                                break;
                            }

                        case "4", "Current Assignment Type", "current assignment type", "Assignment type", "assignment type":
                            System.out.println("Input new data, using only 'Flight' or 'Airport': ");
                            ArrayList<String> typesList = new ArrayList<String>();

                            typesList.add("Flight");
                            typesList.add("Airport");
                            typesList.add("airport");
                            typesList.add("flight");
                            typesList.add("");

                            currentSetter = getter.getInput(typesList);

                            if (currentSetter.equals("airport"))
                            {
                                currentSetter = "Airport";
                            }

                            else if (currentSetter.equals("flight"))
                            {
                                currentSetter = "Flight";
                            }

                            if(currentSetter.isEmpty())
                            {
                                System.out.println("Input Error: No code input.");
                            }

                            else
                            {
                                currentPlane.setCurrentAssignmentType(currentSetter);
                            }
                            break;

                        case "5", "Exit Edit Menu", "Exit", "Exit Menu", "exit", "exit menu", "exit edit menu", "%":
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
     * searchOption gets user input and checks it against Plane data in the manifest. If there are any matches
     * which contain the input string, the Plane is added to a list which is displayed to the user after all
     * Planes have been checked.
     * @param terminalData the TerminalData structure being searched by the searchOption
     */
    public void searchOption(TerminalData terminalData)
    {
        /* First, initialize the ArrayList to which will hold the indices of the found objects,
        and the objects to get user input. Then, present a dialogue to the user to prompt their input */

        ArrayList<Integer> foundIndices = new ArrayList<Integer>();
        System.out.println("Searching planes. Input search query: ");
        GetInput getter = new GetInput();

        /* Get user input, and check against manifest data for the provided search term */

        String searchQuery = getter.getInput("S",0,0);

        for (int i = 0; i < terminalData.getPlaneManifest().size(); i++)
        {
            Plane currentPlane = terminalData.getPlaneManifest().get(i);

            if (currentPlane.getPlaneID().contains(searchQuery)
                    || currentPlane.getModel().contains(searchQuery)
                    || Integer.toString(currentPlane.getPassLimit()).contains(searchQuery)
                    || currentPlane.getCurrentAssignmentCode().contains(searchQuery)
                    || currentPlane.getCurrentAssignmentType().contains(searchQuery))
            {
                foundIndices.add(i);
            }
        }

        /* Output the list of potential matches */

        System.out.println(foundIndices.size() + " possible matching plane(s):\n");

        for (Integer i : foundIndices)
        {
            Plane currentPlane = terminalData.getPlaneManifest().get(i);

            String planeData = currentPlane.getPlaneID() + ":\n1. Model- " + currentPlane.getModel()
                    + "\n2. Passenger Limit- " + currentPlane.getPassLimit()
                    + "\n3. Current Assignment Code- " + currentPlane.getCurrentAssignmentCode()
                    + "\n4. Current Assignment Type- " + currentPlane.getCurrentAssignmentType()
                    + "\n";

            System.out.println("Manifest Index " + i + ":\nPlane ID- " + planeData);
        }
    }

    /**
     * viewOption allows the user to input a specific Plane ID and view the data for that Plane alone
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

            System.out.println("Viewing Planes. Input Plane Code, or type % to exit:");

            String userInput = getter.getInput("S", 0, 0);

            /* Allow the user to exit the dialogue */

            if (userInput.equals("%"))
            {
                break;
            }

            /* Check for the provided object's ID in the manifest */

            else
            {
                for (int i = 0; i < terminalData.getPlaneManifest().size(); i++)
                {
                    if (terminalData.getPlaneManifest().get(i).getPlaneID().equals(userInput))
                    {
                        found = i;
                        break;
                    }
                }
            }

            /* If no such ID is found, display as much to the user */

            if (found == -1)
            {
                System.out.println("Plane Code not found");
                inputFlag = true;
            }

            /* Otherwise, display the found object's data */

            else
            {
                Plane currentPlane = terminalData.getPlaneManifest().get(found);

                String planeData = currentPlane.getPlaneID() + ":\n1. Model- " + currentPlane.getModel()
                        + "\n2. Passenger Limit- " + currentPlane.getPassLimit()
                        + "\n3. Current Assignment Code- " + currentPlane.getCurrentAssignmentCode()
                        + "\n4. Current Assignment Type- " + currentPlane.getCurrentAssignmentType()
                        + "\n";

                System.out.println("Viewing Plane " + planeData);
            }
        }
        while (inputFlag);
    }

    /**
     * viewListOption allows the user to see all Planes in the Plane Manifest (see terminalData)
     * @param terminalData the TerminalData structure being viewed by the viewOption
     */
    public void viewListOption(TerminalData terminalData)
    {
        /* Output the data for all objects in the manifest, one by one, after a header */

        System.out.println("Viewing Plane List:\n");

        for (int i = 0; i < terminalData.getPlaneManifest().size(); i++)
        {
            Plane currentPlane = terminalData.getPlaneManifest().get(i);

            String planeData = currentPlane.getPlaneID() + ":\n1. Model- " + currentPlane.getModel()
                    + "\n2. Passenger Limit- " + currentPlane.getPassLimit()
                    + "\n3. Current Assignment Code- " + currentPlane.getCurrentAssignmentCode()
                    + "\n4. Current Assignment Type- " + currentPlane.getCurrentAssignmentType()
                    + "\n";

            System.out.println("Manifest Index " + i + ":\nPlane ID- " + planeData);
        }
    }

    /**
     * deleteOption allows the user to delete a Plane from the manifest when given a specified Plane ID
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

            System.out.println("Deleting Planes. Input Plane Code, or type % to exit:");

            String userInput = getter.getInput("S", 0, 0);

            if (userInput.equals("%"))
            {
                break;
            }

            /* If the user did not exit, check against all objects in the manifest to find the match */

            else
            {
                for (int i = 0; i < terminalData.getPlaneManifest().size(); i++)
                {
                    if (terminalData.getPlaneManifest().get(i).getPlaneID().equals(userInput))
                    {
                        found = i;
                        break;
                    }
                }
            }

            /* If there is no match, output as much to the user */

            if (found == -1)
            {
                System.out.println("Plane Code not found");
                inputFlag = true;
            }

            /* If a match is found, delete the object from the manifest */

            else
            {
                terminalData.getPlaneManifest().remove(found);

                System.out.println("Plane " + userInput + " deleted.");
            }
        }
        while (inputFlag);
    }
}