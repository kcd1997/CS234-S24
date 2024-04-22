package MainPackage;

import java.util.ArrayList;

/**
 * The TerminalData class is the storage structure central to the Terminal Ticketing Software system, and contains
 * ArrayLists of each of the major system object data types (Ticket, Passenger, Flight, Plane, and Airport), as well
 * as methods to manipulate each of these lists.
 * @author Kyle Dean, Mason Herbig, and Lwesso Mukeni
 */

public class TerminalData
{
    /* The TerminalData structure initializes an ArrayList for each of the major system data types, Ticket, Passenger,
     * Flight, Plane, and Airport, and initializes the variable which stores the master system time */
    private ArrayList<Passenger> passengerManifest;
    private ArrayList<Airport> airportManifest;
    private ArrayList<Plane> planeManifest;
    private ArrayList<Flight> flightManifest;
    private ArrayList<Ticket> ticketManifest;
    private double systemTime;

    /**
     * This is the constructor for the TerminalData class
     * @param init This boolean determines if the TerminalData object should be created with initial values in each of
     *             its ArrayLists
     * @param passengerManifest This is the ArrayList of Passenger objects
     * @param airportManifest This is the ArrayList of Airport objects
     * @param planeManifest This is the ArrayList of Plane objects
     * @param flightManifest This is the ArrayList of Flight objects
     * @param ticketManifest This is the ArrayList of Ticket objects
     * @param systemTime This is the time used by the system to check for which flights have departed and arrived,
     *                   and for location data of planes based on flights.
     */
    TerminalData(boolean init, ArrayList<Passenger> passengerManifest, ArrayList<Airport> airportManifest,
                 ArrayList<Plane> planeManifest, ArrayList<Flight> flightManifest, ArrayList<Ticket> ticketManifest, double systemTime)
    {
        this.passengerManifest = passengerManifest;
        this.airportManifest = airportManifest;
        this.planeManifest = planeManifest;
        this.flightManifest = flightManifest;
        this.ticketManifest = ticketManifest;
        this.systemTime = systemTime;

        /* If the init boolean is true, the object should be provided with initial values in each of its ArrayLists */

        if (init)
        {
            passengerManifest.add(new Passenger("*INIT*", "*INIT*", "*INIT*", "*INIT*",
                    "*INIT*", "*INIT*", "*INIT*", "*INIT*"));

            airportManifest.add(new Airport("*INIT*", "*INIT*"));

            planeManifest.add(new Plane("*INIT*", "*INIT*", 0, "*INIT*", "*INIT*"));

            flightManifest.add(new Flight("*INIT*", 0, 0, "*INIT*",
                    "*INIT*", "*INIT*"));

            ticketManifest.add(new Ticket("*INIT*", false, "*INIT*", "*INIT*",
                    (byte) 0, (byte) 0));
        }
    }

    /**
     * toFileData uses the data stored within the TerminalData object passed to generate a delimited string which can
     * later be read back into a TerminalData object from a file.
     * @param terminalData The object from which the data is being taken and concatenated into a string.
     * @return The string generated by the TerminalData object's stored data.
     */
    public String toFileData(TerminalData terminalData)
    {
        String fileData = "";

        /* For each of the major system data types, add all of their various stored data in delimited strings,
        * further delimiting each object by another delimiter, and each type by a final delimiter */

        for(Ticket i : terminalData.getTicketManifest())
        {
            fileData += "Ticket%" + i.getTicketID() + "%" + i.isPaidStatus()
                    + "%" + i.getFlightCode() + "%" + i.getPassengerCode() + "%"
                    + i.getSeatCompartment() + "%" + i.getCheckBags() + "%|";
        }
        fileData += "/";

        for(Passenger i : terminalData.getPassengerManifest())
        {
            fileData += "Passenger%" + i.getPassengerID() + "%" + i.getName()
                    + "%" + i.getDOB() + "%" + i.getGender() + "%" + i.getPhone()
                    + "%" + i.getEmail() + "%" + i.getHome() + "%" + i.getGovID() + "%|";
        }
        fileData += "/";

        for(Flight i : terminalData.getFlightManifest())
        {
            fileData += "Flight%" + i.getFlightID() + "%" + i.getDepartTime()
                    + "%" + i.getArriveTime() + "%" + i.getDestinationPortCode()
                    + "%" + i.getStartingPortCode() + "%" + i.getAssociatedPlaneCode() + "%|";
        }
        fileData += "/";

        for(Plane i : terminalData.getPlaneManifest())
        {
            fileData += "Plane%" + i.getPlaneID() + "%" + i.getModel() + "%" + i.getPassLimit()
                    + "%" + i.getCurrentAssignmentCode() + "%" + i.getCurrentAssignmentType() + "%|";
        }
        fileData += "/";

        for(Airport i : terminalData.getAirportManifest())
        {
            fileData += "Airport%" + i.getAirportID() + "%" + i.getName() + "%|";
        }
        fileData += "/";

        /* Finally, add the System time value to the end of the string */

        fileData += "System Time%";
        fileData += systemTime + "%|/";

        return fileData;
    }

