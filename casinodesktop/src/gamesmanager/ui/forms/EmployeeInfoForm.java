package gamesmanager.ui.forms;

import gamesmanager.beans.Address;
import gamesmanager.beans.Employee;
import gamesmanager.beans.Type;
import gamesmanager.beans.User;
import gamesmanager.db.DatabaseOperations;
import gamesmanager.db.EmployeeManager;
import gamesmanager.ui.GuiDialogs;
import gamesmanager.ui.Helpers;
import gamesmanager.ui.ImageFilter;
import gamesmanager.ui.ImagePanel;
import gamesmanager.ui.layout.SpringUtilities;
import gamesmanager.ui.session.Session;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.Locale;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SpringLayout;

import com.toedter.calendar.JDateChooser;
import com.toedter.calendar.JTextFieldDateEditor;

public class EmployeeInfoForm extends JFrame implements ActionListener {

    private static final long serialVersionUID = 1L;
    private static final int FIELDSIZE = 15;
    private static final int PICWIDTH = 300;
    private static final int PICHEIGHT = 225;

    private final JFileChooser fc;
    private JLabel piclabel;
    private JTextField appat;
    private JLabel appatlabel;
    private JTextField apmat;
    private JTextField nombre;
    private JLabel nombrelabel;
    private JTextField telcasa;
    private JLabel telcasalabel;
    private JTextField telcel;
    private JLabel telcellabel;
    private JComboBox sexo;
    private JDateChooser fecha;
    private JLabel fechalabel;
    private JButton imagebutton;
    private JTextField calle;
    private JLabel callelabel;
    private JTextField num;
    private JLabel numlabel;
    private JTextField colonia;
    private JLabel colonialabel;
    private JTextField municipio;
    private JLabel municipiolabel;
    private JTextField cp;
    private JLabel cplabel;
    private JComboBox estado;
    private JComboBox pais;
    private ImagePanel image;
    private JButton newemployee;
    private JButton savechanges;
    private JButton cancel;
    private Employee e;
    private ViewEmployees employeeviewer;
    private File fotofile;

    private JComboBox types;
    private JTextField username;
    private JLabel usernamelabel;
    private JPasswordField password;
    private JLabel passwordlabel;

