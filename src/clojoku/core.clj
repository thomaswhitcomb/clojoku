(ns clojoku.core (:gen-class)
  (:require
    [clojoku.lib :as lib  ]
    [compojure.route :as route]
    [compojure.core :refer [defroutes,GET]]
    [compojure.handler :as handler]
    [ring.adapter.jetty :refer [run-jetty]]
  )
)
; HTTP response codes
(def http-status-ok 200)
(def http-status-created 201)
(def http-status-bad-request 400)
(def http-status-not-found 404)
(def http-status-gone 410)

(defn send-response [status content]
    {
     :status status
     :headers {"Content-Type" "text/plain"}
     :body content
    }
)

(defroutes casper-routes

  (GET "/" []
       (send-response http-status-ok
                      "CLOJOKU - Sudoku written in Clojure.  Enter a string of 81 characters ranging 0 - 9 for a sudoku board.  A zero is an empty slot.  Usage: /game/{string of 81 characters of 0-9}. Example /game/123050000450789023709003006204005097360000214090010300001002970000900530900501040"))

  (GET "/game/:s" [s]
    (if (re-find #"^[0-9]{81}$" s)
      (send-response http-status-ok
        (let [solution (lib/solve s)]
           (if (= "" solution)
             "No solution"
             solution)
        )
      )
      (send-response http-status-bad-request "Invalid game board" )
    )
  )
  (GET "/health" []
    (send-response http-status-ok "I am healthy" )
  )
  (route/not-found "Page Not Found.")
)

(def app (handler/site casper-routes))

(defn -main [& port]
    (run-jetty app {:port (Integer/parseInt (first port)) })
)
