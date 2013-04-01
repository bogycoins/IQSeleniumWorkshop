package pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class QuickAccessComponent {
	private final WebDriver driver;

	public QuickAccessComponent(WebDriver _driver) {
		this.driver = _driver;		
		PageFactory.initElements(_driver, this);
	}

	@FindBy(className = "welcome-msg")
	private WebElement welcomeMessage;

	@FindBy(linkText = "Logout")
	private WebElement logoutButton;

	@FindBy(linkText = "Contact support")
	private WebElement contactSupportButton;

	/**
	 * perform logout
	 * 
	 * @return a login page
	 */
	public LoginPage logout() {

		try {
			logoutButton.click();
			//Utils.waitForAllAjaxRequests(driver);
			LogoutConfirmationPage logoutConfirmationPage = new LogoutConfirmationPage(driver);
			LoginPage loginPage = logoutConfirmationPage.confirmLogout();
			return loginPage;
		} catch (Exception e) {
			throw new IllegalStateException("Error: " + e);
		}
	}
}
