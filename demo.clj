(ns apni.foo
    (:require [clojure.string :as str]))
;map
(def myMap{
    :key1 (fn [] (print "keyvalue1"))
    :key2 (fn [] (print "keyvalue2"))
    :key3 {:key31 "key31Value", :key32 "not found"}
})

; (get myMap :key3)
 (get-in myMap [:key3 :key42])
(vals myMap)
(contains? myMap :key31)
    

(def vector1 [
    "dog", 3, 6, (fn [] "fn")
])
(def list1 `(3, 4, 3, {:op "C"}))

(get vector1 3)
(get (nth list1 3) :op)

;FUNCTIONS
(defn mynavaNavaFunction 
    "MyFunctions DocString"
([p1 p2 p3]
(println p1, p2, p3))
([]
(println "Nothing"))
([p1 p2]
(+ p1 p2))
    
    )

    (mynavaNavaFunction 2 3)
    (mynavaNavaFunction )
    (mynavaNavaFunction 3 3 3)

(defn stringFunc
    []
    (print (closure.string/trim "    adf")))

(stringFunc)

(def listApni `(1, 3, 5, 0, 5, 2, 8, 45, 23))

(defn functionSumList 
    "Function to get sum of elements of a list"

    ([listARg total]
    (if (empty? listARg)
       total
        (recur (rest listARg) (+ total (first listARg)))))
        )

(println (functionSumList [3 3 3 34 34 3] 0))


(use 'clojure.core.matrix')

(def M  [[1 2 3]
         [4 5 6]
         [7 8 9]])

