(ns clojoku.data-tests (:gen-class)
  (:require [clojure.test :refer :all] )
  (:require [ clojoku.data :refer [cells] ] )
  (:require [ clojoku.data :refer [unitlist] ] )
  (:require [ clojoku.data :refer [units] ] )
  (:require [ clojoku.data :refer [peers] ] )
  (:require [ clojoku.data :refer [cell-map] ] )
)

(deftest cells-length
    (is (= 81 (count cells)))
)

(deftest unitlist-length
    (is (= 27 (count unitlist)))
)
(deftest unitlist-tests
  (is (= (first unitlist) 
         (list "a1" "b1" "c1" "d1" "e1" "f1" "g1" "h1" "i1")))
  (is (= (nth unitlist 6) 
         (list "a7" "b7" "c7" "d7" "e7" "f7" "g7" "h7" "i7")))
  (is (= (nth unitlist 11) 
         (list "c1" "c2" "c3" "c4" "c5" "c6" "c7" "c8" "c9")))
  (is (= (nth unitlist 13) 
         (list "e1" "e2" "e3" "e4" "e5" "e6" "e7" "e8" "e9")))
  (is (= (nth unitlist 20) 
         (list "a7" "a8" "a9" "b7" "b8" "b9" "c7" "c8" "c9")))
  (is (= (nth unitlist 26) 
         (list "g7" "g8" "g9" "h7" "h8" "h9" "i7" "i8" "i9")))

)
(deftest units-tests
  (is (= (get units "c2") 
         (list (list "a2" "b2" "c2" "d2" "e2" "f2" "g2" "h2" "i2") 
         (list "c1" "c2" "c3" "c4" "c5" "c6" "c7" "c8" "c9") 
         (list "a1" "a2" "a3" "b1" "b2" "b3" "c1" "c2" "c3"))))
  (is (= (get units "i8") 
         (list (list "a8" "b8" "c8" "d8" "e8" "f8" "g8" "h8" "i8") 
         (list "i1" "i2" "i3" "i4" "i5" "i6" "i7" "i8" "i9") 
         (list "g7" "g8" "g9" "h7" "h8" "h9" "i7" "i8" "i9"))))
)

(deftest peers-length
  (is (= 20 (count (get peers "c2")) ))
)

(deftest peers-tests
  (is (= (get peers "c2") 
         (list "a2" "b2" "d2" "e2" "f2" "g2" "h2" "i2" "c1" "c3" "c4" "c5" "c6" "c7" "c8" "c9" "a1" "a3" "b1" "b3")))
)

