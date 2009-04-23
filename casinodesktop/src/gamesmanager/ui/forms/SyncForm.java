package gamesmanager.ui.forms;

import gamesmanager.beans.Casino;
import gamesmanager.db.CasinoManager;
import gamesmanager.db.SyncFormThread;
import gamesmanager.db.Synchronizer;
import gamesmanager.db.SynchronizerThread;
import gamesmanager.ui.GuiDialogs;
import gamesmanager.ui.Helpers;
import gamesmanager.ui.session.Session;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableColumn;

public class SyncForm extends JFrame implements ActionListener, MouseListener {

    private static final long serialVersionUID = 1L;

    private static String NULLSTRING = "N/A";

    private static final int TWIDTH = 500;
    private static final int THEIGHT = 300;
    private GridBagConstraints c = new GridBagConstraints();
    private JTextField id;
    private JTextField jdbchost;
    private JButton addbutton;
    private JButton syncbutton;
    private JButton refreshbutton;
    private JTable table;
    private SynInfoTableModel etm;
    private Object[][] syncinfo = null;
    private Casino cas;

    public String[] OPTIONS = { "Cambiar el URL de JDBC", "Dar casino de baja" };
    public String INSTRUCTIONS = "Seleccione qu" + Helpers.EACUTE
            + " desea hacer con el casino seleccionado:";

    public SyncForm() {
        super("Sincronizaci" + Helpers.OACUTE + "n");
        this.setLayout(new GridBagLayout());
        c.gridx = 0;
        c.gridy = 0;

        JPanel newcasino = new JPanel();
        newcasino.setBackground(Helpers.LIGHTBLUE);

        JLabel idlabel = new JLabel("<html><b>ID: </b></html>");
        newcasino.add(idlabel);

        id = new JTextField(5);
        newcasino.add(id);

        JLabel jdbchostlabel = new JLabel("<html><b>URL JDBC: </b></html>");
        newcasino.add(jdbchostlabel);

        jdbchost = new JTextField(18);
        newcasino.add(jdbchost);

        addbutton = new JButton("Agregar");
        addbutton.addActionListener(this);
        newcasino.add(addbutton);

        this.add(newcasino);

        JPanel tablepanel = new JPanel();
        tablepanel.setLayout(new GridLayout(1, 0));

        String[] columns = { "ID", "URL JDBC", "Disponible" };
        syncinfo = Synchronizer.getCasinosTable();
        etm = new SynInfoTableModel(columns, syncinfo);
        table = new JTable(etm);

        this.setColumnWidths();

        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        table.addMouseListener(this);
        table.setRowSelectionAllowed(true);

        table
                .setPreferredScrollableViewportSize(new Dimension(TWIDTH,
                        THEIGHT));
        table.setFillsViewportHeight(true);

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane
                .setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane
                .setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        tablepanel.add(scrollPane);

        c.gridy++;
        this.add(tablepanel, c);

        c.gridy++;

        JPanel syncbuttons = new JPanel();
        syncbuttons.setBackground(Helpers.LIGHTBLUE);

        refreshbutton = new JButton("Actualizar");
        refreshbutton.addActionListener(this);
        syncbuttons.add(refreshbutton);

        syncbutton = new JButton("Sincronizar");
        syncbutton.addActionListener(this);
        syncbuttons.add(syncbutton);

        this.add(syncbuttons, c);

        this.getContentPane().setBackground(Helpers.LIGHTBLUE);
        Helpers.setIcon(this);
        this.pack();
        this.setSize(this.getWidth() + Helpers.XOFFSET, this.getHeight()
                + Helpers.YOFFSET);
        this.setLocationRelativeTo(null);
    }

    private void setColumnWidths() {
        TableColumn column = null;
        column = this.table.getColumnModel().getColumn(0);
        column.setPreferredWidth(100);

        column = this.table.getColumnModel().getColumn(1);
        column.setPreferredWidth(300);

        column = this.table.getColumnModel().getColumn(2);
        column.setPreferredWidth(100);
    }

    public boolean validateForm() {
        String cid = id.getText().trim();
        String host = jdbchost.getText().trim();
        if (!cid.equals("") && !host.equals("")) {
            cas = new Casino(cid, host);
            return true;
        }
        return false;
    }

