package de.johannes.commons.util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.File;

public class Json {

    private static final Gson gson = new GsonBuilder().setPrettyPrinting().create();

    public static <T>T read(String json, Class<T> clazz) {
        return gson.fromJson(json, clazz);
    }

    public static <T>T read(File json, Class<T> clazz) {
        return gson.fromJson(Files.readFile(json), clazz);
    }

    public static String get(Object object) {
        return gson.toJson(object);
    }

}
