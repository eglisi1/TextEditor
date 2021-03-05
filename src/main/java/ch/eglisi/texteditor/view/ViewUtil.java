package ch.eglisi.texteditor.view;

import javax.swing.JOptionPane;
import java.awt.Component;

public final class ViewUtil {
    private ViewUtil() {}

    /**
     * Show a Message Dialog as Error Message
     * @param title Title of the Dialog
     * @param message Message for the User
     */
    public static void showErrorMessage(String title, String message) {
        showErrorMessage(null, title, message);
    }

    public static void showErrorMessage(Component parentComponent, String title, String message) {
        showMessage(parentComponent, message, title, JOptionPane.ERROR_MESSAGE);
    }

    /**
     * Show a Message Dialog as Warning Message
     * @param title Title of the Dialog
     * @param message Message for the User
     */
    public static void showWarningMessage(String title, String message) {
        showWarningMessage(null, message, title);
    }

    public static void showWarningMessage(Component parentComponent, String title, String message) {
        showMessage(parentComponent, message, title, JOptionPane.WARNING_MESSAGE);
    }

    /**
     * Show a Message Dialog
     * @param parentComponent Parent Component of the Message Dialog.
     * @param title Title of the Dialog
     * @param message Message for the User
     * @param type the JOptionPane Message type.
     */
    private static void showMessage(Component parentComponent, String title, String message, int type) {
        JOptionPane.showMessageDialog(parentComponent, message, title, type);
    }
}
