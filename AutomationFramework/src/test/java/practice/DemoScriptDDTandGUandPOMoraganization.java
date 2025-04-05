package practice;

import java.io.IOException;
import java.util.Random;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import genericUtility.ExcelFileUtility;
import genericUtility.PeropertyFileUtility;
import genericUtility.WebDriverUtility;
import objectRepository.CreateOrganizationPage;
import objectRepository.HomePage;
import objectRepository.LoginPage;
import objectRepository.OrganizationInfoPage;
import objectRepository.OrganizationPage;

public class DemoScriptDDTandGUandPOMoraganization {

	public static void main(String[] args) throws IOException {
		
		PeropertyFileUtility putil = new PeropertyFileUtility();
		ExcelFileUtility eutil = new ExcelFileUtility();
		WebDriverUtility wutil = new WebDriverUtility();

		String url = putil.toReadDataFromPeropertyFile("url");
		String browser = putil.toReadDataFromPeropertyFile("browser");
		String username = putil.toReadDataFromPeropertyFile("username");
		String password = putil.toReadDataFromPeropertyFile("password");
		
		String organization = eutil.toReadDataFromExcelFile("Sheet2", 1, 2);

		
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
		
		driver.get(url);
		LoginPage lp = new LoginPage(driver);
		lp.getUsernametextfield().sendKeys(username);
		lp.getPasswordtextField().sendKeys(password);
		lp.getLogintextfield().click();

		HomePage hp = new HomePage(driver);
		hp.getOrganizationslink().click();
		
		OrganizationPage op = new OrganizationPage(driver);
		op.getOrganizationlookupimage().click();
		
		
		Random r = new Random();
		int random = r.nextInt();
		
		CreateOrganizationPage cop= new CreateOrganizationPage(driver);
		cop.getOrganizationname().sendKeys(organization+random);
		cop.getIndustryDropDown().click();
		cop.getTypeDropDown().click();
		cop.getAssignedDropDown().click();
		
		cop.getSaveButton().click();
		OrganizationInfoPage oip = new OrganizationInfoPage(driver);
		oip.getOrginfo().getText();
		if(organization.contains(organization)) {
			System.out.println(organization+"passed");
		}
		else {
			System.out.println(organization+"failed");
		}
		wutil.toMouseHover(driver, hp.getLogoutlink());
		hp.getLogoutlink().click();
		
		driver.quit();
		}
			
	}


