package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends BasePage {

	public LoginPage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}

	@FindBy(xpath = "//input[@id='input-email']")
	WebElement txt_username;

	@FindBy(xpath = "//input[@id='input-password']")
	WebElement txt_password;

	@FindBy(xpath = "//input[@value='Login']")
	WebElement btn_login;

	public void setEmail(String username) {
		txt_username.sendKeys(username);
	}

	public void setPassword(String password) {
		txt_password.sendKeys(password);
	}

	public void clickLogin() {
		btn_login.click();
	}
	

}
