package extensionInfo;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

public class getInfoFromDatabase 
{
   private final static String userNameString;
   private final static String passwordDbmsString;
   static Scanner scanner;
   static
   {
	   scanner=new Scanner(System.in);                            //Initialising Database User name and password
	   System.out.println("Enter Your Database User Name:\n");
       userNameString=scanner.nextLine();
       System.out.println("Enter Your Database Password:\n");
       passwordDbmsString=scanner.nextLine();  
   }
   public String[] getInfo(String extString)                  //function to get information about a extension from database
   {                                                                                       
	   String[] resultset1=new String[6];
	   for(int i=0;i<6;i++)
		   resultset1[i]="N/A";
	   try {
			Class.forName("com.mysql.cj.jdbc.Driver");                 //Loading JDBC driver into memory
			java.sql.Connection connection=DriverManager.getConnection("jdbc:mysql://localhost:3306/fileinfo",userNameString,passwordDbmsString); //Connection to mysql server
			PreparedStatement ps=connection.prepareStatement("SELECT * FROM file_extension where Extension= ?");  //Query to find information from file_extension table
			ps.setString(1,extString);
			ResultSet rs=ps.executeQuery();
			
			PreparedStatement ps1=connection.prepareStatement("SELECT * FROM fileinfo where Extension=?");  //Query to find information from fileinfo table
			ps1.setString(1,extString);
			ResultSet rs1=ps1.executeQuery();
		    
		    int i=1;
		    boolean flag1=rs1.next();
		    while(flag1 && i<=6)
		    {
		    	String resulString=rs1.getString(i);
		    	String arrString[];
		    	if(i==2)
		    	{
		    		arrString=resulString.split(" ");
		    		resulString="";
		    		int count=0;
		    		while(count<arrString.length && count<1)
		    		{
		    			if(!arrString[count].equals("N/A"))
		    			     resulString+=arrString[count]+" ";
		    			count++;
		    		}
		    	}
		    	else if(i==3)
		    	{
		    		arrString=resulString.split(" ");
		    		resulString="";
		    		int count=0;
		    		while(count<arrString.length && count<2 )
		    		{
		    			if(!arrString[count].equals("N/A"))
		    			     resulString+=arrString[count]+" ";
		    			count++;
		    		}
		    	} else if(i==4)
		    	{
		    		arrString=resulString.split(" ");
		    		resulString="";
		    		int count=0;
		    		while(count<arrString.length && count<3 )
		    		{
		    			if(!arrString[count].equals("N/A"))
		    			     resulString+=arrString[count]+" ";
		    			count++;
		    		}
				}
		    	
		    	resultset1[i-1]=resulString;
		    	i++;
		    }
		    i=1;
		    boolean flag2=rs.next();
		    while(flag2 && i<=6)
		    {
		    	if((resultset1[i-1].equals("") || resultset1[i-1].equals("N/A")) && rs.getString(i).length()>0)
		    	    resultset1[i-1]=rs.getString(i);
		    	i++;
		    }
		  
		} catch (Exception e) 
		{
			e.printStackTrace();
		}
	   return resultset1;    //Returing a Array of String containing information about extension to calling function
   }
}
