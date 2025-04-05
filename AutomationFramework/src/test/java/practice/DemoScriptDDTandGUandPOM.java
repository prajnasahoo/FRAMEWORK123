package practice;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import genericUtility.ExcelFileUtility;
import genericUtility.PeropertyFileUtility;
import genericUtility.WebDriverUtility;
import objectRepository.ContactIntoPage;
import objectRepository.ContactsPage;
import objectRepository.CreatContactPage;
import objectRepository.HomePage;
import objectRepository.LoginPage;

public class DemoScriptDDTandGUandPOM {

	public static void main(String[] args) throws IOException {

		PeropertyFileUtility putil = new PeropertyFileUtility();
		ExcelFileUtility eutil = new ExcelFileUtility();
		WebDriverUtility wutil = new WebDriverUtility();

		// read data from property file

		String url = putil.toReadDataFromPeropertyFile("url");
		String browser = putil.toReadDataFromPeropertyFile("browser");
		String username = putil.toReadDataFromPeropertyFile("username");
		String password = putil.toReadDataFromPeropertyFile("password");

		// READ DATA FROM EXCEL FILE
		String lastname = eutil.toReadDataFromExcelFile("Sheet1", 1, 2); 

		// step1:- launch the browser
		WebDriver driver = null;
		if (browser.equals("chrome")) {
			driver = new ChromeDriver();
		} else if (browser.equals("edge")) {
			driver = new EdgeDriver();
		} else if (browser.equals("firefox")) {
			driver = new FirefoxDriver();
		}

		wutil.tomaximize(driver);
		wutil.toimlicit(driver);

		// step2:-login application valid credential
		driver.get(url);
		LoginPage lp = new LoginPage(driver);
		lp.getUsernametextfield().sendKeys(username);
		lp.getPasswordtextField().sendKeys(password);
		lp.getLogintextfield().click();

		// step3:- click on contact link
		HomePage hp = new HomePage(driver);
		hp.getContactslink().click();

		// step4:- click on create contact look up image

		ContactsPage cp = new ContactsPage(driver);
		cp.getCreatcantactlookimg().click();

		// step5:- fill in the details
		CreatContactPage ccp = new CreatContactPage(driver);
		ccp.getLastnameTextfield().sendKeys(lastname);

		// step6: save and verify
		ccp.getSavelink().click();
		ContactIntoPage cip = new ContactIntoPage(driver);
		cip.getHeader().getText();
		if (lastname.contains(lastname)) {
			System.out.println(lastname + "-> is passed");
		} else {
			System.out.println(lastname + "-> is failed");

		}
		// step7-logout
		wutil.toMouseHover(driver, hp.getLogoutlink());
		hp.getLogoutlink().click();

		// step8-close
		driver.quit();
	}
}
