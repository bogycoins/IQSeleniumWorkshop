package test;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import pageobjects.DashboardPage;
import pageobjects.LoginPage;
import pageobjects.QuickAccessComponent;
import utils.Browser;
import utils.Constants;

import datamodel.Account;
import dataproviders.AccountDataProvider;

public class TestLogin {

	static WebDriver driver;

	@Parameters({"browser"})
	@BeforeTest
	public void prepareTests(String b){
		Browser browser = new Browser();
		driver = browser.startBrowser(b, Constants.WEBSITE);		
	}
	
	@AfterTest
	public void cleanUp(){	
		driver.quit();
	}
	
	//look into ExternalDataProvider class to search for the required data provider
	@Test(dataProvider = "Login", dataProviderClass = AccountDataProvider.class) 
	public void testLogin(Account account) {
		
		//init login page
		LoginPage loginPage = new LoginPage(driver);
		//login
		loginPage.loginSuccess(account.getEmail(), account.getPassword());
	}
	
	@AfterMethod
	public void logout(){
		try{
		QuickAccessComponent quickAccess = new QuickAccessComponent(driver);
		quickAccess.logout();
		}
		catch(Exception e){
			driver.get(Constants.WEBSITE);
		}
	}
}