    /**
     * toFileData with no parameters uses the toFileData(TerminalData) function on this object to generate a string.
     * @return The string generated by the toFileData function called on this object.
     */
    public String toFileData()
    {
        return toFileData(this);
    }

    /**
     * toTerminalData reads in a delimited string and converts the information to data objects for use in the software.
     * @param fileData The delimited string which is to be read and converted to data objects.
     * @return The TerminalData object created by the information in the fileData string
     */
    public TerminalData toTerminalData(String fileData)
    {
        /* First, initialize the ArrayLists which will be contained in the new TerminalData object */

        ArrayList<Passenger> passengerData = new ArrayList<Passenger>();
        ArrayList<Plane> planeData = new ArrayList<Plane>();
        ArrayList<Flight> flightData = new ArrayList<Flight>();
        ArrayList<Airport> airportData = new ArrayList<Airport>();
        ArrayList<Ticket> ticketData = new ArrayList<Ticket>();

        /* Next, read in the fileData by the outermost delimiter, which denotes type, and create an ArrayList of those
        * generated strings */

        Reader fileIn = new Reader(fileData, "/");

        ArrayList<String> fileArray = new ArrayList<String>();
        fileArray = fileIn.readOutList();

        /* Next, for each type, read in each object's data via the next delimiter, which denotes object boundary */

        for (String i : fileArray)
        {
            /* Replace the next delimiter due to escape character problems related to "|" behavior in String.split
            * as used in Reader.readoutList */

            String replacement = i.replaceAll("\\|","/");

            /* Create the reader object, and read in the objects by the delimiter into the objectInArray ArrayList */

            Reader objectIn = new Reader(replacement, "/");

            ArrayList<String> objectInArray = new ArrayList<String>();
            objectInArray = objectIn.readOutList();

            /* For each object added to the objectInArray, create an object of the appropriate type with the given
            * data values */

            for (String k : objectInArray)
            {
                /* Read the object's data via the final delimiter */

                Reader dataIn = new Reader(k, "%");

                /* Create the list of data values */

                ArrayList<String> dataInArray = new ArrayList<String>();
                dataInArray = dataIn.readOutList();

                /* Based on the data type string given, create an object with the appropriate values */

                switch(dataInArray.get(0))
                {
                    case "Ticket":
                        Ticket newTicket = new Ticket(dataInArray.get(1), Boolean.parseBoolean(dataInArray.get(2)),
                                dataInArray.get(3), dataInArray.get(4), Byte.parseByte(dataInArray.get(5)),
                                Byte.parseByte(dataInArray.get(6)));
                        ticketData.add(newTicket);
                        break;

                    case "Passenger":
                        Passenger newPassenger = new Passenger(dataInArray.get(1), dataInArray.get(2),
                                dataInArray.get(3), dataInArray.get(4), dataInArray.get(5), dataInArray.get(6),
                                dataInArray.get(7), dataInArray.get(8));
                        passengerData.add(newPassenger);
                        break;

                    case "Flight":
                        Flight newFlight = new Flight(dataInArray.get(1), Double.parseDouble(dataInArray.get(2)),
                                Double.parseDouble(dataInArray.get(3)), dataInArray.get(4), dataInArray.get(5),
                                dataInArray.get(6));
                        flightData.add(newFlight);
                        break;

                    case "Plane":
                        Plane newPlane = new Plane(dataInArray.get(1), dataInArray.get(2),
                                Integer.parseInt(dataInArray.get(3)), dataInArray.get(4), dataInArray.get(5));
                        planeData.add(newPlane);
                        break;

                    case "Airport":
                        Airport newAirport = new Airport(dataInArray.get(1), dataInArray.get(2));
                        airportData.add(newAirport);
                        break;

                    case "System Time":
                        this.setSystemTime(Double.parseDouble(dataInArray.get(1)));
                        break;
                }
            }
        }

        /* Assign the completed lists to their object references, then return the new TerminalData structure */

        ticketManifest = ticketData;
        passengerManifest = passengerData;
        flightManifest = flightData;
        planeManifest = planeData;
        airportManifest = airportData;

        return this;
    }

