(ns build
  (:refer-clojure :exclude [test])
  (:require [clojure.tools.build.api :as b] ; for b/git-count-revs
            [org.corfield.build :as bb]))

(def lib 'mago-gbt/onto)
(def version "0.1.0")

(defn install "Install the JAR locally." [opts]
      (-> opts
          (assoc :lib lib :version version)
          (bb/install)))

(defn deploy "Deploy the JAR to Clojars." [opts]
      (-> opts
          (assoc :lib lib :version version)
          (bb/deploy)))
