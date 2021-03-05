package ch.eglisi.texteditor.util;

import java.util.logging.Logger;

public final class Util {
    private Util() {
    }

    @SuppressWarnings("rawtypes")
    public static Logger getLogger(Class clazz) {
        return Logger.getLogger(clazz.getName());
    }
}
