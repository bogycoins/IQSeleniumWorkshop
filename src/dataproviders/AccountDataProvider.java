package dataproviders;

import org.testng.annotations.DataProvider;
import datamodel.Account;
import utils.Constants;
import utils.Utils;


public class AccountDataProvider {

	@DataProvider(name = "Login")
	public static Object[][] login() throws Exception {
		//create an array of strings using data from given file and sheet
		//[["user1", "pass1"], ["user2", "pass2"],["user3", "pass3"]]
		String[][] fileData = Utils.readXLSXFile(Constants.DATA_LOGIN_PAGE, "validUsers");

		//create the object that will be returned; it needs to be an array of arrays
		//it will have the same number of entries as the xls file; each entry will have a single element
		//[[account1], [account2], [account3]]
		Object[][] accounts = new Object[fileData.length][1];

		int i = 0;

		for (String[] row : fileData) {
			//row[0] - email
			//row[1] - password
			Account account = new Account();
			
			account.setEmail(row[0]);
			account.setPassword(row[1]);

			//Alternative
			//Account account = new Account(row[0], row[1]);
			
			accounts[i][0] = account;
			i++;
		}
		return accounts;
	}
}