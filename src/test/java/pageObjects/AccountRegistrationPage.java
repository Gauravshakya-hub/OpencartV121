package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AccountRegistrationPage extends BasePage {

	public AccountRegistrationPage(WebDriver driver) {
		super(driver);
	}
	
	@FindBy(xpath="//input[@id='input-firstname']")
	WebElement txt_FirstName;
	
	@FindBy(xpath="//input[@id='input-lastname']")
	WebElement txt_LastName;
	
	@FindBy(xpath="//input[@id='input-email']")
	WebElement txt_Email;
	
	@FindBy(xpath="//input[@id='input-telephone']")
	WebElement txt_Telephone;
	
	@FindBy(xpath="//input[@id='input-password']")
	WebElement txt_Password;
	
	@FindBy(xpath="//input[@id='input-confirm']")
	WebElement txt_ConfirmPassword;
	
	@FindBy(xpath="//input[@name='agree']")
	WebElement chkd_Agree;
	
	@FindBy(xpath="//input[@value='Continue']")
	WebElement btn_Continue;
	
	@FindBy(xpath="//h1[normalize-space()='Your Account Has Been Created!']")
	WebElement msg_Confirmation;
	
	
	public void setFirstName(String fname) {
		txt_FirstName.sendKeys(fname);
	}
	
	public void setLastName(String lname) {
		txt_LastName.sendKeys(lname);
	}	
	
	public void setEmail(String email) {
		txt_Email.sendKeys(email);
	}
	
	public void setTelephone(String telephone) {
		txt_Telephone.sendKeys(telephone);
	}
	
	public void setPassword(String password) {
		txt_Password.sendKeys(password);
	}
	
	public void setConfirmPassword(String password) {
		txt_ConfirmPassword.sendKeys(password);
	}
	
	public void setPrivacyPolicy() {
		chkd_Agree.click();
	}
	
	public void clickContinue() {
		btn_Continue.click();
	}
	
	public String getConfirmationMsg() {
		try {
			return (msg_Confirmation.getText());
		} catch (Exception e) {
			return (e.getMessage());
		}
	}
	

}
