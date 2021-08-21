package extensionInfo;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.*;

import org.apache.tika.Tika;
import  java.lang.*;
import java.lang.instrument.IllegalClassFormatException;

class getExtension                                                               //Finding Extension String from a File name
{
	public static String findExtension(String filename) throws IllegalClassFormatException 
	{
		int index=filename.indexOf(".");
		if(index==-1)
			throw new IllegalClassFormatException("Illegal File name :"+filename);
		return filename.substring(index);
	}
}

public class FileExtensionInfo 
{

	public static void main(String[] args) 
	{
	try {
		
		FileReader fileread=new FileReader("./input/input.txt");      
		BufferedReader bReader =new BufferedReader(fileread);          //Reading Input from input.txt;
		
		String inputString;
		FileWriter fileWriter=new FileWriter("./output/output.txt");   //Writing Output into output.txt
		while((inputString=bReader.readLine()) != null)
		{
			String exteString=getExtension.findExtension(inputString);  
			Tika tika=new Tika();                       
			String mimetypeString=tika.detect(inputString);           //Using Tika Library to detect MIME type 
			getInfoFromDatabase getInfodb= new getInfoFromDatabase();
			String[] result=getInfodb.getInfo(exteString);            //Used to get info. about a extension String
			fileWriter.write("File Name      : "+inputString);        //
			fileWriter.write("\n");
			fileWriter.write("***********************************************************************");
			fileWriter.write("\n");
			fileWriter.write("File Extension : "+exteString);
			fileWriter.write("\n");
			fileWriter.write("Developer      : "+(result[1].trim().length()==0 ? "N/A":result[1]));
			fileWriter.write("\n");
			fileWriter.write("Category       : "+(result[2].trim().length()==0 ? "N/A":result[2]));
			fileWriter.write("\n");
			fileWriter.write("File Format    : "+(result[3].trim().length()==0 ? "N/A":result[3]));
			fileWriter.write("\n");
			fileWriter.write("Application    : "+(result[4].trim().length()==0 ? "N/A":result[4]));
			fileWriter.write("\n");
			fileWriter.write("Description    : "+(result[5].trim().length()==0 ? "N/A":result[5]));
			fileWriter.write("\n");
			fileWriter.write("MIME Type      : "+mimetypeString);
			fileWriter.write("\n");
			fileWriter.write("***********************************************************************");
			fileWriter.write("\n");
		}
		bReader.close();
		fileWriter.close();
		System.out.println("Thank You U Can Check the result in  output.txt");
	} catch (Exception e1) 
	{
		e1.printStackTrace();
	}
	

	}

}
