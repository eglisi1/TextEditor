package ch.eglisi.texteditor.controller;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileSystemView;
import java.io.File;

public final class FileHandling {

    private FileHandling() {}

    public static void createFile() {
        System.out.println("Open File");
    }

    public static void saveFile() {
        System.out.println("Save File");
    }

    public static void openFile() {
        System.out.println("Open File");
        var fileChooser = getFileChooser();
        int returnValue = fileChooser.showOpenDialog(null);

        if (returnValue == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            System.out.println(selectedFile.getAbsolutePath());
        }
    }

    private static JFileChooser getFileChooser() {
        var fileChooser = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
        fileChooser.setDialogTitle("Datei auswählen");
        fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        return fileChooser;
    }
}
