package springbootproject.courses.entities;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Customer {
	@Id
	private long id;
	private String companyName;
	private String legalName;
	private String businessAddress;
	private String legalAddress;
	private String panNo;
	private String email;
	private String website;
	private int noOfSubscriptions;
	private String validationStatus = ValidationStatus.NOT_STARTED.getValue();
	
	
	public Customer() {
		super();
	}
	

	public long getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	public String getLegalName() {
		return legalName;
	}
	public void setLegalName(String legalName) {
		this.legalName = legalName;
	}
	public String getBusinessAddress() {
		return businessAddress;
	}
	public void setBusinessAddress(String businessAddress) {
		this.businessAddress = businessAddress;
	}
	public String getLegalAddress() {
		return legalAddress;
	}
	public void setLegalAddress(String legalAddress) {
		this.legalAddress = legalAddress;
	}
	public String getPanNo() {
		return panNo;
	}
	public void setPanNo(String panNo) {
		this.panNo = panNo;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getWebsite() {
		return website;
	}
	public void setWebsite(String website) {
		this.website = website;
	}
	public int getNoOfSubscriptions() {
		return noOfSubscriptions;
	}
	public void setNoOfSubscriptions(int noOfSubscriptions) {
		this.noOfSubscriptions = noOfSubscriptions;
	}
	
	public String getValidationStatus() {
		return validationStatus;
	}
	
	public void setValidationStatus(String validationStatus) {
		this.validationStatus = validationStatus;
	}
	

}
