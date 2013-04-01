package datamodel;

public class DataNegociationRequestDetailsTwo {
 
	//TODO: the fields of a data model should be private
	//they should only be accessible by get/setField
	//CREATE SETTERS/GETTERS - right click on this class, select Source -> Generate setters/getters	 
    public String dealCheckBoxfield;
	public String inputMinGuarantee;
	public String currencySelector;	
	public String royaltyRate;
	public String subLicensing;
	public String masterLicense;
	public String dealStartDateBox;
	public String dealStartDate;
	public String dealEndDate;
	public String monthStart;
	public String yearStart;
	public String dayStart;	
	public String monthEnd;
	public String yearEnd;
	public String dayEnd;
	public String retainer1;
	public String retainerCurrency;	
	public String productCost1;	
	public String productCostCurrency;	
	public String bonus1;
	public String bonusCurrency;
	public String budgetYear;
	
	public DataNegociationRequestDetailsTwo(String dealCheckBoxfield, String inputMinGuarantee, String currencySelector,
			String royaltyRate, String subLicensing, String masterLicense,
	 String dealStartDate, String dealEndDate, String monthStart, String yearStart, String dayStart, String monthEnd, String yearEnd,
	 String dayEnd, String retainer1, String retainerCurrency, String productCost1, String productCostCurrency, String bonus1,
	 String bonusCurrency, String budgetYear) {
				
		this.dealCheckBoxfield = dealCheckBoxfield;
		this.inputMinGuarantee = inputMinGuarantee;
		this.currencySelector = currencySelector;
		this.royaltyRate = royaltyRate;
		this.subLicensing = subLicensing;
		this.masterLicense = masterLicense;
		this.dealStartDate = dealStartDate;
		this.dealEndDate = dealEndDate;
		this.monthStart = monthStart;
		this.yearStart = yearStart;
		this.dayStart = dayStart;
		this.monthEnd = monthEnd;
		this.yearEnd = yearEnd;
		this.dayEnd = dayEnd;		
		this.retainer1 = retainer1;
		this.retainerCurrency = retainerCurrency;	
		this.productCost1 = productCost1;	
		this.productCostCurrency= productCostCurrency;	
		this.bonus1 = bonus1;
		this.bonusCurrency = bonusCurrency;
		this.budgetYear = budgetYear;

	}

}
