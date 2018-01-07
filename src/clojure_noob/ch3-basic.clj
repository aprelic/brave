(ns clojure-noob.ch3-basics
  (:gen-class))

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;;;
;; LITERALS
;;;
1
"a string"
["a" "vector" "of" "strings"]

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;;;
;; OPERATIONS
;;;
(+ 1 2 3 4)
;; 10

(str "It was the panda " "in the library " "with a dust buster")
;; "It was the panda in the library with a dust buster"

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;;;
;; CONTROL FLOW
;;;

(if true
  "By Zeus's hammer!"
  "By Aquaman's hammer!")
;; "By Zeus's hammer!"

(if false
  "By Zeus's hammer!"
  "By Aquaman's hammer!")
;; "By Aquaman's hammer!"

(if true
  "It is true!")
;; "It is true!"

(if false
  "It is true!")
;; nil

;; (do ...) ... allows to wrap up multiple forms in pars and run each of them
(if true
  (do (println "Success!")
      "By Zeus's hammer!")
  (do (println "Failure!")
      "By Aquaman's hammer!"))
;; "By Zeus's hammer!"

;; when is combination of if and do, no else
;; use it to do multiple things if true, and return nil if false
(when true
  (println "Success!")
  "abra cadabra")
;; "abra cadabra"

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;;;
;; TRUE FALSE NIL
;;;
(nil? 1)
;; false

(nil? nil)
;; true

;; values are truthey or falsey, nil and false are falsey, everything else is truthey!
(if "bears eat beets"
  "bears beets Battlestar Galactica")
;; "bears beets Battlestar Galactica"

(if nil
  "This won't be the result because nil is falsey"
  "nil is falsey")
;; "nil is falsey"

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;;;
;; EQUALITY
;;;
(= 1 1)
;; true

(= nil nil)
;; true

(= 1 2)
;; false

(= "a" "b")
;; false

(= "a" "a")
;; true

(= :a :a)
;; true

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;; OR  ... RETURNS FIRST TRUTHY VALUE OR THE LAST VALUE
;; AND ... RETURNS THE FIRST FALSEY VALUE, OR IF NOT VALUES ARE FALSEY, THE LAST TRUTHY VALUE
;;;

(or false nil :large_I_mean_venti :why_cant_I_just_say_large)
;; :large_I_mean_venti

(or (= 0 1) (= "yes" "no"))
;; false

(or (= 0 1) (= "yes" "no") nil)
;; nil

(or nil)
;; nil

(and :free_wifi :hot_coffee)
;; :hot_coffee

(and nil)
;; nil

(and true false nil)
;; false

(and true nil false)
;; nil

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;; DEF ... USED TO BIND A NAME TO A VALUE

(def failed-protagonist-names
  ["Larry Potter" "Doreen the Explorer" "The Incredible Bulk"])
;; #'playground.core/failed-protagonist-names

failed-protagonist-names
;; ["Larry Potter" "Doreen the Explorer" "The Incredible Bulk"]

;; VERY bad coding example - changing value of a variable
(def severity :mild)
(def err-msg "oh God! It's a disaster! We're ")
(if (= severity :mild)
  (def err-msg (str err-msg "midly inconvenienced!"))
  (def err-msg (str err-msg "doomed!")))
;; #'playground.core/err-msg

err-msg
;; "oh God! It's a disaster! We're midly inconvenienced!"

;; BETTER coding example
(defn err-message
  [severity]
  (str "Oh God! It's a disaster! We're "
       (if (= severity :mild)
         "mildly inconvenienced!"
         "doomed!")))

(err-message :mild)
;; "Oh God! It's a disaster! We're mildly inconvenienced!"

(err-message :other)
;; "Oh God! It's a disaster! We're doomed!"

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;; DATA STRUCTURES - all data structures are immutable

failed-protagonist-names
;; ["Larry Potter" "Doreen the Explorer" "The Incredible Bulk"]

