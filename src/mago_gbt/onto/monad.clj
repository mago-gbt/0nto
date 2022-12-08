(ns mago-gbt.onto.monad)

(defn state-monad-transformer [m]
      {:mresult (fn [v]
                   (fn [s]
                       ((:mresult m) [v s])))

       :mbind (fn [stm f]
                 (fn [s]
                     ((:mbind m) (stm s)
                                 (fn [[v s']]
                                   ((f v) s')))))

       :mzero (fn [s]
                (:mzero m))

       :mplus (fn [stm stm']
                 (fn [s]
                     ((:mplus m) (stm s)
                                 (stm' s))))

       :mupdate (fn [f]
                    (fn [s]
                        (if (empty? s)
                            (:mzero m)
                            ((:mresult m) [s (f s)]))))})

(def list-monad
     {:mresult (fn [x]
                   [x])
      :mbind (fn [a f]
                 (if (= a [])
                     []
                     (into [] (mapcat f a))))
      :mzero []
      :mplus (fn [a ys]
                 (if (= a [])
                     ys
                     (into a ys)))})

(def parser-monad (state-monad-transformer list-monad))