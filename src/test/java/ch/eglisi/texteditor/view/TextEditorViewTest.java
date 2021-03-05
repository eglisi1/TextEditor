package ch.eglisi.texteditor.view;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

class TextEditorViewTest {

    private List<String> passText = Arrays.asList("a", "b", "c");
    private String expectedResult = "a\nb\nc";
    private TextEditorView textEditorView;

    @BeforeEach
    void init() {
        textEditorView = new TextEditorView();
        textEditorView.setVisible(false);
    }

    @Test
    void setText_simple() {
        textEditorView.setText(passText);
        Assertions.assertEquals(textEditorView.getText(), expectedResult);
    }

    @Test
    void setText_null() {
        textEditorView.setText((String) null);
        Assertions.assertEquals(textEditorView.getText(), "");
    }

    @Test
    void setText_null_list() {
        textEditorView.setText((List<String>) null);
        Assertions.assertEquals(textEditorView.getText(), "");
    }
}