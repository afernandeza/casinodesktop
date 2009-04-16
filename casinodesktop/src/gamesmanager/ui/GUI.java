package gamesmanager.ui;

import gamesmanager.db.DatabaseManager;
import gamesmanager.ui.forms.CheckClientForm;
import gamesmanager.ui.forms.ClientInfoForm;
import gamesmanager.ui.forms.EmployeeInfoForm;
import gamesmanager.ui.forms.GameTypeForm;
import gamesmanager.ui.forms.LoginForm;
import gamesmanager.ui.forms.ViewEmployees;
import gamesmanager.ui.forms.ViewGameTables;
import gamesmanager.ui.forms.ViewSessions;
import gamesmanager.ui.session.Session;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

public class GUI extends JFrame implements ActionListener {

    private static final String NEWMEMBER = "Nuevo miembro...";
    private static final String NEWEMPLOYEE = "Nuevo empleado...";
    private static final String ADMINTABLES = "Mesas...";

    private static final String ADMINGAMETYPES = "Tipos de juego...";
    private static final String ADMINEMPLOYEES = "Empleados...";
    private static final String ADMINSESSIONS = "Sesiones...";
    private static final String SYNC = "Sincronizar...";
    private static final String MANUAL = "Manual de usuario";
    private static final String ABOUT = "Acerca de";
    private static final String LOGOUT = "Cerrar sesi" + Helpers.OACUTE + "n";
    private static final String EXIT = "Salir";
    
    private static final int USERMANUALWIDTH = 800;
    private static final int USERMANUALHEIGHT = 400;

    static final long serialVersionUID = 1L;

    private JMenuBar menubar;
    private JMenu file;
    private JMenu services;
    private JMenu admin;
    private JMenu help;
    private LoginForm loginForm;
    private CheckClientForm checkForm;
    private JMenuItem newEmployee;

    public GUI() {
        super("Games Management System");
        this.setAppearance();
        this.setMenuBar();
        this.stopSession();
    }

    public void startSession() {
        // session started

        this.setMenuBarEnabled(true);
        newEmployee.setEnabled(Session.mayHire());

        this.remove(this.loginForm);

        checkForm = new CheckClientForm();
        this.add(checkForm, BorderLayout.CENTER);
        checkForm.setFocus();

        this.pack();
        this.setSize(this.getWidth() + Helpers.XOFFSET, this.getHeight() + 50);
        this.setLocationRelativeTo(null);
    }

    public void stopSession() {
        // session ended

        this.setMenuBarEnabled(false);
        if (this.checkForm != null) {
            this.remove(checkForm);
        }
        this.setLoginForm();

        this.pack();
        this.setSize(this.getWidth() + Helpers.XOFFSET, this.getHeight()
                + Helpers.YOFFSET);
        this.setLocationRelativeTo(null);
    }

    private void setAppearance() {

        Helpers.setDefaultAppearance(this, true);
        this.setSize(800, 600);
        this.setLayout(new BorderLayout());

        /* Top Panel */

        JPanel top = new JPanel();
        top.setLayout(new GridBagLayout());
        GridBagConstraints topc = new GridBagConstraints();
        topc.gridx = 0;
        topc.gridy = 0;

        Image bannerimg = Helpers.createImage("images/banner.jpg",
                this.getClass()).getImage();
        ImagePanel banner = new ImagePanel(bannerimg);
        top.add(banner, topc);
        top.setBackground(Helpers.BEIGE2);
        this.add(top, BorderLayout.PAGE_START);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }

    private void setLoginForm() {
        loginForm = new LoginForm(this);
        this.add(this.loginForm, BorderLayout.CENTER);
    }

    private void setMenuBarEnabled(boolean enabled) {
        file.setEnabled(enabled);
        services.setEnabled(enabled);
        admin.setEnabled(enabled);
    }

