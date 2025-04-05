package practice;

import java.time.Duration;
import java.util.Random;

import javax.swing.Action;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

public class ToCreateContact2 {


	public static void main(String[] args) {
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.get("http://localhost:8888/");
		
		 driver.findElement(By.name("user_name")).sendKeys("admin");
		 driver.findElement(By.name("user_password")).sendKeys("password");
		 driver.findElement(By.id("submitButton")).click();
		 
		 driver.findElement(By.linkText("Organizations")).click();
		 
		driver.findElement(By.xpath("//img[@alt='Create Organization...']")).click();
		
		Random r = new Random();
		int random = r.nextInt(1000);
		
		driver.findElement(By.name("accountname")).sendKeys("amazon"+random);
		
		driver.findElement(By.name("button")).click();
	
		String organizationname = driver.findElement(By.xpath("//span[@class='dvHeaderText']")).getText();
		
		if(organizationname.contains("amazon"+random)) {
			System.out.println(organizationname+"-----passed");
		}else {
			System.out.println("failed to create organization");
			
		}
		WebElement signout = driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']"));
		Actions action = new Actions(driver);
		action.moveToElement(signout).perform();
		driver.findElement(By.linkText("Sign Out")).click();
		driver.quit();
				
				
		
	}

}