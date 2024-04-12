# Programming in Clojure

- These file has some annotations took of the book Programming in Clojure, the code are took of the book too, but I want do my version in some cases to fix the content.

## Chapter 1 - Getting Started

### Why Clojure

- Is a functional language, so it's simple to understand, and isolate calculation from state and identity.

- Lisp is simple, it separates reading from evaluation, and
  the language is made from a tiny number of orthogonal parts.

- Lisp is also powerful, providing a compiler and macro system at runtime.

- Clojure’s time model is simple, separating values, identities, state, and time.

- Protocols are simple, separating polymorphism from derivation.

## Chapter 2 - Exploring the Language

### Forms

In Clojure, the term "forms" refers to any expression or code construct that is evaluated by the Clojure interpreter. Forms can be anything from function calls to literary values, arithmetic operations, and even flow control structures.

**Using Numeric Types**
Numeric literals are forms. Numbers simply evaluate to themselves. If you
enter a number, the REPL will give it back to you

```clojure
50
-> 50
```

Vector numbers is another kind of form. Create a vector of the numbers 6,23, 30

```clojure
[6, 23, 30]
-> [6, 23, 30]
```

A list is also a kind of form, so a list is "data", but it's also used to call functions, for example:

```clojure
(+ 4 4)
-> 8
```

so what happened ? So Clojure evaluates the list as a function call, this way is called prefix notation, where the function came first of args, so Clojure treating mathematical operators like all other functions and placing them first.

**Symbols**
Forms such as +, concat, and java.lang.String are called symbols and are used to
name things. For example, + names the function that adds things together.
Symbols name all sorts of things in Clojure, but Symbols can't start with a number.

**Booleans and nil**
Rules for booleans in Clojure: - true = true - false = false - Nil also evaluates to false when used in a boolean context. - Other than false and nil, everything else evaluates to true in a boolean context.

some examples:

```clojure

;The empty list in clojure is not false
;Syntax ((if part)       (else part))

(if ()"We're in Clojure" "WTF???")

->"We're in Clojure"
```

**Maps,Keywords and Records:**

Map is a collection of key/value pairs, maps have a literal form surrounded by curly braces, for example:

```clojure
(def franchise {"Lakers" "Lebron" "Warriors" "Curry"})
```

So the value **Lakers** is associated with the key **Lebron** and **Warriors** is associated with the key **Curry**

So if we want, we can use commas to delimit each key/value pair, Clojure doesn't care.

```clojure
(def franchise {"Lakers" "Lebron", "Warriors" "Curry"})
```

Maps also are functions, if we pass the key to a map, it'll return that key's value or nil if the key is not found, for example:

```clojure

(franchise "Lebron")
-> Lakers

(franchise "Klay")
-> nil
```

In clojure data structure are immutable, so any Clojure data structure can be a key in a map, a very common key type is the Clojure keyword.

Keyword is like symbol, except that begin with a colon (:), and keywords resolve to themselves:

```clojure
:foo
-> :foo
```

Besides that we can use keywords as identifiers in our code.

```clojure
(def person {:name "Felipe" :age 25})
```

we also can use the keywords as keys to access values in a map.

```clojure
(println (:name person))
```

Keywords are also functions. They take a map argument and look themselves
up in the map.

```clojure
(:name person)
-> "Felipe"

(person :name)
-> "Felipe"
```

If several maps have keys in common, you can document (and enforce) this
fact by creating a record with defrecord, so the syntax is:

```clojure
(defrecord name [arguments])
```

So the arguments name are converted to keys that have the values passed in when creating tne record. so let's use the defrecord to create a Person record:

```clojure
(defrecord Person [name age])

-> user.Person
```

So we can instantiate the record:

```clojure
(def Felipe (->Person "Felipe" 25))

(:age Felipe)
-> 25
```

### Reader Macros

The reader that is a part of Clojure system that transforms the code wrote on text to structures of data that Clojure can understand and manipulate, and reader macros has special comportment of reader add for character of macro prefix.

The comment is a reader macro, this macro is **;** and the special reader behavior is "ignore everything else up to the end of this line."

