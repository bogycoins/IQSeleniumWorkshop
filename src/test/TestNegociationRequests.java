package test;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import datamodel.Account;
import datamodel.DataNegociationRequestDetailsOne;
import pageobjects.DashboardPage;
import pageobjects.LoginPage;
import pageobjects.NegociationRequestDetailsOnePage;
import pageobjects.QuickAccessComponent;
import pageobjects.SponsorshipNegotiationPage;
import utils.Browser;
import utils.Constants;
import utils.Utils;

public class TestNegociationRequests {

	static WebDriver driver;
	private static Account account;

	@Parameters({ "browser"})
	@BeforeTest
	public void setupBrowser(String browser) {
		Browser b = new Browser();
		driver = b.startBrowser(browser, Constants.WEBSITE);
	}

	@Parameters({"user", "pass" })
	@BeforeTest
	public void setupUser(String u, String p) {
		account = new Account(u, p);
	}
	
	@AfterTest
	public void cleanUp() throws InterruptedException {
		//make sure all ajax is finished
		Utils.waitForAllAjaxRequests(driver); 
		// logout first
		QuickAccessComponent quickAccess = new QuickAccessComponent(driver);
		quickAccess.logout();
		//then quit
		driver.quit();
	}

	/*
	 * 1. Login 
	 * 2. Navigate to Negociation Request Overview 
	 * 3. Create new request (Football & Player/Athlete) 
	 * 4. Fill deal details (1) 
	 * 5. Click Continue 
	 * 6. Fill deal details (2) 
	 * 7. Click Submit for Approval
	 */

	@Test(dataProvider = "NegociationRequestDetailsOneProvider", dataProviderClass = dataproviders.DataNegociationRequestDetailsOneDataProvider.class)
	public void testNegociationReq(DataNegociationRequestDetailsOne data) {
		// init login page
		LoginPage loginPage = new LoginPage(driver);
		// login
		DashboardPage homePage = loginPage.loginSuccess(account);
		// go to sponsorship negociation page
		SponsorshipNegotiationPage negociationPage = homePage.pageHeader.goToSponsorshipNegotiationPage();
		// create new negociation
		NegociationRequestDetailsOnePage detailsOne = negociationPage.createNewNegociation("Football", "Player/Athlete");
		// fill first page of details
		detailsOne.doFillNegociationRequestDetailsOneValid(data);
		// TODO: to be continued
	}

}
