/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package MainPackage;

import java.io.File;
import java.io.FileWriter;
import java.util.Scanner;

/**
 *
 * @author kylec
 */

public class GUIFileDataFunctions {
    
    public String save(String fileName, TerminalData terminalData)
    {
        String fileData = terminalData.toFileData();

        File newFile = new File(fileName);
        
        try
        {
            if(newFile.createNewFile())
            {
                FileWriter fileWriter = new FileWriter(fileName);
                fileWriter.write(fileData);
                fileWriter.close();

                return "File written successfully";
            }

            else
            {
                return "Error: file name already exists";
            }
        }

        catch(Exception e)
        {
            return "Error: invalid entry";
        }
    }
    
    public String load(String filePath, TerminalData terminalData)
    {
        String fileData = null;

        try
        {
            File newFile = new File(filePath);
            
            Scanner fileReader = new Scanner(newFile);

            fileData = fileReader.nextLine();
            fileReader.close();

            terminalData.toTerminalData(fileData);

            return "File read successfully";
        }

        catch(Exception e)
        {
            return "Error: invalid entry";
        }
    }
}
