package gamesmanager.db.tests;

import gamesmanager.db.sync.Synchronizer;

import org.junit.Test;

public class SynchronizerTest {

    @Test
    public void testSync(){
        Synchronizer.sync();
    }
}
