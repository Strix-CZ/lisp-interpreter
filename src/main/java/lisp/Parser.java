package lisp;

public class Parser
{
	public Expression parse(String code)
	{
		String trimmed = code.trim();

		if (trimmed.startsWith("("))
		{
			return Expression.list(null, null);
		}
		else
		{
			return Expression.atom(trimmed);
		}
	}
}
