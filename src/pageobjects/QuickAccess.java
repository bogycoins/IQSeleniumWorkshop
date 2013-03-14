package pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class QuickAccess
{
	private final WebDriver driver;

	public QuickAccess(WebDriver _driver)
	{
		this.driver = _driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(className = "welcome-msg")
	private WebElement welcomeMessage;

	@FindBy(linkText = "Logout")
	private WebElement logoutButton;

	@FindBy(linkText = "Contact support")
	private WebElement contactSupportButton;
	
//	public LogoutPage goToLogoutPage()
//	{
//		this.logoutButton.click();
//		return new LogoutPage(this.driver);
//	}
}
