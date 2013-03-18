//dani
package pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import utils.helpers;

public class NegociationRequestDetailsOne {

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

	@FindBy(id = "genderFemale")
	private WebElement genderFemaleRadioBtn;

	@FindBy(id = "genderMale")
	private WebElement genderMaleRadioBtn;

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

	@FindBy(id = "statement")
	private WebElement marketTypeStatementRadioBtn;

	@FindBy(id = "independent")
	private WebElement marketTypeIndependentRadioBtn;

	@FindBy(id = "complementary")
	private WebElement marketTypeComplementaryRadioBtn;

	public NegociationRequestDetailsOne(WebDriver driver) {

		PageFactory.initElements(driver, this);

		// validate the correct page is displayed
		// <title>Football Player Negotiation Request</title>
		// <title>Motorsports Player Negotiation Request</title>
		if (!driver.getTitle().endsWith(" Player Negotiation Request")) {

			throw new IllegalStateException(
					"This is not the NegociationRequestDetails page");
		}

		//why initialize the elements here?
		yearLst = new Select(yearWebElement);
		agentLst = new Select(agentWebElement);
		unitLst = new Select(unitWebElement);
		nationalityLst = new Select(nationalityWebElement);
		shoeSiloLst = new Select(shoeSiloWebElement);

	}

	//BAD practice to have so many parameters
	private void SubmitForm(String year, String uploadImagePath, String agent,
			String negotiationUnit, String name, String birthDate,
			Boolean male, Boolean female, String nationality, String position,
			String club, String shoeSilo, Boolean nationalTeamPlayer,
			Boolean topDivisionPlayer, Boolean startingPlayer,
			Boolean marketTypeStatement, Boolean marketTypeIndependent,
			Boolean marketTypeComplementary) {

		// TODO: check how to upload image
//		uploadImageBtn.sendKeys(uploadImagePath);

		yearLst.selectByValue(year);
		agentLst.selectByVisibleText(agent);
		unitLst.selectByVisibleText(negotiationUnit);
		nameFld.sendKeys(name);
		birthDateFld.sendKeys(birthDate);

		//why both? it's a radio button..only one can be selected
		helpers.selectCheckbox(genderMaleRadioBtn, male);
		helpers.selectCheckbox(genderFemaleRadioBtn, female);


/*		switch (gender) {
	      case "male":
	    	  genderMaleRadioBtn.click();
	        break;
	      case "female":
	    	  genderFemaleRadioBtn.click();
	        break;
	      default:
	        System.out.println("Not a correct value");
	        break;
	    }*/

		nationalityLst.selectByVisibleText(nationality);

		positionFld.sendKeys(position);
		clubNameFld.sendKeys(club);

		shoeSiloLst.selectByVisibleText(shoeSilo);

		helpers.selectCheckbox(nationalTeamPlayerCheckbox, nationalTeamPlayer);
		helpers.selectCheckbox(topDivisionPlayerCheckbox, topDivisionPlayer);
		helpers.selectCheckbox(startingPlayerCheckbox, startingPlayer);
		helpers.selectCheckbox(marketTypeStatementRadioBtn, marketTypeStatement);
		helpers.selectCheckbox(marketTypeIndependentRadioBtn,
				marketTypeIndependent);
		helpers.selectCheckbox(marketTypeComplementaryRadioBtn,
				marketTypeComplementary);

		continueBtn.click();
	}

	// TODO update return type when second page class is available
	public void DoFillNegociationRequestDetailsOneValid(String year,
			String uploadImagePath, String agent, String negotiationUnit,
			String name, String birthDate, Boolean male, Boolean female,
			String nationality, String position, String club, String shoeSilo,
			Boolean nationalTeamPlayer, Boolean topDivisionPlayer,
			Boolean startingPlayer, Boolean marketTypeStatement,
			Boolean marketTypeIndependent, Boolean marketTypeComplementary,
			WebDriver driver) {

		SubmitForm(year, uploadImagePath, agent, negotiationUnit, name,
				birthDate, male, female, nationality, position, club, shoeSilo,
				nationalTeamPlayer, topDivisionPlayer, startingPlayer,
				marketTypeStatement, marketTypeIndependent,
				marketTypeComplementary);

		// return new Page(driver);

	}
	
	public String GetValidationMessages(WebDriver driver) {
		
		String messages = "";
					
		for(WebElement errorSpan : driver.findElements(By.className("stripes-error"))) {
			
			messages = messages + errorSpan.getText();
		}
		
		return messages;
	}

	public NegociationRequestDetailsOne DoFillNegociationRequestDetailsOneInvalid(
			String year, String uploadImagePath, String agent,
			String negotiationUnit, String name, String birthDate,
			Boolean male, Boolean female, String nationality, String position,
			String club, String shoeSilo, Boolean nationalTeamPlayer,
			Boolean topDivisionPlayer, Boolean startingPlayer,
			Boolean marketTypeStatement, Boolean marketTypeIndependent,
			Boolean marketTypeComplementary, WebDriver driver) {

		SubmitForm(year, uploadImagePath, agent, negotiationUnit, name,
				birthDate, male, female, nationality, position, club, shoeSilo,
				nationalTeamPlayer, topDivisionPlayer, startingPlayer,
				marketTypeStatement, marketTypeIndependent,
				marketTypeComplementary);
	
		return new NegociationRequestDetailsOne(driver);

	}

}
