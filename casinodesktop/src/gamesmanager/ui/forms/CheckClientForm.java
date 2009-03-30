package gamesmanager.ui.forms;

import gamesmanager.beans.Client;
import gamesmanager.db.ClientManager;
import gamesmanager.db.SynchronizeMembers;
import gamesmanager.ui.Helpers;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JTextField;

public class CheckClientForm extends JPanel implements KeyListener {

    private static final long serialVersionUID = 1L;
    private JTextField memberid;
    private JProgressBar syncpb;
    private GridBagConstraints c = new GridBagConstraints();
    public String[] OPTIONS = {"Actualizar informaci"+Helpers.OACUTE+"n personal",
            "Administrar fichas", "Dar de baja"};
    public String INSTRUCTIONS = "Cliente encontrado, " +
    		"seleccione qu"+Helpers.EACUTE+" desea hacer:";
    public String DELETE_CONFIRMATION = Helpers.OQUESTIONM+"Est"+Helpers.AACUTE+
            " seguro que desea borrar al miembro ";

    public CheckClientForm() {
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

    public void startSync() {
        syncpb.setVisible(true);
        SynchronizeMembers sm = new SynchronizeMembers(this.syncpb);
        sm.execute();
    }

    public void setFocus() {
        memberid.requestFocus();
    }

    public void findMember() {
        if (!this.memberid.getText().equals("")) {
            System.out.println("finding member");
            Client client = ClientManager.findClient(this.memberid.getText());
            if (client != null) {
                System.out.println("member found");
                
                String s = (String)
                JOptionPane.showInputDialog(null, INSTRUCTIONS, "",
                        JOptionPane.QUESTION_MESSAGE, null, OPTIONS, OPTIONS[0]);

                if ((s != null) && (s.length() > 0)) {
                    if(s.equals(OPTIONS[0])){
                        System.out.println("actualizar");
                      ClientInfoForm form = new ClientInfoForm(client);
                      form.setVisible(true);
                    } else if(s.equals(OPTIONS[1])){
                        System.out.println("admin fichas");
                    } else if(s.equals(OPTIONS[2])){
                        System.out.println("dar baja");
                        int n = JOptionPane.showConfirmDialog(
                                null,
                                DELETE_CONFIRMATION + client.getFullName() +
                                Helpers.CQUESTIONM,
                                "Confirmar",
                                JOptionPane.YES_NO_OPTION);
                        System.out.println("n: " + n ); 
                        if(n == 0){
                            if(ClientManager.deleteClient(client.getId())){
                                JOptionPane.showMessageDialog(null,
                                        client.getFullName() + " borrado.",
                                        "Informaci"+Helpers.OACUTE+"n",
                                        JOptionPane.INFORMATION_MESSAGE);
                            } else {
                                JOptionPane.showMessageDialog(null,
                                        client.getFullName() + " no ha sido borrado.",
                                        "Informaci"+Helpers.OACUTE+"n",
                                        JOptionPane.ERROR_MESSAGE);
                            }
                        } 
                    }
                    
                    return;
                }      
            } else {
                System.out.println("member not found");
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
