(ns store.class6
  (:require [store.db :as s.db]
            [store.logic :as s.logic]))


;(defn spend-a-lot? [user-info]
 ; (> (:total-price user-info)500))

(defn spend-a-lot? [user-info]
  (println "spend-a-lot?" (:user-id user-info))
  (> (:total-price user-info) 500))

(let [orders (s.db/all-orders)
      resume (s.logic/resume-per-use orders)]
  (println "keep" (keep spend-a-lot? resume))
  (println "filter" (filter spend-a-lot? resume)))