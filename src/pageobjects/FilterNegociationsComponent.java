//Catalin
package pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

public class FilterNegociationsComponent {
	
	private WebDriver driver;
	public PageHeaderComponent pageHeader;
	
	@FindBy (linkText = "Reset all")
	private WebElement resetBtn;
	
	@FindBy (id = "sponsorship-name-id")
	private WebElement requestNameField;
	
	@FindBy (id = "status-id")
	private WebElement negociationStatus;
	
	@FindBy (id = "licensing_status-id")
	private WebElement licensingStatus;
	
	@FindBy (id = "startDate")
	private WebElement startDateField;
	
	@FindBy (id = "endDate")
	private WebElement endDateField;
	
	@FindBy (css = "#sponsorshipFilteringForm > div.button-bar > a.button.button-black > span > span")
	private WebElement applyBtn;
		
	public FilterNegociationsComponent(WebDriver _driver) {
		
		this.driver = _driver;
		PageFactory.initElements(driver, this);
		
		String expectedTitle = "Negotiation Requests Overview";
		Assert.assertEquals(this.driver.getTitle(), expectedTitle);
		
		this.pageHeader = new PageHeaderComponent(driver);
	}
	
	public SponsorshipNegotiationPage filterNegociationRequests(String requestName, String negociationStat, 
			String licenceStat, String startDate, String endDate){
		
		resetBtn.click();
		requestNameField.sendKeys(requestName);
		selectNegociationStatus(negociationStat);
		selectLicensingStatus(licenceStat);
		startDateField.sendKeys(startDate);
		endDateField.sendKeys(endDate);		
		applyBtn.click();
		
		return new SponsorshipNegotiationPage(driver);
	}
	
	private void selectNegociationStatus(String value) {
		
		Select selectedNegociationStatus = new Select(negociationStatus);
		selectedNegociationStatus.selectByVisibleText(value);
	}
	
	private void selectLicensingStatus(String value) {
		
		Select selectedLicensingStatus = new Select(licensingStatus);
		selectedLicensingStatus.selectByVisibleText(value);
	}

}