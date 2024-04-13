(ns stock.class7)


;(def order {:bag {:amount 2, :price 80}
 ;           :tshirt {:amount 3, :price 40}})

;(defn print-value [[key value]]
 ; value)

;(println (map print-value order))

;destruct
; _, underscore is used when we don't use the parameter
;(defn price-per-product [[_ value]]
 ; (*(:amount value)(:price value)))

;(println price-per-product(map price-per-product order))
;(println (reduce + (map price-per-product order)))


;;threading last
;(defn order-total 
 ; [order]
  ;(->> order
   ;   (map price-per-product )
    ;   (reduce + ,,,)))
  
;(print (order-total order))


;; using filter with hashmap 
(def order {:bag {:amount 2, :price 80}
            :tshirt {:amount 3, :price 40}
            :keys {:amount 3}})

(defn is-free?
  [[key item]]
  (<= (get item :price 0) 0))

(println (filter is-free? order))

(defn paid?
  [item]
  (not(is-free? item)))

(print (paid? {:price 50}))
;; true -> output
(print (paid? {:price 0}))
;; false -> output

;; other way to define paid?
(def paid? (comp not is-free?))
(println (paid? {:preco 50}))
(println (paid? {:preco 0}))