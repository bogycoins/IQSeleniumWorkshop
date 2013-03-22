package test;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import pageobjects.DashboardPage;
import pageobjects.LoginPage;
import pageobjects.NegociationRequestDetailsOnePage;
import pageobjects.NegociationRequestDetailsOnePage.genderOptions;
import pageobjects.NegociationRequestDetailsOnePage.marketTypeOptions;
import pageobjects.QuickAccessComponent;
import pageobjects.SponsorshipNegotiationPage;
import utils.Browser;
import utils.Constants;

public class TestNegociationRequests {

	static WebDriver driver;

	@BeforeTest
	public void prepareTests(){
		Browser browser = new Browser();
		driver = browser.startBrowser("chrome", Constants.WEBSITE);
		
	}
	
	@AfterTest
	public void cleanUp(){
		//logout first
		QuickAccessComponent quickAccess = new QuickAccessComponent(driver);
		//quickAccess.logout();
		
		//driver.quit();
	}
/*
1. Login
2. Navigate to Negociation Request Overview
3. Create new request (Football & Player/Athlete)
4. Fill deal details (1)
5. Click Continue
6. Fill deal details (2)
7. Click Submit for Approval
*/
	
	@Parameters({"user", "pass"})
	@Test
	public void testNegociationReq(String user, String pass){
		//init login page
		LoginPage loginPage = new LoginPage(driver);
		//login
		DashboardPage homePage = loginPage.loginSuccess(user, pass);//TODO: use Account data model
		//go to sponsorship negociation page
		SponsorshipNegotiationPage negociationPage = homePage.pageHeader.goToSponsorshipNegotiationPage();
		//create new negociation
		NegociationRequestDetailsOnePage detailsOne = negociationPage.createNewNegociation("Football", "Player/Athlete");
		//fill first page of details
		detailsOne.doFillNegociationRequestDetailsOneValid(
				"2015", "d://mesi.jpg", "Adi Moldovan", "Int Football", "name",
				"1981/03/121", genderOptions.MALE, "Romania", "Position1", "club name",
				"Power", true, true, true, marketTypeOptions.INDEPENDENT);
		//TODO: to be continued
	}
	
}
