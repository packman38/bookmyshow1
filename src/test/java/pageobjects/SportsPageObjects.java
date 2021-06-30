package test.java.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SportsPageObjects {


    WebDriver driver;
    public SportsPageObjects(WebDriver driver){
        this.driver=driver;
        PageFactory.initElements(driver,this);
    }

    @FindBy(xpath = "//a[text()='Sports']")
    public static WebElement linkSports;

    @FindBy(xpath = "//div[contains(@class,'DesktopFilterIte')]/div/div[text()='This Weekend']")
    public static WebElement btnWeekEnd;

}