For definition Reader macros are abbreviations of longer list forms and are use to reduce clutter. You have saw one, that is **'** these character prevents evaluation, for example:

```clojure
'(1 2)
-> (1 2)

;'(1 2) is equivalent to the longer (quote(1 2)):

(quote (1 2))
-> (1 2)
```

So in clojure we have other reader macros to help us, with simple syntax.

### Functions

Functions in Clojure are simple list whose the first element resolves to a function, for example we have this function sum that do a sum of numbers:

```clojure
(+ 1 2)
;So in this example the **+** is the function that called.
```

Function names are typically hyphenated, as in clear-agent-errors. If a function
is a predicate, then by convention its name should end with a question mark, for example

```clojure
(number? 12)
-> true

(number? "S")
-> false
```

If we want declare our own function we can do like this:

```clojure
(defn name doc-string? attr-map? [params*] body)
```

so let's define our function:

```clojure
(defn say-hello
    "Returns Hello"
    [username]
    (str "Hello, " username))
```

If we call

```clojure
(say-hello "Felipe")
-> "Hello, Felipe"
```

But if we try call this function without pass an argument we'll get an error.

```clojure
(say-hello )
-> ArityException Wrong number of args (0) passed to: user$say-hello
clojure.lang.AFn.throwArity (AFn.java:437)
```

Clojure functions enforce their arity, that is, their expected number of arguments. If you call a function with an incorrect number of arguments, Clojure
will throw an ArityException. to avoid this error we can make **say-hello** a issue generic , when the caller omits username, let's se an example:

```clojure
;Syntax
(defn name dock-string? attr-map? ([params*] body)+)
```

so our say-hello function will be like these:

```clojure
(defn say-hello
    "Returns Hello + username, but the default is world"
    ([](say-hello "world"))
    ([username] (str "Hello, " username)))
```

**Anonymous Functions**

In Clojure we can create anonymous functions with **fn**, and we have three reasons to create an anonymous functions:

- The function is so brief and self-explanatory that giving it a name makes the code harder to read, not easier.
- The function is being used only from inside another function and needs a local name, not a top-level binding.
- The function is created inside another function for the purpose of closing over some data.

```clojure
;Syntax
(fn [params*] body)

#(body) ;this is a anonymous function too
```

- **When to Use Anonymous Functions**
  It's recommend use anonymous function when you find that they make your code readable.

### Vars, Bindings, and Namespaces

W

```clojure
foo
-> 300
```

the initial value of a var is called root binding.

- **Bindings**

Exists other kind of bindings in clojure, for example:

```clojure
(defn add1 [number] (+ 1 number)
-> #'user/add1

(add1 10)
-> 11
```

A function’s parameter bindings have a lexical scope: they are visible only
inside the text of the function body. Functions are not the only way to create
a lexical binding. The special form let does nothing other than create a set of
lexical bindings

```clojure
(let [bindings*] exprs*)
```

So the bindings are then in effect for exprs, and the value of the let is the
value of the last expression in exprs.

- **Destructuring**
  In Clojure, destructuring is a powerful feature that allows you to bind values from data structures, such as vectors, lists, or maps, to symbols in a concise and expressive way. Destructuring is commonly used in function parameters, let bindings, and other places where you want to extract values from complex data structures.

```clojure
(let [{:keys [name age]} {:name "John" :age 30}]
  (println name) ; prints "John"
  (println age)) ; prints 30

```

### Flow Control

Clojure has some flow control forms like if, do , loop/recur, and this is all we needs.

#### Branch with IF

If evaluates its first argument, If the args is logically true, it returns the result of evaluating it second argument, but if we want returns when is false we pass like third argument

```clojure
(defn is-small? [number]
(if (< number 100) "yes" "no"))
```

#### Side Effects with Do

The If's in Clojure just allows one form to each brach, but if we want do another thing with register determined brach was chosen we can use **do** to this like below:

```clojure
(defn is-small? [number]
    (if (< number 100)
    "yes"
    (do
    (println "Saw a big number" number)
    "no")))
```

