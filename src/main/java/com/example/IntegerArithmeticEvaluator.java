package com.example;

/**
 * Evaluates expression consisting of only integers and returns
 * evaluation result as {@link IntegerNode}. Supports following
 * arithmetic operations (which are executed in Integer):
 * <ul>
 * <li>{@link Operator#ADDITION addition}</li>
 * <li>{@link Operator#SUBTRACTION subtraction}</li>
 * <li>{@link Operator#MULTIPLICATION multiplication}</li>
 * <li>{@link Operator#DIVISION division}</li>
 * </ul>
 *
 * @author beka
 */
public class IntegerArithmeticEvaluator implements Visitor {

	@Override
	public Node visit(BinaryOperation binaryOperation) {
		Node left = binaryOperation.getLeft();
		Node right = binaryOperation.getRight();
		Operator operator = binaryOperation.getOperator();

		Integer leftValue = ((IntegerNode) left.accept(this)).getValue();
		Integer rightValue = ((IntegerNode) right.accept(this)).getValue();
		Integer value;

		if (operator.is(Operator.ADDITION)) {
			value = leftValue + rightValue;
		} else if (operator.is(Operator.MULTIPLICATION)) {
			value = leftValue * rightValue;
		} else if (operator.is(Operator.SUBTRACTION)) {
			value = leftValue - rightValue;
		} else if (operator.is(Operator.DIVISION)) {
			value = leftValue / rightValue;
		} else {
			throw new UnsupportedOperationException("Unknown operator: \"" + operator + "\"");
		}
		return new IntegerNode(value);
	}

	@Override
	public <T> Node visit(Constant<T> constant) {
		return constant;
	}
}
