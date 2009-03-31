package gamesmanager.db.tests;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import gamesmanager.beans.Address;
import gamesmanager.beans.Employee;
import gamesmanager.beans.EmployeeType;
import gamesmanager.beans.User;
import gamesmanager.db.EmployeeManager;

import java.io.File;

import org.junit.Assert;
import org.junit.Test;

public class EmployeeManagerTest {

    @Test
    public void testInsertEmployee() {
        Employee c = new Employee();
        c.setNombres("Bastard");
        c.setAppaterno("Fern‡ndez");
        c.setApmaterno("Acu–a");
        c.setSexo("M");
        c.setFechanac("1986-08-31");
        c.setFoto(new File("/Users/afa/Desktop/pics/8423.729.486.jpg"));
        c.setTelcasa("5536051150");
        c.setTelcel("0445554534335");
        
        Address d = new Address();
        d.setCallenum("av club de golf lomas 8");
        d.setNumint("mz 3 lt 7");
        d.setColonia("greenhouse");
        d.setMunicipio("huixquilucan");
        d.setCodigopostal("52779");
        d.setEstado("Edo Mex");
        d.setPais("Mexico");
        
        c.setAddress(d);
        
        User u = new User("afa", "afameister");
        c.setUser(u);
        
        EmployeeType et = new EmployeeType(1);
        c.setEmployeetype(et);
        
        Assert.assertTrue(EmployeeManager.insertEmployee(c));
    }

    @Test
    public void testFindEmployee() {
        assertNull(EmployeeManager.findEmployee("asdf"));
    }

    @Test
    public void testFindEmployee2() {
        assertNotNull(EmployeeManager.findEmployee("SUCA_E17"));
    }

}
