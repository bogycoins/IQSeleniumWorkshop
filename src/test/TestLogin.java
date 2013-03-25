package test;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

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
	public void prepareTests(@Optional("chrome") String b){
		Browser browser = new Browser();
		driver = browser.startBrowser(b, Constants.WEBSITE);		
	}
	
	@AfterTest
	public void cleanUp(){	
		driver.quit();
	}
	
	//look into ExternalDataProvider class to search for the required data provider
	//@Test(dataProvider = "Login", dataProviderClass = AccountDataProvider.class) 
	public void testLogin(Account account) {
		
		//init login page
		LoginPage loginPage = new LoginPage(driver);
		//login
		loginPage.loginSuccess(account.getEmail(), account.getPassword());
	}
	
	//@Test(dataProvider = "LoginXMLdp", dataProviderClass = AccountDataProvider.class) 
	public void testXMLProvider(Account account) {
		LoginPage loginPage = new LoginPage(driver);
		loginPage.loginSuccess(account.getEmail(), account.getPassword());
	}
	
	//@Test(dataProvider = "LoginCSVdp", dataProviderClass = AccountDataProvider.class) 
	public void testCSVProvider(Account account) {
		LoginPage loginPage = new LoginPage(driver);
		loginPage.loginSuccess(account.getEmail(), account.getPassword());
	}
	
	@Test(dataProvider = "LoginSQLLitedp", dataProviderClass = AccountDataProvider.class) 
	public void testSqlLiteProvider(Account account) {
		LoginPage loginPage = new LoginPage(driver);
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
