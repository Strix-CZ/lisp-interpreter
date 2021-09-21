package lisp;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class Expression
{
	private final boolean isAtom;
	private final String value;
	private final List<Expression> list;

	public static Expression atom(String value)
	{
		return new Expression(value);
	}

	public static Expression list(List<Expression> list)
	{
		return new Expression(list);
	}

	public Expression(String value)
	{
		this.isAtom = true;
		this.value = value;
		this.list = null;
	}

	public Expression(List<Expression> list)
	{
		this.isAtom = false;
		this.value = null;
		this.list = list;
	}

	public boolean isAtom()
	{
		return isAtom;
	}

	public String value()
	{
		if (!isAtom())
		{
			throw new RuntimeException("The expression is not an atom");
		}

		return value;
	}

	public boolean isEmpty()
	{
		if (list == null)
		{
			throw new RuntimeException("The expression is not a list");
		}

		return list.isEmpty();
	}

	public List<Expression> getList()
	{
		if (list == null)
		{
			throw new RuntimeException("The expression is not a list");
		}

		return List.copyOf(list);
	}

	@Override
	public boolean equals(Object o)
	{
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		Expression that = (Expression) o;
		return isAtom == that.isAtom && Objects.equals(value, that.value) && Objects.equals(list, that.list);
	}

	@Override
	public int hashCode()
	{
		return Objects.hash(isAtom, value, list);
	}

	@Override
	public String toString()
	{
		if (list != null)
		{
			return "(" + list.stream().map(Expression::toString).collect(Collectors.joining(", ")) + ")";
		}
		else
		{
			return value;
		}
	}
}
