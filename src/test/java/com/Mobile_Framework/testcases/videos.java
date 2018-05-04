package com.Mobile_Framework.testcases;

import java.io.FileNotFoundException;
import java.net.MalformedURLException;
import java.util.Hashtable;
import org.testng.SkipException;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import com.Mobile_Framework.util.DataUtil;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class videos extends com.Mobile_Framework.base.BaseTest{
	  public ExtentReports extent=com.Mobile_Framework.util.ExtentManager.getInstance();
	  public ExtentTest test;
	  String testCaseName="TestC";
	  
@Test(dataProvider="getData")
public void TestC(Hashtable<String,String>data) throws FileNotFoundException, MalformedURLException, InterruptedException
	{
		test=extent.startTest("TestC","Starting TestC");
		if(!DataUtil.isTestRunnable(xls,"TestC") || data.get("Runmode").equals("N"))
		throw new SkipException("Skipping TestC");
		System.out.println("TESTC");
		test.log(LogStatus.INFO, "In TESTC");
		test.log(LogStatus.INFO,"Screeshotbelow"+test.addScreenCapture("C:\\report\\SS.png"));
		lauchApp();
		click("region_xpath");
		scrollandclick("videos_xpath");
		getvideoitems();
}
	@DataProvider
	public Object[][] getData(){
	return DataUtil.getData(xls,testCaseName);		
	}
	
	
@AfterMethod
public void afterMethod()
{
	extent.endTest(test);
	extent.flush();
}

}




