package lisp;

public class Parser
{
	public Expression parse(String code)
	{
		return Expression.atom(code);
	}
}
