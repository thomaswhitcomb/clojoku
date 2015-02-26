(ns clojoku.lib-tests (:gen-class)
  (:require [clojure.test :refer :all] )
  (:require [ clojoku.data :as data ] )
  (:require [ clojoku.lib :as lib ] )
)

(def allzero-grid "000000000000000000000000000000000000000000000000000000000000000000000000000000000")
(def consistent-grid "123456789456789123789123456214365897365897214897214365531642978642978531978531642")
(def canbe-consistent-grid "123050000450789023709003006204005097360000214090010300001002970000900530900501040")
(def inconsistent-grid "123056789456789123789123456214365897365897214897214365531642978642978531978521642")
(def hardest-grid      "800000000003600000070090200050007000000045700000100030001000068008500010090000400")

(deftest solved?-tests
    (is (= true (lib/solved? (lib/string-to-grid consistent-grid))))
    (is (not= true (lib/solved? (lib/string-to-grid inconsistent-grid))))
)

(deftest  consistent?-tests
  (is (= true (lib/consistent? (lib/string-to-grid "123" ))))
  (is (= true (lib/consistent? (lib/string-to-grid consistent-grid ))))
  (is (not= true (lib/consistent? (lib/string-to-grid "1231" ))))
  (is (not= true (lib/consistent? (lib/string-to-grid inconsistent-grid ))))
)

