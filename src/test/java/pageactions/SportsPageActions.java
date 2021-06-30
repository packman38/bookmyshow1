package test.java.pageactions;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import test.java.pageobjects.SportsPageObjects;

import java.util.*;

public class SportsPageActions {

    WebDriver driver;
    public SportsPageActions(WebDriver driver){
        this.driver=driver;
        PageFactory.initElements(driver,this);
    }

    public void navigateToSports(){
        SportsPageObjects.linkSports.click();
    }

    public  void selectThisWeekend(){
        SportsPageObjects.btnWeekEnd.click();
    }

    public HashMap<String, Integer> getEvents(){

        List<WebElement> eventNames= driver.findElements(By.xpath("//div[contains(@class,'style__VerticalCardContainer')]/div/div[contains(@class,'commonStyles__CardTextBox')][1]/div"));
        List<WebElement> eventPrices= driver.findElements(By.xpath("//div[contains(@class,'style__VerticalCardContainer')]/div/div[contains(@class,'commonStyles__CardTextBox')][4]/div"));
        HashMap<String, Integer> events= new HashMap<String, Integer>();
        for(int i=0;i<eventNames.size();i++){
            String s[]= eventPrices.get(i).getText().split(" ");

            int sPrice=Integer.parseInt(s[1]);
            events.put(eventNames.get(i).getText(),sPrice);
        }
        System.out.println(events);


        //sortPrices(events);
        return events;

    }

    public HashMap<String,Integer> sortPrices(HashMap<String,Integer> events){
        List<Map.Entry<String, Integer> > list =
                new LinkedList<Map.Entry<String, Integer> >(events.entrySet());


        Collections.sort(list, new Comparator<Map.Entry<String, Integer> >() {
            public int compare(Map.Entry<String, Integer> o1,
                               Map.Entry<String, Integer> o2)
            {
                return (o1.getValue()).compareTo(o2.getValue());
            }
        });


        HashMap<String, Integer> temp = new LinkedHashMap<String, Integer>();
        for (Map.Entry<String, Integer> aa : list) {
            temp.put(aa.getKey(), aa.getValue());
        }
        System.out.println(temp);
        return temp;

    }

    public void printEventWithPrice(HashMap<String, Integer> events){
        for(String e: events.keySet()){
            System.out.println(e);
            if(e.contains("'")){
                String newName[]= e.split("'");
                if(newName[0].length()>newName[1].length()){
                    e=newName[0];
                }else{
                    e=newName[1];
                }

            }
            String xpath="//div[contains(text(),'"+e+"')]";
            String dt;
            try {
                Thread.sleep(2000);
                driver.findElement(By.xpath(xpath)).click();

               dt = driver.findElement(By.xpath("//div[contains(text(),' 2021 - ')]")).getText();
            }catch(Exception ex){
                dt=  "Date not available";
            }
            System.out.println(dt);
            driver.navigate().back();
        }

    }

}
