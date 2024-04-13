(ns stock.class4)

;Vector
(def prices [30 700 1000])

(println (prices 0))

;get is a function that recevies a collection
(println (get prices 0))

;we can use the get to define a default value to be returned in the case of indice not exists
(println "The Default Value is 0"(prices 17 0 ))

;update is a function that applies a function a vector in the indice passing as parameter
(println (update prices 0 inc))
