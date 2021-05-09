package ch.eglisi.texteditor.controller;

import ch.eglisi.texteditor.util.Util;
import ch.eglisi.texteditor.view.TextEditorView;

import java.io.IOException;
import java.nio.file.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public final class TemplateHandling {

    private static final Path TEMPLATE_FOLDER = Path.of("src/main/resources/templates");
    private static final Logger LOGGER = Util.getLogger(TemplateHandling.class);

    private TemplateHandling() {}

    public static String readXmlTemplate() {
        return readTemplate("xml");
    }


    public static String readHtmlTemplate() {
        return readTemplate("html");
    }

    private static String readTemplate(String fileType) {
        try (Stream<Path> walk = Files.walk(getTemplateFolder(), 1)) {
            var filesWithExtension = walk.filter(file -> !Files.isDirectory(file))
                    .map(file -> file.toString().toLowerCase())
                    .filter(file -> file.endsWith(fileType))
                    .collect(Collectors.toList());
            if (filesWithExtension.size() == 1) {
                StringBuilder res = new StringBuilder();
                for (String s : Files.readAllLines(Paths.get(filesWithExtension.get(0)))) {
                  res.append(s).append("\n");
                }
                return res.toString();
            } else if (filesWithExtension.size() > 1) {
                LOGGER.log(Level.SEVERE, "Ambigous fileextension: " + fileType);
                LOGGER.log(Level.SEVERE, "Foundt the following files: " + filesWithExtension);
                return "Dieser Filetype wurde nicht implementiert";
            } else {
                LOGGER.log(Level.SEVERE, "No valid Filetype: " + fileType);
                return "Dieser Filetype wurde nicht implementiert";
            }
        } catch (IOException e) {
            e.printStackTrace();
            return "Ein Fehler ist aufgetreten.";
        }
    }

    public static Path getTemplateFolder() {
        return TEMPLATE_FOLDER;
    }
}
