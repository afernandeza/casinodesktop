package gamesmanager.ui.forms;

import gamesmanager.beans.Employee;
import gamesmanager.beans.GameSession;
import gamesmanager.beans.GameTable;
import gamesmanager.db.EmployeeManager;
import gamesmanager.db.SessionManager;
import gamesmanager.db.TableManager;
import gamesmanager.ui.GuiDialogs;
import gamesmanager.ui.Helpers;
import gamesmanager.ui.session.Session;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableColumn;

public class ViewSessions extends JFrame implements ActionListener,
        MouseListener, ItemListener {

    private static final long serialVersionUID = 1L;
    private static String NULLSTRING = "N/A";

    private static final int TWIDTH = 800;
    private static final int THEIGHT = 400;
    private GridBagConstraints c = new GridBagConstraints();
    private JTable table;
    private SessionsTableModel etm;
    private Object[][] sessions = null;

    private JComboBox tableselector;
    private JTextField startchips;
    private JComboBox empselector;
    private JButton newsessionbutton;
    private JCheckBox displayopensessions;

    private GameSession gs = new GameSession();

    public ViewSessions() {
        super("Administrar Sesiones de Juego");
        this.setLayout(new GridBagLayout());
        c.gridx = 0;
        c.gridy = 0;

        JPanel newtablepanel = new JPanel();
        JLabel idlabel = new JLabel("<html><b>Mesa:</b></html>");
        newtablepanel.add(idlabel);

        tableselector = new JComboBox();
        for (GameTable gt : TableManager.getTablesArray()) {
            tableselector.addItem(gt);
        }
        newtablepanel.add(tableselector);

        JLabel sc = new JLabel("<html><b>Fichas inicio:</b></html>");
        newtablepanel.add(sc);

        startchips = new JTextField(5);
        newtablepanel.add(startchips, c);
        newtablepanel.setBackground(Helpers.LIGHTBLUE);

        JLabel empl = new JLabel("<html><b>Responsable:</b></html>");
        newtablepanel.add(empl);

        empselector = new JComboBox();
        for (Employee emp : EmployeeManager.getEmployeeNames()) {
            empselector.addItem(emp);
        }
        newtablepanel.add(empselector);

        newsessionbutton = new JButton("Agregar sesi" + Helpers.OACUTE + "n");
        newsessionbutton.addActionListener(this);
        newtablepanel.add(newsessionbutton);

        this.add(newtablepanel);

        JPanel tablepanel = new JPanel();
        tablepanel.setLayout(new GridLayout(1, 0));

        String[] columns = SessionManager.TABLECOLUMNS;
        sessions = SessionManager.getOpenGameSessions();
        etm = new SessionsTableModel(columns, sessions);
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

        c.gridy++;
        displayopensessions = new JCheckBox("Mostrar sesiones abiertas "
                + Helpers.UACUTE + "nicamente.");
        displayopensessions.setSelected(true);
        displayopensessions.addItemListener(this);
        this.add(displayopensessions, c);

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
        column.setPreferredWidth(5);

        column = this.table.getColumnModel().getColumn(1);
        column.setPreferredWidth(5);

        column = this.table.getColumnModel().getColumn(2);
        column.setPreferredWidth(120);

        column = this.table.getColumnModel().getColumn(3);
        column.setPreferredWidth(10);

        column = this.table.getColumnModel().getColumn(4);
        column.setPreferredWidth(10);

        column = this.table.getColumnModel().getColumn(5);
        column.setPreferredWidth(120);

        column = this.table.getColumnModel().getColumn(6);
        column.setPreferredWidth(70);

        column = this.table.getColumnModel().getColumn(7);
        column.setPreferredWidth(70);
    }

    public void refreshData() {
        if (displayopensessions.isSelected()) {
            this.sessions = SessionManager.getOpenGameSessions();
        } else {
            this.sessions = SessionManager.getAllGameSessions();
        }
        this.etm.setData(this.sessions);
    }

    public boolean validateForm() {
        String s = this.startchips.getText().trim();
        if (!s.equals("")) {
            try {
                double d = Double.parseDouble(s);
                gs.setFichasinicio(d);

                Employee e = (Employee) this.empselector.getSelectedItem();
                gs.setEmpleadoid(e.getId());

                GameTable gt = (GameTable) this.tableselector.getSelectedItem();
                gs.setTableid(gt.getTableid());

                return true;
            } catch (Exception e) {
                if (Helpers.DEBUG) {
                    e.printStackTrace();
                }
                GuiDialogs.showErrorMessage("Escriba un valor num"
                        + Helpers.EACUTE + "rico para el n" + Helpers.UACUTE
                        + "mero de fichas de inicio.");
            }
        } else {
            GuiDialogs.showErrorMessage("Escriba un valor num" + Helpers.EACUTE
                    + "rico para el n" + Helpers.UACUTE
                    + "mero de fichas de inicio.");
        }
        return false;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (this.validateForm()) {
            if (Session.mayOpenGameSession()) {
                if (SessionManager.startGameSession(gs)) {
                    this.refreshData();
                    GuiDialogs.showSuccessMessage("La sesi" + Helpers.OACUTE
                            + "n de juego ha sido iniciada exitosamente");
                } else {
                    GuiDialogs
                            .showErrorMessage("No se ha podido iniciar la sesi"
                                    + Helpers.OACUTE + "n de juego.");
                }
            } else {
                GuiDialogs.showPermissionsError();
            }
        }
    }

    class SessionsTableModel extends AbstractTableModel {

        static final long serialVersionUID = -1;

        private String[] columnNames;
        private Object[][] data;

        public SessionsTableModel(String[] columnNames, Object[][] data) {
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
                if (sessions[selindex][4] == null) {
                    Object obj = GuiDialogs.showInputDialog("Escriba el n"
                            + Helpers.UACUTE
                            + "mero de fichas con las que la sesi"
                            + Helpers.OACUTE + "n termin" + Helpers.OACUTE
                            + ".");

                    if (obj != null) {
                        String s = obj.toString();
                        int sessionid = Integer.parseInt(sessions[selindex][0]
                                .toString());
                        double fichas = 0.0;
                        try {
                            fichas = Double.parseDouble(s);
                            int i = GuiDialogs
                                    .showConfirmDialog(Helpers.OQUESTIONM
                                            + "Est"
                                            + Helpers.AACUTE
                                            + " seguro que desea cerrar la sesi"
                                            + Helpers.OACUTE + "n de juego"
                                            + Helpers.CQUESTIONM);
                            if (i == 0) {
                                if (SessionManager.closeGameSession(sessionid,
                                        fichas)) {
                                    this.refreshData();
                                    GuiDialogs
                                            .showSuccessMessage("La sesi"
                                                    + Helpers.OACUTE
                                                    + "n de juego ha sido cerrada exitosamente.");
                                } else {
                                    GuiDialogs
                                            .showErrorMessage("No se ha podido cerrar la sesi"
                                                    + Helpers.OACUTE
                                                    + "n de juego");
                                }
                            }
                        } catch (Exception ex) {
                            GuiDialogs
                                    .showErrorMessage("Debe escribir un valor num"
                                            + Helpers.EACUTE + "rico.");
                        }
                    }
                } else {
                    GuiDialogs
                            .showErrorMessage("No hay operaciones disponibles para dicha sesi"
                                    + Helpers.OACUTE + "n de juego.");
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

    @Override
    public void itemStateChanged(ItemEvent e) {
        this.refreshData();
    }

}