package lisp;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class ParserTest
{
	@Test
	void parseAtomTest()
	{
		expectAtom(Parser.parse("1"), "1");
	}

	@Test
	void whitespaceIsNotIncludedInAtom()
	{
		expectAtom(Parser.parse("\t foo\n"), "foo");
	}

	@Test
	void parseEmptyListTest()
	{
		expectEmptyList(Parser.parse("()"));
	}

	@Test
	void leftBracketWithoutRightIsSyntaxError()
	{
		Assertions.assertThatThrownBy(() -> Parser.parse("("))
				.isInstanceOf(SyntaxError.class)
				.hasMessageContaining("Missing right parentheses");
	}

	@Test
	void parseListWithSingleAtomTest()
	{
		expectList(Parser.parse("(1)"), Expression.atom("1"));
	}

	@Test
	void whiteSpaceDoesNotMatterForEmptyList()
	{
		expectEmptyList(Parser.parse(" (  ) "));
	}

	@Test
	void parseListWithTwoAtomsTest()
	{
		expectList(Parser.parse("(1 foo)"), Expression.atom("1"), Expression.atom("foo"));
	}

	@Test
	void parsingListRecursively()
	{
		expectList(Parser.parse("(())"), Expression.list(Collections.emptyList()));
	}

	@Test
	void parsingComplicatedListTest()
	{
		expectList(Parser.parse(" (+ () foo (bar()))"),
				Expression.atom("+"),
				Expression.list(Collections.emptyList()),
				Expression.atom("foo"),
				Expression.list(List.of(Expression.atom("bar"), Expression.list(Collections.emptyList()))));
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

	private void expectList(Expression e, Expression... listBody)
	{
		Assertions.assertThat(e.isAtom()).as("atom").isFalse();
		Assertions.assertThat(e.isEmpty()).as("empty").isFalse();
		Assertions.assertThat(e.getList()).containsExactly(listBody);
	}
}
