package gamesmanager.db;

import javax.swing.SwingWorker;

public class SynchronizerThread extends SwingWorker<Void, Void> {
    
    @Override
    protected Void doInBackground() throws Exception {
        Synchronizer.sync();
        return null;
    }
    
    @Override
    public void done(){
        System.out.println("sync done");
    }
}
