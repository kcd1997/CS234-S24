/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package MainPackage;

import java.util.ArrayList;

/**
 *
 *
 */
public class GUIFlightFunctions {
    
    public String addOption(TerminalData terminalData,
            javax.swing.JTextField addTextInput, javax.swing.JTextField addTextInput1,
            javax.swing.JTextField addTextInput2, javax.swing.JTextField addTextInput3,
            javax.swing.JTextField addTextInput4, javax.swing.JTextField addTextInput5,
            javax.swing.JTextField addTextInput6, javax.swing.JTextField addTextInput7,
            javax.swing.JTextField editTextInput, javax.swing.JTextField editTextInput1,
            javax.swing.JTextField editTextInput2, javax.swing.JTextField editTextInput3,
            javax.swing.JTextField editTextInput4, javax.swing.JTextField editTextInput5,
            javax.swing.JTextField editTextInput6, javax.swing.JTextField editTextInput7)
    {
        
        for (Flight i : terminalData.getFlightManifest())
        {
            if (i.getFlightID().equals(addTextInput.getText()))
            {
                return "Input Error: Non-Unique Code. Please try again.";
            }
        }
        
        double departTime;
        
        try
        {
            departTime = Double.parseDouble(addTextInput1.getText());
        }
        catch(Exception e)
        {
            departTime = 0;
        }
        
        double arriveTime;
        
        try
        {
            arriveTime = Double.parseDouble(addTextInput2.getText());
        }
        catch(Exception e)
        {
            arriveTime = 0;
        }
        
        boolean destinationFlag = true;

        for (Airport i : terminalData.getAirportManifest())
        {
            if (i.getAirportID().equals(addTextInput3.getText()))
            {
                destinationFlag = false;
                break;
            }
        }
        
        if(destinationFlag)
        {
            return ("Input Error: Unable to add airport with unknown destination port code "
                    + addTextInput3.getText());
        }
        
        boolean startingFlag = true;

        for (Airport i : terminalData.getAirportManifest())
        {
            if (i.getAirportID().equals(addTextInput4.getText()))
            {
                startingFlag = false;
                break;
            }
        }
        
        if(startingFlag)
        {
            return ("Input Error: Unable to add airport with unknown starting port code "
                    + addTextInput4.getText());
        }
        
        boolean planeFlag = true;

        for (Plane i : terminalData.getPlaneManifest())
        {
            if (i.getPlaneID().equals(addTextInput5.getText()))
            {
                planeFlag = false;
                break;
            }
        }
        
        if(planeFlag)
        {
            return ("Input Error: Unable to add airport with unknown plane code "
                    + addTextInput5.getText());
        }
        
        ArrayList<Flight> newManifest = terminalData.getFlightManifest();
        newManifest.add(new Flight(addTextInput.getText(), departTime,
                arriveTime, addTextInput3.getText(),
                addTextInput4.getText(), addTextInput5.getText()));
        terminalData.setFlightManifest(newManifest);

        editTextInput.setText(addTextInput.getText());
        editTextInput1.setText(addTextInput1.getText());
        editTextInput2.setText(addTextInput2.getText());
        editTextInput3.setText(addTextInput3.getText());
        editTextInput4.setText(addTextInput4.getText());
        editTextInput5.setText(addTextInput5.getText());
        
        return ("Flight " + addTextInput.getText() + " added successfully. Now editing:");
    }
    
