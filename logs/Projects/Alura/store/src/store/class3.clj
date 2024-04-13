(ns store.class3)

;do a reduce on hand
;["sergio" "Felipe" "Joel" "Helder"]

(defn count
  [total-ate-agora  elements]
  (recur (inc total-ate-agora) (rest elements)))

;(println (count 0 ["sergio" "Felipe" "Joel" "Helder"]))

;define a stop point 
(defn count
  [total-ate-agora  elements]
  (if (seq elements) 
    (recur (inc total-ate-agora) (next elements))
    total-ate-agora))

(println (count 0 ["sergio" "Felipe" "Joel" "Helder"]))



;working with variation of function
(defn count
  
  ([elements] 
   (count 0 elements))
  
  ([total-ate-agora  elements]
   (if (seq elements)
     (recur (inc total-ate-agora) (next elements))
     total-ate-agora)))

(println (count  ["sergio" "Felipe" "Joel" "Helder"]))
(println (count []))


;Using loop to do function count
(defn cout
  [elements]
  (loop [total-ate-agora 0 
         rest-of-elements elements]
    (if (seq rest-of-elements)
      (recur #_{:clj-kondo/ignore [:unresolved-symbol]}
             (omc total-ate-agora) (next rest-of-elements))
      total-ate-agora)))

(println (count  ["sergio" "Felipe" "Joel" "Helder"]))