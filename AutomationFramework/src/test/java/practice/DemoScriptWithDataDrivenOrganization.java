package practice;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;
import java.util.Random;

import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;

public class DemoScriptWithDataDrivenOrganization {

	public static void main(String[] args) throws IOException {
		
		FileInputStream pfis = new FileInputStream(".//src/test/resources/commonData.properties");
		Properties prop = new Properties();
		prop.load(pfis);
		String browser = prop.getProperty("browser");
		String url = prop.getProperty("url");
		String username = prop.getProperty("username");
		String password = prop.getProperty("password");
		
		FileInputStream efis = new FileInputStream(".//src/test/resources/testDataM8.xlsx");
		Workbook wb = WorkbookFactory.create(efis);
		String organization = wb.getSheet("Sheet2").getRow(1).getCell(2).toString();
		
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
		
		Random r = new Random();
		int random = r.nextInt(1000);
		
		driver.findElement(By.name("accountname")).sendKeys("amazon"+random);
		
		driver.findElement(By.name("button")).click();
	
		String organizationname = driver.findElement(By.xpath("//span[@class='dvHeaderText']")).getText();
		
		if(organizationname.contains("amazon"+random)) {
			System.out.println(organizationname+"-----passed");
		}else {
			System.out.println(organizationname+"-----failed");
			
		}
		WebElement signout = driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']"));
		Actions action = new Actions(driver);
		action.moveToElement(signout).perform();
		driver.findElement(By.linkText("Sign Out")).click();
		
		driver.quit();
		
	}

}
