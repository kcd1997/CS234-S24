/**
 * The Passenger class defines the data, getters, and setters needed for a Passenger object, including:
 * -PassengerID (for use in the terminalData structure)
 * -Name
 * -Date of Birth
 * -Gender
 * -Phone Number
 * -Email Address
 * -Home Address
 * -Government ID Number
 * @author Kyle Dean, Mason Herbig, and Lwesso Mukeni
 */

public class Passenger
{
    /* Passenger variables include the Passenger's ID (for use in fetching and verifying the object within the
    TerminalData system), Name, Date of Birth, Gender, Phone Number, Email Address, Home Address, and Government
    ID number */
    private String passengerID;
    private String name;
    private String DOB;
    private String gender;
    private String phone;
    private String email;
    private String home;
    private String govID;

    /**
     * This is the constructor for the Passenger class, which sets the Passenger object's data
     * @param passengerID The Passenger's ID Code
     * @param name The Passenger's Name
     * @param DOB The Passenger's Date of Birth
     * @param gender The Passenger's Gender
     * @param phone The Passenger's Phone Number
     * @param email The Passenger's Email Address
     * @param home The Passenger's Home Address
     * @param govID The Passenger's Government ID Number or Code
     */
    public Passenger(String passengerID, String name, String DOB, String gender, String phone, String email, String home, String govID)
    {
        this.passengerID = passengerID;
        this.name = name;
        this.DOB = DOB;
        this.gender = gender;
        this.phone = phone;
        this.email = email;
        this.home = home;
        this.govID = govID;
    }

    /**
     * getPassengerID gets the value of the Passenger object's passengerID string
     * @return the passenger's ID code
     */
    public String getPassengerID()
    {
        return passengerID;
    }

    /**
     * setPassengerID sets the Passenger's ID string
     * @param passengerID the new value for the ID code
     */
    public void setPassengerID(String passengerID)
    {
        this.passengerID = passengerID;
    }

    /**
     * getName gets the value of the Passenger object's name string
     * @return the passenger's Name
     */
    public String getName()
    {
        return name;
    }

    /**
     * setName sets the Passenger's name string
     * @param name the new value for the name string
     */
    public void setName(String name)
    {
        this.name = name;
    }

    /**
     * getDOB gets the value of the Passenger object's DOB string
     * @return the passenger's DOB
     */
    public String getDOB()
    {
        return DOB;
    }

    /**
     * setDOB sets the Passenger's DOB string
     * @param DOB the new value for the DOB string
     */
    public void setDOB(String DOB)
    {
        this.DOB = DOB;
    }

    /**
     * getGender gets the value of the Passenger object's gender string
     * @return the passenger's gender
     */
    public String getGender()
    {
        return gender;
    }

    /**
     * setGender sets the Passenger's gender
     * @param gender the value for the new gender string
     */
    public void setGender(String gender)
    {
        this.gender = gender;
    }

    /**
     * getPhone gets the value of the Passenger object's phone string
     * @return the passenger's phone number string
     */
    public String getPhone()
    {
        return phone;
    }

    /**
     * setPhone sets the Passenger's phone number string
     * @param phone the value for the new phone number string
     */
    public void setPhone(String phone)
    {
        this.phone = phone;
    }

    /**
     * getEmail gets the value of the Passenger object's email string
     * @return the passenger's email address
     */
    public String getEmail()
    {
        return email;
    }

    /**
     * setEmail sets the Passenger's email address string
     * @param email the value for the new email address string
     */
    public void setEmail(String email)
    {
        this.email = email;
    }

    /**
     * getHome gets the value of the Passenger object's home address string
     * @return the passenger's home address
     */
    public String getHome()
    {
        return home;
    }

    /**
     * setHome sets the Passenger's home address string
     * @param home the value for the new home address string
     */
    public void setHome(String home)
    {
        this.home = home;
    }

    /**
     * getGovID gets the value of the Passenger object's govID string
     * @return the passenger's government ID code
     */
    public String getGovID()
    {
        return govID;
    }

    /**
     * setGovID sets the Passenger's govID string
     * @param govID the new value for the govID string
     */
    public void setGovID(String govID)
    {
        this.govID = govID;
    }
}
