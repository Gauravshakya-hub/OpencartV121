package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.SearchPage;
import testBase.BaseClass;

public class TC_004_SearchProjectTest extends BaseClass {

	@Test(groups = {"Master"})
	public void verifySearchProject() {
		System.out.println("This is verifySearchProject method");

		try {
			HomePage hp = new HomePage(driver);
			hp.clickMyAccount();
			hp.clickLogin();

			LoginPage lp = new LoginPage(driver);
			lp.setEmail(p.getProperty("email"));
			lp.setPassword(p.getProperty("password"));
			lp.clickLogin();
			
			SearchPage sp = new SearchPage(driver);
			sp.enterProductName("Mac");
			sp.clickSearchButton();

			boolean status = sp.isProductExist("MacBook");
			Assert.assertEquals(status, true, "Product 'MacBook' was not found in search results");

		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Test failed due to exception: " + e.getMessage());
		}
	}
}