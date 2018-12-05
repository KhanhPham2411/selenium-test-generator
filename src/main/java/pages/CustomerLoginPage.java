package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CustomerLoginPage extends BasePage {

	@FindBy(css="a[title=\"Create an Account\"]")
	WebElement btnCreateAnAccount;

	public CustomerLoginPage(WebDriver driver) {
		super(driver);
		
		this.url = "http://live.guru99.com/index.php/customer/account/login/";
		this.title = "Customer Login";
	}

	public void login(){
		driver.findElement(By.id("email")).clear();
		driver.findElement(By.id("email")).sendKeys("test11@example.com");
		driver.findElement(By.id("pass")).clear();
		driver.findElement(By.id("pass")).sendKeys("duckhanh");
		driver.findElement(By.id("send2")).click();
	}

	public void clickButtonCreateAnAccount(){
		btnCreateAnAccount.click();
	}
}
