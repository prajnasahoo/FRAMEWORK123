package contactTests;

import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import ObjectRepository1.CreatContactPage;
import ObjectRepository1.CreateInfoPage;
import ObjectRepository1.HomePage;
import ObjectRepository1.contactPage;
import genericUtility.BaseClass;
import genericUtility.excelFielUtility;

@Listeners(genericUtility.ListenersImpementation.class)
public class ToCreatContactTest extends BaseClass {

	@Test(groups = "smoke")

	public void toCreateContact_001() throws EncryptedDocumentException, IOException {
		HomePage hp = new HomePage(driver);
		hp.getContactsLink().click();
		contactPage cp = new contactPage(driver);
		cp.getCreateLookupimagelink().click();

		CreatContactPage ccp = new CreatContactPage(driver);
		excelFielUtility eutil = new excelFielUtility();
		String LASTNAME = eutil.toReadDataFromExcelFile("Sheet1", 1, 2);
		ccp.getLastnameTextfield().sendKeys(LASTNAME);
		ccp.getSaveButton().click();
		// Fail
		//Assert.fail();
		CreateInfoPage cip = new CreateInfoPage(driver);
		String name = cip.getContactInfo().getText();

		// we use assert isted of doing if else condition
		Assert.assertTrue(name.contains(LASTNAME));
//	  if(name.contains(LASTNAME)) {
//		  System.out.println(name+"...............Passed");
//	  }else {
//		  System.out.println(name+"...............Faield");
//	  }
//	    

	}

}
