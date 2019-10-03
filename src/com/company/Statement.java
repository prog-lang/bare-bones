package com.company;

public class Statement implements Code {

    public Operator operator;
    public String varname;

    Statement(Operator operator, String varname) {
        this.operator = operator;
        this.varname = varname;
    }

    public String type() { return "statement"; }
}
