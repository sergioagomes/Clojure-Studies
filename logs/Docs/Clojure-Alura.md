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


### Leiningen

Leiningen is a build automation and project management tool for the Clojure programming language. It was created to simplify the process of configuring, building, and managing Clojure projects, making it easier to work with libraries, dependencies, and build tasks.

So to create a simple project clojure using leinigen, we can do it like that:
```bash
lein new  name-of-project
```
So let create a stock project using the command above, and the project will have the following structure:
```
├── CHANGELOG.md
├── doc
│   └── intro.md
├── resouces
├── LICENSE
├── project.clj
├── README.md
├── resources
├── src
│   └── core.clj
└── test
    └─ core_test.clj
```
Let's describe this:

- CHANGELOG: That's a model for CHANGELOG in the project.
- doc: Is a folder dedicated to the documentation of the project, it's initialized with an intro.md for writing the first steps.
- LICENSE: Is a file containing the Eclipse Public License - v 2.0 the same license used in Clojure and default in most Open Source projects that use the language.
- project.clj: That we're gonna declare our dependencies and configuration for the project.
- README: That's an entry document and front page in the remote repository for the project.
- resources: Where we can put the files we want to zip in the final jar when we compile the project.
- src: That's where our code will really be.
- test: That's where the tests will be placed.



### Predicate

A predicate is a function that returns a boolean value(true or false) with a
base in one or more conditions, the predicate has the goal to test if one or more properties are attending to an argument.

Follow the convention of clojure that predicates finished with **?**
```clojure
(defn even? [n]
  (if (even? n)
    true
    false))
```
In this example the function **even?** Receive an argument **n** and verify if n is an
even number, remember that even is a function that already exists on Clojure.

We can use the predicate in many contexts, like, conditional tests, filters, and high order function, for example:
```clojure
(def numbers [1 2 3 4 5 6])

(def even-numbers (filter even? numeros))
```
In these examples, the **filter** uses the predicate **even?** To create a new sequence
**even-numbers** that contain just a sequence of evens.


### Function as Parameters

In Clojure like other functional languages we can pass functions as parameters to other functions, these allow us to create a code more flexible and reusable, let's see an example.
```clojure
(defn apply-function [function list]
  (map function list))
```
So here we have a function **apply-funtion** that receives two arguments, the "function" and "list", where the function is the function we want apply to which element of the list, so let use.
```clojure
(defn double[n]
  (* n 2))

(def numbers[1 2 3 4 5])

(def double-numbers (apply-function double numbers)

;; Result: (2 4 6 8 10)
```
In this example the function double, is a function that doubles the value of a number, the **numbers** is a list with numbers that we want process. So we'll pass the
**double** as an argument to apply-function, that apply the **double** function to all elements of the list, that will return the result in symbol **double-numbers**

These are called **High Order Functions", in other words is a function that uses as parameter to another function, or can return a new function as a result.

### Anonymous Functions and Lambdas

Anonymous functions on clojure are functions that noun has a specific name, so we create then directly at the point that we wish to use it, in clojure we can create an anonymous function using the macro **fn** or **#()** also called **lambda**, let's see some examples.

- **fn**
```clojure
(def number (fn [a b] (+ a b)))
```
- **Lambda** 
```clojure
(def square #( * % %))
```

### Map and Filter

- **Map**:
```clojure
(println "map"(map apply-discount prices)
```
Here we map pass for all prices and apply the function apply-discount, is realizing a math function **(f(x))**. So
the map is used when we want to do a loop passing all the elements and applying a function.

- **Filter**
```clojure
(println (range 10))
(println (filter even? (range 10)))

;;output
(0 1 2 3 4 5 6 7 8 9)
(0 2 4 6 8)
```
The filter is equal map, and it receives two parameters. The first parameter receive the function **even?** And second receive every element set **(range 10)**, if the function returns true, they are held, if not, they are discarded.

### Reduce

Reduce is the part of Trinity (map, filter, reduce), is like the others, receive as first parameter the function we want apply and the second the sequence of values we want to reduce.


### Threading

In Clojure, "threading" refers to combining multiple functions and operations into an expression in a more readable and functional way, allowing for the composition of functions in a more declarative style. Threading is a technique that helps make code more concise and easier to understand.

There are two main threading macros in Clojure: -> and ->>. Both are used to chain functions and operations in an expression.

- **Threading-First**: This chain the function and operations of left to right, passing the result of operation as argument to next function.
```clojure
(-> x
    (function1 arg1)
    (function2 arg2))
```
the value of X is passed to **function1** and their result is passed to function2...


- **Threading-Last**: is used when we want pass the argument after the operation, of example:
```clojure
defn factorial [n]
 ( ->> (inc n)
       (range)
       (next)
       (reduce *))) ;; reduce them by multiplying

(factorial 5) ;; 120
```
In other word when we need:
 ```clojure
(name-of-function [argument] <function-input>) 
```
We'll use thread first,and if we need:
```clojure
(name-of-function <function-input> [argument])
```
We'll use thread last.


### Working with grouping and schemes

working with maps and groping 

**db file** - in this file I defined a map structure **order** and replied 3 times and also did a function to show all orders.
```clojure
(def order1 {:user 1
             :items  {:bag {:id :bag :amount 2 :unit-price 80}
                     :shirt {:id :shirt :amount 3 :unit-price 40}
                     :shoes {:id :shoes :amount 1}}})

;here we use the keyword to indentify in Id

(def order2 {:user 12
             :items  {:bag {:id :bag :amount 2 :unit-price 80}
                      :shirt {:id :shirt :amount 3 :unit-price 40}
                      :shoes {:id :shoes :amount 1}}})
(def order3 {:user 12
             :items  {:bag {:id :bag :amount 2 :unit-price 80}
                      :shirt {:id :shirt :amount 3 :unit-price 40}
                      :shoes {:id :shoes :amount 1}}})



(defn all-orders []
   [order1, order2, order3])
``` 

