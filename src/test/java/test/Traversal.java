package test;

import java.io.IOException;
import java.lang.ref.Cleaner;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;

import org.apache.commons.io.output.StringBuilderWriter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import business.PageBuilder;
import models.Page;

public class Traversal {
    List<Page> pageList;
    Map<String, Integer> elementList;
    List<String> pageListUrl;
    
    private String homePage;
    private WebDriver driver;

    public Traversal(){

    }

    public Traversal(WebDriver driver){
        this.driver = driver;

        Refresh();
    }

    public void Refresh(){
        pageList = new ArrayList<Page>();
        elementList = new HashMap<String, Integer>();
        pageListUrl = new ArrayList<String>();
    }

    public void Run(String startPage){
        Refresh();
        setHomePage(startPage);

        try {
            Run(startPage, 5, 0);
        } catch (JsonGenerationException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (JsonMappingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    private void Run(String startPage, int deep, int currentDeep) throws JsonGenerationException, JsonMappingException, IOException{
        addPage(startPage);

        // Link Element
        List<WebElement> links = driver.findElements(By.tagName("a"));
        List<String> urlList = new ArrayList<String>();
        for(int i = 0; i < links.size(); i++){
            WebElement link = links.get(i);
            String url = link.getAttribute("href");

            if(!verifyUrl(url)){
                continue;
            } 
            urlList.add(url);
            
            addElement(url);
        }

        // Traversal
        if(currentDeep == deep){
            return;
        }
        for(String url : urlList){
            if(pageListUrl.contains(url)){
                continue;
            }

            pageListUrl.add(url);
            driver.navigate().to(url);
            Run(url, deep, currentDeep + 1);
        }
    }


    public List<Page> getPageList() {
        return pageList;
    }

    public void setPageList(List<Page> pageList) {
        this.pageList = pageList;
    }

    public void setHomePage(String startPage){
        String temp = startPage.substring(startPage.indexOf("//") + 2);
        if(temp.contains("/")){
            homePage = temp.substring(0, temp.indexOf("/"));
        }else{
            homePage = temp;
        }
    }

    public Boolean verifyUrl(String url)
    {
        if(url == null || url.isEmpty()){
            return false;
        }
        if(url.contains("#")){
            System.out.println("URL contain hash tag cannot click on it");
            return false;
        }
        if(!url.contains(homePage)){
            System.out.println("URL belongs to another domain, skipping it.");
            return false;
        }
        return true;
    } 
    
    public Map<String, Integer> getElementList() {
		return elementList;
    }
    
    public void addElement(String element){
        if(elementList.containsKey(element)){
            elementList.put(element, elementList.get(element) + 1);
        }else{  
            elementList.put(element, 1);
        }

    }
    
    public Boolean IsElementExist(String element){
        return elementList.containsKey(element);
    }

	public void setElementList(Map<String, Integer> elementList) {
		this.elementList = elementList;
    }

    public void addPage(String pageUrl){
        
        PageBuilder pageBuilder = new PageBuilder(driver);
        pageList.add(pageBuilder.create(pageUrl));
    }

	@Test
    public void testSetHomePage(){
        String startPage = "http://live.guru99.com/";
        setHomePage(startPage);
        Assert.assertEquals(homePage, "live.guru99.com");
    }
}