**Do** takes any number of forms, evaluates them all, and returns the last. so in this case we use to print the big number.

if we call the function:

```clojure
(is-small? 200)
| Saw a big number 200
-> "no"
```

This is an example of a side effect. The println doesn’t contribute to the return
value of is-small? at all. Instead, it reaches out into the world outside the
function and actually does something.

#### Recur with loop/recur

The syntax of loop in Clojure:

```clojure
(loop [bindings *] exprs *)
```

the loop working like a let, establishing bindings and then evaluating exprs, but the difference is that loop sets a recursion point, which can then be targeted by the recur special form:

```clojure
(recur exprs *)
```

so recur bind new values for loop's bindings and returns control to the top of the loop. let's do a example

```clojure
(loop [result [] x 5]
  (if (zero? x)
    result
    (recur (conj result x)(dec x))))

;result
-> [5 4 3 2 1 ]
```

In the first time through, loop binds result to an empty vector and binds x to 5, since x is not zero, recur then rebinds the names x and result:

- result binds to the previous result conjoined with the previous x.
- x binds to the decrement of the previous x.

Control then returns to the top of the loop, 'cause the x is again not zero, and the loops continues,
accumulating the result and decrementing x. so eventually the x reaches the zero and if terminates the recurrence, returning result.

but we also can use recur back to the top of a function, it's make simple to write a function whose entire body acts as an implicit loop, so we can do this:

```clojure
(defn countdown [result x]
  (if (zero? x)
    result
    (recur (conj result x)(dec x))))

(countdown [] 5)
-> [5 4 3 2 1]
```

How can we see recur is a powerful building block, and Clojure provides many commons recursions, let's see some examples:

```Clojure
(into [] (take 5 (iterate dec 5)))
-> [5 4 3 2 1]

(into [] (drop-last (reverse (range 6))))
-> [5 4 3 2 1]

(vec (reverse (rest (range 6))))
-> [5 4 3 2 1]
```

- **Where's my for loop?**
  So to start clojure has no for loops and no direct mutable variables. In clojure when we want do some kind of loop we use map, filter or reduce to do that.

### Metadata

In Clojure, "metadata" refers to information associated with a data value that provides additional information about that value without affecting its content or behavior. Metadata is stored separately from the value and can be accessed using specific functions.

Metadata in Clojure is typically used to annotate or add contextual information to data values, such as maps, vectors, functions, or even numbers. This information can be useful for documentation, debugging, validation, or any other purpose where you need to associate metadata with a value without modifying the value itself.

#### Reader Metadata

The Clojure language itself uses metadata in several places. For example,
vars have a metadata map containing documentation, type information, and
source information.

some metadata keys:

```
:argilist       -> Parameter info used by doc
:doc            -> Documentation used by doc
:file           -> Source File
:line           -> Source line number
:macro          -> True For Macros
:name           -> Local Name
:ns             -> Namespace
:tag            -> Expected argument or return type
```

## Chapter 3 - Unifying Data with Sequences

Data Structures on Clojure can be accessed with **seq**, it's a logical list, 'cause in Clojure does not tie sequences to implementation details of a list, so the seq is an abstraction that can be used everywhere

Collection in Clojure can be viewed as sequences are called **seq-able**,and we can manipulate these collections using that includes some function to work with collections.

### Everything is a Sequence

Every aggregate data structure in Clojure can be viewed as a sequence. A
sequence has three core capabilities:

- We can get the first item of a sequence:

```clojure
;Syntax
(first aseq)

(first '(1 2 3 ))
-> 1
```

- We can get everything after the first item, in other words, the rest of a sequence.

```clojure
;Syntax
(rest aseq)

(rest '(1 2 3))
-> (2 3 )
```

- We can construct a new sequence by adding an item to front of an existing sequence. This is called cosign:

```clojure
;Syntax
(cons elem aseq)

(cons 0 '(1 2 3 4))
-> (0 1 2 3 4)

```

So we also use these functions in other structures like vector, and will work as well.

```clojure
(first [1 2 3])
-> 1
```

