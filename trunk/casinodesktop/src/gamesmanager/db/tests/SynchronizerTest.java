package gamesmanager.db.tests;

import gamesmanager.db.Synchronizer;

import org.junit.Test;

public class SynchronizerTest {

    @Test
    public void testSync(){
        Synchronizer.sync();
    }
}
