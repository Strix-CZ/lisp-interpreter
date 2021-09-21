package lisp;

import java.util.Collections;
import java.util.function.Function;

public class Parser
{
	private final String code;
	private int position = 0;

	public Parser(String code)
	{
		this.code = code;
	}

	public static Expression parse(String code)
	{
		return new Parser(code).parse();
	}

	public Expression parse()
	{
		String nextToken = readNextToken();

		if (nextToken.equals("("))
		{
			String optionalAtom = readNextToken();
			if (optionalAtom.equals(")"))
			{
				return Expression.list(Collections.emptyList());
			}

			String endParentheses = readNextToken();
			if (!endParentheses.equals(")"))
			{
				throw new SyntaxError("Missing right parentheses\n" + code);
			}

			return Expression.list(Collections.singletonList(Expression.atom(optionalAtom)));
		}
		else
		{
			return Expression.atom(nextToken);
		}
	}

	public String readNextToken()
	{
		position = skip(position, Character::isWhitespace);
		if (position >= code.length())
			return "";

		char currentChar = code.charAt(position);

		if (currentChar == '(' || currentChar == ')')
		{
			position++;
			return Character.toString(currentChar);
		}
		else
		{
			int endPosition = find(position, (c) -> Character.isWhitespace(c) || c == '(' || c == ')');
			String token = code.substring(position, endPosition);
			position = endPosition;
			return token;
		}
	}

	public int skip(int startingPosition, Function<Character, Boolean> predicate)
	{
		return find(startingPosition, (c) -> !predicate.apply(c));
	}

	public int find(int startingPosition, Function<Character, Boolean> predicate)
	{
		int nextPosition = startingPosition;

		while (nextPosition < code.length() && !predicate.apply(code.charAt(nextPosition)))
		{
			nextPosition++;
		}

		return nextPosition;
	}
}
