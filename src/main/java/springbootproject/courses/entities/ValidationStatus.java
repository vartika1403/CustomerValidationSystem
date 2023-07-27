package springbootproject.courses.entities;

public enum ValidationStatus {
	IN_PROGRESS("In Progress"),
	REJECTED("Rejected"),
	ACCEPTED("Accepted"),
	NOT_STARTED("Not Started");
	
	 private String value;
	 public String getValue() {
	    return value;
	   }
	 private ValidationStatus(String value) {
	  this.value = value;
	 } 
}
