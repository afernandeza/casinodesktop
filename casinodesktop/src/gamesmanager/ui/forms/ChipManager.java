package gamesmanager.ui.forms;

import gamesmanager.beans.Client;
import gamesmanager.db.ClientManager;
import gamesmanager.ui.Helpers;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class ChipManager extends JFrame implements ActionListener {

    private static final long serialVersionUID = 1L;

    private static String EDIT_CONFIRMATION = Helpers.OQUESTIONM
    + "Seguro que desea cambiar el cr" + Helpers.EACUTE
    + "dito del cliente" + Helpers.CQUESTIONM;

    private GridBagConstraints c = new GridBagConstraints();
    private JTextField amount;
    private JButton sumar;
    private JButton restar;
    private Client client;
    private BigDecimal validatedAmount;

    public ChipManager(Client client) {
        super("Administrar fichas");
        if (client == null) {
            if (Helpers.DEBUG) {
                throw new NullPointerException("Cliente nulo");
            }
        }
        this.client = client;
        this.setLayout(new GridBagLayout());
        c.gridx = 0;
        c.gridy = 0;

        JLabel ncliente = new JLabel("<html><b>Cliente " + client.getFullName()
                + "</b></html>");
        c.gridwidth = 2;
        this.add(ncliente, c);

        JLabel ccliente = new JLabel("<html><b>Cr" + Helpers.EACUTE + "dito: "
                + client.getCredito() + "</b></html>");
        amount = new JTextField(6);
        sumar = new JButton("Agregar cr" + Helpers.EACUTE + "dito");
        sumar.addActionListener(this);
        restar = new JButton("Restar cr" + Helpers.EACUTE + "dito");
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

    public boolean validateForm() {
        String s = this.amount.getText();
        try {
            BigDecimal bd = new BigDecimal(s);
            this.validatedAmount = bd.setScale(2, BigDecimal.ROUND_HALF_UP);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (this.validateForm()) {
            Object o = e.getSource();
            if (sumar.equals(o) || restar.equals(o)) {
                int n = JOptionPane.showConfirmDialog(null, EDIT_CONFIRMATION,
                        "Confirmar", JOptionPane.YES_NO_OPTION);
                System.out.println("n: " + n);
                if (n == 0) {
                    boolean result = false;
                    if(sumar.equals(o)){
                        result = ClientManager.editCredit(this.client.getId(),
                                this.validatedAmount);
                    } else {
                        result = ClientManager.editCredit(this.client.getId(),
                                this.validatedAmount.negate());
                    }
                    if (result) {
                        JOptionPane.showMessageDialog(null, "Cr"
                                + Helpers.EACUTE + "dito de "
                                + client.getFullName() + " editado.",
                                "Informaci" + Helpers.OACUTE + "n",
                                JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        JOptionPane.showMessageDialog(null,
                                "Cr" + Helpers.EACUTE + "dito de "
                                + client.getFullName()
                                + " no ha sido editado.", "Informaci"
                                + Helpers.OACUTE + "n",
                                JOptionPane.INFORMATION_MESSAGE);
                    }
                }
            }
        }
    }
}