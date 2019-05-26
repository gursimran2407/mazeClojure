(defn Reader []
    "Reads the file from the disk"
    (with-open [rdr (clojure.java.io/reader "map.txt")]
        (reduce conj [] (line-seq rdr))))
               
(def input (Reader))
(def input_Vector [])

(defn get_chars_from_input []
    
    "Getting characters from input"
        (doseq [i input]
        (println (conj input_Vector (into [] (seq i))))))

        (get_chars_from_input)

(print input_Vector)


(defn create-empty
    "Create an empty rectangular maze."
    [rows cols]
    (vec (take rows (repeat (vec (take cols (repeat "-")))))))

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




