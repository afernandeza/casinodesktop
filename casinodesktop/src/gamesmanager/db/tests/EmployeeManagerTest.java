package gamesmanager.db.tests;

import static org.junit.Assert.*;
import junit.framework.Assert;
import gamesmanager.db.EmployeeManager;

import org.junit.Test;

public class EmployeeManagerTest {

	@Test
	public void testInsertEmployee() {
		fail("Not yet implemented");
	}

	@Test
	public void testFindEmployee() {
		Assert.assertNull(EmployeeManager.findEmployee("asdf"));
	}
	
	@Test
	public void testFindEmployee2() {
		Assert.assertNotNull(EmployeeManager.findEmployee("SUCA_E17"));
	}

}
	