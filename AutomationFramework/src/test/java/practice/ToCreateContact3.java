package practice;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

public class ToCreateContact3 {

	

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
			driver.findElement(By.name("accountname")).sendKeys("myntra");
			
			driver.findElement(By.name("industry")).click();
			 WebElement dropdown = driver.findElement(By.xpath("//option[@value='Chemicals']"));
			 Select Chemicals = new Select(dropdown);
			 Chemicals.selectByVisibleText("zinc");
			 System.out.println(Chemicals.isMultiple());
			 
			 
			 driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();
				String organization = driver.findElement(By.xpath("//span[@class='dvHeaderText']")).getText();

				
				if(organization.contains("myntra")) {
					System.out.println(organization+"-----passed");
				}else {
					System.out.println("faitled to create organization");
					
				}
				
				WebElement logout = driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']"));
				Actions action = new Actions(driver);
				action.moveToElement(logout).perform();
				driver.findElement(By.linkText("Sign Out")).click();
				driver.quit();
				
			 
			 
	}

}
