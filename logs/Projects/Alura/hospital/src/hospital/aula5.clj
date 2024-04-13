(ns hospital.aula5
  (:use [clojure pprint])
  (:require [hospital.model :as h.model]))

(defn cabe-na-fila? [fila]
  (-> fila
      count
      (< 5)))


;; ref and ref-set and dref

(defn chega-em 
  [fila pessoa]
  (if (cabe-na-fila? fila)
    (conj fila pessoa)
    (throw (ex-info "Fila esta cheia" {:tentando-adicionar pessoa}))))

(defn chega-em! 
  "troca de referencia via ref-set"
  [hospital pessoa]
  (let [fila (get hospital :espera)]
    (ref-set fila (chega-em  @fila pessoa)))) ;@ - deref

(defn chega-em-com-alter! 
  "troca de referenvia via alter"
  [hospital pessoa]
  (let [fila (get hospital :espera)]
    (alter fila chega-em   pessoa)))


(defn simula-um-dia []
  (let [hospital {:espera (ref h.model/fila_vazia)
                  :laboratorio1 (ref h.model/fila_vazia)
                  :laboratorio2 (ref h.model/fila_vazia)
                  :laboratorio3 (ref h.model/fila_vazia)}]
    (dosync 
     (chega-em! hospital "guilherme")
     (chega-em! hospital "maria")
     (chega-em! hospital "lucia")
     (chega-em! hospital "daniela")
     (chega-em! hospital "ana"))))


;(simula-um-dia)

(defn async-chega-em!
  [hospital pessoa]
  (future
    (Thread/sleep (rand 5000))
    (dosync
     (println "Tentando o codigo sincronizado pra essa pessoa")
     (chega-em! hospital pessoa))))

(defn simula-um-dia-async
  []
  (let [hospital {:espera (ref h.model/fila_vazia)
                  :laboratorio1 (ref h.model/fila_vazia)
                  :laboratorio2 (ref h.model/fila_vazia)
                  :laboratorio3 (ref h.model/fila_vazia)}
        futures (mapv #(async-chega-em! hospital %) (range 10))]
    
    (future
      (dotimes [n 4]
      (Thread/spleep 2000)
      (pprint hospital)
      (pprint futures)))))


(simula-um-dia-async)