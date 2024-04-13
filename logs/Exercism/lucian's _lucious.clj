;Define the expected-time var to check how many minutes the lasagna should be 
;in the oven. According to the cooking book, the expected oven time in minutes is 40:

(def expected-time 40)

(defn remaining-time
  "Takes the actual time in minutes the lasagna has been in the oven,
   and returns how many minutes the lasagna still has to remain in the oven."
  [actual-time]
  ;; Define the function body here
  (-  expected-time actual-time))

(defn prep-time
  "Takes the number of layers added to the lasagna,
   and returns how many minutes you spent preparing the lasagna"
  [num-layers]
  (* 2 num-layers))

(defn total-time
  "Takes the number of layers of lasagna and the actual time in minutes it has been in the oven.
   Returns how many minutes in total you've worked on cooking the lasagna"
  [num-layers actual-time]
  (+ (prep-time num-layers) actual-time))