We also can treat maps as seqs, if you think of a key/value pair as an item in a sequences, let's see some examples:

```clojure
(first {:fname "Aaron" :lname "Bedra"})
-> [:lname "Bedra"]

(rest {:fname "Aaron" :lname "Bedra"})
-> ([:fname "Aaron"])

(cons [:mname "James"] {:fname "Aaron" :lname "Bedra"})
-> ([:mname "James"] [:lname "Bedra"] [:fname "Aaron"])
```

### Using the Sequence Library

CLojure provides a lib of sequence it has a rich set of functionality that can work with any sequence. The functions provide a rich backbone of functionality that can take advantage of any data structure that obeys the basic first/rest/cons contract.

These functions are grouped into four broad categories:

- Functions that create sequences.
- Functions that filter sequences.
- Sequences predicates.
- Functions that transforms em sequences.

These divisions are somewhat arbitrary. Since sequences are immutable, most
of the sequence functions create new sequences. Some of the sequence
functions both filter and transform.

- **Creating Sequences**

Clojure provides some functions that create sequences like range, repeat, iterate, let's do some examples:

Range:

```clojure
;syntax - Range produces a sequence from a start to an end, incrementing by a step each time.
(range start? end step?)

(range 10)
- (0 1 2 3 4 5 6 7 8 9)
; Range includes their start but not their end, if I do not specify then, so start
; defaults to zero, and steps defaults to 1.

```

Repeat:

```clojure
;syntax - Repeats an element x n times
(repeat n x)


(repeat 6 2)
-> (2 2 2 2 2 2)

```

- **Filter Sequences**

Clojure also provides functions that filer sequences, returning a subsequence of the original sequence, like filter, take-while, drop-while, let's do some examples:

Filter:

```clojure
;Syntax
(filter pred coll)

(filter even? list-of-numbers)
-> (2 4 6 8 10)

```

- **Sequence Predicates**

A sequence predicate asks how some other predicate applies to every item in a sequence, for example,
the every? predicate asks whether some other predicate is true for every element of a sequence.
(words of book)

```clojure
(every? pred coll)

(every? odd? [1 3 5])
-> true

(every odd? [1 3 5 8])
-> false 
```

also we have some, that returns the first nonfalse value for it's predicate or returns nil if
no element matched:
```clojure
(some even? [1 2 3])
-> true

(some eve? [1 3 5])
-> false
```
It's important say that some is not a predicate, but it's often used like one, some returns 
the actual value of the first match instead of true.


- **Transforming Sequences**
Transformation functions transform the values in the sequence, for example:
```clojure
(map f coll)
```
Map takes a source collection coll and a function f, and it returns a new
sequence by invoking f on each element in the coll.

Reduce is another transformation:
```clojure
(reduce f coll)
```
F is a function of two arguments, reduce applies f on the first two elements in coll and then applies
f to the result and the third element, and so on. So reduce is useful for functions that "total up" a sequence in some way. let's see examples:
```clojure
(reduce + (range 1 11))
-> 55
```

We can sort a collection with sort or sort-by:
```clojure
(sort comp? coll)
(sort-by a-fn comp? coll)
```
sort sorts a collection by the natural order of it's elements, where sort-by sort a sequence by the result of calling a-fn on each element:
```clojure
(sort [42 1 7 11])
-> (1 7 11 42)
``` 

- **Lazy and Infinite Sequences**

Most Clojure sequences are lazy, that means  elementes are not calculated until they are neeeded. Using lazy sequences has many benefits:
 - You can postpone expensive computations that may not in fact be needed.
 - You can work with huge data sets that do not fit into memory.
 - You can delay I/O until it is absolutely needed.

When should you prefer lazy sequences? Most of the time. Most sequence functions return lazy sequences, so you pay only for what you use. More important, lazy sequences do not require any special effort on your part.


- **Forcing Sequences**

You have created a lazy sequence, and you want to force the sequence to evaluate fully. The problem usually arises when the code generating the sequence has side effects. Consider the following sequence, which embeds side effects via println:
```clojure
(def x (for [i (range 1 3)] (do (println i)i)))
```

