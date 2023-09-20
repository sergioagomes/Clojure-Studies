# Clojure Alura 
These markdown have some simple annotations about the content saw in training clojure. 


### Functions and Vectors

In Clojure when the first "arg" is the operation we want execute, for example:
```clojure
(println "Welcome")

;Output
Welcome
nil
```
The interpreter of clojure executes the function and also give us your return, in this case is null(nil).

If we want print a total of something, we can define a symbol using the keyword **def**, we call these **varibles** because the value can change, a simple example below.
```clojure
(def total-books 15)

;return in repl
#'user/total-books
```
In these cases the **user** is the space in memory that we work, in other words, is a **namespace**. So if execute the total-books with pritln we get the value:
```clojure
(println total-books)

;output in repl
15
nil
```

In clojure the operation/function we want to execute is always the first argument inside parenthesis.
```clojure
(+ 13 3)

;output
16
```
If we want sum 5 in the value of total-books, we can do it like this.
```clojure
(def total-books (+ total-books 5))

```
Like these we redefine the value of total-books, so as we saw, we can use the result of a function invocation as parameter to another function.

But in the most of case we want to use the immutability, for example, we can declare stock with a vector that has two items:
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
For example, above we call the first element of our vector stock.In Clojure almost everything can works like a function, with some exceptions like number values.

If we want to add another book to our stock, we can use the function **conj**. 
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
The conj not change the content of original vector, and here we found immutability, when we defined a vector with two items it always has just two items, even we use the conj, because we don't informant that stock should indicate an other value, to do this redefine, we can do it like this:
```clojure
(def stock (conj stock "Naruto"))
```
These way we use the new vector with tree values.


### Functions and Parameters

To define a function in Clojure we can use **defn**, so let define a fuction print message.
```clojure
(defn print-message
    (println "Welcome to the Store!!"))
```

But if we try these we get a syntax error, because if we define a function with defn, we need to
also pass the parameters that it receives, in our case we'll pass a void vector because the function doesn't receive a parameter.
```clojure
(defn print-message []
    (println "Welcome to the Store!!"))
```
Now if we execute the function, the message will appear on screen.

But now let trying do a complex function, so we define a function "apply-discount" that receive a parameter a value [tota-value], and this function will return the total-value multiplied to 0.9, so the function will stay like that.
```clojure
(defn apply-discount [total-value]
    (* total-value 0.9))
```
So if we call this function and pass the 1000 as value, we get the following result.
```clojure
(apply-discount 1000)

;output
900.0
```
This is a pure function because with the same input, we'll always get the same output.