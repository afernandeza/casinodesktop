package gamesmanager.ui.forms;

import gamesmanager.beans.GameTable;
import gamesmanager.beans.Type;
import gamesmanager.db.GameTypeManager;
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
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
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

public class ViewGameTables extends JFrame implements ActionListener,
        MouseListener {

    private static final long serialVersionUID = 1L;
    private static String NULLSTRING = "N/A";

    private static final int TWIDTH = 400;
    private static final int THEIGHT = 400;
    private JTable table;
    private GameTableTableModel gttm;
    private Object[][] tableinfo;

    public String[] OPTIONS = { "Cambiar tipo de juego", "Eliminar mesa" };
    
    public String INSTRUCTIONS = "Seleccione qu" + Helpers.EACUTE
            + " desea hacer con la mesa de juego:";

    private JTextField newtableid;
    private JComboBox newgametype;
    private JButton newtablebutton;

    public ViewGameTables() {
        super("Mesas de Juego");
        Helpers.setDefaultAppearance(this, true);
        this.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.gridx = 0;
        c.gridy = 0;

        JPanel newtablepanel = new JPanel();
        JLabel idlabel = new JLabel("<html><b>ID: </b></html>");
        newtablepanel.add(idlabel);

        newtableid = new JTextField(5);
        newtablepanel.add(newtableid, c);
        newtablepanel.setBackground(Helpers.LIGHTBLUE);

        newgametype = new JComboBox();
        for (Type gt : GameTypeManager.getGameTypes()) {
            newgametype.addItem(gt);
        }
        newtablepanel.add(newgametype);

        newtablebutton = new JButton("Agregar mesa");
        newtablebutton.addActionListener(this);
        newtablepanel.add(newtablebutton);

        this.add(newtablepanel);

        JPanel tablepanel = new JPanel();
        tablepanel.setLayout(new GridLayout(1, 0));

        String[] columns = TableManager.TABLECOLUMNS;
        tableinfo = TableManager.getTables();
        gttm = new GameTableTableModel(columns, tableinfo);
        table = new JTable(gttm);
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
        column.setPreferredWidth(200);
    }

    public void refreshData() {
        this.tableinfo = TableManager.getTables();
        this.gttm.setData(this.tableinfo);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object o = e.getSource();
        if (o.equals(this.newtablebutton)) {
            String newid = this.newtableid.getText().trim();
            if (!newid.equals("")) {
                try {
                    int tid = Integer.parseInt(newid);
                    Type t = (Type) this.newgametype.getSelectedItem();
                    int gtid = t.getTypeid();
                    GameTable gt = new GameTable(tid, gtid);
                    if (TableManager.insertGameTable(gt)) {
                        this.refreshData();
                        GuiDialogs
                                .showSuccessMessage("Mesa agregada exitosamente.");
                    } else {
                        GuiDialogs
                                .showErrorMessage("La mesa no ha sido agregada.");
                    }
                } catch (Exception ex) {
                    GuiDialogs.showErrorMessage("El ID de la mesa debe ser num"
                            + Helpers.EACUTE + "rico.");
                }
            } else {
                GuiDialogs.showErrorMessage("ID incorrecto");
            }
        }
    }

    class GameTableTableModel extends AbstractTableModel {

        static final long serialVersionUID = -1;

        private String[] columnNames;
        private Object[][] data;

        public GameTableTableModel(String[] columnNames, Object[][] data) {
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
                if(!Session.mayUpdateGameTable()){
                    GuiDialogs.showPermissionsError();
                    return;
                }
                String tableid = tableinfo[selindex][0].toString();
                int tid = Integer.parseInt(tableid);
                
                if(TableManager.gameTableInUse(tid)){
                    GuiDialogs.showErrorMessage("Esta mesa no se puede editar porque " +
                    		"est"+Helpers.AACUTE+" asociada con una sesi"+Helpers.OACUTE
                    		+"n abierta.");
                    return;
                }

                Object obj = GuiDialogs.showInputDialog(INSTRUCTIONS, OPTIONS,
                        0);

                if (obj != null) {
                    String opt = obj.toString();
                    if (opt.equals(OPTIONS[0])) {
                        Object[] o = GameTypeManager.getGameTypes().toArray();
                        Object ntopt = GuiDialogs.showInputDialog(
                                "Seleccione el nuevo tipo de juego "
                                        + "para la mesa:", o, 0);
                        if ((ntopt != null)) {
                            Type newgt = (Type) ntopt;
                            if (Session.mayUpdateGameTable()) {
                                if (TableManager.updateGameTable(tid, newgt
                                        .getTypeid())) {
                                    this.refreshData();
                                    GuiDialogs
                                            .showSuccessMessage("Mesa actualizada correctamente.");
                                } else {
                                    GuiDialogs
                                            .showErrorMessage("No se pudo cambiar el tipo de juego.");
                                }
                            } else {
                                GuiDialogs.showPermissionsError();
                            }
                        }
                    } else if (opt.equals(OPTIONS[1])) {
                        if (Session.mayDeleteGameTable()) {
                            int i = GuiDialogs
                                    .showConfirmDialog(Helpers.OQUESTIONM
                                            + "Est" + Helpers.AACUTE
                                            + " seguro que desea borrar la "
                                            + "mesa de juego"
                                            + Helpers.CQUESTIONM);
                            if (i == 0) {
                                if (TableManager.deleteGameTable(tid)) {
                                    refreshData();
                                    GuiDialogs
                                            .showSuccessMessage("La mesa de juego ha sido "
                                                    + "borrada exitosamente.");
                                } else {
                                    GuiDialogs
                                            .showErrorMessage("No se pudo borrar la mesa seleccionada.");
                                }
                            }
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
