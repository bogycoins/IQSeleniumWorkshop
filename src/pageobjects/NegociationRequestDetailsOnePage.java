//dani
package pageobjects;

import utils.RadioGroup;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import utils.helpers;

public class NegociationRequestDetailsOnePage {

	private final WebDriver driver;
	
	@FindBy(id = "continue")
	private WebElement continueBtn;
	
	//@FindBy(xpath = "//input[@type='file']")
	@FindBy(id = "input-profileImage")
	private WebElement uploadImageBtn;

	@FindBy(id = "clearButton")
	private WebElement removeImageBtn;

	@FindBy(id = "year")
	private WebElement yearWebElement;
	
	private Select yearLst;

	@FindBy(id = "agentSelect")
	private WebElement agentWebElement;
	private Select agentLst;

	@FindBy(id = "sponsorship-unit-id")
	private WebElement unitWebElement;
	private Select unitLst;

	@FindBy(id = "assetName")
	private WebElement nameFld;

	@FindBy(id = "dateOfBirth")
	private WebElement birthDateFld;

	private RadioGroup genderRadioGroup;
	// use this name to search / initialize radio group
	private String genderRadioGroupName = "sponsorship.specificInfo.gender";

	@FindBy(id = "nationality")
	private WebElement nationalityWebElement;
	private Select nationalityLst;

	@FindBy(id = "position")
	private WebElement positionFld;

	@FindBy(id = "clubName")
	private WebElement clubNameFld;

	@FindBy(id = "shoeSilo")
	private WebElement shoeSiloWebElement;
	private Select shoeSiloLst;

	@FindBy(id = "nationalTeamPlayer")
	private WebElement nationalTeamPlayerCheckbox;

	@FindBy(id = "topDivisionPlayer")
	private WebElement topDivisionPlayerCheckbox;

	@FindBy(id = "startingPlayer")
	private WebElement startingPlayerCheckbox;

	private RadioGroup marketTypeRadioGroup;
	// use this name to search / initialize radio group
	private String marketTypeRadioGroupName = "sponsorship.specificInfo.typeOfMarket";

	public NegociationRequestDetailsOnePage(WebDriver _driver) {
		
		this.driver = _driver;
		PageFactory.initElements(_driver, this);

		// validate the correct page is displayed
		// <title>Football Player Negotiation Request</title>
		// <title>Motorsports Player Negotiation Request</title>
		Assert.assertTrue(driver.getTitle().endsWith(" Player Negotiation Request"), "Checking title for NegociationRequestDetails page");
	}

	/**
	 * @param year year value (selected from list)
	 * @param uploadImagePath path on disk where the file to be uploaded resides
	 * @param agent agent name as displayed text (selected from list)
	 * @param negotiationUnit negotiation unit as displayed text (selected from list)
	 * @param name name
	 * @param birthDate birth date
	 * @param genderOption use genderOptions enum
	 * @param nationality nationality as displayed text (selected from list)
	 * @param position position
	 * @param club club
	 * @param shoeSilo shoe silo as displayed text (selected from list)
	 * @param nationalTeamPlayer true|false for selecting/unselecting checkbox 
	 * @param topDivisionPlayer true|false for selecting/unselecting checkbox
	 * @param startingPlayer true|false for selecting/unselecting checkbox
	 * @param marketTypeOption use marketTypeOptions enum
	 */
	private void submitForm(String year, String uploadImagePath, String agent,
			String negotiationUnit, String name, String birthDate,
			genderOptions genderOption, String nationality, String position,
			String club, String shoeSilo, Boolean nationalTeamPlayer,
			Boolean topDivisionPlayer, Boolean startingPlayer,
			marketTypeOptions marketTypeOption) {

		// TODO: check how to upload image
//		uploadImageBtn.sendKeys(uploadImagePath);

		// initialize specific type web elements (e.g. lists, radiogroups etc)
		yearLst = new Select(yearWebElement);
		agentLst = new Select(agentWebElement);
		unitLst = new Select(unitWebElement);
		nationalityLst = new Select(nationalityWebElement);
		shoeSiloLst = new Select(shoeSiloWebElement);		
		genderRadioGroup = new RadioGroup(genderRadioGroupName, this.driver);
		marketTypeRadioGroup = new RadioGroup(marketTypeRadioGroupName, this.driver);
		
		// fill in fields
		yearLst.selectByValue(year);
		agentLst.selectByVisibleText(agent);
		unitLst.selectByVisibleText(negotiationUnit);
		nameFld.sendKeys(name);
		birthDateFld.sendKeys(birthDate);
		genderRadioGroup.selectOptionByValue(genderOption.getText());
		nationalityLst.selectByVisibleText(nationality);
		positionFld.sendKeys(position);
		clubNameFld.sendKeys(club);
		shoeSiloLst.selectByVisibleText(shoeSilo);
		helpers.selectCheckbox(nationalTeamPlayerCheckbox, nationalTeamPlayer);
		helpers.selectCheckbox(topDivisionPlayerCheckbox, topDivisionPlayer);
		helpers.selectCheckbox(startingPlayerCheckbox, startingPlayer);
		marketTypeRadioGroup.selectOptionByValue(marketTypeOption.getText());

		// submit
		continueBtn.click();
	}

