package pageobjects;

import java.util.List;
import utils.*;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.internal.selenesedriver.FindElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import datamodel.DataNegociationRequestDetailsTwo;

import java.awt.AWTException;

public class NegotiationRequestDetailsTwoPage {

	private final WebDriver driver;

	private Select yearLst;
	private Select monthLst;
	private Select budgetYearLst;

	// identify page elements

	@FindBy(id = "back_create_deal")
	private WebElement backButton;

	// private WebElement saveAsDraftButton;
	@FindBy(id = "saveAsDraft")
	private WebElement saveAsDraftButton;

	// private WebElement submitForApproval;
	@FindBy(id = "submit")
	private WebElement submitForApproval;
	// private WebElement dealCheckBoxfield;
	@FindBy(id = "sponsorshipAndLicensingCheckbox")
	private WebElement dealCheckBoxfield;

	// private WebElement inputMinGuarantee;
	@FindBy(css = "#minimumGuarantee + input")
	private WebElement inputMinGuarantee;

	// private WebElement currencySelector;
	@FindBy(id = "minimumGuarantee_currency_selector")
	private WebElement currencySelector;
	@FindBy(xpath = "//*[@id='royaltyRatesTable']/tbody/tr/td[2]/input[2]")
	private WebElement royaltyRate;

	@FindBy(xpath = "//*[@id='royaltyRatesTable']/tbody/tr/td[2]/input[2]")
	private WebElement addRoyaltyRate;

	// private WebElement subLicensingYes;
	@FindBy(id = "sub-licensing-yes")
	private WebElement subLicensingYes;

	// private WebElement subLicensingNo;
	@FindBy(id = "sub-licensing-no")
	private WebElement subLicensingNo;

	// private WebElement masterLicenseYes;
	@FindBy(id = "master-license-yes")
	private WebElement masterLicenseYes;

	// private WebElement masterLicenseNo;
	@FindBy(id = "master-license-no")
	private WebElement masterLicenseNo;

	// private WebElement dealStartDate;
	@FindBy(id = "dealStartDate")
	private WebElement dealStartDate;

	// private WebElement dealEndDate;
	@FindBy(id = "dealEndDate")
	private WebElement dealEndDate;

	// identify calendar fields
	@FindBy(id = "ui-datepicker-div")
	private WebElement calendar;

	@FindBy(id = "deal_details_table")
	private WebElement dealTable;

	// identify retainer fields

	@FindBy(css = "#retainerCurrencyAmount_0 + input")
	private WebElement retainer1;

	@FindBy(css = "#retainerCurrencyAmount_0_currency_selector")
	private WebElement retainerCostCurrency;

	@FindBy(css = "#retainerCurrencyAmount_0_in_euro")
	private WebElement retainerResult;

	// identify Product Cost fields

	@FindBy(css = "#productCostAmount_0 + input")
	private WebElement productCost1;

	@FindBy(css = "#retainerCurrencyAmount_0_currency_selector")
	private WebElement productCostCurrency;

	@FindBy(css = "#productCostAmount_0_in_euro")
	private WebElement productResult;

	// identify Bonus fields
	@FindBy(css = "#bonusCurrencyAmount_0 + input")
	private WebElement bonus1;

	@FindBy(css = "#bonusCurrencyAmount_0_currency_selector")
	private WebElement bonusCurrency;

	@FindBy(css = "#bonusCurrencyAmount_0_in_euro_error")
	private WebElement bonusResult;

	@FindBy(id = "year")
	private WebElement budgetYear;

	@FindBy(css = "#uploadAttachmentButton")
	private WebElement uploadDealContainer;
	// private WebElement uploadDealContainer =
	// dealSheetContainer.findElement(By.id("input-upload-attachment"));

	@FindBy(css = "#profit_loss_sheet_container input#input-upload-attachment")
	private WebElement uploadProfitLossContainer;

	@FindBy(css = "#draft_LOI_sheet_container input#input-upload-attachment")
	private WebElement uploadDraftLOIContainer;

	// end identify page elements

	// Select selectStart/EndDate
	private void selectStartEndDate(String startEndDate) {
		if (startEndDate.equalsIgnoreCase("clickStart"))
			dealStartDate.click();
		else if (startEndDate.equalsIgnoreCase("clickEnd"))
			dealEndDate.click();
		else
			return;
	}// end Select selectStart/EndDate

	// select calendar date
	private void selectCalendarDate(String day, String month, String year) {

		WebElement monthStart = calendar.findElement(By.className("ui-datepicker-month"));
		WebElement yearStart = calendar.findElement(By.className("ui-datepicker-year"));
		List<WebElement> dateStartList = calendar.findElements(By.tagName("a"));

		int day1 = Integer.parseInt(day);

		yearLst = new Select(yearStart);
		monthLst = new Select(monthStart);

		// select year and month

		yearLst.selectByVisibleText(year);
		monthLst.selectByVisibleText(month);

		WebElement dayToSelect = calendar.findElement(By.linkText(day));
		dayToSelect.click();

	} // end select calendar date

	// select sublicence
	private void selectSubLicence(String subLicence) {
		if (subLicence != null) {
			if (subLicence.equals("yes"))
				subLicensingYes.click();
			else if (subLicence.equals("no"))
				subLicensingNo.click();
			else
				throw new IllegalArgumentException(String.format(
						"No constant with text <%s> found", subLicence));
		} else
			return;
	}// end select sublicence

