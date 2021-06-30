package test.java.utility;


import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.LogStatus;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;
import test.java.managers.PageObjectManager;
import test.java.managers.WebDriverManager;

import java.io.File;

@Listeners({Listner.class})
public class BaseClass {
	public static WebDriver driver;
	public static PageObjectManager pageObjectManager;

	
	@BeforeSuite
	public void launchDriver(){

		driver= new WebDriverManager().getDriver();
		System.out.println("BeforeSuite Executed");
//		driver = new ChromeDriver();
//		GenerateReport.generateReport();
		pageObjectManager= PageObjectManager.getInstance();
	}
	@BeforeClass
	public void beforeTest(){
//		GenerateReport.getInstance().reporter = new ExtentReports(System.getProperty("user.dir") +"/test-output/STMExtentReport.html", true);
		//extent.addSystemInfo("Environment","Environment Name")
		GenerateReport.getInstance().reporter
				.addSystemInfo("Host Name", System.getProperty("user.name"))
				.addSystemInfo("Environment", "Production")
				.addSystemInfo("User Name", "Rachit Manglik");
		//loading the external xml file (i.e., extent-config.xml) which was placed under the base directory
		//You could find the xml file below. Create xml file in your project and copy past the code mentioned below
//		GenerateReport.getInstance().reporter..config(new File(System.getProperty("user.dir")+"\\extent-config.xml"));
	}

	@AfterTest
	public void endTest(){
		GenerateReport.getInstance().endTest();
	}
	
	@AfterSuite
	public void closeBrowser(){
		driver.quit();
		GenerateReport.getInstance().reporter.flush();
		GenerateReport.getInstance().reporter.close();
	}




}
