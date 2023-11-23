(ns simple-http.core
  (:require [org.httpkit.server :refer [run-server]]))


(defn app [req]
  {:status 200
   :headers {"Content-type" "text/html"}
   :body (str "First HTTP Server with Clojure")})

(defn -main [& args]
  (run-server app {:port 3000})
  (println "Initiate Server on Port 3000"))
