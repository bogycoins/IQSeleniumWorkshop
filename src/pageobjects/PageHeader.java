package pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class PageHeader
{
	private final WebDriver driver;
	
	@FindBy(xpath = "//*[@id=\"main-menu\"]/li[1]/a")
	private WebElement navItemDashboard;

	@FindBy(id = "menu-itemSponsorship")
	private WebElement navItemSponsorship;

	@FindBy(xpath = "//ul[@id='main-menu']/li[3]/a")
	private WebElement navItemBootOrder;

	@FindBy(id = "menu-itemBudget")
	private WebElement navItemBudget;

	@FindBy(id = "menu-itemReports")
	private WebElement navItemReports;

	@FindBy(id = "menu-itemAdmin")
	private WebElement navItemAdmin;

	// Budget sub menu
	@FindBy(xpath = "//div[@id='submenu-budget-container']/div/ul/li[1]/a")
	private WebElement navBudgetSubItemBudget;

	@FindBy(xpath = "//div[@id='submenu-budget-container']/div/ul/li[2]/a")
	private WebElement navBudgetSubItemCurrencyExchangeRates;

	@FindBy(xpath = "//div[@id='submenu-budget-container']/div/ul/li[3]/a")
	private WebElement navBudgetSubItemCurrenciesAdministration;

	@FindBy(xpath = "//div[@id='submenu-reports-container']/div/ul/li[4]/a")
	private WebElement navBudgetSubItemExchangeRateBoard;

	// Reports sub menu
	@FindBy(xpath = "//div[@id='submenu-reports-container']/div/ul/li[1]/a")
	private WebElement navReportsSubContractOverview;

	@FindBy(xpath = "//div[@id='submenu-reports-container']/div/ul/li[2]/a")
	private WebElement navReportsSubCustomizeReports;

	@FindBy(id = "browser-back")
	private WebElement navItemBack;

	public PageHeader(WebDriver _driver)
	{
		this.driver = _driver;
		PageFactory.initElements(driver, this);
		this.quickAccessMenu = new QuickAccess(this.driver);
	}
	
	public QuickAccess quickAccessMenu;

	public PageHeader goBackToPreviousPage()
	{
		this.navItemBack.click();
		return new PageHeader(this.driver);
	}

	public BootordersPage goToBootordersPage()
	{
		this.navItemBootOrder.click();
		return new BootordersPage(this.driver);
	}

	public SponsorshipNegotiationPage goToSponsorshipNegotiationPage()
	{
		this.navItemSponsorship.click();
		WebElement navSponsorshipSubItemNegotiationRequest = new WebDriverWait(
				this.driver,
				10)
				.until(ExpectedConditions.elementToBeClickable(By
						.xpath("//div[@id='submenu-sponsorship-container']/div/ul/li[2]/a")));
		navSponsorshipSubItemNegotiationRequest.click();
		return new SponsorshipNegotiationPage(this.driver);
	}

	public SponsorshipOverviewPage goToSponsorshipOverview()
	{
		this.navItemSponsorship.click();
		WebElement navSponsorshipSubItemOverview = new WebDriverWait(
				this.driver,
				10)
				.until(ExpectedConditions.elementToBeClickable(By
						.xpath("//div[@id='submenu-sponsorship-container']/div/ul/li[1]/a")));
		navSponsorshipSubItemOverview.click();
		return new SponsorshipOverviewPage(this.driver);
	}
}
