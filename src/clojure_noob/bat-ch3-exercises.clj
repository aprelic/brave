(ns clojure-noob.bat-ch3-exercises
  (:gen-class))

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
(str "Test " "A" "," "B")
;; "Test A,B"

(vector [1 2 3])
;; [[1 2 3]]

(list [1 2 3])
;; ([1 2 3])

(list 1 2 3)
;; (1 2 3)

(list? (list 1 2 3))
;; true

;; (hash-map {:a 1 :b 2})
(hash-map [1 2] :a)
;; {[1 2] :a}

((hash-map [1 2] :a) [1 2])
;; :a

((hash-map [1 2] :a) :a)
;; nil

(println (hash-map [1 2] :a))
;; nil

(hash-map 1 :b)
;; {1 :b}

((hash-map 1 :b) 1)
;; :b

((hash-map 1 :b) :b)
;;nil

(hash-map :a 1)
;; {:a 1}

(hash-set)
;; #{}

(println (hash-set ))
;; nil

(hash-set 1 :a 1 3 3 :b :c)
;; #{1 :c 3 :b :a}

(println (hash-set 1 :a 1 3 3 :b :c))
;; nil

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
(defn add100
  [x]
  (+ x 100))

(add100 5)
;; 105

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
(defn dec-maker
  [dec-by]
  #(- % dec-by))

(def dec9 (dec-maker 9))
(dec9 20)
;; 11

(defn dec-maker-2
  [dec-by]
  (fn [y] (- y dec-by))) ; using full notation

(def dec9-2 (dec-maker-2 9))
(dec9-2 20)
;; 11

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
(map inc [1 2 3])
;; (2 3 4)

(defn mapset
  [fn coll]
  (into #{} (map fn coll)))

(mapset inc [1 2 3])
;; #{4 3 2}

(mapset inc [1 2 3 3 4])
;; #{4 3 2 5}

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;




