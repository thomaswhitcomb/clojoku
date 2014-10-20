(ns clojoku.data (:gen-class))

;(def rows-in-quadrant 2)
;(def cols-in-quadrant 3)
(def rows-in-quadrant 3)
(def cols-in-quadrant 3)

(def unit-size (* rows-in-quadrant cols-in-quadrant))


(defn member [x l] (some #(= x %) l))

(defn generate-sequence [c] 
  (let [n (int c)]
    (map char (range n (+ n unit-size)))
  )  
)

(def col-ids (range 1 (+ unit-size 1)))
(def row-ids (generate-sequence \a))

(def row-partitions (partition rows-in-quadrant row-ids))
(def col-partitions (partition cols-in-quadrant col-ids))

(def cell-map 
  (apply 
    hash-map 
      (concat (list nil (apply sorted-set col-ids) \0 (apply sorted-set col-ids)) 
              (apply concat (map #(list (get (str %) 0) (set (list %))) col-ids) )
      ) 
  ) 
) 

(defn cross [l1 l2] (for [x l1 y l2] (str x y ) ) )

(def cells (cross row-ids col-ids) )

(def unitlist 
  (concat
    (for [c col-ids] (cross row-ids (list c)))
    (for [r row-ids] (cross (list r) col-ids))
    (for [rs row-partitions cs col-partitions] (cross rs cs))
  )  
)

(def units
  (apply hash-map
    (apply concat
      ( for [ s cells ] 
        (list s (for [u unitlist :when ( member s u ) ]  u ) ) 
      )
    )       
  )       
)  

(defn peers-as-list-of-tuples [] 
  (for [ s units ] 
    ( list (first s) (filter #( not= (first s) %  ) (distinct (flatten (second s) ) ) ) )
  )
)

(def peers (apply hash-map (apply concat (peers-as-list-of-tuples))))
