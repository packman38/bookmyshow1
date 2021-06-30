package test.java.utility;

import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;



public class Listner extends TestListenerAdapter {

    ExtentTest extentTest=null;
    @Override
    public void onTestStart(ITestResult tr) {
        System.out.println("test is started");
//        GenerateReport.getInstance().extentTest=GenerateReport.getInstance().reporter.startTest(tr.getTestName());
        extentTest=GenerateReport.getInstance().startTest(tr.getName());


        }

    @Override
    public void onTestSuccess(ITestResult tr) {
        System.out.println(tr.getName()+" test is passed");
//        GenerateReport.getInstance().extentTest.log(LogStatus.PASS,tr.getName());
        extentTest.log(LogStatus.PASS,tr.getName());
    }

    @Override
    public void onTestFailure(ITestResult tr) {
        System.out.println(tr.getName()+" Step is failed");
        GenerateReport.getInstance().extentTest.log(LogStatus.FAIL,tr.getName());
//        GenerateReport.getInstance().attachScreenShot(tr.getName());
        GenerateReport.getInstance().attachFailedScreenShot(tr.getName());
    }

    @Override
    public void onTestSkipped(ITestResult tr) {
        System.out.println(tr.getName()+" test is skipped");
        GenerateReport.getInstance().extentTest.log(LogStatus.SKIP,tr.getName());
    }




}

