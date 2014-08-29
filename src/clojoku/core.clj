(ns clojoku.core (:gen-class)
  (:require [ clojoku.data :refer [peers] ] )
  ;(:require [ clojoku.clojoku ] )
  ;(:use [ clojoku.clojoku :only [peers] ] )
)

(defn -main
  [& args]
  (println peers) )

(defn handler [request]
    {
     :status 200
     :headers {"Content-Type" "text/html"}
     :body "Hello World"
     }
)
