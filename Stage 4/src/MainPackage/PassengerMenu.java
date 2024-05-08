package MainPackage;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * The PassengerMenu class is used to host passenger-specific functions. It can read, write, delete, search, list, and
 * view passenger objects within the terminalData structure, and hosts the submenu structure for taking command input
 * from the user.
 * @author Kyle Dean, Mason Herbig, and Lwesso Mukeni
 */

public class PassengerMenu extends Menu
{
    /* terminalData's location is always passed in from the MainMenu */
    TerminalData terminalData;

    /**
     * This is the constructor for the PassengerMenu class
     * @param optionsList The list of options presented to the user
     * @param menuPrint The header for the menu
     * @param altPrint The header for each option in the menu
     * @param terminalData the TerminalData structure being accessed by the menu
     */
    public PassengerMenu(ArrayList<String> optionsList, String menuPrint, String altPrint, TerminalData terminalData)
    {
        super(optionsList, menuPrint, altPrint);
        this.terminalData = terminalData;
    }

    /**
     menuOption is defined for the PassengerMenu class as containing methods to call the appropriate methods to:
     * 1- Add a Passenger
     * 2- Edit a Passenger
     * 3- Search for a specific Passenger by checking a search string against the Passenger's data
     * 4- View a Passenger when given a specific Passenger ID
     * 5- View a list of all Passenger in the "manifest" (terminalData)
     * 6- Delete a Passenger when given a specific Passenger ID
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
     * The addOption method is triggered from the menu selection, and tries to add a new Passenger to the manifest when
     * given a unique Passenger ID from the user. If the Passenger ID is already in use, the process is not carried out.
     * The Passenger ID string supplied is also limited in the following ways:
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

            System.out.println("Adding Passengers. Input New Passenger Code, or enter nothing to exit:");
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
                for (Passenger i : terminalData.getPassengerManifest())
                {
                    if (i.getPassengerID().equals(userInput))
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
                ArrayList<Passenger> newManifest = terminalData.getPassengerManifest();
                newManifest.add(new Passenger(userInput," "," "," "," "," "," "," "));
                terminalData.setPassengerManifest(newManifest);
            }
        }
        while(inputFlag);
    }

    /**
     * The editOption method is triggered from the menu selection, and tries to edit a Passenger within the manifest
     * when given a specified Passenger ID from the user. It does not allow the user to change the Passenger's ID, but
     * its other data may be changed.
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

            System.out.println("Editing Passengers. Input Passenger Code, or type % to exit:");

            String userInput = getter.getInput("S",0,0);

            if (userInput.equals("%"))
            {
                break;
            }

            /* Try to find the object in the manifest */

            else
            {
                for (int i = 0; i < terminalData.getPassengerManifest().size(); i++)
                {
                    if (terminalData.getPassengerManifest().get(i).getPassengerID().equals(userInput))
                    {
                        found = i;
                        break;
                    }
                }
            }

            /* Report if the object isn't found */

            if(found == -1)
            {
                System.out.println("Passenger Code not found");
                inputFlag = true;
            }

            /* If all checks are passed, create a dialogue which allows the user to edit the object */

