package ObjectRepository1;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CreateInfoPage {
	
		
		//constructor
		public  CreateInfoPage(WebDriver driver) {
			PageFactory.initElements(driver, this);
			
		}
		
		@FindBy(xpath="//span[@class='dvHeaderText']")
		private WebElement contactInfo;

		public WebElement getContactInfo() {
			return contactInfo;
		}
}
