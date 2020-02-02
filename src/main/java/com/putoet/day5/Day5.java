package com.putoet.day5;

import com.putoet.day2.Day2;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Day5 {
    public static void main(String[] args) throws URISyntaxException, IOException {
        final URL url = Day2.class.getResource("/day5.txt");
        final Path path = Paths.get(url.toURI());

        System.out.println("Number of nice strings: " + Files.lines(path).filter(Nice::isNice).count());
        System.out.println("Number of nicer strings: " + Files.lines(path).filter(Nice::isNicer).count());
    }
}
