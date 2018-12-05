package business;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.apache.commons.io.FileUtils;

import helper.FileHelper;
import models.Page;

public class TemplateGenerator {
	private String pageTemplateFile;
	private String templateDirectory;
	private String templateSharedDirectory;
	private String generatorDirectory;

	public TemplateGenerator() throws IOException {
		templateDirectory = "E:\\Temp\\template";
		generatorDirectory = "E:\\Temp\\generator";
		templateSharedDirectory = "E:\\Temp\\template\\shared";
		
		pageTemplateFile = templateDirectory + "\\Page.java";
	}
	
	public void generatePage(Page page) throws IOException {
		if(!page.getUrlOriginal().equals(page.getUrlRedirected())){
			return;
		}

		String pageTemplateText = FileUtils.readFileToString(new File(pageTemplateFile));
		pageTemplateText = pageTemplateText.replace("{PageName}", page.getName());
		pageTemplateText = pageTemplateText.replace("{Url}", page.getUrlOriginal());
		pageTemplateText = pageTemplateText.replace("{Title}", page.getTitle());

		String pageGeneratedFile = generatorDirectory + "\\" + page.getName() + ".java";
		FileUtils.writeStringToFile(new File(pageGeneratedFile), pageTemplateText);
	}

	public void generatePages(List<Page> pageList) throws IOException {
		copySharedDirectory();

		for(Page page : pageList){
			generatePage(page);
		}
	}

	public void copySharedDirectory() throws IOException {
		FileHelper.copyDirectory(new File(templateSharedDirectory), new File(generatorDirectory));
	}
}
