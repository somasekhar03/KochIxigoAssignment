package browserLaunch;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import helperMethods.PropertFileHandler;

public class BrowserHandler {

	private static WebDriver driver;
	private static BrowserHandler instanceOfBrowserHandler = null;
	PropertFileHandler prop = new PropertFileHandler();
	
	
	public BrowserHandler() {
		File file = new File("src/test/resources/driver/chromedriver.exe");
		System.out.println(file.getAbsolutePath());
		Map<String, Object> prefs = new HashMap<String, Object>();
		prefs.put("profile.default_content_setting_values.notifications", 2);
		ChromeOptions options = new ChromeOptions();
		options.setExperimentalOption("prefs", prefs);
		options.addArguments("start-maximized");
		BrowserOptions browser = BrowserOptions.valueOf(prop.getPropertyValues().getProperty("browser"));
		
		
		switch(browser) {
		case Chrome :
			
			System.setProperty("webdriver.chrome.driver", file.getAbsolutePath());
			driver = new ChromeDriver(options);
			break;
		
		case ChromeHeadless:
			
			System.setProperty("webdriver.chrome.driver", file.getAbsolutePath());
			options.setHeadless(true);
			driver = new ChromeDriver(options);
			break;	
		default:
			
			System.setProperty("webdriver.chrome.driver", file.getAbsolutePath());
			driver = new ChromeDriver(options);
			break;
		}
	}
	
	
	
	public  static BrowserHandler getInstanceOfBrowser() {
		if(instanceOfBrowserHandler == null) {
			instanceOfBrowserHandler = new BrowserHandler();
		}
		return instanceOfBrowserHandler;
	}
	
	public static WebDriver getDriver() {
		return driver;
	}
}
