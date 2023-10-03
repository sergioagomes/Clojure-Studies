(ns store.class1)

;def a map
(defn my-map
 [func sequence]
 (let [first (first sequence)]
 ((func first))))


(my-map println ["sergio" "Felipe" "Joel" "Helder"])


(defn my-map
  [func sequence]
  (let [first (first sequence)]
    (func first)
    (my-map func(rest sequence))))

(my-map println ["sergio" "Felipe" "Joel" "Helder"])


;function with do
(defn my-map
  [func sequence]
  (let [first (first sequence)]
    (if first
      (do
        (func first)
        (my-map func (rest sequence))))))

(my-map println ["sergio" "Felipe" "Joel" "Helder"])
(my-map println ["sergio" false "Felipe" "Joel" "Helder"])


;if is null
(defn my-map
  [func sequence]
  (let [first (first sequence)]
    (if (not(nil? first))
      (do
        (func first)
        (my-map func (rest sequence))))))

(my-map println ["sergio" "Felipe" "Joel" "Helder"])
(my-map println ["sergio" false "Felipe" "Joel" "Helder"])