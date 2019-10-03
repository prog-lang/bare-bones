package com.company;

public class While implements Code {

    public String varname;  // variable that triggers the end of while loop
    public Code[] code;     // see AST.java to learn more

    While(String varname, Code[] code, int codeLength) {
        this.varname = varname;
        this.code = new Code[codeLength];
        int i = 0;
        while (i < codeLength && code[i] != null)
            this.code[i++] = code[i];
    }

    public String type() { return "while"; }

}
