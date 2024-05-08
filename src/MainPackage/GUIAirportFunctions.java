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
public class GUIAirportFunctions {
    
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
        
        for (Airport i : terminalData.getAirportManifest())
        {
            if (i.getAirportID().equals(addTextInput.getText()))
            {
                return "Input Error: Non-Unique Code. Please try again.";
            }
        }
        
        ArrayList<Airport> newManifest = terminalData.getAirportManifest();
        newManifest.add(new Airport(addTextInput.getText(), addTextInput1.getText()));
        terminalData.setAirportManifest(newManifest);

        editTextInput.setText(addTextInput.getText());
        editTextInput1.setText(addTextInput1.getText());
        
        return ("Airport " + addTextInput.getText() + " added successfully. Now editing:");
    }
    
    public String editOption(TerminalData terminalData,
            javax.swing.JTextField editTextInput, javax.swing.JTextField editTextInput1,
            javax.swing.JTextField editTextInput2, javax.swing.JTextField editTextInput3,
            javax.swing.JTextField editTextInput4, javax.swing.JTextField editTextInput5,
            javax.swing.JTextField editTextInput6, javax.swing.JTextField editTextInput7)
    {
        for (int i = 0; i < terminalData.getAirportManifest().size(); i++)
        {
            if (terminalData.getAirportManifest().get(i).getAirportID().equals(editTextInput.getText()))
            {
                terminalData.getAirportManifest().set(i,
                new Airport(editTextInput.getText(), editTextInput1.getText()));
                
                return ("Airport " + editTextInput.getText() + " edited successfully.");
            }
        }
        
        return ("Error. No matching airports found.");
    }
    
    public String deleteOption(TerminalData terminalData,
            javax.swing.JTextField editTextInput)
    {
        for (Airport i : terminalData.getAirportManifest())
        {
            if (i.getAirportID().equals(editTextInput.getText()))
            {
                terminalData.getAirportManifest().remove(i);
                
                return ("Airport " + editTextInput.getText() + " deleted successfully.");
            }
        }
        
        return ("Error. No matching airports found.");
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

            for (int i = 0; i < terminalData.getAirportManifest().size(); i++)
            {
                Airport currentPort = terminalData.getAirportManifest().get(i);

                if (currentPort.getAirportID().contains(searchQuery) || currentPort.getName().contains(searchQuery))
                {
                    foundIndices.add(i);
                }
            }

            String returnString = "Possible matching airport(s):\n";

            for (Integer i : foundIndices)
            {
                Airport currentPort = terminalData.getAirportManifest().get(i);

                String portData = currentPort.getAirportID() + ":\n1. Name- " + currentPort.getName() + "\n";

                returnString += "\nManifest Index " + i + ":\nAirport ID- " + portData;
            }
            
            return returnString;
        }
        
        else
        {
            for (int i = 0; i < terminalData.getAirportManifest().size(); i++)
            {
                if (terminalData.getAirportManifest().get(i).getAirportID().equals(viewTextInput.getText()))
                {
                    Airport currentPort = terminalData.getAirportManifest().get(i);
                    
                    editTextInput.setText(currentPort.getAirportID());
                    editTextInput1.setText(currentPort.getName());

                   String portData = currentPort.getAirportID() + ":\n1. Name- " + currentPort.getName() + "\n";

                   String returnString = "Viewing Airport " + portData;

                   return returnString;
                }
            }
        }
        
        return ("Error. No matching airports found.");
    }
}