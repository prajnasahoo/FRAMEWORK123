package practice;

import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ToReadTestDataFromePropeties {


	public static void main(String[] args) throws EncryptedDocumentException, IOException {
		
		FileInputStream fis = new FileInputStream(".\\/src/test/resources/testDataM8.xlsx");
		Workbook wb = WorkbookFactory.create(fis);
		
		//call method
		String lastname = wb.getSheet("Sheet1").getRow(1).getCell(2).toString();
		System.out.println(lastname);
		
		String amazon = wb.getSheet("Sheet2").getRow(4).getCell(3).toString();	
		System.out.println(amazon);
		}

}
