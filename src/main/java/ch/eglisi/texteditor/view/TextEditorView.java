package ch.eglisi.texteditor.view;

import ch.eglisi.texteditor.controller.FileHandling;
import ch.eglisi.texteditor.util.Util;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TextEditorView extends JFrame {

    private String text = "";
    private Path filePath;

    private final JTextArea textArea = new JTextArea(text);
    private final JScrollPane scrollPane = new JScrollPane(textArea);

    private static final Logger LOGGER = Util.getLogger(TextEditorView.class);

    public TextEditorView() {
        this("SE Code");
    }

    public TextEditorView(String title) {
        super(title);
        initComponents();
        styleUI();
    }

    /**
     * Initialize the Components.
     */
    private void initComponents() {
        initTabs();
        initTextArea();
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
        // create New File
        var newFile = createMenuItem("Neue Datei...", e -> createFile());
        fileMenu.add(newFile);
        
        // create Open File
        var openFile = createMenuItem("Datei öffnen...", e -> openFile());
        fileMenu.add(openFile);

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

    private void initTextArea() {
        add(scrollPane);
        setTextArea(false);
    }

    private void setTextArea(boolean visible) {
        remove(scrollPane);
        textArea.setText(getText());
        textArea.setVisible(visible);
        scrollPane.setVisible(visible);
        add(scrollPane);
    }

    /**
     * Get File Path and Text of a File
     */
    private void openFile() {
        var file = FileHandling.openFile();
        if (file != null) {
            var path = Path.of(file.toURI());
            setFilePath(path);
            try {
                setText(Files.readAllLines(path));
                setTextArea(true);
            } catch (IOException e) {
                LOGGER.log(Level.SEVERE, "Unable to read Content of File: " + path.toAbsolutePath());
            }
        }
    }

    /**
     * Saves the current Text from the TextArea to the File Path
     */
    private void saveFile() {
        setText(textArea.getText());
        FileHandling.saveFile(getFilePath(), getText());
    }

    private void createFile() {
        setText("");
        setFilePath(null);
        setTextArea(true);
    }

    /**
     * Setter with restriction null values is replaced with Empty String
     * @param text that shall be set
     */
    public void setText(String text) {
        this.text = Objects.requireNonNullElse(text, "");
    }

    public String getText() {
        return text;
    }

    /**
     * Setter with restriction null values is replaced with Empty String
     * @param text that is set
     */
    public void setText(List<String> text) {
        if (text != null) {
            StringBuilder content = new StringBuilder();
            for (int i = 0; i < text.size(); i++) {
                content.append(text.get(i));
                // Don't append new line if it's the last line of text
                if (text.size() - 1 != i) {
                    content.append("\n");
                }
            }
            this.text = content.toString();
        } else {
            this.text = "";
        }
    }

    public Path getFilePath() {
        return filePath;
    }

    public void setFilePath(Path filePath) {
        this.filePath = filePath;
    }
}
