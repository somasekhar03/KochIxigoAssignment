package pageObjects;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import browserLaunch.BrowserHandler;

public class SearchFlightPage {
	
	public SearchFlightPage() {
		PageFactory.initElements(BrowserHandler.getDriver(), this);
	}

	@FindBy(xpath="//span[text()='Round Trip']")
	public WebElement RoundTrip;
	
	@FindBy(css="div.form-fields input[autocomplete='new-password']")
	public List<WebElement>cities;
	
	@FindBy(xpath="//div[text()='From']//following::div[1]")
	public WebElement cityNameArea;
	
	@FindBy(xpath="//div[@class='airport']//preceding::div[1]")
	public List<WebElement> cityNames;
	
	@FindBy(xpath="//div[text()='Return']//following::input[1]")
	public WebElement returnDate;
	
	@FindBy(xpath="//div[text()='Departure']//following::input[1]")
	public WebElement departureDate;
	
	@FindBy(xpath="(//div[@class='rd-month']//following::div[@class='rd-month-label'])[1]")
	public WebElement monthLabel;
	
	@FindBy(xpath="(//div[@class='rd-month']//following::div[@class='rd-month-label'])[3]")
	public WebElement returnMonthLabel;
	
	@FindBy(xpath="//table[@class='rd-days']//td[not(contains(@class,'day-disabled'))]//div[1]")
	public List<WebElement> dateRanges;
	
	@FindBy(xpath="//div[(contains(@class,'flight-ret-cal'))]//table[@class='rd-days']//td[not(contains(@class,'day-disabled'))]//div[1]")
	public List<WebElement> ReturndateRanges;
	
	@FindBy(css="button[class='ixi-icon-arrow rd-next']")
	public WebElement forwardButton;
	
	@FindBy(xpath="//div[@class='more-fltrs u-link']")
	public WebElement MoreFilters;
	
	@FindBy(xpath="//div[@class='fltr-col-1 u-ib']//span[@class='checkbox-list']/div[1]/span[1]/span")
	public WebElement NonStop;
	
	@FindBy(xpath="//button[.='APPLY']")
	public WebElement ApplyButton;	
	
	@FindBy(xpath="(//button[@class='ixi-icon-arrow rd-next'])[2]")
	public WebElement returnForwardButton;
	
	@FindBy(xpath="//div[text()='Travellers | Class']//following::input[1]")
	public WebElement travellersInputBox;
	
	@FindBy(css="div[class='u-box passanger-class-list flex-container']")
	public WebElement passengerListContainer;
	
	@FindBy(css="div.number-counter:nth-child(1) span")
	public List<WebElement>listofAdults;
	
	@FindBy(css="div.number-counter:nth-child(2) span")
	public List<WebElement>listofChildren;
	
	@FindBy(css="div.number-counter:nth-child(3) span")
	public List<WebElement>listofInfant;
	
	@FindBy(css="div[class='close-btn u-pos-abs ixi-icon-cross']")
	public WebElement passengerListCloseButton;
	
	@FindBy(css="div[class='search u-ib u-v-align-bottom'] div")
	public WebElement searchButton;
	
	@FindBy(xpath="//div[@class='u-ib u-layout-col-1']/div/div[3]/div[1]/div[@class='nav-with-label']/div[@class='nav-label u-ib']")
	public WebElement SearchSort;
	
	@FindBy(css="div.price-group div.price span:nth-child(2)")
	public List<WebElement>listOfFlightPrice;
	
	
	
	
}
