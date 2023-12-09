(ns api-clojure.server (:require [io.pedestal.http.route :as route]
                                  [io.pedestal.http :as http]
                                   [io.pedestal.test :as test]))
                       
(defn funcao-hello [request]
     {:status 200 :body  (str "Hello World" (get-in request [:query-params :name]))})
                       
(def routes (route/expand-routes
             #{["/hello" :get funcao-hello :route-name :hello-wolrd]}))
                      
(def service-map {::http/routes routes
                  ::http/port 8080
                  ::http/type :jetty
                  ::http/join? false})
                       
;defines atom to test the application
(def server (atom nil))

(defn start-server []
  (reset! server (http/start (http/create-server service-map))))

(defn test-request [verb url]
  (test/response-for (::http/service-fn @server) verb url))


(start-server)
(test-request :get "/hello")
