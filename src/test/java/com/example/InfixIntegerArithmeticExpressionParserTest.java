package com.example;

import com.tngtech.java.junit.dataprovider.DataProvider;
import com.tngtech.java.junit.dataprovider.DataProviderRunner;
import com.tngtech.java.junit.dataprovider.UseDataProvider;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author beka
 */
@RunWith(DataProviderRunner.class)
public class InfixIntegerArithmeticExpressionParserTest {

	private InfixIntegerArithmeticExpressionParser parser;

	@Before
	public void setUp() throws Exception {
		parser = new InfixIntegerArithmeticExpressionParser();
	}

	@Test
	public void canParseConstantInteger() throws Exception {
		Node node = parser.parse("6");

		assertThat(node).isInstanceOf(IntegerNode.class);
		assertThat(((IntegerNode) node).getValue().intValue()).isEqualTo(6);
	}

	@DataProvider
	public static Object[][] binaryAdditions() {
		return new Object[][]{
				{"6+5"},
				{"6 + 5"},
				{" 6+5"},
				{"6+ 5 "},
				{"6     + 5   "}
		};
	}

	@DataProvider
	public static Object[][] ternaryAdditions() {
		return new Object[][]{
				{"6+5+4"},
				{"6 + 5+4"},
				{" 6+5 + 4"},
				{"6+ 5 +   4"},
				{"6     + 5   +4"}
		};
	}

	@Test
	@UseDataProvider("binaryAdditions")
	public void canParseAdditionOfTwoIntegers(String addition) throws Exception {
		Node node = parser.parse(addition);

		assertThat(node).isInstanceOf(BinaryOperation.class);
		BinaryOperation binaryOperation = (BinaryOperation) node;
		assertThat(binaryOperation.getOperator().is(Operator.ADDITION)).isTrue();

		assertThat(binaryOperation.getLeft()).isInstanceOf(IntegerNode.class);
		assertThat(((IntegerNode) binaryOperation.getLeft()).getValue().intValue()).isEqualTo(6);

		assertThat(binaryOperation.getRight()).isInstanceOf(IntegerNode.class);
		assertThat(((IntegerNode) binaryOperation.getRight()).getValue().intValue()).isEqualTo(5);
	}

	@Test
	@UseDataProvider("ternaryAdditions")
	public void canParseAdditionOfThreeIntegers(String addition) throws Exception {
		Node node = parser.parse(addition);

		assertThat(node).isInstanceOf(BinaryOperation.class);
		BinaryOperation binaryOperation = (BinaryOperation) node;
		assertThat(binaryOperation.getOperator().is(Operator.ADDITION)).isTrue();

		assertThat(binaryOperation.getLeft()).isInstanceOf(IntegerNode.class);
		assertThat(((IntegerNode) binaryOperation.getLeft()).getValue().intValue()).isEqualTo(6);

		assertThat(binaryOperation.getRight()).isInstanceOf(BinaryOperation.class);
		BinaryOperation rightBinaryOperation = (BinaryOperation) binaryOperation.getRight();
		assertThat(rightBinaryOperation.getOperator().is(Operator.ADDITION)).isTrue();

		assertThat(rightBinaryOperation.getLeft()).isInstanceOf(IntegerNode.class);
		assertThat(((IntegerNode) rightBinaryOperation.getLeft()).getValue().intValue()).isEqualTo(5);

		assertThat(rightBinaryOperation.getRight()).isInstanceOf(IntegerNode.class);
		assertThat(((IntegerNode) rightBinaryOperation.getRight()).getValue().intValue()).isEqualTo(4);
	}

}
