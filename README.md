# Homework 4 (20 Points)

The deadline for Homework 4 is Saturday, October 6, 6pm. The late
submission deadline is Thursday, October 11, 6pm.

## Getting the code template

Before you perform the next steps, you first need to create your own
private copy of this git repository. To do so, click on the link
provided in the announcement of this homework assignment on
Piazza. After clicking on the link, you will receive an email from
GitHub, when your copy of the repository is ready. It will be
available at
`https://github.com/nyu-pl-fa18/hw04-<YOUR-GITHUB-USERNAME>`.  
Note that this may take a few minutes.

* Open a browser at `https://github.com/nyu-pl-fa18/hw04-<YOUR-GITHUB-USERNAME>` with your Github username inserted at the appropriate place in the URL.
* Choose a place on your computer for your homework assignments to reside and open a terminal to that location.
* Execute the following git command: <br/>
  ```git clone https://github.com/nyu-pl-fa18/hw04-<YOUR-GITHUB-USERNAME>.git```<br/>
  ```cd hw04```

Put your answers to problems 1-3 into the text file `solution.md`.
The code template for solving Problem 4 is provided in the file

```
src/main/scala/pl/hw04/hw04.scala
```

relative to the root directory of the repository. Follow the
instructions in the notes for Class 2 to import the project into
InteliJ (or use your other favorite IDE or editor to work on the assignment).


## Submitting your solution

Once you have completed the assignment, you can submit your solution
by pushing the modified code template to GitHub. This can be done by
opening a terminal in the project's root directory and executing the
following commands in the :

```bash
git add .
git commit -m "solution"
git push
```

You can replace "solution" by a more meaningful commit message.

Refresh your browser window pointing at
```
https://github.com/nyu-pl-fa18/hw04-<YOUR-GITHUB-USERNAME>/
```
and double-check that your solution has been uploaded correctly.

You can resubmit an updated solution anytime by reexecuting the above
git commands. Though, please remember the rules for submitting
solutions after the homework deadline has passed.


## Problem 1: Activation Records (4 Points)

Consider the following simple C program (see also the file `foo.c`):

```c
#include <stdio.h>

void foo() {
  int i;
  printf("%d ", i++);
}

int main() {
  int j = 1;
  while (j <= 10) {
    foo();
    j = j + 1;
  }
}
```

This program is erroneous: the local variable `i` in `foo` is not
initialized before it is used, which according to the C standard,
causes the program to have undefined behavior. Nevertheless, for many
C compilers and architectures, this program will consistently print
the sequence `0 1 2 3 4 5 6 7 8 9` when it is executed. 

1. Use your knowledge about how local variables such as `i` in `foo`
   are stored in activation records on the stack to explain this
   behavior.

2. Also explain why the program might print a sequence other than `0 1
   2 3 4 5 6 7 8 9`.

## Problem 2: Deep vs. Shallow Binding (6 Points)

Consider the following Scala program:

```scala
var x = 0;

def set_x(v: Int): Unit {
  x = v;
}

def print_x(): Unit {
  println(x);
}

def foo(s: Int => Unit, p: () => Unit, y: Int): Unit {
  var x = 0;
  
  if (y == 1 || y == 2) x = 1;
  else set_x(2)
  
  if (y == 1 || y == 3) println(x);
  else p();
}

foo(set_x, print_x, 1);
foo(set_x, print_x, 2);
foo(set_x, print_x, 3);
```

1. What does this program print with static scoping semantics for
   names and deep binding semantics for functions that are passed as
   arguments to other functions (i.e. Scala's standard semantics)?
   Explain.
   
2. What would this program print if Scala were to use dynamic scoping
   for names and shallow binding semantics for functions passed as
   arguments? Explain.

## Problem 3: Parameter Passing Modes (8 Points)

Consider the following program:

```scala
def params(x: Int, y: Int) {
  x = y + y;
  println(x);
}

var z = 1;
params(z, z + 1);
println(z);
```

What does this program print if we make the following assumptions about
the parameter passing modes for the parameters `x` and `y` of
`params`:

1. both `x` and `y` are call-by-value parameters;
2.  `x` is call-by-reference and `y` is call-by-value;
3. `x` is call-by-value and `y` is call-by-name;
4. `x` is call-by-reference and `y` is call-by-name;

It is enough if you provide the outputs of the two `println` calls for
each variant. You don't need to explain your results.

Clarification: Scala does not actually allow assignments to parameters
and does not support parameters with call-by-reference semantics
directly. So you can't execute this code in Scala as given. We here
consider a hypothetical language that behaves like Scala in every way
except for the above differences.

## Problem 4: Implementing Custom Control Flow Constructs (2 Points)

Call-by-name parameters are particularly useful when one wants to
implement custom control-flow constructs. Here, we use this idea to
extend Scala with a new loop construct

```scala
until (cond) { 
  body
}
```

This loop construct should behave like a `while` loop in Scala, except
that the loop condition is negated: the loop exits when `cond` becomes
`true` rather than `false`. That is, the above code should behave
exactly like the following while loop:

```scala
while (!cond) {
  body
}
```

We can implement `until` as a function that takes the loop condition
`cond` and the loop body `body` as call-by-name parameters:

```scala
def until(cond: => Boolean)(body: => Unit): Unit = {
  ???
}
```

Note that we here take advantage of another useful feature of Scala:
multiple parameter lists. The two parameters `cond` and `body` are
passed to `until` in separate parameter lists. This allows us to call
`until` as if it were a loop construct that was built into the Scala
language. For instance, with the correct implementation of `until`,
the following code will print all values from `1` to `10`:

```scala
var x = 0

until (x == 10) {
  x = x + 1
  println(x)
}
```

Here, the expression `x == 10` will be passed to `cond` and the block
expression `{ x = x + 1; println(x) }` will be passed to `body`.

Provide an implementation of `until` that has the desired behavior.
Do not use a while loop for the implementation of `until`. Instead,
use recursion to implement the looping behavior.

