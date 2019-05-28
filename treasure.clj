(use 'clojure.pprint)
(use 'clojure.string)

(defn Reader []
    "Reads the file from the disk"
    (with-open [rdr (clojure.java.io/reader "map.txt")]
        (vec (reduce conj [] (line-seq rdr)))))
               
; (print (count input))
; (print (get input 7))
(defn get_chars_from_input []
    "Getting characters from input"
    (loop [i 0
        temp_vec []]
         (if (< i (count (Reader)))
            (recur (inc i) (conj temp_vec (into [] (seq (get (Reader) i))))) 
                temp_vec
                )))
  

(defn print_input [in]
    "It prints the input vector"
    (doseq [i in]
        (println i)))
;(print (get input_vec 7))
;(print_input input_vec)
(defn get_element [x y maze]
    "Getting the element from input vector"
    (let [temp (trim (str (get-in maze [x y])))]
        temp)
    )

;(get_element 0 12)
;(pprint input_vec)
; (def maze (to-array-2d input_vec))
; ;The dimensions of the maze
; (pprint maze)

;(aget maze 7 12)

(defn outside_maze [x y]
    "Checks if we are outside the maze"
    (cond
        (> x (dec n)) false
        (> y (dec m)) false
        :else true))

;(outside_maze 0 0)

(defn found_goal [x y]
    "Checks if goal is found or not"
    (def goal (get_element x y))
    ;(pprint goal)
    (if (= (trim (str goal)) "@")
        true
        false)
)
;(found_goal 7 12)

(defn blocked [x y]
    "Checks if goal is found or not"
    (def goal (get_element x y))
    ;(pprint goal)
    (if (= (trim (str goal)) "#")
        true
        false)
)
;(blocked 0 3)

(defn mark_solution [x y]
    "This function marks a position as possible path"
    (def input_vec)
    (swap! (assoc-in input_vec [x y] "+" ))
    )

(mark_solution 2 3)
(defn path_finder
      "Find the path"
      ([x, y]
        (if (outside_maze x y)
            false
            (if (found_goal x y)
                true
                (if (blocked x y)
                    false
                    (mark_solution x y)
                    (if (path_finder (+ x 1) (+ y 1))
                        true)
                    (if (path_finder (+ x 1) y)
                        true)
                    (if (path_finder (- x 1) (- y 1))
                        true)
                    (if (path_finder x (+ y 1))
                        true)
                    (unmark x y)
                    false
                )))))

(defn print_file []
    "Prints the map from the disk"
    (slurp "map.txt"))

(defn -main [& args]
    "The main function"
    (println "This is my challenge:")
    (println (print_file))
    
    (def input_vec (get_chars_from_input))
    )
    (def n (count input_vec))
    (def m (count (get input_vec 0)))
    (format "n: %d and m: %d" n m)

(-main)