    public EmployeeInfoForm(Employee e) {
        super("Empleado del Casino");
        this.e = e;
        Helpers.setDefaultAppearance(this, true);
        this.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();

        /* General info */
        JPanel memberform = new JPanel(new SpringLayout());
        String[] labels = {
                "<html><b>Fotograf" + Helpers.IACUTE + "a:</b></html>",
                "<html><b>Apellido paterno:</b></html>",
                "<html><b>Apellido materno:</b></html>",
                "<html><b>Nombre(s):</b></html>", "<html><b>Tipo:</b></html>" };

        int numPairs = labels.length;

        image = new ImagePanel();
        image.setPreferredSize(new Dimension(PICWIDTH, PICHEIGHT));
        image.setBackground(Color.WHITE);
        c.gridx = 0;
        c.gridy = 0;
        this.add(image, c);

        piclabel = new JLabel(labels[0], JLabel.TRAILING);
        memberform.add(piclabel);
        imagebutton = new JButton("Abrir foto...");
        imagebutton.addActionListener(this);
        fc = new JFileChooser();
        fc.setAcceptAllFileFilterUsed(false);
        fc.setFileFilter(new ImageFilter());

        piclabel.setLabelFor(imagebutton);
        memberform.add(imagebutton);

        appatlabel = new JLabel(labels[1], JLabel.TRAILING);
        memberform.add(appatlabel);
        appat = new JTextField(FIELDSIZE);
        appatlabel.setLabelFor(appat);
        memberform.add(appat);

        JLabel l2 = new JLabel(labels[2], JLabel.TRAILING);
        memberform.add(l2);
        apmat = new JTextField(FIELDSIZE);
        l2.setLabelFor(apmat);
        memberform.add(apmat);

        nombrelabel = new JLabel(labels[3], JLabel.TRAILING);
        memberform.add(nombrelabel);
        nombre = new JTextField(FIELDSIZE);
        nombrelabel.setLabelFor(nombre);
        memberform.add(nombre);

        JLabel typel = new JLabel(labels[4], JLabel.TRAILING);
        memberform.add(typel);
        types = new JComboBox();
        for (Type et : EmployeeManager.getEmployeeTypes()) {
            types.addItem(et);
        }
        typel.setLabelFor(types);
        memberform.add(types);

        SpringUtilities.makeCompactGrid(memberform, numPairs, 2, // rows, cols
                6, 6, // initX, initY
                6, 6); // xPad, yPad
        memberform.setBackground(Helpers.LIGHTBLUE);
        c.gridx = 0;
        c.gridy++;
        this.add(memberform, c);

        /* Address */

        JPanel addressform = new JPanel(new SpringLayout());
        String[] alabels = {
                "<html><b>Sexo:</b></html>",
                "<html><b>Fecha nacimiento:</b></html>",
                "<html><b>Calle y n" + Helpers.UACUTE + "mero:</b></html>",
                "<html><b>N" + Helpers.UACUTE + "mero interior:</b></html>",
                "<html><b>Colonia:</b></html>",
                "<html><b>Municipio o delegaci" + Helpers.OACUTE
                        + "n:</b></html>",
                "<html><b>C" + Helpers.OACUTE + "digo postal:</b></html>",
                "<html><b>Estado:</b></html>",
                "<html><b>Pa" + Helpers.IACUTE + "s:</b></html>",
                "<html><b>Tel" + Helpers.EACUTE + "fono casa:</b></html>",
                "<html><b>Tel" + Helpers.EACUTE + "fono celular:</b></html>",
                "<html><b>Usuario:</b></html>",
                "<html><b>Contrase" + Helpers.NTILDE + "a:</b></html>" };
        int aPairs = alabels.length;

        telcasalabel = new JLabel(alabels[9], JLabel.TRAILING);
        addressform.add(telcasalabel);
        telcasa = new JTextField(FIELDSIZE);
        telcasalabel.setLabelFor(telcasa);
        addressform.add(telcasa);

        telcellabel = new JLabel(alabels[10], JLabel.TRAILING);
        addressform.add(telcellabel);
        telcel = new JTextField(FIELDSIZE);
        telcellabel.setLabelFor(telcel);
        addressform.add(telcel);

        JLabel l4 = new JLabel(alabels[0], JLabel.TRAILING);
        addressform.add(l4);
        sexo = new JComboBox();
        sexo.addItem("Masculino");
        sexo.addItem("Femenino");
        l4.setLabelFor(sexo);
        addressform.add(sexo);

        fechalabel = new JLabel(alabels[1], JLabel.TRAILING);
        addressform.add(fechalabel);
        fecha = new JDateChooser();
        fecha.setLocale(Locale.getDefault());
        JTextFieldDateEditor editor = (JTextFieldDateEditor) fecha
                .getDateEditor();
        editor.setEditable(false);
        editor.setFocusable(false);
        fecha.setDateFormatString("yyyy-MM-dd");
        fechalabel.setLabelFor(fecha);
        addressform.add(fecha);

        callelabel = new JLabel(alabels[2], JLabel.TRAILING);
        addressform.add(callelabel);
        calle = new JTextField(FIELDSIZE);
        callelabel.setLabelFor(calle);
        addressform.add(calle);

        numlabel = new JLabel(alabels[3], JLabel.TRAILING);
        addressform.add(numlabel);
        num = new JTextField(FIELDSIZE);
        numlabel.setLabelFor(num);
        addressform.add(num);

        colonialabel = new JLabel(alabels[4], JLabel.TRAILING);
        addressform.add(colonialabel);
        colonia = new JTextField(FIELDSIZE);
        colonialabel.setLabelFor(colonia);
        addressform.add(colonia);

        municipiolabel = new JLabel(alabels[5], JLabel.TRAILING);
        addressform.add(municipiolabel);
        municipio = new JTextField(FIELDSIZE);
        municipiolabel.setLabelFor(municipio);
        addressform.add(municipio);

        cplabel = new JLabel(alabels[6], JLabel.TRAILING);
        addressform.add(cplabel);
        cp = new JTextField(FIELDSIZE);
        cplabel.setLabelFor(cp);
        addressform.add(cp);

        JLabel l6 = new JLabel(alabels[7], JLabel.TRAILING);
        addressform.add(l6);
        estado = new JComboBox();
        for (String est : DatabaseOperations.getStates()) {
            estado.addItem(est);
        }
        l6.setLabelFor(estado);
        addressform.add(estado);

        JLabel paisl = new JLabel(alabels[8], JLabel.TRAILING);
        addressform.add(paisl);
        pais = new JComboBox();
        pais.addItem("Mexico");
        pais.setSelectedIndex(0);
        pais.setEditable(false);
        paisl.setLabelFor(pais);
        addressform.add(pais);

        usernamelabel = new JLabel(alabels[11], JLabel.TRAILING);
        addressform.add(usernamelabel);
        username = new JTextField(FIELDSIZE);
        usernamelabel.setLabelFor(username);
        addressform.add(username);

        passwordlabel = new JLabel(alabels[12], JLabel.TRAILING);
        addressform.add(passwordlabel);
        password = new JPasswordField(FIELDSIZE);
        passwordlabel.setLabelFor(password);
        addressform.add(password);

        SpringUtilities.makeCompactGrid(addressform, aPairs, 2, // rows, cols
                6, 6, // initX, initY
                6, 6); // xPad, yPad
        addressform.setBackground(Helpers.LIGHTBLUE);
        c.gridx++;
        c.gridy = 0;
        c.gridheight = 2;
        this.add(addressform, c);

        cancel = new JButton("Cancelar");
        cancel.addActionListener(this);

        if (this.e == null) {
            // new member
            JPanel p = new JPanel();
            newemployee = new JButton("Agregar");
            newemployee.addActionListener(this);
            p.add(newemployee);
            p.add(cancel);
            p.setOpaque(false);
            c.gridy = 2;
            c.gridx = 0;
            c.gridheight = 1;
            c.gridwidth = 2;
            this.add(p, c);
        } else {
            // existing employee
            this.password.setEditable(Session.mayChangePasswordFor(this.e.getId()));
            this.password.setEnabled(Session.mayChangePasswordFor(this.e.getId()));
            JPanel p = new JPanel();
            savechanges = new JButton("Guardar cambios");
            savechanges.addActionListener(this);
            p.add(savechanges);
            p.add(cancel);
            p.setOpaque(false);
            c.gridy = 2;
            c.gridx = 0;
            c.gridheight = 1;
            c.gridwidth = 2;
            this.add(p, c);

            this.nombre.setText(this.e.getNombres());
            this.appat.setText(this.e.getAppaterno());
            this.apmat.setText(this.e.getApmaterno());
            this.sexo.setSelectedIndex(this.e.getSexoIndex());
            this.telcasa.setText(this.e.getTelcasa());
            this.telcel.setText(this.e.getTelcel());
            this.fecha.setDate(this.e.getFechanac());

            Address d = this.e.getAddress();
            this.calle.setText(d.getCallenum());
            this.num.setText(d.getNumint());
            this.colonia.setText(d.getColonia());
            this.municipio.setText(d.getMunicipio());
            this.cp.setText(d.getCodigopostal());
            this.estado.setSelectedItem(d.getEstado());
            this.pais.setSelectedItem(d.getPais());

            User u = this.e.getUser();
            this.username.setText(u.getUsername());

            Type et = this.e.getEmployeetype();
            this.types.setSelectedItem(et);
            this.types.setEnabled(Session.mayChangeUserType());
        }

        this.getContentPane().setBackground(Helpers.LIGHTBLUE);
        this.pack();
        this.setSize(this.getWidth() + Helpers.XOFFSET, this.getHeight()
                + Helpers.YOFFSET);
        this.setLocationRelativeTo(null);
    }

