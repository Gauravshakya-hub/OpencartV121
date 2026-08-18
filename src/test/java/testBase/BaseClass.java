package testBase;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Properties;

import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;

public class BaseClass {
public static WebDriver driver;
public Properties p;
 

	
	@BeforeClass(groups= {"Sanity", "Regression", "Master"})
	 public void setUp() throws IOException {
		System.out.println("This is setup method");
		
		//loading config.properties file
		FileReader file = new FileReader(System.getProperty("user.dir") + "\\src\\test\\resources\\config.properties");
		p = new Properties();
		p.load(file);
		
		driver= new ChromeDriver();
		driver.manage().deleteAllCookies();
		driver.get(p.getProperty("appURL"));  //reading the URL from config.properties file
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
		System.out.println("before class executed");
		
	}
	
	@AfterClass(groups= {"Sanity", "Regression", "Master"}, alwaysRun = true)
	public void tearDown() {
		System.out.println("This is tearDown method");
		 //driver.quit();
	}
	
	 // Random Alphabetic String (e.g., AbCdEf)
    public String randomString() {
        return RandomStringUtils.secure().nextAlphabetic(6);
    }

    // Random Numeric String (e.g., 5839201456)
    public String randomNumber() {
        return RandomStringUtils.secure().nextNumeric(10);
    }

    // Random Alphanumeric String (e.g., A1b2C3)
    public String randomAlphaNumeric() {
        return RandomStringUtils.secure().nextAlphanumeric(8);
    }

    // Random Email (e.g., abcxyz123@gmail.com)
    public String randomEmail() {
        return RandomStringUtils.secure().nextAlphabetic(6).toLowerCase()
                + RandomStringUtils.secure().nextNumeric(3)
                + "@gmail.com";
    }
    
	public String captureScreen(String tname) {
		// Implement your screenshot capture logic here
		// This method should return the path of the captured screenshot
		String timestamp = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date(System.currentTimeMillis()));
		TakesScreenshot takesScreenshot = (TakesScreenshot) driver;
		File sourceFile = takesScreenshot.getScreenshotAs(OutputType.FILE);
		
		String targetFilePath = System.getProperty("user.dir") + "\\screenshots\\" + tname + "_" + timestamp + ".png";
		
		File targetFile = new File(targetFilePath);
		sourceFile.renameTo(targetFile);
		return targetFilePath;
		
	}

}
