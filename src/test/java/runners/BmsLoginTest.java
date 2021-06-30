package test.java.runners;

import com.relevantcodes.extentreports.LogStatus;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import test.java.managers.WebDriverManager;
import test.java.pageactions.BmsHomePageActions;
import test.java.pageobjects.BmsHomePageObjects;
import test.java.utility.BaseClass;
import test.java.utility.GenerateReport;

public class BmsLoginTest extends BaseClass {

    BmsHomePageObjects bmsHomePageObjects;
    BmsHomePageActions bmsHomePageActions;
    WebDriver driver;


    @BeforeTest
    public void setUpPage() {
        bmsHomePageObjects = pageObjectManager.getBmsHomePageObjects();
        bmsHomePageActions = pageObjectManager.getBmsHomePageActions();

       driver = WebDriverManager.getDriver();
    }

    @Test(testName = "OpenBookMyShow")
    public void launch(){
        bmsHomePageActions.navigateToBMS();
        GenerateReport.getInstance().extentTest.log(LogStatus.PASS,"BMS launcehed");
        bmsHomePageActions.dismissPersonalizedUpdates();
        GenerateReport.getInstance().extentTest.log(LogStatus.PASS,"Popup declined");
        bmsHomePageActions.selectMumbai();
        GenerateReport.getInstance().extentTest.log(LogStatus.PASS,"City selected");
        String errorText=bmsHomePageActions.loginNeg();
        GenerateReport.getInstance().extentTest.log(LogStatus.PASS,"Login failed: "+errorText);
        GenerateReport.getInstance().attachScreenShot(errorText);


    }

}
