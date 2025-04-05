package genericUtility;

import java.io.IOException;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentReporter;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ListenersImpementation implements ITestListener {
	
	ExtentReports report;
	ExtentTest test;
	
	 

	  /**
	   * Invoked before running all the test methods belonging to the classes inside the &lt;test&gt; tag
	   * and calling all their Configuration methods.
	   *
	   * @param context The test context
	   */
	  public void onStart(ITestContext context) {
	    System.out.println("database connection established");
	    
	    
	    
	    
	  //ExtentsReports
		
		ExtentSparkReporter htmlReport= new ExtentSparkReporter("./ExtentReports/Reports-"+new JavaUtility().toGetSystemDateAndTime()+".htm");
		htmlReport.config().setDocumentTitle("Vtiger Excution Report");
		htmlReport.config().setTheme(Theme.DARK);
		htmlReport.config().setReportName("Vtiger Excution Report");
		
		
		report = new ExtentReports();
		report.attachReporter(htmlReport);
		report.setSystemInfo("BaseUrl", "http://localhost:8888/");
		report.setSystemInfo("UserName", "admin");
		report.setSystemInfo("Password", "password");
		report.setSystemInfo("ReportName", "prajna");
		
	    
	    
	    
	    
	  }
	
	/**
	   * Invoked each time before a test will be invoked. The <code>ITestResult</code> is only partially
	   * filled with the references to class, method, start millis and status.
	   *
	   * @param result the partially filled <code>ITestResult</code>
	   * @see ITestResult#STARTED
	   */
	public void onTestStart(ITestResult result) {
	      String methodname = result.getMethod().getMethodName();
	      System.out.println(methodname+"Started");
	      test = report.createTest(methodname);//store in variable//extent
	  
	
	
	
	
	
	
	}
	  /**
	   * Invoked each time a test succeeds.
	   *
	   * @param result <code>ITestResult</code> containing information about the run test
	   * @see ITestResult#SUCCESS
	   */
	  public void onTestSuccess(ITestResult result) {
		  String methodname = result.getMethod().getMethodName();
	      System.out.println(methodname+"Test passed");
	      
	     test.log(Status.PASS, methodname+"Test passed");//extent
	  }

	  /**
	   * Invoked each time a test fails.
	   *
	   * @param result <code>ITestResult</code> containing information about the run test
	   * @see ITestResult#FAILURE
	   */
	  public void onTestFailure(ITestResult result) {
		  String methodname = result.getMethod().getMethodName();
	      System.out.println(methodname+"Test failed");
	      
	      test.log(Status.FAIL, methodname+"Test failed");
	      test.log(Status.INFO, result.getThrowable());
	      
	      JavaUtility jutil=new JavaUtility();
	      String screenShotname = methodname+" "+jutil.toGetSystemDateAndTime();
	      
	      WebDriverUtility wutil=new WebDriverUtility();
	      try {
			 String path = wutil.toTakeScreenshot(BaseClass.sDriver, screenShotname);
			 test.addScreenCaptureFromPath(path);//extentTest
			 
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	      
	  }

	  /**
	   * Invoked each time a test is skipped.
	   *
	   * @param result <code>ITestResult</code> containing information about the run test
	   * @see ITestResult#SKIP
	   */
	  public void onTestSkipped(ITestResult result) {
		  String methodname = result.getMethod().getMethodName();
	      System.out.println(methodname+"test skipped");
	      test.log(Status.FAIL, methodname+"test skipped");//extentReport
	      test.log(Status.INFO, result.getThrowable());
	  }

	 

	  /**
	   * Invoked after all the test methods belonging to the classes inside the &lt;test&gt; tag have run
	   * and all their Configuration methods have been called.
	   *
	   * @param context The test context
	   */
	  public void onFinish(ITestContext context) {
	    System.out.println("database connection removed");
	    report.flush();
	  }
	
}
