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
public class GUIPassengerFunctions {
    
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
        
        for (Passenger i : terminalData.getPassengerManifest())
        {
            if (i.getPassengerID().equals(addTextInput.getText()))
            {
                return "Input Error: Non-Unique Code. Please try again.";
            }
        }
        
        ArrayList<Passenger> newManifest = terminalData.getPassengerManifest();
        newManifest.add(new Passenger(addTextInput.getText(), addTextInput1.getText(),
                addTextInput2.getText(), addTextInput3.getText(),
                addTextInput4.getText(), addTextInput5.getText(),
                addTextInput6.getText(), addTextInput7.getText()));
        terminalData.setPassengerManifest(newManifest);

        editTextInput.setText(addTextInput.getText());
        editTextInput1.setText(addTextInput1.getText());
        editTextInput2.setText(addTextInput2.getText());
        editTextInput3.setText(addTextInput3.getText());
        editTextInput4.setText(addTextInput4.getText());
        editTextInput5.setText(addTextInput5.getText());
        editTextInput6.setText(addTextInput6.getText());
        editTextInput7.setText(addTextInput7.getText());
        
        return ("Passenger " + addTextInput.getText() + " added successfully. Now editing:");
    }
    
    public String editOption(TerminalData terminalData,
            javax.swing.JTextField editTextInput, javax.swing.JTextField editTextInput1,
            javax.swing.JTextField editTextInput2, javax.swing.JTextField editTextInput3,
            javax.swing.JTextField editTextInput4, javax.swing.JTextField editTextInput5,
            javax.swing.JTextField editTextInput6, javax.swing.JTextField editTextInput7)
    {
        for (int i = 0; i < terminalData.getPassengerManifest().size(); i++)
        {
            if (terminalData.getPassengerManifest().get(i).getPassengerID().equals(editTextInput.getText()))
            {
                terminalData.getPassengerManifest().set(i,
                new Passenger(editTextInput.getText(), editTextInput1.getText(),
                editTextInput2.getText(), editTextInput3.getText(),
                editTextInput4.getText(), editTextInput5.getText(),
                editTextInput6.getText(), editTextInput7.getText()));
                
                return ("Passenger " + editTextInput.getText() + " edited successfully.");
            }
        }
        
        return ("Error. No matching passengers found.");
    }
    
    public String deleteOption(TerminalData terminalData,
            javax.swing.JTextField editTextInput)
    {
        for (Passenger i : terminalData.getPassengerManifest())
        {
            if (i.getPassengerID().equals(editTextInput.getText()))
            {
                terminalData.getPassengerManifest().remove(i);
                
                return ("Passenger " + editTextInput.getText() + " deleted successfully.");
            }
        }
        
        return ("Error. No matching passengers found.");
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

            String returnString = "Possible matching passenger(s):\n";

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

                returnString += "\nManifest Index " + i + ":\nPassenger ID- " + passData;
            }
            
            return returnString;
        }
        
        else
        {
            for (int i = 0; i < terminalData.getPassengerManifest().size(); i++)
            {
                if (terminalData.getPassengerManifest().get(i).getPassengerID().equals(viewTextInput.getText()))
                {
                    Passenger currentPass = terminalData.getPassengerManifest().get(i);
                    
                    editTextInput.setText(currentPass.getPassengerID());
                    editTextInput1.setText(currentPass.getName());
                    editTextInput2.setText(currentPass.getDOB());
                    editTextInput3.setText(currentPass.getGender());
                    editTextInput4.setText(currentPass.getPhone());
                    editTextInput5.setText(currentPass.getEmail());
                    editTextInput6.setText(currentPass.getHome());
                    editTextInput7.setText(currentPass.getGovID());

                    String passData = currentPass.getPassengerID() + "\nName- " + currentPass.getName()
                        + "\nDOB- " + currentPass.getDOB()
                        + "\nGender- " + currentPass.getGender()
                        + "\nPhone Number- " + currentPass.getPhone()
                        + "\nEmail Address- " + currentPass.getEmail()
                        + "\nHome Address- " + currentPass.getHome()
                        + "\nGovernment ID Number- " + currentPass.getGovID()
                        + "\n";

                    String returnString = "Viewing Passenger " + passData;

                    return returnString;
                }
            }
        }
        
        return ("Error. No matching passengers found.");
    }
}