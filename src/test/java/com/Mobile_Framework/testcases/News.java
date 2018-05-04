package com.Mobile_Framework.testcases;

import java.io.FileNotFoundException;
import java.net.MalformedURLException;
import java.util.Hashtable;
import java.util.List;
import org.testng.SkipException;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class News extends com.Mobile_Framework.base.BaseTest{
	  public ExtentReports extent=com.Mobile_Framework.util.ExtentManager.getInstance();
	  public ExtentTest test;
	  String testCaseName="TestA";
	  
@Test(dataProvider="getData")
public void TestA(Hashtable<String,String>data) throws FileNotFoundException, MalformedURLException, InterruptedException
	{
		test=extent.startTest("TestA","Starting TestA");
		if(!com.Mobile_Framework.util.DataUtil.isTestRunnable(xls,"TestA") || data.get("Runmode").equals("N"))
			throw new SkipException("Skipping TetsA");
		 System.out.println("TESTA");
		test.log(LogStatus.INFO, "In TESTA");
		test.log(LogStatus.INFO,"Screeshotbelow"+test.addScreenCapture("C:\\report\\SS.png"));
		lauchApp();
		click("region_xpath");
		List<String>actualmenu=getmenuitems();
		for(int i=0;i<actualmenu.size();i++ )
		{
		System.out.println(actualmenu.get(i));
		}
		 scrollandclick(data.get("News"));	
	     scrollandclicktest_News("News_id");	
}
@DataProvider
public Object[][] getData(){
	return com.Mobile_Framework.util.DataUtil.getData(xls,testCaseName);
		
	}	
	
@AfterMethod
public void afterMethod()
{
	extent.endTest(test);
	extent.flush();
}

}