            else
            {
                /* Set the object's data, and create a string of acceptable inputs from the user in the dialogue */

                Passenger currentPass = terminalData.getPassengerManifest().get(found);
                String passReadIn = "1%Name%name%"
                        + "2%DOB%dob%"
                        + "3%Gender%gender%"
                        + "4%Phone Number%Phone%phone%phone number%"
                        + "5%Email Address%Email%email%email address%"
                        + "6%Home Address%Home%home%home address%"
                        + "7%Government ID Number%ID%ID Number%Government ID%government id%government id number%id%id number%"
                        + "8%Exit%Exit Menu%Exit Edit Menu%exit%exit menu%exit edit menu%";

                /* Using reader to create an array from the delimited string of acceptable inputs */

                Reader reader = new Reader(passReadIn,"%");
                ArrayList<String> attributesList;
                attributesList = reader.readOutList();
                attributesList.add("%");

                /* Run a menu-like dialogue for the edit process */

                boolean editRunning = true;

                do
                {
                    /* First, display the object's data, give dialogue instructions, and initialize an object to get
                     * further user input */

                    String passData = currentPass.getPassengerID() + ":\n1. Name- " + currentPass.getName()
                            + "\n2. DOB- " + currentPass.getDOB()
                            + "\n3. Gender- " + currentPass.getGender()
                            + "\n4. Phone Number- " + currentPass.getPhone()
                            + "\n5. Email Address- " + currentPass.getEmail()
                            + "\n6. Home Address- " + currentPass.getHome()
                            + "\n7. Government ID Number- " + currentPass.getGovID()
                            + "\n8. Exit Edit Menu";

                    System.out.println("Editing Passenger " + passData + "\nInput the label or index of the field you wish to access, or type % to exit:");

                    String fieldInput = getter.getInput(attributesList);
                    String currentSetter = "%";

                    /* Next, edit the data the user tried to access, or exit */

                    switch (fieldInput)
                    {
                        /* If the user tries to edit data, get further input to set the new value, rejecting illegal
                         * inputs which contain delimiter characters "%", "|", or "/" */

                        case "1", "Name", "name":
                            System.out.println("Input new data: ");
                            currentSetter = getter.getInput("S",0,0);

                            if (!(currentSetter.contains("/")) && !(currentSetter.contains("|")) && !(currentSetter.contains("%"))) {
                                currentPass.setName(currentSetter);
                            }

                            else
                            {
                                System.out.println("Input Error: Code contains illegal characters '%', '|', or '/'. Please try again.");
                            }
                            break;

                        case "2", "DOB", "dob":
                            System.out.println("Input new data: ");
                            currentSetter = getter.getInput("S",0,0);

                            if (!(currentSetter.contains("/")) && !(currentSetter.contains("|")) && !(currentSetter.contains("%")))
                            {
                                currentPass.setDOB(currentSetter);
                            }

                            else
                            {
                                System.out.println("Input Error: Code contains illegal characters '%', '|', or '/'. Please try again.");
                            }
                            break;

                        case "3", "Gender", "gender":
                            System.out.println("Input new data: ");
                            currentSetter = getter.getInput("S",0,0);

                            if (!(currentSetter.contains("/")) && !(currentSetter.contains("|")) && !(currentSetter.contains("%")))
                            {
                                currentPass.setGender(currentSetter);
                            }

                            else
                            {
                                System.out.println("Input Error: Code contains illegal characters '%', '|', or '/'. Please try again.");
                            }
                            break;

                        case "4", "Phone Number", "Phone", "phone number", "phone":
                            System.out.println("Input new data: ");
                            currentSetter = getter.getInput("S",0,0);

                            if (!(currentSetter.contains("/")) && !(currentSetter.contains("|")) && !(currentSetter.contains("%")))
                            {
                                currentPass.setPhone(currentSetter);
                            }

                            else
                            {
                                System.out.println("Input Error: Code contains illegal characters '%', '|', or '/'. Please try again.");
                            }
                            break;

                        case "5", "Email Address", "Email", "email address", "email":
                            System.out.println("Input new data: ");
                            currentSetter = getter.getInput("S",0,0);

                            if (!(currentSetter.contains("/")) && !(currentSetter.contains("|")) && !(currentSetter.contains("%")))
                            {
                                currentPass.setEmail(currentSetter);
                            }

                            else
                            {
                                System.out.println("Input Error: Code contains illegal characters '%', '|', or '/'. Please try again.");
                            }
                            break;

                        case "6", "Home Address", "Home", "home address", "home":
                            System.out.println("Input new data: ");
                            currentSetter = getter.getInput("S",0,0);

                            if (!(currentSetter.contains("/")) && !(currentSetter.contains("|")) && !(currentSetter.contains("%")))
                            {
                                currentPass.setHome(currentSetter);
                            }

                            else
                            {
                                System.out.println("Input Error: Code contains illegal characters '%', '|', or '/'. Please try again.");
                            }
                            break;

                        case "7", "Government ID Number", "ID", "Government ID", "ID Number", "id", "id number", "government id", "government id number":
                            System.out.println("Input new data: ");
                            currentSetter = getter.getInput("S",0,0);

                            if (!(currentSetter.contains("/")) && !(currentSetter.contains("|")) && !(currentSetter.contains("%")))
                            {
                                currentPass.setGovID(currentSetter);
                            }

                            else
                            {
                                System.out.println("Input Error: Code contains illegal characters '%', '|', or '/'. Please try again.");
                            }
                            break;

                        case "8", "Exit Edit Menu", "Exit", "Exit Menu", "exit", "exit menu", "exit edit menu", "%":
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
     * searchOption gets user input and checks it against Passenger data in the manifest. If there are any matches
     * which contain the input string, the Passenger is added to a list which is displayed to the user after all
     * Passenger have been checked.
     * @param terminalData the TerminalData structure being searched by the searchOption
     */
    public void searchOption(TerminalData terminalData)
    {
        /* First, initialize the ArrayList to which will hold the indices of the found objects,
        and the objects to get user input. Then, present a dialogue to the user to prompt their input */

        ArrayList<Integer> foundIndices = new ArrayList<Integer>();
        System.out.println("Searching passengers. Input search query: ");
        GetInput getter = new GetInput();

        /* Get user input, and check against manifest data for the provided search term */

        String searchQuery = getter.getInput("S",0,0);

        for (int i = 0; i < terminalData.getPassengerManifest().size(); i++)
        {
            Passenger currentPass = terminalData.getPassengerManifest().get(i);

            if (currentPass.getPassengerID().contains(searchQuery)
                    || currentPass.getName().contains(searchQuery)
                    || currentPass.getDOB().contains(searchQuery)
                    || currentPass.getGender().contains(searchQuery)
                    || currentPass.getPhone().contains(searchQuery)
                    || currentPass.getEmail().contains(searchQuery)
                    || currentPass.getHome().contains(searchQuery)
                    || currentPass.getGovID().contains(searchQuery))
            {
                foundIndices.add(i);
            }
        }

        /* Output the list of potential matches */

        System.out.println(foundIndices.size() + " possible matching passenger(s):\n");

        for (Integer i : foundIndices)
        {
            Passenger currentPass = terminalData.getPassengerManifest().get(i);

            String passData = currentPass.getPassengerID() + "\nName- " + currentPass.getName()
                    + "\nDOB- " + currentPass.getDOB()
                    + "\nGender- " + currentPass.getGender()
                    + "\nPhone Number- " + currentPass.getPhone()
                    + "\nEmail Address- " + currentPass.getEmail()
                    + "\nHome Address- " + currentPass.getHome()
                    + "\nGovernment ID Number- " + currentPass.getGovID()
                    + "\n";

            System.out.println("Manifest Index " + i + ":\nPassenger ID- " + passData);
        }
    }

    /**
     * viewOption allows the user to input a specific Passenger ID and view the data for that Passenger alone
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

            System.out.println("Viewing Passengers. Input Passenger Code, or type % to exit:");

            String userInput = getter.getInput("S", 0, 0);

            /* Allow the user to exit the dialogue */

            if (userInput.equals("%"))
            {
                break;
            }

            /* Check for the provided object's ID in the manifest */

            else
            {
                for (int i = 0; i < terminalData.getPassengerManifest().size(); i++)
                {
                    if (terminalData.getPassengerManifest().get(i).getPassengerID().equals(userInput))
                    {
                        found = i;
                        break;
                    }
                }
            }

            /* If no such ID is found, display as much to the user */

            if (found == -1)
            {
                System.out.println("Passenger Code not found");
                inputFlag = true;
            }

            /* Otherwise, display the found object's data */

            else
            {
                Passenger currentPass = terminalData.getPassengerManifest().get(found);

                String passData = currentPass.getPassengerID() + ":\n1. Name- " + currentPass.getName()
                        + "\n2. DOB- " + currentPass.getDOB()
                        + "\n3. Gender- " + currentPass.getGender()
                        + "\n4. Phone Number- " + currentPass.getPhone()
                        + "\n5. Email Address- " + currentPass.getEmail()
                        + "\n6. Home Address- " + currentPass.getHome()
                        + "\n7. Government ID Number- " + currentPass.getGovID()
                        + "\n";

                System.out.println("Viewing Passenger " + passData);
            }
        }
        while (inputFlag);
    }

    /**
     * viewListOption allows the user to see all Passengers in the Passenger Manifest (see terminalData)
     * @param terminalData the TerminalData structure being viewed by the viewOption
     */
    public void viewListOption(TerminalData terminalData)
    {
        /* Output the data for all objects in the manifest, one by one, after a header */

        System.out.println("Viewing Passenger List:\n");

        for (int i = 0; i < terminalData.getPassengerManifest().size(); i++)
        {
            Passenger currentPass = terminalData.getPassengerManifest().get(i);

            String passData = currentPass.getPassengerID() + "\nName- " + currentPass.getName()
                    + "\nDOB- " + currentPass.getDOB()
                    + "\nGender- " + currentPass.getGender()
                    + "\nPhone Number- " + currentPass.getPhone()
                    + "\nEmail Address- " + currentPass.getEmail()
                    + "\nHome Address- " + currentPass.getHome()
                    + "\nGovernment ID Number- " + currentPass.getGovID()
                    + "\n";

            System.out.println("Manifest Index " + i + ":\nPassenger ID- " + passData);
        }
    }

    /**
     * deleteOption allows the user to delete a Passenger from the manifest when given a specified Passenger ID
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

            System.out.println("Deleting Passengers. Input Passenger Code, or type % to exit:");

            String userInput = getter.getInput("S", 0, 0);

            if (userInput.equals("%"))
            {
                break;
            }

            /* If the user did not exit, check against all objects in the manifest to find the match */

            else
            {
                for (int i = 0; i < terminalData.getPassengerManifest().size(); i++)
                {
                    if (terminalData.getPassengerManifest().get(i).getPassengerID().equals(userInput))
                    {
                        found = i;
                        break;
                    }
                }
            }

            /* If there is no match, output as much to the user */

            if (found == -1)
            {
                System.out.println("Passenger Code not found");
                inputFlag = true;
            }

            /* If a match is found, delete the object from the manifest */

            else
            {
                terminalData.getPassengerManifest().remove(found);

                System.out.println("Passenger " + userInput + " deleted.");
            }
        }
        while (inputFlag);
    }
}