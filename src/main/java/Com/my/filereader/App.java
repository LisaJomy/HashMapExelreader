package Com.my.filereader;

import java.io.File;
import java.io.FileInputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) throws Exception
    {
    	WebDriverManager.chromedriver().setup();
    	WebDriver driver=new ChromeDriver();
    	driver.get("http:app.hubspot.com/login");
    	driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
    	
    	doLogin( driver,getMapData(),"Doctor");	
    }
	public static final String EXELFILELOCATION="C:\\Users\\user\\eclipse-workspace\\yes-m system\\Seleniumfilereader\\src\\main\\java\\Com\\my\\filereader\\Hospital.xlsx";

	
	private static FileInputStream fis;
	private static XSSFWorkbook workbook;
	private static XSSFSheet sheet;
	private static XSSFRow row;
	
	public static void loadExcel() throws Exception{
	
	System.out.println("Loading Exel data...");
	
	File file = new File(EXELFILELOCATION);
	fis = new FileInputStream(file);
	workbook =new XSSFWorkbook(fis);
	sheet= workbook.getSheet("Testdata");
	fis.close();
	
	}
	 public static Map<String, String > getMapData() throws Exception {
	        if (sheet == null){
	            loadExcel();
	        }
	        Map<String, String> myMap = new HashMap<String, String>();

	        //for (int i = 1 ; i < sheet.getLastRowNum()+1 ; i++)
	      for (int i = 0 ; i < sheet.getLastRowNum() ; i++)
	        {
	            row = sheet.getRow(i+1);
	            String key = row.getCell(0).getStringCellValue();
	            
	            int colNumber = row.getLastCellNum();
	            for (int j = 0 ; j < colNumber ; j++){
	                String value = row.getCell(j).getStringCellValue();
	                myMap.put(key, value);
	            }
	        }
	        return myMap; 
}
public static void doLogin(WebDriver driver,Map<String,String>myMap,String key) {
driver.findElement(By.id("username")).sendKeys(myMap.get(key).split("_")[0]);
driver.findElement(By.id("password")).sendKeys(myMap.get(key).split("_")[1]);
}	 

}
