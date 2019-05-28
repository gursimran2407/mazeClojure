;Author: Gursimran Singh
;Treasure Game Closure
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
        (println (join i))))
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

(defn outside_maze [x y maze]
    "Checks if we are outside the maze"
    (def n (count maze))
    (def m (count (get maze 0)))
    (cond
        (> x (dec n)) true
        (> y (dec m)) true
        :else false))

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

(defn rah [x y]
    "Checks if goal is found or not"
    (def goal (get_element x y))
    ;(pprint goal)
    (if (= (trim (str goal)) "-")
        true
        false)
)
;(blocked 0 3)

(defn setFlag [x]
    "Set flag to true "
    (def flag (atom x))
    (add-watch flag :watcher
        (fn [key atom old-state new-state]
            ))
    
)

(defn path_finder [x y maze]
    "Path finder"
    (def temp_maze (assoc-in maze [x y] "+" ))
    ; (print_input temp_maze)
    ; (println)
    (if (and (not (outside_maze (dec x) y temp_maze)) (= (get_element (dec x) y temp_maze) "@"))
    (do (println "\nWoo hoo, I found the treasure :-)\n")
    (print_input temp_maze) 
    (reset! flag 0)))
    (if (and (not (outside_maze (inc x) y temp_maze)) (= (get_element (inc x) y temp_maze) "@"))
    (do (println "\nWoo hoo, I found the treasure :-)\n")
    (print_input temp_maze) 
        (reset! flag 0)))
    (if (and (not (outside_maze x (dec y) temp_maze)) (= (get_element x (dec y) temp_maze) "@"))
    (do (println "\nWoo hoo, I found the treasure :-)\n")
    (print_input temp_maze) 
        (reset! flag 0)))
    (if (and (not (outside_maze x (inc y) temp_maze)) (= (get_element x (inc y) temp_maze) "@"))
    (do (println "\nWoo hoo, I found the treasure :-)\n")
    (print_input temp_maze) 
        (reset! flag 0)))

    (if (and (not (outside_maze (dec x) y temp_maze)) (= (get_element (dec x) y temp_maze) "-"))
        (def temp_maze (path_finder (dec x) y temp_maze)))
    (if (and (not (outside_maze (inc x) y temp_maze)) (= (get_element (inc x) y temp_maze) "-"))
        (def temp_maze (path_finder (inc x) y temp_maze)))
    (if (and (not (outside_maze x (dec y) temp_maze)) (= (get_element x (dec y) temp_maze) "-"))
        (def temp_maze (path_finder x (dec y) temp_maze)))
    (if (and (not (outside_maze x (inc y) temp_maze)) (= (get_element x (inc y) temp_maze) "-"))
        (def temp_maze (path_finder x (inc y) temp_maze)))

    (assoc-in temp_maze [x y] "!" )
)

(defn print_file []
    "Prints the map from the disk"
    (slurp "map.txt"))

(defn -main [& args]
    "The main function"
    (println "This is my challenge:\n")
    (println (print_file))
    (setFlag 1)
    (def input_maze (get_chars_from_input))
;(print (get_element 7 11 input_maze))
    (def solution_final (path_finder 0 0 input_maze))


    (if (= @flag 1)
        (do (println "\nUh oh, I could not find the treasure :-(\n")
        (print_input solution_final))
        )
    )
   


(-main)




