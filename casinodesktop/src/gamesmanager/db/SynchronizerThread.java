package gamesmanager.db;

import gamesmanager.ui.Helpers;

import javax.swing.JFrame;
import javax.swing.JProgressBar;
import javax.swing.SwingWorker;

public class SynchronizerThread extends SwingWorker<Void, Void> {
    
    private JFrame progress;
    
    @Override
    protected Void doInBackground() throws Exception {
        progress = new JFrame("Enviando informaci" + Helpers.OACUTE +"n...");
        Helpers.setIcon(progress);
        JProgressBar pb = new JProgressBar();
        pb.setIndeterminate(true);
        progress.add(pb);
        progress.pack();
        progress.setSize(progress.getWidth() + 100, progress.getHeight());
        progress.setLocationRelativeTo(null);
        progress.setVisible(true);
        Synchronizer.sync();
        return null;
    }
    
    @Override
    public void done(){
        progress.dispose();
        System.out.println("sync done");
    }
}
