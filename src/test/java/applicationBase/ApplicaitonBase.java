package applicationBase;


import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.testng.annotations.AfterClass;

import org.testng.annotations.BeforeSuite;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

import browserLaunch.BrowserHandler;
import helperMethods.GenericKeywords;
import helperMethods.PropertFileHandler;





public class ApplicaitonBase {
	
	
    Logger log = Logger.getLogger(ApplicaitonBase.class);
	PropertFileHandler property = new PropertFileHandler();
	public static Date date = new Date();
	public static SimpleDateFormat dateFormatter = new SimpleDateFormat("MMddyyyy_HHmmss");
	public static String startDate = dateFormatter.format(date);
	ExtentHtmlReporter reporter = null;
	public ExtentReports extent = null;
	public static ExtentTest logger = null;
	public static String reportLocation = System.getProperty("user.dir")+"/Test-report";
	public static String screenshotLocation = System.getProperty("user.dir")+"/Screenshot/";
	File testReportfilePath = new File(reportLocation+"/"+startDate+"");
	GenericKeywords keywords = new GenericKeywords();
	
	@BeforeSuite
	
	public void setUpApplication() {
		
		BrowserHandler.getInstanceOfBrowser();
		String appUrl = property.getPropertyValues().getProperty("url");
		String browser = property.getPropertyValues().getProperty("browser");
		BrowserHandler.getDriver().get(appUrl);
		BrowserHandler.getDriver().manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
		log.info(appUrl+" is launched on "+browser);
		if(!testReportfilePath.exists()) {
			testReportfilePath.mkdir();
		}
		reporter = new ExtentHtmlReporter(testReportfilePath+"/report.html");
		extent = new ExtentReports();
		extent.attachReporter(reporter);
		
		}
	
	
	
	@AfterClass
	public void closeApplication() {
		
		BrowserHandler.getDriver().quit();
		extent.flush();
	}

}
