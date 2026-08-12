package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import testBase.BaseClass;
import utilities.DataProviders;

//Data is valid - login success --- test pass  -- logout
//data is valid  - login failed -- test failed 

//data is invalid - login success -- test fail - logout 
//data is invalid - login failed - test pass


public class TC003_LoginDataDrivenTesting extends BaseClass {

    @Test(dataProvider = "LoginData", dataProviderClass = DataProviders.class, groups = {"DataDriven"}) // getting data from DataProviders class
    public void verify_loginDDT(String email, String pwd, String exp) {

        try {
            // HomePage
            HomePage hp = new HomePage(driver);
            hp.clickMyAccount();
            System.out.println("Clicked on My Account");
            hp.clickLogin();
            System.out.println("Clicked login");

            // Login
            LoginPage lp = new LoginPage(driver);
            lp.setEmail(email);
            lp.setPassword(pwd);
            lp.clickLogin();

            // MyAccount
            MyAccountPage mac = new MyAccountPage(driver);
            boolean targetPage = mac.isMyAccountPageExists();

            if (exp.equalsIgnoreCase("Valid")) {
                if (targetPage) {
                    mac.clickLogout();
                    Assert.assertTrue(true);
                } else {
                    Assert.fail("Expected login to SUCCEED for valid data, but it failed. Email: " + email);
                }
            } else if (exp.equalsIgnoreCase("Invalid")) {
                if (targetPage) {
                    mac.clickLogout();
                    Assert.fail("Expected login to FAIL for invalid data, but it succeeded. Email: " + email);
                } else {
                    Assert.assertTrue(true);
                }
            } else {
                Assert.fail("Unrecognized 'exp' value in test data: " + exp);
            }

        } catch (Exception e) {
            e.printStackTrace();               // ✅ prints real error to console/log
            Assert.fail("Test failed due to exception: " + e.getMessage());  // ✅ shows real reason in report
        }
    }
}