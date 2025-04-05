package practice;

import java.time.Duration;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

public class ToCreateContact5 {



	private static final WebElement SignOut = null;

	public static void main(String[] args) {
		
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.get("http://localhost:8888/");
		
		 driver.findElement(By.name("user_name")).sendKeys("admin");
		 driver.findElement(By.name("user_password")).sendKeys("password");
		 driver.findElement(By.id("submitButton")).click();
		 
		 driver.findElement(By.linkText("Contacts")).click();
		 driver.findElement(By.xpath("//img[@alt='Create Contact...']")).click();
		 driver.findElement(By.name("lastname")).sendKeys("sahoo");
		 driver.findElement(By.xpath("//img[@src='themes/softed/images/select.gif'])[1]")).click();
		 
		Set<String> allIds = driver.getWindowHandles();
		 String parentId = driver.getWindowHandle();
		 allIds.remove(parentId);
		 for(String id : allIds)
		 {
			 driver.switchTo().window(id);
			 driver.findElement(By.linkText("vtiger")).click();
		 }
			 
			 driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();
			 String lastname = driver.findElement(By.xpath("//span[@class='dvHeaderText']")).getText();
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


