(ns stock.class2)

(defn welcome-message []
  (println "-------------------------")
  (println "Welcome to the stock system"))



(defn apply-discount
  [total-value]
  (if (> total-value 100)
    (let  [discount-rate (/ 10 100)
           discount (* total-value discount-rate)]
      (println "Calc the discount" discount)
      (- total-value discount))
    total-value))