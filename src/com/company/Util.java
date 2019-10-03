package com.company;

import java.io.File;
import java.util.Scanner;

public class Util {

    String[] readFileAsLines(File file, int max_lines) throws Exception {
        String[] lines = new String[max_lines];
        Scanner fileReader = new Scanner(file);
        int i = 0;
        while ( fileReader.hasNextLine() )
            lines[i++] = fileReader.nextLine();
        fileReader.close();
        return lines;
    }

}
