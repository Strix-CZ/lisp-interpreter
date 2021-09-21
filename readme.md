Created as part of application to [the Recurse Center](https://www.recurse.com/).

# Lisp parser

Write code that takes some Lisp code and returns an abstract syntax tree.
The AST should represent the structure of the code and the meaning of each token.
For example, if your code is given `(first (list 1 (+ 2 3) 9))`, it could return
a nested array like `["first", ["list", 1, ["+", 2, 3], 9]]`.

During your interview, you will pair on writing an interpreter to run the AST.
You can start by implementing a single built-in function (for example, `+`) and add
more if you have time.

## Syntax

Lisp has many dialects with different focuses and slightly different syntax.
To keep the parser and interpreter as simple as possible I have decided to
implement a subset of Common Lisp which is used by Paul Graham in the article  
[The roots of Lisp](http://www.paulgraham.com/rootsoflisp.html). This is a
short description of the syntax.

Expression is either:
 - atom, which is a sequence of letters. Example: `1`, `foo`, `foo+bar@`
 - a list enclosed in parentheses consisting of zero or more expressions.
   Items are separated by whitespace. Example: `(lorem ipsum 1)`, `()`

Shorthands:
 - `'x` translates to `(quote x)`.
 - `(list e1 ... en)` translates to `(cons e1 ... (cons en '()) ... )`.
