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
public class GUIPlaneFunctions {
    
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
        
        for (Plane i : terminalData.getPlaneManifest())
        {
            if (i.getPlaneID().equals(addTextInput.getText()))
            {
                return "Input Error: Non-Unique Code. Please try again.";
            }
        }
        
        int passLimit;
        
        try
        {
            passLimit = Integer.parseInt(addTextInput2.getText());
        }
        catch(Exception e)
        {
            passLimit = 0;
        }
        
        boolean codeFlag = true;
        
        if(addTextInput4.getText().equals("Airport"))
        {
            for (Airport i : terminalData.getAirportManifest())
            {
                if (i.getAirportID().equals(addTextInput3.getText()))
                {
                    codeFlag = false;
                    break;
                }
            }
        }
        
        else if(addTextInput4.getText().equals("Flight"))
        {
            for (Flight i : terminalData.getFlightManifest())
            {
                if (i.getFlightID().equals(addTextInput3.getText()))
                {
                    codeFlag = false;
                    break;
                }
            }
        }
                
        else if(addTextInput4.getText().isEmpty())
        {
            if(addTextInput3.getText().isEmpty())
            {
                codeFlag = false;
            }
            
            else
            {
                return ("Input Error: Unable to add plane with associated assignment code " +  addTextInput3.getText() + " but no associated assignment type.");
            }
        }
        
        if(codeFlag)
        {
            return ("Input Error: Unable to add plane with unknown associated assignment code "
                    + addTextInput3.getText());
        }
        
        
        ArrayList<Plane> newManifest = terminalData.getPlaneManifest();
        newManifest.add(new Plane(addTextInput.getText(), addTextInput1.getText(),
                passLimit, addTextInput3.getText(),
                addTextInput4.getText()));
        terminalData.setPlaneManifest(newManifest);

        editTextInput.setText(addTextInput.getText());
        editTextInput1.setText(addTextInput1.getText());
        editTextInput2.setText(addTextInput2.getText());
        editTextInput3.setText(addTextInput3.getText());
        editTextInput4.setText(addTextInput4.getText());
        
        return ("Plane " + addTextInput.getText() + " added successfully. Now editing:");
    }
    
    public String editOption(TerminalData terminalData,
            javax.swing.JTextField editTextInput, javax.swing.JTextField editTextInput1,
            javax.swing.JTextField editTextInput2, javax.swing.JTextField editTextInput3,
            javax.swing.JTextField editTextInput4, javax.swing.JTextField editTextInput5,
            javax.swing.JTextField editTextInput6, javax.swing.JTextField editTextInput7)
    {
        for (int i = 0; i < terminalData.getPlaneManifest().size(); i++)
        {
            if (terminalData.getPlaneManifest().get(i).getPlaneID().equals(editTextInput.getText()))
            {
                int passLimit;
        
                try
                {
                    passLimit = Integer.parseInt(editTextInput1.getText());
                }
                catch(Exception e)
                {
                    passLimit = 0;
                }

                boolean codeFlag = true;

                if(editTextInput4.getText().equals("Airport"))
                {
                    for (Airport j : terminalData.getAirportManifest())
                    {
                        if (j.getAirportID().equals(editTextInput3.getText()))
                        {
                            codeFlag = false;
                            break;
                        }
                    }
                }

                else if(editTextInput4.getText().equals("Flight"))
                {
                    for (Flight j : terminalData.getFlightManifest())
                    {
                        if (j.getFlightID().equals(editTextInput3.getText()))
                        {
                            codeFlag = false;
                            break;
                        }
                    }
                }

                else if(editTextInput4.getText().isEmpty())
                {
                    if(editTextInput3.getText().isEmpty())
                    {
                        codeFlag = false;
                    }

                    else
                    {
                        return ("Input Error: Unable to modify plane to exxhibit associated assignment code " +  editTextInput3.getText() + " with no associated assignment type.");
                    }
                }

                if(codeFlag)
                {
                    return ("Input Error: Unable to modify plane to exhibit unknown associated assignment code "
                            + editTextInput3.getText());
                }

                
                terminalData.getPlaneManifest().set(i,
                new Plane(editTextInput.getText(), editTextInput1.getText(),
                passLimit, editTextInput3.getText(),
                editTextInput4.getText()));
                
                return ("Plane " + editTextInput.getText() + " edited successfully.");
            }
        }
        
        return ("Error. No matching planes found.");
    }
    
    public String deleteOption(TerminalData terminalData,
            javax.swing.JTextField editTextInput)
    {
        for (Plane i : terminalData.getPlaneManifest())
        {
            if (i.getPlaneID().equals(editTextInput.getText()))
            {
                terminalData.getPlaneManifest().remove(i);
                
                return ("Plane " + editTextInput.getText() + " deleted successfully.");
            }
        }
        
        return ("Error. No matching planes found.");
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

            String returnString = "Possible matching plane(s):\n";

            for (Integer i : foundIndices)
            {
                Plane currentPlane = terminalData.getPlaneManifest().get(i);

                String planeData = currentPlane.getPlaneID() + ":\n1. Model- " + currentPlane.getModel()
                    + "\n2. Passenger Limit- " + currentPlane.getPassLimit()
                    + "\n3. Current Assignment Code- " + currentPlane.getCurrentAssignmentCode()
                    + "\n4. Current Assignment Type- " + currentPlane.getCurrentAssignmentType()
                    + "\n";

                returnString += "\nManifest Index " + i + ":\nPlane ID- " + planeData;
            }
            
            return returnString;
        }
        
        else
        {
            for (int i = 0; i < terminalData.getPlaneManifest().size(); i++)
            {
                if (terminalData.getPlaneManifest().get(i).getPlaneID().equals(viewTextInput.getText()))
                {
                    Plane currentPlane = terminalData.getPlaneManifest().get(i);
                    
                    editTextInput.setText(currentPlane.getPlaneID());
                    editTextInput1.setText(currentPlane.getModel());
                    editTextInput2.setText(Integer.toString(currentPlane.getPassLimit()));
                    editTextInput3.setText(currentPlane.getCurrentAssignmentCode());
                    editTextInput4.setText(currentPlane.getCurrentAssignmentType());

                   String planeData = currentPlane.getPlaneID() + ":\n1. Model- " + currentPlane.getModel()
                    + "\n2. Passenger Limit- " + currentPlane.getPassLimit()
                    + "\n3. Current Assignment Code- " + currentPlane.getCurrentAssignmentCode()
                    + "\n4. Current Assignment Type- " + currentPlane.getCurrentAssignmentType()
                    + "\n";

                    String returnString = "Viewing Plane " + planeData;

                    return returnString;
                }
            }
        }
        
        return ("Error. No matching planes found.");
    }
}