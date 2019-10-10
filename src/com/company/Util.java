package com.company;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.regex.*;

public class Util {

    public static ArrayList<String> readFileAsLines(File file) throws Exception {
        String ignoredLineExpression = "(\\s*#.*)|(\\s*)";
        Pattern ignoredLine = Pattern.compile(ignoredLineExpression);
        ArrayList<String> lines = new ArrayList<String>();
        Scanner fileReader = new Scanner(file);
        while ( fileReader.hasNextLine() ) {
            String line = fileReader.nextLine();
            Matcher m = ignoredLine.matcher(line);
            if ( !m.matches() )
                lines.add(line);
        }
        fileReader.close();
        return lines;
    }

    public static String strMul(String string, int coefficient) {
        StringBuilder out = new StringBuilder();
        for (int i = 0; i < coefficient; i++)
            out.append(string);
        return out.toString();
    }

}
