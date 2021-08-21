package webScrap;
import java.security.cert.Extension;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLIntegrityConstraintViolationException;

import javax.swing.table.TableStringConverter;
import java.util.*;
import java.util.Locale.Category;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class FileInfo 
{
	
	public static void main(String[] args) 
	{
		final String urlString="https://fileinfo.com/filetypes";  //url for fileinfo.com
		final String databaseString="fileinfo";
		try 
		{
			Document doc = Jsoup.connect(urlString).get();                //parsing website html
			Elements linksElements=doc.select("#left > article > div.buttongroup.alpha>a");
			
			for(Element link:linksElements)
			{
				String href_linkString=link.select("a").attr("href");
				Document doc1=Jsoup.connect(urlString+"/"+href_linkString.charAt(href_linkString.length()-1)).get();
				Elements aElements=doc1.select("#left > article > div.tablewrap > table > tbody > tr");
				for(Element b:aElements)
				{
				  
					String file_href=b.select("td.extcol > a").attr("href");
					Document doc3=Jsoup.connect("https://fileinfo.com"+file_href).get();
					String ExtenString=doc3.select("#left > article > h1").text().split(" ")[0];
					
					Elements sec=doc3.select("section");
				
					
					
					String developerString= sec.select("div.entryHeader > table > tbody > tr:nth-child(1) > td:nth-child(2)").text();   //Extracting data for Developer of 
					
					String categoryString=sec.select("div.entryHeader > table > tbody > tr:nth-child(3) > td:nth-child(2)").text();    //Extracting Data for Category of extension
					
					categoryString=categoryString.substring(0, Math.min(400,categoryString.length()));
					
					String formatString=sec.select("div.entryHeader > table > tbody > tr:nth-child(4) > td:nth-child(2)").text();     //Extracting data about File Format
					
					formatString=formatString.substring(0, Math.min(100,formatString.length()));
					
					String descriptionString=sec.select("div:nth-child(4) > p").text();                                   //Extracting data about Description of file extension
					
					descriptionString=descriptionString.substring(0,Math.min(1200,descriptionString.length()));
					
					String appString="";
					
					
				
					String Apprequire="";
					String[] apprequireArr=sec.select("> div.programsBox.infoBox > div:nth-child(1) > div > div.apps > div > div.appmid > div > a").text().split(" ");     //Extracting data about Application required for file
				    Apprequire=apprequireArr[0]+" "+(apprequireArr.length>1 ?apprequireArr[1]:"");
					if(Apprequire.length()==0)
				    	Apprequire="N/A";
				   
				 
				dataBaseInsertion dBaseInsertion=new dataBaseInsertion(ExtenString, categoryString, developerString, formatString, descriptionString, Apprequire, databaseString); 
				dBaseInsertion.insertData();                                   //Inserting Data into dbms
			}
				}
			
			
			
		} catch (Exception e) 
		{
            e.printStackTrace();
		}
		

	}
	


}
