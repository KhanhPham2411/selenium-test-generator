package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public abstract class BasePage implements IPage{
	protected WebDriver driver;
	protected String url;
	protected String title;
	

	public BasePage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	public void start() {
		driver.get(url);
	}
	public void open() {
		driver.navigate().to(url);;
	}
	public String getTitle() {
		return title;
	}
	public String getUrl() {
		return url;
	}
	public Boolean isRedirected(){
		return !driver.getCurrentUrl().equals(this.url);
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public void setTitle(String title) {
		this.title = title;
	}
}
