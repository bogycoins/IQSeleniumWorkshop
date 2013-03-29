package pageobjects;

import java.util.List;
import utils.*;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import java.awt.AWTException;

public class NegotiationRequestDetailsTwoPage {

	private static final String file_path = null;

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

	@FindBy(id = "deal_sheet_container")
	private WebElement dealSheetContainer;
	private WebElement uploadDealContainer = dealSheetContainer.findElement(By.id("input-upload-attachment"));
	
	
	@FindBy(id = "profit_loss_sheet_container")
	private WebElement profitLossSheetContainer;
	private WebElement uploadProfitLossContainer = profitLossSheetContainer.findElement(By.id("input-upload-attachment"));
	
	
	@FindBy(id = "draft_LOI_sheet_container")
	private WebElement draftLOISheetContainer;
	private WebElement uploadDraftLOIContainer = draftLOISheetContainer.findElement(By.id("input-upload-attachment"));
	
	@FindBy(id = "sponsorshipActionConfirmationPopup")
	private WebElement sponsorshipActionConfirmationPopup;
	
	// end identify page elements
	
	
	
	//Select selectStart/EndDate
	private void selectStartEndDate(String startEndDate)
	{
	if (startEndDate == "DealStartDate")
		dealStartDate.click();
		else if (startEndDate == "DealEndDate")
			dealEndDate.click();
		else return;
	}//end Select selectStart/EndDate
	
	
	
	//select calendar date
	private void selectCalendarDate(String day, String month, String year) {
        int day1 =Integer.parseInt(day);
              
		yearLst = new Select(yearStart);
		monthLst = new Select(monthStart);

		// select year and month

		yearLst.selectByValue(year);
		monthLst.selectByValue(month);
     if ((day1 > 0) || (day1<32)){
		for (WebElement element1 : dateStartList) {
			
			if (element1.getAttribute("href") == day);
			element1.click();
			break;
		}
     }
     else throw new IllegalArgumentException(String.format("Invalid date specified: <%s>", day1));
	} //end select calendar date
	
	//select sublicence
		private void selectSubLicence(String subLicence)
		{	
			if (subLicence != null){
			if(subLicence == "YES")	
				subLicensingYes.click();				
				else if (subLicence == "NO")
					subLicensingNo.click();
				else throw new IllegalArgumentException(String.format("No constant with text <%s> found", subLicence));	
		}
			else return;	
		}//end select sublicence
		
		//select master licence
		private void selectMasterLicence(String MasterLicence)
		{	
			if (MasterLicence != null){
			if(MasterLicence == "YES")	
				masterLicenseYes.click();				
				else if (MasterLicence == "NO")
					masterLicenseNo.click();
				else throw new IllegalArgumentException(String.format("No constant with text <%s> found", MasterLicence));	
		}
			else return;	
		}//end select master licence
		
//NegotiationRequestDetailsTwoPage constructor
	public NegotiationRequestDetailsTwoPage(WebDriver driver) {

		PageFactory.initElements(driver, this);

		this.driver = driver;// use this.driver so we explicitly refer to class
								// driver field
		Assert.assertTrue(this.submitForApproval.isDisplayed());
	} //end NegotiationRequestDetailsTwoPage constructor
	
	// fill Deal Text Fields
	  private void fillDealTextFields(String dealCheckBox, String minGuarantee, String currency, String royRate,
	  String subLicencing, String masterLicense) {
		Select  selectCurrency;
		selectCurrency = new Select(currencySelector);  
		  
	  if(dealCheckBox == "true"){
		  dealCheckBoxfield.click(); 
		  inputMinGuarantee.clear();
		  inputMinGuarantee.sendKeys(minGuarantee);
		  selectCurrency.selectByValue(currency);
		  royaltyRate.clear();
		  royaltyRate.sendKeys(royRate);
	      selectSubLicence(subLicencing);
	      selectMasterLicence(masterLicense);		 
	  }	  
	  } // end fill Deal Text Fields
	  
	  //select budget year
	  private void selectBudgetYear(String year1) 
	  {
		  budgetYearLst = new Select(budgetYear);
		  budgetYearLst.selectByValue(year1);
	  	  
	  }  //end select budget year
	  
	 private void uploadField(String file_path) throws AWTException{
		 uploadDealContainer.click();
		 Utils.uploadFile(file_path);
	 } //end upload file method
	 private void submitData() throws AWTException {
		 uploadDealContainer.click();
		Utils.uploadFile(file_path);
	 } //end
}
