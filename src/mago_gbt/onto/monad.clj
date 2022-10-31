(ns mago-gbt.onto.monad)

(defn state-monad-transformer [m]
  {:result (fn [v]
               (fn [s]
                   ((:result m) [v s])))
   :bind (fn [stm f]
             (fn [s]
                 ((:bind m) (stm s)
                            (fn [[v s']]
                              ((f v) s')))))
   :zero (fn [s]
             (:zero m))
   :plus (fn [stm stm']
             (fn [s]
                 ((:plus m) (stm s)
                            (stm' s))))
   :update (fn [f]
               (fn [s]
                   (if (empty? s)
                       (:zero m)
                       ((:result m) [s (f s)]))))})

(def list-monad
  {:result (fn [x]
               [x])
   :bind (fn [a f]
             (if (= a [])
                 []
                 (into [] (mapcat f a))))
   :zero []
   :plus (fn [a ys]
             (if (= a [])
                 ys
                 (into a ys)))})

(def parser-monad (state-monad-transformer list-monad))