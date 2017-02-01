package com.example;

/**
 * Parses infix integer expressions, such as:
 * <ul>
 * <li>5</li>
 * <li>5 + 3</li>
 * <li>5 * 6</li>
 * <li>5 * 6 - 5</li>
 * <li>5 * (6 - 5)</li>
 * <li>5 * (6 / 2 - 5)</li>
 * </ul>
 * into {@link Node AST}
 *
 * @author beka
 */
public class InfixIntegerArithmeticExpressionParser implements ExpressionParser {

    @Override
    public Node parse(String expression) {
        String[] operands = expression.split("\\+");
        if (operands.length > 1) {

            BinaryOperation op = new BinaryOperation(Operator.ADDITION,
                intNode(operands[operands.length - 2]),
                intNode(operands[operands.length - 1]));

            for (int i = operands.length - 3; i >= 0; i--) {
                op = new BinaryOperation(Operator.ADDITION, intNode(operands[i]), op);
            }

            return op;
        }
        return new IntegerNode(Integer.valueOf(expression));
    }

    private IntegerNode intNode(String op) {
        return new IntegerNode(Integer.valueOf(op.trim()));
    }
}
