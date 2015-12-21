# Homework 2: User-defined inductive types and polymorphic higher-order functions

*CSci 2041: Advanced Programming Principles, Fall 2015*

**Due:** Friday, October 9 at 11:59pm


##1. More working with user-defined types

In class we saw several examples of user-defined types.  Let's
continue to work with these.

### TLD of hostinfo

In lecture, we defined a type `hostinfo` that is either a 4-byte "IP
address" (like `(134,84,159,182)`) or a string-valued "DNS name" (like
`"www.myu.umn.edu"`): 

```
type hostinfo = IP of int * int * int * int | DNSName of string
```
We've conveniently placed this type definition in the file
"hostinfo.ml" under `hw2`.

An important property of a DNS name is the _top level domain_ - the
string after the last `'.'` character. For example the top-level
domain (or TLD) of "www.myu.umn.edu" is "edu", the TLD of
"www.google.com" is "com", and the TLD of "cs2041.org" is "org".
In `hostinfo.ml`, add the OCaml definition for the function `tld : hostinfo -> string
option`, which returns the TLD of a hostinfo value that is a DNS
name, and `None` if its argument is an IP address.   Some example
evaluations:

+ `tld (IP (8,8,8,8))` evaluates to `None` because its argument is an
IP value.
+ `tld (DNSName "cnn.com")` evaluates to `Some "com"`
+ `tld (DNSName "comcast.net")` evaluates to `Some "net"`

