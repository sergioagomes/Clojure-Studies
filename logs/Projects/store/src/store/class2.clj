(ns store.class2)

;class about tail recursion

(defn my-map
  [func sequence]
  (let [first (first sequence)]
    (if (not (nil? first))
      (do
        (func first)
        (my-map func (rest sequence))))))

;(my-map println (range 1000))


;this is a tail recursion
(defn my-map
  [func sequence]
  (let [first (first sequence)]
    (if (not (nil? first))
      (do
        (func first)
        (recur func (rest sequence))))))

