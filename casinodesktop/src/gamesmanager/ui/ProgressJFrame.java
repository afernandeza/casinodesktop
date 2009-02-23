package gamesmanager.ui;

import javax.swing.JFrame;

public abstract class ProgressJFrame extends JFrame{

	private static final long serialVersionUID = 1L;

	public ProgressJFrame(String title){
		super(title);
	}
	
	public abstract void setProgress(int progress);
	public abstract void signalError();
	public abstract void signalSuccess();

}