Hint: you might find the functions `String.sub` and `String.rindex` in the
[`String` module](http://caml.inria.fr/pub/docs/manual-ocaml/libref/String.html)
to be useful here.

### Records and referential integrity

Consider the following data structures, that we've defined in
`facebook.ml`, and which might be implemented by a database server
somewhere at `facebook.com`: 

```
type user_id = UID of int
type user = { id: user_id ; name : string ; private_data : string ; friends : user_id list }
type post_id = PID of int
type wall_post = { pid : post_id ; uid : user_id ; contents: string }
type database = { users : user list ; wall_posts : wall_post list }
```

A key property that we might want a` database`  to have is referential integrity:
essentially that every `uid` appearing in some element of `db.wall_posts` or as an
element of `u.friends` is also present as the `u.id` component of some
record in the user list.  Let's write Ocaml functions that check the
referential integrity of a `database`, and add them to the
`facebook.ml` file:

+ `check_wp : wall_post -> user list -> bool` Checks the referential
  integrity of a `wall_post`, e.g. that `w.uid` appears as `u.id`
  for some user `u` in its second argument.  Example evaluations:
  `check_wp viktors_post hw.users` should evaluate to `false` and `check_wp
  rons_post hw.users` should evaluate to `true`.

+ `check_friends: user -> user list -> bool` Checks the referential
  integrity of a single `user`, `u`, by making sure that each
  `user_id` in `u.friends` is present as the `id` of some user `u'` in
  its second argument.  Example evaluations: `check_friends viktor
  hw.users` should evaluate to `false` and `check_friends harry
  hw.users` should evaluate to `true`.

+ `check_db : database -> bool` Checks the referential integrity of an
  entire database, using `check_wp` and `check_friends` as
  needed. Example evaluations; `check_db hw` should evaluate to `true`
  and `check_db ds` should evaluate to `false`.

Note that since Facebook has around one billion users, your functions should
be tail recursive in order to get full credit.

### Documents

An example of computing objects that have a heirarchical structure is
HTML documents; an HTML document in general is structured as a
sequence of HTML _entities_, (for example, anchors (links), text,
headings, lists, images, tables, frames...) many of which may enclose
more HTML entities.  In the file `document.ml`, you'll find definitions
for a type representing a small subset of HTML:

```
type entity =
	Title of entity list
	| Heading of entity list 
	| Text of string 
	| Anchor of anchor
and anchor = Named of string * (entity list) | HRef of string * (entity list)

type document = { head : entity list ; body : entity list }
```

Notice that the types `anchor` and `entity` are _mutually recursive_:
an `entity` might include an `anchor` as one of its elements and every
`anchor` includes an `entity list`. 

#### Extending with Lists 
Extend the definition of the `entity` type to include another variant,
`List`; a List is a list of `listEntity`s, and each
`listEntity` consists of a list of `entity`s.  Modify the example
`document`, `d2`, in the place indicated to include a list of the
indicated elements.

You'll also need to update the function `check_rules` that checks that the `head` of a
document contains no `Anchor`s,  the `body` of a document contains
no `Title`s, and `anchor`s do not include nested `anchor`s.

#### Computing with Documents
Once you've extended `entity` with `List`s, you should also add the
following functions that compute on documents:

+ `find_headings : document -> entity list` returns a list of all the
  `Heading` entities in the body of a document.  Example evaluations:
  `find_headings d1` should evaluate to
  `[Heading [(Text "CS 2041 Document")] ]` and `find_headings d_err1`
  should evaluate to `[]`.

+ `extract_text: document -> string` Should extract and concatenate
  together (separated by single spaces) the contents of all `Text`
  variants appearing in the body of its argument.  Example evaluation:
  `extract_text d1` should evaluate to `"CS 2041 Document A short
  document A little more stuff Click this to go back"`.
  
##2. Higher-order functions

In lectures, we discussed how higher-order functions can be used to implement
parametric polymorphism by passing comparison functions to polymorphic
algorithms.  Let's investigate this further; put your answers to this
problem in your `hw2` directory in a file named `hw2_hof.ml`

The basic `assoc` function, which takes an element of type `'a` and a
list of `('a * 'b)` pairs, and searches by `=` for a pair whose first
element equals its first argument, can be parameterized in two ways.
In lecture, we discussed the parameterized version `assoc_by : ('a ->
bool) -> ('a * 'b) list -> 'b option` that searches for a pair whose
first element makes the function argument evaluate to `true`.  Another
way to parameterize `assoc` is by passing a function to use in place
of `=`: `assoc_eq : 'a -> ('a -> 'a -> bool) -> ('a * 'b) list ->
'b option`.

+ Give the basic implementation of `assoc_eq`.  Example evaluations:
  `assoc_eq 2 (=) [(2, "two")]` should evaluate to `Some "two"`;
  `assoc_eq "hi" (fun x y -> false) [("hi", "there")]` evaluates to
  `None`, and `assoc_eq 5 (fun x y -> y = (3 * x + 1))
  [(5, "no"); (16, "yes")]` evaluates to `Some "yes"`.

+ Let's see that neither choice of generalization is inherently
  better, part I: Give a definition of the function `assoc_by_eq : ('a -> bool) ->
  ('a * 'b) list -> 'b option` that implements `assoc_by` through a
  single call to `assoc_eq`.

+ Neither choice is inherently better, part II: Give a definition of the function
  `assoc_eq_by : 'a -> ('a -> 'a -> bool) -> ('a * 'b) list -> 'b
  option` that implements `assoc_eq` through a single call to
  `assoc_by`.

##3. Boolean Expressions

In class, we defined a datatype to represent boolean expressions.
Let's further explore how we might perform computations with these
expressions.  The file `boolExpr.ml` contains some starting pieces for
this question.

In order to process expressions, we first have to obtain them from
input, and we often do this from strings (either stored in files, or
entered into a program some other way).  In this question, we'll write
code to convert strings that represent prefix-form boolean expressions
into data structures representing these expressions, and evaluate
these expressions.  Here are some examples of these expressions:
`"(and (or (not (and x y)) y) (and z F))"`, `"(or (and x (and y z))
(or (not z) (and x (not y))))"`.

### Lexing
Reading structures like expressions or programs is usually
separated into two phases, _lexing_ and _parsing_.  In _lexing_, a
string is converted into a list of *tokens*, which are the important
lexical components of a program.  Here, the important components are:

+ Opening and Closing parentheses, `(` and `)`.
+ Operator keywords, `and`, `or`, and `not`
+ Constants, `T` and `F`
+ Variables, which could be named by strings like `x`, `y`, or `z_boolean_variable_blah_blah`.

1. In `boolExpr.ml`, define a type `token` which has constructors for
   each of these token types, `(`, `)`, `and`, `or`, `not`, constant
   (of bool),  and variable (carrying a string). 

2. The function `wordlist : string -> string list` already defined in
  `boolExpr.ml` converts a string into a list of substrings that could
  be tokens.  Write a function `tokens_p : (string -> bool) -> string list
  -> token list` which takes a list of strings output by `wordlist`, and
  a function that decides whether a string is a legal variable name, and
  outputs a list of tokens. If the input contains strings that are not
  valid tokens, `tokens_p` should cause an exception to be raised by
  calling `invalid_arg` with an error message about unknown or invalid
  tokens in the input.

3. Define the function `lowervars : string -> bool` that says any
   substring returned by `wordlist` is a legal variable name if it
   starts with a lowercase letter, and contains only lowercase letters and
   digits, and define the function `tokens` to lex `string list`s
   using this function. (A value `c : char` is a lowercase letter if `'a' <= c
   && c <= 'z'` and it is a digit if `'0' <= c && c <= '9'`.)

### Parsing

In _parsing_, a list of tokens is converted to a structured
representation of an expression, often called an "abstract syntax
tree."  In order to do this, we need to define the syntax of
expressions and the abstract representation.  We do so inductively, as
follows.  A boolean expression is either:

+ a constant,or
+ a variable, or
+ The and of two expressions, appearing in the input token sequence as
  `OPEN AND <expr1> <expr2> CLOSE`
+ the or of two epressions, appearing in the input token sequence as
   `OPEN OR <expr1> <expr2> CLOSE`
+ the negation of an expression, appearing in the token sequence as
  `OPEN NOT <expr> CLOSE`.

Add the type `boolExpr` to the file `boolExpr.ml`, to have variants
for each of these cases, and then define the function
`parse_bool_exp : token list -> boolExpr`.

A note about strategy: you will probably find it most convenient
to use a helper function that, given a token list, returns a pair: the
first complete boolean expression represented by the tokens in the
list, and the list of tokens remaining. So for example, if the list of
tokens given to the helper function is something like
`[ OPEN ; AND ; TRUE ; FALSE ; CLOSE ; OPEN ; NOT ; F ; CLOSE ]` then
it should return something like `(AndExpr (ConstExpr true, ConstExpr
false), [ OPEN ; NOT ; F ; CLOSE ])`.  This would be accomplished by

+ noticing (using a match expression) that the list starts with `OPEN::AND::more`; then

+ recursively calling the helper function on `more` to parse the first argument to the `AndExpr` and
  get the list of tokens after this argument (`[FALSE ; CLOSE; OPEN; NOT; F; CLOSE]`);

+ making a second recursive call on this list to parse the second argument and get
  the list of remaining tokens (`[CLOSE; OPEN; NOT; F; CLOSE]`); and

+ Checking with a pattern match that the new list of remaining tokens
  starts with a `CLOSE`, and if it does, returning the `AndExpr` value
  and the tail of _that_ list.

Your function should fail on token lists resulting from syntactically
ill-formed expressions: any that do not match one of the forms given
above, for example: 
+ `"and T F"` would be ill-formed because of the lack of parentheses;
+ `"(and T F"` would be ill-formed becuse of the lack of closing
parenthesis;
+ `"(and T)"` and `"(or T F T)"` would be ill-formed due to the
wrong number of arguments.
+ `"(and or T)"` would be ill-formed because the first argument to `and`
is not a valid expression.
+  `"(not T))"` would be ill-formed because of the extra closing paren;
+ `"T F"` would be ill-formed because it represents more than one
expression.

When parsing encounters this kind of ill-formed expression, it should
raise an exception by calling `invalid_arg` with a string containing a message
about a syntax error.  (Don't spend too much effort to make your error
messages detailed for this assignment.)
  
### Evaluating

Since we defined boolean expressions to allow variables, in order to
evaluate them we need some way to assign values to identifiers.  Write
the function `eval_bool_exp : boolExpr -> (string -> bool) -> bool`
that evaluates a boolean expression using its second argument to
assign boolean values to any identifiers in the expression.  Some
sample evaluations:

+ `eval_bool_exp (ConstExpr true) (fun v -> false)` should evaluate to
`true`.
+ `eval_bool_exp (VarExpr "var1") (fun v -> false)` should evaluate to
`false`.
+ `eval_bool_exp (AndExpr (VarExpr "var1", ConstExpr true)) (fun v ->
  true)` should evaluate to `true`.

Wrapping it up: add the following code to the end of `boolExpr.ml` to make the
program parse and evaluate a string passed on the command line
assuming the variables listed on the command line are true and any
others are false:

```
let () = if Array.length Sys.argv < 2 then () else
  let (_::sExpr::tlist)  = Array.to_list Sys.argv in
  let bExpr = sExpr |> wordlist |> tokens |> parse_bool_exp in
  let result = eval_bool_exp bExpr (fun v -> List.mem v tlist) in
  let () = print_string (if result then "True\n" else "False\n") in
  flush stdout
```

If you build `boolExpr.native` in the terminal: (note that you'll need
to include `-lib str` as an argument to `ocamlbuild`)
```
% ocamlbuild -lib str boolExpr.native
```
Then you can test your executable in the terminal, e.g.:
```
% ./boolExpr.native "(and v1 (not v2))" v1
True
% ./boolExpr.native "(not v1)" v1
False
```

##4.  Binary Search Trees

In lecture, we defined the type `'a bstree` to include both a binary
tree and comparison function  so that `insert` and `search` use
the same comparison function.  But since `'a bstree` is simply a
product type, nothing stops a really determined programmer from
creating, say, a value  `t : int btree` that is not a binary search
tree and then making an `int bstree` with `{ tree = t ; cmp = compare
}`.  Let's look at writing code to check that an `'a bstree` satisfies
the binary search tree condition required by its `cmp` function.
You'll find the type definition and function definitions for `insert` and
`search` in the file `btrees.ml`, and you should add your code for
this problem to the same program.

+ Write a function `treeMin : 'a btree -> 'a compare -> 'a option`
  that finds the smallest element in a binary tree (or `None` if the
  tree is `Empty`) according to its comparison function argument.

+ Write a function `treeMax : 'a btree -> 'a compare -> 'a option`,
  which finds the largest element in a binary tree.

+ Now write the function `check_bst : 'a bstree -> bool` that checks
  that its argument satisfies the binary search tree condition.

+ Add four let declarations to your file. binding the names `tree1`
  and `tree2` to correct bstrees and `tree_err1`, `tree_err2` to
  bstrees that are not actually binary search tree.

+ You can add test cases to an OCaml program using the idiom
```
	let () = assert <boolean expression>
```
  Add assertions to the end of `btrees.ml` that test each of the
  following:
  + `tree1` and `tree2` pass `check_bst`
  + `tree_err1` and `tree_err2` do not pass `check_bst`
  + inserting an element of the correct type into `tree1` results in
    a correct `bstree`
  + if we insert an element `e` into `tree2` to get `tree3` and
    search `tree3` for `e`, we find it.
  + `treeMax tree1.tree tree1.cmp` returns the correct value
  + `treeMin tree1.tree tree1.cmp` returns the correct value
	
## All done!

Don't forget to commit all of your changes to the various files edited
for this homework, and push them to your individual class repository
on UMN github:
+ `hostinfo.ml`
+ `facebook.ml`
+ `document.ml`
+ `hw2_hof.ml`
+ `boolExpr.ml`
+ `btrees.ml`
You can always check that your changes haved pushed
correctly by visiting the repository page on github.umn.edu.
