package lisp;

import java.util.ArrayList;
import java.util.List;
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
			List<Expression> listBody = new ArrayList<>();

			while (true)
			{
				String potentialEndParentheses = readNextToken();

				if (potentialEndParentheses.isEmpty())
				{
					throw new SyntaxError("Missing right parentheses\n" + code);
				}
				else if (potentialEndParentheses.equals(")"))
				{
					return Expression.list(listBody);
				}
				else
				{
					putBackToken(potentialEndParentheses);
					listBody.add(parse());
				}
			}
		}
		else
		{
			return Expression.atom(nextToken);
		}
	}

	public String readNextToken()
	{
		if (position >= code.length())
			return "";

		skipWhitespace();

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
