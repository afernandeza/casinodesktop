package gamesmanager.ui.forms;

import gamesmanager.beans.Address;
import gamesmanager.beans.Employee;
import gamesmanager.beans.Type;
import gamesmanager.beans.User;
import gamesmanager.db.DatabaseOperations;
import gamesmanager.db.EmployeeManager;
import gamesmanager.ui.Helpers;
import gamesmanager.ui.ImageFilter;
import gamesmanager.ui.ImagePanel;
import gamesmanager.ui.layout.SpringUtilities;

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
import javax.swing.JOptionPane;
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
    private JTextField appat;
    private JTextField apmat;
    private JTextField nombre;
    private JTextField telcasa;
    private JTextField telcel;
    private JComboBox sexo;
    private JDateChooser fecha;
    private JButton imagebutton;
    private JTextField calle;
    private JTextField num;
    private JTextField colonia;
    private JTextField municipio;
    private JTextField cp;
    private JComboBox estado;
    private JComboBox pais;
    private ImagePanel image;
    private JButton newemployee;
    private JButton savechanges;
    private JButton cancel;
    private Employee e;
    private ViewEmployees employeeviewer;
    private int selindex;

    private JComboBox types;
    private JTextField username;
    private JPasswordField password;

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

        JLabel pic = new JLabel(labels[0], JLabel.TRAILING);
        memberform.add(pic);
        imagebutton = new JButton("Abrir foto...");
        imagebutton.addActionListener(this);
        fc = new JFileChooser();
        fc.setAcceptAllFileFilterUsed(false);
        fc.setFileFilter(new ImageFilter());

        pic.setLabelFor(imagebutton);
        memberform.add(imagebutton);

        JLabel l = new JLabel(labels[1], JLabel.TRAILING);
        memberform.add(l);
        appat = new JTextField(FIELDSIZE);
        l.setLabelFor(appat);
        memberform.add(appat);

        JLabel l2 = new JLabel(labels[2], JLabel.TRAILING);
        memberform.add(l2);
        apmat = new JTextField(FIELDSIZE);
        l2.setLabelFor(apmat);
        memberform.add(apmat);

        JLabel l3 = new JLabel(labels[3], JLabel.TRAILING);
        memberform.add(l3);
        nombre = new JTextField(FIELDSIZE);
        l3.setLabelFor(nombre);
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

        JLabel labeltelcasa = new JLabel(alabels[9], JLabel.TRAILING);
        addressform.add(labeltelcasa);
        telcasa = new JTextField(FIELDSIZE);
        labeltelcasa.setLabelFor(telcasa);
        addressform.add(telcasa);

        JLabel labeltelcel = new JLabel(alabels[10], JLabel.TRAILING);
        addressform.add(labeltelcel);
        telcel = new JTextField(FIELDSIZE);
        labeltelcel.setLabelFor(telcel);
        addressform.add(telcel);

        JLabel l4 = new JLabel(alabels[0], JLabel.TRAILING);
        addressform.add(l4);
        sexo = new JComboBox();
        sexo.addItem("Masculino");
        sexo.addItem("Femenino");
        l4.setLabelFor(sexo);
        addressform.add(sexo);

        JLabel l5 = new JLabel(alabels[1], JLabel.TRAILING);
        addressform.add(l5);
        fecha = new JDateChooser();
        fecha.setLocale(Locale.getDefault());
        JTextFieldDateEditor editor = (JTextFieldDateEditor) fecha
                .getDateEditor();
        editor.setEditable(false);
        editor.setFocusable(false);
        fecha.setDateFormatString("yyyy-MM-dd");
        l5.setLabelFor(fecha);
        addressform.add(fecha);

        l = new JLabel(alabels[2], JLabel.TRAILING);
        addressform.add(l);
        calle = new JTextField(FIELDSIZE);
        l.setLabelFor(calle);
        addressform.add(calle);

        l2 = new JLabel(alabels[3], JLabel.TRAILING);
        addressform.add(l2);
        num = new JTextField(FIELDSIZE);
        l2.setLabelFor(num);
        addressform.add(num);

        l3 = new JLabel(alabels[4], JLabel.TRAILING);
        addressform.add(l3);
        colonia = new JTextField(FIELDSIZE);
        l3.setLabelFor(colonia);
        addressform.add(colonia);

        l4 = new JLabel(alabels[5], JLabel.TRAILING);
        addressform.add(l4);
        municipio = new JTextField(FIELDSIZE);
        l4.setLabelFor(municipio);
        addressform.add(municipio);

        l5 = new JLabel(alabels[6], JLabel.TRAILING);
        addressform.add(l5);
        cp = new JTextField(FIELDSIZE);
        l5.setLabelFor(cp);
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

        JLabel userl = new JLabel(alabels[11], JLabel.TRAILING);
        addressform.add(userl);
        username = new JTextField(FIELDSIZE);
        userl.setLabelFor(username);
        addressform.add(username);

        JLabel passwordl = new JLabel(alabels[12], JLabel.TRAILING);
        addressform.add(passwordl);
        password = new JPasswordField(FIELDSIZE);
        passwordl.setLabelFor(password);
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
            c.gridy = 2;
            c.gridx = 0;
            c.gridheight = 1;
            c.gridwidth = 2;
            this.add(p, c);
        } else {
            // existing employee
            this.password.setEditable(false);
            this.password.setEnabled(false);
            JPanel p = new JPanel();
            savechanges = new JButton("Guardar cambios");
            savechanges.addActionListener(this);
            p.add(savechanges);
            p.add(cancel);
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
        }

        this.getContentPane().setBackground(Helpers.LIGHTBLUE);
        this.pack();
        this.setSize(this.getWidth() + Helpers.XOFFSET, this.getHeight()
                + Helpers.YOFFSET);
        this.setLocationRelativeTo(null);
    }
    
    public void setEmployeeViewer(ViewEmployees ve, int selindex){
        this.employeeviewer = ve;
        this.selindex = selindex;
    }

    private boolean validateForm() {
        return true;
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        Object o = e.getSource();
        if (o == imagebutton) {
            int returnVal = fc.showOpenDialog(this);
            if (returnVal == JFileChooser.APPROVE_OPTION) {
                File file = fc.getSelectedFile();
                this.image.loadImage(file.getAbsolutePath());
//                System.out.println("Opening: " + file.getName() + ".");
            } else {
//                System.out.println("Open command cancelled by user.");
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
                
                Type et = (Type)this.types.getSelectedItem();
                this.e.setEmployeetype(et);
                
                boolean inserted = EmployeeManager.insertEmployee(this.e);
                if (inserted) {
                    JOptionPane.showMessageDialog(null,
                            "Nuevo empleado insertado.", "Informaci"
                                    + Helpers.OACUTE + "n",
                            JOptionPane.INFORMATION_MESSAGE);
                    this.dispose();
                } else {
                    JOptionPane.showMessageDialog(null,
                            "El nuevo empleado no ha sido insertado.\n" +
                            "Vuelva a intentar.",
                            "Informaci" + Helpers.OACUTE + "n",
                            JOptionPane.ERROR_MESSAGE);
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
                
                Type et = (Type)this.types.getSelectedItem();
                this.e.setEmployeetype(et);
                
                boolean updated = EmployeeManager.updateEmployee(this.e);
                if (updated) {
                    if(employeeviewer != null){
                        this.employeeviewer.refreshData(this.selindex);
                    }
                    JOptionPane.showMessageDialog(null,
                            "Cambios guardados exitosamente.", "Informaci"
                                    + Helpers.OACUTE + "n",
                            JOptionPane.INFORMATION_MESSAGE);
                    this.dispose();
                } else {
                    JOptionPane.showMessageDialog(null,
                            "Los cambios no han sido guardados.\n" +
                            "Vuelva a intentar.",
                            "Informaci" + Helpers.OACUTE + "n",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
        }
    }

}
