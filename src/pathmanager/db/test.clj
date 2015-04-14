(ns pathmanager.db.test
  (:require
    [yesql.core :refer [defqueries]]
    [clojure.java.io :as io]))

(def db-store (str (.getName (io/file ".")) "/site.db"))

(def db-spec
  {:classname   "org.h2.Driver"
   :subprotocol "h2"
   :subname     "mem:test_db"
   :make-pool?  true
   :naming      {:keys   clojure.string/lower-case
                 :fields clojure.string/upper-case}})


(defqueries "sql/queries.sql" {:connection db-spec})