    public String editOption(TerminalData terminalData,
            javax.swing.JTextField editTextInput, javax.swing.JTextField editTextInput1,
            javax.swing.JTextField editTextInput2, javax.swing.JTextField editTextInput3,
            javax.swing.JTextField editTextInput4, javax.swing.JTextField editTextInput5,
            javax.swing.JTextField editTextInput6, javax.swing.JTextField editTextInput7)
    {
        for (int i = 0; i < terminalData.getFlightManifest().size(); i++)
        {
            if (terminalData.getFlightManifest().get(i).getFlightID().equals(editTextInput.getText()))
            {
                double departTime;
        
                try
                {
                    departTime = Double.parseDouble(editTextInput1.getText());
                }
                catch(Exception e)
                {
                    departTime = 0;
                }

                double arriveTime;

                try
                {
                    arriveTime = Double.parseDouble(editTextInput2.getText());
                }
                catch(Exception e)
                {
                    arriveTime = 0;
                }

                boolean destinationFlag = true;

                for (Airport j : terminalData.getAirportManifest())
                {
                    if (j.getAirportID().equals(editTextInput3.getText()))
                    {
                        destinationFlag = false;
                        break;
                    }
                }

                if(destinationFlag)
                {
                    return ("Input Error: Unable to modify airport to exhibit unknown destination port code "
                            + editTextInput3.getText());
                }

                boolean startingFlag = true;

                for (Airport j : terminalData.getAirportManifest())
                {
                    if (j.getAirportID().equals(editTextInput4.getText()))
                    {
                        startingFlag = false;
                        break;
                    }
                }

                if(startingFlag)
                {
                    return ("Input Error: Unable to modify airport to exhibit unknown starting port code "
                            + editTextInput4.getText());
                }

                boolean planeFlag = true;

                for (Plane j : terminalData.getPlaneManifest())
                {
                    if (j.getPlaneID().equals(editTextInput5.getText()))
                    {
                        planeFlag = false;
                        break;
                    }
                }

                if(planeFlag)
                {
                    return ("Input Error: Unable to modify airport to exhibit unknown plane code "
                            + editTextInput5.getText());
                }    
                
                terminalData.getFlightManifest().set(i,
                new Flight(editTextInput.getText(), departTime,
                arriveTime, editTextInput3.getText(),
                editTextInput4.getText(), editTextInput5.getText()));
                
                return ("Flight " + editTextInput.getText() + " edited successfully.");
            }
        }
        
        return ("Error. No matching flights found.");
    }
    
    public String deleteOption(TerminalData terminalData,
            javax.swing.JTextField editTextInput)
    {
        for (Flight i : terminalData.getFlightManifest())
        {
            if (i.getFlightID().equals(editTextInput.getText()))
            {
                terminalData.getFlightManifest().remove(i);
                
                return ("Flight " + editTextInput.getText() + " deleted successfully.");
            }
        }
        
        return ("Error. No matching flights found.");
    }
    
        
    public String searchOption(TerminalData terminalData, 
            javax.swing.JTextField editTextInput, javax.swing.JTextField editTextInput1,
            javax.swing.JTextField editTextInput2, javax.swing.JTextField editTextInput3,
            javax.swing.JTextField editTextInput4, javax.swing.JTextField editTextInput5,
            javax.swing.JTextField editTextInput6, javax.swing.JTextField editTextInput7,
            javax.swing.JTextField viewTextInput, javax.swing.JTextField searchTextInput)
    {
        if (viewTextInput.getText().isEmpty())
        {
            String searchQuery = searchTextInput.getText();
            
            ArrayList<Integer> foundIndices = new ArrayList<Integer>();

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

            String returnString = "Possible matching flight(s):\n";

            for (Integer i : foundIndices)
            {
                Flight currentFlight = terminalData.getFlightManifest().get(i);

                String flightData = currentFlight.getFlightID() + ":\n1. Departure Time- " + currentFlight.getDepartTime()
                    + "\n2. Arrival Time- " + currentFlight.getArriveTime()
                    + "\n3. Destination Airport Code- " + currentFlight.getDestinationPortCode()
                    + "\n4. Starting Airport Code- " + currentFlight.getStartingPortCode()
                    + "\n5. Plane Code- " + currentFlight.getAssociatedPlaneCode()
                    + "\n";

                returnString += "\nManifest Index " + i + ":\nFlight ID- " + flightData;
            }
            
            return returnString;
        }
        
        else
        {
            for (int i = 0; i < terminalData.getFlightManifest().size(); i++)
            {
                if (terminalData.getFlightManifest().get(i).getFlightID().equals(viewTextInput.getText()))
                {
                    Flight currentFlight = terminalData.getFlightManifest().get(i);
                    
                    editTextInput.setText(currentFlight.getFlightID());
                    editTextInput1.setText(Double.toString(currentFlight.getDepartTime()));
                    editTextInput2.setText(Double.toString(currentFlight.getArriveTime()));
                    editTextInput3.setText(currentFlight.getDestinationPortCode());
                    editTextInput4.setText(currentFlight.getStartingPortCode());
                    editTextInput5.setText(currentFlight.getAssociatedPlaneCode());

                   String flightData = currentFlight.getFlightID() + ":\n1. Departure Time- " + currentFlight.getDepartTime()
                    + "\n2. Arrival Time- " + currentFlight.getArriveTime()
                    + "\n3. Destination Airport Code- " + currentFlight.getDestinationPortCode()
                    + "\n4. Starting Airport Code- " + currentFlight.getStartingPortCode()
                    + "\n5. Plane Code- " + currentFlight.getAssociatedPlaneCode()
                    + "\n";

                    String returnString = "Viewing Flight " + flightData;

                    return returnString;
                }
            }
        }
        
        return ("Error. No matching flights found.");
    }
}