;; failed-protagonist-names = ["Larry Potter" "Doreen the Explorer" "The Incredible Bulk"]
;; ["Larry Potter" "Doreen the Explorer" "The Incredible Bulk"]
;;
;; failed-protagonist-names[0] = "Garry Potter"
;;
;; failed-protagonist-names
;; ["Larry Potter" "Doreen the Explorer" "The Incredible Bulk"]


;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;; NUMBERS
93
1.2
1/5

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;; STRINGS
"Lord Vordermort"
;; "Lord Vordermort"

"\"He who must not be named\""
;; "\"He who must not be named\""

"\"Great cow of Moscow!\" - Hermes Conrad"
;; "\"Great cow of Moscow!\" - Hermes Conrad"

(def cname "Chewbacca")
(str "\"Uggllglglglglglglglll\" - " cname)
;; "\"Uggllglglglglglglglll\" - Chewbacca"

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;; MAPS - hash maps / sorted maps

{}
;; {}

{:1st-name "Me"
 :2nd-name "MMe"}
;; {:1st-name "Me", :2nd-name "MMe"}

{"string-key" +}
;; {"string-key" #object[clojure.core$_PLUS_ 0xfd124c1 "clojure.core$_PLUS_@fd124c1"]}

{:name {:first "John" :middle "Jacob" :last "Schmid"}}
;; {:name {:first "John", :middle "Jacob", :last "Schmid"}}

(hash-map :a 1 :b 2)
;; {:b 2, :a 1}

(get {:a 1 :b 2} :a)
;; 1

(get {:a 0 :b {:c "ho hum"}} :b)
;; {:c "ho hum"}

(get {:a 0 :b {:c "ho hum"}} :c)
;; nil

(get-in {:a 0 :b {:c "ho hum"}} [:b :c])
;; "ho hum"

(get {:a 0 :b 1} :c)
;; nil

(get {:a 0 :b 1} :c "unicorns?")
;; "unicorns?" -> fallback value!

(get-in {:a 0 :b {:c "ho hum"}} [:b :c])
;; "ho hum"

({:name "Name"} :name)
;; "Name"

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;; KEYWORDS - evaluate to themselves (symbols)

:a
:rumplestiltsken
:34
:_?

(:a {:a 1 :b 2})
;; 1

(get {:a 1 :b 2} :a) 
;; 1

(:c {:a 1 :b 2} "fallback")
;; "fallback"

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;; VECTORS

[1 2 3]
;; [1 2 3]

(get [1 2 3] 0)
;; 1

(get [1 2 3] 2)
;; 3

(get [1 2 3] 3)
;; nil

(get ["a" {:name "Pugsley"} "c"] 1)
;; {:name "Pugsley"}

(vector "creepy" "full" "moon")
;; ["creepy" "full" "moon"]

(conj [1 2 3] 4)
;; [1 2 3 4] ... elements added to the END of the vector

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;; LISTS

'(1 2 3)
;; (1 2 3)

(nth '(:a :b :c) 0)
;; :a

(nth '(:a :b :c) 2)
;; :c

(list 1 "a" {3 4})
;; (1 "a" {3 4})

(conj '(1 2) 3)
;; (3 1 2) - elements added to the BEGINNING of the list!

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;; SETS - hash sets / sorted sets

#{"kurt vonnegut" 20 :icicle}
;; #{20 :icicle "kurt vonnegut"}

(hash-set 1 1 2 2)
;; #{1 2}

(conj #{:a :b} :b)
;; #{:b :a}

(set [3 3 3 4 4])
;; #{4 3}

(contains? #{:a :b} :a)
;; true

(contains? #{:a :b} :c)
;; false ... as opposed to NIL

(get #{:a :b} :b)
;; :b

(:a #{:a :b :c})
;; :a

(:d #{:a :b :c})
;; nil ... as opposed to FALSE

(:d #{:a :b :c} "test")
;; "test"

(contains? #{nil} nil)
;; true

(get #{:a nil} nil)
;; nil


;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
