package com.company;

import java.io.File;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args) throws Exception {
        File inputFile = new File("/home/sharpvik/Projects/java/BareBones/inputs/hard.bb");
        ArrayList<String> lines = Util.readFileAsLines(inputFile);

        ArrayList<Code> ast = AST.analyze(lines, 0);  // Syntactic Analyzer to produce Abstract Syntax Tree (AST)

        for (Code c: ast)
            c.print(0);

        VM vm = new VM();                                    // Virtual Machine to use AST and produce relevant output
        vm.exec(ast);
        System.out.println(vm.vars);
    }

}
