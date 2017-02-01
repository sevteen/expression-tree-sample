package com.example;

/**
 * Represents visitor abstraction. Different implementations
 * allow to traverse and evaluate expression trees differently
 *
 * @author beka
 */
public interface Visitor {

	/**
	 * Visits binary operation and possibly returns
	 * evaluation result
	 */
	Node visit(BinaryOperation binaryOperation);

	/**
	 * Visits constant expression and possibly returns
	 * evaluation result
	 */
	<T> Node visit(Constant<T> constant);
}
