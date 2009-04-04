package gamesmanager.ui.forms;

import gamesmanager.beans.Address;
import gamesmanager.beans.Client;
import gamesmanager.db.ClientManager;
import gamesmanager.db.DatabaseOperations;
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
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.util.Locale;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SpringLayout;
import javax.swing.SwingWorker;

import com.toedter.calendar.JDateChooser;
import com.toedter.calendar.JTextFieldDateEditor;

public class ClientInfoForm extends JFrame implements ActionListener,
        MouseListener {

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
    private JTextField estado;
    private JComboBox pais;
    private ImagePanel image;
    private JButton newmember;
    private JButton register;
    private JButton cancel;
    private Client client;

    public ClientInfoForm(Client client) {
        super("Miembro del Casino");
        this.client = client;
        Helpers.setDefaultAppearance(this, true);
        this.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();

        /* General info */
        JPanel memberform = new JPanel(new SpringLayout());
        String[] labels = {
                "<html><b>Fotograf" + Helpers.IACUTE + "a:</b></html>",
                "<html><b>Apellido paterno:</b></html>",
                "<html><b>Apellido materno:</b></html>",
                "<html><b>Nombre(s):</b></html>" };

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
                "<html><b>Tel" + Helpers.EACUTE + "fono celular:</b></html>" };
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

        JLabel paisl = new JLabel(alabels[8], JLabel.TRAILING);
        addressform.add(paisl);
        pais = new JComboBox();
        for (String country : DatabaseOperations.getCountries()) {
            pais.addItem(country);
        }
        paisl.setLabelFor(pais);
        addressform.add(pais);
        pais.addActionListener(this);

        JLabel l6 = new JLabel(alabels[7], JLabel.TRAILING);
        addressform.add(l6);
        estado = new JTextField(FIELDSIZE);
        estado.addMouseListener(this);
        l6.setLabelFor(estado);
        addressform.add(estado);

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

        if (this.client == null) {
            // new member
            JPanel p = new JPanel();
            newmember = new JButton("Agregar");
            newmember.addActionListener(this);
            p.add(newmember);
            p.add(cancel);
            c.gridy = 2;
            c.gridx = 0;
            c.gridheight = 1;
            c.gridwidth = 2;
            this.add(p, c);
        } else {
            // existing member
            JPanel p = new JPanel();
            register = new JButton("Guardar cambios");
            register.addActionListener(this);
            p.add(register);
            p.add(cancel);
            c.gridy = 2;
            c.gridx = 0;
            c.gridheight = 1;
            c.gridwidth = 2;
            this.add(p, c);

            this.nombre.setText(this.client.getNombres());
            this.appat.setText(this.client.getAppaterno());
            this.apmat.setText(this.client.getApmaterno());
            this.sexo.setSelectedIndex(this.client.getSexoIndex());
            this.telcasa.setText(this.client.getTelcasa());
            this.telcel.setText(this.client.getTelcel());
            this.fecha.setDate(this.client.getFechanac());

            Address d = this.client.getAddress();
            this.calle.setText(d.getCallenum());
            this.num.setText(d.getNumint());
            this.colonia.setText(d.getColonia());
            this.municipio.setText(d.getMunicipio());
            this.cp.setText(d.getCodigopostal());
            this.estado.setText(d.getEstado());
            this.pais.setSelectedItem(d.getPais());

        }

        this.getContentPane().setBackground(Helpers.LIGHTBLUE);
        this.pack();
        this.setSize(this.getWidth() + Helpers.XOFFSET, this.getHeight()
                + Helpers.YOFFSET);
        this.setLocationRelativeTo(null);
    }

    public void loadCurrentImage() {
        if (this.client.getFotoImageIcon() != null) {
            this.image.loadImage(this.client.getFotoImageIcon());
        } else {
            throw new NullPointerException("Imagen nula");
        }
    }

    private boolean validateForm() {
        return true;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object o = e.getSource();
        if (o == pais) {
            // String selectedcountry = (String) pais.getSelectedItem();
            estado.setEnabled(true);

        } else if (o == imagebutton) {
            SwingWorker<Void, Void> worker = new SwingWorker<Void, Void>() {

                public Void doInBackground() {
                    int returnVal = fc.showOpenDialog(ClientInfoForm.this);
                    if (returnVal == JFileChooser.APPROVE_OPTION) {
                        File file = fc.getSelectedFile();
                        image.loadImage(file.getAbsolutePath());
                        System.out.println("Opening: " + file.getName() + ".");
                    } else {
                        System.out.println("Open command cancelled by user.");
                    }
                    return null;
                }

                public void done() {
                }
            };
            worker.execute();
        } else if (o == newmember) {
            System.out.println("agregando");
            if (this.validateForm()) {
                Client c = new Client();
                c.setCredito("0");
                c.setNombres(nombre.getText().trim());
                c.setAppaterno(appat.getText().trim());
                c.setApmaterno(apmat.getText().trim());
                c.setSexo(sexo.getSelectedIndex());
                c.setFechanac(fecha.getDate());
                c.setFoto(fc.getSelectedFile());
                c.setTelcasa(telcasa.getText().trim());
                c.setTelcel(telcel.getText().trim());

                Address d = new Address();
                d.setCallenum(calle.getText().trim());
                d.setNumint(num.getText().trim());
                d.setColonia(colonia.getText().trim());
                d.setMunicipio(municipio.getText().trim());
                d.setCodigopostal(cp.getText().trim());
                d.setEstado(estado.getText().trim());
                d.setPais(pais.getSelectedItem());

                c.setAddress(d);
                boolean inserted = ClientManager.insertClient(c);
                if (inserted) {
                    JOptionPane.showMessageDialog(null,
                            "Nuevo miembro insertado.", "Informaci"
                                    + Helpers.OACUTE + "n",
                            JOptionPane.INFORMATION_MESSAGE);
                    this.dispose();
                } else {
                    JOptionPane.showMessageDialog(null,
                            "El nuevo miembro no ha sido insertado.",
                            "Informaci" + Helpers.OACUTE + "n",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
        } else if (o == cancel) {
            this.dispose();
        } else if (o == register) {
            if (this.validateForm()) {
                this.client.setNombres(nombre.getText().trim());
                this.client.setAppaterno(appat.getText().trim());
                this.client.setApmaterno(apmat.getText().trim());
                this.client.setSexo(sexo.getSelectedIndex());
                this.client.setFechanac(fecha.getDate());
                this.client.setFoto(fc.getSelectedFile());
                this.client.setTelcasa(telcasa.getText().trim());
                this.client.setTelcel(telcel.getText().trim());

                Address d = this.client.getAddress();
                d.setCallenum(calle.getText().trim());
                d.setNumint(num.getText().trim());
                d.setColonia(colonia.getText().trim());
                d.setMunicipio(municipio.getText().trim());
                d.setCodigopostal(cp.getText().trim());
                d.setEstado(estado.getText().trim());
                d.setPais(pais.getSelectedItem());

                boolean updated = ClientManager.updateClient(this.client);
                System.out.println("updated: " + updated);
                if (updated) {
                    JOptionPane.showMessageDialog(null,
                            "Cambios guardados exitosamente.", "Informaci"
                                    + Helpers.OACUTE + "n",
                            JOptionPane.INFORMATION_MESSAGE);
                    this.dispose();
                } else {
                    JOptionPane.showMessageDialog(null,
                            "Los cambios no han sido guardados.\n" +
                            "Vuelva a intentar.",
                            "Informaci"
                                    + Helpers.OACUTE + "n",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        String sc = (String) pais.getSelectedItem();
        if (sc.equals("Mexico")) {
            Object[] possibilities = DatabaseOperations.getStates().toArray();
            String s = (String) JOptionPane.showInputDialog(null,
                    "Seleccione el estado de la Rep" + Helpers.UACUTE
                            + "blica:\n", "", JOptionPane.QUESTION_MESSAGE,
                    null, possibilities, "Distrito Federal");

            if ((s != null) && (s.length() > 0)) {
                estado.setText(s);
                estado.setEnabled(false);
                return;
            }
        } else {
            estado.setEnabled(true);
        }
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        // TODO Auto-generated method stub

    }

    @Override
    public void mouseExited(MouseEvent e) {
        // TODO Auto-generated method stub

    }

    @Override
    public void mousePressed(MouseEvent e) {
        // TODO Auto-generated method stub

    }

    @Override
    public void mouseReleased(MouseEvent e) {
        // TODO Auto-generated method stub

    }

}
