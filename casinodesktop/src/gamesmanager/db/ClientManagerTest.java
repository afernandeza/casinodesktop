package gamesmanager.db;

import gamesmanager.beans.Client;
import junit.framework.Assert;

import org.junit.Test;

public class ClientManagerTest {

	@Test
	public void testInsert(){
		Client c = new Client();
		
		Assert.assertTrue(ClientManager.insertClient(c));
	}
	
}
