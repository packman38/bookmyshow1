package test.java.pageactions;

import com.gargoylesoftware.htmlunit.javascript.background.JavaScriptExecutor;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import test.java.pageobjects.BmsHomePageObjects;
import test.java.utility.GenerateReport;
import test.java.utility.ReadExcel;

import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class BmsHomePageActions {
    WebDriver driver;

    public BmsHomePageActions(WebDriver driver){
        this.driver=driver;
        PageFactory.initElements(driver,this);

    }
    public void navigateToBMS(){
        driver.get("https://in.bookmyshow.com/");
    }
    public void dismissPersonalizedUpdates(){
        if(BmsHomePageObjects.btnNotNowInPopup.size()>0) {
            BmsHomePageObjects.btnNotNowInPopup.get(0).click();
        }
    }
    public String loginNeg(){
        String errorText=null;
        BmsHomePageObjects.btnSignIn.click();
        System.out.println(BmsHomePageObjects.btnCtnWithGmail.getText());
        BmsHomePageObjects.btnCtnWithGmail.click();
        try {
            Thread.sleep(15000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        JavascriptExecutor js = (JavascriptExecutor)driver;
        js.executeScript("arguments[0].click();", BmsHomePageObjects.btnCtnWithGmail);
//        BmsHomePageObjects.btnCtnWithEmail.click();
//        BmsHomePageObjects.btnCtnWithGmail.click();
        System.out.println("Click gmail");
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        String parent = driver.getWindowHandle();
        Set<String> wh=driver.getWindowHandles();
        System.out.println(parent);
        Iterator<String> wi=wh.iterator();
        while(wi.hasNext())
        {
            String ChildWindow=wi.next();
            String loginId= ReadExcel.getInstance().getColData("TestSheet","loginId");
            System.out.println(loginId);

            if(!parent.equalsIgnoreCase(ChildWindow)) {

                // Switching to Child window
                driver.switchTo().window(ChildWindow);
                driver.findElement(By.name("identifier"))
                        .sendKeys(loginId);

                driver.findElement(By.xpath("//span[text()='Next']/..")).click();

                WebElement el= driver.findElement(By.xpath("//div[contains(text(),'find your Google Account')]"));
                js.executeScript("arguments[0].click();", el);
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                errorText=el.getText();
                GenerateReport.getInstance().attachScreenShot(errorText);
            }
        }
        driver.switchTo().window(parent);

//        BmsHomePageObjects.txtBoxEmail.sendKeys("dsarji@gmail.com");
//        BmsHomePageObjects.btnContinue.click();
//        List<WebElement> otpTestBox = driver.findElements(By.xpath("//div/input[@type='tel']"));
//        for(WebElement e: otpTestBox){
//            e.sendKeys("7");
//        }
        System.out.println(errorText);
        return errorText;
    }
    public void selectMumbai(){
        if(BmsHomePageObjects.imgCityMumbai.size()>0)
        BmsHomePageObjects.imgCityMumbai.get(0).click();
    }

    public void selectMBlr(){
        if(BmsHomePageObjects.imgCityBlr.size()>0)
            BmsHomePageObjects.imgCityBlr.get(0).click();
    }
}
