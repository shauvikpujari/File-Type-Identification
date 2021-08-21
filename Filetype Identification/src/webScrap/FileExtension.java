package webScrap;

import java.util.logging.Level;

import javax.sound.sampled.TargetDataLine;

import org.jsoup.*;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class FileExtension {

	

	public static void main(String[] args) 
	{
		final String databaseString="file_extension";  //Name of the database table in which data will Store 
		final String extString="https://www.file-extensions.org/extensions/common-file-extension-list";  //Link of URL to acces file Extension Website
		                                                                                                 
		try 
		{
			
			Document doc = Jsoup.connect(extString).get(); //Connecting to the URL Link to fetch the website HTML File and parse it  
			Elements tb = doc.select("tbody");

			
			for(Element a:tb.select("tr"))
			{
				String linkString=a.select("a").attr("href"); //Fetching the link of Anchor tag "href" attribute

				
				String extString1='.'+a.text().split(" ")[0];
				
				Document doc2=Jsoup.connect("https://www.file-extensions.org"+linkString).get(); //Parsing the nested html page to get info about Single Extension  
				
				Elements divElement=doc2.select("#extdesc > div.box6 > p:nth-child(2) > a");
				
				Elements divdescElement=doc2.select("#extdesc > div.boxextdesc > p:nth-child(1)");
				
				String fileformatString=doc2.select("#extdesc > div.boxextdesc > p:nth-child(1) > strong:nth-child(2)").text(); //Fetching File Format information
				
				String developerString=doc2.select("#jqextnativepopisappsection > div.box7 > div > p:nth-child(4) > span").text(); //Fetching Developer Information
				
				String apprequiredString=doc2.select("#jqextnativepopisappsection > div.box7 > div > p:nth-child(3) > a > span").text();// Fetching Associated App information
				
				String categoryString="";
				
				String categoryStr[]=divElement.text().split(" "); //Fetching Extension Category information
				
				for(int i=0;i<categoryStr.length-1;i++)
					categoryString+=categoryStr[i]+" ";
				String descriptionString=divdescElement.text();
				
				dataBaseInsertion dBaseInsertion=new dataBaseInsertion(extString1, categoryString, developerString, fileformatString, descriptionString,apprequiredString,databaseString);
				dBaseInsertion.insertData();  //Inserting Data into database
				
				
				
			
			}
			
		} 
		catch (Exception e) {
			
			e.printStackTrace();
		}
		
		
	}

}
