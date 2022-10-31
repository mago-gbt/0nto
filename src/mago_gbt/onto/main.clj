(ns mago-gbt.onto.main)

(defn otransform [o]
                 (fn [f]
                     (fn [x]
                         ((f o) x))))

(defn oreduce (fn [o]
                  (fn ([rf elems]
                       ((oreduce o) rf
                                    (first elems)
                                    (rest elems)))
                      ([rf acc elems]
                       (if (not (empty? elems))
                           ((oreduce o) rf
                                        (rf acc (first elems))
                                        (rest elems))
                           acc)))))

(defn oregister (fn [o]
                    (fn [n f]
                        (assoc o n f))))

(def base
     {:transform otransform
      :reduce oreduce
      :register oregister})
