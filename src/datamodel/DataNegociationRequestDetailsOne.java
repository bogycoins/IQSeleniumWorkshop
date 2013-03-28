package datamodel;

import pageobjects.NegociationRequestDetailsOnePage.genderOptions;
import pageobjects.NegociationRequestDetailsOnePage.marketTypeOptions;

public class DataNegociationRequestDetailsOne {

	public String year;
	public String uploadImagePath;
	public String agent;
	public String negotiationUnit;
	public String name;
	public String birthDate;
	public genderOptions genderOption;
	public String nationality;
	public String position;
	public String club;
	public String shoeSilo;
	public Boolean nationalTeamPlayer;
	public Boolean topDivisionPlayer;
	public Boolean startingPlayer;
	public marketTypeOptions marketTypeOption;

	public DataNegociationRequestDetailsOne(String year, String uploadImagePath,
			String agent, String negotiationUnit, String name,
			String birthDate, genderOptions genderOption, String nationality,
			String position, String club, String shoeSilo,
			Boolean nationalTeamPlayer, Boolean topDivisionPlayer,
			Boolean startingPlayer, marketTypeOptions marketTypeOption) {

		this.year = year;
		this.uploadImagePath = uploadImagePath;
		this.agent = agent;
		this.negotiationUnit = negotiationUnit;
		this.name = name;
		this.birthDate = birthDate;
		this.genderOption = genderOption;
		this.nationality = nationality;
		this.position = position;
		this.club = club;
		this.shoeSilo = shoeSilo;
		this.nationalTeamPlayer = nationalTeamPlayer;
		this.topDivisionPlayer = topDivisionPlayer;
		this.startingPlayer = startingPlayer;
		this.marketTypeOption = marketTypeOption;
	}
}
