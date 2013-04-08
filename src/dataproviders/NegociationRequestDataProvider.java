package dataproviders;

import org.testng.annotations.DataProvider;

public class NegociationRequestDataProvider {

	@DataProvider(name = "NegociationRequestDetails")
	public static Object[][] dp() {
		
		Object[][] obj1 = DataNegociationRequestDetailsOneDataProvider.negociationRequestDetailsOne();
		Object[][] obj2 = DataNegociationRequestDetailsTwoDataProvider.negociationRequestDetailsTwo();
		// obj1 and obj2 should have the same length
		if (obj1.length != obj2.length) {
			throw new IllegalStateException(
					"Files should have the same number of rows");
		}

		Object[][] data = new Object[obj1.length][2];
		int i = 0, j = 0;

		for (Object[] row : obj1) {
			data[i][0] = row[0];
			i++;
		}
		for (Object[] row : obj2) {
			data[j][1] = row[0];
			j++;
		}
		return (data);

	}

}
