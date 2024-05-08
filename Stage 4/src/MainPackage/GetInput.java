package MainPackage;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * The GetInput class contains methods to screen user inputs by data type and value range.
 * @author Kyle Dean, Mason Herbig, and Lwesso Mukeni
 */

public class GetInput
{
    /**
     * getInput takes a formatCode to check the user's input for validity, and takes a range of numeric values which
     * limit the user's input to that range, if numeric.
     * @param formatCode The desired format of the user's input.
     *                   "S"- String
     *                   "R"- Real numbers
     *                   "N"- Integers, but without 0
     *                   "Z"- All integers
     * @param rangeStart The minimum value the user's input can be, if numeric.
     * @param rangeEnd The maximum value the user's input can be, if numeric.
     * @return the user's input string.
     */

    String getInput(String formatCode, double rangeStart, double rangeEnd)
    {
        String userInput;
        boolean inputStatus = true;
        boolean inputFlag;

        do
        {
            inputFlag = false;
            Scanner in = new Scanner (System.in);
            userInput = in.nextLine();

            if (userInput == null)
            {
                inputFlag = true;
            }

            else if (formatCode.equals("S"))
            {
                inputStatus = false;
            }

            else
            {
                if (formatCode.equals("R"))
                {
                    try
                    {
                        if (rangeEnd < Double.parseDouble(userInput) || rangeStart > Double.parseDouble(userInput))
                        {
                            inputFlag = true;
                        }
                    }
                    catch(Exception e)
                    {
                        inputFlag = true;
                    }
                }

                else if (formatCode.equals("N"))
                {
                    try
                    {
                        if (rangeEnd < Integer.parseInt(userInput) || rangeStart > Integer.parseInt(userInput) || Integer.parseInt(userInput) == 0)
                        {
                            inputFlag = true;
                        }
                    }
                    catch(Exception e)
                    {
                        inputFlag = true;
                    }
                }

                else if (formatCode.equals("Z"))
                {
                    try
                    {
                        if (rangeEnd < Integer.parseInt(userInput) || rangeStart > Integer.parseInt(userInput))
                        {
                            inputFlag = true;
                        }
                    }
                    catch(Exception e)
                    {
                        inputFlag = true;
                    }
                }
            }

            if (inputFlag)
            {
                System.out.println("Input Error. Please try again.");
            }
            else
            {
                inputStatus = false;
            }
        }
        while(inputStatus);
        return userInput;
    }

    /**
     * getInput can accept an integer in place of a formatCode, which sends the method to the appropriate getInput
     * for the corresponding formatCode.
     * @param i The integer corresponding to the desired format of the user's input.
     * @param rangeStart The minimum value the user's input can be, if numeric.
     * @param rangeEnd The maximum value the user's input can be, if numeric.
     * @return the user's input string.
     */
    String getInput(int i, double rangeStart, double rangeEnd)
    {
        String formatCode = "S";
        switch(i)
        {
            case 0:
                formatCode = "S";
                break;
            case 1:
                formatCode = "R";
                break;
            case 2:
                formatCode = "N";
                break;
            case 3:
                formatCode = "Z";
                break;
        }
        return getInput(formatCode, rangeStart, rangeEnd);
    }

    /**
     * This getInput method compares user input to a list of strings, only allowing input if the user's input matches
     * one of the provided strings.
     * @param formatCode The ArrayList of Strings which count as acceptable inputs.
     * @return the user's input string
     */
    String getInput(ArrayList<String> formatCode)
    {
        String userInput;
        boolean inputStatus = true;
        boolean inputFlag;

        do
        {
            inputFlag = false;
            Scanner in = new Scanner(System.in);
            userInput = in.nextLine();

            if (userInput == null)
            {
                inputFlag = true;
            }

            else
            {
                int checkCounter = 0;
                for(int i = 0; i < formatCode.size(); i++)
                {
                    if (formatCode.get(i).equals(userInput))
                    {
                        checkCounter += 1;
                    }
                    if (checkCounter > 0)
                    {
                        break;
                    }
                }
                if (checkCounter == 0)
                {
                    inputFlag = true;
                }
            }

            if (inputFlag)
            {
                System.out.println("Input Error. Please try again.");
            }
            else
            {
                inputStatus = false;
            }
        }
        while(inputStatus);
        return userInput;
    }

    /**
     * getInput can also be used with a list of acceptable inputs, in order to check if the input matches. This method
     * takes an additional integer parameter and returns a number based on the list and the input string, as given
     * below.
     * @param formatCode The ArrayList of Strings which count as acceptable inputs.
     * @param i Using this method:
     *          i = 0 gives back the number of times the input string is found in the arraylist.
     *          i = -1 returns the last position at which the item was found.
     *          i = 1 returns the first position at which the item was found.
     *          i = (anything else) returns the ith index value at which the item was found, unless that is beyond the
     *          bounds of the list
     *          The method always returns -1 if the item is not found.
     * @return an integer value related to the parameter i and the user's input as compared to the formatCode list.
     */
    int getInput(ArrayList<String> formatCode, int i)
    {
        String userInput;
        int checkCounter = 0;
        int placeMarker = 0;
        ArrayList<Integer> placeMarkers = new ArrayList<Integer>();

        boolean inputStatus = true;
        boolean inputFlag;

        do
        {
            inputFlag = false;
            Scanner in = new Scanner(System.in);
            userInput = in.nextLine();

            if (userInput == null)
            {
                inputFlag = true;
            }

            else
            {
                for(int k = 0; k < formatCode.size(); k++)
                {
                    if (formatCode.get(k).equals(userInput))
                    {
                        checkCounter += 1;
                        placeMarkers.add(k);
                        placeMarker = k;
                    }
                }
                if (checkCounter == 0)
                {
                    i = -1;
                }
            }

            if (inputFlag)
            {
                System.out.println("Input Error. Please try again.");
            }
            else
            {
                inputStatus = false;
            }
        }
        while(inputStatus);

        if (i == 0)
        {
            return checkCounter;
        }
        else if ((i == -1) && (checkCounter != 0))
        {
            return placeMarker;
        }
        else if (i != (-1) && (i < placeMarkers.size()))
        {
            return placeMarkers.get(i);
        }
        else if (i >= placeMarkers.size())
        {
            return (-i);
        }
        return i;
    }
}