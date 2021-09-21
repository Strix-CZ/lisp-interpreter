package lisp;

public class Parser
{
	public Expression parse(String code)
	{
		String trimmed = code.trim();

		if (trimmed.startsWith("("))
		{
			if (trimmed.endsWith(")"))
			{
				return Expression.list(null, null);
			}
			else {
				throw new SyntaxError("Missing right parentheses\n" + code);
			}
		}
		else
		{
			return Expression.atom(trimmed);
		}
	}
}
