package com.putoet.day25;

public class Day24 {
    public static final String INPUT = "To continue, please consult the code grid in the manual.  Enter the code at row 2947, column 3029.";

    public static void main(String[] args) {
        final CodeId codeId = new CodeId();
        System.out.println("Value is " + codeId.valueAtCell(2947, 3029));
    }
}
