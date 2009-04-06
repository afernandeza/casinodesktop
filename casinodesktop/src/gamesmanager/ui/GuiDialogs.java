package gamesmanager.ui;

import javax.swing.JOptionPane;

public class GuiDialogs {

    private static final String PERM_ERROR = "Usted no cuenta con los permisos suficientes "
        + "para realizar la operaci" + Helpers.OACUTE + "n deseada.";

    public static void showFormErrorMessage(String msg) {
        JOptionPane.showMessageDialog(null, msg, "Error al introducir datos",
                JOptionPane.ERROR_MESSAGE);
    }

    public static void showErrorMessage(String msg) {
        JOptionPane.showMessageDialog(null, msg, "Error grave",
                JOptionPane.ERROR_MESSAGE);
    }

    public static void showSuccessMessage(String msg) {
        JOptionPane.showMessageDialog(null, msg, "Informaci" + Helpers.OACUTE
                + "n", JOptionPane.INFORMATION_MESSAGE);
    }

    public static String showInputDialog(String instructions, Object[] options,
            int defindex) {
        if (defindex < 0 || defindex >= options.length) {
            if (Helpers.DEBUG) {
                throw new IllegalArgumentException("opcion default invalida");
            }
        }
        return (String) JOptionPane.showInputDialog(null, instructions,
                "Opciones", JOptionPane.QUESTION_MESSAGE, null, options,
                options[defindex]);

    }

    public static String showInputDialog(String instructions, Object[] options,
            Object selected) {
        return (String) JOptionPane.showInputDialog(null, instructions,
                "Opciones", JOptionPane.QUESTION_MESSAGE, null, options,
                selected);

    }

    public static int showConfirmDialog(String msg) {
        return JOptionPane.showConfirmDialog(null, msg, "Confirmar",
                JOptionPane.YES_NO_OPTION);
    }

    public static void showPermissionsError() {
        showErrorMessage(PERM_ERROR);
    }
}
