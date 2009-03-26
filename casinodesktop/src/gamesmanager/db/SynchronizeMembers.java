package gamesmanager.db;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.Random;

import javax.swing.JProgressBar;
import javax.swing.SwingWorker;

public class SynchronizeMembers extends SwingWorker<Void, Void> implements
        PropertyChangeListener {

    private JProgressBar pb;

    public SynchronizeMembers(JProgressBar pb) {
        this.pb = pb;
        this.addPropertyChangeListener(this);
    }

    @Override
    protected Void doInBackground() throws Exception {
        Random random = new Random();
        int progress = 0;
        setProgress(0);
        try {
            Thread.sleep(1000 + random.nextInt(2000));
        } catch (InterruptedException ignore) {

        }
        while (progress < 100) {
            try {
                Thread.sleep(random.nextInt(1000));
            } catch (InterruptedException ignore) {
            }
            progress += random.nextInt(10);
            setProgress(Math.min(progress, 100));
        }
        return null;
    }

    @Override
    protected void done() {
        this.pb.setVisible(false);
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if (evt.getPropertyName().equals("progress")) {
            int progress = (Integer) evt.getNewValue();
            this.pb.setIndeterminate(false);
            this.pb.setValue(progress);
        }
    }

}
