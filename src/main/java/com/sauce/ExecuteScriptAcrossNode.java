package com.sauce;

import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.ie.InternetExplorerOptions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

public class ExecuteScriptAcrossNode {
	
	
	public static void main(String[] args) throws InterruptedException {
		WebDriver driver =null;
		
		
		//driver = getHubDriver("chrome");
		driver = getDriver("chrome");
		
		//driver = getHubDriver("firefox");

		try {
			driver.get("http://google.com");
	        driver.manage().window().maximize();
			Thread.sleep(30000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

        
        driver.close();
        driver.quit();
        
	}
	
	
	
	  private static RemoteWebDriver getHubDriver(String browserName ) throws InterruptedException  {
			WebDriver driver =null;
	        String gridURL = "http://localhost:4444/wd/hub";
	        DesiredCapabilities capabilities = new DesiredCapabilities();

	        try {
				if(browserName.equalsIgnoreCase("chrome")){
					System.setProperty("webdriver.chrome.driver", "src/main/resources/chrome79v/chromedriver.exe");					
					capabilities.setCapability("browserName", browserName);
				}else if(browserName.equalsIgnoreCase("firefox") || browserName.equalsIgnoreCase("ff")){
					System.setProperty("webdriver.gecko.driver","src/main/resources/geckodriver.exe");
					capabilities.setCapability("marionette",true);
					capabilities = DesiredCapabilities.firefox();
					
				}else if(browserName.equalsIgnoreCase("ie") || browserName.equalsIgnoreCase("internetexplorer")){
					System.setProperty("webdriver.ie.driver","src/main/resources/ie/IEDriverServer.exe");
				}
				
				capabilities.setCapability("platform","Windows 10");
				driver = new RemoteWebDriver(new URL(gridURL), capabilities);		        
				/*String LiveRecordingLink = ((RemoteWebDriver) driver).getSessionId().toString();
		        System.out.println(LiveRecordingLink);
		        System.out.println("https://app.saucelabs.com/tests/"+LiveRecordingLink);*/
		        
			} catch (MalformedURLException e) {
				e.printStackTrace();
			}

	        Thread.sleep(1000);
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
				  System.setProperty("webdriver.gecko.driver","src/main/resources/geckodriver.exe");				  
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
