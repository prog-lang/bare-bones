package com.company;

import java.util.*;

/*
 * Virtual Machine (VM) is a software device that accepts instructions and tells computer what to do to produce the
 * desired output. It allows us to run programs without producing binary executable files.
 */
public class VM {

    private Map vars = new HashMap();

    public void exec(Code[] code) {
        // awaits implementation
    }

    public void eval(Code code) {
        // awaits implementation
    }

    /*
     * The operators below initialize new variables to 0.
     * Also, decr doesn't allow variables to become negative. No exceptions thrown.
     * Integer overflow can be abused to 'produce' negative variables using incr too many times. This flaw can be cured
     * later if necessary.
     */
    public void clear(String varname) {
        vars.put(varname, 0);
    }

    public void incr(String varname) {
        vars.put(
                varname,
                ( !vars.containsKey(varname) ) ? 1 : (int)vars.get(varname) + 1
        );
    }

    public void decr(String varname) {
        vars.put(
                varname,
                ( !vars.containsKey(varname) || (int)vars.get(varname) == 0 ) ? 0 : (int)vars.get(varname) - 1
        );
    }

}
