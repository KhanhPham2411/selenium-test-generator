package test;

import org.testng.annotations.BeforeTest;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;

public class BaseTestCase {
	  protected WebDriver driver;

	  @BeforeTest
	  public void launch() {
		  System.setProperty("webdriver.chrome.driver","E:\\Personal\\Jobs\\20180824 Shopee\\Selenium\\chromedriver.exe");
		  driver = new ChromeDriver();
	      driver.manage().timeouts().implicitlyWait(4, TimeUnit.SECONDS);
	  }
	
	  @AfterTest
	  public void quit() throws InterruptedException {
		   	Thread.sleep(3000);
	        driver.quit();
	   }
	  
}
