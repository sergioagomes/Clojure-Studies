(ns hospital.model)

(def queue clojure.lang.PersistentQueue/EMPTY)

(defn new-hospital []
  {:espera queue
   :lab01 queue
   :lab02 queue
   :lab03 queue})