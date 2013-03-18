package pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

public class DashboardPage
{
	private final WebDriver driver;
	public PageHeaderComponent pageHeader; 
	
	@FindBy(className = "welcome-msg")
	private WebElement welcomeMsg;
	
	public DashboardPage(WebDriver _driver)
	{
		this.driver = _driver;
		PageFactory.initElements(driver, this);
		
		Assert.assertEquals(driver.getTitle(), "PUMA - Dashboard");
		
		this.pageHeader = new PageHeaderComponent(driver);
	}
	
	public String getHomePageWelcomeMessage(){  		  
        return welcomeMsg.getText();
    }  
}
