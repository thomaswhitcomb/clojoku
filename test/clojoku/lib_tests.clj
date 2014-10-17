(ns clojoku.lib-tests (:gen-class)
  (:require [clojure.test :refer :all] )
  (:require [ clojoku.data :refer [cells] ] )
  (:require [ clojoku.data :refer [cell-map] ] )
  (:require [ clojoku.data :refer [peers] ] )
  (:require [ clojoku.lib :refer [solved?] ] )
  (:require [ clojoku.lib :refer [conflict?] ] )
  (:require [ clojoku.lib :refer [consistent?] ] )
  (:require [ clojoku.lib :refer [string-to-grid] ] )
  (:require [ clojoku.lib :refer [reduce-domain] ] )
)

(def allzero-grid "000000000000000000000000000000000000000000000000000000000000000000000000000000000")
(def grid0 "900705003600000080004600000006080120040301070071060500000004800010000002400902001")
(def grid1 "600000084003060000001000502100074000720906035000320008305000200000050900240000007")
(def grid2 "100007090030020008009600500005300900010080002600004000300000010040000007007000300")
(def consistent-grid "123456789456789123789123456214365897365897214897214365531642978642978531978531642")
(def nearly-consistent-grid "123056789456789123789123456214365897365897214897214365531642978642978531978531642")
(def inconsistent-grid "123056789456789123789123456214365897365897214897214365531642978642978531978521642")

(deftest solved?-tests
    (is (= true (solved? (string-to-grid consistent-grid))))
    (is (not= true (solved? (string-to-grid inconsistent-grid))))
)

(deftest  consistent?-tests
  (is (= true (consistent? (string-to-grid "123" ))))
  (is (= true (consistent? (string-to-grid consistent-grid ))))
  (is (not= true (consistent? (string-to-grid "1231" ))))
  (is (not= true (consistent? (string-to-grid inconsistent-grid ))))
)

(deftest conflict?-tests
  (is (= true (conflict? (string-to-grid "123") "a2" (set '(\1)))))
  (is (= true (conflict? (string-to-grid consistent-grid) "a2" (set '(\1)))))
  (is (= false (conflict? (string-to-grid "123") "a2" (set '(\2)))))
  (is (= false (conflict? (string-to-grid "123") "a2" (set '(\4)))))
)

(deftest reduce-domain-tests
  (is (= #{\2 \3 \4 \5 \6 \7 \8 \9} ((reduce-domain (string-to-grid allzero-grid) \1 (peers "a1")) "b1")))
  (is (= #{\2 \3 \4 \5 \6 \7 \8 \9} ((reduce-domain (string-to-grid allzero-grid) \1 (peers "a1")) "c1")))       
  (is (= #{\2 \3 \4 \5 \6 \7 \8 \9} ((reduce-domain (string-to-grid allzero-grid) \1 (peers "a1")) "d1")))      
  (is (= #{\2 \3 \4 \5 \6 \7 \8 \9} ((reduce-domain (string-to-grid allzero-grid) \1 (peers "a1")) "e1")))     
  (is (= #{\2 \3 \4 \5 \6 \7 \8 \9} ((reduce-domain (string-to-grid allzero-grid) \1 (peers "a1")) "f1")))       
  (is (= #{\2 \3 \4 \5 \6 \7 \8 \9} ((reduce-domain (string-to-grid allzero-grid) \1 (peers "a1")) "g1")))    
  (is (= #{\2 \3 \4 \5 \6 \7 \8 \9} ((reduce-domain (string-to-grid allzero-grid) \1 (peers "a1")) "h1")))   
  (is (= #{\2 \3 \4 \5 \6 \7 \8 \9} ((reduce-domain (string-to-grid allzero-grid) \1 (peers "a1")) "i1")))  
  (is (= #{\2 \3 \4 \5 \6 \7 \8 \9} ((reduce-domain (string-to-grid allzero-grid) \1 (peers "a1")) "a2"))) 
  (is (= #{\2 \3 \4 \5 \6 \7 \8 \9} ((reduce-domain (string-to-grid allzero-grid) \1 (peers "a1")) "a3")))
  (is (= #{\2 \3 \4 \5 \6 \7 \8 \9} ((reduce-domain (string-to-grid allzero-grid) \1 (peers "a1")) "a4")))
  (is (= #{\2 \3 \4 \5 \6 \7 \8 \9} ((reduce-domain (string-to-grid allzero-grid) \1 (peers "a1")) "a5")))
  (is (= #{\2 \3 \4 \5 \6 \7 \8 \9} ((reduce-domain (string-to-grid allzero-grid) \1 (peers "a1")) "a6")))
  (is (= #{\2 \3 \4 \5 \6 \7 \8 \9} ((reduce-domain (string-to-grid allzero-grid) \1 (peers "a1")) "a7")))
  (is (= #{\2 \3 \4 \5 \6 \7 \8 \9} ((reduce-domain (string-to-grid allzero-grid) \1 (peers "a1")) "a8")))
  (is (= #{\2 \3 \4 \5 \6 \7 \8 \9} ((reduce-domain (string-to-grid allzero-grid) \1 (peers "a1")) "a9")))
  (is (= #{\2 \3 \4 \5 \6 \7 \8 \9} ((reduce-domain (string-to-grid allzero-grid) \1 (peers "a1")) "b2")))
  (is (= #{\2 \3 \4 \5 \6 \7 \8 \9} ((reduce-domain (string-to-grid allzero-grid) \1 (peers "a1")) "b3")))
  (is (= #{\2 \3 \4 \5 \6 \7 \8 \9} ((reduce-domain (string-to-grid allzero-grid) \1 (peers "a1")) "c2")))
  (is (= #{\2 \3 \4 \5 \6 \7 \8 \9} ((reduce-domain (string-to-grid allzero-grid) \1 (peers "a1")) "c3")))
)
(deftest reduce-domain-tests-2       
  (is (= #{\3 \4 \5 \6 \7 \8 \9} ((reduce-domain (assoc (string-to-grid allzero-grid) "b2" #{\1 \2}) \1 (peers "a1")) "c2")))
  (is (= #{\1 \3 \4 \5 \6 \7 \8 \9} ((reduce-domain (assoc (string-to-grid allzero-grid) "b2" #{\1 \2}) \1 (peers "a1")) "b4")))
  (is (= #{\1 \3 \4 \5 \6 \7 \8 \9} ((reduce-domain (assoc (string-to-grid allzero-grid) "b2" #{\1 \2}) \1 (peers "a1")) "b7")))
)
(deftest reduce-domain-tests-3       
  (is (= #{\4 \5 \6 \7 \8 \9} ((reduce-domain (assoc (string-to-grid allzero-grid) "b2" #{\1 \2} "c2" #{\1 \2 \3}) \1 (peers "a1")) "a2")))
  (is (= #{\1 \4 \5 \6 \7 \8 \9} ((reduce-domain (assoc (string-to-grid allzero-grid) "b2" #{\1 \2} "c2" #{\1 \2 \3}) \1 (peers "a1")) "h2")))
  (is (= #{\1 \2 \4 \5 \6 \7 \8 \9} ((reduce-domain (assoc (string-to-grid allzero-grid) "b2" #{\1 \2} "c2" #{\1 \2 \3}) \1 (peers "a1")) "c7")))
)
