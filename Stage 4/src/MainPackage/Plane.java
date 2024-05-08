package MainPackage;

/**
 * The Plane class defines the data, getters, and setters needed for a Plane object, including:
 * -Plane ID (for use in the terminalData structure)
 * -The Plane's Model
 * -The Plane's Passenger Limit
 * -The Plane's Current Assignment's ID Code
 * -The Plane's Current Assignment's Type (Airport or Flight)
 * @author Kyle Dean, Mason Herbig, and Lwesso Mukeni
 */

public class Plane
{
    /* Plane variables include the Plane's ID (for use in fetching and verifying the object within the TerminalData
    system), the Model, the Passenger Limit, the Current Assignment Code, and the Current Assignment Data Type */
    String planeID;
    String model;
    int passLimit;
    String currentAssignmentCode;
    String currentAssignmentType;

    /**
     * This is the constructor for the Plane class, which sets the Plane object's data
     * @param planeID The Plane's ID code
     * @param model The Plane's Model
     * @param passLimit The Plane's Passenger Limit
     * @param currentAssignmentCode The Plane's Current Assignment's ID Code
     * @param currentAssignmentType The Plane's Current Assignment's Type ("Airport" or "Flight")
     */
    public Plane(String planeID, String model, int passLimit, String currentAssignmentCode, String currentAssignmentType)
    {
        this.planeID = planeID;
        this.model = model;
        this.passLimit = passLimit;
        this.currentAssignmentCode = currentAssignmentCode;
        this.currentAssignmentType = currentAssignmentType;
    }

    /**
     * getPlaneID gets the ID string for the plane object.
     * @return the plane's ID string.
     */
    public String getPlaneID()
    {
        return planeID;
    }

    /**
     * setPlaneID sets the ID string for the plane object to the input string value.
     * @param planeID the value to which the plane's ID string will be changed.
     */
    public void setPlaneID(String planeID)
    {
        this.planeID = planeID;
    }

    /**
     * getModel gets the model string for the plane object.
     * @return the plane's model string.
     */
    public String getModel()
    {
        return model;
    }

    /**
     * setModel sets the model string for the plane object to the input string value.
     * @param model the value to which the plane's model string will be changed.
     */
    public void setModel(String model)
    {
        this.model = model;
    }

    /**
     * getPassLimit gets the Passenger Limit (passLimit) integer for the plane object.
     * @return the plane's passLimit.
     */
    public int getPassLimit()
    {
        return passLimit;
    }

    /**
     * setPassLimit sets the Passenger Limit (passLimit) integer for the plane object to the input value.
     * @param passLimit the value to which the plane's passLimit will be changed.
     */
    public void setPassLimit(int passLimit)
    {
        this.passLimit = passLimit;
    }

    /**
     * getCurrentAssignmentCode gets the ID Code string for the currently assigned object for this plane object.
     * @return the plane's currentAssignmentCode string.
     */
    public String getCurrentAssignmentCode()
    {
        return currentAssignmentCode;
    }

    /**
     * setCurrentAssignmentCode sets the ID Code string for the currently assigned object for this plane object to the
     * input string value.
     * @param currentAssignmentCode the value to which the plane's currentAssignmentCode string will be changed.
     */
    public void setCurrentAssignmentCode(String currentAssignmentCode)
    {
        this.currentAssignmentCode = currentAssignmentCode;
    }

    /**
     * getCurrentAssignmentType gets the data type string ("Airport" or "Flight") for the currently assigned object
     * for this plane object.
     * @return the plane's currentAssignmentType string.
     */
    public String getCurrentAssignmentType()
    {
        return currentAssignmentType;
    }

    /**
     * setCurrentAssignmentType sets the data type string ("Airport" or "Flight") for the currently assigned object
     * for this plane object to the input string value.
     * @param currentAssignmentType the value to which the plane's currentAssignmentType string will be changed.
     */
    public void setCurrentAssignmentType(String currentAssignmentType)
    {
        this.currentAssignmentType = currentAssignmentType;
    }
}
