package org.example.csc311cardgame;

import org.graalvm.polyglot.Context;
import org.graalvm.polyglot.Value;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;


import net.objecthunter.exp4j.ExpressionBuilder;

public class ExpressionEvaluator {
    //Method to evaluate strings
    public static double evaluate(String expression) {
        return new ExpressionBuilder(expression).build().evaluate();
    }
}
