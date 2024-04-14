import java.util.ArrayList;
import java.util.Arrays;

/**
 * The Reader class is used to read strings and convert between delimited strings and lists of string values.
 * @author Kyle Dean, Mason Herbig, and Lwesso Mukeni
 */

public class Reader
{
    /* Initialize:
        readIn- the String being read by the Reader class;
        delimiter- the delimiter by which readIn may be separated, such as when creating an array */
    String readIn = "";
    String delimiter = "";

    /**
     * This is the constructor for the Reader class.
     * @param readIn the string being read
     * @param delimiter the delimiter by which parts of the string may be separated
     */
    Reader(String readIn, String delimiter)
    {
        this.readIn = readIn;
        this.delimiter = delimiter;

    }

    /**
     * readOutList returns an ArrayList of strings taken from the readIn parameter, split by the delimiter.
     * @param readIn The string from which values are taken and placed into the returned ArrayList.
     * @param delimiter the value used as a delimiter to split the readIn string.
     * @return the ArrayList created from the splitting process.
     */
    public ArrayList<String> readOutList(String readIn, String delimiter)
    {
        return new ArrayList<String>(Arrays.asList(readIn.split(delimiter)));
    }

    /**
     * readOutList without parameters uses the object's internal readIn and delimiter values to generate the ArrayList.
     * @return The ArrayList generated by calling readOutList(readIn, delimiter), using this Reader object's values.
     */
    public ArrayList<String> readOutList()
    {
        return readOutList(readIn, delimiter);
    }

    /**
     * writeIn creates and returns a string which is formed from the concatenated of each element in a provided
     * ArrayList and a delimiter value placed between each element.
     * @param writeInList The ArrayList of values to be concatenated into a string.
     * @param delimiter The delimiter that will separate each value in the string creation.
     * @return The string created by concatenating each value in the writeInList plus the delimiter.
     */
    public String writeIn(ArrayList<String> writeInList, String delimiter)
    {
        String writeIn = "";
        for (String i : writeInList)
        {
            writeIn = (writeIn + i + delimiter);
        }
        return writeIn;
    }

    /**
     * getReadIn retrieves the readIn string contained in the Reader object.
     * @return The readIn value stored in this object.
     */
    public String getReadIn()
    {
        return readIn;
    }

    /**
     * setReadIn sets the readIn string value in this Reader object to the passed String value.
     * @param readIn The String value to which this object's readIn String will be changed.
     */
    public void setReadIn(String readIn)
    {
        this.readIn = readIn;
    }

    /**
     * getDelimiter retrieves the delimiter string contained in the Reader object.
     * @return The delimiter value stored in this object.
     */
    public String getDelimiter()
    {
        return delimiter;
    }

    /**
     * setDelimiter sets the delimiter string contained in the Reader object to the passed String value.
     * @param delimiter The String value to which the delimiter value stored in this object will be changed.
     */
    public void setDelimiter(String delimiter)
    {
        this.delimiter = delimiter;
    }
}