    /**
     * getManifest functions return the corresponding ArrayList for the desired object type which is stored in the
     * TerminalData object.
     * @return the Passenger Manifest ArrayList.
     */
    public ArrayList<Passenger> getPassengerManifest()
    {
        return passengerManifest;
    }

    /**
     * setManifest functions set the corresponding ArrayList for the desired object type (stored in the TerminalData
     * object) to the passed ArrayList.
     * @param passengerManifest the Passenger Manifest ArrayList to which this object's passengerManifest will be set.
     */
    public void setPassengerManifest(ArrayList<Passenger> passengerManifest)
    {
        this.passengerManifest = passengerManifest;
    }

    /**
     * getManifest functions return the corresponding ArrayList for the desired object type which is stored in the
     * TerminalData object.
     * @return the Airport Manifest ArrayList.
     */
    public ArrayList<Airport> getAirportManifest()
    {
        return airportManifest;
    }

    /**
     * setManifest functions set the corresponding ArrayList for the desired object type (stored in the TerminalData
     * object) to the passed ArrayList.
     * @param airportManifest the Airport Manifest ArrayList to which this object's airportManifest will be set.
     */
    public void setAirportManifest(ArrayList<Airport> airportManifest)
    {
        this.airportManifest = airportManifest;
    }

    /**
     * getManifest functions return the corresponding ArrayList for the desired object type which is stored in the
     * TerminalData object.
     * @return the Plane Manifest ArrayList.
     */
    public ArrayList<Plane> getPlaneManifest()
    {
        return planeManifest;
    }

    /**
     * setManifest functions set the corresponding ArrayList for the desired object type (stored in the TerminalData
     * object) to the passed ArrayList.
     * @param planeManifest the Plane Manifest ArrayList to which this object's planeManifest will be set.
     */
    public void setPlaneManifest(ArrayList<Plane> planeManifest)
    {
        this.planeManifest = planeManifest;
    }

    /**
     * getManifest functions return the corresponding ArrayList for the desired object type which is stored in the
     * TerminalData object.
     * @return the Flight Manifest ArrayList.
     */
    public ArrayList<Flight> getFlightManifest()
    {
        return flightManifest;
    }

    /**
     * setManifest functions set the corresponding ArrayList for the desired object type (stored in the TerminalData
     * object) to the passed ArrayList.
     * @param flightManifest the Flight Manifest ArrayList to which this object's flightManifest will be set.
     */
    public void setFlightManifest(ArrayList<Flight> flightManifest)
    {
        this.flightManifest = flightManifest;
    }

    /**
     * getManifest functions return the corresponding ArrayList for the desired object type which is stored in the
     * TerminalData object.
     * @return the Ticket Manifest ArrayList.
     */
    public ArrayList<Ticket> getTicketManifest()
    {
        return ticketManifest;
    }

    /**
     * setManifest functions set the corresponding ArrayList for the desired object type (stored in the TerminalData
     * object) to the passed ArrayList.
     * @param ticketManifest the Ticket Manifest ArrayList to which this object's ticketManifest will be set.
     */
    public void setTicketManifest(ArrayList<Ticket> ticketManifest)
    {
        this.ticketManifest = ticketManifest;
    }

    /**
     * getSystemTime retrieves the stored master System Time variable.
     * @return the systemTime variable stored in this object.
     */
    public double getSystemTime()
    {
        return systemTime;
    }

    /**
     * setSystemTime sets the stored master System Time variable to the passed double value.
     * @param systemTime The double value to which the systemTime variable stored in this object will be set.
     */
    public void setSystemTime(double systemTime)
    {
        this.systemTime = systemTime;
    }
}