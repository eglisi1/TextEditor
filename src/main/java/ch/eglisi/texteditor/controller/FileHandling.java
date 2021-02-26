package ch.eglisi.texteditor.controller;

import javax.swing.*;
import javax.swing.filechooser.FileSystemView;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public final class FileHandling {
    private static final Logger LOGGER = Logger.getLogger("Logger");

    private FileHandling() {}

    public static void createFile() {
        System.out.println("Open File");
    }


    public static void saveFile(Path file, List<String> content) {
        try {
            Files.write(file, content);
        } catch (IOException | NullPointerException e) {
            LOGGER.log(Level.SEVERE, "Error occured while saving the file!");
            LOGGER.log(Level.SEVERE, e.getMessage());
        }
    }

    public static void openFile() {
        System.out.println("Open File");
        var fileChooser = getFileChooser();
        File chosenFile = chooseFile(fileChooser);
        if (chosenFile != null) {
            // TODO: Do something with the file
        } else {
            LOGGER.log(Level.INFO, "Won't open File, as File is null");
        }
    }

    private static JFileChooser getFileChooser() {
        var fileChooser = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
        fileChooser.setDialogTitle("Datei auswählen");
        fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        return fileChooser;
    }

    /**
     * Chooses a File and returns the File if selected.
     * If an Error occurs or the Action is cancelled return null
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
                LOGGER.log(Level.SEVERE, "Error occured while chosing a file");
                // TODO: Message to user?
            }
            return null;
        }
    }
}
