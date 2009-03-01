package gamesmanager.ui.forms;

import gamesmanager.beans.Member;
import gamesmanager.db.DatabaseOperations;
import gamesmanager.db.SynchronizeMembers;
import gamesmanager.ui.Helpers;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JTextField;

public class CheckMemberForm extends JPanel implements KeyListener{

	private static final long serialVersionUID = 1L;
	private JTextField memberid;
	private JProgressBar syncpb;
	private GridBagConstraints c = new GridBagConstraints();
	
	public CheckMemberForm(){
		super(new GridBagLayout());
		c.gridx = 0;
		c.gridy = 0;
		
		this.add(new JLabel("<html><b>Miembro #: </b></html>"), c);
		c.gridx++;
		
		memberid = new JTextField(20);
		this.add(memberid, c);
		
		syncpb = new JProgressBar(0, 100);
		syncpb.setValue(0);
		syncpb.setStringPainted(true);
		c.gridy = 1;
		c.gridx = 0;
		c.gridwidth = 2;
		
		this.add(new JLabel(" "), c);
		c.gridy = 2;
		this.add(syncpb, c);	
		syncpb.setVisible(false);
		
		this.setBackground(Helpers.LIGHTBLUE);
		this.memberid.addKeyListener(this);
	}
	
	public void startSync(){
		syncpb.setVisible(true);
		SynchronizeMembers sm = new SynchronizeMembers(this.syncpb);
		sm.execute();
	}
	
	public void setFocus(){
		memberid.requestFocus();
	}
	
	public void findMember(){
		System.out.println("finding member");
		Member m = DatabaseOperations.findMember(this.memberid.getText());
		if(m != null){
			System.out.println("member found");
			MemberInfoForm form = new MemberInfoForm(m);
			form.setVisible(true);
		} else {
			System.out.println("member not found");
		}
	}
	
	@Override
	public void keyPressed(KeyEvent e) {
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_ENTER){
			this.findMember();
		}		
	}

	@Override
	public void keyTyped(KeyEvent e) {		
	}

}
