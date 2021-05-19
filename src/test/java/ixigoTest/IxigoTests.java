package ixigoTest;


import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import applicationBase.ApplicaitonBase;
import helperMethods.GenericKeywords;
import helperMethods.PropertFileHandler;
import pageObjects.SearchFlightPage;

public class IxigoTests extends ApplicaitonBase   {

	SearchFlightPage searchPage = null;
	GenericKeywords keyword = null;
	PropertFileHandler prop = new PropertFileHandler();
	String fromCity,toCity,departureDate=null,ReturnDate =null;
	String noOfAdult,noOfChildren,noOfInfant=null;
	
	@BeforeTest
	public void initializePages() {
		fromCity= prop.getPropertyValues().get("from").toString();
		toCity = prop.getPropertyValues().get("to").toString();
		departureDate = prop.getPropertyValues().get("departure").toString();
		ReturnDate = prop.getPropertyValues().get("return").toString();
		noOfAdult = prop.getPropertyValues().get("adults").toString();
		noOfChildren = prop.getPropertyValues().get("children").toString();
		noOfInfant = prop.getPropertyValues().get("infant").toString();
		searchPage = new SearchFlightPage();
		keyword = new GenericKeywords();
	}

		/*		Launch https://www.ixigo.com/
		*		Validate the page
		*		Enter From – Delhi , To – Bangalore , Departure – 27 May 2021 , Return – 24 June 2021 , Travellers - 2
		*		Click on Search, Validate the result page
		*		Validate filter option for Stops, departure and Airlines – Select Non-Stop in Stops filter option
		*		Print the list of airlines details (Only Airline Number, Departure Time and Fare) having fare < 7000
		*/
	@Test(description="TC will launch ixigo.com page and search for flight and record the details")
	
	public void Launch_Application_Search_Flight_PrintAirlineDetails() throws InterruptedException {
		System.out.println("Under Test");
		logger = extent.createTest("To search flights and filter out sorted flight details based upon the price");
		Thread.sleep(2000);
		keyword.performClick(searchPage.RoundTrip);
		keyword.performClick(searchPage.cities.get(0));
		logger.info("From City selected successfully as "+fromCity);
		keyword.enterText(searchPage.cities.get(0),fromCity);
		logger.info("To City selected successfully as "+toCity);
		Assert.assertEquals(keyword.checkValueAndSelect(searchPage.cityNames,fromCity), true);
		logger.pass("From city value selected from the suggestion box successfully");
		keyword.performClick(searchPage.cities.get(1));
		keyword.enterText(searchPage.cities.get(1),toCity);
		Assert.assertEquals(keyword.checkValueAndSelect(searchPage.cityNames,toCity), true);
		logger.pass("To city value selected from the suggestion box successfully");
		keyword.performClick(searchPage.departureDate);
		Assert.assertEquals(keyword.lookForDateAndSelect(departureDate), true);
		logger.pass("Departure date has been selected from the suggestion box successfully");
		
		keyword.performClick(searchPage.returnDate);
		Assert.assertEquals(keyword.lookForReturnDateAndSelect(ReturnDate), true);
		logger.pass("Return date has been selected from the suggestion box successfully");
		keyword.performClick(searchPage.travellersInputBox);
		Assert.assertEquals(keyword.selectPassengers(noOfAdult,"adult"), true);
		logger.pass("Travellers details selected from the suggestion box successfully");
		
		keyword.performClick(searchPage.searchButton);
		
		Assert.assertTrue(keyword.IsElementDisplayed(searchPage.MoreFilters), "Not Navigated to Search Results page");
		logger.pass("Successfully Navigated to Search Results Page");
		keyword.PerformJavaScriptClick(searchPage.MoreFilters);
		logger.pass("More Filters has been selected successfully");
		keyword.PerformJavaScriptClick(searchPage.NonStop);
		keyword.PerformJavaScriptClick(searchPage.ApplyButton);
		logger.pass("Nonstop has been selected and Applied Filters successfully");
		
		Assert.assertEquals(keyword.RetrieveFlightPrice("7000"), true);
		logger.pass("Flight prices published successfully");
		
		
		
	}
	
	

}
