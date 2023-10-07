(ns store.logic)


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
       (reduce +)))

(defn quantity-of-orders-and-value-total-per-user
  [[user orders]]
  {:user-id orders
   :total-orders (count orders)
   :total-price (total-of-user orders)})


(defn resume-per-user [orders]
  (->> orders
   (group-by :user)
   (map quantity-of-orders-and-value-total-per-user)))
