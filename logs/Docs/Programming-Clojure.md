# Clojure 



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
