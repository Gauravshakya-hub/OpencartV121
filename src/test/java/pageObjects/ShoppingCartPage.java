package pageObjects;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ShoppingCartPage extends BasePage {

	public ShoppingCartPage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}
	
	// quantity field on product page
		@FindBy(xpath = "//input[@id='input-quantity']")
		WebElement txtQuantity;

		// Add to Cart button on product page
		@FindBy(xpath = "//button[@id='button-cart']")
		WebElement btnAddToCart;
	
		// set quantity value on the product page
		public void setQuantity(String qty) {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
			WebElement qtyField = wait.until(ExpectedConditions.visibilityOf(txtQuantity));
			qtyField.clear();
			qtyField.sendKeys(qty);
			System.out.println("Quantity set to: " + qty);
		}
		

		// click Add to Cart button on the product page
		public void addtocart() {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
			WebElement cartBtn = wait.until(ExpectedConditions.elementToBeClickable(btnAddToCart));
			cartBtn.click();
			System.out.println("Clicked Add to Cart button");
		}

		
		// verify success message after adding to cart
		public boolean verifySuccessMessage(String expectedText) {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
			try {
				WebElement successMsg = wait.until(
					ExpectedConditions.visibilityOfElementLocated(
						By.xpath("//div[@class='alert alert-success alert-dismissible']")
					)
				);
				String actualText = successMsg.getText();
				System.out.println("Success message displayed: " + actualText);
				return actualText.contains(expectedText);
			} catch (Exception e) {
				System.out.println("Success message not found: " + e.getMessage());
				return false;
			}
		}
}
