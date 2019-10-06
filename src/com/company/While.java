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

}
