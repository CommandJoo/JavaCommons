package de.johannes;

import de.johannes.commons.util.Files;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        System.out.println(Files.readResource("/de/johannes/test"));
    }
}