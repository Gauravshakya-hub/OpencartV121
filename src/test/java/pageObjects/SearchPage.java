package pageObjects;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SearchPage extends BasePage {

	public SearchPage(WebDriver driver) {
		super(driver);
	}

	@FindBy(xpath = "//input[@placeholder='Search']")
	WebElement txtSearch;

	@FindBy(xpath = "//i[@class='fa fa-search']")
	WebElement btnSearch;

	public void enterProductName(String productName) {
		txtSearch.clear();
		txtSearch.sendKeys(productName);
	}

	public void clickSearchButton() {
		btnSearch.click();
	}

	private String buildProductXpath(String productName) {
		return "//div[contains(@class,'product-thumb')]//h4/a[contains(translate(text(),"
			+ "'ABCDEFGHIJKLMNOPQRSTUVWXYZ','abcdefghijklmnopqrstuvwxyz'), '"
			+ productName.toLowerCase() + "')]";
	}

	private void scrollAndPause(WebElement element) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView({block: 'center'});", element);
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
		}
	}

	public boolean isProductExist(String productName) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
		String xpath = buildProductXpath(productName);

		try {
			WebElement product = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(xpath)));
			scrollAndPause(product);

			List<WebElement> elements = wait.until(
				ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath(xpath))
			);

			boolean status = !elements.isEmpty();
			if (status) {
				System.out.println("Product found: " + elements.get(0).getText());
			}
			return status;

		} catch (Exception e) {
			System.out.println("Current URL: " + driver.getCurrentUrl());
			System.out.println("Product '" + productName + "' not found: " + e.getMessage());
			return false;
		}
	}

	public void selectProduct(String productName) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
		String xpath = buildProductXpath(productName);

		WebElement product = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(xpath)));
		scrollAndPause(product);

		WebElement clickableProduct = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(xpath)));
		clickableProduct.click();
		System.out.println("Clicked on product: " + productName);
	}
}