package pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class BootordersPage
{
	private final WebDriver driver;

	public PageHeaderComponent pageHeader; 
	
	public BootordersPage(WebDriver _driver)
	{
		this.driver = _driver;
		PageFactory.initElements(driver, this);
		this.pageHeader = new PageHeaderComponent(driver);
	}
}
