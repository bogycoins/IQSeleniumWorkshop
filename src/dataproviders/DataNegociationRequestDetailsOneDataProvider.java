package dataproviders;

import org.testng.annotations.DataProvider;

import pageobjects.NegociationRequestDetailsOnePage.genderOptions;
import pageobjects.NegociationRequestDetailsOnePage.marketTypeOptions;

import utils.Constants;
import utils.Utils;
import datamodel.DataNegociationRequestDetailsOne;

public class DataNegociationRequestDetailsOneDataProvider {

	@DataProvider(name = "NegociationRequestDetailsOneProvider")
	public static Object[][] negociationRequestDetailsOne() throws Exception {

		// create an array of strings using data from given file and sheet
		String[][] fileData = Utils.readXLSXFile(
				Constants.DATA_NEGOCIATION_REQUEST_DETAILS_ONE_PAGE_XSLX,
				"sectionOne");

		return fillDataNegociationRequestDetailsOneModel(fileData);
	}

	private static Object[][] fillDataNegociationRequestDetailsOneModel(
			String[][] fileData) {
		// create the object that will be returned; it needs to be an array of
		// arrays
		// it will have the same number of entries as the xls file; each entry
		// will have a single element
		// [[account1], [account2], [account3]]
		Object[][] sectionOneData = new Object[fileData.length][1];

		int i = 0;

		for (String[] row : fileData) {
			// row[0] - year
			// row[1] - uploadImagePath
			// row[2] - agent
			// row[3] - negotiationUnit
			// row[4] - name
			// row[5] - birthDate
			// row[6] - genderOption
			// row[7] - nationality
			// row[8] - position
			// row[9] - club
			// row[10] - shoeSilo
			// row[11] - nationalTeamPlayer
			// row[12] - topDivisionPlayer
			// row[13] - startingPlayer
			// row[14] - marketTypeOption

			// TODO remove hack: 2015 from excel gets here as 2015.0
			String year = row[0].replace(".0", "");
			String uploadImagePath = row[1];
			String agent = row[2];
			String negotiationUnit = row[3];
			String name = row[4];
			String birthDate = row[5];
			genderOptions genderOption = genderOptions.fromString(row[6]);
			String nationality = row[7];
			String position = row[8];
			String club = row[9];
			String shoeSilo = row[10];
			Boolean nationalTeamPlayer = Boolean.valueOf(row[11]);
			Boolean topDivisionPlayer = Boolean.valueOf(row[12]);
			Boolean startingPlayer = Boolean.valueOf(row[13]);
			marketTypeOptions marketTypeOption = marketTypeOptions
					.fromString(row[14]);

			DataNegociationRequestDetailsOne dataNegociationRequestDetailsOne = new DataNegociationRequestDetailsOne(
					year, uploadImagePath, agent, negotiationUnit, name,
					birthDate, genderOption, nationality, position, club,
					shoeSilo, nationalTeamPlayer, topDivisionPlayer,
					startingPlayer, marketTypeOption);

			sectionOneData[i][0] = dataNegociationRequestDetailsOne;
			i++;
		}
		return sectionOneData;
	}
}
