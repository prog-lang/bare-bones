package com.company;

import java.io.File;
import java.util.Scanner;

public class Util {

    public static String[] readFileAsLines(File file, int max_lines) throws Exception {
        String[] lines = new String[max_lines];
        Scanner fileReader = new Scanner(file);
        int i = 0;
        while ( fileReader.hasNextLine() )
            lines[i++] = fileReader.nextLine();
        fileReader.close();
        return lines;
    }

    public static String strMul(String string, int coefficient) {
        String out = "";
        for (int i = 0; i < coefficient; i++)
            out += string;
        return out;
    }

}