So the previous code prints nothing. Since the definition of x does not actually use the elements, Clojure does not evaluate the compehension to get them. You can force evaluation with **doall**:
```clojure
(doall coll)
```
The **doall** forces Clojure to walk the elements of a sequence and returns the elements as a result:
```clojure
(doall x)
|1
|2
-> (1 2)
```
We can also use **dorun**:
```clojure
(dorun coll)
```
**dorun** wals the elments of a sequence without keeping past elements in a memory. As a result, dorun can walk collection to large to fit in memory.
```clojure
(def x (for [i (range 1 3)(do (println i))]))

(dorun x)
|1
|2
->nil
```
The nil return value is a telltale reminder that dorun does not hold a reference
to the entire sequence. The dorun and doall functions help you deal with side
effects, while most of the rest of Clojure discourages side effects. You should
use these functions rarely.

### Clojure Makes Java Seq-able

abstraction of first/res applies to anything that there can be more than one of, in the Java world, that includes the following:
- Regular Expressions
- File system traversal
- XML processing
- Relational database results
- The collectionsl API 

So Clojure wraps these Java API's, making the sequence library available for almost everything we do.

- **Seq-ing Java Collection**

If we try to apply the sequence functions to Java collections, you will find that they behave as sequences. Collection that can act as sequences are called seq-able. For example, arrays are seq-able:
```clojure
(first (.getBytes "hello")) ;String.getBytes returns a byte array
-> 104

(rest (.getBytes "hello"))
-> (101 108 108 111)

(cons (int \h) (.getBytes "ello"))
-> (104 101 108 108 111)
```
Maps and HashTables are also seq-able:
```clojure
 ;System.getProperties returns a Hashtable
(first (System/getProperties))
-> #<Entry java.runtime.name=Java(TM) SE Runtime Environment>

(rest (System/getProperties))
-> #<Entry sun.boot.library.path=/System/Library/... etc. ...
```


- **Seq-ing Regular Expressions**
Clojure's regular expressions use the java.util.regex library unde the hood. At the lowest level, this exposes the mutable nature of Java's Matcher, so we can use **re-matcher** to create a Matcher for a regular expression and a string and then a loop on re-find to iterate over the matches.
```clojure
(re-matcher regexp string)
```
we also can use a better regex:
```clojure
(re-seq regexp string)
```
re-seq exposes an immutable seq over the matchers. This gives you the power of all of CLojure's sequence functions.

re-seq is a great example of how good abstractions reduce code bloat. Regular
expression matches are not a special kind of thing, requiring special methods
to deal with them. They are sequences, just like everything else.

- **Seq-ing the File System**

We can seq over  the file system. For the starters, you can call java.io.file directyl:
```clojure
(import '(java.io File))
(.listFiles (File. "."))
-> [Ljava.io.File;@lf70f15e
```
java.io.File is a Java's toString() representation for an array of Files. Sequence
functions would call seq o this automattically, but the REPL doesn't.

## Calling Structure-Specific Functions 

Clojure's sequence functions allow  you to write very general code. Sometiimes
you will want to be more specific and take advantage of the characteristics of 
a specific data structure, clojure includes functions that specifically target 
list, vectors, maps, structs, and sets.

- **Functions on List**
```clojure
(peek coll); take first element of list

(pop coll); take rest elements of list



(peek '(1 2 3))
-> 1

(pop '(1 2 3))
-> 2
```

- **Functions on Vectors**
```clojure
(peek '(1 2 3))
-> 1

(pop '(1 2 3))
-> 2

;we can use GET, get returns the value at an index or returns nil if the index
;is outside the vector
(get [:a :b :c] 1)
-> :b

(get [:a :b :c] 8)
-> nil

;Vectors are themselver functions
([:a :b :c] 2)
-> :c

([:a :b :c] 5)
-> Java.lang.ArrayIndexOutOfBoundsException: 5


;we can use assoc to assocate a new value with a particular index
(assoc [0 1 2 3 4 5] 1 :one)
-> [0 :one 2 3 4 5]

```