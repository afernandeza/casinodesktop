package gamesmanager.beans;

public class EmployeeType {

	private final String type;
	
	private EmployeeType(String type){
		this.type = type;
	}
	
	public String toString(){
		return this.type;
	}
	
	public static final EmployeeType MANAGER = new EmployeeType("Manager");
	public static final EmployeeType EMPLOYEE = new EmployeeType("Employee");
	public static final EmployeeType PARTNER = new EmployeeType("Partner");
}
