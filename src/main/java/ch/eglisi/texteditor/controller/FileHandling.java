package ch.eglisi.texteditor.controller;

import ch.eglisi.texteditor.util.Util;
import ch.eglisi.texteditor.view.ViewUtil;

import javax.swing.*;
import javax.swing.filechooser.FileSystemView;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public final class FileHandling {
    private static final Logger LOGGER = Util.getLogger(FileHandling.class);

    private FileHandling() {
    }

    /**
     * Saves a File with the given Content
     * @param filePath Path where the File is saved
     * @param content Content of the File
     */
    public static void saveFile(Path filePath, List<String> content) {
        if (filePath == null) {
            filePath = chooseDirectory();
            if (filePath != null) {
                String fileName = defineFilename();
                filePath = Path.of(filePath.toAbsolutePath().toString(), fileName);
            } else {
                ViewUtil.showWarningMessage("Kein valider Dateiname",
                        "Dateiname konnte nicht ermittelt werden!");
                LOGGER.log(Level.INFO, "The User chose no file.");
                return;
            }
        }
        try {
            Files.write(filePath, content);
        } catch (IOException | NullPointerException e) {
            LOGGER.log(Level.SEVERE, "Error occured while saving the file!");
            LOGGER.log(Level.SEVERE, e.getMessage());
        }
    }

    /**
     * Calls the saveFile method after Converting a String to a List<String>.
     * Adds an Elements to a List for ever occurence of "\n".
     * @param filePath Path of file that is saved
     * @param text that is saved to file
     */
    public static void saveFile(Path filePath, String text) {
        saveFile(filePath, Arrays.asList(text.split("\n")));
    }

    /**
     * Define a Filename by prompting the user,
     * @return A Filename or 'undefined.txt' if no valid entry is made.
     */
    private static String defineFilename() {
        var filename = JOptionPane.showInputDialog(
                null,
                "Eingabe des Dateiname: ",
                "Dateinamen eingeben",
                JOptionPane.PLAIN_MESSAGE);
        if (isFilenameValid(filename)) {
            return filename;
        } else {
            defineFilename();
            return "undefined.txt"; // Never called. But the compiler want's an implemented case
        }
    }

    /**
     * Checks if a filenme is valid
     * To be valid the File must contain following
     * - a char or "_" or "-" before a dot
     * - a dot
     * - two to 5 chars after the dot
     * @param filename that needs to be validated
     * @return if the Filename is valid
     */
    public static boolean isFilenameValid(String filename) {
        if (filename == null || filename.isEmpty()) {
            return false;
        } else {
            return filename.matches("[A-Za-z0-9_-]*\\.*[A-Za-z0-9]{2,5}");
        }
    }

    /**
     * Choose a File with a JFileChooser and display the file
     */
    public static File openFile() {
        var fileChooser = getFileChooser();
        var chosenFile = chooseFile(fileChooser);
        if (chosenFile != null) {
            return chosenFile;
        } else {
            LOGGER.log(Level.INFO, "Won't open File, as File is null");
            return null;
        }
    }

    /**
     * Returns a JFileChooser with the restriction to select only Directories.
     * @return a JFileChooser instance.
     */
    private static JFileChooser getDirectoryChooser() {
        return getJFileChooser(JFileChooser.DIRECTORIES_ONLY);
    }

    /**
     * Returns a JFileChooser with the restriction to select only Files.
     * @return a JFileChooser instance.
     */
    private static JFileChooser getFileChooser() {
        return getJFileChooser(JFileChooser.FILES_ONLY);
    }

    /**
     * Creates a new JFileChooser with the given Restriction
     * @param selectionMode Selection Modes from JFileChooser
     * @return a JFileChooser instance.
     */
    private static JFileChooser getJFileChooser(int selectionMode) {
        var fileChooser = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
        fileChooser.setDialogTitle("Datei auswählen");
        fileChooser.setFileSelectionMode(selectionMode);
        return fileChooser;
    }

    /**
     * Chooses a File and returns the File if selected.
     * If an Error occurs or the Action is cancelled return null
     *
     * @param fileChooser the File Chooser to select a file
     * @return the selected file or null
     */
    private static File chooseFile(JFileChooser fileChooser) {
        var returnValue = fileChooser.showOpenDialog(null);
        if (returnValue == JFileChooser.APPROVE_OPTION) {
            LOGGER.info("Chose File: " + fileChooser.getSelectedFile().getAbsolutePath());
            return fileChooser.getSelectedFile();
        } else {
            if (returnValue == JFileChooser.ERROR_OPTION) {
                ViewUtil.showErrorMessage("File Chooser Error", "Beim Auswählen der Datei ist ein Fehler aufgetreten");
                LOGGER.log(Level.SEVERE, "Error occured while chosing a file");
            }
            return null;
        }
    }

    /**
     * Choose a directory
     * @return The chosen directory or null if the action is cancelled
     */
    private static Path chooseDirectory() {
        var directoryChooser = getDirectoryChooser();
        var chosenDirectory = chooseFile(directoryChooser);
        if (chosenDirectory != null) {
            return Path.of(chosenDirectory.toURI());
        } else {
            return null;
        }
    }
}
