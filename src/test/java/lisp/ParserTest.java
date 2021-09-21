package lisp;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ParserTest
{
	private Parser parser;

	@BeforeEach
	void setUp()
	{
		parser = new Parser();
	}

	@Test
	void parseAtomTest()
	{
		expectAtom(parser.parse("1"), "1");
	}

	@Test
	void whitespaceIsNotIncludedInAtom()
	{
		expectAtom(parser.parse("\t foo\n"), "foo");
	}

	@Test
	void parseEmptyListTest()
	{
		expectEmptyList(parser.parse("()"));
	}

	@Test
	void whiteSpaceDoesNotMatterForEmptyList()
	{
		expectEmptyList(parser.parse(" (  ) "));
	}

	private void expectAtom(Expression expression, String expectedValue)
	{
		Assertions.assertThat(expression.isAtom())
				.as("atom")
				.isTrue();

		Assertions.assertThat(expression.value())
				.as("value")
				.isEqualTo(expectedValue);
	}

	private void expectEmptyList(Expression expression)
	{
		Assertions.assertThat(expression.isAtom())
				.as("atom")
				.isFalse();

		Assertions.assertThat(expression.isEmpty())
				.as("empty")
				.isEqualTo(true);
	}
}
