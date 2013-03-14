package pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage
{
private final WebDriver driver;
	
	public LoginPage(WebDriver _driver) {
		this.driver = _driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(id = "email")
		private WebElement emailField;

	@FindBy(id = "password")
		private WebElement passwordField;

	@FindBy(name = "login")
		private WebElement loginButton;
	
	@FindBy(className = "stripes-error")
		private WebElement errorMessage;
	
	public void doLogin(String user, String password)
	{
		try
		{
			this.emailField.clear();
			this.passwordField.clear();
			this.emailField.sendKeys(user);
			this.passwordField.sendKeys(password);
			this.loginButton.click();
		}
		catch (Exception ex)
		{
			System.out.println("There was an error with the login action! " + ex);
		}
	}
	
	public DashboardPage loginToDashboard(String user, String password)
	{
		this.doLogin(user, password);
		return new DashboardPage(driver);
	}
}
