package gamesmanager.ui;

import gamesmanager.db.SynchronizeDatabase;

import java.awt.FlowLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JProgressBar;

public class TrackProgress extends ProgressJFrame {

	private static final long serialVersionUID = 1L;
	
	private JProgressBar current;
    int num = 0;

    public TrackProgress() {
        super("Progreso de sincronizaci" + Helpers.OACUTE + "n");
        JPanel pane = new JPanel();
        pane.setLayout(new FlowLayout());
        current = new JProgressBar(0, 100);
        current.setValue(0);
        current.setStringPainted(true);
        pane.add(current);
        setContentPane(pane);
        this.setResizable(false);
        this.pack();
        this.setLocationRelativeTo(null);
    }


    public static void main(String[] arguments) {
    	TrackProgress frame = new TrackProgress();
    	SynchronizeDatabase db = new SynchronizeDatabase(frame);
        db.start();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        System.out.println("another thread running");
   }

	@Override
	public void setProgress(int progress) {
		current.setValue(progress);
	}

	@Override
	public void signalError() {
		this.setVisible(false);
		this.dispose();
	}

	@Override
	public void signalSuccess() {
		this.setVisible(false);
		this.dispose();
	}
}