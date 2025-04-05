package practice;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

public class ToCreatContact4 {

	private static final WebElement SignOut = null;

	public static void main(String[] args) {
	WebDriver driver = new ChromeDriver();
	driver.manage().window().maximize();
	driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
	driver.get("http://localhost:8888/");
	
	driver.findElement(By.name("user_name")).sendKeys("admin");
	 driver.findElement(By.name("user_password")).sendKeys("password");
	 driver.findElement(By.id("submitButton")).click();
	 
	 driver.findElement(By.linkText("Organizations")).click();
		driver.findElement(By.xpath("//img[@alt='Create Organization...']")).click();
		driver.findElement(By.name("accountname")).sendKeys("myntra");
		
		//driver.findElement(By.name("accounttype")).click();
		
		//  WebElement createOrg = driver.findElement(By.id("createOrgBtn")); 
	      //  createOrg.click();

	        
	      //  WebElement orgName = driver.findElement(By.id("orgName"));
	      //  orgName.sendKeys("Test Organization");

	        // Select "Energy" in  Industry dropdown
	        WebElement industryDropdown = driver.findElement(By.name("industry"));
	        Select industrySelect = new Select(industryDropdown);
	        industrySelect.selectByVisibleText("Energy");

	        // Select "Customer" in type dropdown
	        WebElement typeDropdown = driver.findElement(By.name("accounttype"));
	        Select typeSelect = new Select(typeDropdown);
	        typeSelect.selectByVisibleText("Customer");

	        // Click Save button
	         driver.findElement(By.name("button")).click();
	        

	        // Handle alert pop-up 
	        try {
	            driver.switchTo().alert().accept();
	            System.out.println("Alert Handled: Organization Name Already Exists!");
	        } catch (Exception e) {
	            System.out.println("No alert found.");
	        }

	        // Logout
	        driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']"));
	    	Actions action = new Actions(driver);
	    	action.moveToElement(SignOut).perform();
	    	driver.findElement(By.linkText("Sign Out")).click();
	    	
	    	//Step 8:- close the browser
	    	driver.quit();
	    }

	}


