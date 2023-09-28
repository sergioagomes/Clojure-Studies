(ns stock.class6)


;this is map/dictionarie definition with hashmap
(def stock {"book1" 10 "book2" 5})
(println stock)

;other form to declare this dictionarie is using the keyword : 
(def  stock {:book1 10
              :book2 5})

;with we want add other book we can use the word assoc, passing an arg 
(println (assoc stock :book3 20))

;but remember this don't affect the real stock, because of immutabillity


;lets define a new map 
(def order {:bag {:amount 2, :price 80}
            :tshirt {:amount 3, :price 40}})

;output {:bag{:amount 2, :price 80}, :tshirt {:amount 3, :price 40}}
