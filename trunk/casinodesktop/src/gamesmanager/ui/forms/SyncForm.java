package gamesmanager.ui.forms;

import gamesmanager.ui.Helpers;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;

public class SyncForm extends JFrame implements ActionListener {

    private static final long serialVersionUID = 1L;

    public SyncForm() {
        super("Syncronizar informaci" + Helpers.OACUTE + "n");
        Helpers.setDefaultAppearance(this, true);
        this.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();

        //admin sucursales 
        
        this.getContentPane().setBackground(Helpers.LIGHTBLUE);
        this.pack();
        this.setSize(this.getWidth() + Helpers.XOFFSET, this.getHeight()
                + Helpers.YOFFSET);
        this.setLocationRelativeTo(null);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }

}
