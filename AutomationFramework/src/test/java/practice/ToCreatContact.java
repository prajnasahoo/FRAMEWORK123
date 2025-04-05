package practice;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

public class ToCreatContact {

	private static final WebElement SignOut = null;
	private static String lastname;

	public static void main(String[] args) {
		
		//Step 1:- Launch Browser
	 WebDriver driver = new ChromeDriver();
	 driver.manage().window().maximize();
	 driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(0));
	 
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
	
	//Step 7:- logout of application
	WebElement logoutEle  = driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']"));
	Actions action = new Actions(driver);
	action.moveToElement(logoutEle).perform();
	driver.findElement(By.linkText("Sign Out")).click();
	
	//Step 8:- close the browser
	driver.quit();
	
	}

}
