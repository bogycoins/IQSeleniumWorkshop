//Catalin
package datamodel;

public class DataFilterNegociationsComponent {
	
	public String requestName;
	public String negociationStatus;
	public String licensingStatus;
	public String startDate;
	public String endDate;
	
	public DataFilterNegociationsComponent(String requestName, String negociationStatus, String licensingStatus, 
			String startDate, String endDate) {
		
		this.requestName = requestName;
		this.negociationStatus = negociationStatus;
		this.licensingStatus = licensingStatus;
		this.startDate = startDate;
		this.endDate = endDate;
	}

}
