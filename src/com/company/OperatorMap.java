package com.company;

import java.util.HashMap;
import java.util.Map;

import static com.company.Operator.*;

public class OperatorMap {

    public static Map<String, Operator> operatorMap = new HashMap<String, Operator>();

    OperatorMap() {
        operatorMap.put("clear", CLEAR);
        operatorMap.put("incr", INCR);
        operatorMap.put("decr", DECR);
        operatorMap.put("input", INPUT);
        operatorMap.put("print", PRINT);
        operatorMap.put("inputa", INPUTA);
        operatorMap.put("printa", PRINTA);
    }

}
