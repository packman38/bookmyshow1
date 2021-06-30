package test.java.managers;

import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class WebDriverManager {
    private  static WebDriver driver;
    private long waitTime;
    public static WebDriver getDriver() {
        if(driver==null){
            driver= createDriver();
        }
        return driver;
    }

    private static WebDriver createDriver() {
        WebDriver dv=null;
        if(ConfigFileManager.getInstance().getMode().equalsIgnoreCase("grid")){
            try {
                dv= createRemoteDriver();
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
        }else dv= createLocalDriver();

        return dv;
    }

    private static WebDriver createLocalDriver() {
        String browserName=ConfigFileManager.getInstance().getBrowserName();
//        waitTime= ConfigFileManager.getInstance().getImplicitWait();
        switch (browserName){
            case "chrome":
                System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "/BrowserDrivers/chromedriver91.exe");
                driver= new ChromeDriver();
                driver.manage().timeouts().implicitlyWait(ConfigFileManager.getInstance().getImplicitWait(), TimeUnit.SECONDS);
                if(ConfigFileManager.getInstance().getMaximizedStatus()){
                    driver.manage().window().maximize();
                }
                break;
            case "firefox":
                System.setProperty("webdriver.gecko.driver",System.getProperty("user.dir") + "/BrowserDrivers/geckodriver.exe");
                driver= new FirefoxDriver();

                driver.manage().timeouts().implicitlyWait(ConfigFileManager.getInstance().getImplicitWait(), TimeUnit.SECONDS);
                if(ConfigFileManager.getInstance().getMaximizedStatus()){
                    driver.manage().window().maximize();
                }
                break;
            default: throw new RuntimeException(browserName+" browser not implemented yet")   ;



        }
        return driver;
    }

    private static WebDriver createRemoteDriver() throws MalformedURLException {
        String browserName=ConfigFileManager.getInstance().getBrowserName();
        DesiredCapabilities capability;
//        waitTime= ConfigFileManager.getInstance().getImplicitWait();
        switch (browserName){

            case "chrome":

//                capability.setPlatform(Platform.WIN10);
                ChromeOptions chromeOptions = new ChromeOptions();
                capability = DesiredCapabilities.chrome();

                URL uri= new URL("http://localhost:5555/wd/hub");
                driver = new RemoteWebDriver(uri, capability);
//                System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "/BrowserDrivers/chromedriver91.exe");

                driver.manage().timeouts().implicitlyWait(ConfigFileManager.getInstance().getImplicitWait(), TimeUnit.SECONDS);
                if(ConfigFileManager.getInstance().getMaximizedStatus()){
                    driver.manage().window().maximize();
                }
                break;
            case "firefox":

                capability = DesiredCapabilities.firefox();
                capability.setBrowserName("firefox");
                capability.setPlatform(Platform.WIN10);
                driver = new RemoteWebDriver(new URL("http://localhost:5454/wd/hub"), capability);

                driver.manage().timeouts().implicitlyWait(ConfigFileManager.getInstance().getImplicitWait(), TimeUnit.SECONDS);
                if(ConfigFileManager.getInstance().getMaximizedStatus()){
                    driver.manage().window().maximize();
                }
                break;
            default: throw new RuntimeException(browserName+" browser not implemented yet")   ;



        }
        return driver;
    }
}
