package ch.eglisi.texteditor.view;

import javax.swing.JOptionPane;
import java.awt.Component;

public final class ViewUtil {
    private ViewUtil() {}

    public static void showErrorMessage(String title, String message) {
        showErrorMessage(null, title, message);
    }

    public static void showErrorMessage(Component parentComponent, String title, String messge) {
        JOptionPane.showMessageDialog(parentComponent, messge, title, JOptionPane.ERROR_MESSAGE);
    }
}