	// TODO update return type when second page class is available
	/**
	 * @param year year value (selected from list)
	 * @param uploadImagePath path on disk where the file to be uploaded resides
	 * @param agent agent name as displayed text (selected from list)
	 * @param negotiationUnit negotiation unit as displayed text (selected from list)
	 * @param name name
	 * @param birthDate birth date
	 * @param genderOption use genderOptions enum
	 * @param nationality nationality as displayed text (selected from list)
	 * @param position position
	 * @param club club
	 * @param shoeSilo shoe silo as displayed text (selected from list)
	 * @param nationalTeamPlayer true|false for selecting/unselecting checkbox 
	 * @param topDivisionPlayer true|false for selecting/unselecting checkbox
	 * @param startingPlayer true|false for selecting/unselecting checkbox
	 * @param marketTypeOption use marketTypeOptions enum
	 */
	public NegotiationRequestDetailsTwoPage doFillNegociationRequestDetailsOneValid(
			String year, String uploadImagePath, String agent,
			String negotiationUnit, String name, String birthDate,
			genderOptions genderOption, String nationality, String position,
			String club, String shoeSilo, Boolean nationalTeamPlayer,
			Boolean topDivisionPlayer, Boolean startingPlayer,
			marketTypeOptions marketTypeOption) {

		submitForm(year, uploadImagePath, agent, negotiationUnit, name,
				birthDate, genderOption, nationality, position, club, shoeSilo,
				nationalTeamPlayer, topDivisionPlayer, startingPlayer,
				marketTypeOption);

		return new NegotiationRequestDetailsTwoPage(this.driver);
	}

	/**
	 * @param year year value (selected from list)
	 * @param uploadImagePath path on disk where the file to be uploaded resides
	 * @param agent agent name as displayed text (selected from list)
	 * @param negotiationUnit negotiation unit as displayed text (selected from list)
	 * @param name name
	 * @param birthDate birth date
	 * @param genderOption use genderOptions enum
	 * @param nationality nationality as displayed text (selected from list)
	 * @param position position
	 * @param club club
	 * @param shoeSilo shoe silo as displayed text (selected from list)
	 * @param nationalTeamPlayer true|false for selecting/unselecting checkbox 
	 * @param topDivisionPlayer true|false for selecting/unselecting checkbox
	 * @param startingPlayer true|false for selecting/unselecting checkbox
	 * @param marketTypeOption use marketTypeOptions enum
	 * @return NegociationRequestDetailsOnePage()
	 */
	public NegociationRequestDetailsOnePage doFillNegociationRequestDetailsOneInvalid(
			String year, String uploadImagePath, String agent,
			String negotiationUnit, String name, String birthDate,
			genderOptions genderOption, String nationality, String position,
			String club, String shoeSilo, Boolean nationalTeamPlayer,
			Boolean topDivisionPlayer, Boolean startingPlayer,
			marketTypeOptions marketTypeOption) {

		submitForm(year, uploadImagePath, agent, negotiationUnit, name,
				birthDate, genderOption, nationality, position, club, shoeSilo,
				nationalTeamPlayer, topDivisionPlayer, startingPlayer,
				marketTypeOption);
	
		return new NegociationRequestDetailsOnePage(this.driver);

	}
	
	/**
	 * @return the error messages displayed on the page
	 */
	public String getValidationMessages() {
		
		String messages = "";
					
		for(WebElement errorSpan : this.driver.findElements(By.className("stripes-error"))) {
			
			messages = messages + errorSpan.getText();
		}
		
		return messages;
	}
	
	/**
	 * use this for selecting gender radio button options
	 */
	public enum genderOptions {
		
		MALE("MALE"), FEMALE("FEMALE");
	
		private String text;
	
		genderOptions(String text) {
	
			this.text = text;
		}
	
		public String getText() {
	
			return this.text;
		}
	
		public static genderOptions fromString(String text) {
	
			if (text != null) {
	
				for (genderOptions x : genderOptions.values()) {
	
					if (text.equalsIgnoreCase(x.text)) {
	
						return x;
					}
				}
			}
	
			throw new IllegalArgumentException(String.format("No constant with text <%s> found", text));
		}
	}
	
	/**
	 * use this for selecting market type radio button options
	 */
	public enum marketTypeOptions {
		
		STATEMENT("STATEMENT"), INDEPENDENT("INDEPENDENT"), COMPLEMENTARY("COMPLEMENTARY");
	
		private String text;
	
		marketTypeOptions(String text) {
	
			this.text = text;
		}
	
		public String getText() {
	
			return this.text;
		}
	
		public static marketTypeOptions fromString(String text) {
	
			if (text != null) {
	
				for (marketTypeOptions x : marketTypeOptions.values()) {
	
					if (text.equalsIgnoreCase(x.text)) {
	
						return x;
					}
				}
			}
	
			throw new IllegalArgumentException(String.format("No constant with text <%s> found", text));
		}
	}

}
