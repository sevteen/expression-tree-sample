package com.example;

/**
 * @author beka
 */
public class InfixIntegerArithmeticExpressionEvaluator {

    private InfixIntegerArithmeticExpressionParser parser = new InfixIntegerArithmeticExpressionParser();
    private IntegerArithmeticExpressionVisitor visitor = new IntegerArithmeticExpressionVisitor();

    public int evaluate(String expression) {
        return ((IntegerNode) parser.parse(expression).accept(visitor)).getValue();
    }
}
