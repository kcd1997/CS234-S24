/**
 * The Ticket class defines the data, getters, and setters needed for a Ticket object, including:
 * -Ticket ID (for use in the terminalData structure)
 * -The ticket's paid status boolean
 * -The ticket's associated Flight's ID code
 * -The ticket's associated Passenger's ID code
 * -The ticket's seating compartment number (byte)
 * -The ticket's number of checked bags (byte)
 * @author Kyle Dean, Mason Herbig, and Lwesso Mukeni
 */

public class Ticket
{
    /* Ticket variables include the Ticket's ID (for use in fetching and verifying the object within the TerminalData
    system), the payment status, the flightCode associated, the passengerCode associated, the seatCompartment number,
    and the checkBags number */
    private String ticketID;
    private boolean paidStatus;
    private String flightCode;
    private String passengerCode;
    private byte seatCompartment;
    private byte checkBags;

    /**
     * This is the constructor for the Ticket class, which sets the Ticket object's data
     * @param ticketID The Ticket's ID code
     * @param paidStatus The Ticket's payment status boolean
     * @param flightCode The Ticket's associated Flight's ID code
     * @param passengerCode The Ticket's associated Passenger's ID code
     * @param seatCompartment The Ticket's seating compartment number
     * @param checkBags The Ticket's allotment of checked bags
     */
    public Ticket(String ticketID, boolean paidStatus, String flightCode, String passengerCode, byte seatCompartment, byte checkBags)
    {
        this.ticketID = ticketID;
        this.paidStatus = paidStatus;
        this.flightCode = flightCode;
        this.passengerCode = passengerCode;
        this.seatCompartment = seatCompartment;
        this.checkBags = checkBags;
    }

    /**
     * getTicketID gets the ID string for the ticket object.
     * @return the ticket's ID string.
     */
    public String getTicketID()
    {
        return ticketID;
    }

    /**
     * setTicketID sets the ID string for the ticket object to the input string value.
     * @param ticketID the value to which the ticket's ID string will be changed.
     */
    public void setTicketID(String ticketID)
    {
        this.ticketID = ticketID;
    }

    /**
     * isPaidStatus returns the paid status boolean for the current ticket object.
     * @return the paidStatus boolean value for the current ticket.
     */
    public boolean isPaidStatus()
    {
        return paidStatus;
    }

    /**
     * setPaidStatus sets the paid status boolean for the current ticket object.
     * @param paidStatus the boolean value to which the ticket's paidStatus value will be changed.
     */
    public void setPaidStatus(boolean paidStatus)
    {
        this.paidStatus = paidStatus;
    }

    /**
     * getFlightCode gets the current ticket object's associated flight's ID code string.
     * @return the flight ID code string associated with this ticket.
     */
    public String getFlightCode()
    {
        return flightCode;
    }

    /**
     * setFlightCode sets the current ticket object's associated flight ID code string to the passed value.
     * @param flightCode the value to which this ticket's flightCode will be changed.
     */
    public void setFlightCode(String flightCode)
    {
        this.flightCode = flightCode;
    }

    /**
     * getFlightCode gets the current ticket object's associated flight's ID code string.
     * @return the flight ID code string associated with this ticket.
     */
    public String getPassengerCode()
    {
        return passengerCode;
    }

    /**
     * setPassengerCode sets the current ticket object's associated passenger ID code string to the passed value.
     * @param passengerCode the value to which this ticket's passengerCode will be changed.
     */
    public void setPassengerCode(String passengerCode)
    {
        this.passengerCode = passengerCode;
    }

    /**
     * getSeatCompartment gets the current ticket object's seating compartment number.
     * @return this ticket's seatCompartment value.
     */
    public byte getSeatCompartment()
    {
        return seatCompartment;
    }

    /**
     * setSeatCompartment sets the current ticket object's seating compartment number to the passed value.
     * @param seatCompartment the value to which this ticket's seatCompartment value will be changed.
     */
    public void setSeatCompartment(byte seatCompartment)
    {
        this.seatCompartment = seatCompartment;
    }

    /**
     * getCheckBags gets the current ticket object's checkBags number.
     * @return the current ticket object's checkBags value.
     */
    public byte getCheckBags()
    {
        return checkBags;
    }

    /**
     * setCheckBags sets the current ticket object's checkBags number to the passed value.
     * @param checkBags the value to which this ticket's checkBags value will be changed.
     */
    public void setCheckBags(byte checkBags)
    {
        this.checkBags = checkBags;
    }
}
