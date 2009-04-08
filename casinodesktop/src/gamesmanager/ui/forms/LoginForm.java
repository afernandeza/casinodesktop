package gamesmanager.ui.forms;

import gamesmanager.beans.User;
import gamesmanager.db.DatabaseOperations;
import gamesmanager.ui.GUI;
import gamesmanager.ui.GuiDialogs;
import gamesmanager.ui.Helpers;
import gamesmanager.ui.layout.SpringUtilities;
import gamesmanager.ui.session.Session;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SpringLayout;

public class LoginForm extends JPanel implements KeyListener, ActionListener {

    private static final long serialVersionUID = 1L;
    private static final int FIELDSSIZE = 15;

    private GUI mainwindow;
    private JTextField username;
    private JPasswordField password;
    private JLabel errormsg;

    public LoginForm(GUI mainwindow) {
        super();
        this.mainwindow = mainwindow;
        this.setLayout(new GridBagLayout());
        GridBagConstraints centerc = new GridBagConstraints();
        centerc.gridx = 0;
        centerc.gridy = 0;

        errormsg = new JLabel("Usuario o contrase" + Helpers.NTILDE
                + "a incorrecta.");
        errormsg.setForeground(Helpers.LIGHTBLUE);

        centerc.gridy++;
        this.add(errormsg);
        
        JPanel loginform = new JPanel(new SpringLayout());
        String[] labels = { "<html><b>Usuario:</b></html>",
                "<html><b>Contrase" + Helpers.NTILDE + "a:</b></html>" };
        int numPairs = labels.length;

        JLabel l = new JLabel(labels[0], JLabel.TRAILING);
        loginform.add(l);
        username = new JTextField(FIELDSSIZE);
        l.setLabelFor(username);
        loginform.add(username);

        l = new JLabel(labels[1], JLabel.TRAILING);
        loginform.add(l);
        password = new JPasswordField(FIELDSSIZE);
        l.setLabelFor(password);
        loginform.add(password);

        username.addKeyListener(this);
        password.addKeyListener(this);

        SpringUtilities.makeCompactGrid(loginform, numPairs, 2, // rows, cols
                6, 6, // initX, initY
                6, 6); // xPad, yPad
        loginform.setBackground(Helpers.LIGHTBLUE);
        centerc.gridy++;
        this.add(loginform, centerc);

        JButton loginbutton = new JButton("Login");
        loginbutton.addKeyListener(this);
        loginbutton.setActionCommand("login");
        loginbutton.addActionListener(this);
        centerc.gridy++;
        this.setBackground(Helpers.LIGHTBLUE);
        this.add(loginbutton, centerc);
    }

    private void attemptLogin() {
        String username = this.username.getText();
        String password = new String(this.password.getPassword());
        User u = new User(username, password);
        boolean login = DatabaseOperations.login(u);
        if (login) {
            Session.setCurrentSession(DatabaseOperations
                    .getSessionInfo(username));
            this.mainwindow.startSession();
        } else {
            GuiDialogs.errorBeep();
            errormsg.setForeground(Color.RED);
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ENTER) {
            this.attemptLogin();
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("login")) {
            this.attemptLogin();
        }

    }
}