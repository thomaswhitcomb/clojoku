(ns clojoku.lib-tests (:gen-class)
  (:require [clojure.test :refer :all] )
  (:require [ clojoku.data :refer [cells] ] )
  (:require [ clojoku.data :refer [cell-map] ] )
  (:require [ clojoku.lib :refer [is-solved] ] )
)

(deftest is-solved-tests
    (is (= true (is-solved (list (list "a1",(list 1)) (list "a2" (list 1 ))))))
    (is (not= true (is-solved (list (list "a1",(list 1)) (list "a2" (list 1 2 ))))))
)
