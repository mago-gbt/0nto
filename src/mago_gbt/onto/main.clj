(ns mago-gbt.onto.main)

(defn oget [o]
      (fn [k]
          ((k o) o)))

(defn ocall [o]
      (fn [k & args]
          (apply (((:oget o) o) k)
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
     {:oget oget
      :ocall ocall
      :otransform otransform
      :oreduce oreduce
      :oregister oregister})
