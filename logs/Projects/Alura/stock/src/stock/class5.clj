(ns stock.class5)

;Vector
(def prices [30 700 1000])

;apply reduce to sum all elements of prices
(println "vector" prices)
(println (reduce + prices))

;vector [30 700 1000]
;1730