package extentreports.examples;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Iterator;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.MediaEntityModelProvider;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.ExtentHtmlReporterConfiguration;
import com.gargoylesoftware.htmlunit.ElementNotFoundException;

public class RegulatorRequirement {

	WebDriver driver = null;
	DesiredCapabilities dc = new DesiredCapabilities();
	//ExtentHtmlReporter ehr = new ExtentHtmlReporter("src/main/resources/ExtentReports/RegulatorRequirement.html");
	ExtentHtmlReporter ehr = new ExtentHtmlReporter("src/test/resources/ExtentReports/RegulatorRequirement.html");

	ExtentReports extentReports = new ExtentReports();
	

	
	
	@BeforeTest
	public void beforeTest(){
		extentReports.attachReporter(ehr);
	}


	//@Test(priority=1)
	public void login() throws IOException{
		driver.findElement(By.xpath(ORElements.user)).sendKeys("xyz");
		driver.findElement(By.xpath(ORElements.pwd)).sendKeys("xyz");
		driver.findElement(By.xpath(ORElements.loginbtn)).click();
	}
	
	
	@Test(priority=1)
	public void launchBrowser(){
		ExtentTest logger = null;
		try{
			logger = extentReports.createTest("launchBrowser","launchBrowser - RegulatorRequirement");
			logger.log(Status.INFO, "Launching the Browser");			
			driver = openBrowser("chrome", dc, driver);
			if( driver!=null){
				logger.log(Status.PASS, "Browser Launched");	
			}else{
				logger.log(Status.FAIL, "Browser Launched");
			}
						
				
		}catch(Exception e){
			logger.log(Status.FAIL,e.getMessage());
		}finally{
			extentReports.flush();
		}
	}
	
	@Test(priority=2)
	public void test2X(){
		ExtentTest logger3 =null;


		logger3 = extentReports.createTest("test2X","RegulatorRequirement - test2 ");
		logger3.log(Status.INFO, "Test2 begins");
		logger3.log(Status.PASS, "Test2 PASS");
		extentReports.flush();
	}
	
	
	@Test(priority=3)
	public void testDashboard() throws IOException{
		/*ExtentTest logger = null;
		
		
		try{
			logger = extentReports.createTest("testDashboard","ExtentReportExample1-Description");
			logger.log(Status.INFO, "Launching the Browser");
			
			driver = openBrowser("chrome", dc, driver);
			if( driver!=null){
				logger.log(Status.PASS, "Browser Launched");	
			}else{
				logger.log(Status.FAIL, "Browser Launched");
			}
						
				
		}catch(Exception e){
			logger.log(Status.FAIL,e.getMessage());
		}finally{
			extentReports.flush();
		}*/
		
		
		
		
		ExtentTest logger2 =null;
		try{
			
			logger2 = extentReports.createTest("testDashboard","RegulatorRequirement - testDashboard");
			logger2.log(Status.INFO, "Test2 begins");
			driver.get("http://facebook.com");
			logger2.log(Status.PASS,"Navigated across Facebook Page");
			Thread.sleep(3000);
			driver.manage().window().maximize();
			logger2.log(Status.PASS,"Window Maximized");
			Thread.sleep(3000);
			driver.findElement(By.xpath("//*[@id='u_0_m123']")).sendKeys("hello");
			
		}catch(Exception e){
			
			logger2.log(Status.FAIL,e.getMessage());

			try {
				takeSnapShot(driver, "src/main/resources/ExtentReports/error1.png");
				logger2.fail("Failed to get reference of login element",MediaEntityBuilder.createScreenCaptureFromPath("D:\\eclipse-jee-oxygen\\workspaces1\\hackathon\\src\\main\\resources\\ExtentReports\\error1.png").build());							
			} catch (Exception e1) {
				logger2.log(Status.FAIL,e1.getMessage()+"\n........ssdfsdfas");
			}
			
		}finally{
			extentReports.flush();	
		}
		
		
		
		
        /*try
        {
            FileInputStream file = new FileInputStream(new File("src/main/resources/TestData.xls"));
            //Create Workbook instance holding reference to .xlsx file
            HSSFWorkbook workbook = new HSSFWorkbook(file);
 
            //Get first/desired sheet from the workbook
            HSSFSheet sheet = workbook.getSheetAt(0);
 
            //Iterate through each rows one by one
            Iterator<Row> rowIterator = sheet.iterator();
            int iIndex=-1;
            while (rowIterator.hasNext()) 
            {
            	iIndex++;	            	
                Row row = rowIterator.next();
                
                //For each row, iterate through all the columns
                Iterator<Cell> cellIterator = row.cellIterator();
                if(iIndex>0){
                	System.out.print(iIndex+":"+" ");
                	
                	int jIndex=-1;
                	String uniqueIdentifier=null;
                	String tradeID=null;
                	String CMV=null;
                	
                    while (cellIterator.hasNext()) 
                    {
                    	
                    	jIndex++;
                    	String sCellValue = null;

                    	
                        Cell currentCell = cellIterator.next();
                        if (currentCell.getCellTypeEnum() == CellType.STRING) {
    	                       sCellValue=currentCell.getStringCellValue();
    	                } else if (currentCell.getCellTypeEnum() == CellType.NUMERIC) {
    	                    	sCellValue=currentCell.getNumericCellValue()+"";
    	                }
                        if(jIndex==1){
                        	uniqueIdentifier=sCellValue;
                        }else if(jIndex==5){
                        	tradeID=sCellValue;
                        }else if(jIndex==6){
                        	CMV=sCellValue;
                        }
                        
                        //System.out.print(sCellValue + "t");                        
                    }
                    System.out.println("uniqueIdentifier:"+uniqueIdentifier+ " tradeID:"+tradeID+ " , CMV:"+CMV);
                    
                    try{
                        WebElement uniqueIdentifierEle = driver.findElement(By.xpath(".//div//table//td[contains(text(),'"+uniqueIdentifier+"')]"));
                        WebElement tradeIDEle = uniqueIdentifierEle.findElement(By.xpath(".//following-sibling::td[contains(text(),'"+tradeID+"')]"));
                        WebElement CMVEle = uniqueIdentifierEle.findElement(By.xpath(".//following-sibling::td[contains(text(),'"+CMV+"')]"));                    	
                    }catch(ElementNotFoundException e){
                    	
                    }
                    
                }
                System.out.println("");
            }
            file.close();
        } 
        catch (Exception e) 
        {
            e.printStackTrace();
        }*/

		
		
		
		
		
	}
	

	

