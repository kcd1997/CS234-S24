package MainPackage;

import java.util.ArrayList;

/**
 * The Menu class is used to facilitate functional dialogues with the user, taking user inputs and performing assigned
 * functions based on that input. It uses loops and input verification to ensure that the menu is stable and user-
 * friendly. Each subclass of Menu should be assigned its own overwritten version of the menuOption function.
 * @author Kyle Dean, Mason Herbig, and Lwesso Mukeni
 */

public class Menu
{
    /* Initialize the menu's data, including:
        -optionsList: the list of options available to the user (these are just strings which are printed and used
        in the user dialogue)
        -menuPrint: the header printed each time the menu is generated
        -altPrint: the sub-headers printed on each line that gives a menu option to the user. */
    ArrayList<String> optionsList;
    String menuPrint;
    String altPrint;

    /**
     * This is the constructor for the Menu class.
     * @param optionsList The list of options presented to the user
     * @param menuPrint The header for the menu
     * @param altPrint The header for each option in the menu
     */
    Menu(ArrayList<String> optionsList, String menuPrint, String altPrint)
    {
        this.optionsList = optionsList;
        this.menuPrint = menuPrint;
        this.altPrint = altPrint;
    }

    /**
     * getOptionsList is used to retrieve the menu's optionsList variable.
     * @return the menu's optionsList.
     */
    public ArrayList<String> getOptionsList()
    {
        return optionsList;
    }

    /**
     * setOptionsList is used to set the menu's optionsList variable to the provided ArrayList object.
     * @param optionsList the ArrayList to which the menu's optionsList will be changed.
     */
    public void setOptionsList(ArrayList<String> optionsList)
    {
        this.optionsList = optionsList;
    }

    /**
     * getMenuPrint is used to retrieve the string value which is printed as the menu's header.
     * @return the menu's header string.
     */
    public String getMenuPrint()
    {
        return menuPrint;
    }

    /**
     * setMenuPrint is used to set the string value which is printed as the menu's header.
     * @param menuPrint The string value to which this menu's header string will be changed.
     */
    public void setMenuPrint(String menuPrint)
    {
        this.menuPrint = menuPrint;
    }

    /**
     * getAltPrint is used to retrieve the string value which is printed as sub-header for each item in the menu's
     * optionsList.
     * @return the menu's per-item sub-header string.
     */
    public String getAltPrint()
    {
        return altPrint;
    }

    /**
     * setAltPrint is used to set the string value which is printed as sub-header for each item in the menu's
     * optionsList.
     * @param altPrint the string value to which the menu's per-item sub-header string will be set.
     */
    public void setAltPrint(String altPrint)
    {
        this.altPrint = altPrint;
    }

    /**
     * runMenu creates the loop and takes in/verifies user input to call the appropriate menuOption function.
     */
    public void runMenu()
    {
        /* Begin looping to take user input */

        boolean ongoingStatus = true;

        do
        {
            /* Print the header */

            System.out.println(menuPrint);

            /* Print the sub-headers and optionsList strings for each menu option */

            for(int i = 0; i < optionsList.size(); i++)
            {
                System.out.println(altPrint + (i + 1) + optionsList.get(i));
            }

            /* Get user input, only accepting integers matching the menu options provided */

            GetInput getter = new GetInput();
            String userInput = getter.getInput("N",1,optionsList.size());

            int userIn = Integer.parseInt(userInput);

            /* The last option is always the "exit" option */

            if(userIn == optionsList.size())
            {
                ongoingStatus = false;
            }

            /* If the exit option is not selected, run the appropriate menuOption function, passing the userInput
            * integer as the parameter */

            else
            {
                menuOption(userIn);
            }
        }
        while(ongoingStatus);
    }

    /**
     * The menuOption function is left empty and is implemented in subclasses.
     * @param userIn The integer value provided by the runMenu function to determine the function the menu performs.
     */
    public void menuOption(int userIn)
    {
    }
}
