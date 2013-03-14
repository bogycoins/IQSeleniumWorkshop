package pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class DashboardPage
{
	private final WebDriver driver;

	public PageHeader pageHeader; 
	
	public DashboardPage(WebDriver _driver)
	{
		this.driver = _driver;
		PageFactory.initElements(driver, this);
		this.pageHeader = new PageHeader(driver);
	}
}
