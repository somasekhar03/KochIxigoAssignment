package listeners;

import java.io.IOException;

import org.apache.log4j.Logger;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestNGListener;
import org.testng.ITestResult;

import applicationBase.ApplicaitonBase;
import helperMethods.GenericKeywords;



public class TestNGListner implements ITestListener  {
	 Logger log = Logger.getLogger(TestNGListner.class);
	@Override
	public void onTestStart(ITestResult result) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onTestFailure(ITestResult result) {
		try {
			String failedScreenshotPath = GenericKeywords.takeScreenshot("FailedStep").toString();
			log.info("Failed step screenshot placed under-->"+failedScreenshotPath);
			ApplicaitonBase.logger.addScreenCaptureFromPath(failedScreenshotPath);
		}catch(IOException e) {
			log.error(e);
		}
		
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		try {
			String failedScreenshotPath = GenericKeywords.takeScreenshot("SkippedStep").toString();
			log.info("Failed step screenshot placed under-->"+failedScreenshotPath);
			ApplicaitonBase.logger.addScreenCaptureFromPath(failedScreenshotPath);
		}catch(IOException e) {
			log.error(e);
		}
		
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onStart(ITestContext context) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onFinish(ITestContext context) {
		// TODO Auto-generated method stub
		
	}

	

}
