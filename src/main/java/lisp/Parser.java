package lisp;

import java.util.Collections;

public class Parser
{
	private final String code;

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
		String trimmed = code.trim();

		if (trimmed.startsWith("("))
		{
			if (trimmed.endsWith(")"))
			{
				String bodyOfList = trimmed.substring(1, trimmed.length() - 1).trim();
				return Expression.list(
						bodyOfList.isEmpty()
								? Collections.emptyList()
								: Collections.singletonList(parse(bodyOfList)));
			}
			else
			{
				throw new SyntaxError("Missing right parentheses\n" + code);
			}
		}
		else
		{
			return Expression.atom(trimmed);
		}
	}
}
