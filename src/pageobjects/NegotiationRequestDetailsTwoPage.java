package pageobjects;

import java.util.List;

import utils.RadioGroup;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import utils.helpers;

public class NegotiationRequestDetailsTwoPage {

	private final WebDriver driver;

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
	@FindBy(className = "input-text input-number amount-custom-currency")
	private WebElement inputMinGuarantee;

	// private WebElement currencySelector;
	@FindBy(id = "minimumGuarantee_currency_selector")
	private WebElement currencySelector;
	@FindBy(className = "input-text input-number rate")
	private WebElement royaltyRate;

	@FindBy(className = "item-action clickable")
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

	private Select yearLst;
	private Select monthLst;
	private Select budgetYearLst;

	// identify calendar fields
	@FindBy(id = "ui-datepicker-div")
	private WebElement calendar;
	private WebElement monthStart = calendar.findElement(By
			.className("ui-datepicker-month"));
	private WebElement yearStart = calendar.findElement(By
			.className("ui-datepicker-year"));
	private List<WebElement> dateStartList = calendar.findElements(By
			.tagName("a"));

	@FindBy(id = "deal_details_table")
	private WebElement dealTable;

	// identify retainer fields
	private WebElement retainer1 = dealTable
			.findElement(By
					.cssSelector("div[id='retainerCurrencyAmount_0', class='input-text input-number amount-custom-currency']"));
	private WebElement retainerCurrency = dealTable
			.findElement(By
					.cssSelector("div[id='retainerCurrencyAmount_0_currency_selector', name='dealLineItems[0].retainer.currency.id']"));
	private WebElement retainerResult = dealTable.findElement(By
			.className("input-text input-number amount-currency-result"));

	// identify Product Cost fields
	private WebElement productCost1 = dealTable
			.findElement(By
					.cssSelector("div[id='productCostAmount_0', class='input-text input-number amount-custom-currency']"));
	private WebElement productCostCurrency = dealTable
			.findElement(By
					.cssSelector("div[id='retainerCurrencyAmount_0_currency_selector', name='dealLineItems[0].retainer.currency.id']"));
	private WebElement productResult = dealTable.findElement(By
			.className("productCostAmount_0_in_euro_error"));

	// identify Bonus fields
	private WebElement bonus1 = dealTable
			.findElement(By
					.cssSelector("div[id='bonusCurrencyAmount_0', class='input-text input-number amount-custom-currency']"));
	private WebElement bonusCurrency = dealTable
			.findElement(By
					.cssSelector("div[id='bonusCurrencyAmount_0_currency_selector', name='dealLineItems[0].bonus.currency.id']"));
	private WebElement bonusResult = dealTable.findElement(By
			.className("bonusCurrencyAmount_0_in_euro_error"));

	@FindBy(id = "year")
	private WebElement budgetYear;

	private void selectCalendarStartDate(String month, String year, String date) {

		dealStartDate.click();
		yearLst = new Select(yearStart);
		monthLst = new Select(monthStart);

		// select year and month

		yearLst.selectByValue(year);
		monthLst.selectByValue(month);

		for (WebElement element1 : dateStartList) {
			Assert.assertEquals(element1.getAttribute("href"), date);
			element1.click();
			break;
		}
	}

	public NegotiationRequestDetailsTwoPage(WebDriver driver) {

		PageFactory.initElements(driver, this);

		this.driver = driver;// use this.driver so we explicitly refer to class
								// driver field
	}
	/*
	 * private void fillDealTextFields(String minGuarantee, String royRate,
	 * String subLicencing, String masterLicense) {
	 * 
	 * dealCheckBoxfield.click();
	 * 
	 * }
	 */
}
