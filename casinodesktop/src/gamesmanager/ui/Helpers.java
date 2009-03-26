package gamesmanager.ui;

import java.awt.Color;
import java.io.File;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class Helpers {

    public final static boolean DEBUG = true;

    public final static Color BEIGE = new Color(236, 233, 216);
    public final static Color BEIGE2 = new Color(251, 246, 243);
    public final static Color LIGHTBLUE = new Color(195, 217, 255);

    public final static char AACUTE = (char) 225;
    public final static char EACUTE = (char) 233;
    public final static char IACUTE = (char) 237;
    public final static char OACUTE = (char) 243;
    public final static char UACUTE = (char) 250;
    public final static char NTILDE = (char) 241;
    public final static char OQUESTIONM = (char) 191;
    public final static char CQUESTIONM = (char) 63;

    public final static int XOFFSET = 5;
    public final static int YOFFSET = 5;

    public final static String jpeg = "jpeg";
    public final static String jpg = "jpg";
    public final static String gif = "gif";
    public final static String tiff = "tiff";
    public final static String tif = "tif";
    public final static String png = "png";

    private Helpers() {
    }

    public static String getExtension(File f) {
        String ext = null;
        String s = f.getName();
        int i = s.lastIndexOf('.');

        if (i > 0 && i < s.length() - 1) {
            ext = s.substring(i + 1).toLowerCase();
        }
        return ext;
    }

    public static void setDefaultAppearance(JFrame frame, boolean background) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }
        frame.setResizable(false);
        JFrame.setDefaultLookAndFeelDecorated(true);
        Helpers.setIcon(frame);
        if (background) {
            frame.setBackground(Helpers.LIGHTBLUE);
        }
    }

    public static ImageIcon createImage(String path, Class<?> c) {
        java.net.URL imgURL = c.getResource(path);
        if (imgURL != null) {
            return new ImageIcon(imgURL);
        } else {
            System.err.println("Couldn't find file: " + imgURL);
            return null;
        }
    }

    /**
     * Aplica el icono de la aplicacion a cualquier ventana dada.
     * 
     * @param jFrame
     *            ventana de la aplicacion
     */
    public static void setIcon(JFrame jFrame) {
        String path = "images/icon.gif";
        try {
            jFrame.setIconImage(createImage(path, new Helpers().getClass())
                    .getImage());
        } catch (Exception e) {
            if (DEBUG) {
                System.out.println("No se pudo colocar icono de la aplicaci"
                        + OACUTE + "n: " + e);
                e.printStackTrace();
            }
        }
    }
}
