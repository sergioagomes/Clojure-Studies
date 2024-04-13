(ns hospital.collections 
  (:use [clojure pprint]))


(defn my-queue []
  (let [queue (conj clojure.lang.PersistentQueue/EMPTY "1" "2")]
    (println "The Queue")
    (pprint queue)))

(my-queue)