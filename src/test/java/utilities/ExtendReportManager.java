package utilities;

import java.awt.Desktop;
import java.io.File;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import testBase.BaseClass;

public class ExtendReportManager implements ITestListener {
	// Implement the methods of ITestListener here
	public ExtentSparkReporter sparkReporter;
	public ExtentReports extend;
	public ExtentTest test;
	
	String repName;
	
	public void onStart(ITestContext testContext) {
		
//		SimpleDateFormat df = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss");
//		Date dt = new Date();
//		String Currentdatetimestamp = df.format(dt);
		
		
		String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date()); // time stamp	
		 repName = "Test-Report-" + timeStamp + ".html";
		 
		 sparkReporter = new ExtentSparkReporter(".\\reports\\" + repName); // specify location of the report
		
		 sparkReporter.config().setDocumentTitle("Open Cart Automation Report"); // Title of report
		 sparkReporter.config().setReportName("Open Cart Functional Testing"); // name of the report
		 sparkReporter.config().setTheme(com.aventstack.extentreports.reporter.configuration.Theme.DARK);
		 
		 extend = new ExtentReports();
		 extend.attachReporter(sparkReporter);
		 extend.setSystemInfo("Application", "Open Cart");
		 extend.setSystemInfo("Module", "Admin");
		 extend.setSystemInfo("Sub Module", "Customers");
		 extend.setSystemInfo("User Name", System.getProperty("user.name"));
		 extend.setSystemInfo("Environment", "QA");
		 
		 String os = testContext.getCurrentXmlTest().getParameter("os");
		 extend.setSystemInfo("Operating System", os);
		 
		 String browser = testContext.getCurrentXmlTest().getParameter("browser");
		 extend.setSystemInfo("Browser", browser);
		 
		 List<String> includedGroups = testContext.getCurrentXmlTest().getIncludedGroups();
			if (!includedGroups.isEmpty()) {
				
				extend.setSystemInfo("Groups", includedGroups.toString());
			}	
	      }
	
	
			public void onTestSuccess(ITestResult result) {
				// TODO Auto-generated method stub
				
				test = extend.createTest(result.getTestClass().getName()); // create new entry in the report
				test.assignCategory(result.getMethod().getGroups()); // add test method's group)
				test.log(Status.PASS, result.getName() + " got successfully executed");			

			}
			
			public void onTestFailure(ITestResult result) {
				// TODO Auto-generated method stub

				test = extend.createTest(result.getTestClass().getName()); // create new entry in the report
				test.assignCategory(result.getMethod().getGroups()); // add test method's group)
				
				test.log(Status.FAIL, result.getName() + " got failed");
				test.log(Status.FAIL, result.getThrowable().getMessage());
				
				try {
					String impPath = new BaseClass().captureScreen(result.getName());
					test.addScreenCaptureFromPath(impPath); // adding screen shot
					
				} catch (Exception e) {
					e.printStackTrace();
				}

			}
			
			public void onTestSkipped(ITestResult result) {
				// TODO Auto-generated method stub

				test = extend.createTest(result.getTestClass().getName()); // create new entry in the report
				test.assignCategory(result.getMethod().getGroups()); // add test method's group)
				test.log(Status.SKIP, result.getName() + " got skipped");
				test.log(Status.SKIP, result.getThrowable().getMessage());

			}
			
			public void onFinish(ITestContext testContext) {
				// TODO Auto-generated method stub
				extend.flush();
				
				String pathOfExtentReport = System.getProperty("user.dir") + "\\reports\\" + repName;
				File extentReportFile = new File(pathOfExtentReport);
				
				
				try {
					Desktop.getDesktop().browse(extentReportFile.toURI());
				} catch (Exception e) {
					e.printStackTrace();
				}
				
//				try {
//					URL url = new URL("file:///" + system.getProperty("user.dir") + "/reports/" + repName);
//					Desktop.getDesktop( ).browse(url.toURI());
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
				
						
			}
			
			
		
}
