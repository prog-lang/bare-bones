package com.company;

import java.util.*;
import java.util.regex.*;

import static com.company.Operator.*;

/*
 * Abstract Syntax Tree (AST) is defined as an array of 'Code'. Where 'Code' is an interface type that is implemented
 * by the 'Statement' and 'While' classes.
 *
 * 'Statement' is a simple class that consists of an 'operator' and a 'varname' -- this is enough to evaluate it
 * properly. 'While' class, on the other hand, consists of 'varname' and another 'Code' array, as it may contain
 * 'Statements' and other 'While' loops as well. It's all recursive.
 *
 * This class serves as a wrapper for the 'analyze' function that produces the general Abstract Syntax Tree
 * for the whole program.
 */
public class AST {

    public static ArrayList<Code> analyze(ArrayList<String> lines, int depth) throws Exception {
        //System.out.println("analyze called");
        String statementExpression = "\\s*(clear|incr|decr) \\w+;\\s*";
        String whileHeaderExpression = "\\s*while \\w+ not 0 do;\\s*";
        Pattern statement = Pattern.compile(statementExpression);
        Pattern whileLoopHeader = Pattern.compile(whileHeaderExpression);

        /* How to match REGEX *
         * Matcher m = whileLoop.matcher("while X not 0 do; decr X; end;");
         * boolean b = m.matches();
         */
        ArrayList<Code> code = new ArrayList<Code>();
        int i = 0;
        while (i < lines.size()) {
            String line = lines.get(i);
            //System.out.printf("i is %d; looking at line %s\n", i, line);
            Matcher statementMatcher = statement.matcher(line);
            Matcher whileLoopHeaderMatcher = whileLoopHeader.matcher(line);
            if (statementMatcher.matches() && indentedCorrectly(line, depth)) {
                code.add( statementGen(line) );
                i++;
            }
            else if ( whileLoopHeaderMatcher.matches() && indentedCorrectly(line, depth) ) {
                While whileLoop = whileLoopGen(lines, depth, i);
                code.add(whileLoop);
                i += whileLoop.size();
            }
            else throw new Exception(
                    "->" + line + "<-\n" +
                    "Line " + ((Integer)i).toString() + " contains unknown expression. Or is improperly indented.\n" +
                    "Use exactly four spaces as one level of indentation."
                );
        }

        return code;
    }

    public static boolean indentedCorrectly(String line, int depth) {
        String correct = Util.strMul("    ", depth) + line.trim();
        return line.equals(correct);
    }

    public static While whileLoopGen(ArrayList<String> lines, int depth, int whileLoopBegins) throws Exception {
        //System.out.printf("whileLoopGen called with following lines:\n");
        //prettyPrintArrayList(lines);
        // whileLoopBegins is an index of line where whileLoopHeader was found
        String whileExpression = "\\s*while \\w+ not 0 do;\\s*\n" +
                "\\s{4,}([\\w\\s]+;\\s*)+\n" +
                "\\s*end;\\s*\n";
        Pattern whileLoop = Pattern.compile(whileExpression);

        int i = whileLoopBegins;
        StringBuilder whileLoopBuilder = new StringBuilder();
        Matcher whileLoopMatcher;
        do {
            String line = lines.get(i);
            whileLoopBuilder.append(line + "\n");
            whileLoopMatcher = whileLoop.matcher( whileLoopBuilder.toString() );
            i++;
            if ( whileLoopMatcher.matches() && indentedCorrectly(line, depth) )
                break;
        } while ( i < lines.size() /*&& !whileLoopMatcher.matches()*/ );

        if ( !whileLoopMatcher.matches() ) {
            throw new Exception(
                    "Cannot resolve while loop that begins on line " + ((Integer) whileLoopBegins).toString()
            );
        }

        String header = lines.get(whileLoopBegins).trim();
        int indexNot = header.indexOf("not 0 do;");
        String varname = header.substring(6, indexNot - 1);

        return new While(
                varname,
                analyze(
                        new ArrayList<String>( lines.subList(whileLoopBegins + 1, i - 1) ),
                        depth + 1
                )
        );
    }

    public static Statement statementGen(String statementLine) throws Exception {
        //System.out.println("statementGen called");
        statementLine = statementLine.trim();
        int spaceIndex = statementLine.indexOf(' ');
        String operatorString = statementLine.substring(0, spaceIndex);
        String varname = statementLine.substring( spaceIndex + 1, statementLine.length() - 1 );
        Operator operator;
        switch (operatorString) {
            case "clear":
                operator = CLEAR;
                break;
            case "incr":
                operator = INCR;
                break;
            case "decr":
                operator = DECR;
                break;
            default:
                throw new Exception("Unknown operator found while generating a 'Statement'.");
        }
        return new Statement(operator, varname);
    }

    public static void prettyPrintArrayList(ArrayList<String> arr) {
        for (String s: arr)
            System.out.println(s);
    }

}
