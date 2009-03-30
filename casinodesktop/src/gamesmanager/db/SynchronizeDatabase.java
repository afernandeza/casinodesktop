package gamesmanager.db;

import gamesmanager.ui.Helpers;
import gamesmanager.ui.ProgressJFrame;

import javax.swing.JOptionPane;

public class SynchronizeDatabase extends Thread {

    private ProgressJFrame progress;

    public SynchronizeDatabase(ProgressJFrame progress) {
        super();
        this.progress = progress;
    }

    public void error() {
        JOptionPane.showMessageDialog(progress, "El servidor no est"
                + Helpers.AACUTE + " disponible.");
        progress.signalError();
    }

    public void success() {
        JOptionPane.showMessageDialog(progress, "Sincronizaci" + Helpers.OACUTE
                + "n exitosa");
        progress.signalSuccess();
    }

    @Override
    public void run() {
        progress.setVisible(true);
        int n = 0;
        try {
            Thread.sleep(1000);
            n += 33;
            progress.setProgress(n);
            Thread.sleep(1000);
            // if(true){
            // error();
            // return;
            // }
            n += 33;
            progress.setProgress(n);
            Thread.sleep(1000);
            n += 34;
            progress.setProgress(n);
            success();
        } catch (InterruptedException e) {
            if (Helpers.DEBUG) {
                System.out.println("Something went wrong");
                e.printStackTrace();
            }
        }
    }

}
