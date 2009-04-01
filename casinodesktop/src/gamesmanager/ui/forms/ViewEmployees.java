package gamesmanager.ui.forms;

import gamesmanager.db.EmployeeManager;
import gamesmanager.ui.Helpers;

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
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableColumn;

public class ViewEmployees extends JFrame implements ActionListener, KeyListener,
MouseListener{

    private static final long serialVersionUID = 1L;

    private static final int TWIDTH = 800;
    private static final int THEIGHT = 400;
    private GridBagConstraints c = new GridBagConstraints();
    private JTextField buscar;
    private JTable table;
    private EmployeeTableModel etm;
    private Object[][] emps = null;

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
        tablepanel.setLayout(new GridLayout(1,0));

        String[] columns = EmployeeManager.EMPLOYEECOLUMNS;
        emps = EmployeeManager.getEmployeesSummary();
        etm = new EmployeeTableModel(columns, emps);
        table = new JTable(etm);
        table.addMouseListener(this);
        
        this.setColumnWidths();
        
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        table.setRowSelectionAllowed(true);

        table.setPreferredScrollableViewportSize(new Dimension(TWIDTH, THEIGHT));
        table.setFillsViewportHeight(true);

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        tablepanel.add(scrollPane);

        c.gridy++;
        this.add(tablepanel, c);

        this.getContentPane().setBackground(Helpers.LIGHTBLUE);
        this.pack();
        this.setSize(this.getWidth() + Helpers.XOFFSET, this.getHeight()
                + Helpers.YOFFSET);
        this.setLocationRelativeTo(null);
    }

    private void setColumnWidths(){
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
    
    public void search(){
        System.out.println("searching");
        //this.emps = EmployeeManager.search();
        this.etm.fireTableDataChanged();
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
            if(row < this.data.length && col < this.data[row].length){
                if(data[row][col] != null){
                    return data[row][col];   
                } else {
                    return "N/A";
                }
            } else {
                if(Helpers.DEBUG){
                    //System.out.println("nothing there");
                }
                return "";
            }
        }

        public Class<?> getColumnClass(int c) {
            return getValueAt(0, c).getClass();
        }

        public boolean isCellEditable(int row, int col) {
            return false;
        }

        public void initializeArray(int rows, int cols){
            this.data = new Object[rows][cols];
        }

        public void setValueAt(Object value, int row, int col) {
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
        if(e.getClickCount() == 2){
            System.out.println("double clicked");
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