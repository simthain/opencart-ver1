package testCases;

import java.io.IOException;


import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import testBase.BaseClass;
import utilities.XLUtility;

public class TC_003_LoginDDT extends BaseClass {
	
	@Test(dataProvider="LoginData", groups= {"ddt"})
	public void test_LoginDDT(String email, String pwd, String exp)
	{
		try   
		{
			driver.get(rb.getString("appURL"));
			logger.info("Home page Displayed");
			
			driver.manage().window().maximize();
			
			HomePage hp=new HomePage(driver);
			hp.clickMyAccount();
			logger.info("Clicked on My Account menu");
			hp.clickLogin();
			logger.info("Clicked on Login submenu");
			
			LoginPage lp=new LoginPage(driver);
			lp.setEmail(email);
			logger.info("Provided email");
			lp.setPassword(pwd);
			logger.info("Provided password");
			lp.clickLogin();
			logger.info("Clicked Login button");
			
			Boolean targetpage=lp.isMyAccountPageExists();
			
			if(exp.equals("Valid"))  //3rd column(result) in excel sheet has Valid and Invalid entries 
			{
				if(targetpage==true)
				{
					logger.info("Login success..");
					
					MyAccountPage myaccpage=new MyAccountPage(driver);
					myaccpage.clickLogout();
					Assert.assertTrue(true);
				}
				else
				{
					logger.error("Login failed...");
					Assert.assertTrue(false);
				}
				
			}
			
			if(exp.equals("Invalid"))
			{
				if(targetpage==true)
				{
		
					MyAccountPage myaccpage=new MyAccountPage(driver);
					myaccpage.clickLogout();
					Assert.assertTrue(false);
				}
				else
				{
					logger.error("Login failed...");
					Assert.assertTrue(true);
				}
			}
		}
		
		catch(Exception e)
		{
			logger.fatal("Loging failed..");
			Assert.assertTrue(false);
		}
	}
	
	@DataProvider(name="LoginData")
	public String[][] getData() throws IOException
	{
		String path=".\\testData\\OpenCart_LoginData.xlsx";
		
		XLUtility xlutil=new XLUtility(path);
		
		int totalrows=xlutil.getRowCount("Sheet1");
		int totalcols=xlutil.getCellCount("Sheet1",1);
		
		String logindata[][]=new String[totalrows][totalcols];
		for(int i=1;i<=totalrows;i++)
		{
			for(int j=0;j<totalcols;j++)
			{
				logindata[i-1][j]=xlutil.getCellData("Sheet1", i, j);
			}
		}
		return logindata;
	}

}
