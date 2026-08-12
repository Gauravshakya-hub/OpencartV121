package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends BasePage {

	public HomePage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}
	
	@FindBy(xpath="//span[normalize-space()='My Account']")
	WebElement lnk_MyAccount;
	
	@FindBy(xpath = "(//a[normalize-space()='Register'])[1]")
	WebElement lnk_Register;
	
	@FindBy(xpath = "(//a[normalize-space()='Login'])[1]")  //login link in this step
	WebElement lnk_Login;
	
	public void clickMyAccount() {
		lnk_MyAccount.click();
	}
	
	public void clickRegister() {
		lnk_Register.click();
	}

	public void clickLogin() {
		// TODO Auto-generated method stub
		lnk_Login.click();
		
	}
	
	

}
