//Catalin
package datamodel;

public class DataFilterNegociationsComponent {
	
	private String requestName;
	private String negociationStatus;
	private String licensingStatus;
	private String startDate;
	private String endDate;
	
	public String getRequestName() {
		return requestName;
	}

	public void setRequestName(String requestName) {
		this.requestName = requestName;
	}

	public String getNegociationStatus() {
		return negociationStatus;
	}

	public void setNegociationStatus(String negociationStatus) {
		this.negociationStatus = negociationStatus;
	}

	public String getLicensingStatus() {
		return licensingStatus;
	}

	public void setLicensingStatus(String licensingStatus) {
		this.licensingStatus = licensingStatus;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public DataFilterNegociationsComponent(String requestName, String negociationStatus, String licensingStatus, 
			String startDate, String endDate) {
		
		setRequestName(requestName);
		setNegociationStatus(negociationStatus);
		setLicensingStatus(licensingStatus);
		setStartDate(startDate);
		setEndDate(endDate);
	}

}