    public void refresh() {
        SyncFormThread syncer = new SyncFormThread();
        syncer.execute();
        this.dispose();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object src = e.getSource();
        if (src.equals(syncbutton)) {
            SynchronizerThread st = new SynchronizerThread();
            st.execute();
            this.dispose();
        } else if (src.equals(addbutton)) {
            if (Session.mayManageCasinos()) {
                if (this.validateForm()) {
                    if (CasinoManager.insertCasino(this.cas)) {
                        GuiDialogs
                                .showSuccessMessage("El casino ha sido insertado correctamente.");
                        this.refresh();
                    }
                }
            } else {
                GuiDialogs.showPermissionsError();
            }
        } else if (src.equals(refreshbutton)) {
            this.refresh();
        }
    }

    class SynInfoTableModel extends AbstractTableModel {

        static final long serialVersionUID = -1;

        private String[] columnNames;
        private Object[][] data;

        public SynInfoTableModel(String[] columnNames, Object[][] data) {
            this.columnNames = columnNames;
            this.data = data;
        }

        public int getColumnCount() {
            return columnNames.length;
        }

        public int getRowCount() {
            return data.length;
        }

        public String getColumnName(int col) {
            return columnNames[col];
        }

        public Object getValueAt(int row, int col) {
            if (row < this.data.length && col < this.data[row].length) {
                if (data[row][col] != null) {
                    if (data[row][col] instanceof Boolean) {
                        if ((Boolean) data[row][col]) {
                            return "SI";
                        } else {
                            return "NO";
                        }
                    }
                    return data[row][col];
                } else {
                    return NULLSTRING;
                }
            } else {
                if (Helpers.DEBUG) {
                    System.out.println("nothing there");
                }
                return "";
            }
        }

        // public Class<?> getColumnClass(int c) {
        // return getValueAt(0, c).getClass();
        // }

        public boolean isCellEditable(int row, int col) {
            return false;
        }

        public void initializeArray(int rows, int cols) {
            this.data = new Object[rows][cols];
        }

        public void setData(Object[][] o) {
            this.data = o;
            this.fireTableDataChanged();
        }

        public void setValueAt(Object value, int row, int col) {
            if (row < this.data.length && col < this.data[row].length) {
                if (value != null) {
                    data[row][col] = value;
                    this.fireTableDataChanged();
                } else {
                    data[row][col] = NULLSTRING;
                    this.fireTableDataChanged();
                }
            }
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getClickCount() == 2) {
            int selindex = table.getSelectedRow();
            if (selindex != -1) {
                if (Session.mayManageCasinos()) {
                    Object o = GuiDialogs.showInputDialog(INSTRUCTIONS,
                            OPTIONS, 0);
                    String casinoid = syncinfo[selindex][0].toString();
                    if (o != null) {
                        String s = o.toString();
                        if (s.equals(OPTIONS[0])) {
                            // update casino
                            Object obj = GuiDialogs.showInputDialog("Escriba el "
                                    + "nuevo URL de JDBC:");

                            if (obj != null) {
                                String newhost = obj.toString();
                                if(!newhost.equals("")){
                                    Casino c = new Casino(casinoid, newhost);
                                    if(CasinoManager.updateCasino(c)){
                                        GuiDialogs.showSuccessMessage("Casino actualizado correctamente.");
                                        this.refresh();
                                    } else {
                                        GuiDialogs.showErrorMessage("No se pudo actualizar el URL de JDBC");
                                    }
                                }
                            }
                        } else if (s.equals(OPTIONS[1])) {
                            //delete casino
                            int n = GuiDialogs
                                    .showConfirmDialog(Helpers.OQUESTIONM
                                            + "Est"
                                            + Helpers.AACUTE
                                            + " seguro que desea eliminar el casino"
                                            + Helpers.CQUESTIONM);
                            if (n == 0) {
                                // confirmar
                                if (CasinoManager.deleteCasino(casinoid)) {
                                    GuiDialogs.showSuccessMessage("Casino borrado correctamente.");
                                    this.refresh();
                                } else {
                                    //cancelar
                                }
                            }
                        }
                    }
                } else {
                    GuiDialogs.showPermissionsError();
                }
            }
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