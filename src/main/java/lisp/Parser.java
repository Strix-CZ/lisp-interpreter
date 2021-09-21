package lisp;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class Parser
{
	private static final String specialChars = "()'";

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
			return parseList();
		}
		else if (nextToken.equals("'"))
		{
			return Expression.list(Expression.atom("quote"), parse());
		}
		else
		{
			return Expression.atom(nextToken);
		}
	}

	private Expression parseList()
	{
		List<Expression> listBody = new ArrayList<>();

		while (true)
		{
			String potentialRightParentheses = readNextToken();

			if (potentialRightParentheses.isEmpty())
			{
				throw new SyntaxError("Missing right parentheses\n" + code);
			}
			else if (potentialRightParentheses.equals(")"))
			{
				if (!listBody.isEmpty() && listBody.get(0).equals(Expression.atom("list")))
				{
					return rewriteListToCons(listBody);
				}
				else
				{
					return Expression.list(listBody);
				}
			}
			else
			{
				putBackToken(potentialRightParentheses);
				listBody.add(parse());
			}
		}
	}

	/**
	 * Translates (list e1 ... en)
	 * into (cons e1 ... (cons en '()) ... ).
	 */
	private Expression rewriteListToCons(List<Expression> listBody)
	{
		Expression expression = Expression.list(Expression.atom("quote"), Expression.list());

		for (int i = listBody.size() - 1; i >= 1; --i)
		{
			expression = Expression.list(Expression.atom("cons"), listBody.get(i), expression);
		}

		return expression;
	}

	public String readNextToken()
	{
		skipWhitespace();

		if (position >= code.length())
			return "";

		char currentChar = code.charAt(position);

		if (isSpecialCharacter(currentChar))
		{
			position++;
			return Character.toString(currentChar);
		}
		else
		{
			int endPosition = find(position, (c) -> Character.isWhitespace(c) || isSpecialCharacter(c));
			String token = code.substring(position, endPosition);
			position = endPosition;
			return token;
		}
	}

	private boolean isSpecialCharacter(char c)
	{
		return specialChars.contains(Character.toString(c));
	}

	private void putBackToken(String token)
	{
		position -= token.length();
	}

	private void skipWhitespace()
	{
		position = find(position, (c) -> !Character.isWhitespace(c));
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
