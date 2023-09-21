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

Let's make some changes on the function.
```clojure
(defn apply-discount 
    [total-value]
    (* total-value (- 1 0.1)))
```
so here we change the multiplication to **-1 0.1**, in others words 1 less 10% of discount, that is 0.9. 

### Symbols and Namespaces

So let take the apply-discount function and make some improvements, so the fisrt thing is define a symbol to discount to make the function more clear.
```clojure
(defn apply-discount [total-value]
    (def discount 0.10)
    (* total-value (- 1 discount)))
```
If we pass the 1000 again, we'll get the same result.

But these is a bad pratice, 'cause we define a global varible inside our function, we don't have a guarantee that this symbol did not already exist and we just redefine it, and also we can acess the symbol outside the function, these is bad security.

So to avoid this, we need define a local symbol in the space of function, we'll use **let** that receive a vector, so we pass the discount inside the vector.
```clojure
(defn apply-discount [total-value]
    (let  [discount 0.10])
    (* total-value (- 1 discount)))
```
Now we can call a function again and all still works, but we can't acess the discount outside the function, 'cause the symbol is local.

We can better these fuction passing the function (* total-value (- 1 discount)) to inside the let.
```clojure
(defn apply-discount [total-value]
    (let  [discount 0.10]
        (* total-value (- 1 discount))))
``` 
The let will execute the operations inside them and return the result of last instruction, as we just have one instruction, the let will return the discount.


### Let multiples and conditionals

So let do some changes in our apply-discount, where we'll improve them.
```clojure
(defn apply-discount [total-value]
    (let  [discount-rate (/ 10 100)]
        (let [discount (* total-value discount-rate)])
        (println "Calc the discount" discount)
        (* total-value (- 1 discount))))
``` 
As we can see, we could define other let inside a let, but we have a better way to do that, just remember let is a vector so it can receive more than two values, so let's change.
```clojure
(defn apply-discount [total-value]
    (let  [discount-rate (/ 10 100)
           discount (* total-value discount-rate)]
        (println "Calc the discount" discount)
        (- total-value discount)))
``` 
Like this, if the total-value is 100, the discount will be multiply of 0.1 to 100, that is 10, so we change -1 to total-value.

So wait, let suppose we want these discount just work with the value is greater than 100, exclude the own 100. To do this we can use conditional, so let's beggining with tradional **If**, that is simple, let see the syntax.
```clojure
(if (> 50 100)
    (println "Greater")
    (println "Less"))
```
In clojure the **If** receive tree arguments: verification, what should execute if the return is true and what should be execute if the return is false, yeah is look like else but hidden.

Let's back to our function and add the conditional
```clojure
(defn apply-discount 
    [total-value]
    (if (> total-value 100)
    (let  [discount-rate (/ 10 100)
           discount (* total-value discount-rate)]
        (println "Calc the discount" discount)
        (- total-value discount))))
```
If we execute the function
```clojure
(apply-discount 1000)

;output we'll have
900
```
But if we execute like these:
```clojure
(apply-discount 100)

;output we'll have
nil
```
So these happen because we not define a return in the case the condition was false, and for default the return is **nil**, and in clojure the nil is considered false, thus in case of false we'll return just the total-value.
```clojure
(defn apply-discount 
    [total-value]
    (if (> total-value 100)
    (let  [discount-rate (/ 10 100)
           discount (* total-value discount-rate)]
        (println "Calc the discount" discount)
        (- total-value discount))
        total-value))
```
It's important define that **if** is not a function but a form, being more especif is a special form, in pratice special forms could be used in our code and are mixed with the functions that we invoke at different times.