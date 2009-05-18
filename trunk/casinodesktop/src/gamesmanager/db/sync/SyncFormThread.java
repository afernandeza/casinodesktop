package gamesmanager.db.sync;

import gamesmanager.ui.Helpers;
import gamesmanager.ui.forms.SyncForm;

import javax.swing.JFrame;
import javax.swing.JProgressBar;
import javax.swing.SwingWorker;

public class SyncFormThread extends SwingWorker<Void, Void>{
    
        private SyncForm sf;
        private JFrame progress;
        private Object[][] o = null;
        
        @Override
        protected Void doInBackground() throws Exception {
            progress = new JFrame("Buscando servidores...");
            Helpers.setIcon(progress);
            JProgressBar pb = new JProgressBar();
            pb.setIndeterminate(true);
            progress.add(pb);
            progress.pack();
            progress.setSize(progress.getWidth() + 100, progress.getHeight());
            progress.setLocationRelativeTo(null);
            progress.setVisible(true);
            o = CasinoManager.getCasinosTable();
            return null;
        }
        
        @Override
        public void done(){
            progress.dispose();
            sf = new SyncForm(o);
            sf.setVisible(true);
        }
}
