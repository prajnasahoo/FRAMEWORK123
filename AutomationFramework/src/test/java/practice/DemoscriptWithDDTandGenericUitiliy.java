package practice;

import java.io.IOException;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import genericUtility.ExcelFileUtility;
import genericUtility.PeropertyFileUtility;
import genericUtility.WebDriverUtility;

public class DemoscriptWithDDTandGenericUitiliy {

	public static void main(String[] args) throws IOException {
		
		
		PeropertyFileUtility putil = new PeropertyFileUtility();
		ExcelFileUtility eutil = new ExcelFileUtility();
		WebDriverUtility wutil = new WebDriverUtility();
		
		
		// Read data from propertyfile
		String url = putil.toReadDataFromPeropertyFile("url");
		String browser = putil.toReadDataFromPeropertyFile("browser");
		String username = putil.toReadDataFromPeropertyFile("username");
		String password = putil.toReadDataFromPeropertyFile("password");
		
		// Read data from excel file
		String lastname = eutil.toReadDataFromExcelFile("Sheet1", 1, 2);
		
		//launch the browser
		WebDriver driver = null;
		if(browser.equals("chrome")) {
			driver= new ChromeDriver();
		}else if(browser.equals("edge")) {
			driver= new EdgeDriver();
		}else if(browser.equals("firefox")) {
			driver= new FirefoxDriver();
		}
		
		wutil.tomaximize(driver);
		wutil.toimlicit(driver);
		
		//Step 2:- Login to application with valid credential
		 driver.get("http://localhost:8888/");
		 driver.findElement(By.name("user_name")).sendKeys("admin");
		 driver.findElement(By.name("user_password")).sendKeys("password");
		 driver.findElement(By.id("submitButton")).click();
		 
		 //Step 3:- Navigate to contact link
		 driver.findElement(By.linkText("Contacts")).click();
		 
		 //Step 4:-click on create contact look up image
		driver.findElement(By.xpath("//img[@title='Create Contact...']")).click();
		
		//Step 5:- Create a contact mandatory file
		driver.findElement(By.name("lastname")).sendKeys("sahoo");
		
		//Step 6:- Save and verify
		driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();
		driver.findElement(By.xpath("//span[@class='dvHeaderText']")).click();

		
		if(lastname.contains("sahoo")) {
			System.out.println(lastname+"-----passed");
		}else {
			System.out.println("faitled to create contact");
			
		}
		//logout
		WebElement logoutEle = driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']"));
		wutil.toMouseHover(driver, logoutEle);//mousehover
		driver.findElement(By.linkText("Sign Out")).click();
		
		driver.quit();
		 
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
	}

}
