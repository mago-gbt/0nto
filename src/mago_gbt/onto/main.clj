(ns mago-gbt.onto.main)

(defn oreduce [o]
      (fn ([rf elems]
           ((oreduce o) rf
                        (first elems)
                        (rest elems)))
          ([rf acc elems]
           (if (not (empty? elems))
               ((oreduce o) rf
                            (rf acc (first elems))
                            (rest elems))
               acc))))

(defn oregister [o]
      (fn [n f]
          (assoc o n f)))

(def base
     {:oreduce oreduce
      :oregister oregister})
