(ns store.class5
  (:require [store.db :as s.db]
            [store.logic :as s.logic]))


(let [orders (s.db/all-orders)
      resume (s.logic/resume-per-use orders)]
  (println "Resume" resume)
  (println "Order" (sort-by :total-price resume))
  (println "Reverse Order" (reverse (sort-by :total-price resume)))
  (println "Order By Id" (sort-by :user-id resume))
  
  ;get-in update-in assoc-in
  (println (get-in orders [0 :items :bag :amount]))
  )


(defn resume-per-user-orderned [orders]
  (->> orders
       s.logic/resume-per-user
       (sort-by :total-price)
       reverse))

(let [orders (s.db/all-orders)
      resume (resume-per-user-orderned orders)]
  (println "Resume" resume))