package com.company;

import java.util.*;

/*
 * Virtual Machine (VM) is a software device that accepts instructions and tells computer what to do to produce the
 * desired output. It allows us to run programs without producing binary executable files.
 */
public class VM {

    public Map vars = new HashMap();

    public void exec(ArrayList<Code> code) throws Exception {
        for (Code instruction: code) {
            String instructionType = instruction.type();
            if ( instructionType.equals("statement") || instructionType.equals("while") )
                this.eval(instruction);
            else throw new Exception("Unknown code type.");
        }
    }

    public void eval(Code instruction) throws Exception {
        if ( instruction.type().equals("statement") ) {
            Statement statement = (Statement)instruction;
            switch (statement.operator) {
                case CLEAR:
                    this.clear(statement.varname);
                    break;
                case INCR:
                    this.incr(statement.varname);
                    break;
                case DECR:
                    this.decr(statement.varname);
                    break;
                default:
                    throw new Exception("Unknown operator in Statement.");
            }
        }
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
