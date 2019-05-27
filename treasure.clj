(defn Reader []
    "Reads the file from the disk"
    (with-open [rdr (clojure.java.io/reader "map.txt")]
        (vec (reduce conj [] (line-seq rdr)))))
               
(def input (Reader))
; (print (count input))
; (print (get input 7))
(defn get_chars_from_input []
    "Getting characters from input"
    (loop [i 0
        temp_vec []]
         (if (< i (count input))
            (recur (inc i) (conj temp_vec (into [] (seq (get input i))))) 
                temp_vec
                )))
  
        
(def input_vec (get_chars_from_input))

(defn print_input [in]
    "It prints the input vector"
    (doseq [i in]
        (println i)))
;(print (get input_vec 7))
(print_input input_vec)
(println (first (get input_vec 3)))
(def a (to-array-2d input_vec))


(defn create-empty
    "Create an empty rectangular maze."
    [rows cols]
    (vec (take rows (repeat (vec (take cols (repeat #{})))))))

(create-empty 4 4)

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




