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
        String statementExpression = Util.strMul("\t", depth) + "(clear|incr|decr) \\w+;";
        String whileExpression = Util.strMul("\t", depth) + "while \\w+ not 0 do;\\s*\\n" +
                Util.strMul("\t", depth + 1) + ".+\n" +
                Util.strMul("\t", depth) + "end;";
        String whileHeaderExpression = Util.strMul("\t", depth) + "while \\w+ not 0 do;\\s*\\n";
        Pattern statement = Pattern.compile(statementExpression);
        Pattern whileLoopHeader = Pattern.compile(whileHeaderExpression);
        Pattern whileLoop = Pattern.compile(whileExpression);

        ArrayList<Code> code = new ArrayList<Code>();
        /* How to match REGEX *
         * Matcher m = whileLoop.matcher("while X not 0 do; decr X; end;");
         * boolean b = m.matches();
         */
        for (String line: lines) {
            Matcher m = statement.matcher(line);
            if ( m.matches() )
                code.add( statementGen(line) );
        }

        return code;
    }

    public static Statement statementGen(String statementLine) throws Exception {
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
                throw new Exception("AST.statementGen: Unknown operator found while generating a 'Statement'.");
        }
        return new Statement(operator, varname);
    }

}
