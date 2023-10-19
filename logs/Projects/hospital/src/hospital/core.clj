(ns hospital.core
  (:use [clojure pprint])
  (:require [hospital.model :as h.model]))


(let [hospital-punk (h.model/new-hospital)]
  (pprint hospital-punk))

(pprint h.model/queue)