package pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.pagefactory.ElementLocatorFactory;

import utils.Utils;

public class LogoutConfirmationPage {

  @FindBy(css = ".button[name=confirm]")
  private WebElement yesButton;

  private WebDriver driver;

  public LogoutConfirmationPage(WebDriver driver) {

    // wait 1 sec to identify elements
    ElementLocatorFactory finder = new AjaxElementLocatorFactory(driver, 1);
    PageFactory.initElements(finder, this);

    this.driver = driver;

    // Check that we're on the right page.
    if (!"Confirmation required".equals(driver.getTitle())) {
      Utils.takeScreenshot(driver);
      throw new IllegalStateException("This is not the logout confirmation page!");
    }
  }

  /**
   * confirm logout
   * 
   * @return new LoginPage
   */
  public LoginPage confirmLogout() {

    try {
      yesButton.click();
      return new LoginPage(driver);
    }
    catch (Exception e) {
      throw new IllegalStateException("The 'Yes' button could not be clicked!");
    }
  }

}
