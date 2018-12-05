package test;


import java.util.Set;

import org.openqa.selenium.Cookie;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;

import pages.CustomerLoginPage;

public class BaseTestCaseWithLogin extends BaseTestCase {
	private static Set<Cookie> sharedCookies;

	@BeforeTest(dependsOnMethods = "launch")
	public void login() {
		CustomerLoginPage customerLoginPage = new CustomerLoginPage(driver);
		customerLoginPage.start();
		
		if(sharedCookies == null){
			customerLoginPage.login();
			sharedCookies = driver.manage().getCookies();
		}else{
			// driver.get("https://www.google.com.vn/");
			for(Cookie cookie : sharedCookies){
				driver.manage().addCookie(cookie);
			}
		}
	}
}
