package com.Mobile_Framework.testcases;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.*;
import javax.mail.internet.*;

import org.apache.commons.io.filefilter.FileFilterUtils;
import org.testng.annotations.Test;

import java.io.File;
import java.io.FileInputStream;
import java.text.SimpleDateFormat;
import java.util.*;

 

public class SendMail extends com.Mobile_Framework.base.BaseTest

{
@Test
public void SendMail() throws Exception

    {
    	Properties pro=new Properties();
		try {
			FileInputStream fs = new FileInputStream(System.getProperty("user.dir")+"//src//test//java//com//Mobile_Framework//resources//pro.properties");
			pro.load(fs);
			

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//report folder - extent reports
		//date
		String reportFolder=pro.getProperty("REPORT_PATH");
    	 SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
         FileFilterUtils filter = new FileFilterUtils();
         File folder =  new File(reportFolder);
         File files[] = folder.listFiles();
     
        //date
         
         String fileName=files[files.length-1].getName();
         String extentFilePath=reportFolder+fileName;
   //      String xsltReportPath=reportFolder+"Reports.zip";
         
         // mail extent reports
                String[] to={"shanthi.jayaraman7@gmail.com"};

                String[] cc={};
                String[] bcc={};

                //This is for yahoo

            sendMail_first("shanthi_7583@yahoo.co.in",
                		            "7@oracle",
                		            "smtp.mail.yahoo.com",
                		            "587",
                		            "true",
                		            "true",
                		            true,
                		            "javax.net.ssl.SSLSocketFactory",
                		            "false",
                		            to,
                		            cc,
                		            bcc,
                		            "Automation Test Reports - Extent",
                		            "Please find the reports attached.\n\n Regards\nWebMaster",
                		            extentFilePath,
                		            fileName);
                
              /**8  // mail the xslt reports
                Zip.zipDir(System.getProperty("user.dir")+"//XSLT_Reports", xsltReportPath);
                SendMail.sendMail("vaibhavcool12312@yahoo.com",
    		            "Pass@123",
    		            "smtp.mail.yahoo.com",
    		            "25",
    		            "true",
    		            "true",
    		            true,
    		            "javax.net.ssl.SSLSocketFactory",
    		            "false",
    		            to,
    		            cc,
    		            bcc,
    		            "Automation Test Reports - XSLT",
    		            "Please find the reports attached.\n\n Regards\nWebMaster",
    		            xsltReportPath,
    		            "Reports.zip");**/

    }

 

  
 

}