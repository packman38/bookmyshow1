package test.java.runners;

import com.relevantcodes.extentreports.LogStatus;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import test.java.managers.WebDriverManager;
import test.java.pageactions.BmsHomePageActions;
import test.java.pageactions.MoviesPageActions;
import test.java.pageobjects.BmsHomePageObjects;
import test.java.pageobjects.MoviesPageObjects;
import test.java.utility.BaseClass;
import test.java.utility.GenerateReport;
import test.java.utility.Listner;
import test.java.utility.ReadExcel;

import java.util.List;

//@Listeners({Listner.class})
public class MoviesTestCase extends BaseClass {
    WebDriver driver;
    BmsHomePageObjects bmsHomePageObjects;
    BmsHomePageActions bmsHomePageActions;
    MoviesPageActions moviesPageActions;
    MoviesPageObjects moviesPageObject;



    @BeforeTest
    public void setUpPage() {
        bmsHomePageObjects = pageObjectManager.getBmsHomePageObjects();
        bmsHomePageActions = pageObjectManager.getBmsHomePageActions();
        moviesPageActions= pageObjectManager.getMoviesPageActions();
        moviesPageObject=pageObjectManager.getMoviesPageObject();

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
        moviesPageActions.navigateToMovies();
        GenerateReport.getInstance().extentTest.log(LogStatus.PASS,"Navigated to Movies");
        List<String> langs= moviesPageActions.getLanguage();
        GenerateReport.getInstance().extentTest.log(LogStatus.PASS,"Printed languages");
        GenerateReport.getInstance().extentTest.log(LogStatus.INFO,langs.toString());
        GenerateReport.getInstance().attachScreenShot("Movies Page");
        ReadExcel.getInstance().setColData("OutPut","Languages",langs);



    }

}
