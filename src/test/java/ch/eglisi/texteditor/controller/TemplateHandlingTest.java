package ch.eglisi.texteditor.controller;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.nio.file.Files;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.*;

class TemplateHandlingTest {

    @Test
    void correctFilepath() {
        var templatePath = TemplateHandling.getTemplateFolder();
        Assertions.assertTrue(Files.exists(templatePath));
    }
}