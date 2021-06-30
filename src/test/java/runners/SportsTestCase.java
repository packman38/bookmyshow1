package test.java.runners;

import com.relevantcodes.extentreports.LogStatus;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import test.java.managers.WebDriverManager;
import test.java.pageactions.BmsHomePageActions;
import test.java.pageactions.MoviesPageActions;
import test.java.pageactions.SportsPageActions;
import test.java.pageobjects.BmsHomePageObjects;
import test.java.pageobjects.MoviesPageObjects;
import test.java.pageobjects.SportsPageObjects;
import test.java.utility.BaseClass;
import test.java.utility.GenerateReport;
import test.java.utility.ReadExcel;

import java.util.HashMap;
import java.util.List;

public class SportsTestCase extends BaseClass {
    WebDriver driver;
    BmsHomePageObjects bmsHomePageObjects;
    BmsHomePageActions bmsHomePageActions;
    SportsPageObjects sportsPageObjects;
    SportsPageActions sportsPageActions;


    @BeforeTest
    public void setUpPage() {
        bmsHomePageObjects = pageObjectManager.getBmsHomePageObjects();
        bmsHomePageActions = pageObjectManager.getBmsHomePageActions();
        sportsPageActions= pageObjectManager.getSportsPageActions();
        sportsPageObjects= pageObjectManager.getSportsPageObjects();



        driver = WebDriverManager.getDriver();
    }

    @Test(testName = "CheckMoviePass")
    public void checkMoviesPass(){
        bmsHomePageActions.navigateToBMS();
        GenerateReport.getInstance().extentTest.log(LogStatus.PASS,"BMS launcehed");
        bmsHomePageActions.dismissPersonalizedUpdates();
        GenerateReport.getInstance().extentTest.log(LogStatus.PASS,"Popup declined");
        bmsHomePageActions.selectMumbai();
        GenerateReport.getInstance().extentTest.log(LogStatus.PASS,"City selected");
        sportsPageActions.navigateToSports();
        GenerateReport.getInstance().extentTest.log(LogStatus.PASS,"Navigated to sports");
        sportsPageActions.selectThisWeekend();
        GenerateReport.getInstance().extentTest.log(LogStatus.PASS,"Weekend selected");
        GenerateReport.getInstance().attachScreenShot("Sports Page");
        HashMap<String, Integer> events= sportsPageActions.getEvents();
        GenerateReport.getInstance().extentTest.log(LogStatus.PASS,"Stored all the Events");
        HashMap<String, Integer> sortedEvents= sportsPageActions.sortPrices(events);
        GenerateReport.getInstance().extentTest.log(LogStatus.PASS,"Sorted All Events");
        sportsPageActions.printEventWithPrice(sortedEvents);
        GenerateReport.getInstance().extentTest.log(LogStatus.PASS,"Printed Event Dates");
        GenerateReport.getInstance().attachScreenShot("Sports Page");



    }
}
