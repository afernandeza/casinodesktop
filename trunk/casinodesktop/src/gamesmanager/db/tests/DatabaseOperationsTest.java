package gamesmanager.db.tests;

import gamesmanager.beans.User;
import gamesmanager.db.DatabaseOperations;
import gamesmanager.db.EmployeeManager;
import junit.framework.Assert;

import org.junit.Test;

public class DatabaseOperationsTest {

    @Test
    public void testConnect() {
    }

    // @Test
    public void testGetTypes() {
        System.out.println(EmployeeManager.getEmployeeTypes());
    }


}
