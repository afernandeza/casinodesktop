package gamesmanager.ui.forms;

import gamesmanager.beans.Client;
import gamesmanager.db.ClientManager;
import gamesmanager.db.SynchronizeMembers;
import gamesmanager.ui.GuiDialogs;
import gamesmanager.ui.Helpers;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JTextField;

public class CheckClientForm extends JPanel implements KeyListener {

    private static final long serialVersionUID = 1L;
    private JTextField memberid;
    private JLabel errormsg;
    private JProgressBar syncpb;
    private GridBagConstraints c = new GridBagConstraints();
    public String[] OPTIONS = {
            "Actualizar informaci" + Helpers.OACUTE + "n personal",
            "Administrar fichas", "Dar de baja" };
    public String INSTRUCTIONS = "Cliente encontrado, " + "seleccione qu"
    + Helpers.EACUTE + " desea hacer:";
    public String DELETE_CONFIRMATION = Helpers.OQUESTIONM + "Est"
    + Helpers.AACUTE + " seguro que desea borrar al miembro ";

    public CheckClientForm() {
        super(new GridBagLayout());
        c.gridx = 0;
        c.gridy = 0;

        this.errormsg = new JLabel("El miembro buscado no existe.");
        this.errormsg.setForeground(Helpers.LIGHTBLUE);
        this.c.gridwidth = 2;
        this.add(errormsg, c);
        this.c.gridy++;
        this.c.gridwidth = 1;
        
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

    public void startSync() {
        syncpb.setVisible(true);
        SynchronizeMembers sm = new SynchronizeMembers(this.syncpb);
        sm.execute();
    }

    public void setFocus() {
        memberid.requestFocus();
    }

    public void findMember() {
        if (!this.memberid.getText().trim().equals("")) {
            Client client = ClientManager.findClient(this.memberid.getText());
            if (client != null) {
                this.errormsg.setForeground(Helpers.LIGHTBLUE);
                // member found
                String s = GuiDialogs.showInputDialog(INSTRUCTIONS, OPTIONS, 0);

                if ((s != null) && (s.length() > 0)) {
                    if (s.equals(OPTIONS[0])) {
                        // Actualizar
                        ClientInfoForm form = new ClientInfoForm(client);
                        form.setVisible(true);
                        form.loadCurrentImage();
                    } else if (s.equals(OPTIONS[1])) {
                        // Administrar fichas
                        ChipManager cm = new ChipManager(client);
                        cm.setVisible(true);
                    } else if (s.equals(OPTIONS[2])) {
                        // Dar de baja
                        int n = GuiDialogs
                        .showConfirmDialog(DELETE_CONFIRMATION
                                + client.getFullName()
                                + Helpers.CQUESTIONM);
                        if (n == 0) {
                            // confirmar
                            if (ClientManager.deleteClient(client.getId())) {
                                GuiDialogs.showSuccessMessage(client
                                        .getFullName()
                                        + " borrado.");
                            } else {
                                // cancelar
                                GuiDialogs.showSuccessMessage(client
                                        .getFullName()
                                        + "no ha sido borrado.");
                            }
                        }
                    }
                    return;
                }
            } else {
                this.errormsg.setForeground(Color.RED);
            }
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ENTER) {
            this.findMember();
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

}
