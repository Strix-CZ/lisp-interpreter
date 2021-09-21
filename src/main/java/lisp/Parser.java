package lisp;

public class Parser
{
	public Expression parse(String code)
	{
		String trimmed = code.trim();

		if (trimmed.equals("()"))
		{
			return Expression.list(null, null);
		}
		else
		{
			return Expression.atom(trimmed);
		}
	}
}
