package gamesmanager.db.tests;

import gamesmanager.db.DatabaseOperations;

import org.junit.Test;

public class DatabaseOperationsTest {

	@Test
	public void testConnect() {
	}
	
	@Test
	public void testGetTypes(){
		System.out.println(DatabaseOperations.getEmployeeTypes());
	}

}
