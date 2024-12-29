package de.johannes.exceptions;

import java.io.File;

public class FileException extends RuntimeException {

    public FileException(boolean reading, File file) {
        super("Could not "+(reading ? "read" : "write")+" "+file.getName()+(reading ? (file.exists() ? "!" : ": File does not exist!") : "!")+"\nLocated at: "+file.getAbsolutePath());
    }
}
