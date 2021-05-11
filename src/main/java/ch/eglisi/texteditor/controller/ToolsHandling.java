package ch.eglisi.texteditor.controller;

import ch.eglisi.texteditor.view.ViewUtil;

import javax.swing.*;

public final class ToolsHandling {

    private ToolsHandling() {}

    public static void showStatistics(String text) {
        if (text != null && text.length() > 0) {
            JOptionPane.showMessageDialog(null,
                    "Anzahl Wörter:\t" + amountWords(text) +
                            "\nAnzahl Paragraphen:\t" + amountParagraph(text),
                    "Statistik", JOptionPane.INFORMATION_MESSAGE);
        } else {
            ViewUtil.showWarningMessage("Kein Text", "Kein Text verfügbar");
        }
    }

    /**
     * Returns the number of words for the given String.
     * @param text that is counted
     * @return the amount of words
     */
    private static int amountWords(String text) {
        return amount(text, "\\s+"); // Split for whitespaces
    }

    private static int amountParagraph(String text) {
        return amount(text, "\n");
    }

    private static int amount(String text, String regex) {
        String[] words = text.split(regex);
        return words.length;
    }
}
