package MainPackage;

import java.util.ArrayList;

public class GUIReportFunctions
{
    /**
     * compReport gives a comprehensive report on a single object of a given data type and object ID code.
     * @return the report for the selected object.
     */
    public String compReport(String reportSort, String code, MainPackage.TerminalData terminalData)
    {
        String report = "";

        MainPackage.Ticket currentTicket = null;
        String ticketData = "";

        MainPackage.Passenger currentPass = null;
        String passData = "";

        MainPackage.Flight currentFlight = null;
        String flightData = "";

        MainPackage.Plane currentPlane = null;
        String planeData = "";

        MainPackage.Airport currentPort = null;
        String portData = "";

        switch (reportSort)
            {
                /* Checking Ticket objects: For each ticket in terminalData's TicketManifest, check if the
                * ticket's ID code equals the user input code */

                case "Ticket", "ticket", "1":
                    for (int i = 0; i < terminalData.getTicketManifest().size(); i++)
                    {
                        if (terminalData.getTicketManifest().get(i).getTicketID().equals(code))
                        {
                            currentTicket = terminalData.getTicketManifest().get(i);
                            report += "\nTicket Manifest Index " + i + ":\n";
                            break;
                        }
                    }

                    /* If no ticket is found, return as much to the user */

                    if (currentTicket == null)
                    {
                        return "No ticket with code '" + code + "' found.\n";
                    }

                    /* Otherwise, the ticketData string is created and added to the report*/

                    ticketData = "Ticket ID- " + currentTicket.getTicketID()
                            + "\nPaid Status- " + currentTicket.isPaidStatus()
                            + "\nFlight Code- " + currentTicket.getFlightCode()
                            + "\nPassenger Code- " + currentTicket.getPassengerCode()
                            + "\nSeating Compartment- " + currentTicket.getSeatCompartment()
                            + "\nChecked Bags- " + currentTicket.getCheckBags()
                            + "\n";

                    report += ticketData;

                    /* Check all Passengers against the found Ticket for matching Passenger codes, and print any
                    * resulting Passenger's data */

                    for (int i = 0; i < terminalData.getPassengerManifest().size(); i++)
                    {
                        currentPass = terminalData.getPassengerManifest().get(i);

                        if (currentTicket.getPassengerCode().equals(currentPass.getPassengerID()))
                        {
                            passData = "Passenger ID- " + currentPass.getPassengerID()
                                    + "\nName- " + currentPass.getName()
                                    + "\nDOB- " + currentPass.getDOB()
                                    + "\nGender- " + currentPass.getGender()
                                    + "\nPhone Number- " + currentPass.getPhone()
                                    + "\nEmail Address- " + currentPass.getEmail()
                                    + "\nHome Address- " + currentPass.getHome()
                                    + "\nGovernment ID Number- " + currentPass.getGovID()
                                    + "\n";

                            report += passData;

                            break;
                        }
                    }

                    /* Next, perform a similar check against Flights' Flight Codes, printing applicable Flights */

                    for (int i = 0; i < terminalData.getFlightManifest().size(); i++)
                    {
                        currentFlight = terminalData.getFlightManifest().get(i);

                        if (currentFlight.getFlightID().equals(currentTicket.getFlightCode()))
                        {
                            flightData = "Flight ID- " + currentFlight.getFlightID()
                                    + "\nDeparture Time- " + currentFlight.getDepartTime()
                                    + "\nArrival Time- " + currentFlight.getArriveTime()
                                    + "\nDestination Airport Code- " + currentFlight.getDestinationPortCode()
                                    + "\nStarting Airport Code- " + currentFlight.getStartingPortCode()
                                    + "\nPlane Code- " + currentFlight.getAssociatedPlaneCode()
                                    + "\n";

                            /* When a Flight is found, print the applicable Plane data for the Plane whose Plane ID
                            * equals the current Flight's Plane Code */

                            for (int j = 0; j < terminalData.getPlaneManifest().size(); j++)
                            {
                                currentPlane = terminalData.getPlaneManifest().get(j);

                                if (currentPlane.getPlaneID().equals(currentFlight.getAssociatedPlaneCode()))
                                {
                                    planeData = "Plane ID- " + currentPlane.getPlaneID()
                                            + "\nModel- " + currentPlane.getModel()
                                            + "\nPassenger Limit- " + currentPlane.getPassLimit()
                                            + "\nCurrent Assignment Code- " + currentPlane.getCurrentAssignmentCode()
                                            + "\nCurrent Assignment Type- " + currentPlane.getCurrentAssignmentType()
                                            + "\n";

                                    break;
                                }
                            }

                            /* Airports related to the Flight are also printed with Destination or Starting status
                            * shown */

                            for (int j = 0; j < terminalData.getAirportManifest().size(); j++)
                            {
                                currentPort = terminalData.getAirportManifest().get(j);

                                if (currentPort.getAirportID().equals(currentFlight.getDestinationPortCode())
                                || currentPort.getAirportID().equals(currentFlight.getStartingPortCode()))
                                {
                                    if (currentPort.getAirportID().equals(currentFlight.getDestinationPortCode()))
                                    {
                                        portData = "Destination ";
                                    }

                                    else
                                    {
                                        portData = "Starting ";
                                    }

                                    portData += "Airport ID- " + currentPort.getAirportID() + "\nName- " + currentPort.getName() + "\n";
                                }
                            }

                            report += flightData + planeData + portData;

                            break;
                        }
                    }

                    break;

                /* Perform a similar operation to Tickets for Passengers, checking for the Passenger ID Code */

                case "Passenger", "passenger", "2":
                    /* Finding the Passenger */

                    for (int i = 0; i < terminalData.getPassengerManifest().size(); i++)
                    {
                        if (terminalData.getPassengerManifest().get(i).getPassengerID().equals(code))
                        {
                            currentPass = terminalData.getPassengerManifest().get(i);
                            report += "\nPassenger Manifest Index " + i + ":\n";
                            break;
                        }
                    }

                    /* If no Passenger is found, print as much */

                    if (currentPass == null)
                    {
                        return "No passenger with code '" + code + "' found.\n";
                    }

                    /* Format and add the Passenger's data to the report */

                    passData = "Passenger ID- " + currentPass.getPassengerID()
                            + "\nName- " + currentPass.getName()
                            + "\nDOB- " + currentPass.getDOB()
                            + "\nGender- " + currentPass.getGender()
                            + "\nPhone Number- " + currentPass.getPhone()
                            + "\nEmail Address- " + currentPass.getEmail()
                            + "\nHome Address- " + currentPass.getHome()
                            + "\nGovernment ID Number- " + currentPass.getGovID()
                            + "\n";

                    report += passData;

                    /* Check Tickets for matching Passenger Codes */

                    for (Ticket ticket : terminalData.getTicketManifest())
                    {
                        if (ticket.getPassengerCode().equals(currentPass.getPassengerID()))
                        {
                            /* Adding found Tickets to the report */

                            currentTicket = ticket;

                            ticketData = "Ticket ID- " + currentTicket.getTicketID()
                                    + "\nPaid Status- " + currentTicket.isPaidStatus()
                                    + "\nFlight Code- " + currentTicket.getFlightCode()
                                    + "\nPassenger Code- " + currentTicket.getPassengerCode()
                                    + "\nSeating Compartment- " + currentTicket.getSeatCompartment()
                                    + "\nChecked Bags- " + currentTicket.getCheckBags()
                                    + "\n";

                            report += ticketData;

                            /* Adding Flights related to the current ticket */

                            for (Flight flight : terminalData.getFlightManifest())
                            {
                                currentFlight = flight;

                                if (currentFlight.getFlightID().equals(currentTicket.getFlightCode()))
                                {
                                    flightData = "Flight ID- " + currentFlight.getFlightID()
                                            + "\nDeparture Time- " + currentFlight.getDepartTime()
                                            + "\nArrival Time- " + currentFlight.getArriveTime()
                                            + "\nDestination Airport Code- " + currentFlight.getDestinationPortCode()
                                            + "\nStarting Airport Code- " + currentFlight.getStartingPortCode()
                                            + "\nPlane Code- " + currentFlight.getAssociatedPlaneCode()
                                            + "\n";

                                    /* Adding Planes related to the current Flight */

                                    for (Plane plane : terminalData.getPlaneManifest())
                                    {
                                        currentPlane = plane;

                                        if (currentPlane.getPlaneID().equals(currentFlight.getAssociatedPlaneCode()))
                                        {
                                            planeData = "Plane ID- " + currentPlane.getPlaneID()
                                                    + "\nModel- " + currentPlane.getModel()
                                                    + "\nPassenger Limit- " + currentPlane.getPassLimit()
                                                    + "\nCurrent Assignment Code- " + currentPlane.getCurrentAssignmentCode()
                                                    + "\nCurrent Assignment Type- " + currentPlane.getCurrentAssignmentType()
                                                    + "\n";

                                            break;
                                        }
                                    }

                                    /* Adding related Airports, giving Destination or Starting status */

                                    for (Airport port : terminalData.getAirportManifest())
                                    {
                                        currentPort = port;

                                        if (currentPort.getAirportID().equals(currentFlight.getDestinationPortCode())
                                                || currentPort.getAirportID().equals(currentFlight.getStartingPortCode()))
                                        {
                                            if (currentPort.getAirportID().equals(currentFlight.getDestinationPortCode()))
                                            {
                                                portData = "Destination ";
                                            }

                                            else
                                            {
                                                portData = "Starting ";
                                            }

                                            portData += "Airport ID- " + currentPort.getAirportID() + "\nName- " + currentPort.getName() + "\n";
                                        }
                                    }

                                    report += flightData + planeData + portData;

                                    break;
                                }
                            }
                        }
                    }

                    break;

                /* Comprehensive Flight Reports */

                case "Flight", "flight", "3":
                    /* Finding the matching Flight by ID code */

                    for (int i = 0; i < terminalData.getFlightManifest().size(); i++)
                    {
                        if (terminalData.getFlightManifest().get(i).getFlightID().equals(code))
                        {
                            currentFlight = terminalData.getFlightManifest().get(i);
                            report += "\nFlight Manifest Index " + i + ":\n";
                            break;
                        }
                    }

                    /* If no Flight is found, return as much */

                    if (currentFlight == null)
                    {
                        return "No Flight with code '" + code + "' found.\n";
                    }

                    /* Add the found Flight's formatted data to the report */

                    flightData = "Flight ID- " + currentFlight.getFlightID()
                            + "\nDeparture Time- " + currentFlight.getDepartTime()
                            + "\nArrival Time- " + currentFlight.getArriveTime()
                            + "\nDestination Airport Code- " + currentFlight.getDestinationPortCode()
                            + "\nStarting Airport Code- " + currentFlight.getStartingPortCode()
                            + "\nPlane Code- " + currentFlight.getAssociatedPlaneCode()
                            + "\n";

                    report += flightData;

                    /* Find the related Plane by PlaneID */

                    for (Plane plane : terminalData.getPlaneManifest())
                    {
                        currentPlane = plane;

                        if (currentPlane.getPlaneID().equals(currentFlight.getAssociatedPlaneCode()))
                        {
                            planeData = "Plane ID- " + currentPlane.getPlaneID()
                                    + "\nModel- " + currentPlane.getModel()
                                    + "\nPassenger Limit- " + currentPlane.getPassLimit()
                                    + "\nCurrent Assignment Code- " + currentPlane.getCurrentAssignmentCode()
                                    + "\nCurrent Assignment Type- " + currentPlane.getCurrentAssignmentType()
                                    + "\n";

                            break;
                        }
                    }

                    /* Find the Destination and Starting Airports for the Flight */

                    for (Airport port : terminalData.getAirportManifest())
                    {
                        currentPort = port;

                        if (currentPort.getAirportID().equals(currentFlight.getDestinationPortCode())
                                || currentPort.getAirportID().equals(currentFlight.getStartingPortCode()))
                        {
                            if (currentPort.getAirportID().equals(currentFlight.getDestinationPortCode()))
                            {
                                portData = "Destination ";
                            }

                            else
                            {
                                portData = "Starting ";
                            }

                            portData += "Airport ID- " + currentPort.getAirportID() + "\nName- " + currentPort.getName() + "\n";
                        }
                    }

                    report += planeData + portData;

                    /* Add the Tickets related to the Flight */

                    for (Ticket ticket : terminalData.getTicketManifest())
                    {
                        if (ticket.getFlightCode().equals(currentFlight.getFlightID()))
                        {
                            currentTicket = ticket;

                            ticketData = "Ticket ID- " + currentTicket.getTicketID()
                                    + "\nPaid Status- " + currentTicket.isPaidStatus()
                                    + "\nFlight Code- " + currentTicket.getFlightCode()
                                    + "\nPassenger Code- " + currentTicket.getPassengerCode()
                                    + "\nSeating Compartment- " + currentTicket.getSeatCompartment()
                                    + "\nChecked Bags- " + currentTicket.getCheckBags()
                                    + "\n";

                            report += ticketData;

                            for (int i = 0; i < terminalData.getPassengerManifest().size(); i++)
                            {
                                currentPass = terminalData.getPassengerManifest().get(i);

                                if (currentTicket.getPassengerCode().equals(currentPass.getPassengerID()))
                                {
                                    passData = "Passenger ID- " + currentPass.getPassengerID()
                                            + "\nName- " + currentPass.getName()
                                            + "\nDOB- " + currentPass.getDOB()
                                            + "\nGender- " + currentPass.getGender()
                                            + "\nPhone Number- " + currentPass.getPhone()
                                            + "\nEmail Address- " + currentPass.getEmail()
                                            + "\nHome Address- " + currentPass.getHome()
                                            + "\nGovernment ID Number- " + currentPass.getGovID()
                                            + "\n";

                                    report += passData;

                                    break;
                                }
                            }
                        }
                    }

                    break;

                /* Comprehensive Plane Reports */

                case "Plane", "plane", "4":
                    /* Find the Plane with PlaneID matching the user input code, and if found add the Plane's data
                    * to the report */

                    for (int i = 0; i < terminalData.getPlaneManifest().size(); i++)
                    {
                        if (terminalData.getPlaneManifest().get(i).getPlaneID().equals(code))
                        {
                            currentPlane = terminalData.getPlaneManifest().get(i);
                            report += "\nPlane Manifest Index " + i + ":\n";
                            break;
                        }
                    }

                    if (currentPlane == null)
                    {
                        return "No Plane with code '" + code + "' found.\n";
                    }

                    planeData = "Plane ID- " + currentPlane.getPlaneID()
                            + "\nModel- " + currentPlane.getModel()
                            + "\nPassenger Limit- " + currentPlane.getPassLimit()
                            + "\nCurrent Assignment Code- " + currentPlane.getCurrentAssignmentCode()
                            + "\nCurrent Assignment Type- " + currentPlane.getCurrentAssignmentType()
                            + "\n";

                    report += planeData;

                    /* Add related data, including Flights the Plane is assigned to */

                    for (Flight flight : terminalData.getFlightManifest())
                    {
                        currentFlight = flight;

                        if (currentPlane.getPlaneID().equals(currentFlight.getAssociatedPlaneCode())) {
                            flightData = "Flight ID- " + currentFlight.getFlightID()
                                    + "\nDeparture Time- " + currentFlight.getDepartTime()
                                    + "\nArrival Time- " + currentFlight.getArriveTime()
                                    + "\nDestination Airport Code- " + currentFlight.getDestinationPortCode()
                                    + "\nStarting Airport Code- " + currentFlight.getStartingPortCode()
                                    + "\nPlane Code- " + currentFlight.getAssociatedPlaneCode()
                                    + "\n";

                            /* Airports are listed for each Flight */

                            for (Airport port : terminalData.getAirportManifest())
                            {
                                currentPort = port;

                                if (currentPort.getAirportID().equals(currentFlight.getDestinationPortCode())
                                        || currentPort.getAirportID().equals(currentFlight.getStartingPortCode()))
                                {
                                    if (currentPort.getAirportID().equals(currentFlight.getDestinationPortCode()))
                                    {
                                        portData = "Destination ";
                                    }

                                    else
                                    {
                                        portData = "Starting ";
                                    }

                                    portData += "Airport ID- " + currentPort.getAirportID() + "\nName- " + currentPort.getName() + "\n";
                                }
                            }

                            report += flightData + portData;

                            /* Tickets are listed for each Flight */

                            for (Ticket ticket : terminalData.getTicketManifest())
                            {
                                if (ticket.getFlightCode().equals(currentFlight.getFlightID()))
                                {
                                    currentTicket = ticket;

                                    ticketData = "Ticket ID- " + currentTicket.getTicketID()
                                            + "\nPaid Status- " + currentTicket.isPaidStatus()
                                            + "\nFlight Code- " + currentTicket.getFlightCode()
                                            + "\nPassenger Code- " + currentTicket.getPassengerCode()
                                            + "\nSeating Compartment- " + currentTicket.getSeatCompartment()
                                            + "\nChecked Bags- " + currentTicket.getCheckBags()
                                            + "\n";

                                    report += ticketData;

                                    for (int i = 0; i < terminalData.getPassengerManifest().size(); i++)
                                    {
                                        currentPass = terminalData.getPassengerManifest().get(i);

                                        if (currentTicket.getPassengerCode().equals(currentPass.getPassengerID()))
                                        {
                                            passData = "Passenger ID- " + currentPass.getPassengerID()
                                                    + "\nName- " + currentPass.getName()
                                                    + "\nDOB- " + currentPass.getDOB()
                                                    + "\nGender- " + currentPass.getGender()
                                                    + "\nPhone Number- " + currentPass.getPhone()
                                                    + "\nEmail Address- " + currentPass.getEmail()
                                                    + "\nHome Address- " + currentPass.getHome()
                                                    + "\nGovernment ID Number- " + currentPass.getGovID()
                                                    + "\n";

                                            report += passData;

                                            break;
                                        }
                                    }
                                }
                            }
                        }
                    }

                    break;

                /* Comprehensive Airport Reports */

                case "Airport", "airport", "5":
                    /* Find the Airport with matching ID code, and add a found Airport's data to the report */

                    for (int i = 0; i < terminalData.getAirportManifest().size(); i++)
                    {
                        if (terminalData.getAirportManifest().get(i).getAirportID().equals(code))
                        {
                            currentPort = terminalData.getAirportManifest().get(i);
                            report += "\nAirport Manifest Index " + i + ":\n";
                            break;
                        }
                    }

                    if (currentPort == null)
                    {
                        return "No Airport with code '" + code + "' found.\n";
                    }

                    portData = "Airport ID- " + currentPort.getAirportID() + "\nName- " + currentPort.getName() + "\n";

                    report += portData;

                    /* Add data of Planes currently assigned to this Airport */

                    for (Plane plane : terminalData.getPlaneManifest())
                    {
                        currentPlane = plane;

                        if (currentPlane.getCurrentAssignmentType().equals("Airport")
                                && currentPlane.getCurrentAssignmentCode().equals(currentPort.getAirportID()))
                        {
                            planeData = "Plane ID- " + currentPlane.getPlaneID()
                                    + "\nModel- " + currentPlane.getModel()
                                    + "\nPassenger Limit- " + currentPlane.getPassLimit()
                                    + "\nCurrent Assignment Code- " + currentPlane.getCurrentAssignmentCode()
                                    + "\nCurrent Assignment Type- " + currentPlane.getCurrentAssignmentType()
                                    + "\n";

                            report += planeData;

                            /* Add Flight data for Flights using the current plane and either departing from or
                            * arriving at the Airport being comprehensively reported on */

                            for (Flight flight : terminalData.getFlightManifest())
                            {
                                currentFlight = flight;

                                /* Checking conditions */

                                if (currentPlane.getPlaneID().equals(currentFlight.getAssociatedPlaneCode())
                                        && (currentFlight.getStartingPortCode().equals(currentPort.getAirportID()) ||
                                        currentFlight.getDestinationPortCode().equals(currentPort.getAirportID())))
                                {
                                    /* Assigning "type of flight" and using this as the base of the report string */

                                    if (currentFlight.getStartingPortCode().equals(currentPort.getAirportID())
                                            && currentFlight.getDestinationPortCode().equals(currentPort.getAirportID()))
                                    {
                                        flightData = "Round-Trip ";
                                    }

                                    else if (currentFlight.getStartingPortCode().equals(currentPort.getAirportID()))
                                    {
                                        flightData = "Outbound ";
                                    }

                                    else
                                    {
                                        flightData = "Inbound ";
                                    }

                                    /* Adding the Flight data */

                                    flightData += "Flight ID- " + currentFlight.getFlightID()
                                            + "\nDeparture Time- " + currentFlight.getDepartTime()
                                            + "\nArrival Time- " + currentFlight.getArriveTime()
                                            + "\nDestination Airport Code- " + currentFlight.getDestinationPortCode()
                                            + "\nStarting Airport Code- " + currentFlight.getStartingPortCode()
                                            + "\nPlane Code- " + currentFlight.getAssociatedPlaneCode()
                                            + "\n";

                                    report += flightData;

                                    /* Add related tickets */

                                    for (Ticket ticket : terminalData.getTicketManifest())
                                    {
                                        if (ticket.getFlightCode().equals(currentFlight.getFlightID()))
                                        {
                                            currentTicket = ticket;

                                            ticketData = "Ticket ID- " + currentTicket.getTicketID()
                                                    + "\nPaid Status- " + currentTicket.isPaidStatus()
                                                    + "\nFlight Code- " + currentTicket.getFlightCode()
                                                    + "\nPassenger Code- " + currentTicket.getPassengerCode()
                                                    + "\nSeating Compartment- " + currentTicket.getSeatCompartment()
                                                    + "\nChecked Bags- " + currentTicket.getCheckBags()
                                                    + "\n";

                                            report += ticketData;

                                            /* Add the related Passenger for the current Ticket */

                                            for (int i = 0; i < terminalData.getPassengerManifest().size(); i++)
                                            {
                                                currentPass = terminalData.getPassengerManifest().get(i);

                                                if (currentTicket.getPassengerCode().equals(currentPass.getPassengerID()))
                                                {
                                                    passData = "Passenger ID- " + currentPass.getPassengerID()
                                                            + "\nName- " + currentPass.getName()
                                                            + "\nDOB- " + currentPass.getDOB()
                                                            + "\nGender- " + currentPass.getGender()
                                                            + "\nPhone Number- " + currentPass.getPhone()
                                                            + "\nEmail Address- " + currentPass.getEmail()
                                                            + "\nHome Address- " + currentPass.getHome()
                                                            + "\nGovernment ID Number- " + currentPass.getGovID()
                                                            + "\n";

                                                    report += passData;

                                                    break;
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }

                    /* Initialize two lists: one for arriving flights, and one for departing */

                    ArrayList<Flight> departingFlightsList = new ArrayList<>();
                    ArrayList<Flight> arrivingFlightsList = new ArrayList<>();

                    /* Add Flights arriving to/departing from this airport to the corresponding list */

                    for (Flight flight : terminalData.getFlightManifest())
                    {
                        currentFlight = flight;

                        if (currentFlight.getStartingPortCode().equals(currentPort.getAirportID()))
                        {
                            departingFlightsList.add(currentFlight);
                        }

                        if (currentFlight.getDestinationPortCode().equals(currentPort.getAirportID()))
                        {
                            arrivingFlightsList.add(currentFlight);
                        }
                    }

                    /* Reporting inbound (arriving) flights */

                    report += "\n" + currentPort.getAirportID()+ "-- Inbound Flights by time of arrival:\n";

                    /* Bubble sorting the Flights by ArriveTime */

                    for (int i = 0; i < arrivingFlightsList.size() - 1; i++)
                    {
                        if (arrivingFlightsList.get(i).getArriveTime() > arrivingFlightsList.get(i + 1).getArriveTime())
                        {
                            Flight leftFlight = arrivingFlightsList.get(i);
                            Flight rightFlight = arrivingFlightsList.get(i + 1);

                            arrivingFlightsList.set(i, rightFlight);
                            arrivingFlightsList.set((i + 1), leftFlight);
                        }
                    }

                    /* Listing arriving flights in order */

                    for (int i = 0; i < arrivingFlightsList.size(); i++)
                    {
                        currentFlight = arrivingFlightsList.get(i);

                        flightData = "\nInbound Flight #" + i + "--"
                                + "\nFlight ID- " + currentFlight.getFlightID()
                                + "\nDeparture Time- " + currentFlight.getDepartTime()
                                + "\nArrival Time- " + currentFlight.getArriveTime()
                                + "\nDestination Airport Code- " + currentFlight.getDestinationPortCode()
                                + "\nStarting Airport Code- " + currentFlight.getStartingPortCode()
                                + "\nPlane Code- " + currentFlight.getAssociatedPlaneCode()
                                + "\n";

                        report += flightData;
                    }

                    /* Outbound flights */

                    report += "\n" + currentPort.getAirportID()+ "-- Outbound Flights by time of departure:\n";

                    /* Bubble sort */

                    for (int i = 0; i < departingFlightsList.size() - 1; i++)
                    {
                        if (departingFlightsList.get(i).getDepartTime() > departingFlightsList.get(i + 1).getDepartTime())
                        {
                            Flight leftFlight = departingFlightsList.get(i);
                            Flight rightFlight = departingFlightsList.get(i + 1);

                            departingFlightsList.set(i, rightFlight);
                            departingFlightsList.set((i + 1), leftFlight);
                        }
                    }

                    /* Add the formatted Outbound Flights list */

                    for (int i = 0; i < departingFlightsList.size(); i++)
                    {
                        currentFlight = departingFlightsList.get(i);

                        flightData = "\nOutbound Flight #" + i + "--"
                                + "\nFlight ID- " + currentFlight.getFlightID()
                                + "\nDeparture Time- " + currentFlight.getDepartTime()
                                + "\nArrival Time- " + currentFlight.getArriveTime()
                                + "\nDestination Airport Code- " + currentFlight.getDestinationPortCode()
                                + "\nStarting Airport Code- " + currentFlight.getStartingPortCode()
                                + "\nPlane Code- " + currentFlight.getAssociatedPlaneCode()
                                + "\n";

                        report += flightData;
                    }

                    break;
            }

        /* Return the Report generated by user options */

        return report;
    }

    /**
     * The Comprehensive Search function searches all data objects in the TerminalData object currently in use, and
     * returns a list of all data objects with data elements containing a user-input search query string.
     * @return The list of all possible matches.
     */
    public String compSearch(String searchQuery, MainPackage.TerminalData terminalData)
    {
        String report = "Possible matches by data type:\n";

        /* Initialize data objects needed */

        MainPackage.Ticket currentTicket = null;
        String ticketData = "";

        MainPackage.Passenger currentPass = null;
        String passData = "";

        MainPackage.Flight currentFlight = null;
        String flightData = "";

        MainPackage.Plane currentPlane = null;
        String planeData = "";

        MainPackage.Airport currentPort = null;
        String portData = "";

        /* Check all Ticket data for matches, adding the formatted data of any matches to the report */

        for (int i = 0; i < terminalData.getTicketManifest().size(); i++)
        {
            currentTicket = terminalData.getTicketManifest().get(i);

            if (currentTicket.getTicketID().contains(searchQuery)
                    || currentTicket.getFlightCode().contains(searchQuery)
                    || currentTicket.getPassengerCode().contains(searchQuery)
                    || Byte.toString(currentTicket.getSeatCompartment()).contains(searchQuery)
                    || Byte.toString(currentTicket.getCheckBags()).contains(searchQuery))
            {
                report += ("\nTicket Manifest Index #" + i + ":\n");

                ticketData = "Ticket ID- " + currentTicket.getTicketID()
                        + "\nPaid Status- " + currentTicket.isPaidStatus()
                        + "\nFlight Code- " + currentTicket.getFlightCode()
                        + "\nPassenger Code- " + currentTicket.getPassengerCode()
                        + "\nSeating Compartment- " + currentTicket.getSeatCompartment()
                        + "\nChecked Bags- " + currentTicket.getCheckBags()
                        + "\n";

                report += ticketData;
            }
        }

        /* Check passengers */

        for (int i = 0; i < terminalData.getPassengerManifest().size(); i++)
        {
            currentPass = terminalData.getPassengerManifest().get(i);

            if (currentPass.getPassengerID().contains(searchQuery)
                    || currentPass.getName().contains(searchQuery)
                    || currentPass.getDOB().contains(searchQuery)
                    || currentPass.getGender().contains(searchQuery)
                    || currentPass.getPhone().contains(searchQuery)
                    || currentPass.getEmail().contains(searchQuery)
                    || currentPass.getHome().contains(searchQuery)
                    || currentPass.getGovID().contains(searchQuery))
            {
                report += ("\nPassenger Manifest Index #" + i + ":\n");

                passData = "Passenger ID- " + currentPass.getPassengerID()
                        + "\nName- " + currentPass.getName()
                        + "\nDOB- " + currentPass.getDOB()
                        + "\nGender- " + currentPass.getGender()
                        + "\nPhone Number- " + currentPass.getPhone()
                        + "\nEmail Address- " + currentPass.getEmail()
                        + "\nHome Address- " + currentPass.getHome()
                        + "\nGovernment ID Number- " + currentPass.getGovID()
                        + "\n";

                report += passData;
            }
        }

        /* Check flights */

        for (int i = 0; i < terminalData.getFlightManifest().size(); i++)
        {
            currentFlight = terminalData.getFlightManifest().get(i);

            if (currentFlight.getFlightID().contains(searchQuery)
                    || Double.toString(currentFlight.getArriveTime()).contains(searchQuery)
                    || Double.toString(currentFlight.getDepartTime()).contains(searchQuery)
                    || currentFlight.getStartingPortCode().contains(searchQuery)
                    || currentFlight.getDestinationPortCode().contains(searchQuery)
                    || currentFlight.getAssociatedPlaneCode().contains(searchQuery))
            {
                report += ("\nFlight Manifest Index #" + i + ":\n");

                flightData = "Flight ID- " + currentFlight.getFlightID()
                        + "\nDeparture Time- " + currentFlight.getDepartTime()
                        + "\nArrival Time- " + currentFlight.getArriveTime()
                        + "\nDestination Airport Code- " + currentFlight.getDestinationPortCode()
                        + "\nStarting Airport Code- " + currentFlight.getStartingPortCode()
                        + "\nPlane Code- " + currentFlight.getAssociatedPlaneCode()
                        + "\n";

                report += flightData;
            }
        }

        /* Check planes */

        for (int i = 0; i < terminalData.getPlaneManifest().size(); i++)
        {
            currentPlane = terminalData.getPlaneManifest().get(i);

            if (currentPlane.getPlaneID().contains(searchQuery)
                    || currentPlane.getModel().contains(searchQuery)
                    || Integer.toString(currentPlane.getPassLimit()).contains(searchQuery)
                    || currentPlane.getCurrentAssignmentCode().contains(searchQuery)
                    || currentPlane.getCurrentAssignmentType().contains(searchQuery))
            {
                report += ("\nPlane Manifest Index #" + i + ":\n");

                planeData = "Plane ID- " + currentPlane.getPlaneID()
                        + "\nModel- " + currentPlane.getModel()
                        + "\nPassenger Limit- " + currentPlane.getPassLimit()
                        + "\nCurrent Assignment Code- " + currentPlane.getCurrentAssignmentCode()
                        + "\nCurrent Assignment Type- " + currentPlane.getCurrentAssignmentType()
                        + "\n";

                report += planeData;
            }
        }

        /* Check airports */

        for (int i = 0; i < terminalData.getAirportManifest().size(); i++)
        {
            currentPort = terminalData.getAirportManifest().get(i);

            if (currentPort.getAirportID().contains(searchQuery) || currentPort.getName().contains(searchQuery))
            {
                report += ("\nAirport Manifest Index #" + i + ":\n");

                portData = "Airport ID- " + currentPort.getAirportID() + "\nName- " + currentPort.getName()
                + "\n";

                report += portData;
            }
        }

    return report ;
    }

    /**
     * The generate function hosts a wide array of functionalities for displaying lists of objects which contain data
     * containing a user-input search query, sorted by their associations with other data objects within the system.
     * @param genCode The type of report being generated, corresponding to the data type being listed
     * @return The report generated from the user inputs and the data read from terminalData.
     */
    public String generate(String reportSort, String genCodeString, int reportSize, String searchQuery, MainPackage.TerminalData terminalData)
    {
        String report = "";
        
        int genCode = 0;
        
        switch(genCodeString)
        {
            case "Ticket": 
                genCode = 1;
                break;
                
            case "Passenger": 
                genCode = 2;
                break;
                
            case "Flight": 
                genCode = 3;
                break;
                
            case "Plane": 
                genCode = 4;
                break;
            
            case "Airport": 
                genCode = 5;
                break;    
        }

        ArrayList<String> itemList = new ArrayList<>();

        int sizeCounter = 0;

        /* Depending on genCode, add objects of the appropriate data type to the itemList if any of their
        * data contains the searchQuery string */

        switch (genCode)
        {
            case 1:
                for (MainPackage.Ticket currentTicket : terminalData.getTicketManifest())
                {
                    /* Check conditionals to determine if the Ticket is to be added to the itemList */

                    if (currentTicket.getTicketID().contains(searchQuery)
                            || currentTicket.getFlightCode().contains(searchQuery)
                            || currentTicket.getPassengerCode().contains(searchQuery)
                            || Byte.toString(currentTicket.getSeatCompartment()).equals(searchQuery)
                            || Byte.toString(currentTicket.getCheckBags()).equals(searchQuery))
                    {
                        itemList.add(currentTicket.getTicketID());
                    }

                    sizeCounter += 1;

                    /* If the report size limit is reached, stop here */

                    if (sizeCounter == reportSize)
                    {
                        System.out.println("Report Size Limit Reached. Report Size: " + (sizeCounter) + "\n");
                        break;
                    }
                }
                break;

            /* Adding Passengers */

            case 2:
                for (MainPackage.Passenger currentPass : terminalData.getPassengerManifest())
                {
                    if (currentPass.getPassengerID().contains(searchQuery)
                            || currentPass.getName().contains(searchQuery)
                            || currentPass.getDOB().contains(searchQuery)
                            || currentPass.getGender().contains(searchQuery)
                            || currentPass.getPhone().contains(searchQuery)
                            || currentPass.getEmail().contains(searchQuery)
                            || currentPass.getHome().contains(searchQuery)
                            || currentPass.getGovID().contains(searchQuery))
                    {
                        itemList.add(currentPass.getPassengerID());
                    }

                    sizeCounter += 1;

                    if (sizeCounter == reportSize)
                    {
                        System.out.println("Report Size Limit Reached. Report Size: " + (sizeCounter) + "\n");
                        break;
                    }
                }
                break;

            /* Adding Flights */

            case 3:
                for (MainPackage.Flight currentFlight : terminalData.getFlightManifest())
                {
                    if (currentFlight.getFlightID().contains(searchQuery)
                            || Double.toString(currentFlight.getArriveTime()).equals(searchQuery)
                            || Double.toString(currentFlight.getDepartTime()).equals(searchQuery)
                            || currentFlight.getStartingPortCode().contains(searchQuery)
                            || currentFlight.getDestinationPortCode().contains(searchQuery)
                            || currentFlight.getAssociatedPlaneCode().contains(searchQuery))
                    {
                        itemList.add(currentFlight.getFlightID());
                    }

                    sizeCounter += 1;

                    if (sizeCounter == reportSize)
                    {
                        System.out.println("Report Size Limit Reached. Report Size: " + (sizeCounter) + "\n");
                        break;
                    }
                }
                break;

            /* Adding Planes */

            case 4:
                for (MainPackage.Plane currentPlane : terminalData.getPlaneManifest())
                {
                    if (currentPlane.getPlaneID().contains(searchQuery)
                            || currentPlane.getModel().contains(searchQuery)
                            || Integer.toString(currentPlane.getPassLimit()).contains(searchQuery)
                            || currentPlane.getCurrentAssignmentCode().contains(searchQuery)
                            || currentPlane.getCurrentAssignmentType().contains(searchQuery))
                    {
                        itemList.add(currentPlane.getPlaneID());
                    }

                    sizeCounter += 1;

                    if (sizeCounter == reportSize)
                    {
                        System.out.println("Report Size Limit Reached. Report Size: " + (sizeCounter) + "\n");
                        break;
                    }
                }
                break;

            /* Adding Airports */

            case 5:
                for (MainPackage.Airport currentPort : terminalData.getAirportManifest())
                {
                    if (currentPort.getAirportID().contains(searchQuery) || currentPort.getName().contains(searchQuery))
                    {
                        itemList.add(currentPort.getAirportID());
                    }

                    sizeCounter += 1;

                    if (sizeCounter == reportSize)
                    {
                        System.out.println("Report Size Limit Reached. Report Size: " + (sizeCounter) + "\n");
                        break;
                    }
                }
                break;
        }

    /* If there weren't any matches, inform the user */

    if (itemList.isEmpty())
    {
        return ("No possible search query matches found; report empty.");
    }

    /* Initialize local data objects */

    Ticket currentTicket = null;
    String ticketData = "";

    Passenger currentPass = null;
    String passData = "";

    Flight currentFlight = null;
    String flightData = "";

    Plane currentPlane = null;
    String planeData = "";

    Airport currentPort = null;
    String portData = "";

    /* Based on the sort type, begin generating the report */

    switch (reportSort)
    {
        /* Ticket-sorted reports */

        case "Ticket", "ticket", "1":
            /* Ensure the report is eligible to be sorted by Ticket */

            if (!(genCode == 2 || genCode == 3))
            {
                System.out.println("Error: Ticket-sorted reports may only be generated for Passengers and Flights\n");
            }

            /* Find the Tickets with associated Passengers or Flights with ID codes matching the itemList
            codes */

            else
            {
                for (int i = 0; i < terminalData.getTicketManifest().size(); i++)
                {
                    boolean hasCode = false;

                    for (String code: itemList)
                    {
                        /* If a match is found, stop searching */

                        if ((terminalData.getTicketManifest().get(i).getFlightCode().equals(code)
                                && (genCode == 3))
                                || (terminalData.getTicketManifest().get(i).getPassengerCode().equals(code))
                                && (genCode == 2))
                        {
                            hasCode = true;
                            break;
                        }
                    }

                    /* When a match is found, add that Ticket's data to the report */

                    if (hasCode)
                    {
                        report += ("\nTicket Manifest Index #" + i + ":\n");

                        currentTicket = terminalData.getTicketManifest().get(i);

                        ticketData = "Ticket ID- " + currentTicket.getTicketID()
                                + "\nPaid Status- " + currentTicket.isPaidStatus()
                                + "\nFlight Code- " + currentTicket.getFlightCode()
                                + "\nPassenger Code- " + currentTicket.getPassengerCode()
                                + "\nSeating Compartment- " + currentTicket.getSeatCompartment()
                                + "\nChecked Bags- " + currentTicket.getCheckBags()
                                + "\n";

                        report += ticketData;

                        /* Next, find the associated itemList objects for this Ticket */

                        for (String code : itemList)
                        {
                            /* Related objects are checked here */

                            if ((currentTicket.getPassengerCode().equals(code)
                                    && (genCode == 2))
                                    || (currentTicket.getFlightCode().equals(code)
                                    && (genCode == 3)))
                            {
                                /* Adding the related object */

                                int found = -1;

                                /* Using a switch on genCode type to get the appropriate data format */

                                switch (genCode)
                                {
                                    /* Reporting Passengers by Ticket */

                                    case 2:
                                        /* Find the Passenger in the manifest */

                                        for (int j = 0; j < terminalData.getPassengerManifest().size(); j++)
                                        {
                                            if (terminalData.getPassengerManifest().get(j).getPassengerID().equals(code)
                                                    && terminalData.getPassengerManifest().get(j).getPassengerID().equals(currentTicket.getPassengerCode()))
                                            {
                                                /* When found, mark the index and break */

                                                found = j;
                                                break;
                                            }
                                        }

                                        /* Add the found Passenger's data */

                                        currentPass = terminalData.getPassengerManifest().get(found);

                                        passData = "Passenger ID- " + currentPass.getPassengerID()
                                                + "\nName- " + currentPass.getName()
                                                + "\nDOB- " + currentPass.getDOB()
                                                + "\nGender- " + currentPass.getGender()
                                                + "\nPhone Number- " + currentPass.getPhone()
                                                + "\nEmail Address- " + currentPass.getEmail()
                                                + "\nHome Address- " + currentPass.getHome()
                                                + "\nGovernment ID Number- " + currentPass.getGovID()
                                                + "\n";

                                        report += passData;

                                        break;

                                    /* Flights by Ticket */

                                    case 3:
                                        /* Find the manifest object, and add data to the report string */

                                        for (int j = 0; j < terminalData.getFlightManifest().size(); j++)
                                        {
                                            if (terminalData.getFlightManifest().get(j).getFlightID().equals(code)
                                                    && terminalData.getFlightManifest().get(j).getFlightID().equals(currentTicket.getFlightCode()))
                                            {
                                                found = j;
                                                break;
                                            }
                                        }

                                        currentFlight = terminalData.getFlightManifest().get(found);

                                        flightData = "Flight ID- " + currentFlight.getFlightID()
                                                + "\nDeparture Time- " + currentFlight.getDepartTime()
                                                + "\nArrival Time- " + currentFlight.getArriveTime()
                                                + "\nDestination Airport Code- " + currentFlight.getDestinationPortCode()
                                                + "\nStarting Airport Code- " + currentFlight.getStartingPortCode()
                                                + "\nPlane Code- " + currentFlight.getAssociatedPlaneCode()
                                                + "\n";

                                        report += flightData;

                                        break;
                                }
                            }
                        }
                    }
                }
            }

            break;

        /* Passenger-sorted Reports */

        case "Passenger", "passenger", "2":
            /* Ensure the reportSort is eligible for this data type */

            if (!(genCode == 1 || genCode == 3))
            {
                System.out.println("Error: Passenger-sorted reports may only be generated for Tickets and Flights\n");
            }

            /* Find Passenger objects which may be associated with searched objects */

            else
            {
                /* Traverse the PassengerManifest and find a Passenger to sort by */

                for (int i = 0; i < terminalData.getPassengerManifest().size(); i++)
                {
                    boolean hasCode = false;
                    String passID = terminalData.getPassengerManifest().get(i).getPassengerID();

                    /* If the Passenger matches a Ticket's data in the itemList, report on that Passenger */

                    for (String code : itemList)
                    {
                        for (int j = 0; j < terminalData.getTicketManifest().size(); j++) {

                            String ticketID = terminalData.getTicketManifest().get(j).getTicketID();

                            if (terminalData.getTicketManifest().get(j).getPassengerCode().equals(passID)
                                    && ticketID.equals(code))
                            {
                                hasCode = true;
                                break;
                            }
                        }

                        if (hasCode)
                        {
                            break;
                        }
                    }

                    /* Using the found Passenger's data, add Passenger data to the report */

                    if (hasCode)
                        {
                            report += ("\nPassenger Manifest Index #" + i + ":\n");

                            currentPass = terminalData.getPassengerManifest().get(i);

                            passData = "Passenger ID- " + currentPass.getPassengerID()
                                    + "\nName- " + currentPass.getName()
                                    + "\nDOB- " + currentPass.getDOB()
                                    + "\nGender- " + currentPass.getGender()
                                    + "\nPhone Number- " + currentPass.getPhone()
                                    + "\nEmail Address- " + currentPass.getEmail()
                                    + "\nHome Address- " + currentPass.getHome()
                                    + "\nGovernment ID Number- " + currentPass.getGovID()
                                    + "\n";

                            report += passData;

                            /* Depending on the report genCode type, add report data for related objects */

                            switch (genCode)
                                {
                                    /* Tickets by Passenger */

                                    case 1:
                                        /* Find Tickets related to current Passenger which were in the
                                        itemList */

                                        for (String code : itemList)
                                        {
                                            for (int j = 0; j < terminalData.getTicketManifest().size(); j++)
                                            {
                                                currentTicket = terminalData.getTicketManifest().get(j);

                                                if (currentTicket.getPassengerCode().equals(passID)
                                                        && currentTicket.getTicketID().equals(code))
                                                {
                                                    ticketData = "Ticket ID- " + currentTicket.getTicketID()
                                                            + "\nPaid Status- " + currentTicket.isPaidStatus()
                                                            + "\nFlight Code- " + currentTicket.getFlightCode()
                                                            + "\nPassenger Code- " + currentTicket.getPassengerCode()
                                                            + "\nSeating Compartment- " + currentTicket.getSeatCompartment()
                                                            + "\nChecked Bags- " + currentTicket.getCheckBags()
                                                            + "\n";

                                                    report += ticketData;
                                                }
                                            }
                                        }

                                        break;

                                    /* Flights by Passenger */

                                    case 3:
                                        ArrayList<Flight> flightList = new ArrayList<>();

                                        /* Find Flights related to current Passenger which were in the
                                        itemList */

                                        for (String code : itemList)
                                        {
                                            for (int j = 0; j < terminalData.getTicketManifest().size(); j++)
                                            {
                                                currentTicket = terminalData.getTicketManifest().get(j);

                                                if (currentTicket.getPassengerCode().equals(passID))
                                                {
                                                    for (Flight flight : terminalData.getFlightManifest())
                                                    {
                                                        if (flight.getFlightID().equals(currentTicket.getFlightCode())
                                                                && flight.getFlightID().equals(code))
                                                        {
                                                            flightList.add(flight);
                                                        }
                                                    }
                                                }
                                            }
                                        }

                                        /* Using an additional ArrayList to keep track of added Flights in
                                        * order to avoid redundancy */

                                        ArrayList<Flight> redundancyList = new ArrayList<>();

                                        /* Print the found Flights if they aren't redundant, and add them to
                                        * the redundancy list */

                                        for (Flight flight : flightList)
                                        {
                                            currentFlight = flight;

                                            if (!redundancyList.contains(currentFlight))
                                            {
                                                flightData = "Flight ID- " + currentFlight.getFlightID()
                                                        + "\nDeparture Time- " + currentFlight.getDepartTime()
                                                        + "\nArrival Time- " + currentFlight.getArriveTime()
                                                        + "\nDestination Airport Code- " + currentFlight.getDestinationPortCode()
                                                        + "\nStarting Airport Code- " + currentFlight.getStartingPortCode()
                                                        + "\nPlane Code- " + currentFlight.getAssociatedPlaneCode()
                                                        + "\n";

                                                report += flightData;

                                                redundancyList.add(currentFlight);
                                            }
                                        }

                                        break;
                                }
                        }
                }
            }
            break;

        /* Flight-sorted Reports */

        case "Flight", "flight", "3":
            /* Begin reporting Flights which may match the search of the objects given by the genCode type */

            switch (genCode)
            {
                /* Tickets by Flight */

                case 1:
                    /* Get related Flights */

                    for (int i = 0; i < terminalData.getFlightManifest().size(); i++)
                    {
                        boolean hasCode = false;
                        String flightID = terminalData.getFlightManifest().get(i).getFlightID();

                        /* Check Ticket Flight Codes and IDs for matches in the FlightManifest and itemList */

                        for (String code : itemList)
                        {
                            for (int j = 0; j < terminalData.getTicketManifest().size(); j++)
                            {
                                String flightCode = terminalData.getTicketManifest().get(j).getFlightCode();
                                String ticketID = terminalData.getTicketManifest().get(j).getTicketID();

                                if (flightCode.equals(flightID) && ticketID.equals(code))
                                {
                                    hasCode = true;
                                    break;
                                }
                            }

                            if (hasCode)
                            {
                                break;
                            }
                        }

                        /* A found Flight object is now reported */

                        if (hasCode)
                        {
                            /* Adding Flight data to the report */

                            report += ("\nFlight Manifest Index #" + i + ":\n");

                            currentFlight = terminalData.getFlightManifest().get(i);

                            flightData = "Flight ID- " + currentFlight.getFlightID()
                                    + "\nDeparture Time- " + currentFlight.getDepartTime()
                                    + "\nArrival Time- " + currentFlight.getArriveTime()
                                    + "\nDestination Airport Code- " + currentFlight.getDestinationPortCode()
                                    + "\nStarting Airport Code- " + currentFlight.getStartingPortCode()
                                    + "\nPlane Code- " + currentFlight.getAssociatedPlaneCode()
                                    + "\n";

                            report += flightData;

                            /* Adding data for related Tickets */

                            for (int j = 0; j < terminalData.getTicketManifest().size(); j++)
                            {
                                for (int k = 0; k < itemList.size(); k++)
                                {
                                    /* Verifying the ticket */

                                    if (terminalData.getTicketManifest().get(j).getTicketID().equals(itemList.get(k))
                                            && terminalData.getTicketManifest().get(j).getFlightCode().equals(currentFlight.getFlightID()))
                                    {
                                        currentTicket = terminalData.getTicketManifest().get(j);

                                        ticketData = "Ticket ID- " + currentTicket.getTicketID()
                                                + "\nPaid Status- " + currentTicket.isPaidStatus()
                                                + "\nFlight Code- " + currentTicket.getFlightCode()
                                                + "\nPassenger Code- " + currentTicket.getPassengerCode()
                                                + "\nSeating Compartment- " + currentTicket.getSeatCompartment()
                                                + "\nChecked Bags- " + currentTicket.getCheckBags()
                                                + "\n";

                                        report += ticketData;

                                        break;
                                    }
                                }
                            }
                        }
                    }
                    break;

                /* Passengers by Flight */

                case 2:
                    /* Finding related Flights */

                    for (int i = 0; i < terminalData.getFlightManifest().size(); i++)
                    {
                        boolean hasCode = false;
                        String flightID = terminalData.getFlightManifest().get(i).getFlightID();

                        /* Checking against itemList and passenger codes */

                        for (String code : itemList)
                        {
                            for (int j = 0; j < terminalData.getTicketManifest().size(); j++)
                            {

                                String flightCode = terminalData.getTicketManifest().get(j).getFlightCode();
                                String passengerCode = terminalData.getTicketManifest().get(j).getPassengerCode();

                                if (flightCode.equals(flightID) && passengerCode.equals(code))
                                {
                                    hasCode = true;
                                    break;
                                }
                            }

                            if (hasCode)
                            {
                                break;
                            }
                        }

                        /* Flight object found above is now added to the report */

                        if (hasCode)
                        {
                            /* Adding Flight data */

                            report += ("\nFlight Manifest Index #" + i + ":\n");

                            currentFlight = terminalData.getFlightManifest().get(i);

                            flightData = "Flight ID- " + currentFlight.getFlightID()
                                    + "\nDeparture Time- " + currentFlight.getDepartTime()
                                    + "\nArrival Time- " + currentFlight.getArriveTime()
                                    + "\nDestination Airport Code- " + currentFlight.getDestinationPortCode()
                                    + "\nStarting Airport Code- " + currentFlight.getStartingPortCode()
                                    + "\nPlane Code- " + currentFlight.getAssociatedPlaneCode()
                                    + "\n";

                            report += flightData;

                            /* Finding related Passengers using Tickets */

                            for (int j = 0; j < terminalData.getTicketManifest().size(); j++)
                            {
                                for (int k = 0; k < itemList.size(); k++)
                                {
                                    if (terminalData.getTicketManifest().get(j).getPassengerCode().equals(itemList.get(k))
                                            && terminalData.getTicketManifest().get(j).getFlightCode().equals(currentFlight.getFlightID()))
                                    {
                                        /* Adding found Passenger data */

                                        currentPass = terminalData.getPassengerManifest().get(k);

                                        passData = "Passenger ID- " + currentPass.getPassengerID()
                                                + "\nName- " + currentPass.getName()
                                                + "\nDOB- " + currentPass.getDOB()
                                                + "\nGender- " + currentPass.getGender()
                                                + "\nPhone Number- " + currentPass.getPhone()
                                                + "\nEmail Address- " + currentPass.getEmail()
                                                + "\nHome Address- " + currentPass.getHome()
                                                + "\nGovernment ID Number- " + currentPass.getGovID()
                                                + "\n";

                                        report += passData;

                                        break;
                                    }
                                }
                            }
                        }
                    }

                    break;

                /* Flights by Flight (Error) */

                case 3:
                    System.out.println("Error: Flight-sorted reports may not be generated for Flights\n");
                    break;

                /* Planes by Flight */

                case 4:
                    /* Finding related Flights */

                    for (int i = 0; i < terminalData.getFlightManifest().size(); i++)
                    {
                        boolean hasCode = false;

                        String planeCode = terminalData.getFlightManifest().get(i).getAssociatedPlaneCode();

                        for (String code : itemList)
                        {
                            for (int j = 0; j < terminalData.getPlaneManifest().size(); j++)
                            {

                                String planeID = terminalData.getPlaneManifest().get(j).getPlaneID();

                                if (planeCode.equals(planeID) && planeID.equals(code))
                                {
                                    hasCode = true;
                                    break;
                                }
                            }

                            if (hasCode)
                            {
                                break;
                            }
                        }

                        /* Adding related Flight data for found Flight */

                        if (hasCode)
                        {
                            report += ("\nFlight Manifest Index #" + i + ":\n");

                            currentFlight = terminalData.getFlightManifest().get(i);

                            flightData = "Flight ID- " + currentFlight.getFlightID()
                                    + "\nDeparture Time- " + currentFlight.getDepartTime()
                                    + "\nArrival Time- " + currentFlight.getArriveTime()
                                    + "\nDestination Airport Code- " + currentFlight.getDestinationPortCode()
                                    + "\nStarting Airport Code- " + currentFlight.getStartingPortCode()
                                    + "\nPlane Code- " + currentFlight.getAssociatedPlaneCode()
                                    + "\n";

                            report += flightData;

                            /* Adding related Plane data */

                            for (int j = 0; j < terminalData.getPlaneManifest().size(); j++)
                            {
                                for (int k = 0; k < itemList.size(); k++)
                                {
                                    if (terminalData.getPlaneManifest().get(j).getPlaneID().equals(itemList.get(k))
                                            && terminalData.getPlaneManifest().get(j).getPlaneID().equals(currentFlight.getAssociatedPlaneCode()))
                                    {
                                        currentPlane = terminalData.getPlaneManifest().get(k);

                                        planeData = "Plane ID- " + currentPlane.getPlaneID()
                                            + "\nModel- " + currentPlane.getModel()
                                            + "\nPassenger Limit- " + currentPlane.getPassLimit()
                                            + "\nCurrent Assignment Code- " + currentPlane.getCurrentAssignmentCode()
                                            + "\nCurrent Assignment Type- " + currentPlane.getCurrentAssignmentType()
                                            + "\n";

                                        report += planeData;

                                        break;
                                    }
                                }
                            }
                        }
                    }

                    break;

                /* Airports by Flight */

                case 5:
                    /* Finding eligible Flights by associated starting and destination airport codes */

                    for (int i = 0; i < terminalData.getFlightManifest().size(); i++)
                    {
                        boolean hasCode = false;

                        String startingPortCode = terminalData.getFlightManifest().get(i).getStartingPortCode();
                        String destinationPortCode = terminalData.getFlightManifest().get(i).getDestinationPortCode();

                        /* Checking against itemList codes */

                        for (String code : itemList) {
                            for (int j = 0; j < terminalData.getAirportManifest().size(); j++)
                            {

                                String portID = terminalData.getAirportManifest().get(j).getAirportID();

                                if ((portID.equals(startingPortCode) || portID.equals(destinationPortCode)) && portID.equals(code))
                                {
                                    hasCode = true;
                                    break;
                                }
                            }

                            if (hasCode)
                            {
                                break;
                            }
                        }

                        /* Found Flight is now used for the report */

                        if (hasCode)
                        {
                            /* Flight data is formatted and added to the report */

                            report += ("\nFlight Manifest Index #" + i + ":\n");

                            currentFlight = terminalData.getFlightManifest().get(i);

                            flightData = "Flight ID- " + currentFlight.getFlightID()
                                    + "\nDeparture Time- " + currentFlight.getDepartTime()
                                    + "\nArrival Time- " + currentFlight.getArriveTime()
                                    + "\nDestination Airport Code- " + currentFlight.getDestinationPortCode()
                                    + "\nStarting Airport Code- " + currentFlight.getStartingPortCode()
                                    + "\nPlane Code- " + currentFlight.getAssociatedPlaneCode()
                                    + "\n";

                            report += flightData;

                            /* Related Airports are determined to be of a certain type */

                            for (int j = 0; j < terminalData.getAirportManifest().size(); j++)
                            {
                                for (int k = 0; k < itemList.size(); k++)
                                {
                                    /* If the airport is both the destination and starting point, the Flight is
                                    * a "Round-Trip" Flight */

                                    if (terminalData.getAirportManifest().get(j).getAirportID().equals(itemList.get(k))
                                            && ((terminalData.getAirportManifest().get(j).getAirportID().equals(currentFlight.getStartingPortCode()))
                                            || (terminalData.getAirportManifest().get(j).getAirportID().equals(currentFlight.getDestinationPortCode()))))
                                    {
                                        currentPort = terminalData.getAirportManifest().get(k);

                                        if (currentPort.getAirportID().equals(terminalData.getFlightManifest().get(i).getDestinationPortCode())
                                                && currentPort.getAirportID().equals(terminalData.getFlightManifest().get(i).getStartingPortCode()))
                                        {
                                            portData = "Round-Trip ";
                                        }

                                        else if (currentPort.getAirportID().equals(terminalData.getFlightManifest().get(i).getDestinationPortCode()))
                                        {
                                            portData = "Destination ";
                                        }

                                        else
                                        {
                                            portData = "Starting ";
                                        }

                                        /* Adding the Airport's data to the report */

                                        portData += "Airport ID- " + currentPort.getAirportID()
                                                + "\nName- " + currentPort.getName()
                                                + "\n";

                                        report += portData;
                                    }
                                }
                            }
                        }
                    }

                    break;
            }

            break;

        /* Plane-sorted Reports */

        case "Plane", "plane", "4":
            /* Based on genCode type, find associated Plane object */

            switch (genCode)
            {
                /* Tickets by Plane */

                case 1:
                    /* Find the Plane object related to any eligible Ticket with a code listed in the itemList*/

                    for (int i = 0; i < terminalData.getPlaneManifest().size(); i++)
                    {
                        boolean hasCode = false;
                        currentPlane = terminalData.getPlaneManifest().get(i);

                        for (String code : itemList)
                        {
                            for (int j = 0; j < terminalData.getTicketManifest().size(); j++) {

                                currentTicket = terminalData.getTicketManifest().get(j);

                                for (int k = 0; k < terminalData.getFlightManifest().size(); k++)
                                {
                                    /* Relating a Ticket to a Plane via a Flight */

                                    currentFlight = terminalData.getFlightManifest().get(k);

                                    if (currentTicket.getFlightCode().equals(currentFlight.getFlightID())
                                            && currentTicket.getTicketID().equals(code)
                                            && currentPlane.getPlaneID().equals(currentFlight.getAssociatedPlaneCode()))
                                    {
                                        hasCode = true;
                                        break;
                                    }
                                }

                                if (hasCode)
                                {
                                    break;
                                }
                            }

                            if (hasCode)
                            {
                                break;
                            }
                        }

                        /* Plane found is added to the report */

                        if (hasCode)
                        {
                            report += ("\nPlane Manifest Index #" + i + ":\n");

                            planeData = "Plane ID- " + currentPlane.getPlaneID()
                                    + "\nModel- " + currentPlane.getModel()
                                    + "\nPassenger Limit- " + currentPlane.getPassLimit()
                                    + "\nCurrent Assignment Code- " + currentPlane.getCurrentAssignmentCode()
                                    + "\nCurrent Assignment Type- " + currentPlane.getCurrentAssignmentType()
                                    + "\n";

                            report += planeData;

                            /* Add related Tickets */

                            for (int j = 0; j < terminalData.getTicketManifest().size(); j++)
                            {
                                currentTicket = terminalData.getTicketManifest().get(j);

                                for (Flight flight : terminalData.getFlightManifest())
                                {
                                    currentFlight = flight;

                                    for (int k = 0; k < itemList.size(); k++)
                                    {
                                        if (currentTicket.getFlightCode().equals(currentFlight.getFlightID())
                                                && currentTicket.getTicketID().equals(itemList.get(k))
                                                && currentPlane.getPlaneID().equals(currentFlight.getAssociatedPlaneCode()))
                                        {
                                            ticketData = "Ticket ID- " + currentTicket.getTicketID()
                                                    + "\nPaid Status- " + currentTicket.isPaidStatus()
                                                    + "\nFlight Code- " + currentTicket.getFlightCode()
                                                    + "\nPassenger Code- " + currentTicket.getPassengerCode()
                                                    + "\nSeating Compartment- " + currentTicket.getSeatCompartment()
                                                    + "\nChecked Bags- " + currentTicket.getCheckBags()
                                                    + "\n";

                                            report += ticketData;
                                        }
                                    }
                                }
                            }
                        }
                    }

                    break;

                /* Passengers by Plane */

                case 2:
                    /* Find related Planes */

                    for (int i = 0; i < terminalData.getPlaneManifest().size(); i++)
                    {
                        boolean hasCode = false;
                        currentPlane = terminalData.getPlaneManifest().get(i);

                        /* Check for itemList passenger ID codes */

                        for (String code : itemList)
                        {
                            for (int j = 0; j < terminalData.getTicketManifest().size(); j++) {

                                currentTicket = terminalData.getTicketManifest().get(j);

                                for (int k = 0; k < terminalData.getFlightManifest().size(); k++)
                                {
                                    /* Relating a Passenger to a Plane via a Ticket's associated Flight
                                    information */

                                    currentFlight = terminalData.getFlightManifest().get(k);

                                    if (currentTicket.getFlightCode().equals(currentFlight.getFlightID())
                                            && currentTicket.getPassengerCode().equals(code)
                                            && currentPlane.getPlaneID().equals(currentFlight.getAssociatedPlaneCode()))
                                    {
                                        hasCode = true;
                                        break;
                                    }
                                }

                                if (hasCode)
                                {
                                    break;
                                }
                            }

                            if (hasCode)
                            {
                                break;
                            }
                        }

                        /* Plane found is added to the report */

                        if (hasCode)
                        {
                            report += ("\nPlane Manifest Index #" + i + ":\n");

                            planeData = "Plane ID- " + currentPlane.getPlaneID()
                                    + "\nModel- " + currentPlane.getModel()
                                    + "\nPassenger Limit- " + currentPlane.getPassLimit()
                                    + "\nCurrent Assignment Code- " + currentPlane.getCurrentAssignmentCode()
                                    + "\nCurrent Assignment Type- " + currentPlane.getCurrentAssignmentType()
                                    + "\n";

                            report += planeData;

                            /* Adding related Passengers */

                            for (int j = 0; j < terminalData.getTicketManifest().size(); j++)
                            {
                                currentTicket = terminalData.getTicketManifest().get(j);

                                for (Flight flight : terminalData.getFlightManifest())
                                {
                                    currentFlight = flight;

                                    for (int k = 0; k < itemList.size(); k++)
                                    {
                                        if (currentTicket.getFlightCode().equals(currentFlight.getFlightID())
                                                && currentTicket.getPassengerCode().equals(itemList.get(k))
                                                && currentPlane.getPlaneID().equals(currentFlight.getAssociatedPlaneCode()))
                                        {
                                            /* Checking if a Passenger matches the itemList entry and all
                                            * related ID code strings */

                                            for (Passenger passenger : terminalData.getPassengerManifest())
                                            {
                                                if (passenger.getPassengerID().equals(itemList.get(k)))
                                                {
                                                    currentPass = passenger;
                                                    break;
                                                }
                                            }

                                            /* Adding related Ticket data */

                                            ticketData = "Ticket ID- " + currentTicket.getTicketID()
                                                    + "\nPaid Status- " + currentTicket.isPaidStatus()
                                                    + "\nFlight Code- " + currentTicket.getFlightCode()
                                                    + "\nPassenger Code- " + currentTicket.getPassengerCode()
                                                    + "\nSeating Compartment- " + currentTicket.getSeatCompartment()
                                                    + "\nChecked Bags- " + currentTicket.getCheckBags()
                                                    + "\n";

                                            report += ticketData;

                                            /* Adding the Passenger data for the Ticket */

                                            if (currentPass != null)
                                            {
                                                passData = "Passenger ID- " + currentPass.getPassengerID()
                                                        + "\nName- " + currentPass.getName()
                                                        + "\nDOB- " + currentPass.getDOB()
                                                        + "\nGender- " + currentPass.getGender()
                                                        + "\nPhone Number- " + currentPass.getPhone()
                                                        + "\nEmail Address- " + currentPass.getEmail()
                                                        + "\nHome Address- " + currentPass.getHome()
                                                        + "\nGovernment ID Number- " + currentPass.getGovID()
                                                        + "\n";

                                                report += passData;
                                            }

                                            /* Passenger codes pointing to a nonexistent Passenger are screened
                                            * here */

                                            else
                                            {
                                                report += "Error: Ticket '" + currentTicket.getTicketID()
                                                        + "' for Passenger '" + currentTicket.getPassengerCode()
                                                        + "' is assigned to a non-existent passenger code.\n";
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }

                    break;

                /* Flights by Plane */

                case 3:
                    /* Get related Planes */

                    for (int i = 0; i < terminalData.getPlaneManifest().size(); i++)
                    {
                        boolean hasCode = false;
                        currentPlane = terminalData.getPlaneManifest().get(i);

                        /* Get eligible related Flights */

                        for (String code : itemList)
                        {
                            for (int j = 0; j < terminalData.getFlightManifest().size(); j++)
                            {
                                currentFlight = terminalData.getFlightManifest().get(j);

                                if (currentFlight.getAssociatedPlaneCode().equals(currentPlane.getPlaneID())
                                        && currentFlight.getFlightID().equals(code))
                                {
                                    hasCode = true;
                                    break;
                                }
                            }

                            if (hasCode)
                            {
                                break;
                            }
                        }

                        /* Found Plane is now added to report */

                        if (hasCode)
                        {
                            report += ("\nPlane Manifest Index #" + i + ":\n");

                            planeData = "Plane ID- " + currentPlane.getPlaneID()
                                    + "\nModel- " + currentPlane.getModel()
                                    + "\nPassenger Limit- " + currentPlane.getPassLimit()
                                    + "\nCurrent Assignment Code- " + currentPlane.getCurrentAssignmentCode()
                                    + "\nCurrent Assignment Type- " + currentPlane.getCurrentAssignmentType()
                                    + "\n";

                            report += planeData;

                            /* Add related Flights to report */

                            for (Flight flight : terminalData.getFlightManifest())
                            {
                                currentFlight = flight;

                                for (String code : itemList)
                                {
                                    if (currentFlight.getFlightID().equals(code)
                                            && currentFlight.getAssociatedPlaneCode().equals(currentPlane.getPlaneID()))
                                    {
                                        flightData = "Flight ID- " + currentFlight.getFlightID()
                                                + "\nDeparture Time- " + currentFlight.getDepartTime()
                                                + "\nArrival Time- " + currentFlight.getArriveTime()
                                                + "\nDestination Airport Code- " + currentFlight.getDestinationPortCode()
                                                + "\nStarting Airport Code- " + currentFlight.getStartingPortCode()
                                                + "\nPlane Code- " + currentFlight.getAssociatedPlaneCode()
                                                + "\n";

                                        report += flightData;
                                    }
                                }
                            }
                        }
                    }

                    break;

                /* Planes by Plane or Airports by Plane (error) */

                case 4, 5:
                    System.out.println("Error: Plane-sorted reports may not be generated for Planes or Airports\n");
                    break;
            }

            break;

        /* Airport-sorted Reports */

        case "Airport", "airport", "5":
            /* Based on genCode type, get related Airports */

            switch (genCode)
            {
                /* Tickets by Airport */

                case 1:
                    /* Get related Airports */

                    for (int i = 0; i < terminalData.getAirportManifest().size(); i++) {
                        currentPort = terminalData.getAirportManifest().get(i);

                        boolean hasCode = false;

                        for (String code : itemList)
                        {
                            for (int j = 0; j < terminalData.getTicketManifest().size(); j++)
                            {
                                currentTicket = terminalData.getTicketManifest().get(j);

                                for (int k = 0; k < terminalData.getFlightManifest().size(); k++)
                                {
                                    /* Relating Tickets to Airports via Flights */

                                    currentFlight = terminalData.getFlightManifest().get(k);

                                    if (currentFlight.getFlightID().equals(currentTicket.getFlightCode())
                                            && currentTicket.getTicketID().equals(code)
                                            && (currentFlight.getDestinationPortCode().equals(currentPort.getAirportID())
                                            || currentFlight.getStartingPortCode().equals(currentPort.getAirportID())))
                                    {
                                        hasCode = true;
                                        break;
                                    }
                                }

                                if (hasCode)
                                {
                                    break;
                                }
                            }

                            if (hasCode)
                            {
                                break;
                            }
                        }

                        /* Found Airport is added to Report */

                        if (hasCode)
                        {
                            report += ("\nAirport Manifest Index #" + i + ":\n");

                            portData = "Airport ID- " + currentPort.getAirportID()
                                    + "\nName- " + currentPort.getName()
                                    + "\n";

                            report += portData;

                            /* Adding data for related Tickets */

                            for (String code : itemList)
                            {
                                for (int j = 0; j < terminalData.getTicketManifest().size(); j++)
                                {
                                    currentTicket = terminalData.getTicketManifest().get(j);

                                    for (int k = 0; k < terminalData.getFlightManifest().size(); k++)
                                    {
                                        /* Based on Flight information, the Ticket is assigned a base type
                                        before its data is printed */

                                        currentFlight = terminalData.getFlightManifest().get(k);

                                        if (currentFlight.getFlightID().equals(currentTicket.getFlightCode())
                                                && currentTicket.getTicketID().equals(code)
                                                && (currentFlight.getDestinationPortCode().equals(currentPort.getAirportID())
                                                || currentFlight.getStartingPortCode().equals(currentPort.getAirportID())))
                                        {
                                            if (currentFlight.getStartingPortCode().equals(currentPort.getAirportID())
                                                    && currentFlight.getDestinationPortCode().equals(currentPort.getAirportID()))
                                            {
                                                ticketData = "Round-Trip ";
                                            }

                                            else if (currentFlight.getStartingPortCode().equals(currentPort.getAirportID()))
                                            {
                                                ticketData = "Departing ";
                                            }

                                            else
                                            {
                                                ticketData = "Arriving ";
                                            }

                                            /* Adding the ticket's data after the type is given */

                                            ticketData += "Ticket ID- " + currentTicket.getTicketID()
                                                    + "\nPaid Status- " + currentTicket.isPaidStatus()
                                                    + "\nFlight Code- " + currentTicket.getFlightCode()
                                                    + "\nPassenger Code- " + currentTicket.getPassengerCode()
                                                    + "\nSeating Compartment- " + currentTicket.getSeatCompartment()
                                                    + "\nChecked Bags- " + currentTicket.getCheckBags()
                                                    + "\n";

                                            report += ticketData;
                                        }
                                    }
                                }
                            }
                        }
                    }

                    break;

                /* Passengers by Airport */

                case 2:
                    /* Find related Airports */

                    for (int i = 0; i < terminalData.getAirportManifest().size(); i++) {
                        currentPort = terminalData.getAirportManifest().get(i);

                        boolean hasCode = false;

                        /* Find related Passengers */

                        for (String code : itemList)
                        {
                            for (int j = 0; j < terminalData.getTicketManifest().size(); j++)
                            {
                                currentTicket = terminalData.getTicketManifest().get(j);

                                for (int k = 0; k < terminalData.getFlightManifest().size(); k++)
                                {
                                    /* Relating Passengers to Airports via Tickets and Flights */

                                    currentFlight = terminalData.getFlightManifest().get(k);

                                    if (currentFlight.getFlightID().equals(currentTicket.getFlightCode())
                                            && currentTicket.getPassengerCode().equals(code)
                                            && (currentFlight.getDestinationPortCode().equals(currentPort.getAirportID())
                                            || currentFlight.getStartingPortCode().equals(currentPort.getAirportID())))
                                    {
                                        hasCode = true;
                                        break;
                                    }
                                }

                                if (hasCode)
                                {
                                    break;
                                }
                            }

                            if (hasCode)
                            {
                                break;
                            }
                        }

                        /* Found Airport is added to report */

                        if (hasCode)
                        {
                            report += ("\nAirport Manifest Index #" + i + ":\n");

                            portData = "Airport ID- " + currentPort.getAirportID()
                                    + "\nName- " + currentPort.getName()
                                    + "\n";

                            report += portData;

                            /* Find related Passengers */

                            for (Passenger passenger : terminalData.getPassengerManifest())
                            {
                                boolean found = false;
                                currentPass = passenger;

                                /* Checking for itemList code matches */

                                for (String code : itemList)
                                {
                                    /* Checking Ticket data */

                                    for (int j = 0; j < terminalData.getTicketManifest().size(); j++)
                                    {
                                        currentTicket = terminalData.getTicketManifest().get(j);

                                        /* Checking Flight data */

                                        for (int k = 0; k < terminalData.getFlightManifest().size(); k++)
                                        {
                                            /* Checking the current Flight against other Flights with Tickets
                                            * whose associated Passenger codes match the itemList code and
                                            * the current Passenger, to check if the Flight is temporally
                                            * superseded by any other Flights. Flight "inferiority" is
                                            * represented in the formatting of the report */

                                            currentFlight = terminalData.getFlightManifest().get(k);
                                            boolean inferior = false;

                                            for (Flight flight : terminalData.getFlightManifest())
                                            {
                                                for (Ticket ticket : terminalData.getTicketManifest())
                                                {
                                                    /* First, check the Flights match the Tickets which then
                                                    * match with the current Passenger */

                                                    if (flight.getFlightID().equals(ticket.getFlightCode())
                                                            && ticket.getPassengerCode().equals(code)
                                                            && currentFlight.getFlightID().equals(currentTicket.getFlightCode())
                                                            && currentTicket.getPassengerCode().equals(code)

                                                            /* Next, check if the compared Flight arrived or
                                                            * departed more recently than the current Flight,
                                                            * or if the current Flight is yet to depart and
                                                            * there is another Flight which the current Plane
                                                            * has previously arrived on */

                                                            && ((flight.getDestinationPortCode().equals(currentPort.getAirportID())
                                                            && currentFlight.getDestinationPortCode().equals(currentPort.getAirportID())
                                                            && flight.getArriveTime() > currentFlight.getArriveTime()
                                                            && flight.getArriveTime() <= terminalData.getSystemTime())

                                                            || (flight.getStartingPortCode().equals(currentPort.getAirportID())
                                                            && currentFlight.getStartingPortCode().equals(currentPort.getAirportID())
                                                            && flight.getDepartTime() > currentFlight.getDepartTime()
                                                            && flight.getDepartTime() <= terminalData.getSystemTime())

                                                            || (flight.getDestinationPortCode().equals(currentPort.getAirportID())
                                                            && flight.getArriveTime() <= terminalData.getSystemTime()
                                                            && currentFlight.getDepartTime() > terminalData.getSystemTime()
                                                            && currentFlight.getStartingPortCode().equals(currentPort.getAirportID()))))
                                                    {
                                                        inferior = true;
                                                        break;
                                                    }
                                                }

                                                if (inferior)
                                                {
                                                    break;
                                                }
                                            }

                                            /* Ensure the currentFlight is matching, and not temporally inferior
                                            * to another flight */

                                            if (currentFlight.getFlightID().equals(currentTicket.getFlightCode())
                                                    && currentTicket.getPassengerCode().equals(code)
                                                    && currentPass.getPassengerID().equals(code)
                                                    && (currentFlight.getDestinationPortCode().equals(currentPort.getAirportID())
                                                    || currentFlight.getStartingPortCode().equals(currentPort.getAirportID()))
                                                    && !inferior)
                                            {
                                                /* Output Passenger data formatted by temporal data for the
                                                * current Flight */

                                                passData = "Passenger Scheduled";

                                                if (currentFlight.getArriveTime() <= terminalData.getSystemTime()
                                                        && currentFlight.getDestinationPortCode().equals(currentPort.getAirportID()))
                                                {
                                                    passData += " Post-Arrival--";
                                                }

                                                else if (currentFlight.getDepartTime() <= terminalData.getSystemTime()
                                                        && currentFlight.getStartingPortCode().equals(currentPort.getAirportID()))
                                                {
                                                    passData += " Post-Departure--";
                                                }

                                                else if (currentFlight.getDestinationPortCode().equals(currentPort.getAirportID()))
                                                {
                                                    passData += " for First-Time Arrival--";
                                                }

                                                else
                                                {
                                                    passData += " for First-Time Departure--";
                                                }

                                                passData += "\nPassenger ID- " + currentPass.getPassengerID()
                                                        + "\nName- " + currentPass.getName()
                                                        + "\nDOB- " + currentPass.getDOB()
                                                        + "\nGender- " + currentPass.getGender()
                                                        + "\nPhone Number- " + currentPass.getPhone()
                                                        + "\nEmail Address- " + currentPass.getEmail()
                                                        + "\nHome Address- " + currentPass.getHome()
                                                        + "\nGovernment ID Number- " + currentPass.getGovID()
                                                        + "\n";

                                                report += passData;

                                                found = true;
                                                break;
                                            }
                                        }

                                        if (found)
                                        {
                                            break;
                                        }
                                    }

                                    if (found)
                                    {
                                        break;
                                    }
                                }
                            }
                        }
                    }

                    break;

                /* Flights by Airport  */

                case 3:
                    /* Get related Airports */

                    for (int i = 0; i < terminalData.getAirportManifest().size(); i++)
                    {
                        currentPort = terminalData.getAirportManifest().get(i);

                        boolean hasCode = false;

                        /* Check for related Flights with codes represented in the itemList */

                        for (String code : itemList)
                        {
                            for (int j = 0; j < terminalData.getFlightManifest().size(); j++)
                            {
                                currentFlight = terminalData.getFlightManifest().get(j);

                                if ((currentFlight.getStartingPortCode().equals(currentPort.getAirportID())
                                        || currentFlight.getDestinationPortCode().equals(currentPort.getAirportID()))
                                        && currentFlight.getFlightID().equals(code))
                                {
                                    hasCode = true;
                                    break;
                                }
                            }

                            if (hasCode)
                            {
                                break;
                            }
                        }

                        /* Add found Airport data to report */

                        if (hasCode)
                        {
                            report += ("\nAirport Manifest Index #" + i + ":\n");

                            portData = "Airport ID- " + currentPort.getAirportID()
                                    + "\nName- " + currentPort.getName()
                                    + "\n";

                            report += portData;

                            /* Add related Flight data to report*/

                            for (String code : itemList)
                            {
                                for (int j = 0; j < terminalData.getFlightManifest().size(); j++)
                                {
                                    currentFlight = terminalData.getFlightManifest().get(j);

                                    if ((currentFlight.getStartingPortCode().equals(currentPort.getAirportID())
                                            || currentFlight.getDestinationPortCode().equals(currentPort.getAirportID()))
                                            && currentFlight.getFlightID().equals(code))
                                    {
                                        /* Add a prefix to the report to show the type of flight, using the
                                        * System Time to check the Flight's status */

                                        flightData = "Flight Scheduled";

                                        /* Round-trip flights are considered here */

                                        if ((currentFlight.getStartingPortCode().equals(currentPort.getAirportID())
                                                && currentFlight.getDestinationPortCode().equals(currentPort.getAirportID())))
                                        {
                                            flightData += " Round-Trip";

                                            if (currentFlight.getArriveTime() <= terminalData.getSystemTime())
                                            {
                                                flightData += " Arrived--";
                                            }

                                            else if (currentFlight.getDepartTime() <= terminalData.getSystemTime())
                                            {
                                                flightData += " Departed/Arriving--";
                                            }

                                            else
                                            {
                                                flightData += " Departing--";
                                            }
                                        }

                                        /* Departing/ed Flights */

                                        else if ((currentFlight.getStartingPortCode().equals(currentPort.getAirportID())))
                                        {
                                            flightData += " Depart";

                                            if (currentFlight.getArriveTime() <= terminalData.getSystemTime())
                                            {
                                                flightData += "ed--";
                                            }

                                            else
                                            {
                                                flightData += "ing--";
                                            }
                                        }

                                        /* Arriving/ed Flights */

                                        else
                                        {
                                            flightData += " Arriv";

                                            if (currentFlight.getArriveTime() <= terminalData.getSystemTime())
                                            {
                                                flightData += "ed--";
                                            }

                                            else
                                            {
                                                flightData += "ing--";
                                            }
                                        }

                                        flightData += "\nFlight ID- " + currentFlight.getFlightID()
                                                + "\nDeparture Time- " + currentFlight.getDepartTime()
                                                + "\nArrival Time- " + currentFlight.getArriveTime()
                                                + "\nDestination Airport Code- " + currentFlight.getDestinationPortCode()
                                                + "\nStarting Airport Code- " + currentFlight.getStartingPortCode()
                                                + "\nPlane Code- " + currentFlight.getAssociatedPlaneCode()
                                                + "\n";

                                        report += flightData;

                                        break;
                                    }
                                }
                            }
                        }
                    }

                    break;

                /* Planes by Airport */

                case 4:
                    /* Get related Airports */

                    for (int i = 0; i < terminalData.getAirportManifest().size(); i++)
                    {
                        currentPort = terminalData.getAirportManifest().get(i);

                        boolean hasCode = false;

                        /* Get related Planes with PlaneID codes in the itemList */

                        for (String code : itemList)
                        {
                            for (int j = 0; j < terminalData.getPlaneManifest().size(); j++)
                            {
                                currentPlane = terminalData.getPlaneManifest().get(j);

                                if (currentPlane.getPlaneID().equals(code)
                                        && currentPlane.getCurrentAssignmentCode().equals(currentPort.getAirportID())
                                        && currentPlane.getCurrentAssignmentType().equals("Airport"))
                                {
                                    hasCode = true;
                                    break;
                                }
                            }

                            if (hasCode)
                            {
                                break;
                            }
                        }

                        /* Add found Airport to the report */

                        if (hasCode)
                        {
                            report += ("\nAirport Manifest Index #" + i + ":\n");

                            portData = "Airport ID- " + currentPort.getAirportID()
                                    + "\nName- " + currentPort.getName()
                                    + "\n";

                            report += portData;

                            /* Add related Plane data to the report*/

                            for (String code : itemList)
                            {
                                for (int j = 0; j < terminalData.getPlaneManifest().size(); j++)
                                {
                                    currentPlane = terminalData.getPlaneManifest().get(j);

                                    if (currentPlane.getPlaneID().equals(code)
                                            && currentPlane.getCurrentAssignmentCode().equals(currentPort.getAirportID())
                                            && currentPlane.getCurrentAssignmentType().equals("Airport"))
                                    {
                                        planeData = "Plane ID- " + currentPlane.getPlaneID()
                                                + "\nModel- " + currentPlane.getModel()
                                                + "\nPassenger Limit- " + currentPlane.getPassLimit()
                                                + "\nCurrent Assignment Code- " + currentPlane.getCurrentAssignmentCode()
                                                + "\nCurrent Assignment Type- " + currentPlane.getCurrentAssignmentType()
                                                + "\n";

                                        report += planeData;

                                        break;
                                    }
                                }
                            }
                        }
                    }

                    break;

                /* Airports by Airport (error) */

                case 5:
                    System.out.println("Error: Airport-sorted reports may not be generated for Airports\n");

                    break;
            }

            break;
        }

        /* Finally, return the generated/sorted report */

        return report;
    }
}