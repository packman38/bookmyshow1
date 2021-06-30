package test.java.managers;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Properties;

public class ConfigFileManager {
    private static ConfigFileManager configFileManager= null;
    private Properties properties;
    private final String proportiesFilePath="\\src\\main\\resources\\config.properties";
    private String propertyValue;
    private ConfigFileManager(){
        BufferedReader reader;
        System.out.println("In config reader");
        try{
            System.out.print(System.getProperty("user.dir"));
            System.out.println(proportiesFilePath);
            reader=new BufferedReader(new FileReader(System.getProperty("user.dir")+proportiesFilePath));
            properties= new Properties();
            properties.load(reader);
            reader.close();


        }catch(Exception E){
            System.out.println(E.getStackTrace());

        }

    }
    public static ConfigFileManager getInstance(){
        return (configFileManager==null)?configFileManager=new ConfigFileManager():configFileManager;
    }

    public String getBrowserName(){
        propertyValue= properties.getProperty("browser");
        if(propertyValue!=null){
            return propertyValue;
        }else{
            throw new RuntimeException("browser not mentioned in Config");
        }

    }

    public boolean getMaximizedStatus(){
        propertyValue=properties.getProperty("windowMaximize");
        if(propertyValue!=null){
            return Boolean.valueOf(propertyValue);
        }else{
            System.out.println("Maximize Status not specified running on Maximized mode");
            return true;
        }
    }

    public long getImplicitWait() {
        propertyValue=properties.getProperty("implicitWait");
        if(propertyValue!=null){
            return Long.parseLong(propertyValue);
        }else{
            System.out.println("Implicit wait not specified running with 1 sec");
            return 1;// in second
        }
    }

    public String getMode() {
        propertyValue=properties.getProperty("mode");
        if(propertyValue!=null){
            return propertyValue;
        }else{
            System.out.println("mode not specified so running on local");
            return "local";
        }
    }
}
