/**
 * The Flight class defines the data, getters, and setters needed for a Flight object, including:
 * -Flight ID (for use in the terminalData structure)
 * -The flight's time of departure
 * -The flight's time of arrival
 * -The port the flight is flying to
 * -The port the flight is flying from
 * -The plane being used for the flight
 * @author Kyle Dean, Mason Herbig, and Lwesso Mukeni
 */

public class Flight
{
    /* Flight variables include the Flight's ID (for use in fetching and verifying the object within the TerminalData
    system), the time of departure, time of arrival, destination, starting point, and Plane in use */
    private String flightID;
    private double departTime;
    private double arriveTime;
    private String destinationPortCode;
    private String startingPortCode;
    private String associatedPlaneCode;

    /**
     * This is the constructor for the Flight class, which sets the Flight object's data
     * @param flightID The Flight's ID code
     * @param departTime The Flight's time of departure
     * @param arriveTime The Flight's time of arrival
     * @param destinationPortCode The Flight's destination
     * @param startingPortCode The Flight's starting point
     * @param associatedPlaneCode The ID code of the Plane assigned to the Flight
     */
    public Flight(String flightID, double departTime, double arriveTime, String destinationPortCode, String startingPortCode, String associatedPlaneCode)
    {
        this.flightID = flightID;
        this.departTime = departTime;
        this.arriveTime = arriveTime;
        this.destinationPortCode = destinationPortCode;
        this.startingPortCode = startingPortCode;
        this.associatedPlaneCode = associatedPlaneCode;
    }

    /**
     * getFlightID gets the ID string for the flight object.
     * @return the flight's ID string.
     */
    public String getFlightID()
    {
        return flightID;
    }

    /**
     * setFlightID sets the ID string for the flight object to the input string value.
     * @param flightID the value to which the flight's ID string will be changed.
     */
    public void setFlightID(String flightID)
    {
        this.flightID = flightID;
    }

    /**
     * getDepartTime gets the departure time value for the flight object.
     * @return the flight's time of departure.
     */
    public double getDepartTime()
    {
        return departTime;
    }

    /**
     * setDepartTime changes the flight's time of departure to the number passed.
     * @param departTime the double value to which the flight's departTime will be changed.
     */
    public void setDepartTime(double departTime)
    {
        this.departTime = departTime;
    }

    /**
     * getArriveTime gets the arrival time value for the flight object.
     * @return the flight's time of arrival.
     */
    public double getArriveTime()
    {
        return arriveTime;
    }

    /**
     * setArriveTime changes the flight's time of arrival to the number passed.
     * @param arriveTime the double value to which the flight's arriveTime will be changed.
     */
    public void setArriveTime(double arriveTime)
    {
        this.arriveTime = arriveTime;
    }

    /**
     * getDestinationPortCode gets the destination Airport ID code value for the flight object.
     * @return the flight's destination Airport ID code.
     */
    public String getDestinationPortCode()
    {
        return destinationPortCode;
    }

    /**
     * setDestinationPortCode sets the destination Airport ID code value for the flight object to the value of the
     * passed string.
     * @param destinationPortCode the string value to which the flight's destinationPortCode will be changed.
     */
    public void setDestinationPortCode(String destinationPortCode)
    {
        this.destinationPortCode = destinationPortCode;
    }

    /**
     * getStartingPortCode gets the starting Airport ID code value for the flight object.
     * @return the flight's starting Airport ID code.
     */
    public String getStartingPortCode()
    {
        return startingPortCode;
    }

    /**
     * setStartingPortCode sets the starting Airport ID code value for the flight object to the value of the passed
     * string.
     * @param startingPortCode the string value to which the flight's startingPortCode will be changed.
     */
    public void setStartingPortCode(String startingPortCode)
    {
        this.startingPortCode = startingPortCode;
    }

    /**
     * getAssociatedPlaneCode gets the associated Plane ID code value for the flight object.
     * @return the flight's associated Plane's ID code.
     */
    public String getAssociatedPlaneCode()
    {
        return associatedPlaneCode;
    }

    /**
     * setAssociatedPlaneCode sets the associated Plane ID code value for the flight object to the value of the passed
     * string.
     * @param associatedPlaneCode the string value to which the flight's associatedPlaneCode will be changed.
     */
    public void setAssociatedPlaneCode(String associatedPlaneCode)
    {
        this.associatedPlaneCode = associatedPlaneCode;
    }
}
