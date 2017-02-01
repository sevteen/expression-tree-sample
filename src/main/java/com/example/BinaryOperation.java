package com.example;

/**
 * Represents binary operation consisting {@link Operator operator}
 * and left and right operands
 *
 * @author beka
 */
public class BinaryOperation implements Node {

	private Operator operator;
	private Node left;
	private Node right;

	public BinaryOperation(Operator operator, Node left, Node right) {
		this.operator = operator;
		this.left = left;
		this.right = right;
	}

	public Operator getOperator() {
		return operator;
	}

	public Node getLeft() {
		return left;
	}

	public Node getRight() {
		return right;
	}

	public Node accept(Visitor visitor) {
		return visitor.visit(this);
	}
}
