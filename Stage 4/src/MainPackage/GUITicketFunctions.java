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
public class GUITicketFunctions {
    
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
        
        for (Ticket i : terminalData.getTicketManifest())
        {
            if (i.getTicketID().equals(addTextInput.getText()))
            {
                return "Input Error: Non-Unique Code. Please try again.";
            }
        }
        
        Boolean paidBool;
        
        try
        {
            paidBool = Boolean.parseBoolean(addTextInput1.getText());
        }
        catch(Exception e)
        {
            paidBool = false;
        }
        
        Byte compartment;
        
        try
        {
            compartment = Byte.parseByte(addTextInput4.getText());
        }
        catch(Exception e)
        {
            compartment = 0;
        }
        
        Byte checkBags;
        
        try
        {
            checkBags = Byte.parseByte(addTextInput5.getText());
        }
        catch(Exception e)
        {
            checkBags = 0;
        }
        
        ArrayList<Ticket> newManifest = terminalData.getTicketManifest();
        newManifest.add(new Ticket(addTextInput.getText(), paidBool,
                addTextInput2.getText(), addTextInput3.getText(), compartment, checkBags));
        terminalData.setTicketManifest(newManifest);

        editTextInput.setText(addTextInput.getText());
        editTextInput1.setText(addTextInput1.getText());
        editTextInput2.setText(addTextInput2.getText());
        editTextInput3.setText(addTextInput3.getText());
        editTextInput4.setText(addTextInput4.getText());
        editTextInput5.setText(addTextInput5.getText());
        
        return ("Ticket " + addTextInput.getText() + " added successfully. Now editing:");
    }
    
    public String editOption(TerminalData terminalData,
            javax.swing.JTextField editTextInput, javax.swing.JTextField editTextInput1,
            javax.swing.JTextField editTextInput2, javax.swing.JTextField editTextInput3,
            javax.swing.JTextField editTextInput4, javax.swing.JTextField editTextInput5,
            javax.swing.JTextField editTextInput6, javax.swing.JTextField editTextInput7)
    {
        for (int i = 0; i < terminalData.getTicketManifest().size(); i++)
        {
            if (terminalData.getTicketManifest().get(i).getTicketID().equals(editTextInput.getText()))
            {
                Boolean paidBool;

                try
                {
                    paidBool = Boolean.parseBoolean(editTextInput1.getText());
                }
                catch(Exception e)
                {
                    paidBool = false;
                }

                Byte compartment;

                try
                {
                    compartment = Byte.parseByte(editTextInput4.getText());
                }
                catch(Exception e)
                {
                    compartment = 0;
                }

                Byte checkBags;

                try
                {
                    checkBags = Byte.parseByte(editTextInput5.getText());
                }
                catch(Exception e)
                {
                    checkBags = 0;
                }
                
                terminalData.getTicketManifest().set(i,
                new Ticket(editTextInput.getText(), paidBool,
                editTextInput2.getText(), editTextInput3.getText(), compartment, checkBags));
                
                return ("Ticket " + editTextInput.getText() + " edited successfully.");
            }
        }
        
        return ("Error. No matching tickets found.");
    }
    
    public String deleteOption(TerminalData terminalData,
            javax.swing.JTextField editTextInput)
    {
        for (Ticket i : terminalData.getTicketManifest())
        {
            if (i.getTicketID().equals(editTextInput.getText()))
            {
                terminalData.getTicketManifest().remove(i);
                
                return ("Ticket " + editTextInput.getText() + " deleted successfully.");
            }
        }
        
        return ("Error. No matching tickets found.");
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

            String returnString = "Possible matching ticket(s):\n";

            for (Integer i : foundIndices)
            {
                Ticket currentTicket = terminalData.getTicketManifest().get(i);

                String ticketData = currentTicket.getTicketID() + ":\n1. Paid Status- " + currentTicket.isPaidStatus()
                    + "\n2. Flight Code- " + currentTicket.getFlightCode()
                    + "\n3. Passenger Code- " + currentTicket.getPassengerCode()
                    + "\n4. Seating Compartment- " + currentTicket.getSeatCompartment()
                    + "\n5. Checked Bags- " + currentTicket.getCheckBags()
                    + "\n";

                returnString += "\nManifest Index " + i + ":\nTicket ID- " + ticketData;
            }
            
            return returnString;
        }
        
        else
        {
            for (int i = 0; i < terminalData.getTicketManifest().size(); i++)
            {
                if (terminalData.getTicketManifest().get(i).getTicketID().equals(viewTextInput.getText()))
                {
                    Ticket currentTicket = terminalData.getTicketManifest().get(i);
                    
                    editTextInput.setText(currentTicket.getTicketID());
                    editTextInput1.setText(Boolean.toString(currentTicket.isPaidStatus()));
                    editTextInput2.setText(currentTicket.getFlightCode());
                    editTextInput3.setText(currentTicket.getPassengerCode());
                    editTextInput4.setText(Byte.toString(currentTicket.getSeatCompartment()));
                    editTextInput5.setText(Byte.toString(currentTicket.getCheckBags()));

                   String ticketData = currentTicket.getTicketID() + ":\n1. Paid Status- " + currentTicket.isPaidStatus()
                    + "\n2. Flight Code- " + currentTicket.getFlightCode()
                    + "\n3. Passenger Code- " + currentTicket.getPassengerCode()
                    + "\n4. Seating Compartment- " + currentTicket.getSeatCompartment()
                    + "\n5. Checked Bags- " + currentTicket.getCheckBags()
                    + "\n";

                    String returnString = "Viewing Ticket " + ticketData;

                    return returnString;
                }
            }
        }
        
        return ("Error. No matching tickets found.");
    }
}