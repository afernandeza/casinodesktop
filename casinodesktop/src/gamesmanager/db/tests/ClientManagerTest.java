package gamesmanager.db.tests;

import gamesmanager.beans.Client;
import gamesmanager.db.ClientManager;
import junit.framework.Assert;

import org.junit.Test;

public class ClientManagerTest {

    @Test
    public void testInsert() {
        Client c = new Client();

        Assert.assertTrue(ClientManager.insertClient(c));
    }

}
