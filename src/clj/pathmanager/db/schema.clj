(ns pathmanager.db.schema
  (:require [clojure.java.jdbc :as sql]
            [clojure.java.io :refer [file]]
            [noir.io :as io]))

(def db-store (str (.getName (file ".")) "/site.db"))

(def db-spec {:classname "org.h2.Driver"
              :subprotocol "h2"
              :subname  db-store
              :user ""
              :password ""
              :make-pool? true
              :naming {:keys clojure.string/lower-case
                       :fields clojure.string/upper-case}})
;(defn initialized?
;  "checks to see if the database schema is present"
;  []
;  (.exists (file (str db-store ".h2.db"))))

;(defn create-users-table
;  []
;  (sql/db-do-commands
;    db-spec
;    (sql/create-table-ddl
;      :users
;      [:id "varchar(20) PRIMARY KEY"]
;      [:first_name "varchar(30)"]
;      [:last_name "varchar(30)"]
;      [:email "varchar(30)"]
;      [:admin :boolean]
;      [:last_login :time]
;      [:is_active :boolean]
;      [:pass "varchar(100)"])))

;(defn create-player-table
;  []
;  (sql/db-do-commands
;    db-spec
;    (sql/create-table-ddl
;          :player
;    [:id :serial "PRIMARY KEY"]
;    [:name "varchar(30)"])))

;(defn create-character-table
;  []
;  (sql/db-do-commands
;    db-spec
;    (sql/create-table-ddl
;   :character
;   [:name "varchar(30)"]
;   [:player :serial "references player (id)"])))


;;(defn create-character-table
;;  db-spec
;;  (sql/create-table-ddl
;;   :character
;;   [:id :serial "PRIMARY KEY"]
;;  [:player :serial "references player (id)"]
;; ))


;(defn create-tables
;  "creates the database tables used by the application"
;  []
;  (create-users-table)
;  (create-player-table)
;  (create-character-table)
;)
