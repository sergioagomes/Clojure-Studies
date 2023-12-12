# Programming in Clojure  
 - These file has some annotations took of the book Programming in Clojure, the code are took of the book too, but I want do my version in some cases to fix the content.


## Chapter 1 - Getting Started

### Why Clojure

- Is a functional language, so it's simple to understand, and isolate calculation from state and identity.

- Lisp is simple, it separates reading from evaluation, and
the language is made  from a tiny number of orthogonal parts.

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

A list is  also a kind of form, so a list is "data", but it's also used to call functions, for example:
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
Rules for booleans in Clojure:
    - true = true
    - false = false
    - Nil also evaluates to false when used in a boolean context.
    - Other than false and nil, everything else evaluates to true in a boolean context.

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

When I define a def or defn, that object is store in a Clojure var, like this:
```clojure
(def foo 300)
-> #'user/foo
```
the symbol foo refers to a var that is a bound the the value 300, if I evaluate the symbol foo, it'll return the value of the associated var:
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

Clojure has some flow control forms like if, do , loop/recur


#### Branch with IF

If evaluates its first argument, If the args is logically true, it returns the result of evaluating it second argument, but if we want returns when is false we pass like third argument
```clojure
(defn is-small? [number]
(if (< number 100) "yes" "no"))
```

#### Side Effects with Do

The If's in Clojure just allows one form to each brach, but if we want do another thing with register determined brach was  chosen we can use **do** to this like below:
```clojure
(defn is-small? [number]
    (if (< number 100)
    "yes"
    (do
    (println "Saw a big number" number)
    "no"))
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
the loop os like a let, establishing bindings and then evaluating exprs.

The syntax of recursion in Clojure
```clojure
(recur exprs *)
```

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
## Unifying Data with Sequences

Data Structures on Clojure can be accessed with **seq**, it's a logical list, 'cause in Clojure does not tie sequences to implementation details of a list, so the seq is an abstraction  that can be used everywhere

Collection in Clojure can be viewed as sequences are called **seq-able**,and we can manipulate these collections using that includes some function to work with collections.

### Everything is a Sequence
Every aggregate data structure in Clojure can be viewed as a sequence. A
sequence has three core capabilities:
 - We can get the first item of a sequence:
```clojure
(first aseq)
```
 - We can get everything after the first item, in other words, the rest of a sequence.
```clojure
(rest aseq)
```
 - We can construct a new sequence by adding an item to front of an existing sequence. This is called cosign:
```clojure
(cons elem aseq)
```