package com.sauce;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.ie.InternetExplorerOptions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

public class ExecuteScriptAcrossSauce {
	
	
	public static void main(String[] args) {
		WebDriver driver =null;
		
		System.setProperty("SAUCE_USERNAME", "nagaraju.kura");
		System.setProperty("SAUCE_ACCESS_KEY", "6ae079ba-78a5-4bc2-b0ec-fdc63ca94728");		
		driver = getDriver("chrome","79.0","Windows 10");
		/*driver = getDriver("firefox","70.0","Windows 10");
		driver = getDriver("IE", "11.285", "Windows 10");*/
		
        
        driver.close();
        driver.quit();
        
	}
	
	  private static RemoteWebDriver getDriver(String browserName,String browserVersion, String platformName )  {
			WebDriver driver =null;

	        //String sauceURL = "https://ondemand.saucelabs.com/wd/hub";
	        //String sauceURL = "https://ondemand.eu-central-1.saucelabs.com/wd/hub";
	        String sauceURL = "https://ondemand.us-west-1.saucelabs.com/wd/hub";
	        
	        
	        DesiredCapabilities capabilities = new DesiredCapabilities();

			MutableCapabilities sauceOptions = new MutableCapabilities();
			sauceOptions.setCapability("username", System.getProperty("SAUCE_USERNAME"));
			sauceOptions.setCapability("accessKey", System.getProperty("SAUCE_ACCESS_KEY"));

			MutableCapabilities browserOptions = null;
	        try {
				if(browserName.equalsIgnoreCase("chrome")){
					browserOptions = new ChromeOptions();
					browserOptions.setCapability("browserName", browserName);
					//((ChromeOptions) browserOptions).setExperimentalOption("w3c", true);
				}else if(browserName.equalsIgnoreCase("firefox") || browserName.equalsIgnoreCase("ff")){
					browserOptions = new FirefoxOptions();
				}else if(browserName.equalsIgnoreCase("ie") || browserName.equalsIgnoreCase("internetexplorer")){
					sauceOptions.setCapability("screenResolution", "1280x1024");
					browserOptions = new InternetExplorerOptions();
				}

				/*browserOptions.setCapability(CapabilityType.BROWSER_NAME, browserName);
				browserOptions.setCapability(CapabilityType.BROWSER_VERSION, browserVersion);			
				browserOptions.setCapability(CapabilityType.PLATFORM_NAME, platformName);
				*/
				browserOptions.setCapability("platformName", platformName);
				browserOptions.setCapability("browserVersion", browserVersion);
				browserOptions.setCapability("name", "saucetest1");

				browserOptions.setCapability("sauce:options", sauceOptions);
				
				driver = new RemoteWebDriver(new URL(sauceURL), browserOptions);
		        String LiveRecordingLink = ((RemoteWebDriver) driver).getSessionId().toString();
		        System.out.println(LiveRecordingLink);
		        System.out.println("https://app.saucelabs.com/tests/"+LiveRecordingLink);
		        
				driver.get("http://google.com");
		        driver.manage().window().maximize();
				Thread.sleep(30000);
			} catch (MalformedURLException | InterruptedException e) {
				e.printStackTrace();
			}

	        /*
			WebElement ele= driver.findElement(By.id("dsfs"));
			WebDriverWait wait = new WebDriverWait(driver, 10);
			WebElement sd=wait.until(ExpectedConditions.elementToBeClickable(ele));
			*/
			return (RemoteWebDriver) driver;

	  }
	
	  
	  private static WebDriver getDriver(String browserName)  {
		  DesiredCapabilities dc = new DesiredCapabilities();
		  WebDriver driver = null;	  
		  
		  try{
			  
			  if(browserName.equalsIgnoreCase("chrome")){
				  System.setProperty("webdriver.chrome.driver", "src/main/resources/chrome79v/chromedriver.exe");
				  driver= new org.openqa.selenium.chrome.ChromeDriver(dc);  
			  }else if (browserName.equalsIgnoreCase("ff") ||  browserName.equalsIgnoreCase("firefox")){
				  driver = new FirefoxDriver();
			  }else{
				  System.setProperty("webdriver.ie.driver","src/main/resources/ie/IEDriverServer.exe");
				  driver= new InternetExplorerDriver();
			  }
			    
		  }catch(Exception e){
			  e.printStackTrace();
		  }
		  return driver;	  
	  }
	  
	  
	  
	  

}
