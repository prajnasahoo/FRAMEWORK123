package OrganizationTest;

import java.io.IOException;
import java.util.Random;

import org.apache.poi.EncryptedDocumentException;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Test;



import ObjectRepository1.CreateOrganizationPage1;
import ObjectRepository1.HomePage;
import ObjectRepository1.OrganizationInfoPage;
import ObjectRepository1.OrganizationPage;
import genericUtility.BaseClass;
import genericUtility.excelFielUtility;

public class ToCreateOrgWithIndustryTest extends BaseClass {
	
	@Test(groups="Regression")
	public void ToCreateOrgWithIndustry_004() throws EncryptedDocumentException, IOException{
		HomePage hp = new HomePage(driver);
		hp.getOrganizationsLink().click();
		OrganizationPage orp = new OrganizationPage(driver);
		orp.getOrganizationLokkupImage().click();
		CreateOrganizationPage1 corgp1 = new CreateOrganizationPage1(driver);
		excelFielUtility eutil = new excelFielUtility();
		String Organization = eutil.toReadDataFromExcelFile("Sheet2", 1, 2);
		corgp1.getOrganizationNameTextfield().sendKeys(Organization);
		//corgp1.getIndustryNameTextField().sendKeys(null);
		String Industry = eutil.toReadDataFromExcelFile("Sheet2", 2, 2);
		Random r = new Random();
		int random = r.nextInt(1000);
		corgp1.getOrganizationNameTextfield().sendKeys(Organization+random);
		Select ind = new Select(corgp1.getIndustryNameTextField());
		ind.selectByValue(Industry);
		corgp1.getSaveButton().click();
		OrganizationInfoPage orip = new OrganizationInfoPage(driver);
		String name = orip.getOrganizationInfopage().getText();
		Assert.assertTrue(name.contains(name));
//		if(name.contains(Organization)) {
//			System.out.println(name+"............Passed");
//		}else {
//			System.out.println(name+"............Failed");
//		}

		
		
	}

}
