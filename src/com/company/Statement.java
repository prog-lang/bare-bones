package com.company;

public class Statement implements Code {

    public Operator operator;
    public String varname;

    Statement(Operator operator, String varname) {
        this.operator = operator;
        this.varname = varname;
    }

    public String type() { return "statement"; }

    public int size() { return 1; }

    public void print(int depth) {
        System.out.print( Util.strMul("    ", depth) );
        System.out.print(operator);
        System.out.println(" " + varname + ";");
    }
}
