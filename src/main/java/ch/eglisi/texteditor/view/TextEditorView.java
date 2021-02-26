package ch.eglisi.texteditor.view;

import ch.eglisi.texteditor.controller.FileHandling;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import java.awt.event.ActionListener;

public class TextEditorView extends JFrame {
    public TextEditorView() {
        this("SE Code");
    }

    public TextEditorView(String title) {
        super(title);
        initComponents();
        styleUI();
    }

    /**
     * Initialize all Components.
     */
    private void initComponents() {
        initTabs();
    }

    /**
     * Styles th UI
     */
    private void styleUI() {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(800, 400);
        setVisible(true);
    }

    /**
     * Initialize the Tab.
     */
    private void initTabs() {
        var menuBar = new JMenuBar();

        // Create File Menu
        var fileMenu = new JMenu("Datei");
        menuBar.add(fileMenu);

        // Create Items for File Menu
        // create Open File
        var openFile = createMenuItem("Datei öffnen...", e -> FileHandling.openFile());
        fileMenu.add(openFile);

        // create New File
        var newFile = createMenuItem("Neue Datei...", e -> FileHandling.createFile());
        fileMenu.add(newFile);

        /*fileMenu.addSeparator();
        var saveFile = createMenuItem("Datei speichern", e -> FileHandling.saveFile());
        fileMenu.add(saveFile);*/

        setJMenuBar(menuBar);
    }

    /**
     * Creates a JMenuItem.
     * @param title Title of the Item
     * @param actionListener The corresponding Action Listener
     * @return Returns the created JMenuItem
     */
    private JMenuItem createMenuItem(String title, ActionListener actionListener) {
        var menuItem = new JMenuItem(title);
        menuItem.addActionListener(actionListener);
        return menuItem;
    }
}
