(ns clojoku.lib (:gen-class)
  (:require [ clojoku.data :refer [cells] ] )
  (:require [ clojoku.data :refer [cell-map] ] )
)
(defn string-to-tuples [s] ( 
  map #(list (nth cells %) ( get cell-map ( get s % ) ) )
      (range (count cells) ) ) )

(defn is-solved [grid] (
  = (count grid) (count (take-while #(= 1 (count (second %) ) ) grid)))
)
(defn run_test []
  (list 
    (= true (is-solved (list (list "a1",(list 1)) (list "a2" (list 1 )))))
    (not= true (is-solved (list (list "a1",(list 1)) (list "a2" (list 1 2 )))))
  )
)  
