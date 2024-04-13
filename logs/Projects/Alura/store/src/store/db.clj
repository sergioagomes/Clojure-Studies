(ns store.db)


(def order1 {:user 1
             :items  {:bag {:id :bag :amount 2 :unit-price 80}
                     :shirt {:id :shirt :amount 3 :unit-price 40}
                     :shoes {:id :shoes :amount 1}}})

(def order2 {:user 12
             :items  {:bag {:id :bag :amount 2 :unit-price 80}
                      :shirt {:id :shirt :amount 3 :unit-price 40}
                      :shoes {:id :shoes :amount 1}}})
(def order3 {:user 12
             :items  {:bag {:id :bag :amount 2 :unit-price 80}
                      :shirt {:id :shirt :amount 3 :unit-price 40}
                      :shoes {:id :shoes :amount 1}}})



(defn all-orders []
   [order1, order2, order3])