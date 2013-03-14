package pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class SponsorshipOverviewPage
{
	private final WebDriver driver;

	public PageHeader pageHeader; 
	
	public SponsorshipOverviewPage(WebDriver _driver)
	{
		this.driver = _driver;
		PageFactory.initElements(driver, this);
		this.pageHeader = new PageHeader(driver);
	}
}