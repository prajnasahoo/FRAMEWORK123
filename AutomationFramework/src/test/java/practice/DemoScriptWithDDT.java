package practice;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;

public class DemoScriptWithDDT {

	public static void main(String[] args) throws IOException {
		
		//To Read Data from properties
		
		FileInputStream pfis = new FileInputStream("./src/test/resources/commonData.properties");
		Properties prop = new Properties();
		prop.load(pfis);
		String browser = prop.getProperty("browser");
		String url = prop.getProperty("url");
		String username = prop.getProperty("username");
		String password = prop.getProperty("password");
		
		//To Read Data from excleFile
		FileInputStream efis = new FileInputStream(".//src/test/resources/testDataM8.xlsx");
		Workbook wb = WorkbookFactory.create(efis);
		String lastname = wb.getSheet("Sheet1").getRow(1).getCell(2).toString();
		
		//step 1- lunch browser
		WebDriver driver = null;
		if(browser.equals("chrome")) {
			driver= new ChromeDriver();
		}else if(browser.equals("edge")) {
			driver= new EdgeDriver();
		}else if(browser.equals("firefox")) {
			driver= new FirefoxDriver();
		}
		
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
		
		driver.findElement(By.name("user_name")).sendKeys(username);
		driver.findElement(By.name("user_password")).sendKeys(password);
		driver.findElement(By.id("submitButton")).click();
		
		
		driver.findElement(By.linkText("Contacts")).click();
		
		driver.findElement(By.xpath("//img[@title='Create Contact...']")).click();
		
		driver.findElement(By.name("lastname")).sendKeys(lastname);
		
		
		driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();
		driver.findElement(By.xpath("//span[@class='dvHeaderText']")).click();

		
		if(lastname.contains("sahoo")) {
			System.out.println(lastname+"-----passed");
		}else {
			System.out.println(lastname+"-----failed");
			
		}
		
		WebElement signout = driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']"));
		Actions action = new Actions(driver);
		action.moveToElement(signout).perform();
		driver.findElement(By.linkText("Sign Out")).click();
		
		driver.quit();
		
		
		
		
		
		
	}

}