(deftest conflict?-tests
  (is (= true (lib/conflict? (lib/string-to-grid "123") "a2" (set '(1)))))
  (is (= true (lib/conflict? (lib/string-to-grid consistent-grid) "a2" (set '(1)))))
  (is (= false (lib/conflict? (lib/string-to-grid "123") "a2" (set '(2)))))
  (is (= false (lib/conflict? (lib/string-to-grid "123") "a2" (set '(4)))))
)

(deftest reduce-domain-tests
  (is (= #{2 3 4 5 6 7 8 9} ((lib/reduce-domain (lib/string-to-grid allzero-grid) 1 (data/peers "a1")) "b1")))
  (is (= #{2 3 4 5 6 7 8 9} ((lib/reduce-domain (lib/string-to-grid allzero-grid) 1 (data/peers "a1")) "c1")))       
  (is (= #{2 3 4 5 6 7 8 9} ((lib/reduce-domain (lib/string-to-grid allzero-grid) 1 (data/peers "a1")) "d1")))      
  (is (= #{2 3 4 5 6 7 8 9} ((lib/reduce-domain (lib/string-to-grid allzero-grid) 1 (data/peers "a1")) "e1")))     
  (is (= #{2 3 4 5 6 7 8 9} ((lib/reduce-domain (lib/string-to-grid allzero-grid) 1 (data/peers "a1")) "f1")))       
  (is (= #{2 3 4 5 6 7 8 9} ((lib/reduce-domain (lib/string-to-grid allzero-grid) 1 (data/peers "a1")) "g1")))    
  (is (= #{2 3 4 5 6 7 8 9} ((lib/reduce-domain (lib/string-to-grid allzero-grid) 1 (data/peers "a1")) "h1")))   
  (is (= #{2 3 4 5 6 7 8 9} ((lib/reduce-domain (lib/string-to-grid allzero-grid) 1 (data/peers "a1")) "i1")))  
  (is (= #{2 3 4 5 6 7 8 9} ((lib/reduce-domain (lib/string-to-grid allzero-grid) 1 (data/peers "a1")) "a2"))) 
  (is (= #{2 3 4 5 6 7 8 9} ((lib/reduce-domain (lib/string-to-grid allzero-grid) 1 (data/peers "a1")) "a3")))
  (is (= #{2 3 4 5 6 7 8 9} ((lib/reduce-domain (lib/string-to-grid allzero-grid) 1 (data/peers "a1")) "a4")))
  (is (= #{2 3 4 5 6 7 8 9} ((lib/reduce-domain (lib/string-to-grid allzero-grid) 1 (data/peers "a1")) "a5")))
  (is (= #{2 3 4 5 6 7 8 9} ((lib/reduce-domain (lib/string-to-grid allzero-grid) 1 (data/peers "a1")) "a6")))
  (is (= #{2 3 4 5 6 7 8 9} ((lib/reduce-domain (lib/string-to-grid allzero-grid) 1 (data/peers "a1")) "a7")))
  (is (= #{2 3 4 5 6 7 8 9} ((lib/reduce-domain (lib/string-to-grid allzero-grid) 1 (data/peers "a1")) "a8")))
  (is (= #{2 3 4 5 6 7 8 9} ((lib/reduce-domain (lib/string-to-grid allzero-grid) 1 (data/peers "a1")) "a9")))
  (is (= #{2 3 4 5 6 7 8 9} ((lib/reduce-domain (lib/string-to-grid allzero-grid) 1 (data/peers "a1")) "b2")))
  (is (= #{2 3 4 5 6 7 8 9} ((lib/reduce-domain (lib/string-to-grid allzero-grid) 1 (data/peers "a1")) "b3")))
  (is (= #{2 3 4 5 6 7 8 9} ((lib/reduce-domain (lib/string-to-grid allzero-grid) 1 (data/peers "a1")) "c2")))
  (is (= #{2 3 4 5 6 7 8 9} ((lib/reduce-domain (lib/string-to-grid allzero-grid) 1 (data/peers "a1")) "c3")))
)
(deftest reduce-domain-tests-2       
  (is (= #{3 4 5 6 7 8 9} ((lib/reduce-domain (assoc (lib/string-to-grid allzero-grid) "b2" #{1 2}) 1 (data/peers "a1")) "c2")))
  (is (= #{1 3 4 5 6 7 8 9} ((lib/reduce-domain (assoc (lib/string-to-grid allzero-grid) "b2" #{1 2}) 1 (data/peers "a1")) "b4")))
  (is (= #{1 3 4 5 6 7 8 9} ((lib/reduce-domain (assoc (lib/string-to-grid allzero-grid) "b2" #{1 2}) 1 (data/peers "a1")) "b7")))
)
(deftest reduce-domain-tests-3       
  (is (= #{4 5 6 7 8 9} ((lib/reduce-domain (assoc (lib/string-to-grid allzero-grid) "b2" #{1 2} "c2" #{1 2 3}) 1 (data/peers "a1")) "a2")))
  (is (= #{1 4 5 6 7 8 9} ((lib/reduce-domain (assoc (lib/string-to-grid allzero-grid) "b2" #{1 2} "c2" #{1 2 3}) 1 (data/peers "a1")) "h2")))
  (is (= #{1 2 4 5 6 7 8 9} ((lib/reduce-domain (assoc (lib/string-to-grid allzero-grid) "b2" #{1 2} "c2" #{1 2 3}) 1 (data/peers "a1")) "c7")))
)
(deftest  assign-test
  (is (= #{1} ((lib/assign (lib/string-to-grid allzero-grid) "a1" 1) "a1")))
  (is (= #{2 3 4 5 6 7 8 9} ((lib/assign (lib/string-to-grid allzero-grid) "a1" 1) "a2")))
)

(deftest backtrack-test-1
  (def grid (lib/string-to-grid canbe-consistent-grid))
  (is (= "123456789456789123789123456214365897365897214897214365531642978642978531978531642" (lib/grid-to-string (lib/backtrack grid (lib/get-unresolved grid)))))
)         
(deftest backtrack-test-2
  (def grid1 (lib/string-to-grid allzero-grid))
  (is (= "123456789456789123789123456214365897365897214897214365531642978642978531978531642" (lib/grid-to-string (lib/backtrack grid1 (sort (lib/get-unresolved grid1))))))
)  

(deftest solve-test-1
  (is (= "123456789456789123789123456214365897365897214897214365531642978642978531978531642" (lib/solve allzero-grid)))
)         
(deftest solve-test-2
  (is (= "812753649943682175675491283154237896369845721287169534521974368438526917796318452" (lib/solve hardest-grid)))
)         
(deftest solve-test-3
  (is (= "493817562852649731671352498916485273284731659735926814168594327549273186327168945" 
  (lib/solve "003010002850649700070002000016080070204701609030020810000500020009273086300060900")))
)         
(deftest solve-test-4
  (is (= "128467395659312748374958261235891476417623859986745123561234987743589612892176534" 
  (lib/solve "020007000609000008000950200035000070407000809080000120001034000700000602000100030")))
)         
(deftest solve-test-5
  (is (= "652719384483265791971438562138574629724986135569321478395847216817652943246193857" 
  (lib/solve "600000084003060000001000502100074000720906035000320008305000200000050900240000007")))
)         
(deftest solve-test-6
  (is (= "162857493534129678789643521475312986913586742628794135356478219241935867897261354" 
  (lib/solve "100007090030020008009600500005300900010080002600004000300000010040000007007000300")))
)         
(deftest solve-test-7
  (is (= "124356789357982461896471253738264195241539876965817324682145937519723648473698512" 
  (lib/solve "000000000300000001000001000000264105000000000000000000000100000019020008003090000")))
)         
(deftest solve-test-8
  (is (= "382497516417256983569831472845679321296143758173528694624715839738962145951384267"
  (lib/solve "000090006007200080060001400040009300200040008003500090004700030030002100900080000")))
)         
(deftest solve-test-9
  (is (= "946137852253849617817652943574916328628473195391528476739281564462395781185764239"
  (lib/solve "000107002003000000800000943000900000008003000001520076000001060400390000000704200")))
)         
