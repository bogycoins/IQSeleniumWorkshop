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
		String[][] fileData = Utils.readXLSXFile(Constants.DATA_LOGIN_PAGE_XSLX, "validUsers");

//		//create the object that will be returned; it needs to be an array of arrays
//		//it will have the same number of entries as the xls file; each entry will have a single element
//		//[[account1], [account2], [account3]]
//		Object[][] accounts = new Object[fileData.length][1];
//
//		int i = 0;
//
//		for (String[] row : fileData) {
//			//row[0] - email
//			//row[1] - password
//			Account account = new Account();
//			
//			account.setEmail(row[0]);
//			account.setPassword(row[1]);
//
//			//Alternative
//			//Account account = new Account(row[0], row[1]);
//			
//			accounts[i][0] = account;
//			i++;
//		}
//		return accounts;
		return fillAccountModel(fileData);
	}
	
	@DataProvider(name = "LoginXMLdp")
	public static Object[][] loginXMLdp() throws Exception {
		String[][] fileData = Utils.readXMLFile(Constants.DATA_LOGIN_PAGE_XML);
		return fillAccountModel(fileData);
	}
	
	@DataProvider(name = "LoginCSVdp")
	public static Object[][] loginCSVdp() throws Exception {
		String[][] fileData = Utils.readCSVFile(Constants.DATA_LOGIN_PAGE_CSV, true);
		return fillAccountModel(fileData);
	}
	
	@DataProvider(name = "LoginSQLLitedp")
	public static Object[][] loginSQLLitedp() throws Exception {
		String[][] fileData = Utils.readSQLLite(Constants.DATA_LOGIN_PAGE_SQLLITE, "SELECT EmailAddress, Password FROM UserAccounts");
		return fillAccountModel(fileData);
	}
	
	private static Object[][] fillAccountModel(String[][] fileData)
	{
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