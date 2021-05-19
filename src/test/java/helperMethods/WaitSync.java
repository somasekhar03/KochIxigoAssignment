package helperMethods;

import java.time.Duration;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.google.common.base.Function;

import browserLaunch.BrowserHandler;



public class WaitSync {
	
	Logger log = Logger.getLogger(WaitSync.class);
	PropertFileHandler prop = new PropertFileHandler();
	
	public WebElement pollingElement(final WebElement locator) 
	{
		int timeOut = Integer.parseInt(prop.getPropertyValues().get("timeout").toString());
		
		Wait<WebDriver> wait = new FluentWait<WebDriver>(BrowserHandler.getDriver()).withTimeout(Duration.ofSeconds(timeOut))
				.pollingEvery(Duration.ofMillis(500)).ignoring(NoSuchElementException.class);
		WebElement element = wait.until(new Function<WebDriver, WebElement>()
		{
			public WebElement apply(WebDriver driver) {
				return locator;
				}
		});
		return element;
	}
	
	
	public  void awaitForElementToBeClickable(WebElement element) {
		long maxTimeOut = Long.parseLong(prop.getPropertyValues().getProperty("timeout"));
		try {
			WebDriverWait wait = new WebDriverWait(BrowserHandler.getDriver(),maxTimeOut);
			wait.until(ExpectedConditions.elementToBeClickable(element));
		}catch(Exception e) {
			log.error(e);
			System.exit(1);
		}
		
	}
	
	public void awaitForElementToVisible(WebElement element) {
		long maxTimeOut = Long.parseLong(prop.getPropertyValues().getProperty("timeout"));
		try {
			WebDriverWait wait = new WebDriverWait(BrowserHandler.getDriver(),maxTimeOut);
			wait.until(ExpectedConditions.visibilityOf(element));
		}catch(Exception e) {
			log.error(e);
			System.exit(1);
		}
		
	}
	
	public void awaitForElementToBeRefreshed(String locator) {
		long maxTimeOut = Long.parseLong(prop.getPropertyValues().getProperty("timeout"));
		try {
			WebDriverWait wait = new WebDriverWait(BrowserHandler.getDriver(),maxTimeOut);
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(locator)));
		}catch(Exception e) {
			log.error(e);
			System.exit(1);
		}
		
	}
	
}
