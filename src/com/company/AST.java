package com.company;

import java.util.*;
import java.util.regex.*;

import static com.company.Operator.*;

/*
 * Abstract Syntax Tree (AST) is defined as an array of Code. Where Code is an interface type that is implemented
 * by the Statement and While classes.
 *
 * Statement is a simple class that consists of an 'operator' and a 'varname' -- this is enough to evaluate it
 * properly. While class, on the other hand, consists of another Code array, as it may contain Statements and other
 * While loops as well. It's all recursive.
 *
 * This class serves as a wrapper for the 'analyze' function that produces the general Abstract Syntax Tree
 * for the whole program and puts it into the public 'code' variable.
 */
public class AST {

    public Code[] code;

    public void analyze(String[] lines, int depth) throws Exception {
        Pattern statement = Pattern.compile("(clear|incr|decr) \\w+;");
        String whilePatternString = Util.strMul("\t", depth) + "while \\w+ not 0 do;\\s*\\n" +
                                    Util.strMul("\t", depth + 1) + ".+\n" +
                                    Util.strMul("\t", depth) + "end;";
        Pattern whileLoop = Pattern.compile("while \\w+ not 0 do;\\s*\\n     end;");
        /*
        Matcher m = whileLoop.matcher("while X not 0 do; decr X; end;");
        boolean b = m.matches();
         */

        // must fill in this.code variable
    }

    public Statement statementGen(String statementLine) throws Exception {
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
                throw new Exception("Unknown operator found.");
        }
        return new Statement(operator, varname);
    }

}
