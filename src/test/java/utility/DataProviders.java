package test.java.utility;

import org.testng.annotations.DataProvider;

import java.lang.reflect.Method;

public class DataProviders {

    @DataProvider(name = "Executor")
    public Object[][] driverMethodData(Method m) {
        System.out.println("reached after data extraction"+m.getName());
        Object[][] dataObject = ReadExcel.getInstance().getDataObject("TestSheet",m.getName());

        return dataObject;
    }
}
