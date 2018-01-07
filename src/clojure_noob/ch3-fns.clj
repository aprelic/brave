(ns clojure-noob.ch3-fns
  (:gen-class))

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;; CALLING FUNCTIONS = o.par operator(fn, fn-expr) operands c.par

(+ 1 2 3 4)
;; 10

(* 1 2 3 4)
;; 24

(first [1 2 3 4])
;; 1

(or + -)
;; #object[clojure.core$_PLUS_ 0xfd124c1 "clojure.core$_PLUS_@fd124c1"]

((or + -) 2 3 5 6)
;; 16

((and (= 1 1) +) 1 2 3)
;; 6

((first [+ 0]) 1 2 3)
;; 6

;; (1 2 3 4)

;; prg languages with higher order functions are said to support first-class fns
;; because you can treat fns as values in the same way you treat other familiar
;; data types

(inc 1.1)
;; 2.1

(map inc [1 2 3])
;; (2 3 4)

;; all arguments are evaluated recursively before passing them to fns
(+ (inc 199) (/ 100 (- 7 2)))
;; 220

;; (+ 200 (/ 100 (- 7 2)))
;; (+ 200 (/ 100 5))
;; (+ 200 20)
;; 220

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;; HOW FunctioNS DIFFER FROM MACROS AND SPECIAL FORMS

;; special forms: if, def 
;; ... they don't always evaluate all of their operands
;; ... they can't be used as arguments to functions

;; macros
;; ... are similar to special forms
;; ... they can't be passed as args


;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;; DEFINING FNS

(defn too-enthusiastic
  "return a cheer that might too out"
  [name]
  (str "Oh. My. God! " name " Yor are the very best ever!"))

(too-enthusiastic "Zelda")
;; "Oh. My. God! Zelda Yor are the very best ever!"

;; (doc too-enthusiastic) -> "return a cheer that might too out"

(defn no-params
  "0-arity fn"
  []
  "I take no args!")

(no-params)
;; "I take no args!"

(defn one-param
  "1-arity fn"
  [x]
  (str "Concat " x))

(one-param 1)
;; "Concat 1"

(one-param "test")
;; "Concat test"

(defn two-params
  "2-arity fn"
  [x y]
  (str "Test Concat " x " and " y))

(two-params 1 2)
;; "Test Concat 1 and 2"

;; arity overloading
(defn multi-arity
  ;; 3-arity args
  ([a b c]
   (str a b c))
  ;; 2-arity args
  ([a b]
   (str a b))
  ;; 1-arity args
  ([a]
   (str a))
)

(multi-arity 1)
;; "1"

(multi-arity 1 2)
;; "12"

(multi-arity 1 2 3)
;; "123"

;; (multi-arity 1 2 3 4)

(defn x-chop
  ([name chop-type]
   (str "I " chop-type " chop " name "! Take that!"))
  ([name]
   (x-chop name "karate")))

(x-chop "Amie")
;; "I karate chop Amie! Take that!"

(x-chop "Amie" "slap")
;; "I slap chop Amie! Take that!"

