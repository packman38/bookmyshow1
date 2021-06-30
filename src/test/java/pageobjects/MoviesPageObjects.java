package test.java.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class MoviesPageObjects {
    WebDriver driver;
    public MoviesPageObjects(WebDriver driver){
        this.driver=driver;
        PageFactory.initElements(driver,this);
    }

    @FindBy(xpath = "//a[text()='Movies']")
    public static WebElement linkMovies;

    @FindBy(xpath = "(//div[contains(@class,'style__WidgetContainerBody')])[1]")
    public static WebElement imgContinue;



}
