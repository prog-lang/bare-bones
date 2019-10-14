package com.company;

import java.util.*;

/*
 * Virtual Machine (VM) is a software device that accepts instructions and tells computer what to do to produce the
 * desired output. It allows us to run programs without producing binary executable files.
 */
public class VM {

    public Map<String, Integer> vars = new HashMap<String, Integer>();
    public Scanner stdin = new Scanner(System.in);

    public void exec(ArrayList<Code> code) throws Exception {
        for (Code instruction: code) {
            String instructionType = instruction.type();
            if ( instructionType.equals("statement") || instructionType.equals("while") )
                this.eval(instruction);
            else throw new Exception("Unknown 'Code' type.");
        }
    }

    /*
     * The 'clear' and 'decr' initialize new variables to 0. The 'incr' function initializes them to 1.
     * Also, decr doesn't allow variables to become negative. No exceptions thrown.
     * Integer overflow can be abused to produce variables with negative values using 'incr' too many times. This flaw
     * can be cured later if necessary.
     */
    public void eval(Code instruction) throws Exception {
        if ( instruction.type().equals("statement") ) {
            Statement statement = (Statement)instruction;   // type assertion
            switch (statement.operator) {
                case CLEAR:
                    vars.put(statement.varname, 0);
                    break;

                case INCR:
                    vars.put(
                            statement.varname,
                            ( !vars.containsKey(statement.varname) ) ? 1 : (int)vars.get(statement.varname) + 1
                    );
                    break;

                case DECR:
                    vars.put(
                            statement.varname,
                            ( !vars.containsKey(statement.varname) || (int)vars.get(statement.varname) == 0 ) ?
                                    0 : (int)vars.get(statement.varname) - 1
                    );
                    break;

                case INPUT:
                    System.out.print("int> ");
                    vars.put( statement.varname, stdin.nextInt() );
                    break;

                case PRINT:
                    if ( vars.containsKey(statement.varname) )
                        System.out.println( vars.get(statement.varname) );
                    else throw new Exception("Cannot find varname to print.");
                    break;

                case INPUTA:
                    System.out.print("char> ");
                    vars.put( statement.varname, (Integer)System.in.read() );
                    break;

                case PRINTA:
                    if ( vars.containsKey(statement.varname) )
                        System.out.print( (char)vars.get(statement.varname).byteValue() );
                    else throw new Exception("Cannot find varname to print.");
                    break;

                default:
                    throw new Exception("Unknown 'Statement' operator.");
            }
        } else if ( instruction.type().equals("while") ) {
            While whileLoop = (While)instruction;   // type assertion
            while ( !assertEquals(whileLoop.varname, 0) )
                this.exec(whileLoop.code);
        } else throw new Exception("Instruction of unknown type.");
    }

    public boolean assertEquals(String varname, int value) {
        return (int)vars.get(varname) == value;
    }

}
