package com.example;

import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author beka
 */
public class InfixIntegerArithmeticExpressionEvaluatorTest {

    private InfixIntegerArithmeticExpressionEvaluator evaluator;

    @Before
    public void setUp() throws Exception {
        evaluator = new InfixIntegerArithmeticExpressionEvaluator();
    }

    @Test
    public void canEvaluateAddition() throws Exception {
        assertThat(evaluator.evaluate("5 + 6"))
            .isEqualTo(11);
    }
}
