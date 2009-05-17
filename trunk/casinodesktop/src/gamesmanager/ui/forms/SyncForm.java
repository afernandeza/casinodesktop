package gamesmanager.ui.forms;

import gamesmanager.db.sync.SyncFormThread;
import gamesmanager.db.sync.Synchronizer;
import gamesmanager.db.sync.SynchronizerThread;
import gamesmanager.ui.Helpers;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableColumn;

public class SyncForm extends JFrame implements ActionListener {

    private static final long serialVersionUID = 1L;

    private static String NULLSTRING = "N/A";

    private static final int TWIDTH = 500;
    private static final int THEIGHT = 300;
    private GridBagConstraints c = new GridBagConstraints();
    private JButton syncbutton;
    private JButton refreshbutton;
    private JButton cancel;
    private JTable table;
    private SynInfoTableModel etm;
    private Object[][] syncinfo = null;

    public SyncForm() {
        super("Sincronizaci" + Helpers.OACUTE + "n");
        this.setLayout(new GridBagLayout());
        c.gridx = 0;
        c.gridy = 0;

        JPanel tablepanel = new JPanel();
        tablepanel.setLayout(new GridLayout(1, 0));

        String[] columns = { "ID", "URL JDBC", "Disponible" };
        syncinfo = Synchronizer.getCasinosTable();
        etm = new SynInfoTableModel(columns, syncinfo);
        table = new JTable(etm);

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

        JPanel syncbuttons = new JPanel();
        syncbuttons.setBackground(Helpers.LIGHTBLUE);

        refreshbutton = new JButton("Actualizar");
        refreshbutton.addActionListener(this);
        syncbuttons.add(refreshbutton);

        syncbutton = new JButton("Sincronizar");
        syncbutton.addActionListener(this);
        syncbuttons.add(syncbutton);
        
        cancel = new JButton("Cancelar");
        cancel.addActionListener(this);
        syncbuttons.add(cancel);
        
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
        } else if (src.equals(refreshbutton)) {
            this.refresh();
        } else if(src.equals(cancel)){
            this.dispose();
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
}