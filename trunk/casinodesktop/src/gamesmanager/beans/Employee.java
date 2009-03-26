package gamesmanager.beans;

import java.util.Date;

public class Employee extends Person{

	private EmployeeType employeetype;
	private User user;
	
	public Employee(){
		super();
	}
	
	public Employee(String nombres, String appaterno, String apmaterno,
			Character sexo, Date fechanac, Date membersince,
			String telcasa, String telcel, Address address, EmployeeType e,
			User user) {
		super(nombres, appaterno, apmaterno, sexo, fechanac, membersince,
				telcasa, telcel, address);
		this.employeetype = e;
		this.user = user;
	}

	public Employee(String id, String nombres, String appaterno,
			String apmaterno, Character sexo, Date fechanac,
			Date membersince, String telcasa, String telcel, Address address,
			EmployeeType e, User user) {
		super(id, nombres, appaterno, apmaterno, sexo, fechanac, membersince,
				telcasa, telcel, address);
		this.employeetype = e;
		this.user = user;
	}

	public EmployeeType getEmployeetype() {
		return employeetype;
	}

	public void setEmployeetype(EmployeeType employeetype) {
		this.employeetype = employeetype;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String toString(){
	    final String TAB = ", ";
	    
	    StringBuffer retValue = new StringBuffer();
	    
	    retValue.append("Employee ( ")
	        .append(super.toString()).append(TAB)
	        .append("employeetype = ").append(this.employeetype).append(TAB)
	        .append("user = ").append(this.user).append(TAB)
	        .append(" )");
	    
	    return retValue.toString();
	}
}