(defn weird-arity
  ([]
   "Destiny dressed you this morning, my friend, and now Fear is
     trying to pull off your pants. If you give up, if you give in,
     you're gonna end up naked with Fear just standing there laughing
     at your dangling unmentionables! - the Tick")
  ([number]
   (inc number)))

(weird-arity)
;; "Destiny dressed you this morning, my friend, and now Fear is\n     trying to pull off your pants. If you give up, if you give in,\n     you're gonna end up naked with Fear just standing there laughing\n     at your dangling unmentionables! - the Tick"

(weird-arity 2)
;; 3

;; (weird-arity "a")

;; variable-arity fns are possible using a REST PARAMETER, & (must come last!)
(defn codger-communication
  [whippersnapper]
  (str "Get off my lawn, " whippersnapper "!!!"))

(defn codger
  [& whippersnappers]
  (map codger-communication whippersnappers))

(codger "Billy" "Anne-Marie" "The Incredible Bulk")
;; ("Get off my lawn, Billy!!!" "Get off my lawn, Anne-Marie!!!" "Get off my lawn, The Incredible Bulk!!!")

(defn fav-things
  [name & things]
  (str "Hi, " name ", here are my fav things: "
       (clojure.string/join ", " things)))

(fav-things "Amie" "lolipopp" "sun" "run")
;; "Hi, Amie, here are my fav things: lolipopp, sun, run"

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;; DESTRUCTURING FN ARGS - assign symbolic names to parts of (collection) args

(defn my-first
  "return 1st el of a collection"
  [[first-thing]] ; first-thing associated with the 1st el of the vector
  first-thing)

(my-first ["oven" "bike" "war-axe"])
;; "oven"

(defn chooser
  [[fst-choice snd-choice & rest-choice]]
  (println (str "Your 1st choice is " fst-choice))
  (println (str "Your 2nd choice is " snd-choice))
  (println (str "We're ignoring the rest of your choices. "
                "Here they are in case you need to cry over them: "
                (clojure.string/join ", " rest-choice))))

(chooser ["Marmelade" "Handsome Jack" "Pigpen" "Aquaman"])
;; nil


;; how to DESTRUCTURE maps
(defn announce-treasure-location
  [{lat :lat lng :lng}] ; binds name lat to value corresponding ot key :lat
  (println (str "Treasure lat: " lat))
  (println (str "Treasure lng: " lng)))

(announce-treasure-location {:lat 28.22 :lng 81.33})
;; nil

(defn announce-treasure-location2
  [{:keys [lat lng]}] ; another way to destruct map
  (println (str "Treasure lat: " lat))
  (println (str "Treasure lng: " lng)))

(announce-treasure-location2 {:lat 28.22 :lng 81.33})
;; nil

;; (defn receive-treasure-location
  ;; [{:keys [lat lng] :as treasure-location}]
  ;; (println (str "Treasure lat: " lat))
  ;; (println (str "Treasure lng: " lng))
  ;; one would assume that this would put in neww coords for you ship
  ;; (steer-ship! treasure-location))

;; destructuring = associating names with values in a list, vector, map, set

(defn ill-fn
  []
  (+ 1 304)
  30
  "joe")

(ill-fn)
;; "joe"

(defn number-comment
  [x]
  (if (> x 6)
    "Oh my gosh! What a big number!"
    "That number's OK, I guess"))

(number-comment 1)
;; "That number's OK, I guess"

(number-comment 10)
;; "Oh my gosh! What a big number!"

;; In clojure there are no privileged fns, +, inc, map are all created equal

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;; ANONYMOUS FNS

;; (fn [param-list] fn body)

(map (fn [name] (str "Hi, " name))
     ["Darth Vader" "Mr. Magoo"])
;; ("Hi, Darth Vader" "Hi, Mr. Magoo")

((fn [x] (* x 3)) 8)
;; 24

(def my-special-multiplier (fn [x] (* x 3)))
(my-special-multiplier 12)
;; 36

;; here is a more compact way to define anon-fns'
#(* % 3) 

(#(* % 3) 10) ; feature: reader macros
;; 30

(map #(str "Hi, " %) ["Darth" "Mr."])
;; ("Hi, Darth" "Hi, Mr.")

(* 8 3)
;; 24

(#(* % 3) 8) ; % argument passed to anon fn, for multiple args use %1 %2 %3 ... (% is equiv to %1)
;; 24

(#(str %1 " and " %2) "cornbread" "butter beans")
;; "cornbread and butter beans"

;; can also be combined with REST!
(#(identity %&) 1 "blarg" :yip)
;; (1 "blarg" :yip)

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;; RETURNING FNS

;; Fns can return fns. The returned fns are CLOSURES, as they
;; can access all the vars that were in scope when the fn was created

(defn inc-maker
  "create a custom incrementor"
  [inc-by]
  #(+ % inc-by))

(def inc3 (inc-maker 3))

(inc3 10)
;; 13

(def inc10 (inc-maker 10))
(inc10 20)
;; 30

(defn dec-maker
  [dec-by]
  #(- % dec-by))

(def dec1 (dec-maker 1))
(dec1 10)
;; 9

(defn *-maker
  [*-by]
  #(* % *-by)) ; returns a fn which is a closure (sees, uses *-by)

(def *2 (*-maker 2))
(*2 15)
;; 30

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;; LET .. binds names to values

(let [x 3]
  x)
;; 3

(def dalmatian-list
  ["Pongo" "Perdita" "Puppy 1" "Puppy 2"])
(let [dalmatians (take 2 dalmatian-list)]
  dalmatians)
;; ("Pongo" "Perdita")

(let [[pongo & dalmatians] dalmatian-list] ; used with destructuring
  [pongo dalmatians])
;; ["Pongo" ("Perdita" "Puppy 1" "Puppy 2")]

(def x 0)
(let [x 1] x) ;; let introduces new scope!
;; 1

(def x 0)
(let [x (inc3 x)] x) ;; let refers to pre-existing bindings!
;; 3

;; LET - two main uses: 
;; 1) naming things (clarity)
;; 2) allow you to eval expr only once and reuse the result

(set ["a" "a" "b"])
;; #{"a" "b"}

(into [] (set [:a :a]))
;; [:a]

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;; LOOP

(loop [iteration 0]
  (println (str "Iteration " iteration))
  (if (> iteration 3)
    (println "Goodbye!")
    (recur (inc iteration)))) 
;; as if... loop creates anon fn with arg iteration
;;      ... recur calls fn within itself with arg iteration


(defn recursive-printer
  ([]
   (recursive-printer 0))
  ([iteration]
   (println iteration)
   (if (> iteration 3)
     (println "Goodbye!")
     (recursive-printer (inc iteration)))))

(recursive-printer)
;; nil

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;; REGULAR EXPR'S

(re-find #"^left-" "left-eye")
;; "left-"

(re-find #"^left-" "cleft-eye")
;; nil

(re-find #"^left-" "wonglebart")
;; nil

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;; REDUCE - prc each elem in a seq and build a res

(+ 1 2 3 4)
;; 10

(reduce + [1 2 3 4]) ; equiv to (+ (+ (+ 1 2) 3) 4)
;; 10

(reduce + 15 [1 2 3 4]) ; 15 init val
;; 25


;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;


