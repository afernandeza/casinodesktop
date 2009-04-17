package gamesmanager.ui;

import java.awt.Toolkit;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

public class GuiDialogs {

    private static final ImageIcon INFO = Helpers.createImage(
            "images/infoicon.png", GuiDialogs.class);
    private static final ImageIcon QUESTION = Helpers.createImage(
            "images/questionicon.png", GuiDialogs.class);
    private static final ImageIcon ERROR = Helpers.createImage(
            "images/erroricon.png", GuiDialogs.class);
    private static final ImageIcon WARNING = Helpers.createImage(
            "images/warningicon.png", GuiDialogs.class);
    private static final String PERM_ERROR = "Usted no cuenta con los permisos suficientes "
            + "para realizar la operaci" + Helpers.OACUTE + "n deseada.";

    public static void errorBeep() {
        Toolkit.getDefaultToolkit().beep();
    }

    public static void showFormErrorMessage(String msg) {
        errorBeep();
        JOptionPane.showMessageDialog(null, msg, "Error al introducir datos",
                JOptionPane.ERROR_MESSAGE, ERROR);
    }

    public static void showErrorMessage(String msg) {
        errorBeep();
        JOptionPane.showMessageDialog(null, msg, "Error grave",
                JOptionPane.ERROR_MESSAGE, ERROR);
    }

    public static void showSuccessMessage(String msg) {
        JOptionPane.showMessageDialog(null, msg, "Informaci" + Helpers.OACUTE
                + "n", JOptionPane.INFORMATION_MESSAGE, INFO);
    }

    public static Object showInputDialog(String instructions, Object[] options,
            int defindex) {
        if (defindex < 0 || defindex >= options.length) {
            if (Helpers.DEBUG) {
                throw new IllegalArgumentException("opcion default invalida");
            }
        }
        return JOptionPane.showInputDialog(null, instructions, "Opciones",
                JOptionPane.QUESTION_MESSAGE, QUESTION, options,
                options[defindex]);
    }

    public static Object showInputDialog(String instructions, Object[] options,
            Object selected) {
        return JOptionPane.showInputDialog(null, instructions, "Opciones",
                JOptionPane.QUESTION_MESSAGE, QUESTION, options, selected);

    }

    public static Object showInputDialog(String instructions) {
        return JOptionPane.showInputDialog(null, instructions, "Opciones",
                JOptionPane.QUESTION_MESSAGE, QUESTION, null, null);

    }

    public static int showConfirmDialog(String msg) {
        return JOptionPane.showConfirmDialog(null, msg, "Confirmar",
                JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE,
                WARNING);
    }

    public static void showPermissionsError() {
        showErrorMessage(PERM_ERROR);
    }
}
