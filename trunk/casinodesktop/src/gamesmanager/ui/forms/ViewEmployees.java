package gamesmanager.ui.forms;

import gamesmanager.beans.Employee;
import gamesmanager.beans.User;
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
import java.util.LinkedList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JLabel;
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
    private static String NULLSTRING = "N/A";

    private String DEACTIVATE = "Desactivar cuenta de usuario";
    private String REACTIVATE = "Reactivar cuenta de usuario";
    private String FIRE = "Dar baja temporal";
    private String REHIRE = "Recontratar empleado";
    private String UPDATEINFO = "Actualizar informaci" + Helpers.OACUTE
            + "n personal";

    private static final int TWIDTH = 800;
    private static final int THEIGHT = 400;
    private GridBagConstraints c = new GridBagConstraints();
    private JTextField buscar;
    private JTable table;
    private EmployeeTableModel etm;
    private Object[][] emps = null;

    public String INSTRUCTIONS = "Seleccione qu" + Helpers.EACUTE
            + " desea hacer con el empleado:";

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
        Helpers.setIcon(this);
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
        String squery = this.buscar.getText().trim();
        this.emps = EmployeeManager.searchEmployees(squery);
        this.etm.setData(this.emps);
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
    public void keyPressed(KeyEvent e) {
    }

    @Override
    public void keyReleased(KeyEvent e) {
        this.search();
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getClickCount() == 2) {
            int selindex = table.getSelectedRow();
            if (selindex != -1) {
                String employeeid = emps[selindex][0].toString();
                Employee editemp = EmployeeManager.findEmployee(employeeid);
                User u = editemp.getUser();

                List<String> options = new LinkedList<String>();

                if (Session.mayActivateAccount()) {
                    if (u.isActive()) {
                        options.add(DEACTIVATE);
                    } else {
                        options.add(REACTIVATE);
                    }
                }

                if (Session.mayTemporarilyFire()) {
                    if (editemp.getFired() == null) {
                        options.add(FIRE);
                    } else {
                        options.add(REHIRE);
                    }
                }

                if (Session.mayChangePersonalInfoFor(employeeid)) {
                    options.add(UPDATEINFO);
                }
                
                if(options.size() == 0){
                    GuiDialogs.showPermissionsError();
                    return;
                }

                Object o = GuiDialogs.showInputDialog(INSTRUCTIONS, options
                        .toArray());

                if (o != null) {
                    String opt = o.toString();
                    if (opt.equals(DEACTIVATE)) {
                        // Desactivar cuenta del usuario
                        if (Session.mayDeactivateAccount(employeeid)) {
                            if (EmployeeManager.deactivateAccount(employeeid)) {
                                this.search();
                                GuiDialogs
                                        .showSuccessMessage("Cuenta de usuario desactivada.");
                            } else {
                                GuiDialogs
                                        .showErrorMessage("No se pudo desactivar la"
                                                + " cuenta del usuario seleccionado.");
                            }
                        } else {
                            GuiDialogs.showPermissionsError();
                        }
                    } else if (opt.equals(REACTIVATE)) {
                        // Reactivar cuenta de usuario
                        if (Session.mayActivateAccount()) {
                            if (EmployeeManager.reactivateAccount(employeeid)) {
                                this.search();
                                GuiDialogs
                                        .showSuccessMessage("Cuenta de usuario reactivada.");
                            } else {
                                GuiDialogs
                                        .showErrorMessage("No se pudo reactivar la"
                                                + " cuenta del usuario seleccionado.");
                            }
                        } else {
                            GuiDialogs.showPermissionsError();
                        }
                    } else if (opt.equals(FIRE)) {
                        // Dar baja temporal
                        if (Session.mayTemporarilyFire()) {
                            if (EmployeeManager.fireTemporarily(employeeid)) {
                                this.search();
                                GuiDialogs
                                        .showSuccessMessage("Empleado dado de baja temporal.");
                            } else {
                                GuiDialogs
                                        .showErrorMessage("No se pudo dar de "
                                                + "baja temporal al empleado seleccionado.");
                            }
                        } else {
                            GuiDialogs.showPermissionsError();
                        }
                    } else if (opt.equals(REHIRE)) {
                        // Recontratar empleado
                        if (Session.mayHire()) {
                            if (EmployeeManager.rehire(employeeid)) {
                                this.search();
                                GuiDialogs
                                        .showSuccessMessage("Empleado recontratado.");
                            } else {
                                GuiDialogs
                                        .showErrorMessage("No se pudo recontratar "
                                                + "el empleado seleccionado.");
                            }
                        } else {
                            GuiDialogs.showPermissionsError();
                        }
                    } else if (opt.equals(UPDATEINFO)) {
                        // Actualizar information personal
                        if (Session.mayChangePersonalInfoFor(employeeid)) {
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