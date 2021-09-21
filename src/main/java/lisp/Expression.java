package lisp;

public class Expression
{
	private final boolean isAtom;
	private final String value;

	private final Expression car;
	private final Expression cdr;

	public static Expression atom(String value)
	{
		return new Expression(value);
	}

	public static Expression list(Expression car, Expression cdr)
	{
		return new Expression(car, cdr);
	}

	public Expression(String value)
	{
		this.isAtom = true;
		this.value = value;
		this.car = null;
		this.cdr = null;
	}

	public Expression(Expression car, Expression cdr)
	{
		this.isAtom = false;
		this.value = null;
		this.car = car;
		this.cdr = cdr;
	}

	public boolean isAtom()
	{
		return isAtom;
	}

	public String value()
	{
		return value;
	}

	public boolean isEmpty()
	{
		return car == null;
	}

	public Expression car()
	{
		return car;
	}

	public Expression cdr()
	{
		return cdr;
	}
}