    private void setMenuBar() {
        System.setProperty("com.apple.mrj.application.apple.menu.about.name",
                "Games Manager");
        System.setProperty("apple.laf.useScreenMenuBar", "true");

        menubar = new JMenuBar();
        file = new JMenu("Archivo");
        menubar.add(file);

        JMenuItem newMember = new JMenuItem(NEWMEMBER);
        newMember.addActionListener(this);
        file.add(newMember);

        newEmployee = new JMenuItem(NEWEMPLOYEE);
        newEmployee.addActionListener(this);
        file.add(newEmployee);


        file.addSeparator();

        JMenuItem logout = new JMenuItem(LOGOUT);
        logout.addActionListener(this);
        file.add(logout);
        file.addSeparator();

        JMenuItem close = new JMenuItem(EXIT);
        close.addActionListener(this);
        file.add(close);

        services = new JMenu("Servicios");
        menubar.add(services);

        JMenuItem sync = new JMenuItem(SYNC);
        sync.addActionListener(this);
        services.add(sync);

        admin = new JMenu("Administrar");
        menubar.add(admin);

        JMenuItem newTable = new JMenuItem(ADMINTABLES);
        newTable.addActionListener(this);
        admin.add(newTable);

        JMenuItem newGameType = new JMenuItem(ADMINGAMETYPES);
        newGameType.addActionListener(this);
        admin.add(newGameType);

        JMenuItem adminEmployees = new JMenuItem(ADMINEMPLOYEES);
        adminEmployees.addActionListener(this);
        admin.add(adminEmployees);
        
        JMenuItem adminSessions = new JMenuItem(ADMINSESSIONS);
        adminSessions.addActionListener(this);
        admin.add(adminSessions);

        help = new JMenu("Ayuda");
        menubar.add(help);
        help.setEnabled(true);

        JMenuItem manual = new JMenuItem(MANUAL);
        manual.addActionListener(this);
        help.add(manual);

        help.addSeparator();

        JMenuItem about = new JMenuItem(ABOUT);
        about.addActionListener(this);
        help.add(about);

        this.setJMenuBar(menubar);
    }

    public static void main(String args[]) {
        GUI g = new GUI();
        if (DatabaseManager.databaseAvailable()) {
            g.setVisible(true);
        } else {
            GuiDialogs
                    .showErrorMessage("No se tiene acceso a la base de datos.\n"
                            + "La aplicaci"
                            + Helpers.OACUTE
                            + "n se cerrar"
                            + Helpers.AACUTE);
            System.exit(0);
        }
    }

    private class ImagePanel extends JPanel {

        private static final long serialVersionUID = 1L;
        private Image img;

        public ImagePanel(String img) {
            this(new ImageIcon(img).getImage());
        }

        public ImagePanel(Image img) {
            this.img = img;
            Dimension size = new Dimension(img.getWidth(null), img
                    .getHeight(null));
            setPreferredSize(size);
            setMinimumSize(size);
            setMaximumSize(size);
            setSize(size);
            setLayout(null);
        }

        public void paintComponent(Graphics g) {
            g.drawImage(img, 0, 0, null);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String action = e.getActionCommand();
        if (action.equals(NEWMEMBER)) {
            ClientInfoForm memberform = new ClientInfoForm(null);
            memberform.setVisible(true);
        } else if (action.equals(NEWEMPLOYEE)) {
            EmployeeInfoForm employeeform = new EmployeeInfoForm(null);
            employeeform.setVisible(true);
        } else if (action.equals(ADMINTABLES)) {
            ViewGameTables tableform = new ViewGameTables();
            tableform.setVisible(true);
        } else if (action.equals(SYNC)) {
            checkForm.startSync();
        } else if (action.equals(ADMINEMPLOYEES)) {
            ViewEmployees viewemps = new ViewEmployees();
            viewemps.setVisible(true);
        } else if (action.equals(ADMINGAMETYPES)) {
            GameTypeForm gametypeform = new GameTypeForm();
            gametypeform.setVisible(true);
        }  else if (action.equals(ADMINSESSIONS)) {
            ViewSessions sessions = new ViewSessions();
            sessions.setVisible(true);
        } else if (action.equals(MANUAL)) {
            HelpWindow h = new HelpWindow("help/index.html", USERMANUALWIDTH, USERMANUALHEIGHT);
            h.setVisible(true);
        } else if (action.equals(ABOUT)) {
            HelpWindow h = new HelpWindow("about/index.html", 420, 250);
            h.setVisible(true);
        } else if (action.equals(LOGOUT)) {
            this.stopSession();
        } else if (action.equals(EXIT)) {
            System.exit(0);
        }
    }
}
