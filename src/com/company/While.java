package com.company;

import java.util.ArrayList;

public class While implements Code {

    public String varname;          // variable that triggers the termination of the cycle
    public ArrayList<Code> code;    // contains code that comprises the body of the while loop

    While(String varname, ArrayList<Code> code) {
        this.varname = varname;
        this.code = new ArrayList<Code>(code);
    }

    public String type() { return "while"; }

    public int size() {
        int sz = 2;
        for (Code c: this.code)
            sz += c.size();
        return sz;
    }

    public void print(int depth) {
        System.out.println( Util.strMul("    ", depth) + "while " + varname + " not 0 do;");
        for (Code c: this.code)
            c.print(depth + 1);
        System.out.println( Util.strMul("    ", depth) + "end;");
    }

}
