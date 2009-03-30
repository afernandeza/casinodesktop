package gamesmanager.db.tests;

import gamesmanager.beans.User;
import gamesmanager.db.DatabaseOperations;
import junit.framework.Assert;

import org.junit.Test;

public class DatabaseOperationsTest {

    @Test
    public void testConnect() {
    }

    // @Test
    public void testGetTypes() {
        System.out.println(DatabaseOperations.getEmployeeTypes());
    }

    @Test
    public void testlogin() {
        Assert.assertTrue(DatabaseOperations.login(new User("ric", "ric")));
    }

    @Test
    public void testlogin2() {
        Assert.assertFalse(DatabaseOperations.login(new User("rec", "rec")));
    }

}
