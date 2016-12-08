(ns clojoku.lambda
  (:require [clojoku.lib :as lib  ])
  (:gen-class
   :methods [^:static [handler [String] String]]))

(defn -handler [s]
  (str "Clojoku - "
       (if (re-find #"^[0-9]{81}$" s)
         (let [solution (lib/solve s)]
           (if (= "" solution) "No solution" solution))
         "Invalid game board")))
