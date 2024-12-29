package de.johannes.commons.util;

import de.johannes.exceptions.FileException;

import java.io.*;

@SuppressWarnings("ALL")
public class Files {

    public static String readStream(InputStream stream) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(stream));
        StringBuilder str = new StringBuilder();
        String line = "";
        while ((line = reader.readLine()) != null) {
            str.append(line);
            str.append("\n");
        }
        reader.close();
        return str.toString();
    }

    public static String readResource(String location) throws IOException {
        InputStream stream = Files.class.getResourceAsStream(location.startsWith("/") ? location : "/"+location);
        return readStream(stream);
    }

    public static String readFile(File location) {
        if (!location.exists()) {
            throw new FileException(true, location);
        }
        try {
            return readStream(new FileInputStream(location));
        } catch (Exception ex) {
            throw new FileException(true, location);
        }
    }

    public static String read(String location) {
        return readFile(new File(location));
    }

    public static void writeFile(String content, File file) {
        if (!file.exists()) {
            file.getParentFile().mkdirs();
            try {
                file.createNewFile();
            } catch (Exception ex) {
                throw new FileException(false, file);
            }
        }
        try {
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file)));
            writer.write(content);
            writer.flush();
            writer.close();
        } catch (Exception ignored) {
            throw new FileException(false, file);
        }
    }

    public static void write(String content, String location) {
        writeFile(content, new File(location));
    }

}
