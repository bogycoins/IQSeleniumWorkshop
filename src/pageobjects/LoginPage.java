//iulia
package pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

public class LoginPage {

	public String title;

	private WebDriver driver;

	@FindBy(id = "email")
	private WebElement emailField;

	@FindBy(id = "password")
	private WebElement passwordField;

	@FindBy(name = "login")
	private WebElement loginButton;

	@FindBy(className = "stripes-error")
	private WebElement errorMessage;

	public LoginPage(WebDriver _driver) {

		PageFactory.initElements(_driver, this);

		this.driver = _driver;
		
		String expectedTitle = "PUMA login page";
		Assert.assertEquals(this.driver.getTitle(), expectedTitle);
	}

	public DashboardPage loginSuccess(String username, String password) {

		executeLogin(username, password);
		return new DashboardPage(driver);
	}

	private void executeLogin(String username, String password) {

		emailField.sendKeys(username);
		passwordField.sendKeys(password);
		loginButton.submit();
	}

	// TODO
	public void loginFail(String username, String password) {

		executeLogin(username, password);
	}

	// TODO
	public void forgotPasswordSuccess(String username) {

		forgotPassword(username);
	}

	// TODO
	public void forgotPasswordFail(String username) {

		forgotPassword(username);
	}

	// TODO
	private void forgotPassword(String username) {

	}

}