	// select master licence
	private void selectMasterLicence(String MasterLicence) {
		if (MasterLicence != null) {
			if (MasterLicence.equals("yes"))
				masterLicenseYes.click();
			else if (MasterLicence.equals("no"))
				masterLicenseNo.click();
			else
				throw new IllegalArgumentException(String.format(
						"No constant with text <%s> found", MasterLicence));
		} else
			return;
	}// end select master licence

	// NegotiationRequestDetailsTwoPage constructor
	public NegotiationRequestDetailsTwoPage(WebDriver driver) {

		PageFactory.initElements(driver, this);

		this.driver = driver;// use this.driver so we explicitly refer to class
								// driver field
		Assert.assertTrue(this.submitForApproval.isDisplayed());
	} // end NegotiationRequestDetailsTwoPage constructor

	// fill Deal Text Fields
	private void fillDealTextFields(String dealCheckBox, String minGuarantee,String currency, String royRate, String subLicencing,String masterLicense) {
		Select selectCurrency;
		selectCurrency = new Select(currencySelector);

		// if(dealCheckBox == "true"){
		if (dealCheckBox.equals("true")) {
			dealCheckBoxfield.click();
			inputMinGuarantee.clear();
			inputMinGuarantee.sendKeys(minGuarantee);
			selectCurrency.selectByVisibleText(currency);
			royaltyRate.clear();
			royaltyRate.sendKeys(royRate);
			selectSubLicence(subLicencing);
			selectMasterLicence(masterLicense);
		}
	} // end fill Deal Text Fields

	// select budget year
	private void selectBudgetYear(String year1) {
		budgetYearLst = new Select(budgetYear);
		budgetYearLst.selectByVisibleText(year1);

	} // end select budget year

	private void uploadField(String sectionSheet) throws AWTException {
		if (sectionSheet == null) {
			throw new IllegalArgumentException(
					String.format("No section sheet inputed"));
		}
		switch (sectionSheet) {
		case "DealSheet":
			uploadDealContainer.click();
			break;
		case "Profit_and_Loss_Sheet":
			uploadProfitLossContainer.click();
			break;
		case "Draft_Letter_Of_Intent_Sheet":
			uploadDraftLOIContainer.click();
			break;
		default:
			throw new IllegalArgumentException(
					String.format("No section sheet matching"));
		}
		Utils.uploadFile(Constants.file_path);

	} // end upload file method

	/*
	 * private void submitData(String button1) {
	 * 
	 * switch(button1){ case "SaveDraft": saveAsDraft(); break; case
	 * "SubmitForApprouval": submitForApproval.click(); break; case "Back":
	 * backButton.click(); break; default: throw new
	 * IllegalArgumentException(String.format("No button selected")); } }
	 */// end

	public DashboardPage saveAsDraft() {
		saveAsDraftButton.click();
		return new DashboardPage(driver);
	}

	public FilterNegociationsComponent submitPage() {
		submitForApproval.click();

		// TO DO identify confirmation pop-up button

		return new FilterNegociationsComponent(driver);
	}

	public NegociationRequestDetailsOnePage backPage() {
		backButton.click();
		return new NegociationRequestDetailsOnePage(driver);
	}

	private void fillRetProd(String retainer, String retainerCur, String prodCost, String prodCur, String bonusCost, String bonusCur) {
		Select selectCurrencyRet = new Select(retainerCostCurrency);
		Select selectCurrencyProd = new Select(productCostCurrency);
		Select selectCurrencyBon = new Select(bonusCurrency);
		// fill retainer
		//retainer1.click();
		retainer1.clear();
		retainer1.sendKeys(retainer);
		// select ret currency
		selectCurrencyRet.selectByVisibleText(retainerCur);
		// fill product cost
		//productCost1.click();
		productCost1.clear();
		productCost1.sendKeys(prodCost);
		// select prod currency
		selectCurrencyProd.selectByVisibleText(prodCur);
		// fill bonus cost
		//bonus1.click();
		bonus1.clear();
		bonus1.sendKeys(bonusCost);
		// select bonus currency
		selectCurrencyBon.selectByVisibleText(bonusCur);
	} // end fillRetProd Text Fields

	public void fillNegociationRequestDetailsTwoPage(DataNegociationRequestDetailsTwo dataDetailsTwo) {

		fillDealTextFields(dataDetailsTwo.getDealCheckBoxfield(), dataDetailsTwo.getInputMinGuarantee(),dataDetailsTwo.getCurrencySelector(), dataDetailsTwo.getRoyaltyRate(),dataDetailsTwo.getSubLicensing(), dataDetailsTwo.getMasterLicense());
		
		selectStartEndDate(dataDetailsTwo.getDealStartDate());
		
		selectCalendarDate(dataDetailsTwo.getDayStart(),dataDetailsTwo.getMonthStart(), dataDetailsTwo.getYearStart());
		
		selectStartEndDate(dataDetailsTwo.getDealEndDate());
		
		selectCalendarDate(dataDetailsTwo.getDayEnd(),dataDetailsTwo.getMonthEnd(), dataDetailsTwo.getYearEnd());
		fillRetProd(dataDetailsTwo.getRetainer1(),dataDetailsTwo.getRetainerCurrency(),dataDetailsTwo.getProductCost1(),dataDetailsTwo.getProductCostCurrency(),dataDetailsTwo.getBonus1(), dataDetailsTwo.getBonusCurrency());
		//selectBudgetYear(dataDetailsTwo.getBudgetYear());

		String sectionSheet;
		sectionSheet = "DealSheet";
		try {
			uploadField(sectionSheet);
		} catch (AWTException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