    public void setEmployeeViewer(ViewEmployees ve, int selindex) {
        this.employeeviewer = ve;
    }

    public void loadCurrentImage() {
        if (this.e.getFotoImageIcon() != null) {
            this.image.loadImage(this.e.getFotoImageIcon());
        } else {
            throw new NullPointerException("Imagen nula");
        }
    }

    private boolean validateForm() {
        boolean good = true;
        if (appat.getText().trim().equals("")) {
            appatlabel.setForeground(Color.RED);
            good = false;
        } else {
            appatlabel.setForeground(Color.BLACK);
        }
        if (nombre.getText().trim().equals("")) {
            good = false;
            nombrelabel.setForeground(Color.RED);
        } else {
            nombrelabel.setForeground(Color.BLACK);
        }
        if (telcasa.getText().trim().equals("")) {
            good = false;
            telcasalabel.setForeground(Color.RED);
        } else {
            telcasalabel.setForeground(Color.BLACK);
        }
        if (telcel.getText().trim().equals("")) {
            good = false;
            telcellabel.setForeground(Color.RED);
        } else {
            telcellabel.setForeground(Color.BLACK);
        }
        if (calle.getText().trim().equals("")) {
            good = false;
            callelabel.setForeground(Color.RED);
        } else {
            callelabel.setForeground(Color.BLACK);
        }
        if (colonia.getText().trim().equals("")) {
            good = false;
            colonialabel.setForeground(Color.RED);
        } else {
            colonialabel.setForeground(Color.BLACK);
        }
        if (cp.getText().trim().equals("")) {
            good = false;
            cplabel.setForeground(Color.RED);
        } else {
            cplabel.setForeground(Color.BLACK);
        }
        if (municipio.getText().trim().equals("")) {
            good = false;
            municipiolabel.setForeground(Color.RED);
        } else {
            municipiolabel.setForeground(Color.BLACK);
        }
        if (fecha.getDate() == null) {
            good = false;
            fechalabel.setForeground(Color.RED);
        } else {
            fechalabel.setForeground(Color.BLACK);
        }
        if (username.getText().trim().equals("")) {
            good = false;
            usernamelabel.setForeground(Color.RED);
        } else {
            usernamelabel.setForeground(Color.BLACK);
        }
        if (new String(password.getPassword()).trim().equals("")) {
            good = false;
            passwordlabel.setForeground(Color.RED);
        } else {
            passwordlabel.setForeground(Color.BLACK);
        }
        if(!good){
           GuiDialogs.showErrorMessage("Por favor corrija los campos marcados con rojo.");
        }
        return good;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object o = e.getSource();
        if (o == imagebutton) {
            int returnVal = fc.showOpenDialog(this);
            if (returnVal == JFileChooser.APPROVE_OPTION) {
                fotofile = fc.getSelectedFile();
                this.image.loadImage(fotofile.getAbsolutePath());
            } else {
                // System.out.println("Open command cancelled by user.");
            }
        } else if (o == newemployee) {
            if (this.validateForm()) {
                this.e = new Employee();
                this.e.setNombres(nombre.getText().trim());
                this.e.setAppaterno(appat.getText().trim());
                this.e.setApmaterno(apmat.getText().trim());
                this.e.setSexo(sexo.getSelectedIndex());
                this.e.setFechanac(fecha.getDate());
                this.e.setFoto(fc.getSelectedFile());
                this.e.setTelcasa(telcasa.getText().trim());
                this.e.setTelcel(telcel.getText().trim());

                Address d = new Address();
                d.setCallenum(calle.getText().trim());
                d.setNumint(num.getText().trim());
                d.setColonia(colonia.getText().trim());
                d.setMunicipio(municipio.getText().trim());
                d.setCodigopostal(cp.getText().trim());
                d.setEstado(estado.getSelectedItem().toString());
                d.setPais(pais.getSelectedItem());
                this.e.setAddress(d);

                User u = new User(this.username.getText().trim());
                u.setPassword(this.password.getPassword());
                this.e.setUser(u);

                Type et = (Type) this.types.getSelectedItem();
                this.e.setEmployeetype(et);

                boolean inserted = EmployeeManager.insertEmployee(this.e);
                if (inserted) {
                    GuiDialogs.showSuccessMessage("Nuevo empleado insertado.");
                    this.dispose();
                } else {
                    GuiDialogs
                            .showErrorMessage("El nuevo empleado no ha sido insertado.");
                }
            }
        } else if (o == cancel) {
            this.dispose();
        } else if (o == savechanges) {
            if (this.validateForm()) {
                this.e.setNombres(nombre.getText().trim());
                this.e.setAppaterno(appat.getText().trim());
                this.e.setApmaterno(apmat.getText().trim());
                this.e.setSexo(sexo.getSelectedIndex());
                this.e.setFechanac(fecha.getDate());
                this.e.setFoto(fc.getSelectedFile());
                this.e.setTelcasa(telcasa.getText().trim());
                this.e.setTelcel(telcel.getText().trim());

                Address d = this.e.getAddress();
                d.setCallenum(calle.getText().trim());
                d.setNumint(num.getText().trim());
                d.setColonia(colonia.getText().trim());
                d.setMunicipio(municipio.getText().trim());
                d.setCodigopostal(cp.getText().trim());
                d.setEstado(estado.getSelectedItem().toString());
                d.setPais(pais.getSelectedItem());

                User u = this.e.getUser();
                u.setUsername(this.username.getText().trim());

                Type et = (Type) this.types.getSelectedItem();
                this.e.setEmployeetype(et);

                boolean updated = EmployeeManager.updateEmployee(this.e);
                if (updated) {
                    if (employeeviewer != null) {
                        employeeviewer.search();
                    }
                    GuiDialogs
                            .showSuccessMessage("Cambios guardados exitosamente.");
                    this.dispose();
                } else {
                    GuiDialogs
                            .showErrorMessage("Los cambios no han sido guardados.");
                }
            }
        }
    }

}
