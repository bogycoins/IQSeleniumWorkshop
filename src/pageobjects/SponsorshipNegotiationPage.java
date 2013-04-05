package pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

public class SponsorshipNegotiationPage {
	private final WebDriver driver;

	public PageHeaderComponent pageHeader;
	
	//Catalin -> add filter component
	public FilterNegociationsComponent filterNegociations;
	
	@FindBy(id = "add-new-sponsorship")
	private WebElement newRequestBtn;

	@FindBy(name = "createNew")
	private WebElement createRequestBtn;

	private WebElement sportsCategory;// no need to specify how to find the
										// elements; name/id are used by default
	private WebElement sponsorshipType;

	public SponsorshipNegotiationPage(WebDriver _driver) {
		this.driver = _driver;
		PageFactory.initElements(driver, this);

		String expectedTitle = "Negotiation Requests Overview";
		Assert.assertEquals(this.driver.getTitle(), expectedTitle);

		this.pageHeader = new PageHeaderComponent(driver);// add header component
		
		//Catalin -> add filter component
		this.filterNegociations = new FilterNegociationsComponent(driver);
	}

	/**
	 * Create a new negociation
	 * 
	 * @param sport sport category
	 * @param type request type
	 * @return an instance of NegociationRequestDetailsOne
	 */
	public NegociationRequestDetailsOnePage createNewNegociation(String sport, String type) {
		try {
			newRequestBtn.click();
			selectSport(sport);
			selectType(type);
			createRequestBtn.click();

			return new NegociationRequestDetailsOnePage(driver);
			
		} catch (Exception e) {
			throw new IllegalStateException(
					"I could not create a new negociation request");
		}
	}
	
	/**
	 * Select a sport category. Used when creating a new negociation request
	 * 
	 * @param value
	 */
	private void selectSport(String value) {
		Select sportCat = new Select(sportsCategory);
		sportCat.selectByVisibleText(value);
	}

	/**
	 * Select a sponsorship type. Used when creating a new negociation request
	 * 
	 * @param value
	 */
	private void selectType(String value) {
		Select sportCat = new Select(sponsorshipType);
		sportCat.selectByVisibleText(value);
	}

}