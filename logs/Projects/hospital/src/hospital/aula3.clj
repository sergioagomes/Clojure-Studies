(ns hospital.aula3
  #_{:clj-kondo/ignore [:use]}
  (:use [clojure pprint])
  (:require [hospital.logic :as h.logic]
            [hospital.model :as h.model]))

(defn chega-sem-malvado! [hospital pessoa]
  (swap! hospital h.logic/chega-em :espera pessoa)
  (println "apos inserir" pessoa))

(defn simula-um-dia-em-paralelo-com-mapv
  []
  (let [hospital (atom (h.model/novo-hospital))
        pessoas ["111","222","333","444","555","666"]]

    (mapv #(.start (Thread. (fn [] (chega-sem-malvado! hospital %)))) pessoas)

    (.start (Thread. (fn [] (Thread/sleep 8000)
                       (pprint hospital))))))

(defn simula-um-dia-em-paralelo-com-mapv-refatorando
  []
  (let [hospital (atom (h.model/novo-hospital))
        pessoas ["111","222","333","444","555","666"]
        starta-thread-de-chegada #(.start (Thread. (fn [] (chega-sem-malvado! hospital %))))]

    (mapv  starta-thread-de-chegada pessoas)

    (.start (Thread. (fn [] (Thread/sleep 8000)
                       (pprint hospital))))))

(defn starta-thread-de-chegada
  [hospital pessoa]
  #(.start (Thread. (fn [] (chega-sem-malvado! hospital pessoa)))))

;doseq e dotimes
(defn simula-um-dia-em-paralelo-com-doseq
  []
  (let [hospital (atom (h.model/novo-hospital))
        pessoas ["111","222","333","444","555","666"]]

    (doseq [pessoa pessoas]
      (starta-thread-de-chegada hospital pessoa))

    (.start (Thread. (fn [] (Thread/sleep 8000)
                       (pprint hospital))))))

(defn simula-um-dia-em-paralelo-com-dotimes
  []
  (let [hospital (atom (h.model/novo-hospital))]

    (doseq [pessoa 6]
      (starta-thread-de-chegada hospital pessoa))

    (.start (Thread. (fn [] (Thread/sleep 8000)
                       (pprint hospital))))))

; sem forçar situação de retry (busy retry), pode acontecer, mas pode não acontecer
(simula-um-dia-em-paralelo-com-dotimes)
