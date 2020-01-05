import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;
import org.testng.annotations.AfterClass;

public class LaunchChrome {
 

	WebDriver driver;
	
 @Test
  public void test() {
	 driver= getDriver("chrome");
	 //driver.manage().timeouts().pageLoadTimeout(15, TimeUnit.SECONDS);
	 
	 //Boolean sval = driver.getTitle().toLowerCase().contains("google");
	 driver.manage().timeouts().implicitlyWait(30000, TimeUnit.MILLISECONDS);
     
	 try{
		 driver.manage().window().maximize();
		 Thread.sleep(10);
		 driver.get("http://google.com");
		 Assert.assertEquals("google", driver.getTitle().toLowerCase());
		 
		 Thread.sleep(5000);
		 System.out.println("waited 5 seconds");
		 Assert.assertEquals("google1", driver.getTitle().toLowerCase());
		 System.out.println("waiting for  5 seconds");
		 Thread.sleep(5000);
 
	 }catch(Exception e){
		 e.printStackTrace();
	 }catch(AssertionError ae){
		 System.out.println("waiting for  5 seconds");
		 try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		 ae.printStackTrace();
	 }
	 
     
     
  }
  
 @BeforeClass
 public void beforeClass() {
 }

 
 @AfterClass
 public void afterClass() {
	  driver.close();
	  driver.quit();
 }
 
  private WebDriver getDriver(String browserName)  {
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
