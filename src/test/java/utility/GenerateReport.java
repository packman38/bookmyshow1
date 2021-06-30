package test.java.utility;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterSuite;
import test.java.managers.WebDriverManager;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class GenerateReport {
	WebDriver driver= WebDriverManager.getDriver();
	public  ExtentReports reporter;
	public ExtentTest extentTest=null;
	private static GenerateReport generateReportInstance;
	private String fileWithPath=System.getProperty("user.dir")+"\\Screenshots\\";

	private GenerateReport(){
		try {

			Date date = Calendar.getInstance().getTime();
			DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh-mm-ss");
			System.out.println(1);
			String strDate = dateFormat.format(date);
			String fileName= System.getProperty("user.dir")+"\\Reports\\Extent"+strDate+"Report.html";
			System.out.println(fileName);

			File file = new File(fileName);
			file.createNewFile();

			reporter= new ExtentReports(fileName,true);
		}catch (Exception e){
			System.out.println(e.getStackTrace());
			System.out.println(e.getMessage());
		}
	}

	public static GenerateReport getInstance(){
		return (generateReportInstance==null)? generateReportInstance=new GenerateReport():generateReportInstance;
	}

	public ExtentTest startTest(String name){
		extentTest= reporter.startTest(name);
		return extentTest;
	}
	public void endTest(){
		reporter.endTest(extentTest);
	}
//	public static void generateReport()  {
//		try {
//
//			Date date = Calendar.getInstance().getTime();
//			DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh-mm-ss");
//			System.out.println(1);
//			String strDate = dateFormat.format(date);
//			String fileName= "E:\\FrameWork\\Created\\KeyWordDriven\\Reports\\Extent"+strDate+"Report.html";
//
//			File file = new File(fileName);
//			file.createNewFile();
//
//			reporter= new ExtentReports(fileName,true);
//		}catch (Exception e){
//			System.out.println(e.getStackTrace());
//		}


//	}
	
//	@AfterSuite
//	public void closeReport(){
//		reporter.close();
//	}

	public void attachScreenShot(String... name) {
		TakesScreenshot scrShot = ((TakesScreenshot) driver);
		File SrcFile = scrShot.getScreenshotAs(OutputType.FILE);
		if (name.length == 0) {
			name[0] = extentTest.getTest().name;
		}

		File DestFile = new File(fileWithPath + name[0] + ".jpeg");
		try {
			FileUtils.copyFile(SrcFile, DestFile);
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
		extentTest.log(LogStatus.INFO, "<img src='" + "..\\Screenshots\\" + name[0] + ".jpeg'");

	}

	public void attachFailedScreenShot(String... name) {
		TakesScreenshot scrShot = ((TakesScreenshot) driver);
		File SrcFile = scrShot.getScreenshotAs(OutputType.FILE);

		File DestFile = new File(fileWithPath+"Failed\\" + name[0] + ".jpeg");
		try {
			FileUtils.copyFile(SrcFile, DestFile);
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
		extentTest.log(LogStatus.INFO, "<img src='" + "..\\Screenshots\\Failed\\" + name[0] + ".jpeg'");

	}

}
