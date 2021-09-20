Created as part of application to [the Recurse Center](https://www.recurse.com/).

# Lisp parser

Write code that takes some Lisp code and returns an abstract syntax tree. The AST should represent the structure of the code and the meaning of each token. For example, if your code is given "(first (list 1 (+ 2 3) 9))", it could return a nested array like ["first", ["list", 1, ["+", 2, 3], 9]].

During your interview, you will pair on writing an interpreter to run the AST. You can start by implementing a single built-in function (for example, +) and add more if you have time.

## Lisp syntax

Source: [S-expressions, the Syntax of Lisp](https://www.cs.unm.edu/~luger/ai-final2/LISP/CH%2011_S-expressions,%20The%20Syntax%20of%20Lisp.pdf)

Atom is either:
 - number
 - atomic symbol

S-expression is either:
 - atom
 - when s1, s2 ... sn are S-expressions then a list (s1 s2 ... sn) is an s-expression
