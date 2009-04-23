package gamesmanager.db;

import gamesmanager.ui.Helpers;
import gamesmanager.ui.forms.SyncForm;

import javax.swing.JFrame;
import javax.swing.JProgressBar;
import javax.swing.SwingWorker;

public class SyncFormThread extends SwingWorker<Void, Void>{
    
        private SyncForm sf;
        private JFrame progress;
        
        @Override
        protected Void doInBackground() throws Exception {
            progress = new JFrame("Verificando servidores...");
            Helpers.setIcon(progress);
            JProgressBar pb = new JProgressBar();
            pb.setIndeterminate(true);
            progress.add(pb);
            progress.pack();
            progress.setSize(progress.getWidth() + 100, progress.getHeight());
            progress.setLocationRelativeTo(null);
            progress.setVisible(true);
            sf = new SyncForm();
            return null;
        }
        
        @Override
        public void done(){
            progress.dispose();
            sf.setVisible(true);
        }
}
