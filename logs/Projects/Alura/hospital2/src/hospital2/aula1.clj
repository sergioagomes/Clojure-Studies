(ns hospital2.aula1 
  (:require [clojure.pprint :as pprint]))


;paciente {15 {paciente 15}}

(defn adiciona-paciente
  [pacientes paciente]
  (if-let [id (:id paciente)]
   (assoc pacientes id paciente)
    (throw (ex-info "Paciente nao possui id" {:paciente paciente}))))


(defn teste-uso-de-pacientes 
  []
  (let [pacientes {}
        guilherme {:id 15 :nome "Guilhermer" :nascimento "18/09/1981"}
        daniela {:id 20 :nome "Daniela" :nascimento "18/09/1982"}
        paulo {:id 30 :nome "Paulo" :nascimento "18/07/1981"}]
    
    (pprint/pprint (adiciona-paciente pacientes guilherme))
    (pprint/pprint (adiciona-paciente pacientes daniela))
    (pprint/pprint (adiciona-paciente pacientes paulo))
    ))


;(tete-uso-de-pacientes)
; works like hashmap
(defrecord Paciente [id nome nascimento])

(->Paciente 15 "Guilherme" "18/09/1981")

;construtor e feito pelo nome da class e .
(Paciente. 15 "Guilherme" "18/09/1981")

;passando um mapa
(map->Paciente{:id 15 :nome "Guilherme" :nascimento "18/09/1981"})