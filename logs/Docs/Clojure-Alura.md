# Clojure Alura 
These markdonw has some simple annotations about the contente saw in traning clojure. 


### Functions and Vectors

In Clojure when the firs "arg" is the operation we want execute, for example:
```clojure
(println "Bem Vindo")

;Output
Bem Vindo
nil
```
the interpreter of clojure execute the function and also give us your return, in this case is null(nil).

if we want print a total of something, we can define a symbol using the keyword **def**, we call these **varible** because the value can change, a simple example bellow.
```clojure
(def total-books 15)

;return in repl
#'user/total-books
```
In these case the **user** is the space in memory that we working, in other words is a **namespace**. so if execute the total-books with pritln we get the value:
```clojure
(println total-books)

;output in repl
15
nil
```

in clojure the operation/function we want execute is always the first argument inside parenthesis.
```clojure
(+ 13 3)

;output
16
```
if we want sum 5 in the value of total-books, we can do it like this.
```clojure
(def total-books (+ total-books 5))

```
Like these we redefine the value of total-books, so as we saw, we can use the result of a function invokation as parameter to other function.

But in the most of case we want use the immutability, for example we can declare 
stock with a vector that has two items:
```clojure
(def stock ["Harry Potter", "One Piece"])

;obs in clojure the , between is considered a space, but is  optional to use
```
We can print the stock without println
```clojure
;in repl

stock

;output
["Harry Potter", "One Piece"]
```
we also can use the stock like a function
```clojure
(stock 0)

;output
["Harry Potter"]
```
for example above we call the firs element of our vector stock.In Clojure almost everything can works like a function, with some execpetions like numbers values.

If we want add another book to our stock we can use the function **conj**. 
```clojure
(conj stock "Naruto")

;the return
["Harry Potter" "One Piece" "Naruto"]
```
But if we print the stock will have a surprise
```clojure
(println stock)

;output
["Harry Potter", "One Piece"]
```
The conj not change the content of original vector, and here we found immutability, when we defined a vector with two items it always have just two items, even we use the conj, because we don't informate that stock should indicate a other value, to do this redefine, we can do it like this:
```clojure
(def stock (conj stock "Naruto"))
```
These way we use the new vector with tree values.
