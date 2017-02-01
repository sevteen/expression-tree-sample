package com.example;

/**
 * Represents AST (Abstract Syntax Tree)
 *
 * @author beka
 */
public interface Node {

    /**
     * Accepts visitor to traverse whole tree
     * and probably return some value
     */
    Node accept(Visitor visitor);
}
