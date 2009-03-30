package gamesmanager.ui.forms;

import gamesmanager.beans.Client;
import gamesmanager.ui.Helpers;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class ChipManager extends JFrame implements ActionListener{
    
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
        
        JLabel ncliente = new JLabel("<html><b>Cliente " + client.getFullName()+
                            "</b></html>");
        c.gridwidth = 2;
        this.add(ncliente, c);
        
        JLabel ccliente = new JLabel("<html><b>Cr"+Helpers.EACUTE+"dito: " + 
               client.getCredito() + "</b></html>");
        amount = new JTextField(6);
        sumar = new JButton("Agregar cr"+Helpers.EACUTE+"dito");
        sumar.addActionListener(this);
        restar = new JButton("Restar cr"+Helpers.EACUTE+"dito");
        restar.addActionListener(this);
        
        c.gridwidth = 1;
        c.gridy++;
        
        this.add(ccliente, c);
        c.gridy++;
        this.add(amount, c);
        
        c.gridx++;
        c.gridy = 1;
        this.add(sumar, c);
        
        c.gridy++;
        this.add(restar, c);
        
        this.getContentPane().setBackground(Helpers.LIGHTBLUE);
        this.pack();
        this.setSize(this.getWidth() + Helpers.XOFFSET, this.getHeight()
                + Helpers.YOFFSET);
        this.setLocationRelativeTo(null);
    }
    
    public boolean validateForm(){
        String s = this.amount.getText();
        try{
            BigDecimal bd = new BigDecimal(s);
        } catch (NumberFormatException nfe){
            return false;
        }
        return true;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(this.validateForm()){
            Object o = e.getSource();
            if(sumar.equals(o)){
                System.out.println("sumar");
            } else if (restar.equals(o)){
                System.out.println("restar");
            }
        }
        
    }
}
