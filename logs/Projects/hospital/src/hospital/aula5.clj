(ns hospital.aula5
  (:use [clojure pprint])
  (:require [hospital.model :as h.model]))

;; ref and ref-set and dref

(defn chega-em [fila pessoa]
  (conj fila pessoa))

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
    (dosync (chega-em! hospital "guilherme"))))


(simula-um-dia)