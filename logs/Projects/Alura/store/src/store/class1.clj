(ns store.class1)

;do a simple version of map function
;
(defn my-map
  [function sequence]
  (let [primeiro (first sequence)]
    (function primeiro)))

(my-map println ["sergio" "Felipe" "Joel" "Helder"])

;map that shows the rest, but stay in infinite loop
(defn my-map
  [func sequence]
  (let [firsts (first sequence)]
    (func firsts)
    (my-map func (rest sequence))))

(my-map println ["sergio" "Felipe" "Joel" "Helder"])


;map with recursion but not stay in infinite loop
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
    (if (not (nil? first))
      (do
        (func first)
        (my-map func (rest sequence))))))

(my-map println ["sergio" "Felipe" "Joel" "Helder"])
(my-map println ["sergio" false "Felipe" "Joel" "Helder"])