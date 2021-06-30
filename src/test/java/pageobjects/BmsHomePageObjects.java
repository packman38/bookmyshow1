package test.java.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.PageFactory;

import java.util.ArrayList;
import java.util.List;

public class BmsHomePageObjects {
     static WebDriver driver;

    public BmsHomePageObjects(WebDriver driver){
        this.driver=driver;
        PageFactory.initElements(driver,this);
    }
    @FindBy(id="wzrk-cancel")
    public static List<WebElement> btnNotNowInPopup;

    @FindBy(xpath="//img[@alt='MUMBAI']")
    public static List<WebElement> imgCityMumbai;


    @FindBy(xpath="//img[@alt='BANG']")
    public static List<WebElement> imgCityBlr;

    @FindBy(xpath = "//div[text()='Sign in']")
    public static  WebElement btnSignIn;

    @FindBy(xpath = "//div[text()='Continue with Email']")
    public static WebElement btnCtnWithEmail;

    @FindBy(xpath = "//div[text()='Continue with Google']/..")
    public static WebElement btnCtnWithGmail;


    @FindBy(id="emailId")
    public static WebElement txtBoxEmail;

    @FindBy(xpath = "//button[text()='Continue']")
    public static WebElement btnContinue;






}
