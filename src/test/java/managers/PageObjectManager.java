package test.java.managers;


import org.openqa.selenium.WebDriver;
import test.java.pageactions.*;
import test.java.pageobjects.BmsHomePageObjects;
import test.java.pageobjects.MoviesPageObjects;
import test.java.pageobjects.SportsPageObjects;

public class PageObjectManager {
    private WebDriver driver;
    private BmsHomePageObjects bmsHomePageObjects;
    private static PageObjectManager pageObjectManager=null;
    private BmsHomePageActions getBmsHomePageActions;
    private MoviesPageObjects moviesPageObject;
    private MoviesPageActions moviesPageActions;
    private SportsPageObjects sportsPageObjects;
    private SportsPageActions sportsPageActions;

    private PageObjectManager() {
        if(driver==null){
            driver= WebDriverManager.getDriver();
        }


    }


    public static PageObjectManager getInstance(){
        if(pageObjectManager==null){
            pageObjectManager=new PageObjectManager();
        }
        return pageObjectManager;
    }



    public BmsHomePageObjects getBmsHomePageObjects() {
        return (bmsHomePageObjects==null)?bmsHomePageObjects=new BmsHomePageObjects(driver): bmsHomePageObjects;

    }
    public BmsHomePageActions getBmsHomePageActions(){
        return (getBmsHomePageActions==null)?getBmsHomePageActions=new BmsHomePageActions(driver): getBmsHomePageActions;
    }
    public MoviesPageObjects getMoviesPageObject(){
        return (moviesPageObject==null)?moviesPageObject= new MoviesPageObjects(driver):moviesPageObject;
    }

    public MoviesPageActions getMoviesPageActions(){
        return  moviesPageActions==null?moviesPageActions=new MoviesPageActions(driver):moviesPageActions;
    }

    public SportsPageActions getSportsPageActions(){
        return sportsPageActions==null?sportsPageActions=new SportsPageActions(driver):sportsPageActions;
    }

    public SportsPageObjects getSportsPageObjects(){
        return sportsPageObjects==null?sportsPageObjects= new SportsPageObjects(driver):sportsPageObjects;
    }
}
