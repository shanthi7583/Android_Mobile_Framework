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

public class market extends com.Mobile_Framework.base.BaseTest{
	  public ExtentReports extent=com.Mobile_Framework.util.ExtentManager.getInstance();
	  public ExtentTest test;
	  String testCaseName="TestB";
	  
@Test(dataProvider="getData")
public void TestB(Hashtable<String,String>data) throws FileNotFoundException, MalformedURLException, InterruptedException
	{
		test=extent.startTest("TestB","Starting TestB");
		if(!com.Mobile_Framework.util.DataUtil.isTestRunnable(xls,"TestB") || data.get("Runmode").equals("N"))
		throw new SkipException("Skipping TetsA");
		System.out.println("TESTB");
		test.log(LogStatus.INFO, "In TESTA");
		test.log(LogStatus.INFO,"Screeshotbelow"+test.addScreenCapture("C:\\report\\SS.png"));
		lauchApp();
		click("region_xpath");
		scrollandclick(data.get("Market"));
		click(data.get("Index"));
		scrollandclicktestB("symbol_id");
		Thread.sleep(5000);
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



