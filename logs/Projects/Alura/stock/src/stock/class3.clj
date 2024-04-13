(ns stock.class3)

;PREDICATE
(defn it's-than-100?
  [total-value]
  (> total-value 100))

(defn discount
  "Return the value with discount of 10% if the total-value is greater than 100."
  [apply?  total-value]
  (if (apply? total-value)
    (let  [discount-rate (/ 10 100)
           discount (* total-value discount-rate)]
      (- total-value discount))
    total-value))

; Function as parameter
(println (discount it's-than-100? 1000))
(println (discount it's-than-100? 100))

; Lambda or Anonymous function

(println (discount (fn [total-value] (> total-value 100)) 1000))
(println (discount (fn [total-value] (> total-value 100))100))