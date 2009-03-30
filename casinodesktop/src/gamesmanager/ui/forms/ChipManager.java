package gamesmanager.ui.forms;

import gamesmanager.beans.Client;
import gamesmanager.ui.Helpers;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class ChipManager extends JFrame{
    
    private static final long serialVersionUID = 1L;
    
    private GridBagConstraints c = new GridBagConstraints();
    private JTextField amount;
    private JButton sumar;
    private JButton restar;

    public ChipManager(Client client){
        super("Administrar fichas");
        if(client == null){
            if(Helpers.DEBUG){
                throw new NullPointerException("Cliente nulo");
            }
        }
        this.setLayout(new GridBagLayout());
        c.gridx = 0;
        c.gridy = 0;
        
        JLabel ncliente = new JLabel("Cliente " + client.getFullName());
        JLabel ccliente = new JLabel("<html>Cr"+Helpers.EACUTE+"dito: " + 
                "<b>" + client.getCredito() + "</b></html>");
        c.gridwidth = 2;
        this.add(ncliente, c);
        c.gridy++;
        this.add(ccliente, c);
        
        amount = new JTextField(6);
        sumar = new JButton("Agregar cr"+Helpers.EACUTE+"dito");
        restar = new JButton("Restar cr"+Helpers.EACUTE+"dito");
        
        c.gridwidth = 1;
        c.gridy++;
        c.gridheight = 2;
        this.add(amount, c);
        
        c.gridx++;
        c.gridheight = 1;
        this.add(sumar, c);
        
        c.gridy++;
        this.add(restar, c);
        
        this.getContentPane().setBackground(Helpers.LIGHTBLUE);
        this.pack();
        this.setSize(this.getWidth() + Helpers.XOFFSET, this.getHeight()
                + Helpers.YOFFSET);
        this.setLocationRelativeTo(null);
    }
}
