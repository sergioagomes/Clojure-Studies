(ns store.class4
  (:require [store.db :as s.db]))


(println (s.db/all-orders))

(println (group-by :user (s.db/all-orders)))


(defn group-by-function 
  [element]
  (println "element" element)
  (:user element))

(println (group-by group-by-function (s.db/all-orders)))

(println (map count (vals (group-by :user (s.db/all-orders)))))

(defn orders-total-per-user
  [[user order]]
  {:user-id user
   :order-total (count order)})


  
;do a threading last
(->> (s.db/all-orders)
     (group-by :user)
     (map orders-total-per-user)
     println)
 
;we can do like this too
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

(defn quantia-de-pedidos-e-gasto-total-por-usuario
  [[user orders]]
  {:user-id orders
   :total-orders (count orders)
   :total-price (total-of-user orders)})



(->> (s.db/all-orders)
     (group-by :user)
     (map quantia-de-pedidos-e-gasto-total-por-usuario)
     println)