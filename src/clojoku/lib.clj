(ns clojoku.lib (:gen-class)
  (:require [ clojoku.data :as data ]))
(defn string-to-grid [s]
  (let [r (range (count s))]
   (zipmap
     (map #(nth data/cells %) r)
     (map #(get data/cell-map
                (get s %)) r))))

(defn grid-to-string [grid]
  (apply
    str
    (map #(let [d (get grid %)]
            (if
              (> (count d) 1)
              d
              (first d)))
         data/cells)))

(defn blah [grid unit]
    (if (empty? unit)
      []
      (let [cell-value (get grid (first unit) #{}) ]
        (if (= (count cell-value) 1)
          (cons cell-value (blah grid (rest unit)))
          (blah grid (rest unit))))))

(defn consistent?_ [grid unitlist]
  (loop [ ul unitlist]
    (if (empty? ul)
      true
      (let [
            unit (first ul)
        ;   unified-unit  (map #(get grid % #{}) unit)
        ;   unified-facts (filter #(= (count %) 1) unified-unit)
           unified-facts (blah grid unit)
           ]
        (if (= (count unified-facts) (count (set unified-facts)))
          (recur (rest ul))
          false)))))

(defn consistent? [grid]
  (consistent?_ grid data/unitlist))

(defn solved? [grid]
  (let [values (vals grid)
        list-with-multi-cardinality (drop-while #(= (count %) 1) values)]
    (cond
      (= (count list-with-multi-cardinality) 0) true
      :else false)))

(defn conflict? [grid cell domain]
  (let [ps (data/peers cell)]
    (cond
      (some #(= (get grid % ) domain) ps)
      true
      :else  false)))

(defn count-0 [sequ] (= 0 (count sequ)))

(defn reduce-domain [grid domain peers]
  (let [peer (first peers) current-domain (grid peer) ]
    (cond
      (count-0 peers) grid
      (and (contains? current-domain domain) (> (count current-domain) 1))
        (let [diff (disj current-domain domain)
              g (reduce-domain (assoc grid peer diff) domain (rest peers))]
        (cond
          (= (count diff) 1)
          (reduce-domain g (first diff) (data/peers peer))
          :else g))
      :else
      (reduce-domain grid domain (rest peers)))))

(defn assign [grid cell domain]
  (cond
    (= false (conflict? grid cell domain))
    (reduce-domain
      (assoc grid cell #{domain})
      domain
      (data/peers cell))
    :else grid))

(declare backtrack)

(defn backtrack-in-domain [unresolved grid cell domain]
  (loop [d domain]
    (if (empty? d)
      {}
      (let [g (backtrack (assign grid cell (first d)) unresolved) ]
        (if (empty? g)
          (recur (rest d))
          g)))))


(defn backtrack [grid unresolved]
  (let [ solved (solved? grid) consistent (consistent? grid) ]
  (cond
    (and solved consistent) grid
    (count-0 unresolved) {}
    :else
      (if consistent
        (let
          [cell (first unresolved)]
          (backtrack-in-domain (rest unresolved) grid cell (grid cell))) {}))))

(defn get-unresolved [grid]
  (filter
    #(not= 1 (count (get grid % #{})))
    (keys grid)))

(defn get-resolved [grid]
  (filter
    #(= 1 (count (get grid % #{})) )
    (keys grid)))

(defn assign-facts [grid facts]
  (if (empty? facts)
    grid
    (let [cell (first (first facts)) domain (second (first facts)) ]
      (assign
        (assign-facts
          grid
          (rest facts))
        cell domain))))

(defn solve [string]
  (let [
    grid (string-to-grid string)
    empty-grid (string-to-grid (take (* data/unit-size data/rows-in-quadrant data/cols-in-quadrant) (repeat "0")))
    resolved (map #(list % (first (get grid %))) (get-resolved grid))
    grid-ready (assign-facts empty-grid resolved)
    unresolved (sort (get-unresolved grid-ready))
   ]
    (grid-to-string (backtrack grid-ready unresolved))))
