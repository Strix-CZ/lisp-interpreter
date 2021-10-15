package lisp;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;

public class ParsingFileTest
{
	@Test
	void parseFileTest() throws IOException
	{
		List<Expression> expressions = Parser.parseFile(Path.of("jmc.lisp"));
		Assertions.assertThat(expressions)
				.hasSize(10);

		Assertions.assertThat(expressions.get(9))
				.isEqualTo(Parser.parse(
						"(defun evlis. (m a)\n"
						+ "  (cond ((null. m) '())\n"
						+ "        ('t (cons (eval.  (car m) a)\n"
						+ "                  (evlis. (cdr m) a)))))"));
	}
}
