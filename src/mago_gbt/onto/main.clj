(ns mago-gbt.onto.main)

(defn ogetf [o]
      (fn [k]
          ((k o) o)))

(defn ocall [o]
      (fn [k & args]
          (apply ((((:ogetf o) o) k) o)
                 args)))

(defn otransform [o]
      (fn [f]
          (fn [x]
              ((f o) x))))

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
     {:getf ogetf
      :call ocall
      :transform otransform
      :reduce oreduce
      :register oregister})
