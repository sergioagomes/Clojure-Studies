# hospital2

## Class1

- **if-let**
```clojure
(defn adiciona-paciente
  [pacientes paciente]
  (if-let [id (:id paciente)]
   (assoc pacientes id paciente)
    (throw (ex-info "Paciente nao possui id" {:paciente paciente}))))
```

- **defrecord**
```clojure
(defrecord Paciente [id nome nascimento])

(println (->Paciente 15 "Guilherme" "18/9/1981"))
```