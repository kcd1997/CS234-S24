package MainPackage;

/**
 * The Airport class defines the data, getters, and setters needed for an Airport object. Only an ID code (for system
 * use) and a name are needed for Airports.
 * @author Kyle Dean, Mason Herbig, and Lwesso Mukeni
 */

public class Airport
{
    /* Airport variables include the Airport's ID (for use in fetching and verifying the object within the TerminalData
    system) and an Airport Name. */
    private String airportID;
    private String name;

    /**
     * This is the constructor for the Airport class, which sets the airport object's data
     * @param airportID The Airport's ID code
     * @param name The Airport's name
     */
    public Airport(String airportID, String name)
    {
        this.airportID = airportID;
        this.name = name;
    }

    /**
     * getAirportID gets the ID string for the airport object.
     * @return the airport's ID string.
     */
    public String getAirportID()
    {
        return airportID;
    }

    /**
     * setAirportID changes the airport object's ID string to the value of the input parameter.
     * @param airportID the string value to which this airport's ID value is changed.
     */
    public void setAirportID(String airportID)
    {
        this.airportID = airportID;
    }

    /**
     * getName gets the Name string for the airport object.
     * @return the airport's name string.
     */
    public String getName()
    {
        return name;
    }

    /**
     * setName changes the airport object's name string to the value of the input parameter.
     * @param name the sting value to which this airport's name value is changed.
     */
    public void setName(String name)
    {
        this.name = name;
    }
}