package gamesmanager.beans;

import gamesmanager.ui.Helpers;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Employee extends Person {

    private Type employeetype;
    private User user;
    private Date hired;
    private Date fired;

    public Employee() {
        super();
    }

    public Employee(String nombres, String appaterno, String apmaterno,
            Character sexo, Date fechanac, Date membersince, String telcasa,
            String telcel, Address address, EmployeeType e, User user) {
        super(nombres, appaterno, apmaterno, sexo, fechanac, membersince,
                telcasa, telcel, address);
        this.employeetype = e;
        this.user = user;
    }

    public Employee(String id, String nombres, String appaterno,
            String apmaterno, Character sexo, Date fechanac, Date membersince,
            String telcasa, String telcel, Address address, EmployeeType e,
            User user) {
        super(id, nombres, appaterno, apmaterno, sexo, fechanac, membersince,
                telcasa, telcel, address);
        this.employeetype = e;
        this.user = user;
    }

    public Type getEmployeetype() {
        return employeetype;
    }

    public void setEmployeetype(Type employeetype) {
        this.employeetype = employeetype;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Date getHired() {
        return hired;
    }

    public void setHired(Date hired) {
        this.hired = hired;
    }

    public void setHired(String hired) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            this.hired = sdf.parse(hired);
        } catch (ParseException e) {
            System.out.println("wrong hired date");
            if (Helpers.DEBUG) {
                e.printStackTrace();
            }
        }
    }

    public Date getFired() {
        return fired;
    }

    public void setFired(Date fired) {
        this.fired = fired;
    }

    public void setFired(String fired) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            this.fired = sdf.parse(fired);
        } catch (ParseException e) {
            System.out.println("wrong fired date");
            if (Helpers.DEBUG) {
                e.printStackTrace();
            }
        }
    }

    public String toString() {
        return this.getNombres();
    }

}
