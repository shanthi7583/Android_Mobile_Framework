package com.Mobile_Framework.base;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import com.Mobile_Framework.util.ExtentManager;
import com.Mobile_Framework.util.Xls_Reader;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import io.appium.java_client.MobileDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;

public class BaseTest {
	public Xls_Reader xls = new Xls_Reader("C:\\Users\\nikil kaarthi\\Downloads\\CoreFrameWork\\Data.xlsx");
	public Properties pr;
	public AndroidDriver adr;
	public WebDriver dr;
	public ExtentReports extent=ExtentManager.getInstance();
	  public ExtentTest test;
	
	public void lauchApp() throws FileNotFoundException, MalformedURLException, InterruptedException
	{
		if(pr==null)
		{
			pr=new Properties();
			
			try{
			 FileInputStream fs=new FileInputStream("C:\\Users\\nikil kaarthi\\Downloads\\Mobile_Framework\\src\\test\\java\\com\\Mobile_Framework\\resources\\pro.properties");
				pr.load(fs);
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		    File app = new File(pr.getProperty("apkfilepath"));
			DesiredCapabilities capabilities = new DesiredCapabilities();
			//DesiredCapabilities capabilities = DesiredCapabilities.android();
			capabilities.setCapability("deviceName", pr.getProperty("device"));
			capabilities.setCapability("platformVersion",pr.getProperty("platform"));
			capabilities.setCapability("platformName", pr.getProperty("platformN"));
			//capabilities.setCapability(CapabilityType.BROWSER_NAME,"Chrome");
			//capabilities.setCapability("autoWebview",true);
		    capabilities.setCapability("app", app.getAbsolutePath());
			//capabilities.setCapability("appPackage", "com.cnbc.client");
			//capabilities.setCapability("appActivity","com.cnbc.client.Home.HomeActivity");
			dr = new AndroidDriver<AndroidElement>(new URL("http://127.0.0.1:4723/wd/hub"),capabilities);	
			Thread.sleep(5000);
	     	}
		
	}

	public void click(String locator) throws InterruptedException
	{
		getAndroidElement(locator).click();
		Thread.sleep(7000);
	}
	
	public void Webclick(String locator) throws InterruptedException
	{
		getWebElement(locator).click();
		Thread.sleep(7000);
	}
	public void tap(String locator) throws InterruptedException
	{
		WebElement e3=( WebElement) dr.findElement(By.xpath(pr.getProperty(locator)));;
		   TouchAction tap1=new TouchAction((MobileDriver)dr);
		   tap1.tap(e3).perform();
    }
	
  public void touch(String locator)
  {
	  getAndroidElement(locator).tap(1,1000);
  }
 
  public void swipe(String Locator) throws InterruptedException
	{
	        System.out.println("IN SWIPE");
		    AndroidDriver<AndroidElement> aDriver =(AndroidDriver<AndroidElement>)dr;
	        AndroidElement  menulist=aDriver.findElement(By.id(pr.getProperty(Locator)));
	        Thread.sleep(5000);
			int startx=menulist.getLocation().x;
			int endy=menulist.getLocation().y;	
		   aDriver.swipe(startx,500, 500, endy,1000);
		   Thread.sleep(5000);
			   
	}
  
  public void scroll() throws InterruptedException
 	{
 	      System.out.println("IN SCROLL");
 		  AndroidDriver<AndroidElement> aDriver =(AndroidDriver<AndroidElement>)dr;
 		  aDriver.findElementByAndroidUIAutomator("Uiscrollable(UiSelector().resourceId(\"com.cnbc.client:id/news_item_layout\")");
 		  aDriver.getPageSource();
 		  Thread.sleep(5000);
 			   
 	}
   
	public void type(String locator,String data) throws InterruptedException
	{
		  getAndroidElement(locator).sendKeys(data);
		  Thread.sleep(5000);
	}
	
	public void quitApp()
	{
		if(dr!=null)
		{
			dr.quit();
		}
	}

	public AndroidElement getAndroidElement(String Locator)
	{
		AndroidElement e=null;
		AndroidDriver<AndroidElement> adriver =(AndroidDriver<AndroidElement>)dr;
		
		 try
		 {
		   System.out.println("LOCATOR IS"+Locator);	
		
		if(Locator.endsWith("_id"))
		{
			System.out.println("IN IF LOOP");
			Thread.sleep(2000);
			e=adriver.findElement(By.id((pr.getProperty(Locator))));
		}
		else if(Locator.endsWith("_xpath"))
		{
			System.out.println("IN IF LOOP");
			Thread.sleep(5000);
			e=adriver.findElement(By.xpath((pr.getProperty(Locator))));
			System.out.println(e);

		}
			else
			{
				System.out.println("element not found");
			}
	
		}catch(Exception ex)
		{

		}
		return e;
	
		
	}
	
	
	public WebElement getWebElement(String Locator)
	{
		WebElement e=null;
		WebElement adriver =(WebElement)dr;
		
		 try
		 {
		System.out.println("LOCATOR IS"+Locator);	
		
		if(Locator.endsWith("_id"))
		{
			System.out.println("IN IF LOOP");
			Thread.sleep(2000);
			e=adriver.findElement(By.id((pr.getProperty(Locator))));
		}
		else if(Locator.endsWith("_xpath"))
		{
			System.out.println("IN IF LOOP");
			Thread.sleep(5000);
			e=adriver.findElement(By.xpath((pr.getProperty(Locator))));
			System.out.println(e);

		}
	   else
		{
				System.out.println("element not found");
		}
	
		}catch(Exception ex)
		{

		}
		return e;
	
		
	}
	
	public List<String> getmenuitems()
	{
	List<String> items=new ArrayList<String>();
	List<WebElement> menulist=dr.findElements(By.id(pr.getProperty("drawer_id")));
	for(WebElement menu:menulist)
	{
		System.out.println(menu.getText());
		items.add(menu.getText());
	}
	while(true)
	{
	AndroidDriver<AndroidElement> aDriver =(AndroidDriver<AndroidElement>)dr;
	int starty=menulist.get(0).getLocation().y;
	int endy=menulist.get(menulist.size()-1).getLocation().y;	
    aDriver.swipe(300, starty, 300, endy,1000);;
	String Lastelement=menulist.get(menulist.size()-1).getText();
	return items;
	}
}	
	public List<String> getmenuitemstestB()
	{
	List<String> items=new ArrayList<String>();
	List<WebElement> menulist=dr.findElements(By.id(pr.getProperty("symbol_id")));
	for(WebElement menu:menulist)
	{
		//System.out.println(menu.getText());
		items.add(menu.getText());
	}
	while(true)
	{
	AndroidDriver<AndroidElement> aDriver =(AndroidDriver<AndroidElement>)dr;
	int startx=menulist.get(0).getLocation().getX();
	int starty=menulist.get(menulist.size()-1).getLocation().getY();	
   aDriver.swipe(startx, 300,300,starty,1000);
   
    menulist=dr.findElements(By.id("com.cnbc.client:id/txtSymbolID"));
	String Lastelement=menulist.get(menulist.size()-1).getText();
	System.out.println("LAST ELEMENT iS"+menulist.get(menulist.size()-1).getText());	
	}
	}
	
	public void scrollandclick(String Locator)
	{
		
	WebElement menulist=dr.findElement(By.xpath(pr.getProperty(Locator)));
	menulist.click();
	}

	public void scrollandclicktestB(String Locator) throws InterruptedException
	{
		List<String> items=new ArrayList<String>();
		List<WebElement> menulist=dr.findElements(By.id(pr.getProperty(Locator)));
		int waitcount=1;
		for(WebElement menu:menulist)
		{
			//System.out.println(menu.getText());
			items.add(menu.getText());
		}
		while(true)
		{
		String Lastelement=menulist.get(menulist.size()-1).getText();
	    AndroidDriver<AndroidElement> aDriver =(AndroidDriver<AndroidElement>)dr;
		int startx=menulist.get(0).getLocation().getX();
		int starty=menulist.get(menulist.size()-1).getLocation().getY();	
	    aDriver.swipe(startx, 300,300,starty,1000);
		Thread.sleep(3000);
	if(Lastelement.equals(pr.getProperty("MERVAL_xpath")))
			{
			if(waitcount==4)
				{
					break;
				
				}
				waitcount++;
				Thread.sleep(3000);
				menulist.get(menulist.size()-1).click();
				Thread.sleep(3000);
			}
			else
			{
			System.out.println(Lastelement);
			Thread.sleep(3000);
			click("MERVAL_xpath");
				
			}
	return;
		}
	}
		
	public void scrollandclicktest_News(String Locator) throws InterruptedException
	{
		List<String> items=new ArrayList<String>();
		List<WebElement> menulist=dr.findElements(By.id(pr.getProperty(Locator)));
		int waitcount=1;
		for(WebElement menu:menulist)
		{
			System.out.println(menu.getText());
			items.add(menu.getText());
		}
		while(true)
		{
		
		      String Lastelement=menulist.get(menulist.size()-1).getText();
	         AndroidDriver<AndroidElement> aDriver =(AndroidDriver<AndroidElement>)dr;
	  	     int startx=menulist.get(0).getLocation().getX();
	         int starty=menulist.get(menulist.size()-1).getLocation().getY();	
	         aDriver.swipe(startx, 500,500,starty,2000);
		     Thread.sleep(3000);
	         WebElement E=menulist.get(2);
	     	for(int i=0;i<=menulist.size();i++)
			{
	          if(menulist.get(i).getText().equals(E))
	         {	  
				menulist.get(i).click();
				return;
			}
			else
			{
				E.click();	
	     return;
         }
			}
		}
	}
		
	public void getvideoitems() throws InterruptedException
	{
	  int waitcount=1;
	  List<String> items=new ArrayList<String>();
	   List<WebElement> menulist=dr.findElements(By.id(pr.getProperty("play_id")));
	for(WebElement menu:menulist)
	{
		System.out.println(menu.getText());
		items.add(menu.getText());
	}
	while(true)
	{
		    String Lastelement=menulist.get(menulist.size()-1).getText();
	        AndroidDriver<AndroidElement> aDriver =(AndroidDriver<AndroidElement>)dr;
	        Dimension size=dr.manage().window().getSize();
	        int x_start=(int)(size.width*0.60);
	        int x_end=(int)(size.width*0.30);
	        int y=1000;
	        aDriver.swipe(x_start,y,x_end,y,4000);
	        /* int startx=menulist.get(0).getLocation().getX();
	         int endx=menulist.get(menulist.size()-1).getLocation().getX();	
            aDriver.swipe(startx,100,100,endx,5000);*/
           //WebElement video=dr.findElement(By.xpath(pr.getProperty("video_xpath")));
            System.out.println("video");
           // menulist=dr.findElements(By.id(pr.getProperty("play_id")));
		    Thread.sleep(3000);
	        String text=menulist.get(1).getText();
	        //System.out.println("text");
	   if(Lastelement.equals(text))
	     {
	   if(waitcount==4)
	        {
				break;
	     	}
			waitcount++;
			Thread.sleep(3000);
			menulist.get(1).click();
		}
		else
		{
		  System.out.println(Lastelement);
		 // System.out.println("ELEMENT NOT FOUND");
		  menulist.get(menulist.size()-1).click();		
		}
     return;
	}
}
public void takeScreenshot(){
		Date d = new Date();	
		String screenshotFile=d.toString().replace(":", "_").replace(" ","_")+".png";
		File scrFile = ((TakesScreenshot)dr).getScreenshotAs(OutputType.FILE);
		try {
			FileUtils.copyFile(scrFile, new File("C://Reports//screenshots//"+screenshotFile));
		}catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		test.log(LogStatus.INFO,"Snapshot below: ("+screenshotFile+")"+
		test.addScreenCapture(System.getProperty("C://Reports//screenshots//"+screenshotFile)));
	}
	
public  static boolean SendMail(
    		final String userName,
    		final String passWord,
    		String host,
    		String port,
    		String starttls,
    		String auth,
    		boolean debug,
    		String socketFactoryClass,
    		String fallback,
    		String[] to,
    		String[] cc,
    		String[] bcc,
    		String subject,
    		String text,
    		String attachmentPath,
    		String attachmentName){
    	Properties props = new Properties();
        props.put("mail.smtp.starttls.enable", starttls);
        props.put("mail.smtp.auth",auth);
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.port", port);

    try
    {
    	Session session = Session.getInstance(props,
    	new javax.mail.Authenticator() {
    	protected PasswordAuthentication getPasswordAuthentication() {
    	return new PasswordAuthentication(userName, passWord);
    	            }
    	          });

        MimeMessage msg = new MimeMessage(session);
        msg.setText(text);
        msg.setSubject(subject);
        //attachment start
        // create the message part 
        Multipart multipart = new MimeMultipart();
        MimeBodyPart messageBodyPart = new MimeBodyPart();
        DataSource source = new FileDataSource(attachmentPath);
        messageBodyPart.setDataHandler(new DataHandler(source));
        messageBodyPart.setFileName(attachmentName);
        multipart.addBodyPart(messageBodyPart);
        // attachment ends
        // Put parts in message
        msg.setContent(multipart);
        msg.setFrom(new InternetAddress(userName));
        for(int i=0;i<to.length;i++){
        msg.addRecipient(Message.RecipientType.TO, new InternetAddress(to[i]));
         }
        for(int i=0;i<cc.length;i++){
        msg.addRecipient(Message.RecipientType.CC, new InternetAddress(cc[i]));
         }
        for(int i=0;i<bcc.length;i++){
        msg.addRecipient(Message.RecipientType.BCC, new InternetAddress(bcc[i]));
         }
        msg.saveChanges();
        Transport transport = session.getTransport("smtp");
        transport.connect(host,userName,passWord);
        transport.sendMessage(msg,msg.getAllRecipients());
        transport.close();
        return true;
    }
    catch (Exception mex)
    {
        mex.printStackTrace();
        return false;

    }

    }
	public  static boolean sendMail_first(
    		final String userName,
    		final String passWord,
    		String host,
    		String port,
    		String starttls,
    		String auth,
    		boolean debug,
    		String socketFactoryClass,
    		String fallback,
    		String[] to,
    		String[] cc,
    		String[] bcc,
    		String subject,
    		String text,
    		String attachmentPath,
    		String attachmentName){
    	    Properties props = new Properties();
            props.put("mail.smtp.starttls.enable", starttls);
            props.put("mail.smtp.auth",auth);
            props.put("mail.smtp.host", host);
            props.put("mail.smtp.port", port);

    try
    {
    	    Session session = Session.getInstance(props,
    	    new javax.mail.Authenticator() {
    	    protected PasswordAuthentication getPasswordAuthentication() {
    	    return new PasswordAuthentication(userName, passWord);
    	     }
   });

        MimeMessage msg = new MimeMessage(session);
        msg.setText(text);
        msg.setSubject(subject);
        //attachment start
        // create the message part 
        Multipart multipart = new MimeMultipart();
        MimeBodyPart messageBodyPart = new MimeBodyPart();
        DataSource source = 
        new FileDataSource(attachmentPath);
        messageBodyPart.setDataHandler(
        new DataHandler(source));
        messageBodyPart.setFileName(attachmentName);
        multipart.addBodyPart(messageBodyPart); 
        // attachment ends
        // Put parts in message
        msg.setContent(multipart);
        msg.setFrom(new InternetAddress(userName));
       for(int i=0;i<to.length;i++){
    	   msg.addRecipient(Message.RecipientType.TO, new InternetAddress(to[i]));
                    }
       for(int i=0;i<cc.length;i++){
        msg.addRecipient(Message.RecipientType.CC, new InternetAddress(cc[i]));
                    }
       for(int i=0;i<bcc.length;i++){
        msg.addRecipient(Message.RecipientType.BCC, new InternetAddress(bcc[i]));
                    }
        msg.saveChanges();
       Transport transport = session.getTransport("smtp");
       transport.connect(host,userName,passWord);
       transport.sendMessage(msg,msg.getAllRecipients());
       transport.close();
       return true;
    }
    catch (Exception mex)
    {
        mex.printStackTrace();
        return false;

    }

    }


}


	
