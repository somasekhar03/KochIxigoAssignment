package helperMethods;

import java.io.File;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotInteractableException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.google.common.io.Files;

import applicationBase.ApplicaitonBase;
import browserLaunch.BrowserHandler;
import pageObjects.SearchFlightPage;


public class GenericKeywords extends WaitSync{

	Logger log = Logger.getLogger(GenericKeywords.class);
	
	public void performClick(WebElement elementToClick) {
		try {
			awaitForElementToBeClickable(elementToClick);
			elementToClick.click();
			log.info("Perform click on: "+elementToClick);
			
		}catch(ElementNotInteractableException e) {
			e.printStackTrace();
		}
		catch(NoSuchElementException e) {
			e.printStackTrace();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void sleep(int secs) throws InterruptedException {
		Thread.sleep((secs * 1000));
	}
	
	public static boolean waitforElement_to_visible(WebElement element, int timeOut) {
		boolean isElementVisible;
		try {
			WebDriverWait wait = new WebDriverWait(BrowserHandler.getDriver(), timeOut);
			wait.until(ExpectedConditions.visibilityOf(element));
			wait.pollingEvery(Duration.ofMillis(200));
			isElementVisible = true;
		} catch (Exception e) {
			isElementVisible = false;
			System.out.println("element " + element.toString() + "' is not enabled even after waiting for :" + timeOut
					+ " seconds.");
		}

		return isElementVisible;

	}
	
	public boolean IsElementDisplayed(WebElement elementToDisplay)
	{
		boolean testFlag = false;
		try {
			waitforElement_to_visible(elementToDisplay,5000);
			if(elementToDisplay.isDisplayed())
			{
				return testFlag = true;
			}
		}
		catch(ElementNotInteractableException e) {
			e.printStackTrace();
		}
		catch(NoSuchElementException e) {
			e.printStackTrace();
		}catch(Exception e) {
			e.printStackTrace();
		}
		return testFlag;
	}
	
	public void jsClick(WebElement element) {
		JavascriptExecutor executor  = (JavascriptExecutor ) browserLaunch.BrowserHandler.getDriver();
		executor.executeScript("arguments[0].click();", element);
	}
	
	public static File takeScreenshot(String screenshotName) {
		File screenshotDestination = null;
		
		try {
			
			TakesScreenshot takescreenshot = (TakesScreenshot)BrowserHandler.getDriver();
			File screenshot = takescreenshot.getScreenshotAs(OutputType.FILE);
			screenshotDestination = new File(ApplicaitonBase.screenshotLocation+"/"+screenshotName+".png");
			Files.copy(screenshot, screenshotDestination);
		}catch(Throwable e) {
			e.printStackTrace();
		}
		return screenshotDestination;
	}
	
	public void enterText(WebElement element, String text) {
		try {
			
			awaitForElementToVisible(element);
			element.sendKeys(text);
			log.info(text+" has been entered into the field");
			
		}catch(ElementNotInteractableException e) {
			e.printStackTrace();
		}
		catch(NoSuchElementException e) {
			e.printStackTrace();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public boolean checkAttribute(WebElement element, String attribute, String value) {
		boolean testFlag = false;
		
		try {
			Thread.sleep(1500);
			if(element.getAttribute(attribute).equalsIgnoreCase(value)) {
				return testFlag=true;
			}
			
		}catch(ElementNotInteractableException e) {
			e.printStackTrace();
		}
		catch(NoSuchElementException e) {
			e.printStackTrace();
		}catch(Exception e) {
			e.printStackTrace();
		}
		return testFlag;
	}
	
	public boolean checkValueAndSelect(List<WebElement> element, String expectedOption) {
		boolean testFlag = false;
		
		
		try {	
				Thread.sleep(1500);
				if(element.size()!=0) {
					for(WebElement eachOption : element) {
						if(eachOption.getText().contains(expectedOption)) {
							pollingElement(eachOption);
							eachOption.click();
							log.info("Selected value is "+eachOption.getText());
							return testFlag = true;
						}
					}
				}
			
		}catch(ElementNotInteractableException e) {
			e.printStackTrace();
		}
		catch(NoSuchElementException e) {
			e.printStackTrace();
		}catch(Exception e) {
			
			e.printStackTrace();
		}
		return testFlag;
	}
	
	public boolean lookForDateAndSelect(String date) {
		boolean testFlag = false;
		SearchFlightPage pageObj = new SearchFlightPage();
		String month = date.split("-")[1];
		String year = date.split("-")[2];
		String day = date.split("-")[0]; 
		
		try {	
				while(pageObj.monthLabel.isDisplayed()) {
					if(pageObj.monthLabel.getAttribute("innerText").contains(month) 
							&& pageObj.monthLabel.getAttribute("innerText").contains(year)) {
						for(WebElement eachday : pageObj.dateRanges) {
							 if(eachday.getAttribute("innerText").equalsIgnoreCase(day)) {
								 performClick(eachday);
								 log.info("Selected date is "+date);
								 
								 return testFlag=true;
							 }
								
						}
			}else {
				performClick(pageObj.forwardButton);
			}
		}
		}catch(ElementNotInteractableException e) {
			e.printStackTrace();
		}
		catch(NoSuchElementException e) {
			e.printStackTrace();
		}catch(Exception e) {
			
			e.printStackTrace();
		}
		return testFlag;
	}
	
	public boolean ValidateSearchResultsPage(WebElement MoreElementDisplayed) throws InterruptedException 
	{
		return IsElementDisplayed(MoreElementDisplayed);
	}
	
	public void PerformJavaScriptClick(WebElement Element)
	{
		JavascriptExecutor js = (JavascriptExecutor) BrowserHandler.getDriver();
		js.executeScript("arguments[0].click();", Element);
	}
	
	
	public boolean lookForReturnDateAndSelect(String date) {
		boolean testFlag = false;
		SearchFlightPage pageObj = new SearchFlightPage();
		String month = date.split("-")[1];
		String year = date.split("-")[2];
		String day = date.split("-")[0]; 
		
		try {	
				while(pageObj.returnMonthLabel.isDisplayed()) {
					if(pageObj.returnMonthLabel.getAttribute("innerText").contains(month) 
							&& pageObj.returnMonthLabel.getAttribute("innerText").contains(year)) {
						for(WebElement eachday : pageObj.ReturndateRanges) {
							 if(eachday.getAttribute("innerText").equalsIgnoreCase(day)) {
								 PerformJavaScriptClick(eachday);
								 log.info("Selected date is "+date);
								 
								 return testFlag=true;
							 }
								
						}
			}else {
				PerformJavaScriptClick(pageObj.returnForwardButton);
			}
		}
		}catch(ElementNotInteractableException e) {
			e.printStackTrace();
		}
		catch(NoSuchElementException e) {
			e.printStackTrace();
		}catch(Exception e) {
			
			e.printStackTrace();
		}
		return testFlag;
	}
	
	
	public boolean selectPassengers(String passengers, String typeOfPassenger) {
		boolean testFlag = false;
		SearchFlightPage pageObj = new SearchFlightPage();
		
		try {	Thread.sleep(1500);
				if(!passengers.isEmpty() && typeOfPassenger.equalsIgnoreCase("adult")) {
					performClick(pageObj.listofAdults.get(Integer.parseInt(passengers)-1));
					log.info(passengers+" Traveller are selected");
					return testFlag = true;
				}if(!passengers.isEmpty() && typeOfPassenger.equalsIgnoreCase("children")) {
					performClick(pageObj.listofChildren.get(Integer.parseInt(passengers)-1));
					log.info(passengers+" Traveller are selected");
					return testFlag = true;
				}if(!passengers.isEmpty() && typeOfPassenger.equalsIgnoreCase("infant")) {
					performClick(pageObj.listofInfant.get(Integer.parseInt(passengers)-1));
					log.info(passengers+" Traveller are selected");
					return testFlag = true;
				}
			
		}catch(ElementNotInteractableException e) {
			e.printStackTrace();
		}
		catch(NoSuchElementException e) {
			e.printStackTrace();
		}catch(Exception e) {
			
			e.printStackTrace();
		}
		return testFlag;
	}
	
	public boolean RetrieveFlightPrice(String priceGreaterThan) {
		boolean testFlag = false;
		SearchFlightPage pageObj = new SearchFlightPage();
		List<Integer>prices = new ArrayList<Integer>();
		int convertedActualPrice = 0;
		int convertedExpectedPrice =0;
		
		try {	
				awaitForElementToVisible(pageObj.SearchSort);
				for(WebElement eachPrice : pageObj.listOfFlightPrice) {
					convertedActualPrice = Integer.parseInt(eachPrice.getText());
					convertedExpectedPrice = Integer.parseInt(priceGreaterThan);
					if(convertedActualPrice>convertedExpectedPrice) {
						prices.add(convertedActualPrice);
					}
				}
				
				Collections.sort(prices);
				
	    	    System.out.printf("%10s %30s %20s %20s %20s","Flight Number", "Departure at", "Departure Date","Departure From","Price");
			for(int eachValue : prices) {
				
				String flightNumberLoc = "(//div[@class='price']//span[text()='"+eachValue+"'])[1]//preceding::a[1]//following::div[1]";
				String departureTimeLoc = "(//div[@class='price']//span[text()='"+eachValue+"'])[1]//preceding::a[1]//following::div[5]";
	            String departureDateLoc = "(//div[@class='price']//span[text()='"+eachValue+"'])[1]//preceding::a[1]//following::div[6]";
	            String airportLoc = "(//div[@class='price']//span[text()='"+eachValue+"'])[1]//preceding::a[1]//following::div[7]";
	            
	            String flightNumber = BrowserHandler.getDriver().findElement(By.xpath(flightNumberLoc)).getText();
	            String departureTime = BrowserHandler.getDriver().findElement(By.xpath(departureTimeLoc)).getText();
	            String departureDate = BrowserHandler.getDriver().findElement(By.xpath(departureDateLoc)).getText();
	            String airport = BrowserHandler.getDriver().findElement(By.xpath(airportLoc)).getText();
	            
	            System.out.println("-----------------------------------------------------------------------------------------");
	            System.out.format("%10s %30s %20s %20s %20s", flightNumber,departureTime,departureDate,airport,eachValue);
    			System.out.println();
    			System.out.println("-----------------------------------------------------------------------------------------");   
				}
			  
				return testFlag =true;
				
			
		}catch(ElementNotInteractableException e) {
			e.printStackTrace();
		}
		catch(NoSuchElementException e) {
			e.printStackTrace();
		}catch(Exception e) {
			
			e.printStackTrace();
		}
		return testFlag;
	}
	
	
	
}
 