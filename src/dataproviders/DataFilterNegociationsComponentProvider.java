//Catalin
package dataproviders;

import org.testng.annotations.DataProvider;
import utils.Constants;
import utils.Utils;

import datamodel.DataFilterNegociationsComponent;

public class DataFilterNegociationsComponentProvider {
	
	@DataProvider(name = "FilterNegociationsComponentProvider")
	public static Object[][] filterNegociationsComponent() throws Exception {

		String[][] fileData = Utils.readXLSXFile(Constants.DATA_FILTER_NEGOCIATIONS_COMPONENT_XSLX,"negociationFilters");

		return fillDataFilterNegociationsComponenModel(fileData);
	}
	
	private static Object[][] fillDataFilterNegociationsComponenModel(
			String[][] fileData) {

		Object[][] negociationFiltersData = new Object[fileData.length][1];

		int i = 0;

		for (String[] row : fileData) {
			
//			row[0] = requestName;
//			row[1] = negociationStatus;
//			row[2] = licensingStatus;
//			row[3] = startDate;
//			row[4] = startDate;
			
			String requestName = row[0];
			String negociationStatus = row[1];
			String licensingStatus = row[2];
			String startDate = row[3];
			String endDate = row[4];
			
			DataFilterNegociationsComponent dataFilterNegociationsComponent = new DataFilterNegociationsComponent(requestName, 
					negociationStatus, licensingStatus, startDate, endDate);
			
			negociationFiltersData[i][0] = dataFilterNegociationsComponent;
			i++;
		}
		
		return negociationFiltersData;
	}
}
