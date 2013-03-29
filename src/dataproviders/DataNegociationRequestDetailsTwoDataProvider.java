package dataproviders;

import org.testng.annotations.DataProvider;


import datamodel.DataNegociationRequestDetailsTwo;

import utils.Constants;
import utils.Utils;

public class DataNegociationRequestDetailsTwoDataProvider {

	@DataProvider(name = "NegociationRequestDetailsTwoProvider")
	public static Object[][] negociationRequestDetailsTwo() throws Exception {

		// create an array of strings using data from given file and sheet
		String[][] fileData = Utils.readXLSXFile(
				Constants.DATA_NEGOCIATION_REQUEST_DETAILS_TWO_PAGE_XSLX,
				"pageTwo");

		return fillDataNegociationRequestDetailsTwoModel(fileData);
	}
	private static Object[][] fillDataNegociationRequestDetailsTwoModel(
			String[][] fileData) {

		Object[][] pageTwoData = new Object[fileData.length][1];

		int i = 0;

		for (String[] row : fileData) {
			// row[0] -dealCheckBoxfield
			// row[1] - inputMinGuarantee
			// row[2] - currencySelector
			// row[3] - royaltyRate
			// row[4] - subLicensing
			// row[5] - masterLicense
			// row[6] - dealStartDate
			// row[7] - dealEndDate
			// row[8] - monthStart
			// row[9] - yearStart
			// row[10] - dayStart
			// row[11] - monthEnd
			// row[12] - yearEnd
			// row[13] - dayEnd
			// row[14] - retainer1
			// row[15] - retainerCurrency
			// row[16] - productCost1
			// row[17] - productCostCurrency
			// row[18] - bonus1
			// row[19] - bonusCurrency
			// row[20] - budgetYear
					
			String dealCheckBoxfield = row[0];
			String inputMinGuarantee = row[1];
			String currencySelector = row[2];
			String royaltyRate = row[3];
			String subLicensing = row[4];
			String masterLicense = row[5];
			String dealStartDate = row[6];
			String dayStart = row[7];			
			String monthStart = row[8];
			String yearStart = row[9];
			String dealEndDate = row[10];
			String dayEnd = row[11];			
			String monthEnd = row[12];
			String yearEnd = row[13];
			String retainer1 = row[14];		
			String retainerCurrency = row[15];
			String productCost1 = row[16];
			String productCostCurrency = row[17];
			String bonus1 = row[18];
			String bonusCurrency = row[19];
			String budgetYear = row[20];
			
			
			DataNegociationRequestDetailsTwo dataNegociationRequestDetailsTwo = new DataNegociationRequestDetailsTwo(
					dealCheckBoxfield, inputMinGuarantee, currencySelector, royaltyRate, subLicensing,
					masterLicense, dealStartDate, dayStart, monthStart, yearStart,
					dealEndDate, dayEnd, monthEnd,
					yearEnd, retainer1, retainerCurrency, productCost1, productCostCurrency, bonus1, bonusCurrency, budgetYear);

			pageTwoData[i][0] = dataNegociationRequestDetailsTwo;
			i++;
		}
		return pageTwoData;
	}
	
	
}
