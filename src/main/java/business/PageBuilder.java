package business;

import org.openqa.selenium.WebDriver;

import models.Page;

public class PageBuilder {
	WebDriver driver;
	public PageBuilder(WebDriver driver){
		this.driver = driver;
	}

	public Page create(String urlOriginal) {
		Page page = new Page();
		page.setUrlOriginal(urlOriginal);
		page.setUrlRedirected(driver.getCurrentUrl());
		page.setTitle(driver.getTitle());
		page.setName(getPageName());
		
		return page;
	}

	public String getPageName(){
		String pageName = driver.getTitle().replace(" ", "");
		pageName = pageName.replace("-", "");
        if(!pageName.toLowerCase().contains("page")){
            pageName += "Page";
        }

        return pageName;
    }
}
