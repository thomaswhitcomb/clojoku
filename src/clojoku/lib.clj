(ns clojoku.lib (:gen-class)
  (:require [ clojoku.data :as data ] )
)
(defn string-to-grid [s] 
  (
   let [r (range (count s))]
   (zipmap (map #(nth data/cells %) r) (map #(get data/cell-map (get s %)) r))
  )
)

(defn consistent? [grid] 
  (let [
        unitlist-unified-to-grid (for [unit data/unitlist] (map #(get grid % %) unit))
        fully-constrained-unitlist (for [unit unitlist-unified-to-grid] (filter #(= (count %) 1) unit ))
        non-conflicting-unitlist (filter #(if (empty? %) true (apply distinct? %)) fully-constrained-unitlist)
       ]
    (= (count non-conflicting-unitlist) (count fully-constrained-unitlist))
  )
) 
(defn solved? [grid] 
  (let [ 
         values (vals grid)
         list-with-multi-cardinality (drop-while #(= (count %) 1) values)
       ]
    (cond
      (= (count list-with-multi-cardinality) 0) true
      :else false
    )  
  )
)  
(defn conflict? [grid cell domain] 
  (let [ ps (data/peers cell) ]
    (cond 
      (some #(= (get grid % ) domain) ps) true
      :else  false
    )
  )  
)

(defn count-0 [sequ] (= 0 (count sequ)))

(defn reduce-domain [grid domain peers]
  (let [peer (first peers) current-domain (grid peer) ]
    ;(println peer " pushing " domain " diff is "  (disj current-domain domain))
    (cond
      (count-0 peers) grid
      (contains? current-domain domain) 
        (let [
              diff (disj current-domain domain)
              g (reduce-domain (assoc grid peer diff) domain (rest peers))
             ]
        (cond
          (= (count diff) 1) (reduce-domain g (first diff) (data/peers peer))
          :else g
        )
      )
      :else (reduce-domain grid domain (rest peers))
    )
  )    
)  

(defn backtrack [grid unresolved]
  (let [
        solved (solved? grid)
        consistent (consistent? grid)
       ] 
  (cond 
    (and solved consistent) grid
    (count-0 unresolved) {}
    :else 
      (cond 
        (consistent) {}
        :else {} 
      )
  )  
  )  
)
(defn solve [string]
  (let [
    grid (string-to-grid string)
    unresolved (select-keys grid (filter #(not= 1 (count (grid %)) ) (keys grid)))
   ]
   unresolved
  )
)
