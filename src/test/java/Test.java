import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class Test {

	public static void main(String[] args) {

		System.setProperty("webdriver.chrome.driver", "src/main/resources/chrome78v/chromedriver.exe");
		
		WebDriver fd = new ChromeDriver();
		try{
			fd.manage().timeouts().implicitlyWait(10000, TimeUnit.MILLISECONDS);
			fd.navigate().to("http://google.com");			
			fd.manage().timeouts().implicitlyWait(20000, TimeUnit.MILLISECONDS);
			fd.get("http://facebook.com");
			Thread.sleep(3000);
			
		}catch(Exception e){
			e.printStackTrace();
		}finally {
			fd.close();		
			fd.quit();			
		}
		
				
		
	}

}
