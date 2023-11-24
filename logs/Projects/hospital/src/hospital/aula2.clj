(ns hospital.aula2
  (:use [clojure pprint])
  (:require [hospital.logic :as h.logic]
            [hospital.model :as h.model]))

(defn testa-atomao []
  (let [hospital-silveira (atom {:espera h.model/queue})]
    (println hospital-silveira)
    (pprint hospital-silveira)
    (pprint (deref hospital-silveira))
    (pprint @hospital-silveira)

    (pprint (assoc @hospital-silveira :lab01 h.model/queue))
    (update @hospital-silveira :lab01 conj "111")
    (swap! hospital-silveira assoc :lab01 conj "111")
    (pprint @hospital-silveira)
    ) 
  )
   

(testa-atomao)
