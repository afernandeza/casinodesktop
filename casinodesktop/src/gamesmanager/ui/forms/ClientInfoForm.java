package gamesmanager.ui.forms;

import gamesmanager.beans.Address;
import gamesmanager.beans.Client;
import gamesmanager.db.ClientManager;
import gamesmanager.db.DatabaseOperations;
import gamesmanager.ui.GuiDialogs;
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
    private File fotofile;
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
    private JTextField estado;
    private JLabel estadolabel;
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

        JLabel paisl = new JLabel(alabels[8], JLabel.TRAILING);
        addressform.add(paisl);
        pais = new JComboBox();
        for (String country : DatabaseOperations.getCountries()) {
            pais.addItem(country);
        }
        pais.setSelectedItem("Mexico");
        paisl.setLabelFor(pais);
        addressform.add(pais);
        pais.addActionListener(this);

        estadolabel = new JLabel(alabels[7], JLabel.TRAILING);
        addressform.add(estadolabel);
        estado = new JTextField(FIELDSIZE);
        estado.addMouseListener(this);
        estadolabel.setLabelFor(estado);
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
            p.setOpaque(false);
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
            p.setOpaque(false);
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
        if (num.getText().trim().equals("")) {
            good = false;
            numlabel.setForeground(Color.RED);
        } else {
            numlabel.setForeground(Color.BLACK);
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
        if (estado.getText().trim().equals("")) {
            good = false;
            estadolabel.setForeground(Color.RED);
        } else {
            estadolabel.setForeground(Color.BLACK);
        }
        if (fecha.getDate() == null) {
            good = false;
            fechalabel.setForeground(Color.RED);
        } else {
            fechalabel.setForeground(Color.BLACK);
        }
        if (fotofile != null) {
            if (!fotofile.exists()) {
                good = false;
                piclabel.setForeground(Color.RED);
            } else {
                piclabel.setForeground(Color.BLACK);
            }
        } else {
            good = false;
            piclabel.setForeground(Color.RED);
        }
        if(!good){
           GuiDialogs.showErrorMessage("Por favor corrija los campos marcados con rojo.");
        }
        return good;
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
                        fotofile = fc.getSelectedFile();
                        image.loadImage(fotofile.getAbsolutePath());
                        System.out.println("Opening: " + fotofile.getName()
                                + ".");
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
                    GuiDialogs.showSuccessMessage("Nuevo miembro insertado.");
                    this.dispose();
                } else {
                    GuiDialogs
                            .showErrorMessage("El nuevo miembro no ha sido insertado.");
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
                if (updated) {
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

    @Override
    public void mouseClicked(MouseEvent e) {
        String sc = (String) pais.getSelectedItem();
        if (sc.equals("Mexico")) {
            Object[] possibilities = DatabaseOperations.getStates().toArray();
            Object o = GuiDialogs.showInputDialog(
                    "Seleccione el estado de la Rep" + Helpers.UACUTE
                            + "blica:\n", possibilities, "Distrito Federal");

            if (o != null) {
                String s = o.toString();
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
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }
}
