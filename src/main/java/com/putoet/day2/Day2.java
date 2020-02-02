package com.putoet.day2;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Day2 {
    public static void main(String[] args) throws URISyntaxException, IOException {
        final URL url = Day2.class.getResource("/day2.txt");
        final Path path = Paths.get(url.toURI());

        System.out.println("Total wrapping paper required is " + Files.lines(path).map(Box::of).mapToInt(Box::totalWrap).sum());
        System.out.println("Total feet of ribbon required is " + Files.lines(path).map(Box::of).mapToInt(Box::totalRibbon).sum());
    }
}
