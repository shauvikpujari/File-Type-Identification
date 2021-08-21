# File-Type-Identification
Display information about the file type from the extension of the file name
Build using Web Scraping(jsoup),Database(MySQL),ApacheTika in java Language
How to execute?
sol:
  1. Give input (file name) in input.txt
  2. Run the fileextensioninfo.java file present inside src/extensioninfo
  3. It will write the File Information in Output.txt
How it works?
sol:
   1. Project use Web Scrapping for fetching information about the file extension  from Website like (File-extensions,fileinfo,Filext)
   2. Fetched Information are dumped into database using sql queries (MySQL is Used)
   3. Filename are read from the input.txt and extension are extracted
   4. Extracted Extension name are searched in database table which was stored in (step 2) 
   5. Apachetika lib is used to get information about file name mime type
   6. All information Collected from step 4 and step 5 are written into output.txt file

Thank you!
