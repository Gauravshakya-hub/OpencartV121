package testCases;
import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.SearchPage;
import pageObjects.ShoppingCartPage;
import testBase.BaseClass;

public class TC_005_AddToCartPageTest extends BaseClass {

	@Test(groups = { "Master" })
	public void verifyAddToCart() {
		System.out.println("This is verifyAddToCart method");
		try {

			HomePage hp = new HomePage(driver);
			hp.clickMyAccount();
			hp.clickLogin();
			LoginPage lp = new LoginPage(driver);
			lp.setEmail(p.getProperty("email"));
			lp.setPassword(p.getProperty("password"));
			lp.clickLogin();

			// Step 2: Search product
			SearchPage sp = new SearchPage(driver);
			sp.enterProductName(p.getProperty("searchProductName"));
			sp.clickSearchButton();

			// DEBUG: confirm search actually navigated
			System.out.println("URL after search click: " + driver.getCurrentUrl());
			System.out.println("Page title after search: " + driver.getTitle());

			// Step 3: Check product exists and add to cart
			ShoppingCartPage scp = new ShoppingCartPage(driver);
			if (sp.isProductExist(p.getProperty("searchProductName"))) {
			    sp.selectProduct(p.getProperty("searchProductName"));

			    scp.setQuantity("2");
			    scp.addtocart();

				// Step 4: Verify success message
				boolean status = scp.verifySuccessMessage(
					"You have added " + p.getProperty("searchProductName") + " to your shopping cart!"
				);
				Assert.assertTrue(status, "Success message not displayed after adding product to cart");
				System.out.println("Product added to cart and success message verified");
			} else {
				Assert.fail("Product '" + p.getProperty("searchProductName") + "' was not found in search results");
			}
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Test failed due to exception: " + e.getMessage());
		}
	}
}