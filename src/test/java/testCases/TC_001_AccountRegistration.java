package testCases;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.AccountRegistrationPage;
import pageObjects.HomePage;
import testBase.BaseClass;

public class TC_001_AccountRegistration extends BaseClass
{	

	@Test(groups= {"regression", "master"})
	public void test_account_registration() throws IOException
	{
//		logger.debug("Start of debug logging.....");
		logger.info("Starting TC_001_AccountRegistration");
		try
		{
			logger.info("Launching applicaton");
			driver.get(rb.getString("appURL"));
			driver.manage().window().maximize();
					
			//homepage access
			logger.info("Clicking on MyInfo-->Registration link");
			HomePage hp=new HomePage(driver);
			hp.clickMyAccount();
			hp.clickRegister();
			
		
			 //AccountRegistrationPage access
			logger.info("Providing customer details");
			AccountRegistrationPage regpage=new AccountRegistrationPage(driver);
			
			regpage.setFirstName("Simran");
			logger.info("Provided firstname");
			regpage.setLastName("Thapar");
			logger.info("Provided lastname");
			regpage.setEmail(randomestring()+"@gmail.com");
			logger.info("Provided email");
			regpage.setTelephone("4567891234");
			logger.info("Provided phone no");
			regpage.setPassword("abcxyz");
			logger.info("Provided password");
			regpage.setConfirmPassword("abcxyz");
			logger.info("provided confirm password");
			regpage.setPrivacyPolicy();
			logger.info("clicked on policy");
			regpage.clickContinue();
			logger.info("clicked on continue button");
			
			String confmsg=regpage.getConfirmationMsg();
			logger.info("Validation started...");
			
			if(confmsg.equals("Your Account Has Been Created!"))
			{
				logger.info("Registration passed ...");
				Assert.assertTrue(true);
			}
			else
			{
				captureScreen(driver,"test_account_registration");
				logger.error("Registration failed..");
				Assert.assertTrue(false);
			}
		}
		
		catch(Exception e)
		{
			captureScreen(driver,"test_account_registration");
			logger.fatal("Registration could not start..");
			Assert.fail();
		}
		
	}
	
	

	
}
