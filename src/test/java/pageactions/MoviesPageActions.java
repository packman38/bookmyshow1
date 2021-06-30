package test.java.pageactions;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import test.java.pageobjects.MoviesPageObjects;

import java.util.ArrayList;
import java.util.List;

public class MoviesPageActions {

      WebDriver driver;

    public MoviesPageActions(WebDriver driver){
        this.driver=driver;
        PageFactory.initElements(driver,this);
    }

    public void navigateToMovies(){
        MoviesPageObjects.linkMovies.click();
        MoviesPageObjects.imgContinue.click();
    }

    public List<String> getLanguage(){
        List<String> langs= new ArrayList<String>();
        List<WebElement> langElements= driver.findElements(By.xpath("//div[contains(text(),'Language')]/../../div[contains(@class,'style__FilterBody')]/div"));
        for(WebElement e: langElements){
            langs.add(e.getText());
            System.out.println(e.getText());
        }

        return langs;

    }

}
