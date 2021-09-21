package lisp;

public class Expression
{
	private final boolean isAtom;
	private final String value;

	public static Expression atom(String value)
	{
		return new Expression(value);
	}

	public Expression(String value)
	{
		this.isAtom = true;
		this.value = value;
	}

	public boolean isAtom()
	{
		return isAtom;
	}

	public String value()
	{
		return value;
	}
}
