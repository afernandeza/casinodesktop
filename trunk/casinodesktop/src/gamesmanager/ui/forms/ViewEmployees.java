package gamesmanager.ui.forms;

import gamesmanager.beans.Employee;
import gamesmanager.db.EmployeeManager;
import gamesmanager.ui.GuiDialogs;
import gamesmanager.ui.Helpers;
import gamesmanager.ui.session.Session;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableColumn;

public class ViewEmployees extends JFrame implements ActionListener,
KeyListener, MouseListener {

    private static final long serialVersionUID = 1L;

    private static final int TWIDTH = 800;
    private static final int THEIGHT = 400;
    private GridBagConstraints c = new GridBagConstraints();
    private JTextField buscar;
    private JTable table;
    private EmployeeTableModel etm;
    private Object[][] emps = null;

    public String[] OPTIONS = { "Desactivar cuenta de usuario",
            "Reactivar cuenta de usuario", "Dar baja temporal",
            "Recontratar empleado", "Dar baja definitiva",
            "Cambiar contrase" + Helpers.NTILDE + "a",
            "Actualizar informaci" + Helpers.OACUTE + "n personal" };
    public String INSTRUCTIONS = "Seleccione qu" + Helpers.EACUTE
    + " desea hacer con el empleado:";
    private String NULLSTRING = "N/A";

    public ViewEmployees() {
        super("Administrar Empleados");
        this.setLayout(new GridBagLayout());
        c.gridx = 0;
        c.gridy = 0;

        JPanel buscarp = new JPanel();
        JLabel buscarlabel = new JLabel("<html><b>Buscar: </b></html>");
        buscarp.add(buscarlabel);

        buscar = new JTextField(20);
        buscar.addKeyListener(this);
        buscarp.add(buscar, c);
        buscarp.setBackground(Helpers.LIGHTBLUE);
        this.add(buscarp);

        JPanel tablepanel = new JPanel();
        tablepanel.setLayout(new GridLayout(1, 0));

        String[] columns = EmployeeManager.EMPLOYEECOLUMNS;
        emps = EmployeeManager.getEmployeesSummary();
        etm = new EmployeeTableModel(columns, emps);
        table = new JTable(etm);
        table.addMouseListener(this);

        this.setColumnWidths();

        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
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

        this.getContentPane().setBackground(Helpers.LIGHTBLUE);
        this.pack();
        this.setSize(this.getWidth() + Helpers.XOFFSET, this.getHeight()
                + Helpers.YOFFSET);
        this.setLocationRelativeTo(null);
    }

    private void setColumnWidths() {
        TableColumn column = null;
        column = this.table.getColumnModel().getColumn(0);
        column.setPreferredWidth(10);

        column = this.table.getColumnModel().getColumn(1);
        column.setPreferredWidth(10);

        column = this.table.getColumnModel().getColumn(2);
        column.setPreferredWidth(120);

        column = this.table.getColumnModel().getColumn(3);
        column.setPreferredWidth(10);

        column = this.table.getColumnModel().getColumn(4);
        column.setPreferredWidth(10);

        column = this.table.getColumnModel().getColumn(5);
        column.setPreferredWidth(45);

        column = this.table.getColumnModel().getColumn(6);
        column.setPreferredWidth(40);

        column = this.table.getColumnModel().getColumn(7);
        column.setPreferredWidth(60);

        column = this.table.getColumnModel().getColumn(8);
        column.setPreferredWidth(10);
    }

    public void search() {
        this.emps = EmployeeManager.getEmployeesSummary();
        for (int i = 0; i < emps.length; i++) {
            for (int j = 0; j < emps[0].length; j++) {
                this.etm.setValueAt(emps[i][j], i, j);
            }
        }
    }

    public void refreshData(int selindex) {
        this.emps = EmployeeManager.getEmployeesSummary();
        for (int j = 0; j < emps[0].length; j++) {
            this.etm.setValueAt(emps[selindex][j], selindex, j);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
    }

    class EmployeeTableModel extends AbstractTableModel {

        static final long serialVersionUID = -1;

        private String[] columnNames;
        private Object[][] data;

        public EmployeeTableModel(String[] columnNames, Object[][] data) {
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

        public void setValueAt(Object value, int row, int col) {
            if (row < this.data.length && col < this.data[row].length) {
                if (value != null) {
                    // if (Helpers.DEBUG) {
                    // System.out.println("Setting value at " + row + "," + col
                    // + " to " + value + " (an instance of "
                    // + value.getClass() + ")");
                    // }
                    data[row][col] = value;
                    this.fireTableCellUpdated(row, col);
                } else {
                    data[row][col] = NULLSTRING;
                    this.fireTableCellUpdated(row, col);
                }
            }
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ENTER) {
            this.search();
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getClickCount() == 2) {
            int selindex = table.getSelectedRow();
            if (selindex != -1) {
                String opt = GuiDialogs.showInputDialog(INSTRUCTIONS, OPTIONS,
                        6);

                String employeeid = emps[selindex][0].toString();

                if ((opt != null) && (opt.length() > 0)) {
                    if (opt.equals(OPTIONS[0])) {
                        // Desactivar cuenta del usuario
                        if (Session.mayDeactivateAccount(employeeid)) {
                            if (EmployeeManager.deactivateAccount(employeeid)) {
                                GuiDialogs
                                .showSuccessMessage("Cuenta de usuario desactivada.");
                                this.refreshData(selindex);
                            } else {
                                GuiDialogs
                                .showErrorMessage("No se pudo desactivar la"
                                        + " cuenta del usuario seleccionado.");
                            }
                        } else {
                            GuiDialogs.showPermissionsError();
                        }
                    } else if (opt.equals(OPTIONS[1])) {
                        // Reactivar cuenta de usuario
                        if (Session.mayActivateAccount()) {
                            if (EmployeeManager.reactivateAccount(employeeid)) {
                                GuiDialogs
                                .showSuccessMessage("Cuenta de usuario reactivada.");
                                this.refreshData(selindex);
                            } else {
                                GuiDialogs
                                .showErrorMessage("No se pudo reactivar la"
                                        + " cuenta del usuario seleccionado.");
                            }
                        } else {
                            GuiDialogs.showPermissionsError();
                        }
                    } else if (opt.equals(OPTIONS[2])) {
                        // Dar baja temporal
                        if (Session.mayTemporarilyFire()) {
                            if (EmployeeManager.fireTemporarily(employeeid)) {
                                GuiDialogs
                                .showSuccessMessage("Empleado dado de baja temporal.");
                                this.refreshData(selindex);
                            } else {
                                GuiDialogs
                                .showErrorMessage("No se pudo dar de "
                                        + "baja temporal al empleado seleccionado.");
                            }
                        } else {
                            GuiDialogs.showPermissionsError();
                        }
                    } else if (opt.equals(OPTIONS[3])) {
                        // Recontratar empleado
                        if (Session.mayHire()) {
                            if (EmployeeManager.rehire(employeeid)) {
                                GuiDialogs
                                .showSuccessMessage("Empleado recontratado.");
                                this.refreshData(selindex);
                            } else {
                                GuiDialogs
                                .showErrorMessage("No se pudo recontratar "
                                        + "el empleado seleccionado.");
                            }
                        } else {
                            GuiDialogs.showPermissionsError();
                        }
                    } else if (opt.equals(OPTIONS[4])) {
                        // Dar baja definitiva
                        if (Session.mayPermanentlyFire()) {
                        } else {
                            GuiDialogs.showPermissionsError();
                        }
                    } else if (opt.equals(OPTIONS[5])) {
                        // Cambiar password
                        if (Session.mayChangePasswordFor(employeeid)) {
                        } else {
                            GuiDialogs.showPermissionsError();
                        }
                    } else if (opt.equals(OPTIONS[6])) {
                        // Actualizar information personal
                        if (Session.mayChangePersonalInfoFor(employeeid)) {
                            Employee editemp = EmployeeManager
                            .findEmployee(employeeid);
                            EmployeeInfoForm eif = new EmployeeInfoForm(editemp);
                            eif.loadCurrentImage();
                            eif.setEmployeeViewer(this, selindex);
                            eif.setVisible(true);
                        } else {
                            GuiDialogs.showPermissionsError();
                        }
                    }
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