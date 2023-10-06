# Programing in Clojure 
 - These file has some annotations took of the book Programming in Clojure, the code are took of the book too, but I want do my version in somes cases to fix the content.

### Why Clojure

- Is a functional language, so it's simple to understand, and isolate calculation from state and identity.

- Lisp is simple, it separates reading from evaluation, and
the language is made  from a tiny number of orthogonal partsp.

- Lisp is also powerful, providing a compiler and macro system at runtime.

- Clojure’s time model is simple, separating values, identities, state, and time.

- Protocols are simple, separating polymorphism from derivation.


### Forms

In Clojure, the term "forms" refers to any expression or code construct that is evaluated by the Clojure interpreter. Forms can be anything from function calls to literary values, arithmetic operations, and even flow control structures.


**Keywords:**

In clojure data structure are immutable, so any Clojure data structure can be a key in a map, a very common key type is the Clojure kerword.

Keyword is lika symbol, except that begin with a colon (:), and keywords resolve to themselves:
```clojure
:foo
-> :foo
```
Besides that we can use keywords as identifiers in our code.
```clojure
(def person {:name "Felipe" :idade 25})
```
we also can use the keywords as keys to acess values in a map.
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

(:age Felipeg)
-> 25
```


### Reader Macros

The reader that is a part of Clojure system that transforms the code wrote on text to strucutures of data that Clojure can understand and manipulate, and reader macros has special comportments of reader add for character of macro prefix.

The comment is a reader macro, this macro is **;** and the speciak reader behavior is "ignore everything else up to the end of this line."

For definition Reader macros are abrreviations of longer list forms and are use to reduce clutter. You have saw one, that is **'** these character prevents evaluation, for example:
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
(defn name-function [params*] body
```

so let's define our function:
```clojure
(defn say-hello [username]
    (str "Hello, " username))
```
If we call
```clojure
(say-hello "Felipe")
-> "Hello, Felipe"
```
But if we try call this functin without pass an argument we'll get an erro.
```clojure
(say-hello )
-> rityException Wrong number of args (0) passed to: user$say-hello
clojure.lang.AFn.throwArity (AFn.java:437)
```
Clojure functions enforce their arity, that is, their expected number of arguments. If you call a function with an incorrect number of arguments, Clojure
will throw an ArityException.


### Flow Control

Clojure has some flow control forms like if, do , loop/recur


#### Branch with IF

If evaluates its first argument, If the args is logically true, it returns the result of evaluating it second argument, but if we want returns when is false we pass like third argument
```clojure
(defn is-small? [number]
(if (< number 100) "yes" "no"))
```

#### Side Effects with Do


```clojure
(defn is-small? [number]
    (if (< number 100)
    "yes"
    (do
    (println "Saw a big number" number)
    "no"))
```


#### Recur with loop/recur 