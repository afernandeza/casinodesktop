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
import java.math.BigInteger;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;

import javax.swing.JButton;
import javax.swing.JCheckBox;
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
    private JLabel apmatlabel;
    private JTextField nombre;
    private JLabel nombrelabel;
    private JTextField telcasa;
    private JLabel telcasalabel;
    private JTextField telcel;
    private JLabel telcellabel;
    private JComboBox sexo;
    private JLabel sexolabel;
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
    private JLabel estadolabel;
    private JComboBox pais;
    private ImagePanel image;
    private JButton newemployee;
    private JButton savechanges;
    private JButton cancel;
    private Employee e;
    private ViewEmployees employeeviewer;
    private File fotofile;

    private JComboBox types;
    private JLabel typeslabel;
    private JTextField username;
    private JLabel usernamelabel;
    private JPasswordField password;
    private JLabel passwordlabel;
    private JCheckBox editpw;

    private static final List<String> formerrors = new LinkedList<String>();
    private static final List<String> ESTADOS = DatabaseOperations.getStates();

    private boolean newphotoselected = false;
    private boolean imageloaded = false;
    private boolean editing = false;

    public EmployeeInfoForm(Employee e) {
        super("Empleado del Casino");
        this.e = e;
        if (this.e == null) {
            editing = false;
        } else {
            editing = true;
        }
        Helpers.setDefaultAppearance(this, true);
        this.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();

        /* General info */
        JPanel memberform = new JPanel(new SpringLayout());
        String[] labels = {
                "<html><b>*Fotograf" + Helpers.IACUTE + "a:</b></html>",
                "<html><b>*Apellido paterno:</b></html>",
                "<html><b>Apellido materno:</b></html>",
                "<html><b>*Nombre(s):</b></html>", "<html><b>*Tipo:</b></html>" };

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

        apmatlabel = new JLabel(labels[2], JLabel.TRAILING);
        memberform.add(apmatlabel);
        apmat = new JTextField(FIELDSIZE);
        apmatlabel.setLabelFor(apmat);
        memberform.add(apmat);

        nombrelabel = new JLabel(labels[3], JLabel.TRAILING);
        memberform.add(nombrelabel);
        nombre = new JTextField(FIELDSIZE);
        nombrelabel.setLabelFor(nombre);
        memberform.add(nombre);

        typeslabel = new JLabel(labels[4], JLabel.TRAILING);
        memberform.add(typeslabel);
        types = new JComboBox();
        types.addItem("Seleccionar...");
        for (Type et : EmployeeManager.getEmployeeTypes()) {
            types.addItem(et);
        }
        typeslabel.setLabelFor(types);
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
                "<html><b>*Sexo:</b></html>",
                "<html><b>*Fecha nacimiento:</b></html>",
                "<html><b>*Calle y n" + Helpers.UACUTE + "mero:</b></html>",
                "<html><b>N" + Helpers.UACUTE + "mero interior:</b></html>",
                "<html><b>*Colonia:</b></html>",
                "<html><b>*Municipio o delegaci" + Helpers.OACUTE
                + "n:</b></html>",
                "<html><b>*C" + Helpers.OACUTE + "digo postal:</b></html>",
                "<html><b>*Estado:</b></html>",
                "<html><b>*Pa" + Helpers.IACUTE + "s:</b></html>",
                "<html><b>Tel" + Helpers.EACUTE + "fono casa:</b></html>",
                "<html><b>*Tel" + Helpers.EACUTE + "fono celular:</b></html>",
                "<html><b>*Usuario:</b></html>",
                "<html><b>*Contrase" + Helpers.NTILDE + "a:</b></html>" };
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

        sexolabel = new JLabel(alabels[0], JLabel.TRAILING);
        addressform.add(sexolabel);
        sexo = new JComboBox();
        sexo.addItem("Seleccionar...");
        sexo.addItem("Masculino");
        sexo.addItem("Femenino");
        sexolabel.setLabelFor(sexo);
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

        estadolabel = new JLabel(alabels[7], JLabel.TRAILING);
        addressform.add(estadolabel);
        estado = new JComboBox();
        estado.addItem("Seleccionar...");
        for (String est : ESTADOS) {
            estado.addItem(est);
        }
        estadolabel.setLabelFor(estado);
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
        if (editing) {
            username.setEnabled(false);
        }
        usernamelabel.setLabelFor(username);
        addressform.add(username);

        passwordlabel = new JLabel(alabels[12], JLabel.TRAILING);
        if (editing) {
            editpw = new JCheckBox();
            JPanel jp = new JPanel();
            jp.add(editpw);
            jp.add(passwordlabel);
            addressform.add(jp);
        } else {
            editpw = new JCheckBox();
            editpw.setSelected(false);
            addressform.add(passwordlabel);
        }
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
            this.password.setEditable(Session.mayChangePasswordFor(this.e
                    .getId()));
            this.password.setEnabled(Session.mayChangePasswordFor(this.e
                    .getId()));
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
            this.sexo.setSelectedIndex(this.e.getSexoIndex() + 1);
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
            imageloaded = true;
        } else {
            throw new NullPointerException("Imagen nula");
        }
    }

    private boolean validateForm() {
        formerrors.clear();
        boolean good = true;
        if (newphotoselected) {
            if (!this.fotofile.exists() || !this.fotofile.canRead()) {
                good = false;
                this.piclabel.setForeground(Color.RED);
                formerrors.add("La foto seleccionada es inv" + Helpers.AACUTE
                        + "lida.");
            } else {
                this.piclabel.setForeground(Color.BLACK);
            }
        } else if (imageloaded) {
            this.piclabel.setForeground(Color.BLACK);
        } else {
            good = false;
            this.piclabel.setForeground(Color.RED);
        }
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
        if (!telcasa.getText().trim().equals("")) {
            try {
                String telc = telcasa.getText().trim();
                if (telc.length() < 5 || telc.length() > 20) {
                    good = false;
                    telcasalabel.setForeground(Color.RED);
                    formerrors.add("El tel" + Helpers.EACUTE
                            + "fono de casa debe " + "tener entre 5 y 20 d"
                            + Helpers.IACUTE + "gitos.");
                } else {
                    BigInteger bi = new BigInteger(telc);
                    bi.toString();
                    telcasalabel.setForeground(Color.BLACK);
                }
            } catch (Exception e) {
                good = false;
                telcasalabel.setForeground(Color.RED);
                formerrors
                .add("El tel" + Helpers.EACUTE + "fono de casa "
                        + "contiene caracteres inv" + Helpers.AACUTE
                        + "lidos.");
            }
        } else {
            telcasalabel.setForeground(Color.BLACK);
        }
        if (telcel.getText().trim().equals("")) {
            good = false;
            telcellabel.setForeground(Color.RED);
        } else {
            try {
                String telc = telcel.getText().trim();
                if (telc.length() < 5 || telc.length() > 20) {
                    good = false;
                    telcellabel.setForeground(Color.RED);
                    formerrors.add("El tel" + Helpers.EACUTE
                            + "fono celular debe " + "tener entre 5 y 20 d"
                            + Helpers.IACUTE + "gitos.");
                } else {
                    BigInteger bi = new BigInteger(telc);
                    bi.toString();
                    telcellabel.setForeground(Color.BLACK);
                }
            } catch (Exception e) {
                good = false;
                telcellabel.setForeground(Color.RED);
                formerrors
                .add("El tel" + Helpers.EACUTE + "fono celular "
                        + "contiene caracteres inv" + Helpers.AACUTE
                        + "lidos.");
            }
        }
        if (estado.getSelectedIndex() == 0) {
            estadolabel.setForeground(Color.RED);
            good = false;
        } else {
            estadolabel.setForeground(Color.BLACK);
        }
        if (sexo.getSelectedIndex() == 0) {
            sexolabel.setForeground(Color.RED);
            good = false;
        } else {
            sexolabel.setForeground(Color.BLACK);
        }
        if (types.getSelectedIndex() == 0) {
            typeslabel.setForeground(Color.RED);
            good = false;
        } else {
            typeslabel.setForeground(Color.BLACK);
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
            String cpostal = cp.getText().trim();
            if (cpostal.length() > 4) {
                cplabel.setForeground(Color.BLACK);
            } else {
                good = false;
                cplabel.setForeground(Color.RED);
                formerrors.add("El c" + Helpers.OACUTE + "digo postal "
                        + "debe ser de al menos 5 caracteres.");
            }
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
        if (!editing) {
            if (username.getText().trim().equals("")) {
                good = false;
                usernamelabel.setForeground(Color.RED);
            } else {
                if (username.getText().trim().length() < 3) {
                    good = false;
                    usernamelabel.setForeground(Color.RED);
                    formerrors.add("El nombre de usuario "
                            + "debe ser de al menos 3 caracteres.");
                } else {
                    if (EmployeeManager.userExists(username.getText().trim())) {
                        good = false;
                        formerrors.add("El nombre de usuario "
                                + "seleccionado ya existe. Utilice otro.");
                        usernamelabel.setForeground(Color.RED);
                    } else {
                        usernamelabel.setForeground(Color.BLACK);
                    }
                }
            }
        }
        if(editpw.isSelected()){
            if (new String(password.getPassword()).trim().equals("")) {
                good = false;
                passwordlabel.setForeground(Color.RED);
            } else {
                if (new String(password.getPassword()).trim().length() < 6) {
                    good = false;
                    formerrors.add("La contrase" + Helpers.NTILDE
                            + "a debe ser de al menos 6 caracteres.");
                    passwordlabel.setForeground(Color.RED);
                } else {
                    passwordlabel.setForeground(Color.BLACK);
                }
            }
        }
        if (!good) {
            StringBuffer em = new StringBuffer();
            em.append("Por favor complete todos los campos obligatorios");
            if (formerrors.size() > 0) {
                em.append(" y corrija los siguientes errores:\n");
                for (String err : formerrors) {
                    em.append("- " + err + "\n");
                }
            }
            GuiDialogs.showErrorMessage(em.toString());
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
                newphotoselected = true;
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
                this.e.setSexo(sexo.getSelectedIndex() - 1);
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
                this.e.setSexo(sexo.getSelectedIndex() - 1);
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
                u.setPassword(new String(password.getPassword()));

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
