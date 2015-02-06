(ns pathmanager.db.core
  (:use korma.core
        [korma.db :only (defdb)])
  (:require [pathmanager.db.schema :as schema]))

(defdb db schema/db-spec)

(defentity characters)
(defentity players
  (has-many characters))

(defn create-player [player]
  (insert players
          (values {:name player})))

(defn update-player [id name]
  (update players
  (set-fields {:name name
               })
  (where {:id id})))

(defn get-player [id]
  (first (select players
                 (where {:id id})
                 (limit 1))))

(defn get-characters-for-player [id]
  (select characters 
          (where {:player id})))

(defn get-players []
  (select players))