	@AfterTest
	public void afterTest(){
		driver.close();
		driver.quit();
	}

	
	
	
	
	
	
    public static void takeSnapShot(WebDriver webdriver,String fileWithPath) throws Exception{

        //Convert web driver object to TakeScreenshot
        TakesScreenshot scrShot =((TakesScreenshot)webdriver);

        //Call getScreenshotAs method to create image file
        File SrcFile=scrShot.getScreenshotAs(OutputType.FILE);

         //Move image file to new destination
        File DestFile=new File(fileWithPath);

         //Copy file at destination
         FileUtils.copyFile(SrcFile, DestFile);

    }
	
	
	public  WebDriver openBrowser(String browserName,DesiredCapabilities dc, WebDriver fd ) {
		try{
			System.out.println("open chrome browser2");
			dc.setCapability("name", "ExtentReportExample1");
			
			if(browserName.equalsIgnoreCase("chrome")){
				System.setProperty("webdriver.chrome.driver", "src/main/resources/chrome79v/chromedriver.exe");
				dc.setCapability(CapabilityType.BROWSER_NAME, "chrome");
				fd = new ChromeDriver(dc);				
			}else if(browserName.equalsIgnoreCase("firefox")){
				System.setProperty("webdriver.chrome.driver", "src/main/resources/geckodriver.exe");
				dc.setCapability(CapabilityType.BROWSER_NAME, "firefox");				
				
			}else if(browserName.equalsIgnoreCase("ie")){
				System.setProperty("webdriver.ie.driver","src/main/resources/ie/IEDriverServer.exe");
				DesiredCapabilities capabilities = DesiredCapabilities.internetExplorer();				
				capabilities.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
				capabilities.setCapability(InternetExplorerDriver.UNEXPECTED_ALERT_BEHAVIOR, "ignore");
				dc.setCapability(CapabilityType.BROWSER_NAME, "IE");				
				fd = new InternetExplorerDriver(capabilities);
			}
			
			fd.manage().timeouts().pageLoadTimeout(90, TimeUnit.SECONDS);			


		}catch(Exception e){
			e.printStackTrace();
		}
		return fd;
	}
	
	
	
	
	
}
