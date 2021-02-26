package ch.eglisi.texteditor.view;

import javax.swing.*;
import java.awt.event.ActionListener;

public class TextEditorView extends JFrame {
    public TextEditorView() {
        this("SE Code");
    }

    public TextEditorView(String title) {
        super(title);
        initComponents();
    }

    private void initComponents() {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        initTabs();
        setSize(800, 400);
        setVisible(true);
    }

    private void initTabs() {
        var menuBar = new JMenuBar();

        // Create File Menu
        var fileMenu = new JMenu("Datei");
        menuBar.add(fileMenu);

        // Create Items for File Menu
        // create Open File
        var openFile = createMenuItem("Datei öffnen...", e -> openFile());
        fileMenu.add(openFile);

        // create New File
        var newFile = createMenuItem("Neue Datei...", e -> createFile());
        fileMenu.add(newFile);

        fileMenu.addSeparator();
        var saveFile = createMenuItem("Datei speichern", e -> saveFile());
        fileMenu.add(saveFile);

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

    private void openFile() {
        System.out.println("Open File");
    }

    private void createFile() {
        System.out.println("Open File");
    }

    private void saveFile() {
        System.out.println("Save File");
    }
}