- **oder1 ->** Define a order that is a map structure, where I have user  and items, inside of items I have a map of items with amount and unit-price



- **all-orders ->** is a function that returns all orders

**main ->** in this file I will use the data that I defined on **db file**, so what I want grouping these datas on form to show quantity of orders and the value total per user.
```clojure
(ns store.main
  (:require [store.db :as s.db]))

(println "ORDERS")

(defn total-item
  [[_ details]]
  (* (get details :amount 0) (get details :unit-price 0)))


(defn total-of-order
  [orders]
  (->> orders
       (map total-item)
       (reduce +)))

(defn total-of-user
  [orders]
  (->> orders
       (map :items)
       (map total-of-order)
       (reduce + )))

(defn quantity-of-orders-and-value-total-per-user
  [[user orders]]
  {:user-id orders
   :total-orders (count orders)
   :total-price (total-of-user orders)})



(->> (s.db/all-orders)
     (group-by :user)
     (map quantity-of-orders-and-value-total-per-user)
     println)
```
- **:required ->** first of all, to use the map and function I have to import in main file, to do this we use **:required [folder.file as f.file]** we can use **as** to abbreviate the name.

- **total-item ->** total item is a symbol to get the total of items.
- **total-of-order ->** get the total of orders. 
- **total-total-of-user ->** get the total of order of user.
- **quantity-of-orders-and-value-total-per-user ->** sum the quantity of orders and the value total per user.



### Keep
Is a sequence function that is used to apply a function in each element of a sequence and return a new sequence contains just the not null result, is a way to filter elements of a sequence with base in the function passed.

syntax
```clojure
(keep f seq)
```
- **f** is the function that will be applied in each element of sequence.
- **seq** is the sequence in the function **f** will be apllied.


The **keep** run the sequence **seq** and apply the function **f** in each element, if the result of function is not null then the element is include in the new sequence of output, if not is ignored. 
```clojure
(def numbers [1 2 3 4 5 6 7 8 9 10])

(defn double-is-even [n]
  (if (even? n)
    (* 2 n)
    nil))

(def double-numbers (keep double-is-even numbers))
```

Keep is a good toy to filter and transform the sequences keeping just elements that meets specific criteria.

##  Lazy and Eager

### Eager Evaluation:

In eager evaluation, the elements of a sequence or collection are calculated and stored immediately when the sequence is created. This means that all elements of the sequence are calculated and stored in memory right away. The advantage is that subsequent access to the sequence's elements is fast since the elements are already available. The disadvantage is that it can be inefficient when dealing with large sequences or costly operations because all elements are calculated in advance, even if not all of them are used.

### Lazy Evaluation:

In lazy evaluation, the elements of a sequence or collection are not calculated until they are actually needed. This means that elements are calculated only as they are accessed. The advantage is resource efficiency because only the necessary elements are actually calculated and stored in memory. This is particularly efficient when working with potentially infinite sequences or performing filtering, mapping, or transformation operations on large sequences. 

In Clojure, many sequence functions like map, filter, take, drop, and others return lazy sequences by default. This allows you to work with large sequences and perform transformations on them without the need to calculate all elements at once.


# Clojure: Mutability with atoms and refs

### Queue
In Clojure **queue** is a data structure that implements queues, we can create a queue using:
```clojure
(clojure.lang.PersistentQueue/EMPTY)
```
so we can do like this:
```clojure
(defn my-queue []
    (let [elmts(conj clojure.lang.PersistentQueue/Empty "1" "2")]
      (println "queue")
      (pprint elmts)))
```
So here I create an empty queue and using conj add two elements,and then print them afterwards, and we can use some operations like: conj, peek, pop...


### Atoms

Atoms provide a way to manage shared, synchronous, independent state. They are a reference type like refs and vars. You create an atom with atom, and can access its state with deref/@. Like refs and agents, atoms support validators. To change the value of an atom, you can use swap!. A lower-level compare-and-set! is also provided. Changes to atoms are always free of race condition.     ([clojure-docs](https://clojure.org/reference/atoms))

example:
```clojure
(def my-atom (atom 0))
```

- **Swap:**The swap function! in Clojure it is used to modify the value stored in an atom in a safe way in a concurrent environment. It allows you to update the value of an atom based on a function and ensures that this update is done atomicly, that is, without interference from other threads.

example:
```clojure
(swap! my-atom inc)
```


### Refs 

A Ref is a transactional reference that represents a variable whose value can be changed within a transaction. The transaction itself is a block of code that is executed atomically, that is, as a single indivisible unit. This means that if multiple transactions are happening simultaneously, they do not interfere with each other.

By using Refs, you can coordinate updates to multiple Refs within a transaction, ensuring that operations are atomic and consistent, even in a concurrent environment. This helps avoid issues like race conditions and ensure that your code maintains data integrity.

So, in summary, in Clojure, a Ref is a reference to a value that can be updated within transactions, providing a safe and effective approach to dealing with concurrency in software.

And you want use ref when you want work with two or more mutable values, 'cause refs facilitate coordination of both.

```clojure
;EXAMPLE OF DOC
; create(ref)
(def a (ref '(1 2 3)))

; read(deref)
(deref a) ; -> (1 2 3)

; rewrite(ref-set)
; (ref-set a '(3 2 1)) err!
(dosync (ref-set a '(3 2 1)))

(deref a) ; -> (3 2 1
```