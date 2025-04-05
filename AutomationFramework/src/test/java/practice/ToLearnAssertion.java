package practice;

import org.testng.Assert;
import org.testng.asserts.SoftAssert;

public class ToLearnAssertion {
		
	public void sample() {
		
		System.out.println("...Step 1...");
		System.out.println("...Step 2...");
		
		//Validation using HardAssert
		//Assert.assertEquals(false, true);
		
		//Validation SoftAssert
		SoftAssert sa = new SoftAssert();
		sa.assertEquals(false, true);//failed
		
		
		System.out.println("...Step 3...");
		System.out.println("...Step 4...");
		
		sa.assertAll();
	}
}
