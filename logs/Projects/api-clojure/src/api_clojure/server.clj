(ns api-clojure.server (:require [io.pedestal.http :as http]
                                 [io.pedestal.http.route :as route]
                                 [io.pedestal.test :as test]
                                 [api-clojure.database :as database]))
                                

(defn funcao-hello [request]
  {:status 200 :body  (str "Hello World" (get-in request [:query-params :name]))})

(defn create-map-task [uuid name status]
  {:uuid uuid :name name :status status})

(defn create-task [request]
  (let [uuid  (java.util.UUID/randomUUID)
        name (get-in request [:query-params :name])
        status (get-in request [:query-params :status])
        task (create-map-task uuid name status)]
    (swap! database/store assoc uuid task)
    {:status 200 :body {:message "Task created with sucess"
                        :task task}}))

(defn get-tasks [request]
  {:status 200 :body database/store})



(def routes (route/expand-routes
             #{["/hello" :get funcao-hello :route-name :hello-wolrd]
               ["/tasks" :post [db-interceptor create-task]  :route-name :create-task]
               ["/tasks"  :get  get-tasks  :route-name :get-tasks]}))


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

(defn stop []
  (http/stop @server))

(defn restart []
  (stop)
  (start-server))


(start-server)
(restart)
(test-request :get "/hello")
(test-request :post "/tasks?name=Ler&status=todo")
(test-request :post "/tasks?name=Jogar&status=todo")

;list all tasks
(test-request :get "/tasks")