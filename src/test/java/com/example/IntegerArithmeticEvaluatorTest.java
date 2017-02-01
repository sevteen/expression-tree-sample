package com.example;


import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

/**
 * @author beka
 */
public class IntegerArithmeticEvaluatorTest {

    private IntegerArithmeticEvaluator evaluator;

    @Before
    public void setUp() throws Exception {
        evaluator = new IntegerArithmeticEvaluator();
    }

    @Test
    public void canEvaluateIntegerNode() throws Exception {
        Node node = new IntegerNode(32).accept(evaluator);

        assertNodeHasValue(node, 32);
    }

    @Test
    public void canAddIntegers() throws Exception {
        Node node = new BinaryOperation(Operator.ADDITION, new IntegerNode(32), new IntegerNode(2))
            .accept(evaluator);

        assertNodeHasValue(node, 34);
    }

    @Test
    public void canSubtractIntegers() throws Exception {
        Node node = new BinaryOperation(Operator.SUBTRACTION, new IntegerNode(32), new IntegerNode(2))
            .accept(evaluator);

        assertNodeHasValue(node, 30);
    }

    @Test
    public void canMultiplyIntegers() throws Exception {
        Node node = new BinaryOperation(Operator.MULTIPLICATION, new IntegerNode(32), new IntegerNode(2))
            .accept(evaluator);

        assertNodeHasValue(node, 64);
    }

    @Test
    public void canDivideIntegers() throws Exception {
        Node node = new BinaryOperation(Operator.DIVISION, new IntegerNode(32), new IntegerNode(2))
            .accept(evaluator);

        assertNodeHasValue(node, 16);
    }

    @Test
    public void canEvaluateComplexExpressionAsInteger() throws Exception {
        Node node = new BinaryOperation(Operator.ADDITION, // 4 + (3 + 15)
            new BinaryOperation(Operator.MULTIPLICATION, new IntegerNode(2), new IntegerNode(2)), // 4
            new BinaryOperation(Operator.ADDITION, // 3 + 15
                new BinaryOperation(Operator.MULTIPLICATION, new IntegerNode(3), new IntegerNode(5)), // 15
                new IntegerNode(3))) // 3
            .accept(evaluator);

        assertNodeHasValue(node, 22);
    }

    @Test
    public void canEvaluateComplexExpression2AsInteger() throws Exception {
        Node node = new BinaryOperation(Operator.ADDITION, // 25
            new BinaryOperation(Operator.MULTIPLICATION, new IntegerNode(2), new IntegerNode(2)), // 4
            new BinaryOperation(Operator.ADDITION, // 21
                new BinaryOperation(Operator.MULTIPLICATION, new IntegerNode(3), new IntegerNode(5)), // 15
                new BinaryOperation(Operator.SUBTRACTION,  // 6
                    new BinaryOperation(Operator.MULTIPLICATION, new IntegerNode(5), new IntegerNode(2)), // 10
                    new BinaryOperation(Operator.DIVISION, new IntegerNode(20), new IntegerNode(5))))) // 4
            .accept(evaluator);

        assertNodeHasValue(node, 25);
    }

    @Test
    public void shouldThrowUnsupportedOperationExceptionWhenUnknownOperatorIsUsed() throws Exception {
        assertThatThrownBy(() -> new BinaryOperation(new Operator("xor"), new IntegerNode(3), new IntegerNode(52))
            .accept(evaluator))
            .isInstanceOf(UnsupportedOperationException.class)
            .hasMessageContaining("Unknown operator");
    }

    private void assertNodeHasValue(Node node, int expectedValue) {
        assertThat(node).isInstanceOf(IntegerNode.class);
        assertThat(((IntegerNode) node).getValue().intValue()).isEqualTo(expectedValue);
    }
}
