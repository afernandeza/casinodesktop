package gamesmanager.ui.forms;

import gamesmanager.beans.Type;
import gamesmanager.db.GameTypeManager;
import gamesmanager.ui.GuiDialogs;
import gamesmanager.ui.Helpers;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class GameTypeForm extends JFrame implements ListSelectionListener {

    private static final long serialVersionUID = 1L;
    private JList list;
    private DefaultListModel listModel;

    private static final String hireString = "Agregar";
    private static final String fireString = "Borrar";
    private JButton fireButton;
    private JTextField employeeName;

    public GameTypeForm() {
        super("Tipos de juego");
        JPanel p = new JPanel(new BorderLayout());

        listModel = new DefaultListModel();

        for (Type t : GameTypeManager.getGameTypes()) {
            listModel.addElement(t.getType());
        }
        // listModel.addElement("Debbie Scott");
        // listModel.addElement("Scott Hommel");
        // listModel.addElement("Sharon Zakhour");

        // Create the list and put it in a scroll pane.
        list = new JList(listModel);
        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        list.setSelectedIndex(-1);
        list.addListSelectionListener(this);
        list.setVisibleRowCount(5);
        JScrollPane listScrollPane = new JScrollPane(list);

        JButton hireButton = new JButton(hireString);
        HireListener hireListener = new HireListener(hireButton);
        hireButton.setActionCommand(hireString);
        hireButton.addActionListener(hireListener);
        hireButton.setEnabled(false);

        fireButton = new JButton(fireString);
        fireButton.setActionCommand(fireString);
        fireButton.addActionListener(new FireListener());
        fireButton.setEnabled(false);

        employeeName = new JTextField(10);
        employeeName.addActionListener(hireListener);
        employeeName.getDocument().addDocumentListener(hireListener);
        // String name = listModel.getElementAt(
        // list.getSelectedIndex()).toString();

        // Create a panel that uses BoxLayout.
        JPanel buttonPane = new JPanel();
        buttonPane.setLayout(new BoxLayout(buttonPane, BoxLayout.LINE_AXIS));
        buttonPane.add(fireButton);
        buttonPane.add(Box.createHorizontalStrut(5));
        buttonPane.add(new JSeparator(SwingConstants.VERTICAL));
        buttonPane.add(Box.createHorizontalStrut(5));
        buttonPane.add(employeeName);
        buttonPane.add(hireButton);
        buttonPane.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

        buttonPane.setOpaque(true);
        buttonPane.setBackground(Helpers.LIGHTBLUE);
        
        p.add(listScrollPane, BorderLayout.CENTER);
        p.add(buttonPane, BorderLayout.PAGE_END);

        p.setOpaque(true);
        p.setBackground(Helpers.LIGHTBLUE);
        
        this.setContentPane(p);
        Helpers.setIcon(this);
        this.pack();
        this.setSize(this.getWidth(), this.getHeight() + 30);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    class FireListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            // This method can be called only if
            // there's a valid selection
            // so go ahead and remove whatever's selected.
            int index = list.getSelectedIndex();

            String ename = (String) list.getSelectedValue();
            if (GameTypeManager.deleteGameType(ename)) {
                listModel.remove(index);
            } else {
                GuiDialogs.showErrorMessage("No se puede borrar este tipo de juego porque \n" +
                		"hay al menos una mesa de juego que lo ofrece."); 
            }

            int size = listModel.getSize();

            if (size == 0) { // Nobody's left, disable firing.
                fireButton.setEnabled(false);

            } else { // Select an index.
                if (index == listModel.getSize()) {
                    // removed item in last position
                    index--;
                }

                list.setSelectedIndex(index);
                list.ensureIndexIsVisible(index);
            }
        }
    }

    // This listener is shared by the text field and the hire button.
    class HireListener implements ActionListener, DocumentListener {
        private boolean alreadyEnabled = false;
        private JButton button;

        public HireListener(JButton button) {
            this.button = button;
        }

        // Required by ActionListener.
        public void actionPerformed(ActionEvent e) {
            String name = employeeName.getText().trim();

            // User didn't type in a unique name...
            if (name.equals("") || alreadyInList(name)) {
                GuiDialogs.showErrorMessage("No se pudo agregar el tipo de juego pues el nombre \n" +
                "es incorrecto o ya existe en la base de datos.");
                employeeName.requestFocusInWindow();
                employeeName.selectAll();
                return;
            }

            int index = list.getSelectedIndex(); // get selected index
            if (index == -1) { // no selection, so insert at beginning
                index = 0;
            } else { // add after the selected item
                index++;
            }

            String ename = employeeName.getText();
            if (GameTypeManager.insertGameType(ename)) {
                listModel.insertElementAt(ename, index);
            } else {
                GuiDialogs.showErrorMessage("Hubo un error inesperado.");
            }
            // If we just wanted to add to the end, we'd do this:
            // listModel.addElement(employeeName.getText());

            // Reset the text field.
            employeeName.requestFocusInWindow();
            employeeName.setText("");

            // Select the new item and make it visible.
            list.setSelectedIndex(index);
            list.ensureIndexIsVisible(index);
        }

        // This method tests for string equality. You could certainly
        // get more sophisticated about the algorithm. For example,
        // you might want to ignore white space and capitalization.
        protected boolean alreadyInList(String name) {
            return listModel.contains(name);
        }

        // Required by DocumentListener.
        public void insertUpdate(DocumentEvent e) {
            enableButton();
        }

        // Required by DocumentListener.
        public void removeUpdate(DocumentEvent e) {
            handleEmptyTextField(e);
        }

        // Required by DocumentListener.
        public void changedUpdate(DocumentEvent e) {
            if (!handleEmptyTextField(e)) {
                enableButton();
            }
        }

        private void enableButton() {
            if (!alreadyEnabled) {
                button.setEnabled(true);
            }
        }

        private boolean handleEmptyTextField(DocumentEvent e) {
            if (e.getDocument().getLength() <= 0) {
                button.setEnabled(false);
                alreadyEnabled = false;
                return true;
            }
            return false;
        }
    }

    // This method is required by ListSelectionListener.
    public void valueChanged(ListSelectionEvent e) {
        if (e.getValueIsAdjusting() == false) {

            if (list.getSelectedIndex() == -1) {
                // No selection, disable fire button.
                fireButton.setEnabled(false);

            } else {
                // Selection, enable the fire button.
                fireButton.setEnabled(true);
            }
        }
    }
}