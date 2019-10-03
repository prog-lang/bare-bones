package com.company;

import java.util.regex.*;

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

    public void analyze(String[] lines, int depth) {
        Pattern statement = Pattern.compile("(clear|incr|decr) \\w+;");
        Pattern whileLoop = Pattern.compile("(clear|incr|decr) \\w+;");
        // must fill in this.code variable
    }

}
