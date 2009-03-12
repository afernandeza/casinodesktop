package gamesmanager.beans;

import java.util.Date;

public class Employee extends Person{

	private EmployeeType employeetype;
	private User user;
	
	public Employee(){
		super();
	}
	
	public Employee(String nombres, String appaterno, String apmaterno,
			Double credito, Character sexo, Date fechanac, Date membersince,
			String telcasa, String telcel, Address address, EmployeeType e,
			User user) {
		super(nombres, appaterno, apmaterno, credito, sexo, fechanac, membersince,
				telcasa, telcel, address);
		this.employeetype = e;
		this.user = user;
	}

	public Employee(String id, String nombres, String appaterno,
			String apmaterno, Double credito, Character sexo, Date fechanac,
			Date membersince, String telcasa, String telcel, Address address,
			EmployeeType e, User user) {
		super(id, nombres, appaterno, apmaterno, credito, sexo, fechanac, membersince,
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
	
}
