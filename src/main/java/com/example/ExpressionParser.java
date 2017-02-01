package com.example;

/**
 * @author beka
 */
public interface ExpressionParser {

    /**
     * Parses raw expression represented as string into AST
     *
     * @throws IllegalArgumentException if expression is invalid
     */
    Node parse(String expression);
}
