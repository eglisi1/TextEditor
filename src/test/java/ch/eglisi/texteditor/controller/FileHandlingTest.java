package ch.eglisi.texteditor.controller;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;


class FileHandlingTest {

    static Path file;
    static List<String> originalContent;
    static List<String> addContent;
    static List<String> expectedResult;

    @BeforeAll
    static void init() throws IOException {
        file = Path.of("src/test/resources/saveFile/save.txt");
        originalContent = Files.readAllLines(file);
        addContent = Collections.singletonList("Content");
    }

    @Test
    void saveFile_simple() throws IOException {
        expectedResult = new ArrayList<>(originalContent);
        expectedResult.add("Content");
        List<String> content = Stream.concat(originalContent.stream(), addContent.stream()).collect(Collectors.toList());
        FileHandling.saveFile(file, content);
        Assertions.assertLinesMatch(Files.readAllLines(file), expectedResult);
    }

    @Test
    void saveFile_null_parameters() throws IOException {
        expectedResult = new ArrayList<>(originalContent);
        FileHandling.saveFile(null, null);
        Assertions.assertLinesMatch(Files.readAllLines(file), expectedResult);
    }
}