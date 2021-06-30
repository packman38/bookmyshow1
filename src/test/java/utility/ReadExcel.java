package test.java.utility;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;

import java.io.*;
import java.util.Hashtable;
import java.util.List;

public class ReadExcel {
	static File file ;
	InputStream fis;
	static ReadExcel instance;
	HSSFWorkbook workbook;
	HSSFSheet sheet;
	HSSFRow row;
	HSSFCell cell;

public ReadExcel(){
 		try{
			 file = new File("src/test/resources/TestData.xls");
//			file = new File("TestSuites/FirstTestSuite.xls");
			 fis= new FileInputStream(file);
			 workbook= new HSSFWorkbook(fis);
		 }catch (Exception e){
 			System.out.println("file reading error check excel present in path");
		 }
}


public static ReadExcel getInstance(){
	if(instance==null){
		instance= new ReadExcel();
	}

	return instance;
}

	public  Object[][] ExcelReader( String SheetName) {

		Object object[][] = null;
		try {

			fis = new FileInputStream(file);

			HSSFWorkbook workbook = new HSSFWorkbook(fis);
			HSSFSheet sheet = workbook.getSheet(SheetName);
			HSSFRow row;
			object = new Object[sheet.getLastRowNum()][4];

			for (int i = 0; i < sheet.getLastRowNum(); i++) {
				row = sheet.getRow(i + 1);
				for (int j = 0; j < row.getLastCellNum(); j++) {
					object[i][j] = row.getCell(j, Row.CREATE_NULL_AS_BLANK).toString();

				}
			}
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return object;
	}



	public int getLastRowNo(String sheetName){
		sheet = workbook.getSheet(sheetName);

		int  lastRowNo= sheet.getLastRowNum();


	return lastRowNo;
	}

	public int getLastColNo(String sheetName){
		sheet = workbook.getSheet(sheetName);
		row= sheet.getRow(sheet.getLastRowNum());
		int  lastColNo= row.getLastCellNum();
		return lastColNo;

	}

	public String getDataFromCell( String sheetName, int rowNo, int colNo ) {




		sheet = workbook.getSheet(sheetName);
		row = sheet.getRow(rowNo);
		return row.getCell(colNo).getStringCellValue();
	}

	public Object[][] getDataObject1(String sheetName, String methodName){


	int rowCounter=0;
	int firstRowForMethod=0;

	sheet = workbook.getSheet(sheetName);
		for(int i= 1;  i<=sheet.getLastRowNum(); i++){
			row= sheet.getRow(i);
			cell= row.getCell(0);
			Hashtable<String, String> dataTable = new Hashtable<String, String>();
			if(cell.getStringCellValue().equalsIgnoreCase(methodName)) {
				if (firstRowForMethod == 0) {
					firstRowForMethod = i;
				}
				rowCounter += 1;
			}
		}
		Object dataObject[][] = new Object[rowCounter][1];
	for(int i= firstRowForMethod;  i<=firstRowForMethod+rowCounter-1; i++){
		HSSFRow row1= sheet.getRow(0);
		row= sheet.getRow(i);
		cell= row.getCell(0);
		Hashtable<String, String> dataTable = new Hashtable<String, String>();
		if(cell.getStringCellValue().equalsIgnoreCase(methodName)){
			for(int j=1; j< row.getLastCellNum(); j++){

				dataTable.put(getDataFromCell(sheetName,0,j),getDataFromCell(sheetName,i,j));
			}
			dataObject[i-firstRowForMethod][0]=dataTable;
		}
	}
	return dataObject;
	}

	public boolean getRunStatus(String scenarioName, String caseId){
	boolean runStatus=false;
		instance= new ReadExcel();
		instance.sheet=instance.workbook.getSheet("Controller");
		int i;
		for(i=1;i< instance.getLastRowNo("Controller");i++){

			if(instance.sheet.getRow(i).getCell(0).getStringCellValue().equalsIgnoreCase(scenarioName)){
				if(instance.sheet.getRow(i).getCell(1).getStringCellValue().equalsIgnoreCase(caseId)){
					if(instance.sheet.getRow(i).getCell(3).getStringCellValue().equalsIgnoreCase("Y")){
						runStatus=true;
						break;
					}
				}
			}
		}

	return true;
	}

	public  Object[][] getDataObject(String sheetName, String methodName){
		int rowCounter=0;
		int firstRowForMethod=0;
		Object dataObject[][] = new Object[1][1];


		sheet = workbook.getSheet(sheetName);
		for(int i= 1;  i<=sheet.getLastRowNum(); i++){
			row= sheet.getRow(i);
			cell= row.getCell(0);

			if(getRunStatus(sheetName,cell.getStringCellValue())) {
				for(int j=1;j<row.getLastCellNum();j++){
					if(row.getCell(i).getStringCellValue().equalsIgnoreCase(methodName)){
						dataObject[1][1]=methodName;
						break;
					}
				}
			}
		}

		return dataObject;
	}
	


	public void setData(String sheetName,int rowNo, int colNo, String value){
		sheet = workbook.getSheet(sheetName);

			row= sheet.getRow(rowNo);
			cell= row.getCell(colNo);
			cell.setCellValue(value);
        System.out.println(value);
		try {
			FileOutputStream outFile = new FileOutputStream(new File("src/test/resources/TestData.xls"));

			workbook.write(outFile);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void setColData(String sheetName, String colName, List<String> values) {
		int colNo = -1;
		sheet = workbook.getSheet(sheetName);
		row = sheet.getRow(0);
		for (int j = 0; j < row.getLastCellNum(); j++) {
			if (row.getCell(j).getStringCellValue().equalsIgnoreCase(colName)) {
				colNo = j;
				break;
			}
		}
		if (colNo == -1) {
			colNo = getLastColNo(sheetName);
			sheet.getRow(0).createCell(colNo).setCellValue(colName);
		}

		int i = 1;

		for (String s : values) {
			sheet.createRow(i++).createCell(colNo).setCellValue(s);
		}
		try {
			FileOutputStream outFile = new FileOutputStream(new File("src/test/resources/TestData.xls"));

			workbook.write(outFile);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	public String getColData(String sheetName, String colName){

		sheet= workbook.getSheet(sheetName);
		row=sheet.getRow(0);
		String value=null;
		for(int j=0;j<row.getLastCellNum();j++){
			if(row.getCell(j).getStringCellValue().trim().equalsIgnoreCase(colName)){

				cell= sheet.getRow(1).getCell(j);
				value= cell.getStringCellValue();
				break;
			}
		}
		return value;



	}



}
