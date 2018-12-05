package test;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import business.TemplateGenerator;
import helper.ReadGuru99ExcelFile;
import helper.WriteGuru99ExcelFile;
import models.Page;

public class TestGenerator extends BaseTestCaseWithLogin {
    String homePage = "http://live.guru99.com/";

	@Test
	public void Craw() throws IOException {
        driver.get(homePage);

        Traversal traversal = new Traversal(driver);
        traversal.Run(homePage);

        ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(new File("E:\\Temp\\data.json"), traversal.getPageList());
    }
    
    @Test
    public void Generate(){
    	
    }

   

    @Test
    public void Test() throws IOException {
        List<Page> pageList;
        ObjectMapper mapper = new ObjectMapper();
        pageList = mapper.readValue(new File("E:\\Temp\\data.json"), new TypeReference<List<Page>>(){});

        TemplateGenerator template = new TemplateGenerator();
        template.generatePages(pageList);
    }
}
