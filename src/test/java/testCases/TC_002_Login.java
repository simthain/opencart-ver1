package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.LoginPage;
import testBase.BaseClass;

public class TC_002_Login extends BaseClass {

	@Test(groups= {"sanity", "master"})
	public void test_login()
	{
		logger.info("Starting TC_002_Login");
		
		try 
		{
			driver.get(rb.getString("appURL"));
			logger.info("Home page Displayed");
			
			driver.manage().window().maximize();
			
			//Home page
			HomePage hp=new HomePage(driver);
			hp.clickMyAccount();
			logger.info("Clicked on My Account");
			hp.clickLogin();
			logger.info("Clicked on Login submenu");
			
			//Login page
			LoginPage lp=new LoginPage(driver);
			lp.setEmail(rb.getString("email"));
			logger.info("Provided email");
			lp.setPassword(rb.getString("password"));
			logger.info("Provided password");
			
			lp.clickLogin();
			logger.info("Clicked on Login button");
			
			boolean targetpage=lp.isMyAccountPageExists();
			
			if(targetpage)
			{
				logger.info("Login Success");
				Assert.assertTrue(true);
			}
			else
			{
				logger.error("Login failed");
				captureScreen(driver, "test_login");
				Assert.assertTrue(false);
			}
		}
		catch(Exception e)
		{
			logger.fatal("Error in login page");
			Assert.fail();
		}
	}
	
	
}
