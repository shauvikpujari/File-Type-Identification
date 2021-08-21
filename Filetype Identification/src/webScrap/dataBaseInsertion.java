package webScrap;

import java.io.File;
import java.io.FileOutputStream;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.HashSet;
import java.util.Scanner;

import com.mysql.cj.xdevapi.Statement;
import com.sun.jdi.connect.spi.Connection;

public class dataBaseInsertion
{
 private String extenString;
 private String categoryString;
 private String developerString;
 private String fomatString;
 private String descrString;
 private String applicationString;
 private String databaseString;
 private static HashSet<String> hashSet;
 
 private final static String userNameString;
 private final static String passwordDbmsString;
 static Scanner scanner;
 static
 {                                                                //Initialising MYSQL User Name and Password
	 scanner=new Scanner(System.in);
	 System.out.println("Enter Your Database User Name:\n");
     userNameString=scanner.nextLine();
     System.out.println("Enter Your Database Password:\n");
     passwordDbmsString=scanner.nextLine();
     hashSet=new HashSet<String>();
 }
 
public dataBaseInsertion(String extenString, String categoryString, String developerString, String fomatString,
		String descrString, String applicationString,String databaseString) 
{
	this.extenString = extenString;
	this.categoryString = categoryString;
	this.developerString = developerString;                
	this.fomatString = fomatString;
	this.descrString = descrString;
	this.applicationString = applicationString;
	this.databaseString=databaseString;
	
}
  

 public void insertData()  throws SQLException
 {
	 try 
		{
		  if(!hashSet.contains(extenString))
		  {
			Class.forName("com.mysql.cj.jdbc.Driver"); //Dynamically Loadig the driver class in memory
			java.sql.Connection connection=DriverManager.getConnection("jdbc:mysql://localhost:3306/fileinfo",userNameString,passwordDbmsString); //Connecting to mysql server
			String sqlString="INSERT INTO "+databaseString+" VALUES (?,?,?,?,?,?)";  //Query to insert data into table
			PreparedStatement statement=connection.prepareStatement(sqlString);  
			statement.setString(1,extenString);
			statement.setString(2,developerString);        //Setting the value in database table
			statement.setString(3,categoryString);
			statement.setString(4,fomatString);                  
			statement.setString(5,applicationString);
			statement.setString(6,descrString);
			statement.executeUpdate();                 //Executing Query & Updating database table 
			hashSet.add(extenString);
		  }
		}
	    catch (SQLIntegrityConstraintViolationException e) 
	    {
			e.printStackTrace();
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
	   
 }
  
  

}
