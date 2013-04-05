//Catalin
package test;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;

import datamodel.Account;
import datamodel.DataFilterNegociationsComponent;

import pageobjects.DashboardPage;
import pageobjects.LoginPage;
import pageobjects.QuickAccessComponent;
import pageobjects.SponsorshipNegotiationPage;

import utils.Browser;
import utils.Constants;

public class TestFilterNegociations {

	static WebDriver driver;
	private static Account account;

	@Parameters({ "browser" })
	@BeforeClass
	public void setupBrowser(String browser) {
		Browser b = new Browser();
		driver = b.startBrowser(browser, Constants.WEBSITE);
	}
	
	@AfterTest
	public void tidyUp() {
		driver.quit();
	}
	
	@Parameters({ "user", "pass" })
	@BeforeTest
	public void setupUser(String user, String pass) {
		account = new Account(user, pass);
	}
	
	@AfterTest
	public void logout() {
		
		QuickAccessComponent quickAccess = new QuickAccessComponent(driver);
		quickAccess.logout();
	}
	
	@Test(dataProvider = "FilterNegociationsComponentProvider", dataProviderClass = dataproviders.DataFilterNegociationsComponentProvider.class)
	public void testFilterNegociations(DataFilterNegociationsComponent data) {
		
		LoginPage loginPage = new LoginPage(driver);		
		DashboardPage homePage = loginPage.loginSuccess(account);
		
		SponsorshipNegotiationPage negociationPage = homePage.pageHeader.goToSponsorshipNegotiationPage();
		
		negociationPage.filterNegociations.filterNegociationRequests(data.getRequestName(), data.getNegociationStatus(), 
				data.getLicensingStatus(), data.getStartDate(), data.getEndDate());
		
	}
	
}
