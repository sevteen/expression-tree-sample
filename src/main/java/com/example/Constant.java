package com.example;

/**
 * Represents constant expression
 *
 * @author beka
 */
public abstract class Constant<T> implements Node {

	private T value;

	public Constant(T value) {
		this.value = value;
	}

	public T getValue() {
		return value;
	}

	@Override
	public Node accept(Visitor visitor) {
		return visitor.visit(this);
	}

}
