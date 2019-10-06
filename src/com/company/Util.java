package com.company;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

public class Util {

    public static ArrayList<String> readFileAsLines(File file) throws Exception {
        ArrayList<String> lines = new ArrayList<String>();
        Scanner fileReader = new Scanner(file);
        while ( fileReader.hasNextLine() )
            lines.add( fileReader.nextLine() );
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
